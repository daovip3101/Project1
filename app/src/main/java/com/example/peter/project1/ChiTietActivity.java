package com.example.peter.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peter.project1.Model.SanPham;

public class ChiTietActivity extends AppCompatActivity {
    SanPham sp;
    TextView tv_xemthem;
    LinearLayout linearLayout_xemthem;
    View gio;
    TextView giaSp,tenSp;
    ImageView imgHinhSp;
    ImageButton img_back_chitiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        Anhxa();
        getData();
        linearLayout_xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_xemthem.getVisibility() == View.VISIBLE){
                    tv_xemthem.setVisibility(View.GONE);
                } else tv_xemthem.setVisibility(View.VISIBLE);
            }
        });

        gio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChiTietActivity.this,GioHangActivity.class);
                startActivity(i);
            }
        });

        //onclick img_back_chitiet
        img_back_chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    public void getData(){
        sp= (SanPham) getIntent().getSerializableExtra("SanPham");
        giaSp.setText(sp.getDongia()+"");
        tenSp.setText(sp.getTenSanPha());
        imgHinhSp.setImageResource(sp.getHinh());
    }
    public  void Anhxa(){
        img_back_chitiet=findViewById(R.id.img_btn_back_chitiet);
        gio = findViewById(R.id.View_gio);
        linearLayout_xemthem = findViewById(R.id.xemthem);
        tv_xemthem = findViewById(R.id.tv_noidungxemthem);
        tv_xemthem.setVisibility(View.INVISIBLE);
        giaSp=findViewById(R.id.tv_giasp_chitiet);
        tenSp=findViewById(R.id.tv_tensp_chitiet);
        imgHinhSp=findViewById(R.id.img_hinhsp_chitiet);
    }
}
