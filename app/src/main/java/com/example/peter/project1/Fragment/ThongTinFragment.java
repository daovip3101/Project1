package com.example.peter.project1.Fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.peter.project1.MainActivity;
import com.example.peter.project1.R;

import static com.example.peter.project1.MuaNgayActivity.setCurrentPage;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinFragment extends android.support.v4.app.Fragment {
    Button btn_tiep_tuc_thong_tin;
    EditText et_hoten_thongtin, et_sdt_thongtin, et_diachi_thongtin, et_email_thongtin, et_ghichu_thongtin;
    View v;
    String user_ten, user_email, user_diachi, user_sdt, user_ghichu;
    final static String ValidEmail = "Sai định dạng Email";
    final static String ValidSDT = "Sai định dạng Email";
    final static String ValidNull = "Bắt buộc nhập";

    public ThongTinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_thong_tin, container, false);
        Anhxa();
        ClickEvent();
        return v;
    }

    public void Anhxa() {
        et_hoten_thongtin = v.findViewById(R.id.et_hoten_thongtin);
        et_sdt_thongtin = v.findViewById(R.id.et_sdt_thongtin);
        et_diachi_thongtin = v.findViewById(R.id.et_diachi_thongtin);
        et_email_thongtin = v.findViewById(R.id.et_email_thongtin);
        et_ghichu_thongtin = v.findViewById(R.id.et_ghichu_thongtin);
    }

    public void ClickEvent() {
        //btn_tiep_tuc_thong_tin CLick
        getUserInput();
        btn_tiep_tuc_thong_tin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentPage(1);
            }
        });
    }

    public void getUserInput() {
        user_ten = et_hoten_thongtin.getText().toString();
        user_diachi = et_diachi_thongtin.getText().toString();
        user_email = et_email_thongtin.getText().toString();
        user_sdt = et_sdt_thongtin.getText().toString();
        user_ghichu = et_ghichu_thongtin.getText().toString();
        Validation();
    }

    public void Validation() {
            
    }

    public void AlertThongBao(String mesage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set title
        alertDialogBuilder.setTitle("Thông báo");

        // set dialog message
        alertDialogBuilder
                .setMessage(mesage)
                .setCancelable(false)

                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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


