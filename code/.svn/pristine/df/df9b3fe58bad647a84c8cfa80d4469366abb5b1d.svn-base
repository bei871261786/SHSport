package shlottery.gov.cn.lotterycenter.ui.nublottery.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.LottoSelectData;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.LottoGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.Base.Configure.LOCATION_DANMA;
import static shlottery.gov.cn.lotterycenter.Base.Configure.LOCATION_DEFAULE;
import static shlottery.gov.cn.lotterycenter.Base.Configure.LOCATION_TUOMAE;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/18 15:04
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class lotto_dantuoFragment extends BaseNumberFragment {
    private LottoGridViewAdapter mDHAdapter;
    private LottoGridViewAdapter mTQAdapter;
    private LottoGridViewAdapter mTHAdapter;
    private GridViewWithoutScroll lottoDanmaQianqu;
    private GridViewWithoutScroll lottoTuomaQianqu;
    private GridViewWithoutScroll lottoDanmaHouqu;
    private ShakeUtils mShakeUtils;
    private GridViewWithoutScroll lottoTuomaHouqu;
    private LottoGridViewAdapter mDQAdapter;
    private LinearLayout mCopyrightLayout;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Object red = mQianquData;
            Object blue = mHouquData;
            List<BaseBean> dataRed = (List<BaseBean>) red;
            List<BaseBean> dataBlue = (List<BaseBean>) blue;
            if (checkSubmit()) {
                mSubmit.setEnabled(true);
            } else {
                mSubmit.setEnabled(false);
            }
            updateBounds(dataRed, dataBlue);
            mDHAdapter.notifyDataSetChanged();
            mDQAdapter.notifyDataSetChanged();
            mTHAdapter.notifyDataSetChanged();
            mTQAdapter.notifyDataSetChanged();
        }
    };

    public lotto_dantuoFragment() {
    }

    @Override
    protected boolean checkDingdan() {
        boolean isCan = true;
        int qianCount = 0;
        int houCount = 0;
        int danQianCount = 0;
        int tuoQianCount = 0;
        int tuoHouCount = 0;
        for (int i = 0; i < mQianquData.size(); i++) {
            if (mQianquData.get(i).isSelected()) {
                qianCount++;
                if (mQianquData.get(i).getLocation() == Configure.LOCATION_DANMA) {
                    danQianCount++;
                }
                if (mQianquData.get(i).getLocation() == Configure.LOCATION_TUOMAE) {
                    tuoQianCount++;
                }
            }
        }

        for (int i = 0; i < mHouquData.size(); i++) {
            if (mHouquData.get(i).isSelected()) {
                houCount++;
                if (mHouquData.get(i).getLocation() == Configure.LOCATION_TUOMAE) {
                    tuoHouCount++;
                }
            }
        }
        LogUtils.i("checkDingdan:" + qianCount + ":::" + houCount + ":::" + danQianCount + ":::" + tuoQianCount + ":::" + tuoHouCount);
        if (qianCount < 6 || houCount < 3 || danQianCount < 1 || tuoQianCount < 2 || tuoHouCount < 2) {
            isCan = false;
            UIUtils.showToastSafe("请正确选号");
        }
        if (!isCanPay) {
            isCan = false;
            UIUtils.showToastSafe("请检查网络");
        }
        if (mTotalCount > 10000) {
            isCan = false;
            UIUtils.showToastSafe("注数不能超过一万");
        }
        return isCan;
    }


    protected boolean checkSubmit() {
        boolean isCan = true;
        int qianCount = 0;
        int houCount = 0;
        int danQianCount = 0;
        int tuoQianCount = 0;
        int tuoHouCount = 0;
        for (int i = 0; i < mQianquData.size(); i++) {
            if (mQianquData.get(i).isSelected()) {
                qianCount++;
                if (mQianquData.get(i).getLocation() == Configure.LOCATION_DANMA) {
                    danQianCount++;
                }
                if (mQianquData.get(i).getLocation() == Configure.LOCATION_TUOMAE) {
                    tuoQianCount++;
                }
            }
        }

        for (int i = 0; i < mHouquData.size(); i++) {
            if (mHouquData.get(i).isSelected()) {
                houCount++;
                if (mHouquData.get(i).getLocation() == Configure.LOCATION_TUOMAE) {
                    tuoHouCount++;
                }
            }
        }
        LogUtils.i("checkDingdan:" + qianCount + ":::" + houCount + ":::" + danQianCount + ":::" + tuoQianCount + ":::" + tuoHouCount);
        if (qianCount < 6 || houCount < 3 || danQianCount < 1 || tuoQianCount < 2 || tuoHouCount < 2) {
            isCan = false;
            UIUtils.showToastSafe("请正确选号");
        }

        if (mTotalCount > 10000) {
            isCan = false;
            UIUtils.showToastSafe("注数不能超过一万");
        }
        return isCan;
    }

    @SuppressLint("ValidFragment")
    public lotto_dantuoFragment(ShakeUtils mShakeUtils, String mFucationType) {
        this.mShakeUtils = mShakeUtils;
        this.mFucationType = mFucationType;
        LogUtils.i("lotto_dantuoFragment:" + mFucationType);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        View view = View.inflate(getActivity(), R.layout.fragment_lotto_dantuo, null);
        lottoDanmaQianqu = (GridViewWithoutScroll) view.findViewById(R.id.lotto_danma_qianqu);
        lottoTuomaQianqu = (GridViewWithoutScroll) view.findViewById(R.id.lotto_tuoma_qianqu);
        lottoDanmaHouqu = (GridViewWithoutScroll) view.findViewById(R.id.lotto_danma_houqu);
        lottoTuomaHouqu = (GridViewWithoutScroll) view.findViewById(R.id.lotto_tuoma_houqu);
        mClear = (TextView) view.findViewById(R.id.baselottery_deleteall_tv);
        mSubmit = (TextView) view.findViewById(R.id.baselottery_sum_bt);
        mTotalCountTxt = (TextView) view.findViewById(R.id.baselottery_total_tv);
        mTotalMoneyTxt = (TextView) view.findViewById(R.id.baselottery_money_tv);
        mCopyrightLayout = (LinearLayout) view.findViewById(R.id.lotto_tuoma_copyright);
        mDQAdapter.setMax(4);
        mDHAdapter.setMax(1);
        mClear.setOnClickListener(mListener);
        mSubmit.setOnClickListener(mListener);
        mSubmit.setEnabled(false);
        mCopyrightLayout.setOnClickListener(mListener);
        setAdapter(lottoDanmaQianqu, mDQAdapter);
        setAdapter(lottoTuomaQianqu, mTQAdapter);
        setAdapter(lottoDanmaHouqu, mDHAdapter);
        setAdapter(lottoTuomaHouqu, mTHAdapter);
        return view;
    }

    @Override
    protected View createLoadedView() {
        return null;
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    private void setAdapter(GridViewWithoutScroll g, final LottoGridViewAdapter p) {
        g.setAdapter(p);
    }

    protected void init() {
        mCurrentPlayType = 1;
        initData();
        mListener = new MyListener();
        mDQAdapter = new LottoGridViewAdapter(getActivity(), LOCATION_DANMA, mQianquData, true, false, handler);
        mDHAdapter = new LottoGridViewAdapter(getActivity(), LOCATION_DANMA, mHouquData, false, false, handler);
        mTQAdapter = new LottoGridViewAdapter(getActivity(), LOCATION_TUOMAE, mQianquData, true, false, handler);
        mTHAdapter = new LottoGridViewAdapter(getActivity(), LOCATION_TUOMAE, mHouquData, false, false, handler);
    }

    private void initData() {
        mQianquData.clear();
        mHouquData.clear();
        String j = "";
        for (int i = 1; i <= 35; i++) {
            if (i < 10) {
                j = "0";
            } else {
                j = "";
            }
            LottoSelectData bean = new LottoSelectData();
            bean.setSelected(false);
            bean.setMsg(j + i);
            bean.setMinCount(5);
            bean.setIsRedArea(true);
            bean.setLocation(LOCATION_DEFAULE);
            mQianquData.add(bean);
        }

        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                j = "0";
            } else {
                j = "";
            }
            LottoSelectData bean = new LottoSelectData();
            bean.setSelected(false);
            bean.setMinCount(2);
            bean.setMsg(j + i);
            bean.setIsRedArea(false);
            bean.setLocation(LOCATION_DEFAULE);
            mHouquData.add(bean);
        }
    }

    public void sendMsg() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataRed", mQianquData);
        bundle.putSerializable("dataBlue", mHouquData);
        Message msg = new Message();
        msg.obj = bundle;
        handler.sendMessage(msg);
    }

    protected void clearData() {
        initData();
        sendMsg();
    }
    @Override
    protected String getTitle() {
        return "大乐透胆拖";
    }

}
