package shlottery.gov.cn.lotterycenter.ui.nublottery.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.LoginManager;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.BqcDingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.JqcDingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.Sf9DingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.SfcDingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.KeyBordStateUtil;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-08-0008 16:39
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SfcDingdanActivity extends BaseActivity {
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
    @BindView(R.id.edit_dingdan_ll)
    LinearLayout editDingdanLl;
    @BindView(R.id.sfcorder_delete_rl)
    RelativeLayout sfcorderDeleteRl;
    @BindView(R.id.sfc_dingdan_lsv)
    ListView sfcDingdanLsv;
    @BindView(R.id.sfc_money_tv)
    TextView sfcMoneyTv;
    @BindView(R.id.sfc_zdsave_tv)
    TextView sfcZdsaveTv;
    @BindView(R.id.sfc_zdsave_ll)
    LinearLayout sfcZdsaveLl;
    @BindView(R.id.main_setting)
    ImageView mainSetting;
    @BindView(R.id.sfc_beishu_tv)
    TextView sfcBeishuTv;
    @BindView(R.id.sfcmin_max_zhushu)
    TextView sfcminMaxZhushu;
    @BindView(R.id.lotto_dingdan_minusSign)
    ImageView lottoDingdanMinusSign;
    @BindView(R.id.sfccount_edit)
    EditText sfccountEdit;
    @BindView(R.id.addcount_im)
    ImageView addcountIm;
    @BindView(R.id.sfc_touzhu_rl)
    RelativeLayout sfcTouzhuRl;

    private List<SfcDingdanBean> sfcDingdanBeans = new ArrayList<>();
    private int count;//注数
    private BaseAdapter mSfcDingdanAdapter;
    private int ActivityType;

    private boolean beansIsNull = false;//是否清空了列表


    String lotId;//彩种
    String issueNo;//期号
    int multiple;//倍数
    String totalMoney;//金额
    String stakeCode;//类容

    private LoadingDialog mLoadingDialog;
    private KeyBordStateUtil keyBordStateUtil;//监听editext弹出与影藏

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sfclotr_dingdan);
        ButterKnife.bind(this);
        keyBordStateUtil = new KeyBordStateUtil(this);
        sfccountEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (StringUtils.isEmpty(sfccountEdit.getText().toString())) {
                        sfccountEdit.setText(1 + "");
                        sfccountEdit.setSelection((1 + "").length());//将光标追踪到内容的最后
                    } else {
                        int i = Integer.parseInt(sfccountEdit.getText().toString());
                        if (i > 99) {
                            sfccountEdit.setText(99 + "");
                            sfccountEdit.setSelection((99 + "").length());//将光标追踪到内容的最后
                        }
                    }

                    //收起键盘
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(SfcDingdanActivity.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        Logger.e(ActivityType + "=====" + "ActivityType");
        if (ActivityType == Configs.ACTIVITYTYPE_R9) {
            mSfcDingdanAdapter = new Sf9DingdanAdapter(this, sfcDingdanBeans.get(0), ActivityType);
            titlebarTv.setText("足彩-任选九");
        } else if (ActivityType == Configs.ACTIVITYTYPE_SF14) {
            titlebarTv.setText("足彩-14场");
            mSfcDingdanAdapter = new SfcDingdanAdapter(this, sfcDingdanBeans.get(0), ActivityType);
        } else if (ActivityType == Configs.ACTIVITYTYPE_JQC) {
            titlebarTv.setText("足彩-进球彩");
            mSfcDingdanAdapter = new JqcDingdanAdapter(this, sfcDingdanBeans.get(0));
        } else {
            titlebarTv.setText("足彩-半全场");
            mSfcDingdanAdapter = new BqcDingdanAdapter(this, sfcDingdanBeans.get(0));
        }

        sfcDingdanLsv.setAdapter(mSfcDingdanAdapter);
        multiple = Integer.parseInt(sfccountEdit.getText().toString());
        sfcMoneyTv.setText(count + "注 " + 2 * count * multiple + "元");
        TextUtils.setStrColor(sfcMoneyTv, count + "注 " + 2 * count * multiple + "元", 2 * count * multiple + "", this.getResources().getColor(R.color.select_red));


        keyBordStateUtil.addOnKeyBordStateListener(new KeyBordStateUtil.onKeyBordStateListener() {
            @Override
            public void onSoftKeyBoardShow(int keyboardHeight) {

            }

            @Override
            public void onSoftKeyBoardHide() {
                if (StringUtils.isEmpty((sfccountEdit.getText().toString())) || sfccountEdit.getText().toString().equals("0")) {
                    sfccountEdit.setText(1 + "");
                    sfccountEdit.setSelection(sfccountEdit.getText().length());
                }
                setCountMoney();
            }
        });
    }

    //返回按钮
    @OnClick(R.id.titlebar_back_ll)
    public void finishActivity() {
        if (beansIsNull) {
            finish();
            return;
        }
        createDialog(Configs.BACK_DIALOG);
//        finish();
    }

    //重写返回键
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (beansIsNull) {
            finish();
            return;
        }
        createDialog(Configs.BACK_DIALOG);
    }

    @OnClick(R.id.sfc_zdsave_tv)//保存注单
    public void saveZhudan() {
        if (LoginManager.getInstance().login(this)) {
            if (StringUtils.isEmpty(sfccountEdit.getText().toString())){
                UIUtils.showToastSafe("倍数不能为空");
                return;
            }
            multiple = Integer.parseInt(sfccountEdit.getText().toString());
            if ((2 * count * multiple) > 20000) {
                UIUtils.showToastSafe("投注金额不能超过20000元");
                return;
            }
            HttpParams httpParams = new HttpParams();
            String Client = PrefUtils.getString(this, "Client", "");
            String signKey = "shgo12al";
            UserInfoBean infoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
            String secret = infoBean.getSecret();

            totalMoney = 2 * count * multiple + "";
            stakeCode = inintStakeCode(sfcDingdanBeans);
            if (sfcDingdanBeans.get(0).getDanOrfushi().equals("f")) {//复式
                stakeCode = "f-" + count + "-" + stakeCode;
            } else if (sfcDingdanBeans.get(0).getDanOrfushi().equals("d")) {
                stakeCode = "d-" + count + "-" + stakeCode;
            } else {
                stakeCode = "dt-" + count + "-" + stakeCode;
            }
            String sign = StringUtils.getMD5(Client + secret + signKey + lotId + issueNo + multiple + totalMoney + stakeCode);
            Logger.e(Client + secret + signKey + "sf14" + issueNo + "1" + count + stakeCode + "----stakeCode");
            Logger.e(stakeCode + "----stakeCode");
            httpParams.put("lotId", lotId);
            httpParams.put("issueNo", issueNo);
            httpParams.put("multiple", multiple);
            httpParams.put("totalMoney", totalMoney);
            httpParams.put("stakeCode", stakeCode);
            httpParams.put("sign", sign);
            httpParams.put("client", Client);


            if (LoginManager.getInstance().login(SfcDingdanActivity.this)) {//判断是否登陆,未登录弹出登陆界面
                OkGo.get(UrlManager.addStake()).connTimeOut(5000).params(httpParams).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                        if (null != vcodeBean) {
                            if (vcodeBean.getRet() == 100) {
                                UIUtils.showToastSafe("保存成功!");
                                Bundle bundle = new Bundle();
                                SfcDingdanActivity.this.setResult(Configs.SFC14SAVE_RESULTCODE, SfcDingdanActivity.this.getIntent().putExtras(bundle));
                                finish();
                            } else {
                                UIUtils.showToastSafe(vcodeBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mLoadingDialog = new LoadingDialog(SfcDingdanActivity.this, "正在保存");
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

    private String inintStakeCode(List<SfcDingdanBean> sfcDingdanBeans) {
        String stakeCodes = "";
        if (ActivityType == Configs.ACTIVITYTYPE_R9) {
            for (int i = 0; i < sfcDingdanBeans.get(0).getMatchListBeans().size(); i++) {
                if (!sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(0) && !sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(1) && !sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                    stakeCodes += "_";
                } else {
                    if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).isChecked()) {
                        stakeCodes += "d";
                    }
                    if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(0)) {
                        stakeCodes += "3";
                    }
                    if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(1)) {

                        stakeCodes += "1";
                    }
                    if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                        stakeCodes += "0";
                    }
                }
                if (i < sfcDingdanBeans.get(0).getMatchListBeans().size() - 1) {
                    stakeCodes += ",";
                }
            }
        } else if (ActivityType == Configs.ACTIVITYTYPE_SF14) {
            for (int i = 0; i < sfcDingdanBeans.get(0).getMatchListBeans().size(); i++) {
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(0)) {
                    stakeCodes += "3";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(1)) {
                    stakeCodes += "1";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                    stakeCodes += "0";
                }
                if (i < sfcDingdanBeans.get(0).getMatchListBeans().size() - 1) {
                    stakeCodes += ",";
                }
            }

        } else if (ActivityType == Configs.ACTIVITYTYPE_JQC) {
            for (int i = 0; i < sfcDingdanBeans.get(0).getMatchListBeans().size(); i++) {
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(0)) {
                    stakeCodes += "0";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(1)) {
                    stakeCodes += "1";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                    stakeCodes += "2";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(3)) {
                    stakeCodes += "3";
                }
                stakeCodes += ",";
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(4)) {
                    stakeCodes += "0";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(5)) {
                    stakeCodes += "1";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(6)) {
                    stakeCodes += "2";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(7)) {
                    stakeCodes += "3";
                }
                if (i < sfcDingdanBeans.get(0).getMatchListBeans().size() - 1) {
                    stakeCodes += ",";
                }
            }
        } else {
            for (int i = 0; i < sfcDingdanBeans.get(0).getMatchListBeans().size(); i++) {
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(0)) {
                    stakeCodes += "3";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(1)) {
                    stakeCodes += "1";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                    stakeCodes += "0";
                }
                stakeCodes += ",";
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(3)) {
                    stakeCodes += "3";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(4)) {
                    stakeCodes += "1";
                }
                if (sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().get(5)) {
                    stakeCodes += "0";
                }
                if (i < sfcDingdanBeans.get(0).getMatchListBeans().size() - 1) {
                    stakeCodes += ",";
                }
            }

        }
        return stakeCodes;
    }

    @OnClick(R.id.sfcorder_delete_rl)//清空列表
    public void deleleData() {
        if (null == sfcDingdanBeans || sfcDingdanBeans.size() == 0) {
            return;
        }
        createDialog(Configs.DELETE_DIALOG);
        setCountMoney();
    }

    //加倍
    @OnClick(R.id.addcount_im)
    void add() {
        multiple = Integer.parseInt(sfccountEdit.getText().toString());
        multiple++;
        if (multiple >= 99) {
            multiple = 99;
            UIUtils.showToastSafe("倍投不能超过99倍");
        }
        sfccountEdit.setText(multiple + "");
        sfccountEdit.setSelection((multiple + "").length());//将光标追踪到内容的最后
        setCountMoney();
    }

    //减倍
    @OnClick(R.id.lotto_dingdan_minusSign)
    void minus() {
        multiple = Integer.parseInt(sfccountEdit.getText().toString());
        if (multiple > 1) {
            multiple--;
        }
        sfccountEdit.setText(multiple + "");
        sfccountEdit.setSelection((multiple + "").length());//将光标追踪到内容的最后
        setCountMoney();
    }

    //更新下方注数及金额
    private void setCountMoney() {
        multiple = Integer.parseInt(sfccountEdit.getText().toString());
        sfcMoneyTv.setText(count + "注 " + 2 * count * multiple + "元");
        TextUtils.setStrColor(sfcMoneyTv, count + "注 " + 2 * count * multiple + "元", 2 * count * multiple + "", this.getResources().getColor(R.color.select_red));
    }

    private void clearData() {
        for (int i = 0; i < sfcDingdanBeans.get(0).getMatchListBeans().size(); i++) {
            if (ActivityType == Configs.ACTIVITYTYPE_JQC) {
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(0, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(1, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(2, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(3, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(4, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(5, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(6, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(7, false);
            }  else if (ActivityType == Configs.ACTIVITYTYPE_BQC) {
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(0, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(1, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(2, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(3, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(4, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(5, false);
            }else {
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(0, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(1, false);
                sfcDingdanBeans.get(0).getMatchListBeans().get(i).getCheckedHashMap().put(2, false);
            }
        }
        beansIsNull = true;
        mSfcDingdanAdapter.notifyDataSetChanged();
        sfcDingdanLsv.setVisibility(View.GONE);
    }

    @OnClick(R.id.edit_dingdan_ll)//编辑注单
    public void editZhudan() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Configs.SFCDINGDAN_RESULTKEY, (Serializable) sfcDingdanBeans.get(0).getMatchListBeans());
        this.setResult(Configs.SFC14_RESULTCODE, this.getIntent().putExtras(bundle));
        finish();
    }

    @Override
    protected void init() {
        Intent mIntent = getIntent();
        sfcDingdanBeans.clear();
        sfcDingdanBeans.add((SfcDingdanBean) mIntent.getExtras().get(Configs.SFCDINGDAN_KEY));
        ActivityType = (int) mIntent.getExtras().get(Configs.SFCDINGDAN_INT_KEY);
        Logger.e(ActivityType + "=====" + "ActivityType");
        lotId = sfcDingdanBeans.get(0).getLotId();
        issueNo = sfcDingdanBeans.get(0).getIssueNo();
        count = sfcDingdanBeans.get(0).getCount();
    }

    //清除号码 和返回时弹出的dialog
    public void createDialog(final int type) {
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dingdan_football_backdialog, null);
        final Dialog mDialog = new AlertDialog.Builder(this).create();
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
    @Override
    protected String getBaidutitle() {
        return "胜负彩注单保存";
    }
}
