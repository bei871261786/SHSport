package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.LottoSelectData;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

import static shlottery.gov.cn.lotterycenter.Base.Configure.LOCATION_DANMA;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/17 13:25
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class LottoGridViewAdapter extends BaseAdapter {

    private List<LottoSelectData> mNumList = new ArrayList<>();
    private Context context;
    private int mLocation;
    private boolean isQianqu;
    private int mMaxCount = 0;
    private Handler handler;
    private boolean isNormal;


    public LottoGridViewAdapter(Context context, int location, List<LottoSelectData> mNumList, boolean isQianqu, boolean isNormal, Handler handler) {
        this.context = context;
        this.mLocation = location;
        this.mNumList = mNumList;
        this.handler = handler;
        this.isQianqu = isQianqu;
        this.isNormal = isNormal;
        init();
    }

    private void init() {

    }

    public void setMax(int mMaxCount) {
        this.mMaxCount = mMaxCount;
    }

    public int getMax() {
        return mMaxCount;
    }

    public List<LottoSelectData> getData() {
        return mNumList;
    }

    public boolean toggleSelected(int position) {
        boolean isSelected = mNumList.get(position).isSelected();
        mNumList.get(position).setSelected(!isSelected);
        return true;
    }

    public boolean toggleSelected(int position, boolean isCan) {
        int location = mNumList.get(position).getLocation();
        //自己取消
        if (location == mLocation) {
            if (mLocation == Configure.LOCATION_DANMA) {
                mNumList.get(position).setDan(false);
            }
            mNumList.get(position).setSelected(false);
            mNumList.get(position).setLocation(Configure.LOCATION_DEFAULE);
        }
        //另一方取消或者第一次点击
        else if (isCan) {
            if (mLocation == Configure.LOCATION_DANMA) {
                mNumList.get(position).setDan(true);
            }
            if (location == Configure.LOCATION_DANMA) {
                mNumList.get(position).setDan(false);
            }
            mNumList.get(position).setSelected(true);
            mNumList.get(position).setLocation(mLocation);
        }
        LogUtils.i("adapter isselected:" + mNumList.get(position).isSelected() + "::::" + mMaxCount + "::::");
        return true;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return mNumList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mNumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view;
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            if (!isQianqu) {
                view = View.inflate(context, R.layout.number_item_circle_blue, null);
            } else {
                view = View.inflate(context, R.layout.number_item_circle_red, null);
            }
            holder.mOptionCb = (CheckBox) view.findViewById(R.id.syxw_num_tv);
            holder.mOptionCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isNormal) {
                        toggleSelected(position);
                    } else {
                        boolean isCan = false;
                        if (getMax() > getSelectedCount(getData(), LOCATION_DANMA) || getMax() == 0) {
                            isCan = true;
                        }
                        toggleSelected(position, isCan);
                    }
                    handler.sendEmptyMessage(0);
                }
            });
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (Holder) view.getTag();
        }
        holder.mOptionCb.setText(mNumList.get(position).getMsg());
        setBackground(holder.mOptionCb, position);
        return view;
    }

    private void setBackground(CheckBox syxwnumTv, int position) {
        if (!isQianqu) {
            if (mNumList.get(position).isSelected() && (mLocation == mNumList.get(position).getLocation())) {
                syxwnumTv.setChecked(true);
            } else {
                syxwnumTv.setChecked(false);
            }

        } else {
            if (mNumList.get(position).isSelected() && (mLocation == mNumList.get(position).getLocation())) {
                syxwnumTv.setChecked(true);
            } else {
                syxwnumTv.setChecked(false);
            }
        }
    }

    private int getSelectedCount(List<LottoSelectData> data, int location) {
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected() && data.get(i).getLocation() == location) {
                count++;
            }
        }
        LogUtils.i("lottoadapter selectCount:" + count);
        return count;
    }

    private class Holder {
        CheckBox mOptionCb;
    }

}