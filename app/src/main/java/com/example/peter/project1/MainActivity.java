package com.example.peter.project1;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.project1.Adapter.AdapterViewpager;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager_slideshow;
    ImageButton imgb;
    DrawerLayout dw;
    TextView tv_xemtatca;
    ImageView  imgV_sp;
    CircleIndicator circleIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        AnhXa();

        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dw.openDrawer(GravityCompat.START);
            }
        });
        imgV_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ChiTietActivity.class);
                startActivity(i);
            }
        });

        tv_xemtatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SanPhamActivity.class);
                startActivity(i);
            }
        });
        setUpCircleIndicator();

    }
    public void AnhXa(){
        imgb = findViewById(R.id.img_btn_menu);
        dw = findViewById(R.id.drawer_layout);
        tv_xemtatca = findViewById(R.id.tv_xemtatca1);
        imgV_sp = findViewById(R.id.imgV_sp1);
        viewPager_slideshow=findViewById(R.id.vp_slideshow);
        circleIndicator=findViewById(R.id.indicator_default);
    }
    public void setUpCircleIndicator(){
        AdapterViewpager adapter= new AdapterViewpager(getSupportFragmentManager());
        viewPager_slideshow.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager_slideshow);

    }
}
