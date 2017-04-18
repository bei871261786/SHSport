package shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.BounsMoneyBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusNumberDetailBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusSoccerDetailBean;
import shlottery.gov.cn.lotterycenter.bean.LottoSelectData;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter.DiaglogJiangJinAdapter;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter.DiaglogJiangJinZhuijiaAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.KeyBordStateUtil;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-01-0001 13:10
 * 描    述：奖金计算的basefragment
 * 修订历史：
 * ================================================
 */

public abstract class CalculateBaseFragment extends BaseFragment {
    private String lotId;
    private String playType;
    private String stakeCode;
    private String multiple;
    private String stakeAdd = "";
    public List<NumLotOrderBean> mNumLotOrderBeans;
    private LoadingDialog mLoadingDialog;
    public ImageView calculatePlusIm;
    public EditText calculateMultipleEdit;
    public ImageView calculateMinusIm;
    public TextView calculateCountTv;
    public TextView calculateMoneyTv;
    public TextView calculateDeleteallTv;
    public TextView calculateSumBt;
    public baseOnclickListener baseOnclickListener;

    public IssueBonusBean issueBonusBean;
    public IssueBonusNumberDetailBean issueBonusNumberDetailBean;//开奖数据详情
    public IssueBonusSoccerDetailBean issueBonusSoccerDetailBean;//开奖数据详情
    public String issueNo;

    private KeyBordStateUtil keyBordStateUtil;
    private int count;//注数

