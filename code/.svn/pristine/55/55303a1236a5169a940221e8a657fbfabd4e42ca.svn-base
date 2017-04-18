package shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusSoccerDetailBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.callback.CalculateEventBus;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter.CalculateBqcLsvAdapter;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-05-0005 09:20
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class CalaculateBqcFragment extends CalculateBaseFragment {

    @BindView(R.id.calculate_issue_tv)
    TextView calculateIssueTv;
    @BindView(R.id.stoptime_tv)
    TextView stoptimeTv;
    @BindView(R.id.sfc_ball1_tv)
    TextView sfcBall1Tv;
    @BindView(R.id.sfc_ball2_tv)
    TextView sfcBall2Tv;
    @BindView(R.id.sfc_ball3_tv)
    TextView sfcBall3Tv;
    @BindView(R.id.sfc_ball4_tv)
    TextView sfcBall4Tv;
    @BindView(R.id.sfc_ball5_tv)
    TextView sfcBall5Tv;
    @BindView(R.id.sfc_ball6_tv)
    TextView sfcBall6Tv;
    @BindView(R.id.sfc_ball7_tv)
    TextView sfcBall7Tv;
    @BindView(R.id.sfc_ball8_tv)
    TextView sfcBall8Tv;
    @BindView(R.id.sfc_ball9_tv)
    TextView sfcBall9Tv;
    @BindView(R.id.sfc_ball10_tv)
    TextView sfcBall10Tv;
    @BindView(R.id.sfc_ball11_tv)
    TextView sfcBall11Tv;
    @BindView(R.id.sfc_ball12_tv)
    TextView sfcBall12Tv;
    @BindView(R.id.sfc_ball13_tv)
    TextView sfcBall13Tv;
    @BindView(R.id.sfc_ball14_tv)
    TextView sfcBall14Tv;
    @BindView(R.id.sfc_ll)
    LinearLayout sfcLl;
    @BindView(R.id.textbei_tv)
    TextView textbeiTv;
    @BindView(R.id.calculate_plus_im)
    ImageView calculatePlusIm;
    @BindView(R.id.calculate_multiple_edit)
    EditText calculateMultipleEdit;
    @BindView(R.id.calculate_minus_im)
    ImageView calculateMinusIm;
    @BindView(R.id.beishu_tv)
    TextView beishuTv;
    @BindView(R.id.calculate_count_tv)
    TextView calculateCountTv;
    @BindView(R.id.calculate_money_tv)
    TextView calculateMoneyTv;
    @BindView(R.id.calculate_deleteall_tv)
    TextView calculateDeleteallTv;
    @BindView(R.id.calculate_sum_bt)
    TextView calculateSumBt;
    @BindView(R.id.calculatebottom_ll)
    LinearLayout calculatebottomLl;
    @BindView(R.id.sf14_listview)
    ListView sf14Listview;
    @BindView(R.id.activity_sf14)
    RelativeLayout activitySf14;

    private List<Sf14Bean.DataBean.IssueListBean.MatchListBean> MatchListBeans = new ArrayList<>();

    private int total;//注数

    private CalculateBqcLsvAdapter sf14CalculateLsvAdapter;

    private NumLotOrderBean numLotOrderBean;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            total = handlerMeath(sf14CalculateLsvAdapter.getmSf14Beans());
            if (total > 0) {

                calculateMoneyTv.setVisibility(View.VISIBLE);
                calculateSumBt.setEnabled(true);
                int value=Integer.parseInt(calculateMultipleEdit.getText().toString());
                calculateCountTv.setText(total + "注");
                calculateMoneyTv.setText(total * 2 *value+ "元");
                TextUtils.setStrColor(calculateCountTv, total + "注", total + "", getActivity().getResources().getColor(R.color.select_red));
                TextUtils.setStrColor(calculateMoneyTv, total * 2 *value+ "元", total * 2 *value+ "", getActivity().getResources().getColor(R.color.select_red));

            } else {
                calculateMoneyTv.setVisibility(View.INVISIBLE);
                calculateSumBt.setEnabled(false);
                calculateCountTv.setText("请选择投注号码");
            }
        }
    };

    int count = 0;//串数
    private List<Integer> checkNum = new ArrayList<>();//每行选中个数的集合

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.fragment_shengfu14_cal);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        calculateMoneyTv.setVisibility(View.INVISIBLE);
        calculateSumBt.setEnabled(false);
        baseOnclickListener = new baseOnclickListener();
        calculateSumBt.setOnClickListener(baseOnclickListener);
        calculateDeleteallTv.setOnClickListener(baseOnclickListener);
        loadData();
        return view;
    }


    /**
     * 计算注数,一遍handler计算
     *
     * @param matchListBeans
     * @return
     */
    private int handlerMeath(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> matchListBeans) {
        if (null != matchListBeans) {
            checkNum.clear();
            for (int i = 0; i < matchListBeans.size(); i++) {
                int j = 0;
                int f = 0;
                if (matchListBeans.get(i).getCheckedHashMap().get(0)) {
                    j++;
                }
                if (matchListBeans.get(i).getCheckedHashMap().get(1)) {
                    j++;
                }
                if (matchListBeans.get(i).getCheckedHashMap().get(2)) {
                    j++;
                }
                if (matchListBeans.get(i).getCheckedHashMap().get(3)) {
                    f++;
                }
                if (matchListBeans.get(i).getCheckedHashMap().get(4)) {
                    f++;
                }
                if (matchListBeans.get(i).getCheckedHashMap().get(5)) {
                    f++;
                }

                checkNum.add(j);
                checkNum.add(f);
            }

            for (int m = 0; m < checkNum.size(); m++) {
                if (m == 0) {
                    count = checkNum.get(0);//相当于初始化串数
                    Logger.e(count + "__0__count的值");
                } else {
                    count = count * checkNum.get(m);//遍历乘以选中的个数就是注数
                }
                Logger.e(count + "____count的值");
            }
            return count;
        }
        return count;
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updatestickyUi(CalculateEventBus messageEvent) {
        issueBonusBean = messageEvent.getIssueBonusBean();
        if (issueBonusBean != null) {
            for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
                if (issueBonusBean.getData().getList().get(i).getLotId().equals("bqc")) {
                    issueNo = issueBonusBean.getData().getList().get(i).getIssueNo();
                    calculateIssueTv.setText("第"+issueBonusBean.getData().getList().get(i).getIssueNo() + "期");
                    long time = 1000 * (long) issueBonusBean.getData().getList().get(i).getBonusTime();
                    String s = DateUtils.formatDate(time);
                    stoptimeTv.setText(s);
                    String arr[] = issueBonusBean.getData().getList().get(i).getBonusCode().split(",");
                    sfcBall1Tv.setText(arr[0]);
                    sfcBall2Tv.setText(arr[1]);
                    sfcBall3Tv.setText(arr[2]);
                    sfcBall4Tv.setText(arr[3]);
                    sfcBall5Tv.setText(arr[4]);
                    sfcBall6Tv.setText(arr[5]);
                    sfcBall7Tv.setText(arr[6]);
                    sfcBall8Tv.setText(arr[7]);
                    sfcBall9Tv. setText(arr[8]);
                    sfcBall10Tv.setText(arr[9]);
                    sfcBall11Tv.setText(arr[10]);
                    sfcBall12Tv.setText(arr[11]);
                    sfcBall13Tv.setVisibility(View.GONE);
                    sfcBall14Tv.setVisibility(View.GONE);

                }
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUi(CalculateEventBus messageEvent) {
        issueBonusSoccerDetailBean = messageEvent.getIssueBonusSoccerDetailBean();
        if (issueBonusSoccerDetailBean != null) {
            if (issueBonusSoccerDetailBean.getData().getLotId().equals("bqc")) {
                issueNo = issueBonusSoccerDetailBean.getData().getIssueNo();
                calculateIssueTv.setText("第"+issueBonusSoccerDetailBean.getData().getIssueNo() + "期");
                stoptimeTv.setText(issueBonusSoccerDetailBean.getData().getBonusDate());
                String arr[] = issueBonusSoccerDetailBean.getData().getBonusCode().split(",");
                sfcBall1Tv.setText(arr[0]);
                sfcBall2Tv.setText(arr[1]);
                sfcBall3Tv.setText(arr[2]);
                sfcBall4Tv.setText(arr[3]);
                sfcBall5Tv.setText(arr[4]);
                sfcBall6Tv.setText(arr[5]);
                sfcBall7Tv.setText(arr[6]);
                sfcBall8Tv.setText(arr[7]);
                sfcBall9Tv. setText(arr[8]);
                sfcBall10Tv.setText(arr[9]);
                sfcBall11Tv.setText(arr[10]);
                sfcBall12Tv.setText(arr[11]);
                sfcBall13Tv.setVisibility(View.GONE);
                sfcBall14Tv.setVisibility(View.GONE);

                issueBonusSoccerDetailBean.getData().getMatchList();
                MatchListBeans.clear();
                for (int i = 0; i < issueBonusSoccerDetailBean.getData().getMatchList().size(); i++) {
                    Sf14Bean.DataBean.IssueListBean.MatchListBean matchListBean = new Sf14Bean.DataBean.IssueListBean.MatchListBean();
                    matchListBean.setLeagueName(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getLeagueName());
                    matchListBean.setLeagueColor(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getLeagueColor());
                    matchListBean.setHostName(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getHostName());
                    matchListBean.setVisitName(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getVisitName());
                    matchListBean.setMatchTime(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getMatchTime());
                    matchListBean.setLeagueId(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getLeagueId());
                    matchListBean.setMatchNo(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getMatchNo());
                    matchListBean.setHostRank(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getHostRank());
                    matchListBean.setVisitRank(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getVisitRank());
                    MatchListBeans.add(matchListBean);
                }
                sf14CalculateLsvAdapter = new CalculateBqcLsvAdapter(MatchListBeans, getActivity(), mHandler);
                Logger.e(MatchListBeans.size() + "Sf14");
                sf14Listview.setAdapter(sf14CalculateLsvAdapter);
                mHandler.sendEmptyMessage(0);
            }
        }
    }


    @Override
    public void initLotteryData() {
        if (mNumLotOrderBeans == null) {
            mNumLotOrderBeans = new ArrayList<>();
        }
        numLotOrderBean = new NumLotOrderBean();
        SfcDingdanBean sfcDingdanBean = new SfcDingdanBean();
        sfcDingdanBean.setMatchListBeans(MatchListBeans);
        sfcDingdanBean.setCount(count);
        if (count > 1) {
            sfcDingdanBean.setDanOrfushi("f");
        } else {
            sfcDingdanBean.setDanOrfushi("d");
        }
        sfcDingdanBean.setLotId("bqc");
        numLotOrderBean.setLotId("bqc");
        numLotOrderBean.setCount(count);

        numLotOrderBean.setSfcDingdanBean(sfcDingdanBean);
        numLotOrderBean.setIssueNo(issueNo);
        numLotOrderBean.setMultiple(calculateMultipleEdit.getText().toString());
        if (total > 1) {
            numLotOrderBean.setGuoguanType("f");
        } else {
            numLotOrderBean.setGuoguanType("d");
        }
        mNumLotOrderBeans.clear();
        mNumLotOrderBeans.add(numLotOrderBean);
    }

    @Override
    public void clearData() {
        for (int i = 0; i < MatchListBeans.size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<>();
            map.put(0, false);
            map.put(1, false);
            map.put(2, false);
            map.put(3, false);
            map.put(4, false);
            map.put(5, false);
            MatchListBeans.get(i).setCheckedHashMap(map);
            sf14CalculateLsvAdapter.notifyDataSetChanged();
        }
    }



    //因为胜负彩一级接口不包括 对战详情,需要创建时获取数据
    private void loadData() {
        OkGo.get(UrlManager.getBonusDetail(getActivity(), "bqc", issueNo)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                issueBonusSoccerDetailBean = gson.fromJson(s, IssueBonusSoccerDetailBean.class);
                if (issueBonusSoccerDetailBean != null&&issueBonusSoccerDetailBean.getRet()==100) {
                        issueNo = issueBonusSoccerDetailBean.getData().getIssueNo();
                        calculateIssueTv.setText(issueBonusSoccerDetailBean.getData().getIssueNo() + "期");
                        stoptimeTv.setText(issueBonusSoccerDetailBean.getData().getBonusDate());
                        String arr[] = issueBonusSoccerDetailBean.getData().getBonusCode().split(",");
                        sfcBall1Tv.setText(arr[0]);
                        sfcBall2Tv.setText(arr[1]);
                        sfcBall3Tv.setText(arr[2]);
                        sfcBall4Tv.setText(arr[3]);
                        sfcBall5Tv.setText(arr[4]);
                        sfcBall6Tv.setText(arr[5]);
                        sfcBall7Tv.setText(arr[6]);
                        sfcBall8Tv.setText(arr[7]);
                        sfcBall9Tv. setText(arr[8]);
                        sfcBall10Tv.setText(arr[9]);
                        sfcBall11Tv.setText(arr[10]);
                        sfcBall12Tv.setText(arr[11]);
                        sfcBall13Tv.setVisibility(View.GONE);
                        sfcBall14Tv.setVisibility(View.GONE);

                        issueBonusSoccerDetailBean.getData().getMatchList();
                        MatchListBeans.clear();
                        for (int i = 0; i < issueBonusSoccerDetailBean.getData().getMatchList().size(); i++) {
                            Sf14Bean.DataBean.IssueListBean.MatchListBean matchListBean = new Sf14Bean.DataBean.IssueListBean.MatchListBean();
                            matchListBean.setLeagueName(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getLeagueName());
                            matchListBean.setLeagueColor(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getLeagueColor());
                            matchListBean.setHostName(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getHostName());
                            matchListBean.setVisitName(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getVisitName());
                            matchListBean.setMatchTime(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getMatchTime());
                            matchListBean.setLeagueId(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getLeagueId());
                            matchListBean.setMatchNo(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getMatchNo());
                            matchListBean.setHostRank(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getHostRank());
                            matchListBean.setVisitRank(issueBonusSoccerDetailBean.getData().getMatchList().get(i).getVisitRank());
                            MatchListBeans.add(matchListBean);
                        }
                        sf14CalculateLsvAdapter = new CalculateBqcLsvAdapter(MatchListBeans, getActivity(), mHandler);
                        sf14Listview.setAdapter(sf14CalculateLsvAdapter);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络设置");
            }
        });
    }
    @Override
    protected String getTitle() {
        return "奖金计算半全场";
    }
}
