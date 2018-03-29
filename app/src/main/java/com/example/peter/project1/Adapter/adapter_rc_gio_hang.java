package com.example.peter.project1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.project1.ChiTietActivity;
import com.example.peter.project1.GioHangActivity;
import com.example.peter.project1.Model.SanPham;
import com.example.peter.project1.R;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;
import static com.example.peter.project1.GioHangActivity.setTongTien;
import static com.example.peter.project1.GioHangActivity.updateArraylistgiohang;
/**
 * Created by daovip on 3/27/2018.
 */

public class adapter_rc_gio_hang extends RecyclerView.Adapter<adapter_rc_gio_hang.View1SanPham> {
    ArrayList<SanPham> arrayList;
    Context c;
   Activity activity;
   int tong;
    int beforeSoluong;
    String chuoiEdit="";
    public adapter_rc_gio_hang(ArrayList<SanPham> arrayList,Context c,Activity activity,int tong) {
        this.arrayList = arrayList;
        this.c=c;
        this.activity=activity;
        this.tong=tong;
    }

    @Override
    public View1SanPham onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view1o_giohang,parent,false);
        return new View1SanPham(view);
    }

    @Override
    public void onBindViewHolder(final View1SanPham holder, final int position) {
        final String tenSp =arrayList.get(position).getTenSanPha();
        final int giaSp=arrayList.get(position).getDongia();
        final int hinhSp=arrayList.get(position).getHinh();
        final int soluong=arrayList.get(position).getSoluong();
        final SanPham sp = new SanPham(tenSp,giaSp,hinhSp,soluong);
        holder.et_soluong_giohang.setText(soluong+"");
        holder.tv_giasp_giohang.setText(giaSp+"");
        holder.tv_tensp_giohang.setText(tenSp);
        holder.img_hinhsp_giohang.setImageResource(hinhSp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(c,ChiTietActivity.class);
                i.putExtra("SanPham",sp);
                startActivity(c,i,null);
            }
        });
        //
        holder.img_xoa_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(c, ""+position, Toast.LENGTH_SHORT).show();
                arrayList.remove(position);
                // updateArraylist to GioHangActivity
                updateArraylistgiohang(arrayList);
                notifyDataSetChanged();
            }
        });
        // set forcusable editext
        holder.et_soluong_giohang.setFocusable(false);
        //
        holder.et_soluong_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set forcusable true when click
                v.setFocusableInTouchMode(true);
                v.setFocusable(true);
            }
        });
        holder.et_soluong_giohang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("ONtext changed " + new String(charSequence.toString()));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("beforeTextChanged " + new String(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                beforeSoluong=sp.getSoluong();
                System.out.println("afterTextChanged " + new String(editable.toString()));
                chuoiEdit=editable.toString();
//                sp.setSoluong(Integer.parseInt(editable.toString()));
//                Toast.makeText(c, "trước khi:"+beforeSoluong+"\n sau "+sp.getSoluong(), Toast.LENGTH_SHORT).show();
//               Log.d("AAA","dang edit");
            }
        });
        AddVentforKeybord(holder.et_soluong_giohang,position,sp);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class View1SanPham extends RecyclerView.ViewHolder{
        public ImageView img_hinhsp_giohang,img_xoa_giohang;
        public TextView tv_tensp_giohang,tv_giasp_giohang;
        public EditText et_soluong_giohang;
        public View1SanPham(View itemView) {
            super(itemView);
            et_soluong_giohang=itemView.findViewById(R.id.et_soluong_giohang);
            img_hinhsp_giohang=itemView.findViewById(R.id.img_hinhsp_giohang);
            tv_tensp_giohang=itemView.findViewById(R.id.tv_tensp_giohang);
            tv_giasp_giohang=itemView.findViewById(R.id.tv_giasp_giohang);
            img_xoa_giohang=itemView.findViewById(R.id.img_xoa);


        }

    }
    public  void AddVentforKeybord(final EditText edt, final int position, final SanPham sp){
        KeyboardVisibilityEvent.setEventListener(
                activity,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        // some code depending on keyboard visiblity status
                        if(isOpen){

//                            Toast.makeText(c, "đã mở", Toast.LENGTH_SHORT).show();
                        }else{
                            //update soluong
                            if(chuoiEdit.trim().length()>0){
                                if(Integer.parseInt(chuoiEdit.trim())<=0){
                                    AlertDialogError();
                                    edt.setText(1+"");
                                }else{
                                    sp.setSoluong(Integer.parseInt(chuoiEdit.toString()));
                                    tong=tong+((sp.getSoluong()*sp.getDongia())-(beforeSoluong*sp.getDongia()));
                                    setTongTien(tong);
                                    arrayList.set(position,sp);
                                }


//                  Log.d("AAA","tổng là "+ tong);
                            }
                            // updateArraylist to GioHangActivity when keyboard close
                            updateArraylistgiohang(arrayList);
                            edt.setFocusable(false);
//                            Toast.makeText(c, "so lượng:+"+arrayList.get(0).getSoluong(), Toast.LENGTH_SHORT).show();
//                            Log.d("AAA","");
                        }
                    }
                });
    }

    public void AlertDialogError(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                c);

        // set title
        alertDialogBuilder.setTitle("Thông báo");

        // set dialog message
        alertDialogBuilder
                .setMessage("Số lượng sản phẩm tối thiểu là 1")
                .setCancelable(false)
//                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // if this button is clicked, close
//                        // current activity
//                        activity.finish();
//                    }
//                })
                .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

}
