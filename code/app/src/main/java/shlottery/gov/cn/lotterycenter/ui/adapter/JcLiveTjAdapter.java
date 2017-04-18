package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.MatchFtDetailBean;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * Created by booob on 2016-09-18-0018.
 */
public class JcLiveTjAdapter extends BaseAdapter {

    //    private Context mContext;
    private LayoutInflater mInflater = null;
    private MatchFtDetailBean mMatchFtDetailBean;
    public List<String> mTjHostData = new ArrayList<>();//统计即时数据集合

    public List<String> getmTjVisitData() {
        return mTjVisitData;
    }

    public void setmTjVisitData(List<String> mTjVisitData) {
        this.mTjVisitData = mTjVisitData;
    }

    public List<String> mTjVisitData = new ArrayList<>();//统计场均数据集合

    public List<String> getmTjHostData() {
        return mTjHostData;
    }

    public void setmTjHostData(List<String> mTjHostData) {
        this.mTjHostData = mTjHostData;
    }


    public JcLiveTjAdapter(MatchFtDetailBean mMatchFtDetailBean) {
        //        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(UIUtils.getContext());
        this.mMatchFtDetailBean = mMatchFtDetailBean;
        init();
    }

    private void init() {
       /* setmTjHostData(mTjHostData);
        setmTjVisitData(mTjVisitData);*/
        //初始化及时数据
        if (null != mMatchFtDetailBean && mMatchFtDetailBean.getData().getStatList() != null && mMatchFtDetailBean
                .getData().getStatList().size() > 0) {
            mTjHostData.clear();
            mTjVisitData.clear();
            for (int i = 0; i < mMatchFtDetailBean.getData().getStatList().size(); i++) {
                MatchFtDetailBean.DataBean.StatListBean mLiveStatisticsDataBean = mMatchFtDetailBean.getData()
                        .getStatList().get(i);
                //            MatchFtDetailBean.AvgStatisticsDataBean mAvgStatisticsDataBean = mMatchFtDetailBean
                // .getAvgStatisticsData().get(0);

                if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName()
                        .equals("射门")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",s");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",s");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("射正")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",sh");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",sh");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("射偏")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",sm");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",sm");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("射门被挡")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",bs");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",bs");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("射门被扑")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",ss");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",ss");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("越位")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",o");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",o");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("犯规")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",f");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",f");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("角球")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",ck");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",ck");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("黄牌")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",yc");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",yc");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("红牌")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",rc");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",rc");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("控球率%")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",c");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",c");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("任意球")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",fk");
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",fk");
                } else if (!StringUtils.isEmpty(mLiveStatisticsDataBean.getName()) && mLiveStatisticsDataBean.getName
                        ().equals("界外球")) {
                    mTjHostData.add(mLiveStatisticsDataBean.getHost() + ",ti");//任意球
                    mTjVisitData.add(mLiveStatisticsDataBean.getVisit() + ",ti");//任意球
                }
            }
        }
    }

    @Override
    public int getCount() {

        if (null != mMatchFtDetailBean && mMatchFtDetailBean.getData().getStatList().size() > 0) {
            return mTjHostData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mTjHostData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.analyselistv_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.livestatisticsTv = (TextView) convertView.findViewById(R.id.livestatistics_tv);
            mViewHolder.hostfullanalyseTv = (TextView) convertView.findViewById(R.id.hostfullanalyse_tv);
            mViewHolder.hosthalfanalyseTv = (TextView) convertView.findViewById(R.id.hosthalfanalyse_tv);
            mViewHolder.visitfullanalyseTv = (TextView) convertView.findViewById(R.id.visitfullanalyse_tv);
            mViewHolder.visithalfanalyseTv = (TextView) convertView.findViewById(R.id.visithalfanalyse_tv);
//            mViewHolder.tvAnalyseHostdata = (TextView) convertView.findViewById(R.id.tv_analyse_hostdata);
//            mViewHolder.tvAnalyseVisitdata = (TextView) convertView.findViewById(R.id.tv_analyse_visitdata);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        //        mViewHolder.livestatisticsTv
        setTjName(StringUtils.splitString(mTjHostData.get(position), ",", 1), mViewHolder.livestatisticsTv);

        //获取屏幕宽度 以及计算需要设置背景的宽度
        float hostvalue = Float.parseFloat(StringUtils.splitString(mTjHostData.get(position), ",", 0));
        float visitvalue = Float.parseFloat(StringUtils.splitString(mTjVisitData.get(position), ",", 0));


        float f = (hostvalue + visitvalue);
        DisplayMetrics dm;
        //取得窗口属性
        dm = UIUtils.getContext().getApplicationContext().getResources().getDisplayMetrics();

        //窗口的宽度
        int screenWidth = dm.widthPixels;
        int width = (screenWidth - UIUtils.dip2px(120)) / 2;
        if (StringUtils.splitString(mTjHostData.get(position), ",", 1).equals("c")) {
            mViewHolder.hostfullanalyseTv.setText(StringUtils.splitString(mTjHostData.get(position), ",", 0) + "%");
            mViewHolder.visitfullanalyseTv.setText(StringUtils.splitString(mTjVisitData.get(position), ",", 0) + "%");
            mViewHolder.hostfullanalyseTv.setWidth((int) (width * hostvalue / f));
            mViewHolder.visitfullanalyseTv.setWidth((int) (width * visitvalue / f));
        } else {
            mViewHolder.hostfullanalyseTv.setText(StringUtils.splitString(mTjHostData.get(position), ",", 0));
            mViewHolder.visitfullanalyseTv.setText(StringUtils.splitString(mTjVisitData.get(position), ",", 0));

            if (hostvalue != 0) {//主队不为0
                if (visitvalue != 0) {//客队不为0
                    if (hostvalue / f > 0.19) {
                        mViewHolder.hostfullanalyseTv.setWidth((int) (width * hostvalue / f));
                    }
                    if (visitvalue / f > 0.19) {
                        mViewHolder.visitfullanalyseTv.setWidth((int) (width * visitvalue / f));
                    }
                } else {//客队为0
                    if (hostvalue / f > 0.19) {
                        mViewHolder.hostfullanalyseTv.setWidth((int) (width * hostvalue / f));
                    }
                }
            } else {//主队为0
                if (visitvalue != 0) {//客队不为0 此时主队宽度不设置,默认宽度
                    if (visitvalue / f > 0.19) {
                        mViewHolder.visitfullanalyseTv.setWidth((int) (width * visitvalue / f));
                    }
                } else {//客队为0

                }
            }
            mViewHolder.hosthalfanalyseTv.setVisibility(View.GONE);
            mViewHolder.visithalfanalyseTv.setVisibility(View.GONE);
            //        mViewHolder.hosthalfanalyseTv
            //        mViewHolder.visithalfanalyseTv}
        }
        return convertView;
    }


    private void setTjName(String s, TextView mTextView) {
        if (s.equals("s")) {
            mTextView.setText("射门");
            return;
        }
        if (s.equals("sh")) {
            mTextView.setText("射正");
        }
        if (s.equals("sm")) {
            mTextView.setText("射偏");
        }
        if (s.equals("bs")) {
            mTextView.setText("射门被挡");
        }
        if (s.equals("ss")) {
            mTextView.setText("射门被扑");
        }
        if (s.equals("o")) {
            mTextView.setText("越位");
        }
        if (s.equals("f")) {
            mTextView.setText("犯规");
        }
        if (s.equals("ck")) {
            mTextView.setText("角球");
        }
        if (s.equals("yc")) {
            mTextView.setText("黄牌");
        }
        if (s.equals("rc")) {
            mTextView.setText("红牌");
        }
        if (s.equals("c")) {
            mTextView.setText("控球率");
        }
        if (s.equals("fk")) {
            mTextView.setText("任意球");
        }
        if (s.equals("ti")) {
            mTextView.setText("界外球");
        }
    }

    class ViewHolder {
        TextView     livestatisticsTv;
        TextView     hostfullanalyseTv;
        TextView     hosthalfanalyseTv;
        LinearLayout hostanalyseLl;
        TextView     visitfullanalyseTv;
        TextView     visithalfanalyseTv;
        LinearLayout visitanalyseLl;
//        TextView     tvAnalyseHostdata;
//        TextView     tvAnalyseVisitdata;
    }
}
