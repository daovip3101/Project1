package com.example.peter.project1;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.peter.project1.Adapter.adapterFragmentMuaNgayActivity;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class MuaNgayActivity extends AppCompatActivity {
    static ViewPager vp_mungay_activity;
    StateProgressBar stateProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_ngay);
        Anhxa();
        setUpViewpager();
    }
    public void Anhxa(){
        vp_mungay_activity=findViewById(R.id.vp_mungay_activity);
        stateProgressBar=findViewById(R.id.state_progress_bar);
    }
    public void setUpViewpager(){
        adapterFragmentMuaNgayActivity adapter = new adapterFragmentMuaNgayActivity(getSupportFragmentManager());
        vp_mungay_activity.setAdapter(adapter);

    }
    public static void setCurrentPage(int item){
        vp_mungay_activity.setCurrentItem(item);
    }

}
