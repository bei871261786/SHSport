package shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusNumberDetailBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.callback.CalculateEventBus;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-02-0002 09:28
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class CalculatePl3Fragment extends BaseFragment {


    @BindView(R.id.calculate_issue_tv)
    TextView calculateIssueTv;
    @BindView(R.id.stoptime_tv)
    TextView stoptimeTv;
    @BindView(R.id.pl3_ball1_tv)
    TextView pl3Ball1Tv;
    @BindView(R.id.pl3_ball2_tv)
    TextView pl3Ball2Tv;
    @BindView(R.id.pl3_ball3_tv)
    TextView pl3Ball3Tv;
    @BindView(R.id.calculate_top_ll)
    LinearLayout calculateTopLl;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.pl3_zhixuan_rb)
    RadioButton pl3ZhixuanRb;
    @BindView(R.id.pl3_zu3_rb)
    RadioButton pl3Zu3Rb;
    @BindView(R.id.pl3_zu6_rb)
    RadioButton pl3Zu6Rb;
    @BindView(R.id.pl3_rg)
    RadioGroup pl3Rg;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.pl3_childfragment)
    FrameLayout pl3Childfragment;
    @BindView(R.id.pl3_fragment_cal)
    RelativeLayout pl3FragmentCal;
    private IssueBonusBean issueBonusBean;
    private IssueBonusNumberDetailBean issueBonusNumberDetailBean;//开奖数据详情
    private String issueNo;

    private MyOnclickListener myOnclickListener;

    private NumLotOrderBean numLotOrderBean;

    private BaseFragment baseFragment;


    //延迟消息更新开奖数据  fragment 还未创建
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updatestickyUi(CalculateEventBus messageEvent) {
        issueBonusBean = messageEvent.getIssueBonusBean();
        if (issueBonusBean != null) {
            for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
                if (issueBonusBean.getData().getList().get(i).getLotId().equals("pl3")) {
                    issueNo = issueBonusBean.getData().getList().get(i).getIssueNo();
                    calculateIssueTv.setText("第"+issueBonusBean.getData().getList().get(i).getIssueNo() + "期");
                    long time = 1000 * (long) issueBonusBean.getData().getList().get(i).getBonusTime();
                    String s = DateUtils.formatDate(time);
                    stoptimeTv.setText(s);
                    String arr[] = issueBonusBean.getData().getList().get(i).getBonusCode().split(",");
                    pl3Ball1Tv.setText(arr[0]);
                    pl3Ball2Tv.setText(arr[1]);
                    pl3Ball3Tv.setText(arr[2]);
                }
            }
        }
    }

    //选择期号后,重新获取数据,更新到ui  fragment已经创建
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUi(CalculateEventBus messageEvent) {
        issueBonusNumberDetailBean = messageEvent.getIssueBonusNumberDetailBean();

        if (issueBonusNumberDetailBean != null) {
            if (issueBonusNumberDetailBean.getData().getLotId().equals("pl3")) {
                issueNo = issueBonusNumberDetailBean.getData().getIssueNo();
                calculateIssueTv.setText("第"+issueBonusNumberDetailBean.getData().getIssueNo() + "期");
//                long time = 1000 * (long) issueBonusNumberDetailBean.getData().getBonusDate();
//                String s = DateUtils.getPl5DateAndTime(time);
                stoptimeTv.setText(issueBonusNumberDetailBean.getData().getBonusDate());
                String arr[] = issueBonusNumberDetailBean.getData().getBonusCode().split(",");
                pl3Ball1Tv.setText(arr[0]);
                pl3Ball2Tv.setText(arr[1]);
                pl3Ball3Tv.setText(arr[2]);
                Logger.e("event");
            }
        }
    }


    @Override
    protected View createLoadedView() {
        View view = UIUtils.inflate(R.layout.fragment_pl3_cal);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        FragmentManager fragmentManager = getChildFragmentManager();
        baseFragment = new CalPL3NomalFragment();
        fragmentManager.beginTransaction().replace(R.id.pl3_childfragment, baseFragment).commit();
        pl3Rg.check(R.id.pl3_zhixuan_rb);
        myOnclickListener = new MyOnclickListener();
        pl3Rg.setOnCheckedChangeListener(myOnclickListener);
        return view;
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }


    private class MyOnclickListener implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

        @Override
        public void onClick(View v) {

        }


        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentManager fragmentManager = getChildFragmentManager();

            switch (checkedId) {
                case R.id.pl3_zhixuan_rb:
                    baseFragment = new CalPL3NomalFragment();
                    break;
                case R.id.pl3_zu3_rb:
                    baseFragment = new CalPL3Zu3Fragment();
                    break;
                case R.id.pl3_zu6_rb:
                    baseFragment = new CalPL3Zu6Fragment();
                    break;
                default:
                    break;
            }
            Bundle bundle = new Bundle();
            bundle.putString("issueNo", issueNo);
            baseFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.pl3_childfragment, baseFragment).commit();
        }
    }
    @Override
    protected String getTitle() {
        return "奖金计算排列三";
    }
}
