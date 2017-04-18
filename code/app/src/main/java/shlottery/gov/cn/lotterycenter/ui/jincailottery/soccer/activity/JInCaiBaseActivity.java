package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.JingCaiZuqiuInfo;
import shlottery.gov.cn.lotterycenter.bean.Jincai.LeaguesBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.GuoGuanGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.JcActivityFragment_DataCallback;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment.SwitchJingCaiFangShiFragment;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;


/**
 * Created by booob on 2016-05-20-0020.
 */
public abstract class JInCaiBaseActivity extends BaseActivity implements JcActivityFragment_DataCallback {

    private TextView mTopTv;
    private ImageView mXialaButton;
    private ImageView mFiltrate;
    private GridView mJinZuGridView;
    private Boolean mFlag = false;
    private GuoGuanGridViewAdapter mGuoGuanGridViewAdapter;
    protected String[] mString;//title名称的数组
    private int mClickTemp;
    private FrameLayout content_fragment;
    public BaseFragment2 fragment;
    private ImageView mBack;
    private ImageView mShanglaButton;
    private RelativeLayout mToPTitleButton;
    private static HashMap<Integer, List<MatchsBean>> mDataMap = new HashMap<>();
    private JingCaiZuqiuInfo.DataBean mDatas;
    private TreeSet<LeaguesBean> mFlitrateData;
    protected String mTitleName = "竞足-";
    protected int mType;

    protected abstract void SwitchFragment(int i);

    @Override
    protected void init() {
        prepareInit();
        mClickTemp = 0;
        LogUtils.e(mClickTemp + "mClickTemp的值");
    }

    protected abstract void prepareInit();

    protected abstract int getTypeName();

    public int getType() {
        return getTypeName();
    }

    public String getTitleName() {
        return mTopTv.getText().toString();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void initView() {
        Message msg = new Message();
        Bundle data = new Bundle();
        msg.setData(data);
        handler.sendMessage(msg);
        setContentView(R.layout.activity_jingcaifootball);
        // ActivityManager.getInstance().addActivity(this);

        content_fragment = (FrameLayout) findViewById(R.id.content_fragment);
        mBack = (ImageView) findViewById(R.id.topButton_back);//返回键
        mFiltrate = (ImageView) findViewById(R.id.filtrate);//筛选
        mTopTv = (TextView) findViewById(R.id.topTv);
        mTopTv.setText(mTitleName + mString[mClickTemp]);
        mXialaButton = (ImageView) findViewById(R.id.xialaButton);
        mShanglaButton = (ImageView) findViewById(R.id.shangla_button);
        mJinZuGridView = (GridView) findViewById(R.id.chuanguan_gridview);
        mGuoGuanGridViewAdapter = new GuoGuanGridViewAdapter(this, mString, mClickTemp);
        // fragment = SwitchJingCaiFangShiFragment.createFragment(mClickTemp);
        LogUtils.e(mClickTemp + "=mClickTemp");
        //transaction.add(R.id.content_fragment, fragment);
        mToPTitleButton = (RelativeLayout) findViewById(R.id.topTitleButton);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchJingCaiFangShiFragment.removeFragment();
                finish();
            }
        });
        mToPTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mFlag) {
                    mFlag = false;
                } else {
                    mFlag = true;
                }
                showMethod();
            }
        });

        mFiltrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mFlitrateData) {
                    Intent intent = new Intent(getBaseContext(),
                            FootballFiltrateActivity.class);
                    Bundle bundle = new Bundle();
                    LogUtils.i("flitrateData:" + mFlitrateData.size());
                    bundle.putSerializable("flitrateData", mFlitrateData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        //竞彩头部gridview的item点击切换
        mJinZuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mFlag = false;
                mClickTemp = position;
                changeButtonState(mFlag);
                mTopTv.setText(mTitleName + mString[position]);
                if (null != getFlitrateData()) {
                    for (LeaguesBean bean : getFlitrateData()) {
                        bean.setFlitrate(true);
                    }
                }
                mGuoGuanGridViewAdapter.setSeclection(position);
                mJinZuGridView.setVisibility(View.GONE);
                LogUtils.e(position + "=position");
                handler.sendEmptyMessage(0);
            }
        });
    }

    protected void showMethod() {
        // TODO Auto-generated method stub
        if (mFlag) {
            changeButtonState(mFlag);
            mJinZuGridView.setAdapter(mGuoGuanGridViewAdapter);
            mJinZuGridView.setVisibility(View.VISIBLE);
        } else {
            changeButtonState(mFlag);
            mJinZuGridView.setVisibility(View.GONE);
        }
    }

    private void changeButtonState(Boolean flag) {
        if (flag) {
            mXialaButton.setVisibility(View.VISIBLE);
            mShanglaButton.setVisibility(View.INVISIBLE);
        } else {
            mShanglaButton.setVisibility(View.VISIBLE);
            mXialaButton.setVisibility(View.INVISIBLE);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.i("page 更换fragment");
            super.handleMessage(msg);
            Bundle data = msg.getData();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            SwitchFragment(mClickTemp);
            LogUtils.e(mClickTemp + "=mClickTemp" + "::::" + fragment);
            transaction.replace(R.id.content_fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            // UI界面的更新等相关操作
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SwitchJingCaiFangShiFragment.removeFragment();
            finish();
        }
        return false;
    }

    public void setmDatas(Object data) {
        if (data instanceof JingCaiZuqiuInfo.DataBean) {
            mDatas = (JingCaiZuqiuInfo.DataBean) data;
        }
    }

    public JingCaiZuqiuInfo.DataBean getmDatas() {
        if (null == mDatas)
            return mDatas;
        return null;
    }

    @Override
    protected void onStop() {
        LogUtils.i("activity stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtils.i("activity destroy");
        super.onDestroy();
        SoccerDialogUtils.removeDialog();
    }

    @Override
    public TreeSet<LeaguesBean> getFlitrateData() {
        return this.mFlitrateData;
    }

    @Override
    public void setFlitrateData(boolean isUpdate, TreeSet<LeaguesBean> mFlitrateData) {
        LogUtils.i("setFlitrateData  " + (mFlitrateData == null)+":::"+mFlitrateData.size());
        if (isUpdate || null == this.mFlitrateData) {
            this.mFlitrateData = mFlitrateData;
        }
    }
}
