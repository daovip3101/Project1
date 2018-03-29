package com.example.peter.project1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.project1.Adapter.adapter_rc_gio_hang;
import com.example.peter.project1.Model.SanPham;
import com.example.peter.project1.Service.OnClearFromRecentService;

import java.util.ArrayList;


public class GioHangActivity extends AppCompatActivity {
    ImageButton img_btn_back_gio_hang;
    RecyclerView rc_giohang;
    static ArrayList<SanPham> arrayList_giohang;
    static TextView tv_giasp_giohang;
    int tong=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
        Anhxa();
        getDataFromSanPhamActivity();
//        setData();
        tinhtong();
        setUpRcGiohang();
        img_btn_back_gio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataToSever();
                sendBackArrayGioHangEditSanPhamActivity();
            }
        });
    }

    public void Anhxa() {
        tv_giasp_giohang = findViewById(R.id.tv_giasp_giohang);
        img_btn_back_gio_hang = findViewById(R.id.img_btn_back_gio_hang);
        rc_giohang = findViewById(R.id.rc_giohang);
    }

    public void setUpRcGiohang() {
        adapter_rc_gio_hang adapter_rc_gio_hang = new adapter_rc_gio_hang(arrayList_giohang, GioHangActivity.this, GioHangActivity.this,tong);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rc_giohang.setLayoutManager(layoutManager);
        rc_giohang.setAdapter(adapter_rc_gio_hang);
    }

    public void tinhtong() {
        for (int i = 0; i < arrayList_giohang.size(); i++) {
            int dongia=arrayList_giohang.get(i).getDongia();
            int soluong=arrayList_giohang.get(i).getSoluong();
            tong = tong + (dongia*soluong);
        }
        setTongTien(tong);


    }

       public void getDataFromSanPhamActivity() {
        arrayList_giohang = (ArrayList<SanPham>) getIntent().getSerializableExtra("arrayGioHang");
//           Toast.makeText(this, "Đã Nhận danh sách giỏ hàng từ SanphamAcTivity - "+arrayList_giohang.size(), Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<SanPham> getArryGioHang() {
        return arrayList_giohang;
    }

    public void setData() {
        for (int i = 0; i < 20; i++) {
            SanPham sp = new SanPham("sp" + i, 5 + i * 10, R.drawable.garan,1);
            arrayList_giohang.add(sp);
        }

    }
    public static void setTongTien(int tong){
        tv_giasp_giohang.setText(tong + "đồng");
    }
    public  static void updateArraylistgiohang(ArrayList<SanPham> arrayList){
            arrayList_giohang=arrayList;
//        Log.d("AAA","size "+arrayList_giohang.size());
    }
    public  void updateDataToSever(){
        Toast.makeText(GioHangActivity.this, "đả gửi giỏ hàng lên sever", Toast.LENGTH_SHORT).show();
    }
    public void sendBackArrayGioHangEditSanPhamActivity(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("arrayListEdit",arrayList_giohang);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
