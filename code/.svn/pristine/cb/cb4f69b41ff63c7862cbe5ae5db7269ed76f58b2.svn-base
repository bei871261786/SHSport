package shlottery.gov.cn.lotterycenter.ui.nublottery.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.OrderBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.LoginManager;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.AddToStakeProtocol;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.LottoDingdanAdapter;
import shlottery.gov.cn.lotterycenter.utils.KeyBordStateUtil;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/21 10:11
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class LottoDingdanActivity extends BaseActivity implements LoadCallback {
    private Intent mIntent;
    private ArrayList<BaseBean> mQianquData = new ArrayList<>();
    private ArrayList<BaseBean> mHouquData = new ArrayList<>();
    private LottoDingdanAdapter mAdapter;
    private ArrayList<OrderBean> mOrders;
    private long count;
    private ListView mListview;
    private LinearLayout mAuto1;
    private LinearLayout mClear;
    private LinearLayout mManual;
    private TextView mTotalCountTxt;
    private TextView mTotalMoneyTxt;
    private Button mSave;
    private MyListener myListener;
    private CheckBox mAddCheckbox;
    private ImageView mMinus;
    private ImageView mPlus;
    private EditText mMultipleEdit;
    private int mMultiple = 1;//倍数
    private int mCurrentPlayType;
    private String mIssueno = "";
    private int mStakeAdd = 0;
    private KeyBordStateUtil keyBordStateUtil;

    private int mLottoeryPrice = 2;//彩票单价
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mAdapter.notifyDataSetChanged();
            updateBounds();
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_lotto_dingdan);
        keyBordStateUtil = new KeyBordStateUtil(this);
        mListview = (ListView) findViewById(R.id.lotto_dingdan_listView);
        mAuto1 = (LinearLayout) findViewById(R.id.lotto_headexpand_auto_1);
        mClear = (LinearLayout) findViewById(R.id.lotto_headexpand_clearAll);
        mManual = (LinearLayout) findViewById(R.id.lotto_headexpand_manual);
        mTotalCountTxt = (TextView) findViewById(R.id.lotto_dingdan_countTxt);
        mTotalMoneyTxt = (TextView) findViewById(R.id.lotto_dingdan_moneyTxt);
        mSave = (Button) findViewById(R.id.lotto_dingdan_save);
        mAddCheckbox = (CheckBox) findViewById(R.id.lotto_dingdan_checkbox);
        mMinus = (ImageView) findViewById(R.id.lotto_dingdan_minusSign);
        mPlus = (ImageView) findViewById(R.id.lotto_dingdan_plusSign);
        mMultipleEdit = (EditText) findViewById(R.id.lotto_dingdan_multiple_edit);
        mAuto1.setOnClickListener(myListener);
        mManual.setOnClickListener(myListener);
        mClear.setOnClickListener(myListener);
        mMinus.setOnClickListener(myListener);
        mPlus.setOnClickListener(myListener);
        mSave.setOnClickListener(myListener);
        mMultipleEdit.addTextChangedListener(myListener);
        mAddCheckbox.setOnCheckedChangeListener(myListener);
        mListview.setAdapter(mAdapter);
        mMultipleEdit.setOnKeyListener(new View.OnKeyListener() {
                                           @Override
                                           public boolean onKey(View v, int keyCode, KeyEvent event) {
                                               if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event.getAction()) {
                                                   if (StringUtils.isEmpty(mMultipleEdit.getText().toString())) {
                                                       mMultipleEdit.setText(1 + "");
                                                       mMultipleEdit.setSelection((1 + "").length());//将光标追踪到内容的最后
                                                   } else {
                                                       int i = Integer.parseInt(mMultipleEdit.getText().toString());
                                                       if (i > 99) {
                                                           mMultipleEdit.setText(99 + "");
                                                           mMultipleEdit.setSelection((99 + "").length());//将光标追踪到内容的最后
                                                       }
                                                   }
                                                   //收起键盘
                                                   InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(LottoDingdanActivity.this.INPUT_METHOD_SERVICE);
                                                   imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                                   return true;
                                               }
                                               return false;
                                           }
                                       }

        );

        keyBordStateUtil.addOnKeyBordStateListener(new KeyBordStateUtil.onKeyBordStateListener() {
            @Override
            public void onSoftKeyBoardShow(int keyboardHeight) {

            }

            @Override
            public void onSoftKeyBoardHide() {
                if (StringUtils.isEmpty((mMultipleEdit.getText().toString())) || mMultipleEdit.getText().toString().equals("0")) {
                    mMultipleEdit.setText(1 + "");
                    mMultipleEdit.setSelection(mMultipleEdit.getText().length());
                }
            }
        });
        mMultipleEdit.setSelection(mMultipleEdit.getText().length());//将光标追踪到内容的最后
        initTitleBar();
        updateBounds();
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOrders != null && mOrders.size() >= 1) {
                    showBackDialog();
                } else {
                    finish();
                }
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText("大乐透");
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }

    @Override
    protected void init() {
        mIntent = getIntent();
        mOrders = new ArrayList<>();
        Bundle bundle = mIntent.getExtras();
        if (null != bundle) {
            mQianquData = (ArrayList<BaseBean>) bundle.getSerializable("redData");
            mHouquData = (ArrayList<BaseBean>) bundle.getSerializable("blueData");
            mCurrentPlayType = (int) bundle.getSerializable("mCurrentPlayType");
            mIssueno = bundle.getString("issueno");
            count = bundle.getLong("count");
        }
        addToOrders(mQianquData, mHouquData, count);
        mAdapter = new LottoDingdanAdapter(this, mOrders, handler);
        myListener = new MyListener();
    }

    private void showBackDialog() {
        LogUtils.i("datasize showDialog");
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dingdan_football_backdialog, null);
        final Dialog mDialog = new android.app.AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(true);
        Button cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
        Button querrn = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
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
                finish();

            }
        });
    }

    private void addToOrders(ArrayList<BaseBean> mQianquData, ArrayList<BaseBean> mHouquData, long count) {
        OrderBean bean = new OrderBean();
        bean.setCount(count);
        bean.setData("redData", mQianquData);
        bean.setData("blueData", mHouquData);
        if (count <= 1) {
            bean.setOrderType("单式投注");
        } else {
            bean.setOrderType("复式投注");
        }
        mOrders.add(0, bean);
    }

    private void initData(ArrayList<BaseBean> redData, ArrayList<BaseBean> blueData) {
        String j = "";
        for (int i = 1; i <= 35; i++) {
            if (i < 10) {
                j = "0";
            } else {
                j = "";
            }
            BaseBean bean = new BaseBean();
            bean.setSelected(false);
            bean.setMsg(j + i);
            bean.setDan(false);
            bean.setMinCount(5);
            redData.add(bean);
        }

        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                j = "0";
            } else {
                j = "";
            }
            BaseBean bean = new BaseBean();
            bean.setSelected(false);
            bean.setMsg(j + i);
            bean.setDan(false);
            bean.setMinCount(2);
            blueData.add(bean);
        }
    }

    private void createByAuto() {
        ArrayList<BaseBean> redBean = new ArrayList<>();
        ArrayList<BaseBean> blueBean = new ArrayList<>();
        initData(redBean, blueBean);
        Random random = new Random();
        int position = 0;
        TreeSet<Integer> redData = new TreeSet();
        TreeSet<Integer> blueData = new TreeSet();
        while (redData.size() < 5) {
            position = random.nextInt(35);
            redData.add(position);
        }
        while (blueData.size() < 2) {
            position = random.nextInt(12);
            blueData.add(position);
        }
        Iterator<Integer> it = redData.iterator();
        while (it.hasNext()) {
            redBean.get(it.next()).setSelected(true);
        }
        Iterator<Integer> it2 = blueData.iterator();
        while (it2.hasNext()) {
            blueBean.get(it2.next()).setSelected(true);
        }
        addToOrders(redBean, blueBean, 1);
//        printOrder();
        mAdapter.notifyDataSetChanged();

    }

    protected void updateBounds() {
        long count = 0;
        for (int i = 0; i < mOrders.size(); i++) {
            count += mOrders.get(i).getCount();
        }
        mMultipleEdit.setText(mMultiple + "");
        mTotalCountTxt.setText("共" + count + "注");
        mTotalMoneyTxt.setText(count * mLottoeryPrice * mMultiple + "元");
        mMultipleEdit.setSelection((mMultiple + "").length());//将光标追踪到内容的最后
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                if (null != bundle) {
                    mQianquData = (ArrayList<BaseBean>) bundle.getSerializable("redData");
                    mHouquData = (ArrayList<BaseBean>) bundle.getSerializable("blueData");
                    count = bundle.getLong("count");
                }
                addToOrders(mQianquData, mHouquData, count);
                handler.sendEmptyMessage(0);
                break;
            default:
                break;
        }
    }

    private String inintStakeCode(ArrayList<OrderBean> mOrders) {

        String stakeCode = "";
        String count = "";

        for (int i = 0; i < mOrders.size(); i++) {
            count += mOrders.get(i).getCount();

        }
        for (int i = 0; i < mOrders.size(); i++) {
            String s = "";
            String stakeType = "";
            if (mOrders.get(i).getOrderType().equals("单式投注")) {
                stakeType = "d-";
            } else if (mOrders.get(i).getOrderType().equals("复式投注")) {
                stakeType = "f-";
            }
            count = mOrders.get(i).getCount() + "-";
            List<BaseBean> beanListRed = mOrders.get(i).getData().get("redData");
            List<BaseBean> beanListBlue = mOrders.get(i).getData().get("blueData");
            boolean hasDan = false;

            for (int j = 0; j < beanListRed.size(); j++) {

                if (beanListRed.get(j).isSelected()) {
                    if (beanListRed.get(j).isDan()) {
                        hasDan = true;
                        stakeType = "dt-";
                        s += beanListRed.get(j).getMsg() + ",";
                    } else {
                        if (hasDan) {
                            s = s.substring(0, s.length() - 1);
                            s += "$";
                            hasDan = false;
                        }
                        s += beanListRed.get(j).getMsg() + ",";
                    }
                }
            }
            LogUtils.i("inintStakeCode   IssueBonusJLDetailBean s  0:" + s);
            s = s.substring(0, s.length() - 1);
            s += "#";
            LogUtils.i("inintStakeCode   IssueBonusJLDetailBean s  1:" + s);
            for (int j = 0; j < beanListBlue.size(); j++) {
                LogUtils.i("inintStakeCode  number:" + beanListBlue.get(i).getMsg());
                if (beanListBlue.get(j).isSelected()) {
                    if (beanListBlue.get(j).isDan()) {
                        hasDan = true;
                        s += beanListBlue.get(j).getMsg() + ",";
                    } else {
                        if (hasDan) {
                            s = s.substring(0, s.length() - 1);
                            s += "$";
                            hasDan = false;
                        }
                        s += beanListBlue.get(j).getMsg() + ",";
                    }
                }
            }
            s = stakeType + count + s;
            LogUtils.i("inintStakeCode   IssueBonusJLDetailBean s  2:" + s);
            s = s.substring(0, s.length() - 1);
            if (!(i == mOrders.size() - 1)) {
                s += "|";
            }
            stakeCode += s;
        }
        return stakeCode;
    }


    public void saveZhudan(ArrayList<OrderBean> mOrders) {
        if (LoginManager.getInstance().login(this)) {
            count = 0;
            for (int i = 0; i < mOrders.size(); i++) {
                count += mOrders.get(i).getCount();
            }
            final String totalMoney = count * mLottoeryPrice * mMultiple + "";
            if (mStakeAdd == 0) {
                if (Integer.parseInt(totalMoney) <= Configs.LIMIT_DINGDAN) {
                    LogUtils.i("Lottodingdan totalMoney:" + totalMoney + "::::" + count + ":::" + mLottoeryPrice + "::::" + mMultiple);
                    final String stakeCode = inintStakeCode(mOrders);
                    AddToStakeProtocol protocol = new AddToStakeProtocol(this);
                    LogUtils.i("Lottodingdan params:" + "issueNo:" + mIssueno + "    mMultiple:" + mMultiple + "    mStakeAdd:" + mStakeAdd + "    totalMoney" + totalMoney + ":::stakeCode:" + stakeCode);
                    protocol.load(this, new ParamsHelperInterface() {
                        @Override
                        public LinkedHashMap<String, String> getParamas() {
                            LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                            params.put("lotId", "dlt");
                            params.put("issueNo", mIssueno);
                            params.put("multiple", mMultiple + "");
                            params.put("stakeAdd", mStakeAdd + "");
                            params.put("totalMoney", totalMoney);
                            params.put("stakeCode", stakeCode);
                            return params;
                        }
                    }, this);
                } else {
                    UIUtils.showToastSafe("投注金额不能超过" + Configs.LIMIT_DINGDAN);
                }
            } else if (mStakeAdd == 1) {
                if (Integer.parseInt(totalMoney) <= Configs.LIMIT_DINGDAN_ADD) {
                    LogUtils.i("Lottodingdan totalMoney:" + totalMoney + "::::" + count + ":::" + mLottoeryPrice + "::::" + mMultiple);
                    final String stakeCode = inintStakeCode(mOrders);
                    AddToStakeProtocol protocol = new AddToStakeProtocol(this);
                    LogUtils.i("Lottodingdan params:" + "issueNo:" + mIssueno + "    mMultiple:" + mMultiple + "    mStakeAdd:" + mStakeAdd + "    totalMoney" + totalMoney + ":::stakeCode:" + stakeCode);
                    protocol.load(this, new ParamsHelperInterface() {
                        @Override
                        public LinkedHashMap<String, String> getParamas() {
                            LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                            params.put("lotId", "dlt");
                            params.put("issueNo", mIssueno);
                            params.put("multiple", mMultiple + "");
                            params.put("stakeAdd", mStakeAdd + "");
                            params.put("totalMoney", totalMoney);
                            params.put("stakeCode", stakeCode);
                            return params;
                        }
                    }, this);
                } else {
                    UIUtils.showToastSafe("追加投注金额不能超过" + Configs.LIMIT_DINGDAN_ADD);
                }
            }
        }
    }

    @Override
    public void onSucess(Object o) {
        VcodeBean vcodeBean = (VcodeBean) o;
        if (vcodeBean.getRet() == 100) {
            UIUtils.showToastSafe("保存成功!");
            mOrders.clear();
            mAdapter.notifyDataSetChanged();
            Intent intent = new Intent();
            intent.setClass(LottoDingdanActivity.this, LottoActivity.class);
            intent.putExtra("type", "finish");
            intent.putExtra("mCurrentPlayType", mCurrentPlayType);
            LogUtils.i("lottoDingdan putextra type:" + mCurrentPlayType);
            startActivity(intent);
            finish();
        } else {
            UIUtils.showToastSafe(vcodeBean.getMsg());
        }
    }

    @Override
    public void onError() {
    }

    private class MyListener implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, TextWatcher {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lotto_dingdan_minusSign:
                    mMultiple--;
                    mMultiple = mMultiple < 1 ? 1 : mMultiple;
                    break;
                case R.id.lotto_dingdan_plusSign:
                    mMultiple = mMultiple >= 99 ? 99 : mMultiple;
                    mMultiple++;
                    break;
                case R.id.lotto_headexpand_manual:
                    Intent intent = new Intent();
                    intent.setClass(LottoDingdanActivity.this, LottoActivity.class);
                    intent.putExtra("type", "dingdan");
                    intent.putExtra("mCurrentPlayType", mCurrentPlayType);
                    startActivityForResult(intent, 0);
                    break;
                case R.id.lotto_headexpand_auto_1:
                    createByAuto();
                    break;
                case R.id.lotto_headexpand_clearAll:
                    if (mOrders == null || mOrders.size() == 0) {
                        return;
                    }
                    createDialog(Configs.DELETE_DIALOG);
                    break;
                case R.id.lotto_dingdan_save:
                    saveZhudan(mOrders);
                    break;
            }
            updateBounds();
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                mLottoeryPrice = 3;
                mStakeAdd = 1;
            } else {
                mLottoeryPrice = 2;
                mStakeAdd = 0;
            }
            handler.sendEmptyMessage(0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!editable.toString().isEmpty()) {
                mMultiple = Integer.valueOf(editable.toString());
            }
            long count = 0;
            for (int i = 0; i < mOrders.size(); i++) {
                count += mOrders.get(i).getCount();
            }
            mTotalCountTxt.setText("共" + count + "注");
            mTotalMoneyTxt.setText(count * mLottoeryPrice * mMultiple + "元");
        }
    }

    @Override
    public void onBackPressed() {
        if (mOrders != null && mOrders.size() >= 1) {
            createDialog(Configs.BACK_DIALOG);
        } else {
            finish();
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
                    mOrders.clear();
                    mAdapter.notifyDataSetChanged();
                } else {
                    finish();
                }
            }
        });
    }
    @Override
    protected String getBaidutitle() {
        return "数字彩注单保存";
    }
}
