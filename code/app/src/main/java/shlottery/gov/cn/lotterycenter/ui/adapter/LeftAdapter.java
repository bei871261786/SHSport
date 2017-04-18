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

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-14-0014 10:28
 * 描    述：篮球赛事球员技术数据左边的适配器
 * 修订历史：
 * ================================================
 */

public class LeftAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private List<String> list2;
    private BasketBallDetailBean basketballdetailbean;
    private int type;

    public LeftAdapter(Context context, List<String> list, List<String> list2, BasketBallDetailBean basketballdetailbean, int type) {
        super();
        this.context = context;
        this.list = list;
        this.list2 = list2;
        this.type = type;
        this.basketballdetailbean = basketballdetailbean;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_left_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
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

        setBackGroud(viewHolder.memberTv, list2.get(position));
        viewHolder.leftContainerTextview0.setText(list.get(position));
        return convertView;
    }

    //设置球员的位置以及背景
    private void setBackGroud(TextView textview, String s) {
        if (s != null) {
            switch (s) {
                case "CF"://前锋

                case "SF"://小前锋

                case "PF"://大前锋
                    textview.setText("F");
                    textview.setBackgroundResource(R.mipmap.basketb_blue);
                    break;

                case "G"://后卫

                case "PG"://控卫

                case "SG"://分卫
                    textview.setText("G");
                    textview.setBackgroundResource(R.mipmap.basketball_red);
                    break;

                case "C"://中锋
                    textview.setText("C");
                    textview.setBackgroundResource(R.mipmap.basketball_hese);
                    break;
                default:
                    break;
            }
        }
    }


    class ViewHolder {
        @BindView(R.id.member_tv)
        TextView memberTv;
        @BindView(R.id.left_container_textview0)
        TextView leftContainerTextview0;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
