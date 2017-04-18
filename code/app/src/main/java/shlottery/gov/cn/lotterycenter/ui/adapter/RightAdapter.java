package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BasketBallDetailBean;
import shlottery.gov.cn.lotterycenter.bean.RightModel;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-14-0014 10:04
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class RightAdapter extends BaseAdapter {
    private Context context;
    private List<RightModel> list;
    private BasketBallDetailBean basketballdetailbean;
    private int type;

    public RightAdapter(Context context, List<RightModel> list,BasketBallDetailBean basketballdetailbean,int type) {
        this.context = context;
        this.list = list;
        this.basketballdetailbean=basketballdetailbean;
        this.type=type;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.layout_right_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.basTimeTv.setText(list.get(position).getText1());
        viewHolder.basMimgzhongTv.setText(list.get(position).getText2());
        viewHolder.basSanfenTv.setText(list.get(position).getText3());
        viewHolder.basDefenTv.setText(list.get(position).getText4());
        viewHolder.basLanbanTv.setText(list.get(position).getText5());
        viewHolder.basZhugongTv.setText(list.get(position).getText6());

        viewHolder.basShiwuTv.setText(list.get(position).getText7());
        viewHolder.basQiangduanTv.setText(list.get(position).getText8());
        viewHolder.basGaimaoTv.setText(list.get(position).getText9());
        viewHolder.basFanguiTv.setText(list.get(position).getText10());

        if (type == 0) {//主队
            if (basketballdetailbean.getData().getHostPersonStat().get(position).getSt().equals("2")) {
                convertView.setBackgroundResource(R.drawable.tablerow_gray_bg);
            } else {
                convertView.setBackgroundResource(R.drawable.tablerow_white_bg);
            }
        } else {//客队
            if (basketballdetailbean.getData().getVisitPersonStat().get(position).getSt().equals("2")) {
                convertView.setBackgroundResource(R.drawable.tablerow_gray_bg);
            } else {
                convertView.setBackgroundResource(R.drawable.tablerow_white_bg);
            }
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.bas_time_tv)
        TextView basTimeTv;
        @BindView(R.id.bas_mimgzhong_tv)
        TextView basMimgzhongTv;
        @BindView(R.id.bas_sanfen_tv)
        TextView basSanfenTv;
        @BindView(R.id.bas_defen_tv)
        TextView basDefenTv;
        @BindView(R.id.bas_lanban_tv)
        TextView basLanbanTv;
        @BindView(R.id.bas_zhugong_tv)
        TextView basZhugongTv;
        @BindView(R.id.bas_shiwu_tv)
        TextView basShiwuTv;
        @BindView(R.id.bas_qiangduan_tv)
        TextView basQiangduanTv;
        @BindView(R.id.bas_gaimao_tv)
        TextView basGaimaoTv;
        @BindView(R.id.bas_fangui_tv)
        TextView basFanguiTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
