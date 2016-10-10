package com.jxw.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxw.myapp.R;
import com.jxw.myapp.pojo.ListActivityBean;
import com.jxw.myapp.utils.xUtilsImageUtils;

public class one_activity extends AppCompatActivity {

    private ImageView iv_photoImg;
    private TextView tv_infoName;
    private TextView tv_infoContent;
    private TextView tv_infoDate;
    private ImageView iv_like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_one);
        iv_photoImg = ((ImageView) findViewById(R.id.iv_photoImg));
        tv_infoName = ((TextView) findViewById(R.id.tv_infoName));
        tv_infoContent = ((TextView) findViewById(R.id.tv_infoContent));
        tv_infoDate = ((TextView) findViewById(R.id.tv_infoDate));
        iv_photoImg = ((ImageView)findViewById(R.id.iv_photoImg));


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ListActivityBean.Info infomore = (ListActivityBean.Info)bundle.getSerializable("infomore");
        xUtilsImageUtils.display(iv_photoImg,"http://10.0.2.2:8080/info/"+infomore.photoImg,true);
        tv_infoName.setText(infomore.infoName+"");
        tv_infoContent.setText(infomore.infoContent);
        tv_infoDate.setText(infomore.infoDate);


    }
}
