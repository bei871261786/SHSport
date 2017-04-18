package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

/**
 * Created by booob on 2016-08-31-0031.
 */
public class JcScoreFilterGridViewAdapter extends BaseAdapter {

    // 用来控制CheckBox的选中状况
    private HashMap<Integer, Boolean> isSelected;
    private Context mContext;
    private List<String> mLeagueList;//联赛名
    private Handler handler;
    List<String> mLeagueListChecked;

    public JcScoreFilterGridViewAdapter(Context mContext, List<String> mLeagueList, Handler handler, List<String> mLeagueListChecked) {
        this.mContext = mContext;
        this.mLeagueList = mLeagueList;
        this.handler = handler;
        this.mLeagueListChecked = mLeagueListChecked;
        isSelected = new HashMap<>();
        // 初始化数据
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate() {
        if ( mLeagueListChecked.size() > 0) {
            for (int i = 0; i < mLeagueList.size(); i++) {
                if (mLeagueListChecked.contains(mLeagueList.get(i))) {
                    getIsSelected().put(i, true);
                } else {
                    getIsSelected().put(i, false);
                }
            }
        }else {
            for (int i = 0; i < mLeagueList.size(); i++) {
                    getIsSelected().put(i, true);
            }
        }
    }

    @Override
    public int getCount() {
        if (null != mLeagueList) {
            return mLeagueList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != mLeagueList) {
            return mLeagueList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder mHolder = null;
        if (null == convertView) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            convertView = mInflater.inflate(R.layout.football_listview_item_flitrate, null);
            mHolder = new Holder();
            mHolder.matchName = (CheckBox) convertView.findViewById(R.id.flitrate_matchName_cb);
            convertView.setTag(mHolder);
        } else {
            mHolder = (Holder) convertView.getTag();
        }
        mHolder.matchName.setText(StringUtils.splitString(mLeagueList.get(position), ",", 0));
        if (null != getIsSelected()) {
            mHolder.matchName.setChecked(getIsSelected().get(position));
        }
        mHolder.matchName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getIsSelected().put(position, true);
                } else {
                    getIsSelected().put(position, false);
                }
                handler.sendEmptyMessage(0);
            }
        });
        return convertView;
    }


    public HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        this.isSelected = isSelected;
    }

    class Holder {
        CheckBox matchName;
    }
}
