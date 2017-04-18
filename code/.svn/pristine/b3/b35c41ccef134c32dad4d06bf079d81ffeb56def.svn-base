package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.protool.IssueBonusProtocol;
import shlottery.gov.cn.lotterycenter.ui.adapter.IssueRecyCircleAdapter;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_JINGCAI;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_NUMBER;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_SH115;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_SOCCER;
import static shlottery.gov.cn.lotterycenter.Base.Configure.RECY_BLOCK;
import static shlottery.gov.cn.lotterycenter.Base.Configure.RECY_SMALL_CIRCLR;

public class IssueBountsActivity extends BaseActivity implements LoadCallback<IssueBonusBean.DataBean> {

    private RecyclerView msh11x5Listview;
    private RecyclerView mLottoListview;
    private RecyclerView mSort3Listview;
    private RecyclerView mSort5Listview;
    private RecyclerView mQxListview;
    private RecyclerView mSfListview;
    private RecyclerView mBqc4Listview;
    private RecyclerView mBqc6Listview;

    private RelativeLayout mSh11x5Layout;
    private RelativeLayout mLottoLayout;
    private RelativeLayout mSort3Layout;
    private RelativeLayout mSort5Layout;
    private RelativeLayout mQxLayout;
    private RelativeLayout mSfLayout;
    private RelativeLayout mBqc4Layout;
    private RelativeLayout mBqc6Layout;
    private RelativeLayout mJcSoccerLayout;
    private RelativeLayout mJcBasketBallLayout;

    private ScrollView scrollView;


    private IssueRecyCircleAdapter mSh11x5dapter;
    private IssueRecyCircleAdapter mLottoAdapter;
    private IssueRecyCircleAdapter mSort3Adapter;
    private IssueRecyCircleAdapter mSort5Adapter;
    private IssueRecyCircleAdapter mQxAdapter;
    private IssueRecyCircleAdapter mSfAdapter;
    private IssueRecyCircleAdapter mJqc4Adapter;
    private IssueRecyCircleAdapter mBqc6Adapter;

    private IssueBonusBean.DataBean.ListBean mSh11x5Data;
    private IssueBonusBean.DataBean.ListBean mLotto5Data;
    private IssueBonusBean.DataBean.ListBean mSort3Data;
    private IssueBonusBean.DataBean.ListBean mSort5Data;
    private IssueBonusBean.DataBean.ListBean mQxData;
    private IssueBonusBean.DataBean.ListBean mSfData;
    private IssueBonusBean.DataBean.ListBean mJqc4Data;
    private IssueBonusBean.DataBean.ListBean mBqc6Data;
    private IssueBonusBean.DataBean.ListBean mSoccerData;
    private IssueBonusBean.DataBean.ListBean mBasketballData;

    private ArrayList<BaseBean> mSh11x5AdapterData = new ArrayList<>();
    private ArrayList<BaseBean> mLottoAdapterData = new ArrayList<>();
    private ArrayList<BaseBean> mSort3AdapterData = new ArrayList<>();
    private ArrayList<BaseBean> mSort5AdapterData = new ArrayList<>();
    private ArrayList<BaseBean> mQxAdapterData = new ArrayList<>();
    private ArrayList<BaseBean> mSfAdapterData = new ArrayList<>();
    private ArrayList<BaseBean> mBqc6AdapterData = new ArrayList<>();
    private ArrayList<BaseBean> mJqc4AdapterData = new ArrayList<>();

    private TextView mIssuno_sh115;
    private TextView mIssuno_lotto;
    private TextView mIssuno_pl3;
    private TextView mIssuno_pl5;
    private TextView mIssuno_qixin;
    private TextView mIssuno_sf;
    private TextView mIssuno_bqc6;
    private TextView mIssuno_soccer;
    private TextView mIssuno_basketball;
    private TextView mIssuno_jqc4;

