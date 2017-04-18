package shlottery.gov.cn.lotterycenter.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.PushBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-16-0016 16:14
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SendSettingActivity extends BaseActivity {
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
    @BindView(R.id.send_setting_tv)
    TextView sendSettingTv;
    @BindView(R.id.send_setting_rl)
    RelativeLayout sendSettingRl;
    @BindView(R.id.sh115_im)
    ImageView sh115Im;
    @BindView(R.id.sh115_tv)
    TextView sh115Tv;
    @BindView(R.id.sh115_sendon_im)
    ImageView sh115SendonIm;
    @BindView(R.id.sh115_sendoff_im)
    ImageView sh115SendoffIm;
    @BindView(R.id.sh115_rl)
    RelativeLayout sh115Rl;
    @BindView(R.id.dlt_im)
    ImageView dltIm;
    @BindView(R.id.dlt_tv)
    TextView dltTv;
    @BindView(R.id.dlt_sendon_im)
    ImageView dltSendonIm;
    @BindView(R.id.dlt_sendoff_im)
    ImageView dltSendoffIm;
    @BindView(R.id.dlt_rl)
    RelativeLayout dltRl;
    @BindView(R.id.pl3_im)
    ImageView pl3Im;
    @BindView(R.id.pl3_tv)
    TextView pl3Tv;
    @BindView(R.id.pl3_sendon_im)
    ImageView pl3SendonIm;
    @BindView(R.id.pl3_sendoff_im)
    ImageView pl3SendoffIm;
    @BindView(R.id.pl3_rl)
    RelativeLayout pl3Rl;
    @BindView(R.id.pl5_im)
    ImageView pl5Im;
    @BindView(R.id.pl5_tv)
    TextView pl5Tv;
    @BindView(R.id.pl5_sendon_im)
    ImageView pl5SendonIm;
    @BindView(R.id.pl5_sendoff_im)
    ImageView pl5SendoffIm;
    @BindView(R.id.pl5_rl)
    RelativeLayout pl5Rl;
    @BindView(R.id.qxc_im)
    ImageView qxcIm;
    @BindView(R.id.qxc_tv)
    TextView qxcTv;
    @BindView(R.id.qxc_sendon_im)
    ImageView qxcSendonIm;
    @BindView(R.id.qxc_sendoff_im)
    ImageView qxcSendoffIm;
    @BindView(R.id.qxc_rl)
    RelativeLayout qxcRl;
    @BindView(R.id.sfc_im)
    ImageView sfcIm;
    @BindView(R.id.sfc_tv)
    TextView sfcTv;
    @BindView(R.id.sfc_sendon_im)
    ImageView sfcSendonIm;
    @BindView(R.id.sfc_sendoff_im)
    ImageView sfcSendoffIm;
    @BindView(R.id.sfc_rl)
    RelativeLayout sfcRl;
    @BindView(R.id.jqc_im)
    ImageView jqcIm;
    @BindView(R.id.jqc_tv)
    TextView jqcTv;
    @BindView(R.id.jqc_sendon_im)
    ImageView jqcSendonIm;
    @BindView(R.id.jqc_sendoff_im)
    ImageView jqcSendoffIm;
    @BindView(R.id.jqc_rl)
    RelativeLayout jqcRl;
    @BindView(R.id.bqc_im)
    ImageView bqcIm;
    @BindView(R.id.bqc_tv)
    TextView bqcTv;
    @BindView(R.id.bqc_sendon_im)
    ImageView bqcSendonIm;
    @BindView(R.id.bqc_sendoff_im)
    ImageView bqcSendoffIm;
    @BindView(R.id.bqc_rl)
    RelativeLayout bqcRl;
    @BindView(R.id.system_messageon_im)
    ImageView systemMessageonIm;
    @BindView(R.id.system_messageoff_im)
    ImageView systemMessageoffIm;
    @BindView(R.id.system_message_rl)
    RelativeLayout systemMessageRl;
    @BindView(R.id.zhongjiang_messageon_im)
    ImageView zhongjiangMessageonIm;
    @BindView(R.id.zhongjiang_messageoff_im)
    ImageView zhongjiangMessageoffIm;
    @BindView(R.id.zhongjiang_message_rl)
    RelativeLayout zhongjiangMessageRl;
    @BindView(R.id.zhuanjiakancaion_im)
    ImageView zhuanjiakancaionIm;
    @BindView(R.id.zhuanjiakancaioff_im)
    ImageView zhuanjiakancaioffIm;
    @BindView(R.id.zhuanjiakancai_rl)
    RelativeLayout zhuanjiakancaiRl;

    private Map<Integer, Boolean> selectedMap;
    private MyOnClickListener myOnclickListener;
    private LoadingDialog loadingDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sendsetting);
        ButterKnife.bind(this);
        titlebarTv.setText("推送设置");
        initOnOff();//初始化开关按钮,从shareperfence中获取
        myOnclickListener = new MyOnClickListener();
        sh115Rl.setOnClickListener(myOnclickListener);
        dltRl.setOnClickListener(myOnclickListener);
        pl3Rl.setOnClickListener(myOnclickListener);
        pl5Rl.setOnClickListener(myOnclickListener);
        qxcRl.setOnClickListener(myOnclickListener);
        sfcRl.setOnClickListener(myOnclickListener);
        jqcRl.setOnClickListener(myOnclickListener);
        bqcRl.setOnClickListener(myOnclickListener);
        systemMessageRl.setOnClickListener(myOnclickListener);
        zhongjiangMessageRl.setOnClickListener(myOnclickListener);
        zhuanjiakancaiRl.setOnClickListener(myOnclickListener);
        getPush();
    }

    //获取网络上的设置信息
    private void getPush() {
        OkGo.get(UrlManager.getPushItem(this)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                LogUtils.i("SendSettingActivity getJson:"+s);
                PushBean pushBean = gson.fromJson(s, PushBean.class);
                if (null != pushBean && pushBean.getRet() == 100) {
                    for (int i = 0; i < pushBean.getData().getList().get(0).getItems().size(); i++) {
                        switch (pushBean.getData().getList().get(0).getItems().get(i).getId()) {
                            case "dlt":
                                selectedMap.put(Configs.SENDSETTING_KEY_DLT, pushBean.getData().getList().get(0).getItems().get(i).isPush());
                                break;
                            case "pl3":
                                selectedMap.put(Configs.SENDSETTING_KEY_PL3, pushBean.getData().getList().get(0).getItems().get(i).isPush());
                                break;
                            case "pl5":
                                selectedMap.put(Configs.SENDSETTING_KEY_PL5, pushBean.getData().getList().get(0).getItems().get(i).isPush());
                                break;
                            case "qxc":
                                selectedMap.put(Configs.SENDSETTING_KEY_QXC, pushBean.getData().getList().get(0).getItems().get(i).isPush());
                                break;
                            case "sh115":
                                selectedMap.put(Configs.SENDSETTING_KEY_SH115, pushBean.getData().getList().get(0).getItems().get(i).isPush());
                                break;
                            case "sfc":
                                selectedMap.put(Configs.SENDSETTING_KEY_SFC, pushBean.getData().getList().get(0).getItems().get(i).isPush());
                                break;
                            case "bqc":
                                selectedMap.put(Configs.SENDSETTING_KEY_BQC, pushBean.getData().getList().get(0).getItems().get(i).isPush());
                                break;
                            case "jqc":
                                selectedMap.put(Configs.SENDSETTING_KEY_JQC, pushBean.getData().getList().get(0).getItems().get(i).isPush());
                                break;
                            default:
                                break;
                        }
                    }
                    for (int i = 0; i < pushBean.getData().getList().size(); i++) {
                        switch (pushBean.getData().getList().get(i).getId()) {
                            case "sysmsg":
                                selectedMap.put(Configs.SENDSETTING_KEY_SYSTEM, pushBean.getData().getList().get(i).isPush());
                                break;
                            case "bonus":
                                selectedMap.put(Configs.SENDSETTING_KEY_ZHONGJ, pushBean.getData().getList().get(i).isPush());
                                break;
                            case "expert":
                                selectedMap.put(Configs.SENDSETTING_KEY_ZHUANJIA, pushBean.getData().getList().get(i).isPush());
                                break;
                            default:
                                break;

                        }
                    }
                    SharedPreferencesUtils.putObject(Configs.SENDSETTING_KEY, selectedMap);
                    initOnOff();
                }
            }
        });
    }

    //保存设置信息到网络
    private void savePush() {
        String push = "";
        for (int i = 0; i < 12; i++) {
            if (selectedMap.get(i)) {
                switch (i) {
                    case Configs.SENDSETTING_KEY_DLT:
                        push += "dlt";
                        break;
                    case Configs.SENDSETTING_KEY_PL3:
                        push += "pl3";
                        break;
                    case Configs.SENDSETTING_KEY_PL5:
                        push += "pl5";
                        break;
                    case Configs.SENDSETTING_KEY_QXC:
                        push += "qxc";
                        break;
                    case Configs.SENDSETTING_KEY_SH115:
                        push += "sh115";
                        break;
                    case Configs.SENDSETTING_KEY_SFC:
                        push += "sfc";
                        break;
                    case Configs.SENDSETTING_KEY_BQC:
                        push += "bqc";
                        break;
                    case Configs.SENDSETTING_KEY_JQC:
                        push += "jqc";
                        break;
                    case Configs.SENDSETTING_KEY_SYSTEM:
                        push += "sysmsg";
                        break;
                    case Configs.SENDSETTING_KEY_ZHONGJ:
                        push += "bonus";
                        break;
                    case Configs.SENDSETTING_KEY_ZHUANJIA:
                        push += "expert";
                        break;
                    default:
                        break;

                }
                if (i < 11) {
                    push += ",";
                }
            }
        }

        OkGo.get(UrlManager.savePushItem(this, push)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);

                if (null != vcodeBean) {
                    if (vcodeBean.getRet() == 100) {
                        SharedPreferencesUtils.putObject(Configs.SENDSETTING_KEY, selectedMap);
                        UIUtils.showToastSafe("设置保存成功");
                    } else {
                        UIUtils.showToastSafe("设置保存失败");
                    }

                    initOnOff();
                }
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
                finish();
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                loadingDialog = new LoadingDialog(SendSettingActivity.this, "正在保存设置");
                loadingDialog.show();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络设置");
                UIUtils.showToastSafe("设置保存失败");
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
                finish();
            }
        });
    }

    /**
     * 初始化开关的状态
     */
    private void initOnOff() {
        setOnOff(Configs.SENDSETTING_KEY_SH115, sh115SendonIm, sh115SendoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_DLT, dltSendonIm, dltSendoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_PL3, pl3SendonIm, pl3SendoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_PL5, pl5SendonIm, pl5SendoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_QXC, qxcSendonIm, qxcSendoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_SFC, sfcSendonIm, sfcSendoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_JQC, jqcSendonIm, jqcSendoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_BQC, bqcSendonIm, bqcSendoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_SYSTEM, systemMessageonIm, systemMessageoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_ZHONGJ, zhongjiangMessageonIm, zhongjiangMessageoffIm, selectedMap);
        setOnOff(Configs.SENDSETTING_KEY_ZHUANJIA, zhuanjiakancaionIm, zhuanjiakancaioffIm, selectedMap);
    }

    /**
     * 设置开关的状态
     */
    private void setOnOff(int positon, ImageView imageView1, ImageView imageView2, Map<Integer, Boolean> map) {

        if (map.get(positon)) {
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.GONE);
        } else {
            imageView1.setVisibility(View.GONE);
            imageView2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void init() {
        selectedMap = (HashMap<Integer, Boolean>) SharedPreferencesUtils.getObject(Configs.SENDSETTING_KEY);
        if (null == selectedMap) {
            selectedMap = new HashMap<>();
            for (int i = 0; i <= 11; i++) {//因为上海十一选5对应的键为51,循环初始化开关按钮
//                selectedMap.put(Configs.SENDSETTING_KEY_SH115,false);
                selectedMap.put(i, true);//默认打开
            }
        }
    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void back() {
        savePush();
    }

    @Override
    public void onBackPressed() {
        savePush();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sh115_rl:
                    changeState(Configs.SENDSETTING_KEY_SH115, selectedMap);
                    break;
                case R.id.dlt_rl:
                    changeState(Configs.SENDSETTING_KEY_DLT, selectedMap);

                    break;
                case R.id.pl3_rl:
                    changeState(Configs.SENDSETTING_KEY_PL3, selectedMap);

                    break;
                case R.id.pl5_rl:
                    changeState(Configs.SENDSETTING_KEY_PL5, selectedMap);

                    break;
                case R.id.qxc_rl:
                    changeState(Configs.SENDSETTING_KEY_QXC, selectedMap);

                    break;
                case R.id.sfc_rl:
                    changeState(Configs.SENDSETTING_KEY_SFC, selectedMap);

                    break;
                case R.id.jqc_rl:
                    changeState(Configs.SENDSETTING_KEY_JQC, selectedMap);
                    break;
                case R.id.bqc_rl:
                    changeState(Configs.SENDSETTING_KEY_BQC, selectedMap);
                    break;
                case R.id.system_message_rl:
                    changeState(Configs.SENDSETTING_KEY_SYSTEM, selectedMap);
                    break;
                case R.id.zhongjiang_message_rl:
                    changeState(Configs.SENDSETTING_KEY_ZHONGJ, selectedMap);
                    break;
                case R.id.zhuanjiakancai_rl:
                    changeState(Configs.SENDSETTING_KEY_ZHUANJIA, selectedMap);
                    break;
                default:
                    break;
            }
            initOnOff();
        }
    }

    //点击更改开关状态
    private void changeState(int postion, Map<Integer, Boolean> selectedMap) {
        if (selectedMap.get(postion)) {
            selectedMap.put(postion, false);
        } else {
            selectedMap.put(postion, true);
        }
    }
    @Override
    protected String getBaidutitle() {
        return "推送设置";
    }
}
