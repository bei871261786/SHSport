package shlottery.gov.cn.lotterycenter.ui.nublottery.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.LottoSelectData;
import shlottery.gov.cn.lotterycenter.callback.CaculateAnsyTask;
import shlottery.gov.cn.lotterycenter.callback.CaculateResulCallback;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.LottoActivity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.LottoDingdanActivity;
import shlottery.gov.cn.lotterycenter.utils.DialogUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/21 13:30
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public abstract class BaseNumberFragment extends BaseFragment implements CaculateResulCallback {
    protected abstract void clearData();

    private CaculateAnsyTask mAnsyTask;
    protected TextView mTotalCountTxt;
    protected TextView mTotalMoneyTxt;
    protected String mFucationType;
    protected MyListener mListener;
    protected TextView mClear;
    protected TextView mSubmit;
    protected LinearLayout mShake;
    protected ArrayList<LottoSelectData> mQianquData = new ArrayList<>();
    protected ArrayList<LottoSelectData> mHouquData = new ArrayList<>();
    protected boolean isCanPay = false;//没完成计算情况下，禁止跳转订单
    protected long mTotalCount;
    protected int mCurrentPlayType = 0;
    protected boolean isCanSub = false;


    public boolean hasSelected() {
        for (int i = 0; i < mQianquData.size(); i++) {
            if (mQianquData.get(i).isSelected()) {
                return true;
            }
        }
            for (int j = 0; j < mHouquData.size(); j++) {
                if (mHouquData.get(j).isSelected()) {
                    return true;
                }
            }
            return false;
        }

    protected String getIssueNo() {
        LottoActivity activity = (LottoActivity) getActivity();
        return activity.getmIssueNo();
    }

    public BaseNumberFragment() {
    }


    protected class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.baselottery_deleteall_tv:
                    clearData();
                    break;
                case R.id.baselottery_sum_bt:
                    if (checkDingdan()) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), LottoDingdanActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("redData", sortDanData(mQianquData));
                        bundle.putSerializable("blueData", sortDanData(mHouquData));
                        bundle.putLong("count", mTotalCount);
                        bundle.putString("issueno", getIssueNo());
                        bundle.putInt("mCurrentPlayType", mCurrentPlayType);
                        intent.putExtras(bundle);
                        LogUtils.i("mFucationType:" + mFucationType);
                        if ("dingdan".equals(mFucationType)) {
                            getActivity().setResult(Activity.RESULT_OK, intent);
                        } else {
                            startActivity(intent);
                        }
                        getActivity().finish();
                    } else {
                    }
                    break;
                case R.id.shake_layout:
                    shake();
                    break;
                case R.id.lotto_tuoma_copyright:
                    DialogUtils.ShowXieYiDialog(getActivity());
                    break;
            }
        }
    }

    protected void shake() {
    }

    protected abstract boolean checkDingdan();

    protected void updateBounds(List<BaseBean> dataRed, List<BaseBean> dataBlue) {
        isCanPay = false;
        if (mAnsyTask != null) {
            mAnsyTask.cancel(true);
        }
        mAnsyTask = new CaculateAnsyTask(this);
        mAnsyTask.execute(dataRed, dataBlue);
    }

    @Override
    public void updateBounds(BigInteger bounts) {
        mTotalCount = Long.valueOf(bounts.toString());
        mTotalCountTxt.setText("共" + bounts.toString() + "注");
        String totalMoneyStr = bounts.multiply(new BigInteger("2")) + "元";
        String totalMoney = bounts.multiply(new BigInteger("2")).toString();
        TextUtils.setStrColor(mTotalMoneyTxt, totalMoneyStr, totalMoney, getResources().getColor(R.color.select_red));
        isCanPay = true;
    }

    private ArrayList<LottoSelectData> sortDanData(ArrayList<LottoSelectData> data) {
        ArrayList<LottoSelectData> danData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isDan()) {
                danData.add(data.get(i));
                data.remove(i);
                i--;
            }
        }
        data.addAll(0, danData);
        return data;
    }
}
