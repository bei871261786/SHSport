package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingDanSelectBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanItemBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.RequestBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.callback.LoginCallBack;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.SaveStakeProtocol;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.DingDanGridViewMoreAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.DingDanGridviewAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.DingdanBaseAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.CalculateStage_BonusAsyncTask;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.DingDanGridViewChangeEventBus;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.RequestAsyncTask;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.RequestCallback;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.UpdataCallback;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.confirg.DataPool;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.utils.DingDanStringUtils;
import shlottery.gov.cn.lotterycenter.utils.EventBusUtil;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.KeyBordStateUtil;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerBaseDialogUtil;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.R.id.lotto_dingdan_multiple_edit;

/**
 * Created by yongchao.bei on 2016/7/8.
 */

public abstract class FB_DingDanBaseActivity extends BaseActivity implements UpdataCallback, RequestCallback,
        LoginCallBack, LoadCallback<VcodeBean> {
    protected TextView                   mAddMatch;
    protected TextView                   mDeleteMatch;
    protected TextView                   mTouZhuButton;
    protected EditText                   mMultipleEdit;
    protected TextView                   mMinMaxZhuShu;//投注倍数为1-99
    protected ListView                   mDingDanListView;
    protected TextView                   mSelectFangShi;//过关方式
    protected DingdanBaseAdapter         mDingDanAdapter;
    protected DingDanGridviewAdapter     mDingDanGridviewAdapter;
    protected DingDanGridViewMoreAdapter mDingDanGridviewMoreAdapter;
    protected GridViewWithoutScroll      mGridViewChuanLianGuoGuan;
    protected GridViewWithoutScroll      mGridViewMoreChuanLianGuoGuan;
    protected TextView                   mPassTypeShouTv;
    protected RelativeLayout             mPassTypeShou;
    protected LinearLayout               mGuoGuanTitle;
    protected ImageView                  mPassTypeShouImg;
    protected ImageView                  mPassTypeHideImg;
    private   ImageView                  mMinus;
    private   ImageView                  mPlus;
    protected boolean mFlag = true;
    protected String            mGuoGuanFangShi;
    protected Intent            mIntent;
    protected int               mSubType;
    protected int               mType;
    protected SoccerDialogUtils mDialogUtil;
    protected List<HashMap<Integer, MatchsBean>> mDingDanMixDatas = new ArrayList<>();//订单拿到的数据
    protected List<MatchsBean>                   mDingDanDatas    = new ArrayList<MatchsBean>();//订单拿到的数据
    protected TextView mIssue_count_tv;//下方注数textview
    protected TextView mIssue_bonus_tv;//下方金额textview
    protected int      mIssueCount;
    protected ArrayList<String>          mChuanlianList     = new ArrayList<>();//串联方式集合,用于计算奖金和注数
    protected String                     mMsg               = "";//eventbus得到的是过关方式在更多还是几选一中
    protected int                        mDingdanLength     = 0;
    protected DingDanSelectBean          mDingDanSelectBean = new DingDanSelectBean();
    protected ArrayList<DingdanItemBean> mDingdans          = new ArrayList<>();
    protected String     mIssueNo;
    protected int        mJcType;//具体的彩种id，由configure控制，在子类中初始化
    protected int        mMinLimitType;//用于计算更多串法的数量类型,是当前混合过关中选中的最小限制类型
    private   MyListener mOnclickListener;
    protected boolean isNormal = true;//是否使用常规/混合数据
    protected int              mMultiple;
    private   RequestAsyncTask mRequestAsyncTask;
    protected String           mTitleName;

    private KeyBordStateUtil keyBordStateUtil;


    public void setDingdan(ArrayList<DingdanItemBean> mDingdans) {
        this.mDingdans = mDingdans;
    }

    public ArrayList<DingdanItemBean> getmDingdans() {
        return mDingdans;
    }

    protected void initView() {
        setContentView(R.layout.activity_dingdan);
        mAddMatch = (TextView) findViewById(R.id.add_match);
        mDeleteMatch = (TextView) findViewById(R.id.delete_all);
        mTouZhuButton = (TextView) findViewById(R.id.touzhu_button);
        mDingDanListView = (ListView) findViewById(R.id.dingdan_listview);
        mGuoGuanTitle = (LinearLayout) findViewById(R.id.guoguan_button);
        mMultipleEdit = (EditText) findViewById(lotto_dingdan_multiple_edit);
        mMinMaxZhuShu = (TextView) findViewById(R.id.min_max_zhushu);
        mPassTypeShouTv = (TextView) findViewById(R.id.passTypeShouTv);
        mPassTypeShou = (RelativeLayout) findViewById(R.id.passTypeShou);
        mPassTypeShouImg = (ImageView) findViewById(R.id.passTypeShouImg);
        mPassTypeHideImg = (ImageView) findViewById(R.id.passTypeHideImg);
        mGridViewChuanLianGuoGuan = (GridViewWithoutScroll) findViewById(R.id.chuanguan_gridview);
        mGridViewMoreChuanLianGuoGuan = (GridViewWithoutScroll) findViewById(R.id.more_chuanlian_gridview);
        mSelectFangShi = (TextView) findViewById(R.id.select_fangshi);
        mIssue_count_tv = (TextView) findViewById(R.id.dingdan_issue_count);
        mIssue_bonus_tv = (TextView) findViewById(R.id.dingdan_issue_bonus);
        mMinus = (ImageView) findViewById(R.id.lotto_dingdan_minusSign);
        mPlus = (ImageView) findViewById(R.id.lotto_dingdan_plusSign);
        initTitleBar(mTitleName);
        mDingDanListView.setAdapter(mDingDanAdapter);
        mOnclickListener = new MyListener();
        mAddMatch.setOnClickListener(mOnclickListener);
        mDeleteMatch.setOnClickListener(mOnclickListener);
        mMinus.setOnClickListener(mOnclickListener);
        mPlus.setOnClickListener(mOnclickListener);
        mTouZhuButton.setOnClickListener(mOnclickListener);
        keyBordStateUtil = new KeyBordStateUtil(this);
        mMultipleEdit.setOnKeyListener(new View.OnKeyListener() {
                                           @Override
                                           public boolean onKey(View v, int keyCode, KeyEvent event) {
                                               if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event
                                                       .getAction()) {
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
                                                   InputMethodManager imm = (InputMethodManager) v.getContext()
                                                           .getSystemService(FB_DingDanBaseActivity.this
                                                                   .INPUT_METHOD_SERVICE);
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
                if (StringUtils.isEmpty((mMultipleEdit.getText().toString())) || mMultipleEdit.getText().toString()
                        .equals("0")) {
                    mMultipleEdit.setText(1 + "");
                    mMultipleEdit.setSelection(mMultipleEdit.getText().length());
                }
                String s = mMultipleEdit.getText().toString();
                if (!StringUtils.isEmpty(s)) {
                    mMultiple = Integer.parseInt(s.toString());
                } else {
                    mMultiple = 1;
                }
                DataPool.setMultiple(mMultiple);
                updateBonusInfo();

            }
        });
        if (mGuoGuanFangShi.equals("danguan")) {
            mPassTypeShou.setVisibility(View.GONE);
            mGridViewChuanLianGuoGuan.setVisibility(View.GONE);
            mGridViewMoreChuanLianGuoGuan.setVisibility(View.GONE);
            mGuoGuanTitle.setVisibility(View.GONE);
        } else {
            initSelectBean();
            mDingDanGridviewAdapter = new DingDanGridviewAdapter(this, mGuoGuanFangShi, mDingDanSelectBean);
            //订单gridview适配器
            mDingDanGridviewMoreAdapter = new DingDanGridViewMoreAdapter(this, mGuoGuanFangShi, mDingDanSelectBean);
            //订单更多gridview适配器
            mGridViewMoreChuanLianGuoGuan.setAdapter(mDingDanGridviewMoreAdapter);
            mGridViewChuanLianGuoGuan.setAdapter(mDingDanGridviewAdapter);
            mPassTypeShou.setOnClickListener(mOnclickListener);
        }
        if (mGuoGuanFangShi.equals("guoguan")) {//初始化过关方式
            updateGuoguanfangshi();
            handlerEventbus.sendEmptyMessage(0);
        } else {
            updateBonusInfo();
        }
    }


    private void initTitleBar(String titleName) {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNormal) {
                    LogUtils.i("datasize:" + mDingDanDatas.size());
                    if (mDingDanDatas.size() > 0) {
                        showDialog();
                    } else {
                        //                        handleBack();
                        setResult(1);
                        finish();
                    }
                } else {
                    LogUtils.i("datasize:" + mDingDanMixDatas.size());
                    if (mDingDanMixDatas.size() > 0) {
                        showDialog();
                    } else {
                        //                        handleBack();
                        setResult(1);
                        finish();
                    }
                }
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText(titleName);
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }

    protected void saveDingdanInfo(List<MatchsBean> data) {
        mDingdans.clear();
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                MatchsBean bean = data.get(i);
                String[] itemInfos = null;
                String[] orderInfos = null;
                if (null != bean.getLotId()) {
                    itemInfos = getSuitableItemInfo(bean.getLotId(), mType);
                    orderInfos = getSuitableOrderInfo(bean.getLotId(), mType);
                }
                //每个checkbox状态集合
                HashMap<Integer, Boolean> spsMap = JcDataUtils.getSpsStateMap(bean);
                if (spsMap.containsValue(true)) {
                    //每个订单
                    DingdanItemBean dingdanItem = new DingdanItemBean();
                    //数据中的sps值集合
                    ArrayList<String> sps = (ArrayList<String>) bean.getSps();

                    //选中状态的sps值集合，用于添加到订单信息中
                    ArrayList<String> spsList = new ArrayList<>();
                    //订单中每一个item上的信息
                    StringBuilder itemInfo = new StringBuilder();
                    //订单中需要得到投注代码的信息
                    StringBuilder orderInfo = new StringBuilder();

                    LogUtils.i("dingdan spsMap长度：" + spsMap.size() + ":::" + sps.size());
                    for (int j = 0; j < spsMap.size(); j++) {
                        LogUtils.i("dingdan spsMap：" + j);
                        if (spsMap.get(j)) {
                            spsList.add(sps.get(j));
                            if (null != itemInfos && itemInfos.length > 0) {
                                itemInfo.append(itemInfos[j] + " , ");
                            }
                            if (null != orderInfos && orderInfos.length > 0) {
                                orderInfo.append(orderInfos[j] + " , ");
                            }
                        }
                    }
                    dingdanItem.setItemInfo(itemInfo.length() > 0 ? itemInfo.toString().substring(0, itemInfo
                            .toString().lastIndexOf(" ,")) : "");
                    dingdanItem.setSpsList(spsList);
                    dingdanItem.setLotId(data.get(i).getLotId());
                    dingdanItem.setDan(data.get(i).isDan());
                    dingdanItem.setMatchNo(data.get(i).getMatchNo());
                    dingdanItem.setOptionValue(orderInfo.toString());
                    mDingdans.add(dingdanItem);
                }
            }
        }
    }

    protected void saveMixDingdanInfo(List<HashMap<Integer, MatchsBean>> datas, ArrayList<Boolean> danList) {
        mDingdans.clear();
        if (!datas.isEmpty()) {
            for (int i = 0; i < datas.size(); i++) {
                MatchsBean data = datas.get(i).get(JcDataUtils.getIndicator(datas.get(i)));
                String[] itemInfos = getSuitableItemInfo(data.getLotId(), mType);
                String[] orderInfos = getSuitableOrderInfo(data.getLotId(), mType);
                //每个checkbox状态集合
                HashMap<Integer, Boolean> spsMap = JcDataUtils.getSpsStateMap(data);
                if (spsMap.containsValue(true)) {
                    //每个订单
                    DingdanItemBean dingdanItem = new DingdanItemBean();
                    //数据中的sps值集合
                    ArrayList<String> sps = (ArrayList<String>) data.getSps();
                    //选中状态的sps值集合，用于添加到订单信息中
                    ArrayList<String> spsList = new ArrayList<>();
                    //订单中每一个item上的信息
                    StringBuilder itemInfo = new StringBuilder();
                    //订单中需要得到投注代码的信息
                    StringBuilder orderInfo = new StringBuilder();
                    LogUtils.i("dingdan spsMap长度：" + spsMap.size() + "::::" + itemInfos.length + "::::" + data
                            .getLotId());
                    for (int j = 0; j < spsMap.size(); j++) {
                        if (spsMap.get(j)) {
                            spsList.add(sps.get(j));
                            if (null != itemInfos && itemInfos.length > 0) {
                                itemInfo.append(itemInfos[j] + " , ");
                            }
                            if (null != orderInfos && orderInfos.length > 0) {
                                orderInfo.append(orderInfos[j] + " , ");
                            }
                        }
                    }
                    dingdanItem.setItemInfo(itemInfo.length() > 0 ? itemInfo.toString().substring(0, itemInfo
                            .toString().lastIndexOf(" ,")) : "");
                    dingdanItem.setSpsList(spsList);
                    dingdanItem.setLotId(data.getLotId());
                    dingdanItem.setDan(danList.get(i));
                    LogUtils.i("dingdan dan:：" + danList.get(i) + "::::" + itemInfo);
                    dingdanItem.setMatchNo(data.getMatchNo());
                    dingdanItem.setOptionValue(orderInfo.toString());
                    mDingdans.add(dingdanItem);
                }
            }
        }
    }

    protected String[] getSuitableItemInfo(String lotId, int type) {

        String[] infos = null;
        if (type == Configure_JC.TYPE_SOCCER) {
            switch (lotId) {
                case "jzxspf":
                    infos = Configure_JC.FB_DIALOG_XSPF;
                    break;
                case "jzspf":
                    infos = Configure_JC.FB_DIALOG_SPF;
                    break;
                case "jzjqs":
                    infos = Configure_JC.FB_DIALOG_ZJQ;
                    break;
                case "jzbf":
                    infos = Configure_JC.FB_DIALOG_BF;
                    break;
                case "jzbqc":
                    infos = Configure_JC.FB_DIALOG_BQC;
                    break;
                default:
                    infos = Configure_JC.FB_DIALOG_2x1;
                    break;
            }
        } else {
            switch (lotId) {
                case "jlsf":
                    LogUtils.i("getSuitableItemInfo:::::1");
                    infos = Configure_JC.FB_DIALOG_SF;
                    break;
                case "jlfc":
                    LogUtils.i("getSuitableItemInfo:::::2");
                    infos = Configure_JC.FB_DIALOG_SFC;
                    break;
                case "jlrsf":
                    LogUtils.i("getSuitableItemInfo:::::3");
                    infos = Configure_JC.FB_DIALOG_XSF;
                    break;
                case "jldx":
                    LogUtils.i("getSuitableItemInfo:::::4");
                    infos = Configure_JC.FB_DIALOG_DX;
                    break;
            }
        }
        return infos;
    }

    protected String[] getSuitableOrderInfo(String lotId, int type) {
        String[] infos = null;
        if (type == Configure_JC.TYPE_SOCCER) {
            switch (lotId) {
                case "jzxspf":
                    infos = Configure_JC.FB_DIALOG_XSPF;
                    break;
                case "jzspf":
                    infos = Configure_JC.FB_DIALOG_SPF;
                    break;
                case "jzjqs":
                    infos = Configure_JC.FB_DIALOG_ZJQ;
                    break;
                case "jzbf":
                    infos = Configure_JC.FB_DIALOG_BF;
                    break;
                case "jzbqc":
                    infos = Configure_JC.FB_DIALOG_BQC;
                    break;
                default:
                    infos = Configure_JC.FB_DIALOG_2x1;
                    break;
            }
        } else {
            switch (lotId) {
                case "jlsf":
                    LogUtils.i("getSuitableItemInfo:::::1");
                    infos = Configure_JC.FB_DIALOG_SF;
                    break;
                case "jlfc":
                    LogUtils.i("getSuitableItemInfo:::::2");
                    infos = Configure_JC.FB_DIALOG_SFC;
                    break;
                case "jlrsf":
                    LogUtils.i("getSuitableItemInfo:::::3");
                    infos = Configure_JC.FB_DIALOG_SF;
                    break;
                case "jldx":
                    LogUtils.i("getSuitableItemInfo:::::4");
                    infos = Configure_JC.FB_DIALOG_DX;
                    break;
            }
        }
        LogUtils.i("getSuitableItemInfo:" + lotId + ":::" + type + ":::" + infos.length + ":::::" + infos[0] + ":::"
                + infos[1]);
        return infos;
    }

    public String getIssueNo() {
        return mIssueNo;
    }

    protected boolean isCanPayment() {//是否可以跳转支付页面的规则
        int totalMoney = 0;
        DingdanBean dingdanBean = getPaymentDingdan();
        if (dingdanBean != null && dingdanBean.getDingdanItems().size() > 0) {
            totalMoney = dingdanBean.getTotalMoney();
        }
        //过关方式
        String passWay = mSelectFangShi.getText().toString().trim();
        boolean isGuoGuanShow = mGuoGuanTitle.getVisibility() == View.VISIBLE;
        //需要选择过根方式
        boolean sholdChosePassWay = isGuoGuanShow && TextUtils.isEmpty(passWay);

        if (mIssueCount > 0 && BaseApplication.ismLoginStatus() && !sholdChosePassWay && totalMoney <= 20000) {
            return true;
        } else if (!BaseApplication.ismLoginStatus()) {
            SoccerBaseDialogUtil dialogUtil = new SoccerBaseDialogUtil();
            dialogUtil.createLoginDialog(this);
            return false;
        } else if (sholdChosePassWay) {
            UIUtils.showToastSafe("您还没有选择过关方式");
            return false;
        } else if (mIssueCount <= 0) {
            LogUtils.i("isCanPayment" + "您没有下注");
            UIUtils.showToastSafe("您没有下注");
            return false;
        } else if (totalMoney > 20000) {
            UIUtils.showToastSafe("金额不能超过2万");
            return false;
        }
        return false;
    }


    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            LogUtils.i("dingdan  handler what是：" + msg.what);
            //根据mDingDanDatas保存订单数据
            if (isNormal) {
                saveDingdanInfo(mDingDanDatas);
            } else {
                mMinLimitType = JcDataUtils.getMinLimitType(mDingDanMixDatas);
                saveMixDingdanInfo(mDingDanMixDatas, mDingDanAdapter.getDanList());
            }
            chooseMatchsBeans();
            LogUtils.i("dingdan  handler :" + mDingdanLength);
            //根据订单的改变改变下方的串联列表,并且根据订单的长度，重新填充串联方式数据
            if (mGuoGuanFangShi.equals("guoguan")) {
                //跟新mDingdanBeans和过关方式集合,防止计算奖金和注数出现数组越界
                updateGuoguanfangshi();
                mDingDanGridviewAdapter.notifyDataSetChanged();
                mDingDanGridviewMoreAdapter.notifyDataSetChanged();
            }
            //跟新下方金额和注数
            updateBonusInfo();
            mDingDanAdapter.notifyDataSetChanged();
        }
    };

    //处理过关方式eventbus的handler
    public Handler handlerEventbus = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.i("adapter spf msg:" + mMsg);
            chooseMatchsBeans();
            //  initSelectBean();
            updateChuanlianList(mMsg);
            //更新下方金额和注数
            updateBonusInfo();
            if (mMsg.equals("MoreAdapterChange")) {
                LogUtils.i("handlerEventbus MoreAdapterChange");
                for (int i = 0; i < mDingdans.size(); i++) {
                    mDingdans.get(i).setDan(false);
                }
                mDingDanAdapter.setDanCount(0);
                mDingDanAdapter.setmIsCleanDan(true);
            } else {
                if (null != mChuanlianList && !mChuanlianList.isEmpty()) {
                    int mShouldDanCount = 0;
                    //当前定胆数量， 如果应该具有的定胆数量小于当前定胆数量，下达清除指令
                    int currentDanCount = 0;
                    if (isNormal) {
                        currentDanCount = JcDataUtils.getDanCount(mDingDanDatas);
                        LogUtils.i("adapter spf dan count:" + currentDanCount);
                    } else {
                        currentDanCount = mDingDanAdapter.getDanCount();
                        LogUtils.i("adapter spf dan count mix:" + currentDanCount);
                    }
                    LogUtils.i("handler eventbus:" + mShouldDanCount + ":::" + currentDanCount + ":::" +
                            mDingDanAdapter.ismIsCleanDan());
                    switch (mChuanlianList.get(0)) {
                        case "2串1":
                            mShouldDanCount = 1;
                            break;
                        case "3串1":
                            mShouldDanCount = 2;
                            break;
                        case "4串1":
                            mShouldDanCount = 3;
                            break;
                        case "5串1":
                            mShouldDanCount = 4;
                            break;
                        case "6串1":
                            mShouldDanCount = 5;
                            break;
                        case "7串1":
                            mShouldDanCount = 6;
                            break;
                        case "8串1":
                            mShouldDanCount = 7;
                            break;
                    }
                    if (currentDanCount > mShouldDanCount) {
                        mDingDanAdapter.setmIsCleanDan(true);
                    }
                    LogUtils.i("handler eventbus2:::::::::::" + mShouldDanCount + ":::" + currentDanCount + ":::" +
                            mDingDanAdapter.ismIsCleanDan());
                    mDingDanAdapter.setDanCount(mShouldDanCount);
                } else {
                    mDingDanAdapter.setDanCount(0);
                    mDingDanAdapter.setmIsCleanDan(true);
                }
            }
            //跟新胆的状态
            mDingDanAdapter.notifyDataSetChanged();
        }
    };

    //初始化dingdanbean,订单长度变化时必须调用(这里变化都会处理handler，所以在handle中被调用),保证过关方式长度随之改变
    private void initSelectBean() {
        LogUtils.i("init selected bean::1  " + mDingdanLength);
        if (null == mDingDanSelectBean) {
            mDingDanSelectBean = DataPool.getDingDanSelectBean(mSubType);
        }
        //m是根据订单长度来确定到几串几
        int m = DingDanStringUtils.calculateCounts(mDingdanLength, mMinLimitType);
        List<Boolean> listGridview;
        List<Boolean> listMoreGridvi0ew;

        //如果在胜平负情况下,重新被选中,就是selectedchuanlian的长度小于订单长度的情况,需要重新添加
        if (mDingDanSelectBean.getmSelectChuanLian().isEmpty() || mDingDanSelectBean.getmSelectChuanLian().size() < m) {
            listGridview = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                listGridview.add(i, Boolean.FALSE);
            }
        } else {
            //因为数据更改，所以删除多余的过关方式
            listGridview = mDingDanSelectBean.getmSelectChuanLian();
            LogUtils.i("init selected bean:::::::1.5:   " + listGridview.size() + "::" + m);
            if (listGridview.size() != m) {
                //几选一不需要初始化listGridview，因为是固定的，不随着订单长度的变化而变化
                //删除超过当前订单长度下的过关方式
                while (listGridview.size() > m) {
                    listGridview.remove(listGridview.size() - 1);
                }
            }
        }
        LogUtils.i("init selected bean::::::::2  " + mDingdanLength + "   listGridview.size::::" + listGridview.size());
        //设置读取字符串数组的位置
        mDingDanSelectBean.setmMoreParentPosition(mDingdanLength - 3, mMinLimitType);
        //n是过关方式选中状态集合的长度
        int n = DingDanStringUtils.calculateMoreCounts(mDingDanSelectBean.getmMoreParentPosition());
        //n和setmMoreParentPosition是限制什么类型能够串几场的关键
        LogUtils.i("init selected bean:::::::::3  " + n);
        if (mDingDanSelectBean.getmSelectMoreChuanLian().isEmpty() || mDingDanSelectBean.getmSelectMoreChuanLian()
                .size() < n) {
            listMoreGridvi0ew = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                listMoreGridvi0ew.add(j, Boolean.FALSE);
            }
        } else {
            //因为数据更改，所以删除多余的过关方式
            listMoreGridvi0ew = mDingDanSelectBean.getmSelectMoreChuanLian();
            //如果不相等代表订单长度变化，所以需要重新初始化mDingDanSelectBean中的listGridview
            if (listMoreGridvi0ew.size() != n) {
                listMoreGridvi0ew = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    listMoreGridvi0ew.add(i, Boolean.FALSE);
                }
            }
        }
        LogUtils.i("init selected bean:::::::::4    " + listGridview.size() + ":::" + listMoreGridvi0ew.size());
        mDingDanSelectBean.setmSelectChuanLian(listGridview);
        mDingDanSelectBean.setmSelectMoreChuanLian(listMoreGridvi0ew);
    }

    //处理过关方式的eventbus
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DingDanGridViewChangeEventBus event) {
        LogUtils.i("updateeventbus");
        mMsg = event.getS();
        handlerEventbus.sendEmptyMessage(0);
    }

    //跟新mDingdanBeans和过关方式集合
    private void updateGuoguanfangshi() {
        initSelectBean();
        LogUtils.i("updateguoguanfangshi list:" + mDingDanSelectBean.getmSelectChuanLian().toString());
        if ("".equals(mMsg)) {
            mMsg = mDingDanSelectBean.getmSelectChuanLian().contains(true) ? "GridViewAdapterChange" : "";
        }
        if ("".equals(mMsg)) {
            mMsg = mDingDanSelectBean.getmSelectMoreChuanLian().contains(true) ? "MoreAdapterChange" : "";
        }
        LogUtils.i("updateguoguanfangshi::::" + mMsg + ":::" + mDingdanLength + ":::");
        updateChuanlianList(mMsg);
    }

    //处理eventbus事件，并且跟新串联方式集合
    private void updateChuanlianList(String msg) {
        if (msg.equals("GridViewAdapterChange")) {
            String s = "";
            mChuanlianList.clear();
            for (int i = 0; i < mDingDanSelectBean.getmSelectChuanLian().size(); i++) {
                if (mDingDanSelectBean.getmSelectChuanLian().get(i)) {
                    if (s.isEmpty()) {
                        mChuanlianList.add(DingDanStringUtils.getChuanLianStringText(i));
                        s = mChuanlianList.get(mChuanlianList.size() - 1);
                    } else {
                        mChuanlianList.add(DingDanStringUtils.getChuanLianStringText(i));
                        s += "," + mChuanlianList.get(mChuanlianList.size() - 1);
                    }
                }
            }
            mSelectFangShi.setText(s);
        } else if (msg.equals("MoreAdapterChange")) {
            String s = "";
            mChuanlianList.clear();
            for (int i = 0; i < mDingDanSelectBean.getmSelectMoreChuanLian().size(); i++) {
                if (mDingDanSelectBean.getmSelectMoreChuanLian().get(i)) {
                    mChuanlianList.add(DingDanStringUtils.getChuanLianMoreStringText(mDingDanSelectBean
                            .getmMoreParentPosition(), i));
                    s += mChuanlianList.get(mChuanlianList.size() - 1);
                }
            }
            mSelectFangShi.setText(s);
        }
        if (null != mDingDanGridviewAdapter && null != mDingDanGridviewMoreAdapter) {
            mDingDanGridviewAdapter.notifyDataSetChanged();
            mDingDanGridviewMoreAdapter.notifyDataSetChanged();
        }
    }

    //跟新下方金额和注数信息
    private void updateBonusInfo() {
        LogUtils.i("caluclate updateBeanInfo preoare" + (null == mIssue_bonus_tv) + ":::" + (null == mIssue_count_tv)
                + ":::" + (null == mChuanlianList) + ":::" + (null == mDingdans));
        if (null != mIssue_bonus_tv && null != mIssue_count_tv && null != mDingdans) {
            new CalculateStage_BonusAsyncTask(this).execute(mDingdans, mChuanlianList, mGuoGuanFangShi);
        }
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.lotto_dingdan_minusSign:
                    mMultiple--;
                    mMultiple = mMultiple < 1 ? 1 : mMultiple;
                    DataPool.setMultiple(mMultiple);
                    mMultipleEdit.setText(mMultiple + "");
                    updateBonusInfo();
                    break;
                case R.id.lotto_dingdan_plusSign:
                    mMultiple++;
                    mMultiple = mMultiple >= 99 ? 99 : mMultiple;
                    DataPool.setMultiple(mMultiple);
                    mMultipleEdit.setText(mMultiple + "");
                    updateBonusInfo();
                    break;

                case R.id.add_match:
                    handleBack();
                    break;
                case R.id.delete_all:
                    if (isNormal) {
                        mDingDanDatas.removeAll(mDingDanDatas);
                    } else {
                        mDingDanMixDatas.removeAll(mDingDanMixDatas);
                    }
                    handler.sendEmptyMessage(0);
                    break;
                case R.id.touzhu_button:
                    if (isCanPayment()) {
                        execSave(getPaymentDingdan());
                    }
                    break;
                case R.id.passTypeShou:
                    if (mFlag) {
                        mFlag = false;
                        mPassTypeHideImg.setVisibility(View.INVISIBLE);
                        mPassTypeShouImg.setVisibility(View.VISIBLE);
                        mGridViewMoreChuanLianGuoGuan.setVisibility(View.VISIBLE);
                    } else {
                        mFlag = true;
                        mPassTypeHideImg.setVisibility(View.VISIBLE);
                        mPassTypeShouImg.setVisibility(View.INVISIBLE);
                        mGridViewMoreChuanLianGuoGuan.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;

            }
        }


        //        //监控文本变化
        //        @Override
        //        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //            LogUtils.i("textwatcher before");
        ////            mMinMaxZhuShu.setVisibility(View.VISIBLE);
        //        }
        //
        //        @Override
        //        public void onTextChanged(CharSequence s, int start, int before, int count) {
        //            Editable editable = mMultipleEdit.getText();
        //            int len = editable.length();
        //            if (len > 2) {
        //                int selEndIndex = Selection.getSelectionEnd(editable);
        //                String str = editable.toString();
        //                //截取新字符串
        //                String newStr = str.substring(0, 2);
        //                mMultipleEdit.setText(newStr);
        //                editable = mMultipleEdit.getText();
        //
        //                //新字符串的长度
        //                int newLen = editable.length();
        //                //旧光标位置超过字符串长度
        //                if (selEndIndex > newLen) {
        //                    selEndIndex = editable.length();
        //                }
        //                //设置新光标所在的位置
        //                Selection.setSelection(editable, selEndIndex);
        //
        //            } else if (len < 1) {
        //                mMultipleEdit.setText("1");
        //            }
        //
        //        }
        //
        //        @Override
        //        public void afterTextChanged(Editable s) {
        //            if (!s.toString().isEmpty()) {
        //                mMultiple = Integer.valueOf(s.toString());
        //            }
        //            LogUtils.i("textwatcher after" + mMultiple);
        //            DataPool.setMultiple(mMultiple);
        //            updateBonusInfo();
        //        }
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }

    private void showDialog() {
        LogUtils.i("datasize showDialog");
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dingdan_football_backdialog, null);
        final Dialog mDialog = new android.app.AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(true);
        Button cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
        Button submit = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNormal) {
                    mDingDanDatas.removeAll(mDingDanDatas);
                    EventBus.getDefault().post(new EventBusUtil().getDingdanResultEvent(mType, mSubType,
                            mGuoGuanFangShi, mDingDanDatas));
                } else {
                    mDingDanMixDatas.removeAll(mDingDanMixDatas);
                    EventBus.getDefault().post(new EventBusUtil().getDingdanMixResultEvent(mType, mSubType,
                            mGuoGuanFangShi, mDingDanMixDatas));
                }
                mDialog.dismiss();
                //                handleBack();
                setResult(1);
                FB_DingDanBaseActivity.this.finish();
            }
        });
    }

    private RequestBean parseFromJson(String json) {
        Gson gson = new Gson();
        RequestBean resultData = gson.fromJson(json, RequestBean.class);
        return resultData;
    }

    @Override
    public void getResultFromAysntask(Object o) {
        Message msg = (Message) o;
        int count = msg.arg1;
        mIssueCount = count;
        String bonus = (String) msg.obj;
        LogUtils.i("asyntask count getresult :" + count + "   bonus:" + bonus);
        mIssue_count_tv.setText(count + "注," + "共" + count * DataPool.getMultiple() * DataPool.getPrice() + "元");
        mIssue_bonus_tv.setText("最高奖金:" + bonus + "元");
    }

    //保存未付款方案接口回调
    @Override
    public void getRequestResultFromAsyntask(Object o) {
        String json = (String) o;
        if (null != json) {
            RequestBean resultData = parseFromJson(json);
            int ret = resultData.getRet();
            if (100 == ret) {
                UIUtils.showToastSafe("保存成功");
                setResult(Configure_JC.PAYRES_SUCC);
                if (isNormal) {
                    mDingDanDatas.removeAll(mDingDanDatas);
                    EventBus.getDefault().post(new EventBusUtil().getDingdanResultEvent(mType, mSubType,
                            mGuoGuanFangShi,
                            mDingDanDatas));
                } else {
                    mDingDanMixDatas.removeAll(mDingDanMixDatas);
                    EventBus.getDefault().post(new EventBusUtil().getDingdanMixResultEvent(mType, mSubType,
                            mGuoGuanFangShi,
                            mDingDanMixDatas));
                }
                mRequestAsyncTask = null;
                finish();
            } else {
                String msg = resultData.getMsg();
                mRequestAsyncTask = null;
                UIUtils.showToastSafe(msg);//提示错误信息
            }
        } else {
            mRequestAsyncTask = null;
            UIUtils.showToastSafe("保存失败");
        }
    }

    private void handleBack() {
        if (isNormal) {
            EventBus.getDefault().post(new EventBusUtil().getDingdanResultEvent(mType, mSubType, mGuoGuanFangShi,
                    mDingDanDatas));
        } else {
            EventBus.getDefault().post(new EventBusUtil().getDingdanMixResultEvent(mType, mSubType, mGuoGuanFangShi,
                    mDingDanMixDatas));
        }
        finish();
    }

    @Override
    protected void onStop() {
        LogUtils.i("activity stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoccerDialogUtils.removeDialog();
        EventBus.getDefault().unregister(this);
    }


    //得到用于进入支付页面的订单信息
    protected DingdanBean getPaymentDingdan() {
        DingdanBean dingdan = new DingdanBean();
        dingdan.setDingdanItems(mDingdans);
        dingdan.setLotId(BaseApplication.getCurrentLotId());
        dingdan.setIssueNo(getIssueNo());
        dingdan.setmJcType(mJcType);
        dingdan.setTotalMoney(mIssueCount * DataPool.getPrice() * DataPool.getMultiple());
        dingdan.setTotalCount(mIssueCount);
        dingdan.setMultiple(DataPool.getMultiple());
        //如果串联方式为空，表明是单关，赋值1串1，以便支付传递参数
        if (mChuanlianList.isEmpty()) {
            mChuanlianList.add("1串1");
        }
        dingdan.setmChuanlianList(mChuanlianList);
        return dingdan;
    }


    protected abstract void chooseMatchsBeans();

    @Override
    public void toggleLoginFragment(boolean isLogin) {

    }


    private void execSave(final DingdanBean dingdanBean) {
        SaveStakeProtocol protocol = new SaveStakeProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                return getParams(dingdanBean);
            }
        }, this);

    }

    // 获得支付的post参数
    private LinkedHashMap<String, String> getParams(DingdanBean dingdanBean) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();

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
            issueNo = dingdanBean.getIssueNo();
            multiple = dingdanBean.getMultiple();
            totalMoney = dingdanBean.getTotalMoney();
            stakeLotid = getStakeLotId(dingdanBean.getmJcType());
            chuanlianList = dingdanBean.getmChuanlianList();
        }

        switch (BaseApplication.getCurrentLotId()) {
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
                    //混合过关的stakeCode与其他方式不一样
                    if ("jzhh".equals(stakeLotid)) {
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
                LogUtils.i("payment 投注参数是   mJcType：" + mJcType + "::::" + "lotId:" + stakeLotid + "     issueNo:" +
                        issueNo + "   multiple:" + multiple + "   stackeAdd:" + stackeAdd + "  totalMoney:" +
                        totalMoney + "  chuanlianList :" + chuanlianList + ":::stakeCode:" + stakeCode);
                break;
            case "jclq":
                StringBuilder chuanlianSb2 = new StringBuilder();
                String chuanlianStr2;//串联方式
                int totalCount2 = dingdanBean.getTotalCount();//总注数
                String detailCode2 = "";//详细号码

                for (int i = 0; i < chuanlianList.size(); i++) {
                    if (i != chuanlianList.size() - 1) {
                        chuanlianSb2.append(chuanlianList.get(i) + ",");
                    } else {
                        chuanlianSb2.append(chuanlianList.get(i));
                    }
                }
                chuanlianStr = chuanlianSb2.toString();
                chuanlianStr = chuanlianStr.replace('串', 'x');
                for (int i = 0; i < dingdanItems.size(); i++) {
                    int matchNo = dingdanItems.get(i).getMatchNo();
                    String optionValue = dingdanItems.get(i).getOptionValue();
                    boolean isDan = dingdanItems.get(i).isDan();
                    String staktType = "";
                    if (isDan) {
                        detailCode2 += "d";
                    }
                    //二选一和混合过关的stakeCode与其他方式不一样
                    if ("jlhh".equals(stakeLotid)) {
                        String paramsLotid = dingdanItems.get(i).getLotId();
                        staktType = "_" + paramsLotid;
                    }
                    if (i != dingdanItems.size() - 1) {
                        detailCode2 += matchNo + staktType + "$" + DingDanStringUtils.getBkStakeNumber(optionValue) +
                                "#";
                    } else {
                        detailCode2 += matchNo + staktType + "$" + DingDanStringUtils.getBkStakeNumber(optionValue);
                    }
                }
                stakeCode = chuanlianStr + "-" + totalCount2 + "-" + detailCode2;
                LogUtils.i("payment 投注参数是   mJcType：" + mJcType + "::::" + "lotId:" + stakeLotid + "     issueNo:" +
                        issueNo + "   multiple:" + multiple + "   stackeAdd:" + stackeAdd + "  totalMoney:" +
                        totalMoney + "  chuanlianList :" + chuanlianList + ":::stakeCode:" + stakeCode);
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
        if (BaseApplication.getCurrentLotId().equals("jczq")) {
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
                    stakeLotid = "jzxspf";
                    break;
            }
        } else if (BaseApplication.getCurrentLotId().equals("jclq")) {
            switch (jcType) {
                case Configure_JC.TAB_DXF:
                    stakeLotid = "jldx";
                    break;
                case Configure_JC.TAB_SF:
                    stakeLotid = "jlsf";
                    break;
                case Configure_JC.TAB_SFC:
                    stakeLotid = "jlfc";
                    break;
                case Configure_JC.TAB_HHGG_BK:
                    stakeLotid = "jlhh";
                    break;
                case Configure_JC.TAB_XSF:
                    stakeLotid = "jlrsf";
                    break;
            }
        }
        LogUtils.i("getStakeLotId:" + jcType + "::::" + stakeLotid);
        return stakeLotid;
    }


    @Override
    public void onSucess(VcodeBean o) {
        if (o != null) {
            if (o.getRet() == 100) {
                UIUtils.showToastSafe("保存成功!");
                mDingDanDatas.clear();
                mDingDanMixDatas.clear();
                handleBack();
            } else {
                UIUtils.showToastSafe(o.getMsg());
            }
        }
    }

    @Override
    public void onError() {

    }
}