    @Override
    protected View createLoadedView() {
        View view = initView();
        calculateMultipleEdit = (EditText) view.findViewById(R.id.calculate_multiple_edit);
        calculatePlusIm = (ImageView) view.findViewById(R.id.calculate_plus_im);
        calculateMinusIm = (ImageView) view.findViewById(R.id.calculate_minus_im);
        calculateCountTv = (TextView) view.findViewById(R.id.calculate_count_tv);
        calculateMoneyTv = (TextView) view.findViewById(R.id.calculate_money_tv);
        calculateDeleteallTv = (TextView) view.findViewById(R.id.calculate_deleteall_tv);
        calculateSumBt = (TextView) view.findViewById(R.id.calculate_sum_bt);
        calculateDeleteallTv.setOnClickListener(baseOnclickListener);
        calculateSumBt.setOnClickListener(baseOnclickListener);
        calculatePlusIm.setOnClickListener(baseOnclickListener);
        calculateMinusIm.setOnClickListener(baseOnclickListener);
        calculateSumBt.setEnabled(false);

        keyBordStateUtil = new KeyBordStateUtil(getActivity());
        calculateMultipleEdit.setOnKeyListener(baseOnclickListener);
        keyBordStateUtil.addOnKeyBordStateListener(new KeyBordStateUtil.onKeyBordStateListener() {
            @Override
            public void onSoftKeyBoardShow(int keyboardHeight) {
            }

            @Override
            public void onSoftKeyBoardHide() {
                if (StringUtils.isEmpty((calculateMultipleEdit.getText().toString())) || calculateMultipleEdit.getText().toString().equals("0")) {
                    calculateMultipleEdit.setText(1 + "");
                    calculateMultipleEdit.setSelection(calculateMultipleEdit.getText().length());
                }
                setCountMoney();
            }
        });

        return view;
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    public abstract View initView();//初始化view

    public abstract void initLotteryData();//查询前初始化数据

    public abstract void clearData();//清除数据


    public class baseOnclickListener implements View.OnClickListener, View.OnKeyListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.calculate_plus_im://增加倍数
                    int i = Integer.parseInt(calculateMultipleEdit.getText().toString());
                    i++;
                    if (i > 99) {
                        i = 99;
                        UIUtils.showToastSafe("倍投不能超过99倍");
                    }
                    calculateMultipleEdit.setText(i + "");
                    calculateMultipleEdit.setSelection((i + "").length());//将光标追踪到内容的最后
                    setCountMoney();
                    break;
                case R.id.calculate_minus_im://减倍
                    int j = Integer.parseInt(calculateMultipleEdit.getText().toString());
                    if (j == 1) {
                        UIUtils.showToastSafe("倍投不能小于1倍");
                        return;
                    }
                    j--;
                    calculateMultipleEdit.setText(j + "");
                    calculateMultipleEdit.setSelection((j + "").length());//将光标追踪到内容的最后
                    setCountMoney();
                    break;
                case R.id.calculate_deleteall_tv://清除所有
                    clearData();
                    break;
                case R.id.calculate_sum_bt://查询
                    query();
                    break;
                default:
                    break;
            }
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if (StringUtils.isEmpty((calculateMultipleEdit.getText().toString())) || calculateMultipleEdit.getText().toString().equals("0")) {
                    calculateMultipleEdit.setText(1 + "");
                    calculateMultipleEdit.setSelection(calculateMultipleEdit.getText().length());
                }
                int i = Integer.parseInt(calculateMultipleEdit.getText().toString());
                if (i > 99) {
                    calculateMultipleEdit.setText(99 + "");
                    calculateMultipleEdit.setSelection((99 + "").length());//将光标追踪到内容的最后
                }
                //收起键盘
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        }
    }

    //查询奖金
    public void query() {
        initLotteryData();
        if (StringUtils.isEmpty(mNumLotOrderBeans.get(0).getMultiple())) {//防止在键盘还没收起时点击了查询,导致
            UIUtils.showToastSafe("倍数不能为空");
            return;
        }
        if (mNumLotOrderBeans.get(0).getCount() * Integer.parseInt(mNumLotOrderBeans.get(0).getMultiple()) > 10000) {
            if (mNumLotOrderBeans.get(0).getLotId().equals("dlt")) {
                if (mNumLotOrderBeans.get(0).getStakeAdd().equals("1")) {
                    UIUtils.showToastSafe("追加单票不能超过三万元");
                } else {
                    UIUtils.showToastSafe("单票不能超过两万元");
                }
            } else {
                UIUtils.showToastSafe("单票不能超过两万元");
            }
            return;
        }
        if (mNumLotOrderBeans.get(0).getLotId().equals("sf9")) {
            if (mNumLotOrderBeans.get(0).getChuanshu() > 9) {
                UIUtils.showToastSafe("任选9最多只能选9场");
                return;
            }
        }
        stakeAdd = mNumLotOrderBeans.get(0).getStakeAdd();
        lotId = mNumLotOrderBeans.get(0).getLotId();
        issueNo = mNumLotOrderBeans.get(0).getIssueNo();
        playType = mNumLotOrderBeans.get(0).getGuoguanType();
        multiple = mNumLotOrderBeans.get(0).getMultiple();
        HttpParams httpParams = new HttpParams();
        String Client = PrefUtils.getString(getActivity(), "Client", "");
        String signKey = "shgo12al";
        stakeCode = inintStakeCode(mNumLotOrderBeans);
        String sign = StringUtils.getMD5(Client + signKey + lotId + issueNo + playType + stakeCode + multiple + stakeAdd);
        Logger.e(stakeCode + "----stakeCode");
        httpParams.put("lotId", lotId);
        httpParams.put("issueNo", issueNo);
        httpParams.put("multiple", multiple);
        httpParams.put("stakeCode", stakeCode);
        httpParams.put("playType", playType);
        httpParams.put("stakeAdd", stakeAdd);
        httpParams.put("sign", sign);
        httpParams.put("client", Client);

        OkGo.get(UrlManager.calcBonus()).connTimeOut(5000).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                BounsMoneyBean bounsMoneyBean = gson.fromJson(s, BounsMoneyBean.class);
                if (null != bounsMoneyBean) {
                    if (bounsMoneyBean.getRet() == 100) {
                        showBounsDialog(bounsMoneyBean);
                    } else {
                        UIUtils.showToastSafe(bounsMoneyBean.getMsg());
                    }
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                mLoadingDialog = new LoadingDialog(getActivity(), "正在查询...");
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

    private String inintStakeCode(List<NumLotOrderBean> mNumLotOrderBeans) {

        String s = "";
        for (int m = 0; m < mNumLotOrderBeans.size(); m++) {
            String oneStr = "";//每一行
            StringBuffer buf = new StringBuffer();
            StringBuffer buf2 = new StringBuffer();
            StringBuffer buf3 = new StringBuffer();

            if (lotId.equals("pl5")) {//排列5
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
            } else if (lotId.equals("qxc")) {//七星彩
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
            } else if (lotId.equals("pl3")) {//排列三
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
            } else if (lotId.equals("dlt")) {
                String stakeCode = "";
                List<LottoSelectData> beanListRed = mNumLotOrderBeans.get(0).getOrderBean().getSelectdata().get("redData");
                List<LottoSelectData> beanListBlue = mNumLotOrderBeans.get(0).getOrderBean().getSelectdata().get("blueData");

                for (int i = 0; i < beanListRed.size(); i++) {
                    if (beanListRed.get(i).isSelected()) {
                        buf.append(beanListRed.get(i).getMsg());
                        if (i < beanListRed.size() - 1) {
                            buf.append(",");
                        }
                    }
                }
                buf.append("#");
                for (int i = 0; i < beanListBlue.size(); i++) {
                    if (beanListBlue.get(i).isSelected()) {
                        buf.append(beanListRed.get(i).getMsg());
                        if (i < beanListRed.size() - 1) {
                            buf.append(",");
                        }
                    }
                }
                oneStr += buf.toString();
            } else if (lotId.equals("sf9")) {
                for (int i = 0; i < mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().size(); i++) {
                    if (!mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(0) && !mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(1) && !mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                        s += "_";
                    } else {
                        if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).isChecked()) {
                            s += "d";
                        }
                        if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(0)) {
                            s += "3";
                        }
                        if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(1)) {

                            s += "1";
                        }
                        if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                            s += "0";
                        }
                    }
                    if (i < mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().size() - 1) {
                        s += ",";
                    }
                }
            } else if (lotId.equals("sf14")) {
                for (int i = 0; i < mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().size(); i++) {
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(0)) {
                        s += "3";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(1)) {
                        s += "1";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                        s += "0";
                    }
                    if (i < mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().size() - 1) {
                        s += ",";
                    }
                }

            } else if (lotId.equals("jqc")) {
                for (int i = 0; i < mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().size(); i++) {
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(0)) {
                        s += "0";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(1)) {
                        s += "1";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                        s += "2";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(3)) {
                        s += "3";
                    }
                    s += ",";
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(4)) {
                        s += "0";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(5)) {
                        s += "1";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(6)) {
                        s += "2";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(7)) {
                        s += "3";
                    }
                    if (i < mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().size() - 1) {
                        s += ",";
                    }
                }
            } else if (lotId.equals("bqc")) {
                for (int i = 0; i < mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().size(); i++) {
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(0)) {
                        s += "3";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(1)) {
                        s += "1";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(2)) {
                        s += "0";
                    }
                    s += ",";
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(3)) {
                        s += "3";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(4)) {
                        s += "1";
                    }
                    if (mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().get(i).getCheckedHashMap().get(5)) {
                        s += "0";
                    }
                    if (i < mNumLotOrderBeans.get(0).getSfcDingdanBean().getMatchListBeans().size() - 1) {
                        s += ",";
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

    private void showBounsDialog(BounsMoneyBean bounsMoneyBean) {
        View mDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_zhongjiang, null);
        final Dialog mDialog = new android.app.AlertDialog.Builder(getActivity()).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(true);
        ImageView cancle = (ImageView) mDialogView.findViewById(R.id.cancel_zj_im);
        TextView jiangjin = (TextView) mDialogView.findViewById(R.id.total_jiangjin_tv);
        ListView listView = (ListView) mDialogView.findViewById(R.id.jiangjin_dialog_lisv);
        if (stakeAdd != null && stakeAdd.equals("1")) {
            DiaglogJiangJinZhuijiaAdapter diaglogJiangJinZhuijiaAdapter = new DiaglogJiangJinZhuijiaAdapter(getActivity(), bounsMoneyBean);
            listView.setAdapter(diaglogJiangJinZhuijiaAdapter);
        } else {
            DiaglogJiangJinAdapter diaglogJiangJinAdapter = new DiaglogJiangJinAdapter(getActivity(), bounsMoneyBean);
            listView.setAdapter(diaglogJiangJinAdapter);
        }
        jiangjin.setText(bounsMoneyBean.getData().getBonusMoney() + "元");

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

    }


    //计算奖金及串数并更新
    private void setCountMoney() {
        initLotteryData();
        stakeAdd = mNumLotOrderBeans.get(0).getStakeAdd();
        count = mNumLotOrderBeans.get(0).getCount();
        int beishu = Integer.parseInt(calculateMultipleEdit.getText().toString());
        if (StringUtils.isEmpty(stakeAdd)) {
            calculateMoneyTv.setText(2 * count * beishu + "元");
            TextUtils.setStrColor(calculateMoneyTv, count * beishu * 2 + "元", count * beishu * 2 + "", BaseApplication.getApplication().getResources().getColor(R.color.select_red));

        } else {
            calculateMoneyTv.setText(3 * count * beishu + "元");
            TextUtils.setStrColor(calculateMoneyTv, count * beishu * 3 + "元", count * beishu * 3 + "", BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        }
        calculateCountTv.setText("共" + count + "注");

        TextUtils.setStrColor(calculateCountTv, count + "注", count + "", BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        Logger.e(count + ":注数" + beishu + "倍数");
    }
}
