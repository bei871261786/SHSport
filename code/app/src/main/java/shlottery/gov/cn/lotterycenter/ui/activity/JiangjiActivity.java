package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ScreenUtils;

public class JiangjiActivity extends BaseActivity {
    private String mTitleName;
    private Intent mIntent;
    private String mIssueName = "";
    private ImageView mImage;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_jiangji);
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        mImage = (ImageView) findViewById(R.id.jiangji_img);
        initTitleBar();
        titlebarTv.setText(mTitleName);
        toggleImg(mIssueName
        );
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText("奖金奖级");
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }

    @Override
    protected void init() {
        mIntent = getIntent();
        Bundle bundle = mIntent.getExtras();
        Logger.i("init bundle:" + bundle);
        mIssueName = bundle.getString("issueName");
        mTitleName = mIssueName + " 奖金奖级";
    }

    private void toggleImg(String issueName) {
        Resources resources = getResources();
        if (issueName.equals(resources.getString(R.string.lotterys_lotto))) {
//            Picasso.with(this).load(R.mipmap.jiangji_lotto).into(mImage);
            Bitmap bitmap = decodeThumbBitmapForFile(R.mipmap.jiangji_lotto);
            mImage.setImageBitmap(bitmap);
        }
        if (issueName.equals(resources.getString(R.string.lotterys_qixing))) {
//            Picasso.with(this).load(R.mipmap.jiangji_qixin).into(mImage);
            Bitmap bitmap = decodeThumbBitmapForFile(R.mipmap.jiangji_qixin);
            mImage.setImageBitmap(bitmap);
        }
        if (issueName.equals(resources.getString(R.string.lotterys_sh11x5))) {
            Bitmap bitmap = decodeThumbBitmapForFile(R.mipmap.jiangji_sh115);
            mImage.setImageBitmap(bitmap);
//            Picasso.with(this).load(b).into(mImage);
        }
    }


    /**
     * 根据View(主要是ImageView)的宽和高来获取图片的缩略图
     *
     * @param id
     * @return
     */
    private Bitmap decodeThumbBitmapForFile(int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id, options);
        LogUtils.i("after injust：" );
        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;
        int width = ScreenUtils.getScreenWidth(this);
        double height = ((double) bitmapHeight / bitmapWidth) * width;
        int screenHeight=ScreenUtils.getScreenHeight(this);
        LogUtils.i("JInangji info::::::"+((double)bitmapHeight / bitmapWidth)+"::::"+width);
        LogUtils.i("JInangji info:"+bitmapWidth+":::"+bitmapHeight+"::::"+width+":::"+height+":::"+screenHeight);
        return Bitmap.createScaledBitmap(bitmap, width, (int) height, true);
    }
    @Override
    protected String getBaidutitle() {
        return "奖级";
    }
}