    private TextView mDate_sh115;
    private TextView mDate_lotto;
    private TextView mDate_pl3;
    private TextView mDate_pl5;
    private TextView mDate_qixin;
    private TextView mDate_sf;
    private TextView mDate_bqc6;
    private TextView mDate_jqc4;
    private TextView mDate_soccer;
    private TextView mDate_basketball;
    private ImageView mIndicatorImg;
    private Resources mResource;
    private MyListener mListener;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_issue_bounts);
        msh11x5Listview = (RecyclerView) findViewById(R.id.issuebonuts_sh11x5_listview);
        mLottoListview = (RecyclerView) findViewById(R.id.issuebonuts_lotto_listview);
        mSort3Listview = (RecyclerView) findViewById(R.id.issuebonuts_sort3_listview);
        mSort5Listview = (RecyclerView) findViewById(R.id.issuebonuts_sort5_listview);
        mQxListview = (RecyclerView) findViewById(R.id.issuebonuts_Qx_listview);
        mSfListview = (RecyclerView) findViewById(R.id.issuebonuts_Sf_listview);
        mBqc4Listview = (RecyclerView) findViewById(R.id.issuebonuts_Bqc4_listview);
        mBqc6Listview = (RecyclerView) findViewById(R.id.issuebonuts_Bqc6_listview);
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        mSh11x5Layout = (RelativeLayout) findViewById(R.id.issue_sh11x5_layout);
        mLottoLayout = (RelativeLayout) findViewById(R.id.issue_lotto_layout);
        mSort3Layout = (RelativeLayout) findViewById(R.id.issue_sort3_layout);
        mSort5Layout = (RelativeLayout) findViewById(R.id.issue_sort5_layout);
        mQxLayout = (RelativeLayout) findViewById(R.id.issue_Qx_layout);
        mSfLayout = (RelativeLayout) findViewById(R.id.issue_Sf_layout);
        mBqc4Layout = (RelativeLayout) findViewById(R.id.issue_Bqc4_layout);
        mBqc6Layout = (RelativeLayout) findViewById(R.id.issue_Bqc6_layout);
        mJcSoccerLayout = (RelativeLayout) findViewById(R.id.issue_jcSoccer_layout);
        mJcBasketBallLayout = (RelativeLayout) findViewById(R.id.issue_jcBasketBall_layout);
        mIndicatorImg = (ImageView) findViewById(R.id.indicator_right);
        mIssuno_sh115 = (TextView) findViewById(R.id.issue_sh11x5_issueNo);
        mIssuno_lotto = (TextView) findViewById(R.id.issue_lotto_issueNo);
        mIssuno_jqc4 = (TextView) findViewById(R.id.issue_Bqc4_issueNo);
        mIssuno_basketball = (TextView) findViewById(R.id.issue_jcBasketBall_issueNo);
        mIssuno_soccer = (TextView) findViewById(R.id.issue_jcSoccer_issueNo);
        mIssuno_bqc6 = (TextView) findViewById(R.id.issue_Bqc6_issueNo);
        mIssuno_sf = (TextView) findViewById(R.id.issue_Sf_issueNo);
        mIssuno_qixin = (TextView) findViewById(R.id.issue_Qx_issueNo);
        mIssuno_pl5 = (TextView) findViewById(R.id.issue_sort5_issueNo);
        mIssuno_pl3 = (TextView) findViewById(R.id.issue_sort3_issueNo);

        mDate_sh115 = (TextView) findViewById(R.id.issue_sh11x5_issueDay);
        mDate_lotto = (TextView) findViewById(R.id.issue_lotto_issueDay);
        mDate_pl3 = (TextView) findViewById(R.id.issue_sort3_issueDay);
        mDate_pl5 = (TextView) findViewById(R.id.issue_sort5_issueDay);
        mDate_qixin = (TextView) findViewById(R.id.issue_Qx_issueDay);
        mDate_sf = (TextView) findViewById(R.id.issue_Sf_issueDay);
        mDate_bqc6 = (TextView) findViewById(R.id.issue_Bqc6_issueDay);
        mDate_jqc4 = (TextView) findViewById(R.id.issue_Bqc4_issueDay);
        mDate_soccer = (TextView) findViewById(R.id.issue_jcSoccer_issueDay);
        mDate_basketball = (TextView) findViewById(R.id.issue_jcBasketBall_issueDay);
        scrollView = (ScrollView) findViewById(R.id.scorll);

        setAdapter(msh11x5Listview, mSh11x5dapter);
        setAdapter(mLottoListview, mLottoAdapter);
        setAdapter(mSort3Listview, mSort3Adapter);
        setAdapter(mSort5Listview, mSort5Adapter);
        setAdapter(mQxListview, mQxAdapter);
        setAdapter(mSfListview, mSfAdapter);
        setAdapter(mBqc4Listview, mJqc4Adapter);
        setAdapter(mBqc6Listview, mBqc6Adapter);

        mSh11x5Layout.setOnClickListener(mListener);
        mJcSoccerLayout.setOnClickListener(mListener);
        mLottoLayout.setOnClickListener(mListener);
        mSort3Layout.setOnClickListener(mListener);
        mSort5Layout.setOnClickListener(mListener);
        mQxLayout.setOnClickListener(mListener);
        mSfLayout.setOnClickListener(mListener);
        mBqc4Layout.setOnClickListener(mListener);
        mBqc6Layout.setOnClickListener(mListener);
        mJcSoccerLayout.setOnClickListener(mListener);
        mJcBasketBallLayout.setOnClickListener(mListener);
        initTitleBar();
        mIndicatorImg.setOnClickListener(mListener);
        loadData();
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
        titlebarTv.setText("最新开奖");
        mIndicatorImg = (ImageView) findViewById(R.id.titlebar_indicator);
        mIndicatorImg.setImageResource(R.mipmap.indicator_right);
    }

    private void setAdapter(RecyclerView recyclerView, IssueRecyCircleAdapter adapter) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void init() {
        mListener = new MyListener();
        mResource = getResources();
        mSh11x5dapter = new IssueRecyCircleAdapter(this, mSh11x5AdapterData, RECY_SMALL_CIRCLR);
        mLottoAdapter = new IssueRecyCircleAdapter(this, mLottoAdapterData, RECY_SMALL_CIRCLR);
        mSort3Adapter = new IssueRecyCircleAdapter(this, mSort3AdapterData, RECY_SMALL_CIRCLR);
        mSort5Adapter = new IssueRecyCircleAdapter(this, mSort5AdapterData, RECY_SMALL_CIRCLR);
        mQxAdapter = new IssueRecyCircleAdapter(this, mQxAdapterData, RECY_SMALL_CIRCLR);
        mSfAdapter = new IssueRecyCircleAdapter(this, mSfAdapterData, RECY_BLOCK);
        mJqc4Adapter = new IssueRecyCircleAdapter(this, mJqc4AdapterData, RECY_BLOCK);
        mBqc6Adapter = new IssueRecyCircleAdapter(this, mBqc6AdapterData, RECY_BLOCK);
    }

    private void loadData() {

        IssueBonusProtocol protocol = new IssueBonusProtocol(this);
        protocol.load(this, this);
    }

    @Override
    public void onSucess(IssueBonusBean.DataBean data) {
        LogUtils.i("IssueBouns success");
        scrollView.setVisibility(View.VISIBLE);
        initData(data);
        mSh11x5dapter.notifyDataSetChanged();
        mLottoAdapter.notifyDataSetChanged();
        mSort3Adapter.notifyDataSetChanged();
        mSort5Adapter.notifyDataSetChanged();
        mQxAdapter.notifyDataSetChanged();
        mSfAdapter.notifyDataSetChanged();
        mJqc4Adapter.notifyDataSetChanged();
        mBqc6Adapter.notifyDataSetChanged();
    }

    private void initData(IssueBonusBean.DataBean data) {
        LogUtils.i("initData :" + data);
        if (data != null) {
            ArrayList<IssueBonusBean.DataBean.ListBean> list = (ArrayList<IssueBonusBean.DataBean.ListBean>) data.getList();
            for (int i = 0; i < list.size(); i++) {
                IssueBonusBean.DataBean.ListBean bean = list.get(i);
                LogUtils.i("initData lotid:" + bean.getLotId());
                if (Configure.LOTID_SH11X5.equals(bean.getLotId())) {
                    mSh11x5Data = bean;
                } else if (Configure.LOTID_LOTTO.equals(bean.getLotId())) {
                    mLotto5Data = bean;
                } else if (Configure.LOTID_QIXING.equals(bean.getLotId())) {
                    mQxData = bean;
                } else if (Configure.LOTID_SORT3.equals(bean.getLotId())) {
                    mSort3Data = bean;
                } else if (Configure.LOTID_SORT5.equals(bean.getLotId())) {
                    mSort5Data = bean;
                } else if (Configure.LOTID_BANQUANCHANG.equals(bean.getLotId())) {
                    mBqc6Data = bean;
                } else if (Configure.LOTID_SHENGFU.equals(bean.getLotId())) {
                    mSfData = bean;
                } else if (Configure.LOTID_JCBASKETBALL.equals(bean.getLotId())) {
                    mBasketballData = bean;
                } else if (Configure.LOTID_JCSOCCER.equals(bean.getLotId())) {
                    mSoccerData = bean;
                } else if (Configure.LOTID_JINQIUCAI.equals(bean.getLotId())) {
                    mJqc4Data = bean;
                }

            }
        }
        setAdapterData(Configure.SIGN_SH11X5, mSh11x5Data, mSh11x5AdapterData);
        setAdapterData(Configure.SIGN_LOTTO, mLotto5Data, mLottoAdapterData);
        setAdapterData(Configure.SIGN_SORT3, mSort3Data, mSort3AdapterData);
        setAdapterData(Configure.SIGN_SORT5, mSort5Data, mSort5AdapterData);
        setAdapterData(Configure.SIGN_BANQUANCHANG_4, mJqc4Data, mJqc4AdapterData);
        setAdapterData(Configure.SIGN_BANQUANCHANG_6, mBqc6Data, mBqc6AdapterData);
        setAdapterData(Configure.SIGN_SHENGFU, mSfData, mSfAdapterData);
        setAdapterData(Configure.SIGN_QIXING, mQxData, mQxAdapterData);

        initSh115Title(mIssuno_sh115, mDate_sh115, mSh11x5Data);
        initTitle(mIssuno_lotto, mDate_lotto, mLotto5Data);
        initTitle(mIssuno_jqc4, mDate_jqc4, mJqc4Data);
        initJincai(mIssuno_basketball, mDate_basketball, mBasketballData);
        initTitle(mIssuno_sf, mDate_sf, mSfData);
        initTitle(mIssuno_qixin, mDate_qixin, mQxData);
        initTitle(mIssuno_pl5, mDate_pl5, mSort5Data);
        initTitle(mIssuno_pl3, mDate_pl3, mSort3Data);
        initJincai(mIssuno_soccer, mDate_soccer, mSoccerData);
        initTitle(mIssuno_bqc6, mDate_bqc6, mJqc4Data);
    }

    private void initJincai(TextView issno, TextView count, IssueBonusBean.DataBean.ListBean data) {
        issno.setText("第" + data.getIssueNo() + "期");
        count.setText("已开奖" + data.getMatchCount() + "场");
    }

    private void initTitle(TextView issno, TextView time, IssueBonusBean.DataBean.ListBean data) {
        if (data != null) {
            issno.setText("第" + data.getIssueNo() + "期");
            long timeLong = data.getBonusTime();
            time.setText(DateUtils.formatDate(timeLong * 1000) + " " + DateUtils.formatTimeSimple(timeLong * 1000));
        }
    }

    private void initSh115Title(TextView issno, TextView time, IssueBonusBean.DataBean.ListBean data) {
        if (data != null) {
            issno.setText("第" + data.getIssueNo() + "期");
            long timeLong = data.getBonusTime();
            time.setText(DateUtils.formatDate(timeLong * 1000) + " " + DateUtils.formatTimeSimple(timeLong * 1000));
        }
    }

    private void setAdapterData(int key, IssueBonusBean.DataBean.ListBean data, ArrayList<BaseBean> adapterData) {
        if (data != null && adapterData != null) {
            adapterData.clear();
            String code = data.getBonusCode();
            LogUtils.i("setAdapterData code:" + code);
            if (code.contains("#")) {
                String redData = code.substring(0, code.indexOf('#'));
                LogUtils.i("setAdapterData red:" + redData);
                String blueData = code.substring(code.indexOf('#') + 1, code.length());
                LogUtils.i("setAdapterData blue::::" + blueData);
                String[] red = redData.split(",");
                String[] blue = blueData.split(",");
                for (int i = 0; i < red.length; i++) {
                    BaseBean bean = new BaseBean();
                    bean.setMsg(red[i]);
                    bean.setRed(true);
                    adapterData.add(bean);
                }
                for (int i = 0; i < blue.length; i++) {
                    BaseBean bean = new BaseBean();
                    bean.setMsg(blue[i]);
                    bean.setRed(false);
                    adapterData.add(bean);
                }
            } else {
                String[] codeData = code.split(",");
                for (int i = 0; i < codeData.length; i++) {
                    BaseBean bean = new BaseBean();
                    bean.setMsg(codeData[i]);
                    bean.setRed(true);
                    adapterData.add(bean);
                }
            }
            LogUtils.i("setAdapterData dataSize:" + adapterData.size());
        }
    }

    @Override
    public void onError() {
        LogUtils.i("IssueBouns eror");
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(IssueBountsActivity.this, IssueBounsDetailActivity.class);
            Bundle bundle = new Bundle();
            switch (view.getId()) {
                case R.id.issue_lotto_layout:
                    bundle.putInt("detailType", DETAIL_NUMBER);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_lotto));
                    bundle.putSerializable("lotid", "dlt");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.issue_Qx_layout:
                    bundle.putInt("detailType", DETAIL_NUMBER);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_qixing));
                    bundle.putSerializable("lotid", "qxc");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.issue_sort3_layout:
                    bundle.putInt("detailType", DETAIL_NUMBER);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_pl3));
                    bundle.putSerializable("lotid", "pl3");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.issue_sort5_layout:
                    bundle.putInt("detailType", DETAIL_NUMBER);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_pl5));
                    bundle.putSerializable("lotid", "pl5");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.issue_sh11x5_layout:
                    bundle.putInt("detailType", DETAIL_SH115);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_sh11x5));
                    bundle.putSerializable("lotid", "sh115");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.issue_jcBasketBall_layout:
                    bundle.putInt("detailType", DETAIL_JINGCAI);
                    bundle.putSerializable("issueNumber", null);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jcBasketball));
                    bundle.putSerializable("lotid", "jclq");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

                case R.id.issue_jcSoccer_layout:
                    bundle.putInt("detailType", DETAIL_JINGCAI);
                    bundle.putSerializable("issueNumber", null);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jcSoccer));
                    bundle.putSerializable("lotid", "jczq");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

                case R.id.issue_Sf_layout:
                    bundle.putInt("detailType", DETAIL_SOCCER);
                    bundle.putSerializable("issueNumber", null);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_sf));
                    bundle.putSerializable("lotid", "sfc");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.issue_Bqc4_layout:
                    bundle.putInt("detailType", DETAIL_SOCCER);
                    bundle.putSerializable("issueNumber", null);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jqc4));
                    bundle.putSerializable("lotid", "jqc");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.issue_Bqc6_layout:
                    bundle.putInt("detailType", DETAIL_SOCCER);
                    bundle.putSerializable("issueNumber", null);
                    bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_bqc6));
                    bundle.putSerializable("lotid", "bqc");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.titlebar_indicator:
                    startActivity(new Intent(IssueBountsActivity.this, SendSettingActivity.class));
                    break;

            }

        }
    }

    @Override
    protected String getBaidutitle() {
        return "开奖首页";
    }
}
