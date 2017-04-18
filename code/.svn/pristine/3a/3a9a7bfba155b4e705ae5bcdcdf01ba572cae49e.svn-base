package shlottery.gov.cn.lotterycenter.ui.nublottery.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.LoginManager;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.NumLotteryDingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.NumSh115DingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.KeyBordStateUtil;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.RandomUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-05-0005 20:26
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class NumLotteryDingdanActivity extends BaseActivity {
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
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.lotto_headexpand_manual)
    LinearLayout lottoHeadexpandManual;
    @BindView(R.id.lotto_headexpand_auto_1)
    LinearLayout lottoHeadexpandAuto1;
    @BindView(R.id.clearall_tv)
    LinearLayout clearAllTv;
    @BindView(R.id.head_expand)
    LinearLayout headExpand;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.lotto_dingdan_listView)
    ListView lottoDingdanListView;
    @BindView(R.id.lotto_dingdan_minusSign)
    ImageView lottoDingdanMinusSign;
    @BindView(R.id.lotto_dingdan_multiple_edit)
    EditText lottoDingdanMultipleEdit;
    @BindView(R.id.lotto_dingdan_plusSign)
    ImageView lottoDingdanPlusSign;
    @BindView(R.id.lotto_dingdan_checkbox)
    CheckBox lottoDingdanCheckbox;
    @BindView(R.id.zuijia_tv)
    TextView zuiJia;
    @BindView(R.id.lotto_dingdan_countTxt)
    TextView lottoDingdanCountTxt;
    @BindView(R.id.lotto_dingdan_moneyTxt)
    TextView lottoDingdanMoneyTxt;
    @BindView(R.id.lotto_dingdan_save)
    Button lottoDingdanSave;
    @BindView(R.id.lotto_dingdan_bottom)
    LinearLayout lottoDingdanBottom;
    @BindView(R.id.activity_lotto)
    RelativeLayout activityLotto;
    @BindView(R.id.stop_time_tv)
    TextView stopTimeTv;
    @BindView(R.id.stopissue_tv)
    TextView stopissueTv;
    @BindView(R.id.stoptime_rl)
    RelativeLayout stopTimeRl;
    private Intent mIntent;
    private List<NumLotOrderBean> mNumLotOrderBeans;
    private int count;
    private String mType;//彩种
    private int mLittleType;//小彩种
    private boolean isDan;//是否是胆

    private int sTime;//投注截止时间
    private long sTimeLong;//投注截止时间
    private String issue;//期号 十一选5用

    private Timer timer = new Timer();
    private final int BEGIN_TASK = 5;//开始倒计时
    private final int BEGIN_REFRSH = 6;//开始刷新页面

    BaseAdapter numLotteryDingdanAdapter;
    private int countTime;//总共相加的次数,用次数除以刷新的秒数.整除就刷新数据

    private String lotId;//彩种
    private String issueNo;//期号
    private int multiple;//倍数
    private String totalMoney;//金额
    private String stakeCode;//类容

    private LoadingDialog mLoadingDialog;
    private KeyBordStateUtil keyBordStateUtil;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == BEGIN_TASK) {
                if (msg.arg1 == BEGIN_REFRSH) {
                    loadData();
                } else {
                    sTimeLong = (long) sTime * 1000;
                    String stime = DateUtils.getTimeExpend(System.currentTimeMillis(), sTimeLong);
                    stopTimeTv.setText("投注截止剩余: "+stime);
                }
            } else {
                setCountMoney();
            }
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_num_dingdan);
        ButterKnife.bind(this);
        keyBordStateUtil = new KeyBordStateUtil(this);
        lottoDingdanMultipleEdit.setOnKeyListener(new View.OnKeyListener() {
                                                      @Override
                                                      public boolean onKey(View v, int keyCode, KeyEvent event) {
                                                          if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event.getAction()) {
                                                              if (StringUtils.isEmpty(lottoDingdanMultipleEdit.getText().toString())) {
                                                                  lottoDingdanMultipleEdit.setText(1 + "");
                                                                  lottoDingdanMultipleEdit.setSelection((1 + "").length());//将光标追踪到内容的最后
                                                              } else {
                                                                  int i = Integer.parseInt(lottoDingdanMultipleEdit.getText().toString());
                                                                  if (i > 99) {
                                                                      lottoDingdanMultipleEdit.setText(99 + "");
                                                                      lottoDingdanMultipleEdit.setSelection((99 + "").length());//将光标追踪到内容的最后
                                                                  }
                                                              }
                                                              //收起键盘
                                                              InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(NumLotteryDingdanActivity.this.INPUT_METHOD_SERVICE);
                                                              imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                                              return true;
                                                          }
                                                          return false;
                                                      }
                                                  }

        );
        lottoDingdanMultipleEdit.setSelection(lottoDingdanMultipleEdit.getText().

                length()
        );//将光标追踪到内容的最后
        zuiJia.setVisibility(View.GONE);
        lottoDingdanCheckbox.setVisibility(View.GONE);
        if (mType.equals("sh115"))

        {
            stopTimeRl.setVisibility(View.VISIBLE);
            numLotteryDingdanAdapter = new NumSh115DingdanAdapter(this, mNumLotOrderBeans, mHandler);
            if (null != mNumLotOrderBeans && mNumLotOrderBeans.get(0) != null && !StringUtils.isEmpty(mNumLotOrderBeans.get(0).getIssueNo())) {
                sTime = mNumLotOrderBeans.get(0).getStoptime();
                sTimeLong = (long) sTime * 1000;
                String stime = DateUtils.getTimeExpend(System.currentTimeMillis(), sTimeLong);
                issue = mNumLotOrderBeans.get(0).getIssueNo();
                stopTimeTv.setText( "投注截止剩余: "+stime);
                stopissueTv.setText("当前第" + issue+"期" );
                startTimeTask();
            }
        } else
        {
            stopTimeRl.setVisibility(View.GONE);
            numLotteryDingdanAdapter = new NumLotteryDingdanAdapter(this, mNumLotOrderBeans, mHandler);
        }
        multiple = Integer.parseInt(lottoDingdanMultipleEdit.getText().
                toString()
        );
        lottoDingdanListView.setAdapter(numLotteryDingdanAdapter);
        lottoDingdanCountTxt.setText("共" + count + "注");
        lottoDingdanMoneyTxt.setText(2 * count * multiple + "元");
        if (mType.equals("pl5")) {
            titlebarTv.setText("排列五");
        } else if (mType.equals("qxc"))

        {
            titlebarTv.setText("七星彩");
        } else if (mType.equals("pl3"))

        {
            titlebarTv.setText("排列三");
        } else

        {
            titlebarTv.setText("上海11选5");
        }

       /* lottoDingdanMultipleEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtils.isEmpty((lottoDingdanMultipleEdit.getText().toString()))) {
                    lottoDingdanMultipleEdit.setText(1 + "");
                    lottoDingdanMultipleEdit.setSelection(lottoDingdanMultipleEdit.getText().length());
                }
                setCountMoney();
            }
        });*/
        keyBordStateUtil.addOnKeyBordStateListener(new KeyBordStateUtil.onKeyBordStateListener()

                                                   {
                                                       @Override
                                                       public void onSoftKeyBoardShow(int keyboardHeight) {

                                                       }

                                                       @Override
                                                       public void onSoftKeyBoardHide() {
                                                           if (StringUtils.isEmpty((lottoDingdanMultipleEdit.getText().toString())) || lottoDingdanMultipleEdit.getText().toString().equals("0")) {
                                                               lottoDingdanMultipleEdit.setText(1 + "");
                                                               lottoDingdanMultipleEdit.setSelection(lottoDingdanMultipleEdit.getText().length());
                                                           }
                                                           setCountMoney();
                                                       }
                                                   }

        );
    }

    @OnClick(R.id.titlebar_back_ll)
    public void finishActivity() {
        if (null == mNumLotOrderBeans || mNumLotOrderBeans.size() == 0) {
            finish();
            return;
        }
        createDialog(Configs.BACK_DIALOG);
    }

    //重写返回键
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (null == mNumLotOrderBeans || mNumLotOrderBeans.size() == 0) {
            finish();
            return;
        }
        createDialog(Configs.BACK_DIALOG);
    }

    //手动选号
    @OnClick(R.id.lotto_headexpand_manual)
    public void XuanHao() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Configs.SFCDINGDAN_RESULTKEY, (Serializable) mNumLotOrderBeans);
        this.setResult(Configs.NUMLOTT_RESULTCODE, this.getIntent().putExtras(bundle));
        finish();
    }

    //清空列表
    @OnClick(R.id.clearall_tv)
    void clearAll() {
        if (null == mNumLotOrderBeans || mNumLotOrderBeans.size() == 0) {
            return;
        }
        createDialog(Configs.DELETE_DIALOG);
    }

    //加倍
    @OnClick(R.id.lotto_dingdan_plusSign)
    void add() {
        multiple = Integer.parseInt(lottoDingdanMultipleEdit.getText().toString());
        multiple++;
        if (multiple > 99) {
            multiple = 99;
            UIUtils.showToastSafe("倍投不能超过99倍");
        }
        lottoDingdanMultipleEdit.setText(multiple + "");
        lottoDingdanMultipleEdit.setSelection((multiple + "").length());//将光标追踪到内容的最后
        setCountMoney();
    }

    //减倍
    @OnClick(R.id.lotto_dingdan_minusSign)
    void minus() {
        multiple = Integer.parseInt(lottoDingdanMultipleEdit.getText().toString());
        if (multiple > 1) {
            multiple--;
            lottoDingdanMultipleEdit.setText(multiple + "");
            lottoDingdanMultipleEdit.setSelection((multiple + "").length());//将光标追踪到内容的最后
            setCountMoney();
        }

    }

    @OnClick(R.id.lotto_dingdan_save)//保存注单
    public void saveZhudan() {
        if (LoginManager.getInstance().login(this)) {
            if (StringUtils.isEmpty(lottoDingdanMultipleEdit.getText().toString())) {
                UIUtils.showToastSafe("倍数不能为空");
                return;
            }
            multiple = Integer.parseInt(lottoDingdanMultipleEdit.getText().toString());
            totalMoney = 2 * count * multiple + "";
            if ((2 * count * multiple) > 20000) {
                UIUtils.showToastSafe("投注金额不能超过20000元");
                return;
            }
            HttpParams httpParams = new HttpParams();
            String Client = PrefUtils.getString(this, "Client", "");
            String signKey = "shgo12al";
            UserInfoBean infoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
            String secret = infoBean.getSecret();


            stakeCode = inintStakeCode(mNumLotOrderBeans);
            LogUtils.i("NumberLotteryDingdan stakeCode:" + stakeCode);
       /* if (mNumLotOrderBeans.get(0).getGuoguanType().equals("f")) {//复式
            stakeCode = "f-" + count + "-" + stakeCode;
        } else if (mNumLotOrderBeans.get(0).getGuoguanType().equals("d")) {//单式
            stakeCode = "d-" + count + "-" + stakeCode;
        } else if (mNumLotOrderBeans.get(0).getGuoguanType().equals("b3")) {//排三直选包号
            stakeCode = "b3-" + count + "-" + stakeCode;
        } else if (mNumLotOrderBeans.get(0).getGuoguanType().equals("b6")) {//排三组三包号
            stakeCode = "b6-" + count + "-" + stakeCode;
        } else {
            stakeCode = "dt-" + count + "-" + stakeCode;
        }*/

            String sign = StringUtils.getMD5(Client + secret + signKey + lotId + issueNo + multiple + totalMoney + stakeCode);
            Logger.e(stakeCode + "----stakeCode");
            httpParams.put("lotId", lotId);
            httpParams.put("issueNo", issueNo);
            httpParams.put("multiple", multiple);
            httpParams.put("totalMoney", totalMoney);
            httpParams.put("stakeCode", stakeCode);
            httpParams.put("sign", sign);
            httpParams.put("client", Client);

            if (LoginManager.getInstance().login(NumLotteryDingdanActivity.this)) {//判断是否登陆,未登录弹出登陆界面
                OkGo.get(UrlManager.addStake()).connTimeOut(5000).params(httpParams).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                        if (null != vcodeBean) {
                            if (vcodeBean.getRet() == 100) {
                                UIUtils.showToastSafe("保存成功!");
                                clearData();//清除数据
                                XuanHao();//返回购彩页
                            } else {
                                UIUtils.showToastSafe(vcodeBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mLoadingDialog = new LoadingDialog(NumLotteryDingdanActivity.this, "正在保存");
                        mLoadingDialog.show();
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        if (null != mLoadingDialog) {
                            mLoadingDialog.close();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        if (null != mLoadingDialog) {
                            mLoadingDialog.close();
                        }
                        UIUtils.showToastSafe("网络异常,请检查网络设置");
                    }
                });
            }
        }
    }

    private String inintStakeCode(List<NumLotOrderBean> mNumLotOrderBeans) {

        String s = "";
        for (int m = 0; m < mNumLotOrderBeans.size(); m++) {
            String oneStr = "";//每一行
            StringBuffer buf = new StringBuffer();
            StringBuffer buf2 = new StringBuffer();
            StringBuffer buf3 = new StringBuffer();
            if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f")) {//复式
                oneStr = "f-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d")) {//单式
                oneStr = "d-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("b3")) {//排三直选包号
                oneStr = "b3-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("b6")) {//排三组三包号
                oneStr = "b6-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d1")) {//直一单选
                oneStr = "d1-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f1")) {//直一复选
                oneStr = "f1-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d2")) {//任二单选
                oneStr = "d2-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f2")) {//任二复选
                oneStr = "f2-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d3")) {//任三单选
                oneStr = "d3-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f3")) {//任三复选
                oneStr = "f3-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d4")) {//任四单选
                oneStr = "d4-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f4")) {//任四复选
                oneStr = "f4-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d5")) {//任五单选
                oneStr = "d5-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f5")) {//任五复选
                oneStr = "f5-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d6")) {//任六单选
                oneStr = "d6-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f6")) {//任六复选
                oneStr = "f6-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d7")) {//任七单选
                oneStr = "d7-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f7")) {//任七复选
                oneStr = "f7-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d8")) {//任八单选
                oneStr = "d8-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f8")) {//任八复选
                oneStr = "f8-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d2q")) {//直二单选
                oneStr = "d2q-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f2q")) {//直二复选
                oneStr = "f2q-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d2z")) {//直二组单选
                oneStr = "d2z-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f2z")) {//直组复选
                oneStr = "f2z-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d3q")) {//直三单选
                oneStr = "d3q-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f3q")) {//直三复选
                oneStr = "f3q-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("d3z")) {//直三组单选
                oneStr = "d3z-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("f3z")) {//直三组复选
                oneStr = "f3z-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt2")) {//任八复选
                oneStr = "dt2-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt3")) {//直二单选
                oneStr = "dt3-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt4")) {//直二复选
                oneStr = "dt4-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt5")) {//直二组单选
                oneStr = "dt5-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt6")) {//直组复选
                oneStr = "dt6-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt7")) {//直三单选
                oneStr = "dt7-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt8")) {//直三复选
                oneStr = "dt8-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt2z")) {//直三组单选
                oneStr = "dt2z-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else if (mNumLotOrderBeans.get(m).getGuoguanType().equals("dt3z")) {//直三组复选
                oneStr = "dt3z-" + mNumLotOrderBeans.get(m).getCount() + "-";
            } else {
                oneStr = "dt-" + mNumLotOrderBeans.get(m).getCount() + "-";
            }
            if (mType.equals("pl5")) {//排列5
                for (int n = 0; n < mNumLotOrderBeans.get(m).getLotteryData().size(); n++) {
                    for (int i = 0; i < 10; i++) {
                        if (mNumLotOrderBeans.get(m).getLotteryData().get(n).get(i)) {
                            buf.append(i);
                        }
                    }
                    if (n < mNumLotOrderBeans.get(m).getLotteryData().size() - 1) {
                        buf.append(",");
                    }
                }
                oneStr += buf.toString();
            } else if (mType.equals("qxc")) {//七星彩
                for (int n = 0; n < mNumLotOrderBeans.get(m).getLotteryData().size(); n++) {
                    for (int i = 0; i < 10; i++) {
                        if (mNumLotOrderBeans.get(m).getLotteryData().get(n).get(i)) {
                            buf.append(i);
                        }
                    }
                    if (n < mNumLotOrderBeans.get(m).getLotteryData().size() - 1) {
                        buf.append(",");
                    }
                }
                oneStr += buf.toString();
            } else if (mType.equals("pl3")) {//排列三
                if (mNumLotOrderBeans.get(m).getmType() == Configs.PL3_ZU3) {//排三组三
                    for (int n = 0; n < mNumLotOrderBeans.get(m).getLotteryData().size(); n++) {
                        for (int i = 0; i < 10; i++) {
                            if (mNumLotOrderBeans.get(m).getLotteryData().get(n).get(i)) {
                                buf.append(i).append(",");
                            }
                        }
                    }
                    oneStr += buf.substring(0, buf.length() - 1);//替换掉最后一个逗号
                } else if (mNumLotOrderBeans.get(m).getmType() == Configs.PL3_ZU6) {//排三组六
                    for (int n = 0; n < mNumLotOrderBeans.get(m).getLotteryData().size(); n++) {
                        for (int i = 0; i < 10; i++) {
                            if (mNumLotOrderBeans.get(m).getLotteryData().get(n).get(i)) {
                                buf.append(i).append(",");
                            }
                        }
                    }
                    oneStr += buf.substring(0, buf.length() - 1);//替换掉最后一个逗号
                } else {//排三直选
                    for (int n = 0; n < mNumLotOrderBeans.get(m).getLotteryData().size(); n++) {
                        for (int i = 0; i < 10; i++) {
                            if (mNumLotOrderBeans.get(m).getLotteryData().get(n).get(i)) {
                                buf.append(i);
                            }
                        }
                        if (n < mNumLotOrderBeans.get(m).getLotteryData().size() - 1) {
                            buf.append(",");
                        }
                    }
                    oneStr += buf.toString();
                }
            } else {//11选5
                if (mNumLotOrderBeans.get(m).isdan()) {//如果是胆的情况
                    switch (mNumLotOrderBeans.get(m).getmType()) {
                        case Configs.SYX5_RXDAN2:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_RXDAN3:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_RXDAN4:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_RXDAN5:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_RXDAN6:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_RXDAN7:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_RXDAN8:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_Q2ZUXDAN:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_Q3ZUXDAN:
                            oneStr += getString(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (mNumLotOrderBeans.get(m).getmType()) {
                        case Configs.SYX5_RX2:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_RX3:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_RX4:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_RX5:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_RX6:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_RX7:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_RX8:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_QY:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_Q2ZHIX:
                            oneStr += getQ2String(mNumLotOrderBeans, m, buf, buf2);
                            break;
                        case Configs.SYX5_Q2ZUX:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        case Configs.SYX5_Q3ZHIX:
                            oneStr += getQ3String(mNumLotOrderBeans, m, buf, buf2, buf3);
                            break;
                        case Configs.SYX5_Q3ZUX:
                            oneStr += getRxString(mNumLotOrderBeans, m, buf);
                            break;
                        default:
                            break;
                    }
                }
            }
            s += oneStr;
            if (m < mNumLotOrderBeans.size() - 1) {
                s += "|";
            }
        }
        return s;
    }

    //返回任选投注的stakecode
    private String getRxString(List<NumLotOrderBean> mNumLotOrderBeans, int m, StringBuffer buf) {
        String oneStr;
        for (int i = 1; i < 12; i++) {
            if (mNumLotOrderBeans.get(m).getLotteryData().get(0).get(i - 1)) {//第一行
                if (i < 10) {
                    buf.append("0" + i).append(",");
                } else {
                    buf.append(i).append(",");
                }
            }
        }
        oneStr = buf.substring(0, buf.length() - 1);//替换掉最后一个","
        return oneStr;
    }


    //返回胆拖的投注stakecode
    @NonNull
    private String getString(List<NumLotOrderBean> mNumLotOrderBeans, int m, StringBuffer buf, StringBuffer buf2) {
        String oneStr;
        for (int i = 1; i < 12; i++) {
            if (mNumLotOrderBeans.get(m).getLotteryData().get(0).get(i - 1)) {//第一行
                if (i < 10) {
                    buf.append("0" + i).append(",");
                } else {
                    buf.append(i).append(",");
                }
            }
            if (mNumLotOrderBeans.get(m).getLotteryData().get(1).get(i - 1)) {//第二行
                if (i < 10) {
                    buf2.append("0" + i).append(",");
                } else {
                    buf2.append(i).append(",");
                }
            }
        }
        oneStr = buf.substring(0, buf.length() - 1) + "$" + buf2.substring(0, buf2.length() - 1);//替换掉最后一个","
        return oneStr;
    }

    //返回前二直选的投注stakecode
    @NonNull
    private String getQ2String(List<NumLotOrderBean> mNumLotOrderBeans, int m, StringBuffer buf, StringBuffer buf2) {
        String oneStr;
        for (int i = 1; i < 12; i++) {
            if (mNumLotOrderBeans.get(m).getLotteryData().get(0).get(i - 1)) {//第一行
                if (i < 10) {
                    buf.append("0" + i).append(",");
                } else {
                    buf.append(i).append(",");
                }
            }
            if (mNumLotOrderBeans.get(m).getLotteryData().get(1).get(i - 1)) {//第二行
                if (i < 10) {
                    buf2.append("0" + i).append(",");
                } else {
                    buf2.append(i).append(",");
                }
            }
        }
        oneStr = buf.substring(0, buf.length() - 1) + "#" + buf2.substring(0, buf2.length() - 1);//替换掉最后一个","
        return oneStr;
    }

    //返回前三直选的投注stakecode
    @NonNull
    private String getQ3String(List<NumLotOrderBean> mNumLotOrderBeans, int m, StringBuffer buf, StringBuffer buf2, StringBuffer buf3) {
        String oneStr;
        for (int i = 1; i < 12; i++) {
            if (mNumLotOrderBeans.get(m).getLotteryData().get(0).get(i - 1)) {//第一行
                if (i < 10) {
                    buf.append("0" + i).append(",");
                } else {
                    buf.append(i).append(",");
                }
            }
            if (mNumLotOrderBeans.get(m).getLotteryData().get(1).get(i - 1)) {//第二行
                if (i < 10) {
                    buf2.append("0" + i).append(",");
                } else {
                    buf2.append(i).append(",");
                }
            }
            if (mNumLotOrderBeans.get(m).getLotteryData().get(2).get(i - 1)) {//第三行
                if (i < 10) {
                    buf3.append("0" + i).append(",");
                } else {
                    buf3.append(i).append(",");
                }
            }
        }
        oneStr = buf.substring(0, buf.length() - 1) + "#" + buf2.substring(0, buf2.length() - 1) + "#" + buf3.substring(0, buf3.length() - 1);//替换掉最后一个","
        return oneStr;
    }

    //机选一注  机选一注根据不同的type生成不同的 不同的机选号码,如果是dan的话生成对应的非胆机选号
    @OnClick(R.id.lotto_headexpand_auto_1)
    public void selectOne() {
        NumLotOrderBean numLotOrderBean = new NumLotOrderBean();
        List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();
        char[] mchar;
        if (mType.equals("pl5")) {//排列5
            mchar = RandomUtils.randomInt(5).toCharArray();
            numLotOrderBean.setLotId("pl5");
            initnomalData(mchar, lotteryData);
            numLotOrderBean.setCount(1);
            numLotOrderBean.setGuoguanType("d");
            numLotOrderBean.setIssueNo(issueNo);
        } else if (mType.equals("qxc")) {//七星彩
            mchar = RandomUtils.randomInt(7).toCharArray();
            numLotOrderBean.setLotId("qxc");
            initnomalData(mchar, lotteryData);
            numLotOrderBean.setCount(1);
            numLotOrderBean.setGuoguanType("d");
            numLotOrderBean.setIssueNo(issueNo);
        } else if (mType.equals("pl3")) {//排列三
            if (mLittleType == Configs.PL3_ZU3) {//排三组三
                int[] mNum = RandomUtils.randomCommon(0, 9, 2);
                Map<Integer, Boolean> selectionMap = new HashMap<>();
                for (int j = 0; j < 10; j++) {
                    if (mNum[0] == j || mNum[1] == j) {
                        selectionMap.put(j, true);
                    } else {
                        selectionMap.put(j, false);
                    }
                }
                numLotOrderBean.setmType(Configs.PL3_ZU3);
                lotteryData.add(selectionMap);
                numLotOrderBean.setCount(2);
                numLotOrderBean.setGuoguanType("b3");
                numLotOrderBean.setIssueNo(issueNo);
            } else if (mLittleType == Configs.PL3_ZU6) {//排三组六
                int[] mNum = RandomUtils.randomCommon(0, 9, 3);
                Map<Integer, Boolean> selectionMap = new HashMap<>();
                for (int j = 0; j < 10; j++) {
                    if (mNum[0] == j || mNum[1] == j || mNum[2] == j) {
                        selectionMap.put(j, true);
                    } else {
                        selectionMap.put(j, false);
                    }
                }
                numLotOrderBean.setmType(Configs.PL3_ZU6);
                lotteryData.add(selectionMap);
                numLotOrderBean.setCount(1);
                numLotOrderBean.setGuoguanType("b6");
                numLotOrderBean.setIssueNo(issueNo);
            } else {//排三直选
                mchar = RandomUtils.randomInt(3).toCharArray();
                initnomalData(mchar, lotteryData);
                numLotOrderBean.setCount(1);
                numLotOrderBean.setGuoguanType("d");
                numLotOrderBean.setIssueNo(issueNo);
            }
            numLotOrderBean.setLotId("pl3");
        } else {//11选5
            if (isDan) {//如果是胆的情况
                Map<Integer, Boolean> selectionMap = new HashMap<>();
                int[] mNum;
                switch (mLittleType) {
                    case Configs.SYX5_RXDAN2:
                        mNum = RandomUtils.randomCommon(1, 11, 2);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX2);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setGuoguanType("d2");
                        numLotOrderBean.setStoptime(sTime);
                        break;
                    case Configs.SYX5_RXDAN3:
                        mNum = RandomUtils.randomCommon(1, 11, 3);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX3);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d3");
                        break;
                    case Configs.SYX5_RXDAN4:
                        mNum = RandomUtils.randomCommon(1, 11, 4);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX4);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setGuoguanType("d4");
                        numLotOrderBean.setStoptime(sTime);
                        break;
                    case Configs.SYX5_RXDAN5:
                        mNum = RandomUtils.randomCommon(1, 11, 5);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j || mNum[4] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX5);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d5");
                        break;
                    case Configs.SYX5_RXDAN6:
                        mNum = RandomUtils.randomCommon(1, 11, 6);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j || mNum[4] == j || mNum[5] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX6);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d6");
                        break;
                    case Configs.SYX5_RXDAN7:
                        mNum = RandomUtils.randomCommon(1, 11, 7);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j || mNum[4] == j || mNum[5] == j || mNum[6] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX7);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d7");
                        break;
                    case Configs.SYX5_RXDAN8:
                        mNum = RandomUtils.randomCommon(1, 11, 8);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j || mNum[4] == j || mNum[5] == j || mNum[6] == j || mNum[7] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX8);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d8");
                        break;
                    case Configs.SYX5_Q2ZUXDAN:
                       /* mNum = RandomUtils.randomCommon(1, 11, 2);
                        for (int i = 0; i < mNum.length; i++) {
                            int m = Integer.parseInt(String.valueOf(mNum[i]));
                            Map<Integer, Boolean> selectionMap1 = new HashMap<>();
                            for (int j = 1; j < 12; j++) {
                                if (m == j) {
                                    selectionMap1.put(j - 1, true);
                                } else {
                                    selectionMap1.put(j - 1, false);
                                }
                            }
                            lotteryData.add(selectionMap1);
                        }
                        numLotOrderBean.setmType(Configs.SYX5_Q2ZUX);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d2z");*/
                        mNum = RandomUtils.randomCommon(1, 11, 2);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_Q2ZUX);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d2z");
                        break;
                    case Configs.SYX5_Q3ZUXDAN:
                        mNum = RandomUtils.randomCommon(1, 11, 3);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_Q3ZUX);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d3z");
                        break;
                    default:
                        break;
                }
            } else {
                Map<Integer, Boolean> selectionMap = new HashMap<>();
                int[] mNum;
                switch (mLittleType) {
                    case Configs.SYX5_RX2:
                        mNum = RandomUtils.randomCommon(1, 11, 2);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX2);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d2");
                        break;
                    case Configs.SYX5_RX3:
                        mNum = RandomUtils.randomCommon(1, 11, 3);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX3);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d3");
                        break;
                    case Configs.SYX5_RX4:
                        mNum = RandomUtils.randomCommon(1, 11, 4);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX4);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d4");
                        break;
                    case Configs.SYX5_RX5:
                        mNum = RandomUtils.randomCommon(1, 11, 5);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j || mNum[4] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX5);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d5");
                        break;
                    case Configs.SYX5_RX6:
                        mNum = RandomUtils.randomCommon(1, 11, 6);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j || mNum[4] == j || mNum[5] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX6);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d6");
                        break;
                    case Configs.SYX5_RX7:
                        mNum = RandomUtils.randomCommon(1, 11, 7);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j || mNum[4] == j || mNum[5] == j || mNum[6] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX7);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d7");
                        break;
                    case Configs.SYX5_RX8:
                        mNum = RandomUtils.randomCommon(1, 11, 8);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j || mNum[3] == j || mNum[4] == j || mNum[5] == j || mNum[6] == j || mNum[7] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_RX8);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d8");
                        break;
                    case Configs.SYX5_QY:
                        mNum = RandomUtils.randomCommon(1, 11, 1);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_QY);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d1");
                        break;
                    case Configs.SYX5_Q2ZHIX:
                        mNum = RandomUtils.randomCommon(1, 11, 2);
                        for (int i = 0; i < mNum.length; i++) {
                            int m = Integer.parseInt(String.valueOf(mNum[i]));
                            Map<Integer, Boolean> selectionMap1 = new HashMap<>();
                            for (int j = 1; j < 12; j++) {
                                if (m == j) {
                                    selectionMap1.put(j - 1, true);
                                } else {
                                    selectionMap1.put(j - 1, false);
                                }
                            }
                            lotteryData.add(selectionMap1);
                        }
                        numLotOrderBean.setmType(Configs.SYX5_Q2ZHIX);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d2q");
                        break;
                    case Configs.SYX5_Q2ZUX:
                        mNum = RandomUtils.randomCommon(1, 11, 2);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_Q2ZUX);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        numLotOrderBean.setGuoguanType("d2z");
                        break;
                    case Configs.SYX5_Q3ZHIX:
                        mNum = RandomUtils.randomCommon(1, 11, 3);
                        for (int i = 0; i < mNum.length; i++) {
                            int m = Integer.parseInt(String.valueOf(mNum[i]));
                            Map<Integer, Boolean> selectionMap1 = new HashMap<>();
                            for (int j = 1; j < 12; j++) {
                                if (m == j) {
                                    selectionMap1.put(j - 1, true);
                                } else {
                                    selectionMap1.put(j - 1, false);
                                }
                            }
                            lotteryData.add(selectionMap1);
                        }
                        numLotOrderBean.setmType(Configs.SYX5_Q3ZHIX);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setGuoguanType("d3q");
                        numLotOrderBean.setStoptime(sTime);
                        break;
                    case Configs.SYX5_Q3ZUX:
                        mNum = RandomUtils.randomCommon(1, 11, 3);
                        for (int j = 1; j < 12; j++) {
                            if (mNum[0] == j || mNum[1] == j || mNum[2] == j) {
                                selectionMap.put(j - 1, true);
                            } else {
                                selectionMap.put(j - 1, false);
                            }
                        }
                        numLotOrderBean.setmType(Configs.SYX5_Q3ZUX);
                        lotteryData.add(selectionMap);
                        numLotOrderBean.setCount(1);
                        numLotOrderBean.setGuoguanType("d3z");
                        numLotOrderBean.setIssueNo(issue);
                        numLotOrderBean.setStoptime(sTime);
                        break;
                    default:
                        break;
                }
            }

            numLotOrderBean.setLotId("sh115");
        }
        numLotOrderBean.setLotteryData(lotteryData);
        mNumLotOrderBeans.add(0, numLotOrderBean);
        numLotteryDingdanAdapter.notifyDataSetChanged();
        setCountMoney();
    }

    //普通机选 初始化数据  pl5,七星彩
    private void initnomalData(char[] mchar, List<Map<Integer, Boolean>> lotteryData) {
        for (int i = 0; i < mchar.length; i++) {
            Map<Integer, Boolean> selectionMap = new HashMap<>();
            int m = Integer.parseInt(String.valueOf(mchar[i]));
            for (int j = 0; j < 10; j++) {
                if (m == j) {
                    selectionMap.put(j, true);
                } else {
                    selectionMap.put(j, false);
                }
            }
            lotteryData.add(selectionMap);
        }
    }

    //计算奖金及串数并更新
    private void setCountMoney() {
        count = 0;
        for (int n = 0; n < mNumLotOrderBeans.size(); n++) {
            count += mNumLotOrderBeans.get(n).getCount();
        }
        multiple = Integer.parseInt(lottoDingdanMultipleEdit.getText().toString());
        lottoDingdanCountTxt.setText("共" + count + "注");
        lottoDingdanMoneyTxt.setText(2 * count * multiple + "元");
    }


    @Override
    protected void init() {
        mIntent = getIntent();
        mNumLotOrderBeans = ((List<NumLotOrderBean>) mIntent.getSerializableExtra("Pl5Activity"));
        if (null != mNumLotOrderBeans) {
            for (int n = 0; n < mNumLotOrderBeans.size(); n++) {
                count += mNumLotOrderBeans.get(n).getCount();
            }
            mType = mNumLotOrderBeans.get(0).getLotId();
            mLittleType = mNumLotOrderBeans.get(0).getmType();
            isDan = mNumLotOrderBeans.get(0).isdan();
            lotId = mNumLotOrderBeans.get(0).getLotId();
            issueNo = mNumLotOrderBeans.get(0).getIssueNo();
        }
    }

    //清除号码 和返回时弹出的dialog
    public void createDialog(final int type) {
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dingdan_football_backdialog, null);
        final Dialog mDialog = new android.app.AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(true);

        Button cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
        Button querrn = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
        TextView textView = (TextView) mDialogView.findViewById(R.id.rowcontainer_spf);
        TextView title = (TextView) mDialogView.findViewById(R.id.title_dialog);
        if (type == Configs.DELETE_DIALOG) {
            textView.setText("您确定要清空当前的投注内容?");
            title.setText("清空列表");
        } else {
            textView.setText("退出将清空当前已选号码");
            title.setText("返回首页");
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        querrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                if (type == Configs.DELETE_DIALOG) {
                    clearData();
                } else {
                    finish();
                }
            }
        });
    }

    //清空所有数据
    private void clearData() {
        mNumLotOrderBeans.clear();
        numLotteryDingdanAdapter.notifyDataSetChanged();
        count = 0;
        setCountMoney();
    }

    private void startTimeTask() {
        // 更新 倒计时以及期号
        if (null != timer) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = BEGIN_TASK;
                    if (sTimeLong <= System.currentTimeMillis()) {
                        if (countTime % 5 == 0) {
                            message.arg1 = BEGIN_REFRSH;
                        }
                        countTime++;
                    }
                    mHandler.sendMessage(message);
                }
            }, 1000, 1000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (timer == null) {
            timer = new Timer();
            startTimeTask();
        }
    }

    //加载数据
    private void loadData() {
        String lotid = "sh115";
        OkGo.get(UrlManager.getSaleIssueUrl(this, lotid)).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Logger.e(s + "请求成功");
                Gson gson = new Gson();
                NumLotteryBean mNumLotteryBean = gson.fromJson(s, NumLotteryBean.class);
                if (null != mNumLotteryBean) {
                    if (mNumLotteryBean.getRet() == 100) {
                        for (int i = 0; i < mNumLotteryBean.getData().getIssueList().size(); i++) {
                            long currentTime = System.currentTimeMillis();//系统当前时间
                            long startTime = (long) mNumLotteryBean.getData().getIssueList().get(i).getStartTime();
                            sTime = mNumLotteryBean.getData().getIssueList().get(i).getStopTime();
                            if (currentTime < ((long) sTime * 1000)) {
                                mNumLotOrderBeans.get(0).setStoptime(mNumLotteryBean.getData().getIssueList().get(i).getStopTime());
                                mNumLotOrderBeans.get(0).setIssueNo(mNumLotteryBean.getData().getIssueList().get(i).getIssueNo());
                                sTimeLong = (long) sTime * 1000;
                                String stime = DateUtils.getTimeExpend(System.currentTimeMillis(), sTimeLong);
                                issue = mNumLotOrderBeans.get(0).getIssueNo();
                                issueNo = mNumLotOrderBeans.get(0).getIssueNo();
                                stopTimeTv.setText("投注截止剩余: "+stime);
                                stopissueTv.setText("当前第" + issue + "期");
                                startTimeTask();
                                break;
                            }
                        }
                    } else {
                        UIUtils.showToastSafe(mNumLotteryBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络");
                Logger.e("网络异常,请检查网络");
            }
        });
    }
    @Override
    protected String getBaidutitle() {
        return "11选5或数字彩注单保存";
    }
}
