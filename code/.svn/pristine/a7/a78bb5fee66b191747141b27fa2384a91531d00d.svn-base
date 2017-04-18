package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import org.greenrobot.eventbus.EventBus;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingDanSelectBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.DingDanGridViewChangeEventBus;
import shlottery.gov.cn.lotterycenter.utils.DingDanStringUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by booob on 2016-06-16-0016.
 */
public class DingDanGridViewMoreAdapter extends BaseAdapter {
    private Context context;
    private String[][] mChuanLianArr;
    private String mGuoGuanFangShi;
    private DingDanSelectBean mDingDanSelectBean;

    public DingDanGridViewMoreAdapter(Context context, String mGuoGuanFangShi, DingDanSelectBean mDingDanSelectBean) {
        this.context = context;
        this.mGuoGuanFangShi = mGuoGuanFangShi;
        this.mDingDanSelectBean = mDingDanSelectBean;
        mChuanLianArr = DingDanStringUtils.s3;
    }


    @Override
    public int getCount() {
        if (null != mDingDanSelectBean.getmSelectMoreChuanLian() && !mDingDanSelectBean.getmSelectMoreChuanLian().isEmpty()) {
            return mDingDanSelectBean.getmSelectMoreChuanLian().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LogUtils.i("dingdan  gridviewadapter more  size:" + mDingDanSelectBean.getmSelectMoreChuanLian().size() + ":::" + position);
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.dingdan_gridviewitem_chuanlian, null);
        } else {
            view = convertView;
        }
        CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.chuanlian_cbx_d);
        mCheckBox.setText(DingDanStringUtils.getChuanLianMoreStringText(mDingDanSelectBean.getmMoreParentPosition(), position));
        final CheckBox finalcCheckBox = mCheckBox;
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                     if (isChecked) {
                                                         for (int i = 0; i < mDingDanSelectBean.getmSelectChuanLian().size(); i++) {
                                                             mDingDanSelectBean.getmSelectChuanLian().set(i, Boolean.FALSE);
                                                         }
                                                         for (int j = 0; j < mDingDanSelectBean.getmSelectMoreChuanLian().size(); j++) {
                                                             mDingDanSelectBean.getmSelectMoreChuanLian().set(j, Boolean.FALSE);
                                                         }
                                                         mDingDanSelectBean.getmSelectMoreChuanLian().set(position, Boolean.TRUE);
                                                     } else {
                                                         mDingDanSelectBean.getmSelectMoreChuanLian().set(position, Boolean.FALSE);
                                                     }
                                                     for (int i = 0; i < mDingDanSelectBean.getmSelectChuanLian().size(); i++) {
                                                         LogUtils.i(mDingDanSelectBean.getmSelectChuanLian().get(i) + "---" + i + "---" + "mDingDanSelectBean");
                                                     }
                                                     for (int j = 0; j < mDingDanSelectBean.getmSelectMoreChuanLian().size(); j++) {
                                                         LogUtils.i(mDingDanSelectBean.getmSelectMoreChuanLian().get(j) + "--" + j + "---" + "mDingDanMoreSelectBean");
                                                     }
                                                 }

                                             }
        );
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DingDanGridViewChangeEventBus("MoreAdapterChange"));
            }
        });
        mCheckBox.setChecked(mDingDanSelectBean.getmSelectMoreChuanLian().get(position));
        return view;
    }
}
