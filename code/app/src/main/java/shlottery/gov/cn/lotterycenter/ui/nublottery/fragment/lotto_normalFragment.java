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

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

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
import shlottery.gov.cn.lotterycenter.utils.VibratorUtil;

import static shlottery.gov.cn.lotterycenter.Base.Configure.LOCATION_DEFAULE;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/18 15:02
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class lotto_normalFragment extends BaseNumberFragment {
    private LottoGridViewAdapter mQianquAdapter;
    private LottoGridViewAdapter mHouquAdapter;
    private GridViewWithoutScroll lottoQianqu;
    private GridViewWithoutScroll lottoHouqu;
    private ShakeUtils mShakeUtils;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Object red = mQianquData;
            Object blue = mHouquData;
            List<BaseBean> dataRed = (List<BaseBean>) red;
            List<BaseBean> dataBlue = (List<BaseBean>) blue;
            LogUtils.i("handleMessage ");
            if (checkSubmit()) {
                LogUtils.i("handleMessage can");
                mSubmit.setEnabled(true);
            } else {
                LogUtils.i("handleMessage not can");
                mSubmit.setEnabled(false);
            }
            mHouquAdapter.notifyDataSetChanged();
            mQianquAdapter.notifyDataSetChanged();
            updateBounds(dataRed, dataBlue);
        }
    };

    public lotto_normalFragment() {

    }

    @SuppressLint("ValidFragment")
    public lotto_normalFragment(ShakeUtils mShakeUtils, String mFucationType) {
        this.mShakeUtils = mShakeUtils;
        this.mFucationType = mFucationType;
        LogUtils.i("lotto_normalFragment:" + mFucationType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        View view = View.inflate(getActivity(), R.layout.fragment_lotto_normal, null);
        lottoQianqu = (GridViewWithoutScroll) view.findViewById(R.id.lotto_normal_qianqu);
        lottoHouqu = (GridViewWithoutScroll) view.findViewById(R.id.lotto_normal_houqu);
        mClear = (TextView) view.findViewById(R.id.baselottery_deleteall_tv);
        mSubmit = (TextView) view.findViewById(R.id.baselottery_sum_bt);
        mTotalCountTxt = (TextView) view.findViewById(R.id.baselottery_total_tv);
        mTotalMoneyTxt = (TextView) view.findViewById(R.id.baselottery_money_tv);
        mShake = (LinearLayout) view.findViewById(R.id.shake_layout);
        mClear.setOnClickListener(mListener);
        mSubmit.setOnClickListener(mListener);
        mShake.setOnClickListener(mListener);
        mSubmit.setEnabled(false);
        setAdapter(lottoQianqu, mQianquAdapter);
        setAdapter(lottoHouqu, mHouquAdapter);
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
        mCurrentPlayType = 0;
        initData();
        mListener = new MyListener();
        mQianquAdapter = new LottoGridViewAdapter(getActivity(), LOCATION_DEFAULE, mQianquData, true, true, handler);
        mHouquAdapter = new LottoGridViewAdapter(getActivity(), LOCATION_DEFAULE, mHouquData, false, true, handler);
        mShakeUtils.setOnShakeListener(new ShakeUtils.OnShakeListener() {
            @Override
            public void onShake() {
                LogUtils.i("shake !!!!");
                shake();
            }
        });
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
            bean.setDan(false);
            bean.setIsRedArea(true);
            bean.setMinCount(5);
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
            bean.setMsg(j + i);
            bean.setDan(false);
            bean.setMinCount(2);
            bean.setIsRedArea(false);
            bean.setLocation(LOCATION_DEFAULE);
            mHouquData.add(bean);
        }
    }

    @Override
    protected boolean checkDingdan() {
        boolean isCan = true;
        int qianCount = 0;
        int houCount = 0;
        for (int i = 0; i < mQianquData.size(); i++) {
            if (mQianquData.get(i).isSelected()) {
                qianCount++;
            }
        }

        for (int i = 0; i < mHouquData.size(); i++) {
            if (mHouquData.get(i).isSelected()) {
                houCount++;
            }
        }

        if (qianCount < 5 || houCount < 2) {
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
        for (int i = 0; i < mQianquData.size(); i++) {
            if (mQianquData.get(i).isSelected()) {
                qianCount++;
            }
        }

        for (int i = 0; i < mHouquData.size(); i++) {
            if (mHouquData.get(i).isSelected()) {
                houCount++;
            }
        }

        if (qianCount < 5 || houCount < 2) {
            isCan = false;
            UIUtils.showToastSafe("请正确选号");
        }

        if (mTotalCount > 10000) {
            isCan = false;
            UIUtils.showToastSafe("注数不能超过一万");
        }
        return isCan;
    }

    public void sendMsg() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataRed", mQianquData);
        bundle.putSerializable("dataBlue", mHouquData);
        Message msg = new Message();
        msg.obj = bundle;
        handler.sendMessage(msg);
    }

    private int getSelectedCount(List<LottoSelectData> data, int location) {
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected() && data.get(i).getLocation() == location) {
                count++;
            }
        }
        return count;
    }

    protected void clearData() {
        initData();
        sendMsg();
    }

    protected void shake() {
        VibratorUtil.Vibrate(getActivity(), 500);
        initData();
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
            mQianquData.get(it.next()).setSelected(true);
        }
        Iterator<Integer> it2 = blueData.iterator();
        while (it2.hasNext()) {
            mHouquData.get(it2.next()).setSelected(true);
        }
        LogUtils.i("getRandomList:" + getSelectedCount(mHouquData, Configure.LOCATION_DEFAULE) + "::::" + getSelectedCount(mQianquData, Configure.LOCATION_DEFAULE));
        sendMsg();
    }
    @Override
    protected String getTitle() {
        return "大乐透普通";
    }
}
