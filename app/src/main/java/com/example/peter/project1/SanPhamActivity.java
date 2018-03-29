package com.example.peter.project1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.peter.DuAn1;
import com.example.peter.project1.Adapter.adaoter_rc_san_pham;
import com.example.peter.project1.CustomView.Badge;
import com.example.peter.project1.Interface.ILoadMore;
import com.example.peter.project1.Model.SanPham;

import java.util.ArrayList;




public class SanPhamActivity extends AppCompatActivity {
     static Badge badge;
    RecyclerView rc_san_pham;
    ArrayList<SanPham> arrayListSanPham;
    ImageButton img_btn_back;
     static ImageButton img_btn_giohang;
    adaoter_rc_san_pham adapter;
    PullRefreshLayout swipe_refresh_layout;
    static ArrayList<SanPham> arrayList_giohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        Anhxa();
        loadSanPhamFromsever();
        loadGioHangFromsever();
        setNumberBadge(arrayList_giohang.size());
        //set up recycleView
//        adapter_rc_san_pham adapter_rc_san_pham = new adapter_rc_san_pham(arrayListSanPham,SanPhamActivity.this);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
//        rc_san_pham.setLayoutManager(manager);
//        rc_san_pham.setAdapter(adapter_rc_san_pham);
        rc_san_pham.setLayoutManager(new LinearLayoutManager(this));
        adapter= new adaoter_rc_san_pham(rc_san_pham,this,arrayListSanPham);
        rc_san_pham.setAdapter(adapter);
        pulldownToRefresh();
        PullUptoRefresh();






        //onclick btn back
        img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // go to giohang
        img_btn_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendGioHangtoGiohangActivity();
            }
        });


    }
    public void Anhxa(){
;        swipe_refresh_layout=findViewById(R.id.swipe_refresh_layout);
        arrayListSanPham=new ArrayList<>();
        img_btn_giohang=findViewById(R.id.img_btn_giohang);
        rc_san_pham=findViewById(R.id.rc_san_pham);
        img_btn_back=findViewById(R.id.img_btn_back);
        badge=findViewById(R.id.badge);
    }
    public  void loadSanPhamFromsever(){
        for(int i=0;i<20;i++){
            SanPham sp = new SanPham("sp"+i,5+i*10,R.drawable.garan);
            arrayListSanPham.add(sp);
        }
    }
    public  void pulldownToRefresh(){
        swipe_refresh_layout.setColor(ContextCompat.getColor(SanPhamActivity.this,android.R.color.holo_red_dark));
        swipe_refresh_layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(arrayListSanPham.size() <=50){
//                    items.add(null);
//                    adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SanPhamActivity.this, "dang load", Toast.LENGTH_SHORT).show();
//                            items.remove(items.size()-1);
//                            adapter.notifyItemRemoved(items.size());
                            //Random more data
                            int index = arrayListSanPham.size();
                            int end =index+10;
                            for(int i=0;i<10;i++){
//                                String name = UUID.randomUUID().toString();
                                SanPham sanpham = new SanPham("Món"+i,i*100,R.drawable.garan);
                                arrayListSanPham.add(i,sanpham);
                            }
                            adapter.notifyDataSetChanged();
//                            adapter.setLoaded();
                            swipe_refresh_layout.setRefreshing(false);
                        }
                    },2000);
                }else {
                    Toast.makeText(SanPhamActivity.this, "Load data completed!", Toast.LENGTH_SHORT).show();
                    swipe_refresh_layout.setRefreshing(false);
                }
            }
        });
    }
    public void PullUptoRefresh(){
        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if(arrayListSanPham.size() <=50){
                    arrayListSanPham.add(null);
                    adapter.notifyItemInserted(arrayListSanPham.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SanPhamActivity.this, "dang load", Toast.LENGTH_SHORT).show();
                            arrayListSanPham.remove(arrayListSanPham.size()-1);
                            adapter.notifyItemRemoved(arrayListSanPham.size());
                            //Random more data
                            int index = arrayListSanPham.size();
                            int end =index+10;
                            for(int i=index;i<end;i++){
                                SanPham sanpham = new SanPham("Món"+i,i*100,R.drawable.garan);
                                arrayListSanPham.add(i,sanpham);
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    },2000);
                }else {
                    Toast.makeText(SanPhamActivity.this, "Load data completed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void loadGioHangFromsever() {
        arrayList_giohang = new ArrayList<>();
        for(int i=0;i<20;i++){
            SanPham sp = new SanPham("sp"+i,5+i*10,R.drawable.garan,1);
            arrayList_giohang.add(sp);
        }
//        Toast.makeText(this, "Đã load giỏ hàng from sever"+arrayList_giohang.size(), Toast.LENGTH_SHORT).show();
    }
    public static void setNumberBadge(int size){

        Log.d("BADGE",size+"");
        badge.setNumber(size);

//        Toast.makeText(DuAn1.getAppContext(), ""+size, Toast.LENGTH_SHORT).show();
    }
    public static void Addgiohang(SanPham sp){
        arrayList_giohang.add(sp);
//        badge_gio_hang.setNumber(arrayListGiohang.size());
        setNumberBadge(arrayList_giohang.size());

    }
    public void sendGioHangtoGiohangActivity(){
        Intent i = new Intent(SanPhamActivity.this,GioHangActivity.class);
        i.putExtra("arrayGioHang",arrayList_giohang);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                arrayList_giohang = (ArrayList<SanPham>) data.getSerializableExtra("arrayListEdit");
//                Toast.makeText(this, "edit+ " +arrayList_giohang.size(), Toast.LENGTH_SHORT).show();
                setNumberBadge(arrayList_giohang.size());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
