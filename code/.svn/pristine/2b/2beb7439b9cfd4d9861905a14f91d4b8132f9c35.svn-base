package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanItemBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.SchemePayBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.VoucherBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.RequestAsyncTask;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.RequestCallback;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.UpdataCallback;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
import shlottery.gov.cn.lotterycenter.utils.DingDanStringUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

public class PaymentActivity extends BaseActivity implements UpdataCallback, RequestCallback {
    private DingdanBean mDingdan;
    private Intent intent;
    private TextView mUserMoney;
    private TextView mZhiFuChongZhi;
    private TextView mDingdanMoney;
    private TextView mRealMoney;
    private Button mPayButton;
    private GridView mVoucherGridview;
    private ImageView mTopBack;
    private LinearLayout mVoucherLayout;
    private RelativeLayout mRealPayLayout;

    private String mJcTypeStr;//彩票玩法，比如混合过关,用于生成参数，对未付款过来没有影响
    private String mJcLotId;//竞彩类型，比如竞彩足球
    private String mPassword = "";//用户密码
    private VocherAdapter mAdapter;
    private int mVoucherMoney = 0;//彩金券免减金额
    private List<VoucherBean.DataBean.ListBean> mDatas = new ArrayList<>();//彩金券的数据
    private MyListener mListener;
    private CheckBox mVoucherCb;
    private String mVoucherId = "";//彩金券列表
    private String mPageNo = "";
    private String mStatus = "";
    private int mTotalMoney = 0;//总金额
    private String mSchemeIds;
    private String mPayType = "";
    private RequestAsyncTask mRequestAsynTask;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = (Bundle) msg.obj;
            mPassword = bundle.getString("password");
            execSave();
        }
    };


    @Override
    protected void initView() {
        setContentView(R.layout.activity_payment);
        mUserMoney = (TextView) findViewById(R.id.payment_userMoney);
        mDingdanMoney = (TextView) findViewById(R.id.payment_dingdan_money);
        mRealMoney = (TextView) findViewById(R.id.payment_real_payMoney);
        mPayButton = (Button) findViewById(R.id.payment_payButton);
        mVoucherGridview = (GridView) findViewById(R.id.payment_gridview);
        mVoucherCb = (CheckBox) findViewById(R.id.payment_checkbox);
        mTopBack = (ImageView) findViewById(R.id.topbutton_back);
        mVoucherLayout = (LinearLayout) findViewById(R.id.payment_voucherLin);
        mRealPayLayout = (RelativeLayout) findViewById(R.id.payment_realPay_Lin);
        mZhiFuChongZhi = (TextView) findViewById(R.id.zhifu_chongzhi);
        mListener = new MyListener();

        mDingdanMoney.setText(mTotalMoney + ".00元");
        mUserMoney.setText(PrefUtils.getString(this, Configure_JC.AVAILMONEY_KEY, "0") + "元");
        mRealMoney.setText(mTotalMoney + ".00元");
        mVoucherCb.setChecked(true);
        mVoucherCb.setOnCheckedChangeListener(mListener);
        mPayButton.setOnClickListener(mListener);
        mZhiFuChongZhi.setOnClickListener(mListener);
        mTopBack.setOnClickListener(mListener);
        mAdapter = new VocherAdapter(mDatas);
        mVoucherGridview.setOnItemClickListener(mListener);
        View emptyView = findViewById(R.id.payment_emptyView);
        mVoucherGridview.setEmptyView(emptyView);
        mVoucherGridview.setAdapter(mAdapter);
        if (null != mSchemeIds) {
            mVoucherLayout.setVisibility(View.GONE);
            mRealPayLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void init() {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        mDingdan = (DingdanBean) bundle.getSerializable("dingdans");

        if (null != mDingdan) {
            mPayType = "dingdan";
            mJcTypeStr = getResources().getStringArray(R.array.title_jincaizuqiu)[mDingdan.getmJcType()];
            mTotalMoney = mDingdan.getTotalMoney();
        } else {
            mSchemeIds = (String) bundle.getSerializable("schemeId");
            mTotalMoney = bundle.getInt("schemeMoney");
            mPayType = "scheme";
        }
        mJcLotId = BaseApplication.getCurrentLotId();
        loadVoucherData();
    }

    //执行支付
    private synchronized void execSave() {
        if (mRequestAsynTask == null) {
            String url = null;
            List<NameValuePair> params = null;
            mRequestAsynTask = new RequestAsyncTask(PaymentActivity.this);
            if (mPayType.equals("dingdan")) {
                url = HttpHelper.CeshiJCZQURL + HttpHelper.addStake;
//                params = getParams(mDingdan);
            } else if (mPayType.equals("scheme")) {
                new RequestAsyncTask(PaymentActivity.this).execute(url, params);
            }
            mRequestAsynTask.execute(url, params);
        }
    }

    //下载彩金券数据
    private void loadVoucherData() {

//        VoucherProtocol protocol = new VoucherProtocol();
//        protocol.setPageNo(mPageNo);
//        protocol.setPageNo(mStatus);
//        new UpdateAsyncTask(this, protocol).execute();
    }

    //更新实收金额
    private void updateRealMoney() {
        int realMoney = (mTotalMoney - mVoucherMoney);
        realMoney = realMoney <= 0 ? 0 : realMoney;
        mRealMoney.setText(realMoney + ".00元");
    }

    // 获得支付的post参数
    private LinkedHashMap<String, String> getParams(DingdanBean dingdanBean) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        String token = BaseApplication.getApplication().getmToken();
        String secret = BaseApplication.getmSecret();
        String signKey = BaseApplication.signKey;
        //int userId = BaseApplication.getmUserId();
        String lotId = "";
        String stakeLotid = "";
        String issueNo = "";//比赛日期
        int multiple = 0;//倍数
        int stackeAdd = 0;//是否追加
        int totalMoney = 0;//总金额
        String stakeCode = "";//投注内容
        ArrayList<String> chuanlianList = null;//串联集合
        String voucherId = "";
        ArrayList<DingdanItemBean> dingdanItems = null;
        if (dingdanBean != null && dingdanBean.getDingdanItems().size() > 0) {
            dingdanItems = dingdanBean.getDingdanItems();
            lotId = dingdanBean.getLotId();
            issueNo = dingdanBean.getIssueNo();
            multiple = dingdanBean.getMultiple();
            totalMoney = dingdanBean.getTotalMoney();
            stakeLotid = getStakeLotId(dingdanBean.getmJcType());
            chuanlianList = dingdanBean.getmChuanlianList();
        }
        LogUtils.i("payment 投注参数是：" + "issueNo:" + issueNo + "   multiple:" + multiple + "   stackeAdd:" + stackeAdd + "  totalMoney:" + totalMoney + "  payPwd:" + mPassword + "  voucherIds:" + voucherId + "  chuanlianList :" + chuanlianList);
        switch (lotId) {
            case "jczq":
                StringBuilder chuanlianSb = new StringBuilder();
                String chuanlianStr;//串联方式
                int totalCount = dingdanBean.getTotalCount();//总注数
                String detailCode = "";//详细号码

                for (int i = 0; i < chuanlianList.size(); i++) {
                    if (i != chuanlianList.size() - 1) {
                        chuanlianSb.append(chuanlianList.get(i) + ",");
                    } else {
                        chuanlianSb.append(chuanlianList.get(i));
                    }
                }
                chuanlianStr = chuanlianSb.toString();
                chuanlianStr = chuanlianStr.replace('串', 'x');
                for (int i = 0; i < dingdanItems.size(); i++) {
                    int matchNo = dingdanItems.get(i).getMatchNo();
                    String optionValue = dingdanItems.get(i).getOptionValue();
                    boolean isDan = dingdanItems.get(i).isDan();
                    String staktType = "";

                    if (isDan) {
                        detailCode += "d";
                    }
                    //二选一和混合过关的stakeCode与其他方式不一样
                    if ("混合过关".equals(mJcTypeStr)) {
                        String paramsLotid = dingdanItems.get(i).getLotId();
                        staktType = "_" + paramsLotid;
                    }
                    if (i != dingdanItems.size() - 1) {
                        detailCode += matchNo + staktType + "$" + DingDanStringUtils.getStakeNumber(optionValue) + "#";
                    } else {
                        detailCode += matchNo + staktType + "$" + DingDanStringUtils.getStakeNumber(optionValue);
                    }
                }
                stakeCode = chuanlianStr + "-" + totalCount + "-" + detailCode;
                LogUtils.i("payment 投注参数stakeCode:" + stakeCode);
                break;
        }
        params.put("lotId", stakeLotid);
        params.put("issueNo", issueNo);
        params.put("multiple", multiple + "");
        params.put("stakeAdd", stackeAdd + "");
        params.put("totalMoney", totalMoney + "");
        params.put("stakeCode", stakeCode);

        return params;
    }

    private String getStakeLotId(int jcType) {
        String stakeLotid = null;
        switch (jcType) {
            case Configure_JC.TAB_BF:
                stakeLotid = "jzbf";
                break;
            case Configure_JC.TAB_BQC:
                stakeLotid = "jzbqc";
                break;
            case Configure_JC.TAB_ZJQ:
                stakeLotid = "jzjqs";
                break;
            case Configure_JC.TAB_HHGG:
                stakeLotid = "jzhh";
                break;
            case Configure_JC.TAB_SPF:
                stakeLotid = "jzspf";
                break;
            case Configure_JC.TAB_RQSFP:
                stakeLotid = "jzzspf";
                break;
        }
        return stakeLotid;
    }

    //处理支付请求的结果
    @Override
    public void getRequestResultFromAsyntask(Object o) {
//        String json = (String) o;
//        if (null != json) {
//            int ret = 0;
//            String msg = null;
//            if (mPayType.equals("dingdan")) {
//                RequestBean resultData = parseRequest(json);
//                ret = resultData.getRet();
//                msg = resultData.getMsg();
//
//            } else if (mPayType.equals("scheme")) {
//                SchemePayBean resultData = parseSchemeRequest(json);
//                ret = resultData.getRet();
//                msg = resultData.getMsg();
//            }
//
//            if (100 == ret) {
//                UIUtils.showToastSafe("购买成功");
//                BaseApplication.setmUserPayPwd(mPassword);
//                setResult(Configure_JC.PAYRES_SUCC);
//                finish();
//            } else {
//                UIUtils.showToastSafe(msg);//提示错误信息
//            }
//        } else {
//            UIUtils.showToastSafe("购买失败");
//        }
    }

    //解析未付款请求结果的数据
    protected SchemePayBean parseSchemeRequest(String json) {
        Gson gson = new Gson();
        SchemePayBean resultData = gson.fromJson(json, SchemePayBean.class);
        return resultData;
    }


    @Override
    public void getResultFromAysntask(Object o) {
        List<VoucherBean.DataBean> updateData = (List<VoucherBean.DataBean>) o;
        if (null != updateData && null != updateData.get(0)) {
            mDatas.addAll(updateData.get(0).getList());
        }
        if (null != mAdapter) {
            mAdapter.notifyDataSetChanged();
        }
    }


    private class MyListener implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {
        @Override
        public void onClick(View v) {
            LogUtils.i("listener click");
            int id = v.getId();
            switch (id) {
                case R.id.payment_payButton:
//                    mPassword = BaseApplication.getmUserPayPwd();
//                    if (null == mPassword || mPassword.isEmpty()) {
//                        SoccerBaseDialogUtil dialogUtil = new SoccerBaseDialogUtil();
//                        dialogUtil.createPaymentDialog(PaymentActivity.this, handler);
//                    } else {
//                        LogUtils.i("password 支付密码:" + mPassword);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("password", mPassword);
//                        Message msg = new Message();
//                        msg.obj = bundle;
//                        handler.sendMessage(msg);
//                    }
                    break;
                case R.id.payment_gridview:
                    break;
                case R.id.zhifu_chongzhi:

                case R.id.topbutton_back:
                    finish();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//voucherGridview的条目点击事件
            LogUtils.i("listener itemclick");
            if (null != mVoucherCb && mVoucherCb.isChecked() && null != mAdapter) {
                VoucherBean.DataBean.ListBean bean = mDatas.get(position);
                int itemVoucherMoney = bean.getVoucherMoney();
                mVoucherId = bean.getVoucherId() + "";
                mVoucherMoney = itemVoucherMoney;
                mAdapter.setSelectedData(position, bean);//添加选中状态item，并且刷新gridview
                mAdapter.notifyDataSetChanged();
                updateRealMoney();
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {//在取消使用彩金券后，需要清除所有gridview选中状态，并且更新实收金额
            LogUtils.i("listener checkedChanged");
            if (!isChecked) {
                mAdapter.removeSelectData();
                mVoucherMoney = 0;
                updateRealMoney();
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Configure_JC.CHONGZHI) {

        }
    }

    class VocherAdapter extends BaseAdapter {
        private List<VoucherBean.DataBean.ListBean> data;
        private VoucherBean.DataBean.ListBean selectedData = new VoucherBean.DataBean.ListBean();

        VocherAdapter(List<VoucherBean.DataBean.ListBean> data) {
            this.data = data;
        }

        public void setSelectedData(int key, VoucherBean.DataBean.ListBean selectedData) {
            if (null != selectedData)
                this.selectedData = selectedData;
        }

        public boolean isSelected(VoucherBean.DataBean.ListBean selectedData) {
            if (this.selectedData == selectedData) {
                return true;
            } else {
                return false;
            }
        }

        public void removeSelectData() {
            this.selectedData = new VoucherBean.DataBean.ListBean();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            LogUtils.i("adapter voucher gv" + data.size());
            View view = null;
            try {
                if (null == convertView) {
                    holder = new Holder();
                    convertView = UIUtils.inflate(R.layout.adapter_payment);
                    holder.date = (TextView) convertView.findViewById(R.id.payment_item_date);
                    holder.money = (TextView) convertView.findViewById(R.id.payment_item_money);
                    holder.parent = (LinearLayout) convertView.findViewById(R.id.payment_parentLayout);
                    convertView.setTag(holder);
                }
                view = convertView;
                holder = (Holder) view.getTag();
                holder.money.setText(data.get(position).getVoucherMoney() + "元代金券");
                holder.date.setText("截止日期：" + data.get(position).getExprieDate().replace("-", "."));
                if (selectedData == (data.get(position))) {
                    holder.parent.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow_nocorner_bigger_gridview_shape));
                } else {
                    holder.parent.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_nocorner_bigger_gridview_shape));
                }
            } catch (Exception e) {

            }
            return view;
        }


        private class Holder {
            TextView money;
            TextView date;
            LinearLayout parent;
        }
    }
    @Override
    protected String getBaidutitle() {
        return "";
    }
}
