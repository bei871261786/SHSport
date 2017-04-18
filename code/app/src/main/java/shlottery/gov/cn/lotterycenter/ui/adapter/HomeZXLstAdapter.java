package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.HomeBean;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-20-0020 10:09
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class HomeZXLstAdapter extends BaseAdapter {

    private HomeBean homeBean;

    public HomeZXLstAdapter(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    @Override
    public int getCount() {
        if (null != homeBean && homeBean.getData().getNews().size() > 0) {
            if (homeBean.getData().getNews().size() >= 4) {
                return 4;
            } else {
                return homeBean.getData().getNews().size();
            }
        }
        return 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LogUtils.i("HomeZXListAdapter getview:"+homeBean.getData().getNews().get(position).getTitle()+"::::"+homeBean.getData().getNews().get(position).getTags());
        if (convertView == null) {
            convertView = UIUtils.inflate(R.layout.homenews__item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.newscontentTv.setText(homeBean.getData().getNews().get(position).getTitle());
        Picasso.with(BaseApplication.getApplication()).load(homeBean.getData().getNews().get(position).getPicUrl()).into(viewHolder.newsImageIm);
        if (StringUtils.isEmpty(homeBean.getData().getNews().get(position).getTags())) {
            LogUtils.i("HomeZXListAdapter getview tagGone:"+homeBean.getData().getNews().get(position).getTags());
            viewHolder.tag1Tv.setVisibility(View.GONE);
            viewHolder.tag2Tv.setVisibility(View.GONE);
        } else {
            if (homeBean.getData().getNews().get(position).getTags().contains(",")) {
                String[] arr = homeBean.getData().getNews().get(position).getTags().split("\\,");
                viewHolder.tag1Tv.setVisibility(View.VISIBLE);
                viewHolder.tag2Tv.setVisibility(View.VISIBLE);
                viewHolder.tag1Tv.setText(arr[0]);
                viewHolder.tag2Tv.setText(arr[1]);
                LogUtils.i("HomeZXListAdapter getview setTags::::"+arr[0]+":::"+arr[1]);
            }else {
                viewHolder.tag1Tv.setVisibility(View.VISIBLE);
                viewHolder.tag2Tv.setVisibility(View.GONE);
                viewHolder.tag1Tv.setText(homeBean.getData().getNews().get(position).getTags());
                LogUtils.i("HomeZXListAdapter getview setTag:"+homeBean.getData().getNews().get(position).getTags()+":::getText:"+  viewHolder.tag1Tv.getText()+"::::"+viewHolder.tag2Tv.getText());
            }
        }
        viewHolder.newsTimeTv.setText(homeBean.getData().getNews().get(position).getNewsTime());
        return convertView;
    }

    static class ViewHolder {
        TextView newscontentTv;
        TextView tag1Tv;
        TextView tag2Tv;
        TextView newsTimeTv;
        RelativeLayout newsLl;
        ImageView newsImageIm;

        ViewHolder(View view) {
            newscontentTv= (TextView) view.findViewById(R.id.newscontent_tv);
            tag1Tv= (TextView) view.findViewById(R.id.tag1_tv);
            tag2Tv= (TextView) view.findViewById(R.id.tag2_tv);
            newsTimeTv= (TextView) view.findViewById(R.id.news_time_tv);
            newsLl= (RelativeLayout) view.findViewById(R.id.news_ll);
            newsImageIm= (ImageView) view.findViewById(R.id.news_image_im);
        }
    }
}
