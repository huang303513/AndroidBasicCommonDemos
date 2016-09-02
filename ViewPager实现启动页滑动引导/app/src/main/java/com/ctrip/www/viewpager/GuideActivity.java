package com.ctrip.www.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        viewPager = (ViewPager) findViewById(R.id.whatsnew_viewpager);
        viewPager.addOnPageChangeListener(new myOnPageChangeListener());

        imageView0 = (ImageView) findViewById(R.id.page0);
        imageView1 = (ImageView) findViewById(R.id.page1);
        imageView2 = (ImageView) findViewById(R.id.page2);
        imageView3 = (ImageView) findViewById(R.id.page3);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view1 = layoutInflater.inflate(R.layout.whats1,null);
        View view2 = layoutInflater.inflate(R.layout.whats2,null);
        View view3 = layoutInflater.inflate(R.layout.whats3,null);
        View view4 = layoutInflater.inflate(R.layout.whats4,null);

        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }
        };

        viewPager.setAdapter(pagerAdapter);
    }


    public class myOnPageChangeListener implements ViewPager.OnPageChangeListener{

        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    imageView0.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_fouse_pink));
                    imageView1.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_default_pink));
                    break;
                case 1:
                    imageView1.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_fouse_pink));
                    imageView0.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_default_pink));
                    imageView2.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_default_pink));
                    break;
                case 2:
                    imageView2.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_fouse_pink));
                    imageView1.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_default_pink));
                    imageView3.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_default_pink));

                    break;
                case 3:
                    imageView3.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_fouse_pink));
                    imageView2.setImageDrawable(getResources().getDrawable(
                            R.mipmap.start_default_pink));
                    break;
            }
            currentIndex = arg0;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public void startButton(View view){
        finish();
    }
}
