package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.AdapterViewPagerOnBoarding;


public class ActivityOnboarding extends AppCompatActivity {


    private ViewPager viewPagerOnboarding;
    private LinearLayout layoutDot;
    private TextView[] dotsTv;
    private int[] layouts;
    private Button buttonSkip;
    private Button buttonNext;
    private AdapterViewPagerOnBoarding pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarTransparent();
        setContentView(R.layout.activity_onboarding);

        viewPagerOnboarding = findViewById(R.id.viewPagerOnBoarding);
        layoutDot = findViewById(R.id.dotLayout);
        buttonNext = findViewById(R.id.buttonNext);
        buttonSkip = findViewById(R.id.buttonSkip);

        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityHome();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage = viewPagerOnboarding.getCurrentItem() + 1;
                if (currentPage < layouts.length) {
                    viewPagerOnboarding.setCurrentItem(currentPage);
                } else {
                    startActivityHome();
                }
            }
        });
        layouts = new int[]{R.layout.slide1, R.layout.slide2, R.layout.slide3, R.layout.slide4, R.layout.slide5};
        pagerAdapter = new AdapterViewPagerOnBoarding(layouts, getApplicationContext());
        viewPagerOnboarding.setAdapter(pagerAdapter);

        viewPagerOnboarding.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == layouts.length - 1) {
                    buttonNext.setText("START");
                    buttonSkip.setVisibility(View.GONE);
                } else {
                    buttonNext.setText("NEXT");
                    buttonSkip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setDotStatus(0);
    }

    private void setFirstTimeStartStatus() {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ref.edit();
        editor.putBoolean("FirstTimeStartFlag", false);
        editor.commit();
    }

    private void setDotStatus(int page) {
        layoutDot.removeAllViews();
        dotsTv = new TextView[layouts.length];
        for (int i = 0; i < dotsTv.length; i++) {
            dotsTv[i] = new TextView(this);
            dotsTv[i].setText(Html.fromHtml("&#8226"));
            dotsTv[i].setTextSize(30);
            dotsTv[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dotsTv[i]);
        }
        dotsTv[page].setTextColor(Color.parseColor("#64ffda"));
    }

    private void startActivityHome() {
        setFirstTimeStartStatus();
        finish();
    }

    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
