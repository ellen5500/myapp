package com.jxw.myapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

public class Main extends AppCompatActivity {
    private BannerComponent bannerComponent;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.main);
        //ViewPager,Indicator

        ViewPager viewPager = (ViewPager) findViewById(R.id.banner_viewPager);

        //顾名思义是指示器的意思。有点像水平方向的listview 可以自定义item。
        Indicator indicator = (Indicator) findViewById(R.id.banner_indicator);

        //setScrollBar(ScrollBar scrollBar)设置跟随tab滑动的滑动块

        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.WHITE, 0, ScrollBar.Gravity.TOP));
        //加载页；
        viewPager.setOffscreenPageLimit(2);

        bannerComponent = new BannerComponent(indicator, viewPager, true);
        bannerComponent.setAdapter(adapter);

        //默认就是800毫秒，设置单页滑动效果的时间
//        bannerComponent.setScrollDuration(800);
        //setAutoPlayTime(long time)设置自动播放间隔时间，默认情况是3000毫秒
        bannerComponent.setAutoPlayTime(2500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bannerComponent.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerComponent.stopAutoPlay();
    }

    private int[] images = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};

    //IndicatorViewPager用于将ViewPager和Indicator 联合使用。
    //indicatorViewPager.setAdapter(IndicatorPagerAdapter adapter)
    // 设置它可以自定义实现在滑动过程中，tab项的字体变化，颜色变化等等过渡效果
    //indicatorViewPager.setIndicatorOnTransitionListener(onTransitionListener);
    // 设置它可以自定义滑动块的样式
    //子类IndicatorViewPagerAdapter 用于 界面是View的形式.
    private IndicatorViewPager.IndicatorViewPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        //获取tab;
        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new View(container.getContext());
            }
            return convertView;
        }

        //获取每一个界面；
        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new ImageView(getApplicationContext());
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            ImageView imageView = (ImageView) convertView;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(images[position]);
            return convertView;
        }

//        @Override
//        public int getItemPosition(Object object) {
//            return RecyclingPagerAdapter.POSITION_NONE;
//        }

        //返回界面数量；
        @Override
        public int getCount() {
            return images.length;
        }
    };
}
