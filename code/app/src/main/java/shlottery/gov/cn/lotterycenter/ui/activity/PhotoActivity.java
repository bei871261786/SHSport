package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.adapter.PhotoPageAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.PhotoViewPager;

public class PhotoActivity extends AppCompatActivity implements PhotoPageAdapter.onItemClickListener, ViewPager
        .OnPageChangeListener {
    //图片url集合
    public static final String IMG_URLS         = "img_urls";
    //点击的图片url索引
    public static final String IMG_URL_POSITION = "img_url_position";
    @BindView(R.id.vp_photo)
    PhotoViewPager mPvpPhoto;
    @BindView(R.id.tv_photo_position)
    TextView       mTvPhotoPosition;
    private PhotoPageAdapter  mAdapter;
    private ArrayList<String> mImgUrls;
    private int               mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }


    private void initView() {
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
    }

    private void initData() {
        Intent intent = getIntent();
        mImgUrls = intent.getStringArrayListExtra(IMG_URLS);
        mCount = mImgUrls.size();
        int position = intent.getIntExtra(IMG_URL_POSITION, 0);
        mAdapter = new PhotoPageAdapter(this, mImgUrls);
        mAdapter.setOnItemClickListener(this);
        mPvpPhoto.addOnPageChangeListener(this);
        mPvpPhoto.setAdapter(mAdapter);
        mPvpPhoto.setCurrentItem(position, false);
        mTvPhotoPosition.setVisibility(mCount > 1 ? View.VISIBLE : View.INVISIBLE);
        mTvPhotoPosition.setText(position + 1 + "/" + mCount);
    }

    @Override
    public void onItemClick() {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTvPhotoPosition.setText(position + 1 + "/" + mCount);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
