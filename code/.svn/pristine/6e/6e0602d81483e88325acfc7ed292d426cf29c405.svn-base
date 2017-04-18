package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shlottery.gov.cn.lotterycenter.R;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:53
 * 描    述：homefragment 中的开奖等gridview的适配器
 * 修订历史：
 * ================================================
 */


public class NumGridViewAdapter extends BaseAdapter {

    private List<String> mNumList = new ArrayList<>();//0-9的集合

    public List<String> getmMissList() {
        return mMissList;
    }

    public void setmMissList(List<String> mMissList) {
        this.mMissList = mMissList;
    }

    private List<String> mMissList;
    private Map<Integer, Boolean> selectionMap = new HashMap<>();
    private Context context;
    private int mType = 0;//数字彩种类.0为数字猜中的排列三等0-9,当为1时表示为1-11的十一选五

    public Map<Integer, Boolean> getSelectionMap() {
        return selectionMap;
    }

    public void setSelectionMap(Map<Integer, Boolean> selectionMap) {
        this.selectionMap = selectionMap;
    }

    public NumGridViewAdapter(List<String> mMissList, Context context) {
        this.mMissList = mMissList;
        this.context = context;
        init(mType);
    }

    public NumGridViewAdapter(List<String> mMissList, Context context, int mType) {
        this.mMissList = mMissList;
        this.context = context;
        this.mType = mType;
        init(mType);
    }

    private void init(int mType) {
        mNumList.clear();
        if (mType == 0) {//0-9
            for (int i = 0; i < 10; i++) {
                mNumList.add(i + "");
                selectionMap.put(i, false);
            }
        } else {//十一选5
            for (int i = 1; i < 12; i++) {
                if (i < 10) {
                    mNumList.add("0" + i);
                } else {
                    mNumList.add(i + "");
                }
                selectionMap.put(i - 1, false);
            }
        }

    }

    //排列三等只有十个数字时调用
    public void setSelectMap(int position) {
        if (selectionMap.get(position)) {
            selectionMap.put(position, false);
        } else {
            selectionMap.put(position, true);
        }
    }

    //11选5等是一个数字调用
    public void setSelectMap(int position, int mType) {
        if (mType == 0) {
            if (selectionMap.get(position)) {
                selectionMap.put(position, false);
            } else {
                selectionMap.put(position, true);
            }
        } else {

        }
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mMissList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view;
        if (convertView == null) {
            if (mType == 0) {
                view = View.inflate(context, R.layout.number_item, null);
            } else {
                view = View.inflate(context, R.layout.number_whitebg_item, null);
            }

        } else {
            view = convertView;
        }
        TextView syxwnumTv = (TextView) view.findViewById(R.id.syxw_num_tv);
        syxwnumTv.setText(mNumList.get(position));
        TextView missnumTv = (TextView) view.findViewById(R.id.miss_num_tv);
        missnumTv.setText(mMissList.get(position));
        int miss = -1;
        if (!mMissList.get(position).equals("-") && !mMissList.get(position).equals("") && !mNumList.isEmpty()) {//排除排列五和七星彩中没有遗漏的情况 以及默认显示"-"的情况

            for (int i = 0; i < mMissList.size(); i++) {
                if (!mMissList.get(i).equals("-")) {
                    miss = (Integer.parseInt(mMissList.get(i))) > miss ? (Integer.parseInt(mMissList.get(i))) : miss;
                }
            }
        }
        if (miss > 0 && mMissList.get(position).equals(miss + "")) {
            missnumTv.setTextColor(context.getResources().getColor(R.color.select_red));
        } else {
            missnumTv.setTextColor(context.getResources().getColor(R.color.black_gray));
        }
        if (selectionMap.get(position)) {
            syxwnumTv.setBackgroundResource(R.mipmap.ballselect_red);
            syxwnumTv.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            syxwnumTv.setBackgroundResource(R.mipmap.ball_unselect);
            syxwnumTv.setTextColor(context.getResources().getColor(R.color.select_red));
        }

        return view;
    }
}
