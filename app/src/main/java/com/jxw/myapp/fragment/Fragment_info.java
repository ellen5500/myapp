package com.jxw.myapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.jxw.myapp.R;
import com.jxw.myapp.publishInfo.PublishInfo;

public class Fragment_info extends Fragment{
    private RadioGroup rg_two;
    private ImageView iv_publish;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info,null);
        switchFragment(new Fragment_info_all());
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        iv_publish = ((ImageView) getActivity().findViewById(R.id.iv_publish));
        iv_publish.requestFocus();
        iv_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PublishInfo.class);
                startActivity(intent);
            }
        });
        rg_two = ((RadioGroup) getActivity().findViewById(R.id.rg_two));
        rg_two.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId){
                    case R.id.rb_guys:
                        fragment = new Fragment_info_guys();
                        break;
                    case R.id.rb_all:
                        fragment = new Fragment_info_all();
                        break;
                }
                switchFragment(fragment);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    private void switchFragment(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.fl_info_main,fragment).commit();
    }
}
