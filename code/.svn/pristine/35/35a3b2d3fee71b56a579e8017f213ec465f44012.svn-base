package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.RmdFamousListBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.FamousProtocol;
import shlottery.gov.cn.lotterycenter.ui.activity.Rmd_famousDetailActivity;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JCDX;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JLRSF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JLSF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JZRSPF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JZSPF;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/13 10:49
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class RmdFamousFragment extends RmdBaseFragment {
    private MyAdapter mAdapter;
    private ArrayList<RmdFamousListBean.DataBean.ListBean> mAdapterData = new ArrayList<>();

    @Override
    protected void prepareInit() {
        mAdapter = new MyAdapter();
    }

    @Override
    protected void prepareCreateView() {
        mRecycleview.setAdapter(mAdapter);
    }

    @Override
    protected boolean beginLoad() {
        LogUtils.i("RmdFragment beginLoad:" + getTags());
        FamousProtocol protocol = new FamousProtocol(getActivity());
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("tags", getTags() + "");
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
        return false;
    }


    @Override
    protected void handleSucess(Object loadData) {
        LogUtils.i("RmdFragment  success :" + loadData);
        mController.setLoadDataStatus(false);
        mRefreshLayout.setRefreshing(false);
        if (loadData != null) {
            RmdFamousListBean listbean = (RmdFamousListBean) loadData;
            if (listbean.getRet() == 100) {
                mPageCount = listbean.getData().getPageCount();
                if (!isLoadMore) {
                    mAdapterData.clear();
                }
                mAdapterData.addAll(listbean.getData().getList());
                mAdapter.notifyDataSetChanged();
                LogUtils.i("RmdFragment  success data:" + mAdapterData.size());
            }
            isLoadMore = false;
        }
    }

    private Bundle getDetailBundle(RmdFamousListBean.DataBean.ListBean bean) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", bean.getId());
        String title = "";
        String lotName = bean.getLotName();
        String lotId = bean.getLotId();
        int activityType = 0;
        LogUtils.i("getDetailBundle" + lotName + ":::" + lotId);
        switch (lotId) {
            case "jldx":
                activityType = ACTIVITYTYPE_JCDX;
                break;
            case "jlrsf":
                activityType = ACTIVITYTYPE_JLRSF;
                break;
            case "jzxspf":
                activityType = ACTIVITYTYPE_JZRSPF;
                break;
            case "jzspf":
                activityType = ACTIVITYTYPE_JZSPF;
                break;
            case "jlsf":
                activityType = ACTIVITYTYPE_JLSF;
                break;
            case "sf14":
                activityType = Configs.ACTIVITYTYPE_R9;
                break;
            case "sf9":
                activityType = Configs.ACTIVITYTYPE_R9;
                break;

            //竞彩足球 半全场
            case "jzbqc":
                activityType = Configs.ACTIVITYTYPE_JZBQC;
                break;
            //竞彩足球 比分
            case "jzbf":
                activityType = Configs.ACTIVITYTYPE_JZBF;
                break;
            //竞彩足球 总进球数
            case "jzjqs":
                activityType = Configs.ACTIVITYTYPE_JZJQS;
                break;
            //竞彩足球 混合过关
            case "jzhh":
                activityType = Configs.ACTIVITYTYPE_JZHH;
                break;

            default:
                break;
        }
        //        if (lotId.equals("jldx")) {
        //            activityType = ACTIVITYTYPE_JCDX;
        //        }
        //        if (lotId.equals("jlrsf")) {
        //            activityType = ACTIVITYTYPE_JLRSF;
        //        }
        //        if (lotId.equals("jzxspf")) {
        //            activityType = ACTIVITYTYPE_JZRSPF;
        //        }
        //        if (lotId.equals("jzspf")) {
        //            activityType = ACTIVITYTYPE_JZSPF;
        //        }
        //        if (lotId.equals("jlsf")) {
        //            activityType = ACTIVITYTYPE_JLSF;
        //        }
        //        if (lotId.equals("sf14") || lotId.equals("sf9")) {
        //            activityType = Configs.ACTIVITYTYPE_R9;
        //        }

        if (lotName.contains("竞彩")) {
            LogUtils.i("getDetailBundle contains jincai");
            title = lotName;
            //            title = title.replace("球", "");
            title = title.replace(" ", "-");
        } else if (lotName.contains("胜负彩")) {
            LogUtils.i("getDetailBundle contains shengfu");
            title = lotName.replace(" ", "");

        }
        title = title + "推荐";
        bundle.putInt(Configs.SFCDINGDAN_INT_KEY, activityType);
        bundle.putString("title", title);
        return bundle;
    }

    @Override
    public void refresh() {
        isLoadMore = false;
        mPageNo = 1;
        beginLoad();
    }

    @Override
    public void loadMore() {
        isLoadMore = true;
        mRefreshLayout.setRefreshing(true);
        mPageNo++;
        LogUtils.i("loadMore:" + mPageNo + ":::" + mPageCount);
        if (mPageNo > mPageCount) {
            isLoadMore = false;
            mPageNo--;
            UIUtils.showToastSafe("没有更多数据了");
            mRefreshLayout.setRefreshing(false);
        } else {
            beginLoad();
        }
        LogUtils.i("RmdFragment loadMore" + mPageNo);
    }

    class MyAdapter extends RecyclerView.Adapter<RmdFamousFragment.MyAdapter.MyHolder> {
        @Override
        public RmdFamousFragment.MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LogUtils.i("RmdFragment adapter onCreateViewHolder:");
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_famouslist, parent, false);
            return new RmdFamousFragment.MyAdapter.MyHolder(view);
        }

        @Override
        public void onBindViewHolder(RmdFamousFragment.MyAdapter.MyHolder holder, final int position) {
            LogUtils.i("RmdFragment adapter onBindViewHolder:" + position + ":::" + mAdapterData.size());
            holder.name.setText(mAdapterData.get(position).getName());
            holder.schemeMoney.setText(mAdapterData.get(position).getTotalMoney() + "");
            double bonusEstimate = mAdapterData.get(position).getBonusEstimate();
            String money = TextUtils.formatMoney(bonusEstimate);
            if (mAdapterData.get(position).getBonusEstimate() != 0) {
                holder.forecastMoney.setText(money);
                holder.forecastMoney.setVisibility(View.VISIBLE);
            } else {
                holder.forecastMoney.setVisibility(View.GONE);
            }

            Resources resource = BaseApplication.getApplication().getResources();
            TextUtils.setStrColor(holder.schemeMoney, "方案金额" + mAdapterData.get(position).getTotalMoney() + "元",
                    mAdapterData.get(position).getTotalMoney() + "", resource.getColor(R.color.select_red));
            TextUtils.setStrColor(holder.forecastMoney, "预测金额" + money + "元", money, resource.getColor(R.color
                    .select_red));
            holder.wanfa.setText(mAdapterData.get(position).getLotName());
            holder.type.setText(mAdapterData.get(position).getPlayType());
            long timeLong = mAdapterData.get(position).getValidTime();
            LogUtils.i("RmdFragment adapter timeLong:" + timeLong + "::::" + DateUtils.formatDateformatTimeSimple
                    (timeLong) + "::::" + DateUtils.formatDateAndTime(timeLong));
            holder.date.setText(DateUtils.formatDateAndTime(timeLong * 1000));
            String url = mAdapterData.get(position).getLogo();
            if (url != null && !url.isEmpty()) {
                Picasso.with(getActivity()).load(url).into(holder.icon);
            }
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), Rmd_famousDetailActivity.class);
                    Bundle bundle = getDetailBundle(mAdapterData.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            LogUtils.i("RmdFragment adapter getItemCount:" + mAdapterData.size());
            return mAdapterData.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView  name;
            TextView  wanfa;
            TextView  type;
            TextView  date;
            TextView  schemeMoney;
            TextView  forecastMoney;
            ImageView icon;
            View      view;

            public MyHolder(View itemView) {
                super(itemView);
                LogUtils.i("RmdFragment adapter MyHolder:");
                icon = (ImageView) itemView.findViewById(R.id.famous_icon);
                name = (TextView) itemView.findViewById(R.id.famous_name);
                wanfa = (TextView) itemView.findViewById(R.id.famous_wanfa);
                type = (TextView) itemView.findViewById(R.id.famous_type);
                date = (TextView) itemView.findViewById(R.id.famous_date);
                schemeMoney = (TextView) itemView.findViewById(R.id.famous_schemeMoney);
                forecastMoney = (TextView) itemView.findViewById(R.id.famous_forecastMoney);
                view = itemView;
            }
        }
    }

    @Override
    protected String getTitle() {
        return "专家看彩列表";
    }
}