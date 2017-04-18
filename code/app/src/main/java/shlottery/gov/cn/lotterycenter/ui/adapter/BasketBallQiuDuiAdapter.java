package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BasketBallDetailBean;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-14-0014 10:28
 * 描    述：篮球赛事球员技术数据左边的适配器
 * 修订历史：
 * ================================================
 */

public class BasketBallQiuDuiAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;//主队数据
    private List<String> list1;//title名字
    private List<String> list2;//客队数据

    public BasketBallQiuDuiAdapter(Context context, List<String> list, List<String> list1, List<String> list2) {
        super();
        this.context = context;
        this.list = list;
        this.list1 = list1;
        this.list2 = list2;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.basketball_qiudui_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.qiuduhostiNumTv.setText(list.get(position));
        viewHolder.qiuduiTitleTv.setText(list1.get(position));
        viewHolder.qiuduiVisitTv.setText(list2.get(position));
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.qiuduhosti_num_tv)
        TextView qiuduhostiNumTv;
        @BindView(R.id.qiudui_title_tv)
        TextView qiuduiTitleTv;
        @BindView(R.id.qiudui_visit_tv)
        TextView qiuduiVisitTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
