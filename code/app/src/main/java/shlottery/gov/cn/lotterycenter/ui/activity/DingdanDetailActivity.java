package shlottery.gov.cn.lotterycenter.ui.activity;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.bean.ZhudanDetailBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcZDzqDetailAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.NumZDDetailAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.BqcDingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.JqcDingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.SfcDingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.ZhuDanUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-24-0024 16:42
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class DingdanDetailActivity extends BaseActivity {
    @BindView(R.id.titlebar_back)
    ImageView titlebarBack;
    @BindView(R.id.titlebar_back_ll)
    LinearLayout titlebarBackLl;
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.titlebar_indicator)
    ImageView titlebarIndicator;
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.titlebar_title)
    LinearLayout titlebarTitle;
    @BindView(R.id.filtrate_tv)
    ImageView filtrateTv;
    @BindView(R.id.main_calendar)
    ImageView mainCalendar;
    @BindView(R.id.main_setting)
    ImageView mainSetting;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.zddetail_lotid_tv)
    TextView zddetailLotidTv;
    @BindView(R.id.issue_dan_bei_tv)
    TextView issueDanBeiTv;
    @BindView(R.id.zddetail_money_tv)
    TextView zddetailMoneyTv;
    @BindView(R.id.zddetail_total_tv)
    TextView zddetailTotalTv;
    @BindView(R.id.zhudandetail_lsv)
    ListView zhudandetailLsv;
    @BindView(R.id.activity_dingdan_detail)
    LinearLayout activityDingdanDetail;
    @BindView(R.id.zhongjiang_im)
    ImageView zhongjiangIm;
    @BindView(R.id.mostbouns_tv)
    TextView mostbounsTv;
    @BindView(R.id.mostbouns_ll)
    LinearLayout mostbounsLl;
    private LoadingDialog loadingDialog;//加载框
    private BaseAdapter mNumZDDetailAdapter;

    private String stakeId;//查询注单所需的id

    private SfcDingdanBean sfcDingdanBean;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_dingdan_detail);
        ButterKnife.bind(this);
        titlebarTv.setText("模拟注单详情");
        getNetData();
    }

    @OnClick(R.id.titlebar_back_ll)
    void back() {
        closeThisActivity();
    }

    @Override
    protected void init() {
        stakeId = getIntent().getStringExtra(Configs.STAKEID_KEY);
    }

    private void getNetData() {
        if (StringUtils.isEmpty(stakeId)) {
            UIUtils.showToastSafe("该投注不存在");
            return;
        }
        //胜负彩194  排列三 180  197竞足 201比分 278大乐透普通复式 289上海115

        OkGo.get(UrlManager.getStakeDetail(this, stakeId)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                ZhudanDetailBean zhudanDetailBean = gson.fromJson(s, ZhudanDetailBean.class);
                if (null != zhudanDetailBean) {
                    if (zhudanDetailBean.getRet() == 100) {
                        if (zhudanDetailBean.getData().getIsBonus() == 1) {
                            zhongjiangIm.setVisibility(View.VISIBLE);
                        }
                        if (("pl3pl5qxcsh115dlt").contains(zhudanDetailBean.getData().getLotId())) {
                            mNumZDDetailAdapter = new NumZDDetailAdapter(zhudanDetailBean, DingdanDetailActivity.this);
                            zhudandetailLsv.setAdapter(mNumZDDetailAdapter);
                        } else if (("jzspfjzbfjzxspfjzjqsjzhh").contains(zhudanDetailBean.getData().getLotId()) || ("jzbqc".equals(zhudanDetailBean.getData().getLotId()))) {
                            mNumZDDetailAdapter = new JcZDzqDetailAdapter(zhudanDetailBean, DingdanDetailActivity.this, 0);
                            zhudandetailLsv.setAdapter(mNumZDDetailAdapter);
                        } else if (("jlsfjlrsfjldxjlfcjlhh").contains(zhudanDetailBean.getData().getLotId())) {
                            mNumZDDetailAdapter = new JcZDzqDetailAdapter(zhudanDetailBean, DingdanDetailActivity.this, 1);
                            zhudandetailLsv.setAdapter(mNumZDDetailAdapter);
                        } else if (("sfcsf14sf9bqcjqc").contains(zhudanDetailBean.getData().getLotId())) {
                            sfcDingdanBean = ZhuDanUtils.initZhuDanBean(zhudanDetailBean, sfcDingdanBean);
                            if (zhudanDetailBean.getData().getLotId().equals("sf9")) {
                                mNumZDDetailAdapter = new SfcDingdanAdapter(DingdanDetailActivity.this, sfcDingdanBean, Configs.ACTIVITYTYPE_R9);
                            } else if (zhudanDetailBean.getData().getLotId().equals("bqc")) {
                                mNumZDDetailAdapter = new BqcDingdanAdapter(DingdanDetailActivity.this, sfcDingdanBean);
                            } else if (zhudanDetailBean.getData().getLotId().equals("jqc")) {
                                mNumZDDetailAdapter = new JqcDingdanAdapter(DingdanDetailActivity.this, sfcDingdanBean);
                            } else {
                                mNumZDDetailAdapter = new SfcDingdanAdapter(DingdanDetailActivity.this, sfcDingdanBean, Configs.ACTIVITYTYPE_SF14);
                            }
                            zhudandetailLsv.setAdapter(mNumZDDetailAdapter);
                        }
                        if (zhudanDetailBean.getData().getLotName().equals("14场")) {
                            zddetailLotidTv.setText("胜负彩14场");
                        } else {
                            zddetailLotidTv.setText(zhudanDetailBean.getData().getLotName());
                        }
                        //zddetailLotidTv.setText(zhudanDetailBean.getData().getLotName());//彩种名

                        String playType = zhudanDetailBean.getData().getPlayType();
                        if (playType.length() > 12) {
                            playType = playType.substring(0, 11) + "等";
                        }
                        issueDanBeiTv.setText("第" + zhudanDetailBean.getData().getIssueNo() + "期  " + playType + "  倍投" + zhudanDetailBean.getData().getMultiple());
                        zddetailMoneyTv.setText(zhudanDetailBean.getData().getStakeMoney() + "");
                        zddetailTotalTv.setText("本次预计出票共  " + zhudanDetailBean.getData().getTicketCount() + "张  " + zhudanDetailBean.getData().getAmount() + "注");
                        if (StringUtils.isEmpty(zhudanDetailBean.getData().getBonusEstimate())) {
                            mostbounsLl.setVisibility(View.GONE);
                        } else {
                            mostbounsLl.setVisibility(View.VISIBLE);
                            BigDecimal bigDecimal = new BigDecimal(zhudanDetailBean.getData().getBonusEstimate());
                            String result = bigDecimal.toString();
                            mostbounsTv.setText(result);
                        }
                    }
                }
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                loadingDialog = new LoadingDialog(DingdanDetailActivity.this, "正在加载...");
                loadingDialog.show();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络设置");
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
            }
        });
    }

    @Override
    protected String getBaidutitle() {
        return "注单详情";
    }

    @Override
    public void onBackPressed() {
        closeThisActivity();
    }
}