package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.AdvertBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.protool.AdvertProtocol;
import shlottery.gov.cn.lotterycenter.ui.MainActivity;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;

public class AdvertActivity extends BaseActivity implements LoadCallback<AdvertBean> {
    private ImageView mAdvertImg;
    private TextView mCustDownTv;

    private List<AdvertBean.DataBean.PicBean> mAdvertDataList;
    private AdvertBean.DataBean.PicBean mAdvertData;
    private AdvertHelper mHelper = new AdvertHelper();
    private String mPicUrl = "";
    private String title = "";
    private String linkUrl = "";
    private int linkType;
    private int linkItem;
    private long beginTime;
    private long endTime;
    private int mReaminingTime = 5;//倒计时秒数
    private Boolean mHasGetData = false;//是否从缓存中获取到
    private Boolean finished = false;//广告页是否结束，如果结束，那么网速慢情况下的onsuccess和onerror就不再执行
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JumptoMainActivity();
        }
    };
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mReaminingTime--;
            if (mReaminingTime <= 0) {
                timer.cancel();
                JumptoMainActivity();
            } else {
                mCustDownTv.setText(mReaminingTime + " 跳过");
            }
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_advert);
        mAdvertImg = (ImageView) findViewById(R.id.advertImag);
        mCustDownTv = (TextView) findViewById(R.id.countdown_tv);
        mAdvertData = mHelper.getAdvertData();
        if (mAdvertData != null && mAdvertData instanceof AdvertBean.DataBean.PicBean) {
            LogUtils.i("广告页 缓存获取到");
            mHasGetData = true;
            updateInfo(mAdvertData);
        } else {
            mHasGetData = false;
            LogUtils.i("广告页 load");
        }
        load();
        mCustDownTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                JumptoMainActivity();
            }
        });

        mAdvertImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.i("AdvertActivity click" + linkItem);
                Intent intent;
                Bundle bundle = new Bundle();
                switch (linkType) {
                    case 0:
                        break;
                    case 1:
                        timer.cancel();
                        intent = new Intent(AdvertActivity.this, WebViewActivity.class);
                        bundle.putString("mUrl", linkUrl);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        timer.cancel();
                        LogUtils.i("AdvertActivity click 2" + linkItem);
                        intent = new Intent(AdvertActivity.this, NewsDetailActivity.class);
                        bundle.putInt("id", linkItem);
                        bundle.putString("title", "资讯");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        break;
                    case 3:
                        timer.cancel();
                        break;
                    case 4:
                        timer.cancel();
                        break;
                    case 5:
                        timer.cancel();
                        break;
                }
            }
        });
    }

    @Override
    protected void init() {
        LogUtils.i("AdvertActivity init");
    }

    private void load() {
        AdvertProtocol protocol = new AdvertProtocol();
        protocol.load(this, this);
    }

    @Override
    public void onSucess(AdvertBean advertBean) {
        LogUtils.i("AdvertActivity onSucess");
        if (advertBean != null && advertBean.getRet() == 100 && !finished) {
            LogUtils.i("AdvertActivity onSucess 100");
            mAdvertDataList = advertBean.getData().getPic();
            if (mAdvertDataList != null && mAdvertDataList.size() > 0) {
                mAdvertData = advertBean.getData().getPic().get(0);
                mHelper.putAdvertData(mAdvertData);
                beginTime = mAdvertData.getBeginTime();
                beginTime = beginTime * 1000;
                endTime = mAdvertData.getEndTime();
                endTime = endTime * 1000;
                LogUtils.i("AdvertActivity load time:" + "    beginTime:" + DateUtils.formatDateAndTime(beginTime) + "     endTime:" + DateUtils.formatDateAndTime(endTime) + "      currentTime:" + DateUtils.formatDateAndTime(System.currentTimeMillis()));
            } else {
                mHelper.putAdvertData(null);
            }
            LogUtils.i("AdvertActivity onSucess data:" + mAdvertData);

            if (!mHasGetData) {
                updateInfo(mAdvertData);
            }
        } else {
        }
    }

    @Override
    public void onError() {
        LogUtils.i("AdvertActivity onError");
        if (!mHasGetData && !finished) {
            updateInfo(mAdvertData);
        }
    }

    //根据mAdvertData来初始化各项数据
    private void updateInfo(AdvertBean.DataBean.PicBean mAdvertData) {
        LogUtils.i("AdvertActivity updateInfo");
        if (mAdvertData != null) {
            beginTime = mAdvertData.getBeginTime();
            beginTime = beginTime * 1000;
            endTime = mAdvertData.getEndTime();
            endTime = endTime * 1000;
            //时间没有过期就要加载广告页，如果过期，直接进去主页
            if (beginTime <= System.currentTimeMillis() && endTime >= System.currentTimeMillis()) {
                mCustDownTv.setText(mReaminingTime + " 跳过");
                timer.schedule(task, 1000, 1000);
                mPicUrl = mAdvertData.getPicUrl();
                if (mPicUrl != null && !mPicUrl.isEmpty()) {
                    Picasso.with(AdvertActivity.this).load(mPicUrl).into(mAdvertImg);
                }
                title = mAdvertData.getTitle();
                linkUrl = mAdvertData.getLinkUrl();
                String item = mAdvertData.getLinkItem();
                if (item != null && !item.isEmpty()) {
                    linkItem = Integer.parseInt(item);
                }
                linkType = mAdvertData.getLinkType();
                LogUtils.i("AdvertActivity info   " + "linkType:" + linkType + "        linkItem:" + linkItem + "       linkUrl:" + linkUrl + "    beginTime:" + DateUtils.formatDateAndTime(beginTime) + "     endTime:" + DateUtils.formatDateAndTime(endTime) + "      currentTime:" + DateUtils.formatDateAndTime(System.currentTimeMillis()));
            } else {
                mReaminingTime = 3;
                mCustDownTv.setText(mReaminingTime + " 跳过");
                timer.schedule(task, 1000, 1000);
                LogUtils.i("AdvertActivity 过期" + beginTime + ":::" + System.currentTimeMillis() + ":::" + endTime);
            }
        } else {
            mReaminingTime = 3;
            mCustDownTv.setText(mReaminingTime + " 跳过");
            timer.schedule(task, 1000, 1000);
            LogUtils.i("AdvertActivity 空");
        }
    }

    private void JumptoMainActivity() {
        Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        finished = true;
    }

    private class AdvertHelper {
        private void putAdvertData(AdvertBean.DataBean.PicBean mAdvertData) {
            LogUtils.i("AdvertActivity putAdvertData");
            SharedPreferencesUtils.putObject(Configure.SPKEY_ADVERT, mAdvertData);
        }

        private AdvertBean.DataBean.PicBean getAdvertData() {
            LogUtils.i("AdvertActivity begin getAdvertData:");
            AdvertBean.DataBean.PicBean data = (AdvertBean.DataBean.PicBean) SharedPreferencesUtils.getObject(Configure.SPKEY_ADVERT);
            LogUtils.i("AdvertActivity getAdvertData:" + data);
            return data;
        }
    }

    @Override
    protected String getBaidutitle() {
        return "";
    }
}
