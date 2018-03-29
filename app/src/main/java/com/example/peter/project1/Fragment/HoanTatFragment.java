package com.example.peter.project1.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.peter.project1.R;

import static com.example.peter.project1.MuaNgayActivity.setCurrentPage;


/**
 * A simple {@link Fragment} subclass.
 */
public class HoanTatFragment extends android.support.v4.app.Fragment {
    Button btn_hoantat;
    View v;
    public HoanTatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_hoan_tat, container, false);
        Anhxa();
        ClickEvent();
        return v;
    }
    public void Anhxa(){
        btn_hoantat=v.findViewById(R.id.btn_hoantat);
    }
    public void ClickEvent(){
        //btn_tiep_tuc_thong_tin CLick
        btn_hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Đã hoàn tất", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }

}
