package shlottery.gov.cn.lotterycenter.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.MainActivity;
import shlottery.gov.cn.lotterycenter.ui.adapter.WelcomePagerAdapter;


/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-28-0028 13:18
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class WelcomeActivity extends Activity {
    @BindView(R.id.welcome_vper)
    ViewPager welcomeVper;
    private List<ImageView> imageViews = new ArrayList<>();
    private WelcomePagerAdapter welcomepagerAdapter;
    private int imageIds[] = {R.mipmap.welcome_img1, R.mipmap.welcome_img2, R.mipmap.welcome_img3, R.mipmap.welcome_img4};

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private int currentPosition;//当前选中的item

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initImageview();
        setOnclick();
    }

    private void initImageview() {
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews.add(imageView);
        }
    }

    //为viewpager添加点击事件
    private void setOnclick() {
        welcomepagerAdapter = new WelcomePagerAdapter(imageViews);
        welcomeVper.setAdapter(welcomepagerAdapter);
        welcomeVper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = (int) event.getX();
                        endY = (int) event.getY();
                        if (Math.abs(endX - startX) < 50 && Math.abs(endY - startY) < 50) {
                            try {
                                Logger.e(currentPosition + "currentPosition");
                                if (currentPosition == 3) {
                                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (Exception e) {
                            }
                        }
                        break;
                }
                return false;
            }
        });

        welcomeVper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                                                //图片左右滑动时候，将当前页的圆点图片设为选中状态
                                                @Override
                                                public void onPageSelected(int position) {
                                                    currentPosition = position;
                                                    Logger.e(currentPosition + "currentPosition");
                                                }

                                                @Override
                                                public void onPageScrolled(int i, float v, int i1) {
                                                }

                                                @Override
                                                public void onPageScrollStateChanged(int state) {

                                                }
                                            }
        );

    }
}
