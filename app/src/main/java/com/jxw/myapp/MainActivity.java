package com.jxw.myapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jxw.myapp.fragment.Fragment_activity;
import com.jxw.myapp.fragment.Fragment_friend;
import com.jxw.myapp.fragment.Fragment_home;
import com.jxw.myapp.fragment.Fragment_info;
import com.jxw.myapp.fragment.Fragment_sport;

public class MainActivity extends AppCompatActivity {

    private ListView lv_drawer_left;
    private DrawerLayout drawer;
    private RadioGroup rg_five;
    private RadioButton rb_info;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb_info = ((RadioButton) findViewById(R.id.rb_info));
        initview();
        initdata();
        id = getIntent().getIntExtra("id",0);
        Log.i("ID","id"+id);
        if (id==1){
            switchFragment(new Fragment_info());
        }

    }

    //初始化所有view对象
    private void initview() {
        lv_drawer_left = ((ListView) findViewById(R.id.lv_drawer_left));
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
        rg_five = ((RadioGroup) findViewById(R.id.rg_five));

    }

    private void initdata() {
        //默认显示home
        switchFragment(new Fragment_home());
        //侧滑菜单
        lv_drawer_left.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"one","two","three"}));
        //点击listview关闭侧滑
        lv_drawer_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawer.closeDrawers();
            }
        });

        //fragment选中一个页面 出现一个页面
        rg_five.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Fragment fragment = null;
                switch (checkedId){
                    case R.id.rb_home:
                        fragment = new Fragment_home();
                        break;
                    case R.id.rb_sport:
                        fragment = new Fragment_sport();
                        break;
                    case R.id.rb_activity:
                        fragment = new Fragment_activity();
                        break;
                    case R.id.rb_info:
                        fragment = new Fragment_info();
                        break;
                    case R.id.rb_friend:
                        fragment = new Fragment_friend();
                        break;
                }

                switchFragment(fragment);

            }
        });
    }

    private void switchFragment(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.fl_main_one,fragment).commit();
    }


}
