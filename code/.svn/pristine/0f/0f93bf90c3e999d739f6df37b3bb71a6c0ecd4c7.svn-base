package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import org.greenrobot.eventbus.EventBus;

import shlottery.gov.cn.lotterycenter.R;import shlottery.gov.cn.lotterycenter.bean.Jincai.DingDanSelectBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.DingDanGridViewChangeEventBus;
import shlottery.gov.cn.lotterycenter.utils.DingDanStringUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by booob on 2016-06-16-0016.
 */
public class DingDanGridviewAdapter extends BaseAdapter {
    private Context context;
    //    private int length;
//   private  int length;
//    private String[] mGuoGuanChuanLianArr;
//    private String[] mDanGuanChuanLianArr;
    private String mGuoGuanFangShi;
    private DingDanSelectBean mDingDanSelectBean;

    public DingDanGridviewAdapter(Context context, String mGuoGuanFangShi, DingDanSelectBean mDingDanSelectBean) {
        LogUtils.i("dingdan  gridviewadapter");
        this.context = context;
//        this.length = mDingDanSelectBean.getmSelectChuanLian().size();
        this.mGuoGuanFangShi = mGuoGuanFangShi;
//        mGuoGuanChuanLianArr = new String[]{"2串1", "3串1", "4串1", "5串1", "6串1", "7串1", "8串1"};
//        mDanGuanChuanLianArr = new String[]{"单关", "2串1", "3串1", "4串1", "5串1", "6串1", "7串1", "8串1"};
        // intSelectBean();
        this.mDingDanSelectBean = mDingDanSelectBean;
    }


    @Override
    public int getCount() {
        LogUtils.i("dingdan  gridviewadapter size:" + mDingDanSelectBean.getmSelectChuanLian().size());
        if (mDingDanSelectBean.getmSelectChuanLian().size() <= 8) {
            return mDingDanSelectBean.getmSelectChuanLian().size();
        } else {
            return 7;
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
        LogUtils.i("dingdan  gridviewadapter size:" + mDingDanSelectBean.getmSelectChuanLian().size() + ":::" + position);
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.dingdan_gridviewitem_chuanlian, null);
        } else {
            view = convertView;
        }
        CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.chuanlian_cbx_d);
        if (mGuoGuanFangShi.equals("guoguan")) {
            mCheckBox.setText(DingDanStringUtils.getChuanlianStringText(position));
        } else {
            mCheckBox.setText((DingDanStringUtils.getChuanlianStringText(position)));
        }
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mDingDanSelectBean.getmSelectMoreChuanLian().size(); i++) {
                        mDingDanSelectBean.getmSelectMoreChuanLian().set(i, Boolean.FALSE);
                    }
                    mDingDanSelectBean.getmSelectChuanLian().set(position, Boolean.TRUE);
                } else {
                    mDingDanSelectBean.getmSelectChuanLian().set(position, Boolean.FALSE);

                }
            }
        });
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DingDanGridViewChangeEventBus("GridViewAdapterChange"));
            }
        });
        LogUtils.i("dingdan  gridviewadapter size" + mDingDanSelectBean.getmSelectChuanLian().size());
        mCheckBox.setChecked(mDingDanSelectBean.getmSelectChuanLian().get(position));
        return view;
    }
}
