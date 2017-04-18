package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.VoucherBean;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.VoucherPaotocal;
import shlottery.gov.cn.lotterycenter.ui.activity.WebViewActivity;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadDataScrollController;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/18 16:00
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class AccountVoucherFragment extends BaseFragment2<VoucherBean> implements LoadDataScrollController.OnRecycleRefreshListener {
    private TextView mTotalCount;
    private RecyclerView mRecycleView;
    private ArrayList<VoucherBean.DataBean.ListBean> mData = new ArrayList<>();
    private VoucherBean.DataBean mLoadData;
    private MyAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LoadDataScrollController mController;
    private int mPageCount;
    private int mPageNo = 1;
    private int mPageSize = 20;
    private boolean isLoadMore = false;
    private TextView mEmptyView;
    private String mUrl = "";

    @Override
    protected void init() {
        mAdapter = new MyAdapter();
        mController = new LoadDataScrollController(this);
    }

    @Override
    protected void handleMessge(Message msg) {
    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_voucher, null);
        mTotalCount = (TextView) view.findViewById(R.id.account_voucher_totalCount);
        mRecycleView = (RecyclerView) view.findViewById(R.id.account_voucher_recyclerview);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.account_voucher_refreshLayout);
        mEmptyView = (TextView) view.findViewById(R.id.empty_view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        mRecycleView.addOnScrollListener(mController);
        mRefreshLayout.setOnRefreshListener(mController);
        return view;
    }

    @Override
    protected boolean beginLoad() {
        VoucherPaotocal protocal = new VoucherPaotocal(getActivity());
        protocal.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
        return false;
    }

    @Override
    protected void handleSucess(Object loadData) {
        LogUtils.i("accountVoucherfragmnt handleSucess");
        if (!isLoadMore) {
            mData.clear();
        }
        if (null != loadData) {
            VoucherBean bean = (VoucherBean) loadData;
            if (null != bean.getData() && null != bean.getData().getList()) {
                mPageCount = bean.getData().getPageCount();
                mData.addAll(bean.getData().getList());
                mLoadData = bean.getData();
                if (mData != null && !mData.isEmpty()) {
                    mEmptyView.setVisibility(View.GONE);
                } else {
                    mEmptyView.setVisibility(View.VISIBLE);
                }
                mAdapter.notifyDataSetChanged();
                int voucherCount = bean.getData().getCount();
                if (isAdded()) {
                    TextUtils.setStrColor(mTotalCount, "总数：" + voucherCount + "张", voucherCount + "", getResources().getColor(R.color.select_red));
                }
            }
        }
        mRefreshLayout.setRefreshing(false);
        mController.setLoadDataStatus(false);
    }

    @Override
    protected void handleError() {
        LogUtils.i("accountVoucherfragmnt handleError");
        mRefreshLayout.setRefreshing(false);
        mController.setLoadDataStatus(false);
    }

    @Override
    public void refresh() {
        isLoadMore = false;
        mPageNo = 1;
        LogUtils.i("accountVoucherfragmnt refresh" + mPageNo);
        refreshLoad();
    }

    @Override
    public void loadMore() {
        isLoadMore = true;
        mRefreshLayout.setRefreshing(true);
        mPageNo++;
        if (mPageNo > mPageCount) {
            mPageNo--;
            UIUtils.showToastSafe("没有更多数据了");
            mRefreshLayout.setRefreshing(false);
        } else {
            refreshLoad();
        }
        LogUtils.i("accountVoucherfragmnt loadMore" + mPageNo);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(BaseApplication.getApplication()).inflate(R.layout.item_voucher, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            LogUtils.i("voucher onBindViewHolder " + mData.get(position).getVoucherName());
            holder.type.setText(mData.get(position).getVoucherName());
            holder.time.setText("失效时间：" + mData.get(position).getValidDate());
            holder.money.setText(mData.get(position).getVoucherMoney() + "");
            final String url = mLoadData.getDetailUrl();
            final String code = mData.get(position).getVoucherCode();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.layout.getLayoutParams();
            if (mData.get(position).getStatus() == 3 || mData.get(position).getStatus() == 2) {
                holder.view.setEnabled(false);
                holder.type.setEnabled(false);
                holder.time.setEnabled(false);
                holder.money.setEnabled(false);
                holder.tab.setEnabled(false);
                holder.yuan.setEnabled(false);
                params.setMargins(0, 0, 10, 0);
                holder.img.setVisibility(View.VISIBLE);
                if (mData.get(position).getStatus() == 2) {
                    holder.img.setImageResource(R.mipmap.voucher_used);
                } else {
                    holder.img.setImageResource(R.mipmap.voucher_useless);
                }
            } else {
                holder.view.setEnabled(true);
                holder.type.setEnabled(true);
                holder.time.setEnabled(true);
                holder.tab.setEnabled(true);
                holder.yuan.setEnabled(true);
                holder.money.setEnabled(true);
                params.setMargins(0, 0, 0, 0);
                holder.img.setVisibility(View.GONE);
            }
            holder.layout.setLayoutParams(params);
            holder.view.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                                                   Bundle bundle = new Bundle();
                                                   bundle.putString("mUrl", url);
                                                   bundle.putInt("mType", 1);
                                                   bundle.putString("mCode", code);
                                                   intent.putExtras(bundle);
                                                   startActivity(intent);
                                               }
                                           }
            );
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            private TextView type;
            private TextView money;
            private TextView time;
            private TextView yuan;
            private ImageView img;
            private View view;
            private View tab;
            private LinearLayout layout;


            public MyHolder(View itemView) {
                super(itemView);
                view = itemView;
                type = (TextView) itemView.findViewById(R.id.voucher_item_type);
                yuan = (TextView) itemView.findViewById(R.id.voucher_item_yuan);
                money = (TextView) itemView.findViewById(R.id.voucher_item_money);
                time = (TextView) itemView.findViewById(R.id.voucher_item_time);
                img = (ImageView) itemView.findViewById(R.id.voucher_item_img);
                tab = itemView.findViewById(R.id.voucher_tab);
                layout = (LinearLayout) itemView.findViewById(R.id.voucher_layout);
            }
        }
    }

    @Override
    protected String getTitle() {
        return "代金券";
    }

}
