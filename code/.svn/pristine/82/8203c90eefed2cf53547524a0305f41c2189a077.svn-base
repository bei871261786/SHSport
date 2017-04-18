package shlottery.gov.cn.lotterycenter.ui.nublottery.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.callback.Pl3Evenbus;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.NumLotteryDingdanActivity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.NumGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.CalculateStage;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.RandomUtils;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.VibratorUtil;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-21-0021 15:35
 * 描    述：
 * 修订历史：
 * ================================================
 */

@SuppressLint("ValidFragment")
public class PL3Zu6Fragment extends BaseFragment {


    @BindView(R.id.pl3_issue_tv)
    TextView pl3IssueTv;
    @BindView(R.id.pl3_stoptime_tv)
    TextView pl3StoptimeTv;
    @BindView(R.id.z3_l1gridview)
    GridViewWithoutScroll z3L1gridview;
    @BindView(R.id.zu3_scorll)
    ScrollView zu3Scorll;
    @BindView(R.id.baselottery_deleteall_tv)
    TextView baselotteryDeleteallTv;
    @BindView(R.id.baselottery_total_tv)
    TextView baselotteryTotalTv;
    @BindView(R.id.baselottery_money_tv)
    TextView baselotteryMoneyTv;
    @BindView(R.id.baselottery_sum_bt)
    TextView baselotterySumBt;
    @BindView(R.id.baselottery_bottom_ll)
    LinearLayout baselotteryBottomLl;
    @BindView(R.id.shake_layout)
    RelativeLayout mShake;

    @BindView(R.id.pl3_guize_tv)
    TextView pl3GuizeTv;
    @BindView(R.id.pl3_jiangjin_tv)
    TextView pl3JiangjinTv;

    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private NumGridViewAdapter mPL3L1GridViewAdapter;

    private int count1;

    private ShakeUtils mShakeUtils = null;//摇一摇

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 1:
                    baselotterySumBt.setEnabled(true);
                     total=0;
                    if (count1 > 3) {
                        total = CalculateStage.getFactorial(count1).divide(CalculateStage.getFactorial(3)).divide(CalculateStage.getFactorial(count1 - 3)).intValue();
                    } else {
                        total = 1;
                    }
                    baselotteryTotalTv.setText("共" + total + "注" + "  " + total * 2 + "元");
                    break;
                case 2:
                    baselotterySumBt.setEnabled(false);
                    baselotteryTotalTv.setText("请选择投注号码");
                    break;
            }

        }
    };

    private List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();//提交数据
    private List<NumLotOrderBean> mNumLotOrderBeans ;
    int total;
    private MyListener mListener;
    NumLotteryBean mNumLotteryBean;

    @SuppressLint("ValidFragment")
    public PL3Zu6Fragment(ShakeUtils mShakeUtils,List<NumLotOrderBean> mNumLotOrderBeans) {
        this.mShakeUtils = mShakeUtils;
        this. mNumLotOrderBeans=mNumLotOrderBeans;
    }

    @Override
    protected View createLoadedView() {
        View mView = UIUtils.inflate(R.layout.fragment_pl3_zu3);
        ButterKnife.bind(this, mView);
        for (int i = 0; i < 10; i++) {
            mMissList.add(i + "");
        }
        mPL3L1GridViewAdapter = new NumGridViewAdapter(mMissList, getActivity());
        setAdapter(z3L1gridview, mPL3L1GridViewAdapter);
        baselotteryTotalTv.setText("请选择投注号码");
        pl3GuizeTv.setText("至少选择3个号码");
        TextUtils.setStrColor(pl3JiangjinTv,"猜对开奖号(顺序不限)即中173元",173+"", BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        baselotteryMoneyTv.setVisibility(View.GONE);
        initShakeUtils();
        baselotterySumBt.setEnabled(false);
        mListener = new MyListener();
        baselotteryDeleteallTv.setOnClickListener(mListener);
        baselotterySumBt.setOnClickListener(mListener);
        mShake.setOnClickListener(mListener);
        UpDateData();
        return mView;
    }

    private void UpDateData() {
        String lotid = "pl3";
        OkGo.get(UrlManager.getSaleIssueUrl(getActivity(), lotid)).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Logger.e(s + "排列五请求成功");
                Gson gson = new Gson();
                mNumLotteryBean = gson.fromJson(s, NumLotteryBean.class);
                if (null != mNumLotteryBean) {
                    Logger.e(mNumLotteryBean.getData().getLotName() + "lotnmae");
                    long time = mNumLotteryBean.getData().getIssueList().get(0).getStopTime();
                    String stime = DateUtils.getPl5DateAndTime(time * 1000);
                    String issue = "第" + mNumLotteryBean.getData().getIssueList().get(0).getIssueNo() + "期";
                    pl3IssueTv.setText(issue);
                    pl3StoptimeTv.setText("" + stime);
                    mMissList.clear();
                    String[] arr = mNumLotteryBean.getData().getOmit().split("#");
                    String[] omit1 = arr[0].split(",");
                    String[] omit2 = arr[1].split(",");
                    String[] omit3 = arr[2].split(",");
                    mMissList.clear();
                    for (int i = 0; i < 10; i++) {
                        int m = Integer.parseInt((omit1[i]));
                        int o = Integer.parseInt((omit2[i]));
                        int p = Integer.parseInt((omit3[i]));
                        int j = (m > o ? o : m);
                        j = p > j ? j : p;
                        mMissList.add(j + "");
                    }
                    mPL3L1GridViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络设置");
            }
        });
    }

    private void setAdapter(GridViewWithoutScroll g, final NumGridViewAdapter p) {
        g.setAdapter(p);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p.setSelectMap(position);
                p.notifyDataSetChanged();
                meath(p);

            }
        });
    }

    private void meath(NumGridViewAdapter p) {
        if (p.equals(mPL3L1GridViewAdapter)) {
            count1 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count1++;
                }
            }
        }
        if (count1 >= 3) {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        } else {
            Message message = new Message();
            message.what = 2;
            mHandler.sendMessage(message);
        }
    }

    //监听摇一摇,并机选数据
    private void initShakeUtils() {
        mShakeUtils.setOnShakeListener(new ShakeUtils.OnShakeListener() {
            @Override
            public void onShake() {
                VibratorUtil.Vibrate(getActivity(), 300);
                shake();
            }
        });
    }

    protected void clearData() {
        for (int i = 0; i < 10; i++) {
            mPL3L1GridViewAdapter.getSelectionMap().put(i, false);
        }
        mPL3L1GridViewAdapter.notifyDataSetChanged();
        meath(mPL3L1GridViewAdapter);
    }

    protected void shake() {
        VibratorUtil.Vibrate(getActivity(), 300);
        clearData();
        int[] mNum = RandomUtils.randomCommon(0, 9, 3);

        mPL3L1GridViewAdapter.getSelectionMap().put(mNum[0], true);
        mPL3L1GridViewAdapter.getSelectionMap().put(mNum[1], true);
        mPL3L1GridViewAdapter.getSelectionMap().put(mNum[2], true);
        mPL3L1GridViewAdapter.notifyDataSetChanged();
        meath(mPL3L1GridViewAdapter);
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mShakeUtils) {
            mShakeUtils.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mShakeUtils) {
            mShakeUtils.onPause();
        }
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.baselottery_deleteall_tv:
                    clearData();
                    break;
                case R.id.baselottery_sum_bt:
                    initLotteryData();
                    Intent mIntent = new Intent(getActivity(), NumLotteryDingdanActivity.class);
                    mIntent.putExtra("Pl5Activity", (Serializable) mNumLotOrderBeans);
                    startActivityForResult(mIntent, Configs.NUMLOTT_REQUESTCODE);
                    break;
                case R.id.shake_layout:
                    shake();
                    break;
            }
        }
    }

    private void initLotteryData() {
        lotteryData.clear();
        lotteryData.add(mPL3L1GridViewAdapter.getSelectionMap());
        NumLotOrderBean numLotOrderBean = new NumLotOrderBean();
        numLotOrderBean.setLotteryData(lotteryData);
        numLotOrderBean.setCount(total);
        numLotOrderBean.setLotId("pl3");
        numLotOrderBean.setmType(Configs.PL3_ZU6);
        if (mNumLotteryBean != null) {
            numLotOrderBean.setIssueNo(mNumLotteryBean.getData().getIssueList().get(0).getIssueNo());
        }
            numLotOrderBean.setGuoguanType("b6");
//        mNumLotOrderBeans.clear();
        mNumLotOrderBeans.add(0,numLotOrderBean);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Configs.NUMLOTT_REQUESTCODE) {
            if (resultCode == Configs.NUMLOTT_RESULTCODE) {
                clearData();
                Bundle bundle = data.getExtras();
                mNumLotOrderBeans = (List<NumLotOrderBean>) bundle.getSerializable(Configs.SFCDINGDAN_RESULTKEY);
                Pl3Evenbus pl3Evenbus = new Pl3Evenbus();
                pl3Evenbus.setmNumLotOrderBeans(mNumLotOrderBeans);
                EventBus.getDefault().post(pl3Evenbus);
            } else {
                getActivity().finish();
            }
        }
    }
    @Override
    protected String getTitle() {
        return "排列3组6";
    }
}
