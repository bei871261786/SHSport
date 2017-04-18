package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.OrderListBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.DeleteOrderProtocol;
import shlottery.gov.cn.lotterycenter.protool.OrderListProtocol;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadDataScrollController;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.R.id.flitrate_order_sf;

/**
 * 模拟注单页面
 */
public class UsercenterOrderActivity extends BaseActivity implements LoadCallback, LoadDataScrollController
        .OnRecycleRefreshListener {
    private ImageView    iFlitrate;
    private RecyclerView mRecycleview;
    private MyAdapter    mAdapter;
    private ArrayList<OrderListBean.DataBean.ListBean> mDatas    = new ArrayList<>();
    private String                                     mLotId    = "";
    private String                                     mDateFrom = "";
    private String                                     mDateTo   = "";
    private int                                        mPageNo   = 1;
    private String                                     mPageSize = "";
    private ImageView      indicator;
    private MyListener     mListener;
    private PopupWindow    mDeletePopupWindow;
    private PopupWindow    mFlitratePopupWindow;
    private View           mPopContentViw;
    private RelativeLayout mTitlebarLayout;
    private HashMap<String, String>  mFlitrateMap       = new HashMap<>();
    private HashMap<String, Boolean> mCheckedStatuseMap = new HashMap<>();
    private CheckBox                 jinzuTv;
    private CheckBox                 jinlanTv;
    private CheckBox                 sfTv;
    private CheckBox                 ren9Tv;
    private CheckBox                 bqcTv;
    private CheckBox                 jinqiuTv;
    private CheckBox                 sh11x5Tv;
    private CheckBox                 lottoTv;
    private CheckBox                 pl3Tv;
    private CheckBox                 pl5Tv;
    private CheckBox                 qxTv;
    private int                      mDeleteStakeId;
    private int                      mDelatePositopm;
    private SwipeRefreshLayout       mSrlUsercenterOrder;
    private LoadDataScrollController mController;
    private boolean                  mIsLoadMore;
    private int                      mPageCount;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_usercenter_order);
        mSrlUsercenterOrder = (SwipeRefreshLayout) findViewById(R.id.srl_usercenter_order);
        mRecycleview = (RecyclerView) findViewById(R.id.order_recycleview);
        mTitlebarLayout = (RelativeLayout) findViewById(R.id.base_titleBar);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mRecycleview.setAdapter(mAdapter);
        mRecycleview.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
        initFlitratePopView();
        initTitleBar();
        indicator.setOnClickListener(mListener);
        initRefreshLayout();
        beginLoad();
    }

    private void initRefreshLayout() {
        mController = new LoadDataScrollController(this);
        mRecycleview.addOnScrollListener(mController);
        mSrlUsercenterOrder.setOnRefreshListener(mController);
        mSrlUsercenterOrder.setColorSchemeResources(R.color.homeblue);
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView mTitleBar = (TextView) findViewById(R.id.titlebar_tv);
        mTitleBar.setText("模拟注单");
        indicator = (ImageView) findViewById(R.id.titlebar_indicator);
        indicator.setImageResource(R.mipmap.filtrate);
    }

    @Override
    protected void init() {
        mAdapter = new MyAdapter();
        mListener = new MyListener();
    }

    private void beginLoad() {
        OrderListProtocol protocol = new OrderListProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("lotId", mLotId);
                params.put("dateFrom", mDateFrom);
                params.put("dateTo", mDateTo);
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize);
                return params;
            }
        }, this);
    }


    @Override
    public void onSucess(Object o) {
        mController.setLoadDataStatus(false);
        mSrlUsercenterOrder.setRefreshing(false);
        if (null != o) {
            if (o instanceof OrderListBean) {
                OrderListBean orderListBean = (OrderListBean) o;
                if (orderListBean.getRet() == 100) {
                    mPageCount = orderListBean.getData().getPageCount();
                    if (!mIsLoadMore) {
                        mDatas.clear();
                    }
                    mDatas.addAll(orderListBean.getData().getList());
                    LogUtils.i("UserOrdercenter onsuccess" + mDatas.size());
                    mAdapter.notifyDataSetChanged();
                }
                mIsLoadMore = false;
            }

            if (o instanceof VcodeBean) {
                VcodeBean vcodeBean = (VcodeBean) o;
                if (vcodeBean.getRet() == 100) {
                    mDatas.remove(mDelatePositopm);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onError() {
        mSrlUsercenterOrder.setRefreshing(false);
        mController.setLoadDataStatus(false);
        mIsLoadMore = false;
    }

    @Override
    public void refresh() {
        mIsLoadMore = false;
        mPageNo = 1;
        beginLoad();
    }

    @Override
    public void loadMore() {
        mIsLoadMore = true;
        mSrlUsercenterOrder.setRefreshing(true);
        mPageNo++;
        LogUtils.i("loadMore:" + mPageNo + ":::" + mPageCount);
        if (mPageNo > mPageCount) {
            mIsLoadMore = false;
            mPageNo--;
            UIUtils.showToastSafe("没有更多数据了");
            mSrlUsercenterOrder.setRefreshing(false);
        } else {
            beginLoad();
        }

        LogUtils.i("OrderActivity loadMore" + mPageNo);
    }


    private class MyListener implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.titlebar_indicator:
                    createFlitratePop(mTitlebarLayout);
                    break;
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            String lotid = "";
            switch (compoundButton.getId()) {
                case R.id.flitrate_order_jinzu:
                    if (b) {
                        lotid = "jzspf,jzhh,jzxspf,jzbf,jzjqs,jzbqc";
                        mFlitrateMap.put("jinzu", lotid);
                    } else {
                        mFlitrateMap.remove("jinzu");
                    }
                    break;
                case R.id.flitrate_order_jinlan:
                    if (b) {
                        lotid = "jlsf,jlrsf,jldx,jlfc,jlhh";
                        mFlitrateMap.put("jinlan", lotid);
                    } else {
                        mFlitrateMap.remove("jinlan");
                    }
                    break;
                case R.id.flitrate_order_sf:
                    if (b) {
                        lotid = "sf14";
                        mFlitrateMap.put("sf", lotid);
                    } else {
                        mFlitrateMap.remove("sf");
                    }
                    break;
                case R.id.flitrate_order_ren9:
                    if (b) {
                        lotid = "sf9";
                        mFlitrateMap.put("sf9", lotid);
                    } else {
                        mFlitrateMap.remove("sf9");
                    }
                    break;
                case R.id.flitrate_order_bqc:
                    if (b) {
                        lotid = "bqc";
                        mFlitrateMap.put("bqc", lotid);
                    } else {
                        mFlitrateMap.remove("bqc");
                    }
                    break;
                case R.id.flitrate_order_jqc:
                    if (b) {
                        lotid = "jqc";
                        mFlitrateMap.put("jqc", lotid);
                    } else {
                        mFlitrateMap.remove("jqc");
                    }
                    break;
                case R.id.flitrate_order_11x5:
                    if (b) {
                        lotid = "sh115";
                        mFlitrateMap.put("sh115", lotid);
                    } else {
                        mFlitrateMap.remove("sh115");
                    }
                    break;
                case R.id.flitrate_order_lotto:
                    if (b) {
                        lotid = "dlt";
                        mFlitrateMap.put("dlt", lotid);
                    } else {
                        mFlitrateMap.remove("dlt");
                    }
                    break;
                case R.id.flitrate_order_pl3:
                    if (b) {
                        lotid = "pl3";
                        mFlitrateMap.put("pl3", lotid);
                    } else {
                        mFlitrateMap.remove("pl3");
                    }
                    break;
                case R.id.flitrate_order_pl5:
                    if (b) {
                        lotid = "pl5";
                        mFlitrateMap.put("pl5", lotid);
                    } else {
                        mFlitrateMap.remove("pl5");
                    }
                    break;
                case R.id.flitrate_order_qixing:
                    if (b) {
                        lotid = "qxc";
                        mFlitrateMap.put("qxc", lotid);
                    } else {
                        mFlitrateMap.remove("qxc");

                    }
                    break;
                case R.id.titlebar_back_ll:
                    finish();
                    break;
            }
        }
    }

    private void deleteOrder(final int stakeId) {
        DeleteOrderProtocol protocol = new DeleteOrderProtocol(UsercenterOrderActivity.this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("stakeId", stakeId + "");
                return params;
            }
        }, this);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private View currentView;
        private int checkedPosition = -1;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(UsercenterOrderActivity.this).inflate(R.layout.item_order, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            LogUtils.i("position onBindViewHolder :" + position);
            final OrderListBean.DataBean.ListBean bean = mDatas.get(position);
            holder.issueno.setText("期号：" + bean.getIssueNo());
            if (bean.getLotName().equals("14场")) {
                holder.lotName.setText("胜负彩14场");
            } else {
                holder.lotName.setText(bean.getLotName());
            }

            String ticketCount = bean.getTicketCount() + "";
            String stakeCount = bean.getStakeAmount() + "";
            final Resources resouce = UsercenterOrderActivity.this.getResources();
            TextUtils.setStrColor(holder.ticketcount, ticketCount + "张", ticketCount, resouce.getColor(R.color
                    .select_red));
            TextUtils.setStrColor(holder.stakecount, stakeCount + "注", stakeCount, resouce.getColor(R.color
                    .select_red));

            int isBonus = bean.getIsBonus();
            if (isBonus == 1) {
                holder.bonusLogo.setVisibility(View.INVISIBLE);
            } else {
                holder.bonusLogo.setVisibility(View.INVISIBLE);
            }
            TextUtils.setStrColor(holder.money, "投注金额：" + bean.getStakeMoney() + "元", bean.getStakeMoney() + "",
                    resouce.getColor(R.color.select_red));
            long issuetime = bean.getStakeTime();
            holder.issuetime.setText(DateUtils.formatDateAndTime(issuetime * 1000));
            long stoptime = bean.getValidTime();
            holder.stoptime.setText("截止时间：" + DateUtils.formatDateAndTime(stoptime * 1000));
            Picasso.with(UsercenterOrderActivity.this).load(bean.getLogo()).into(holder.lotteryLogo);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentView == null) {
                        currentView = view;
                        currentView.setBackgroundColor(resouce.getColor(R.color.gray_bglight));
                    } else {
                        currentView.setBackgroundColor(resouce.getColor(R.color.white));
                        currentView = view;
                        currentView.setBackgroundColor(resouce.getColor(R.color.gray_bglight));
                    }
                    checkedPosition = position;
                    Intent intent = new Intent(UsercenterOrderActivity.this, DingdanDetailActivity.class);
                    int lotId = mDatas.get(position).getStakeId();
                    intent.putExtra(Configs.STAKEID_KEY, lotId + "");
                    startActivity(intent);
                }
            });
            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (currentView == null) {
                        currentView = view;
                        currentView.setBackgroundColor(resouce.getColor(R.color.gray_bglight));
                    } else {
                        currentView.setBackgroundColor(resouce.getColor(R.color.white));
                        currentView = view;
                        currentView.setBackgroundColor(resouce.getColor(R.color.gray_bglight));
                    }

                    checkedPosition = position;
                    mDeleteStakeId = bean.getStakeId();
                    createDeletePop(view, holder.getLayoutPosition());
                    return false;
                }
            });
            if (checkedPosition == position) {
                holder.view.setBackgroundColor(resouce.getColor(R.color.gray_bglight));
                currentView = holder.view;
            } else {
                holder.view.setBackgroundColor(resouce.getColor(R.color.white));
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView  issueno;
            TextView  lotName;
            TextView  issuetime;
            TextView  stoptime;
            ImageView lotteryLogo;
            ImageView bonusLogo;
            TextView  ticketcount;
            TextView  stakecount;
            TextView  money;
            View      view;

            public MyViewHolder(View itemView) {
                super(itemView);
                issueno = (TextView) itemView.findViewById(R.id.item_orderlist_issueno);
                lotName = (TextView) itemView.findViewById(R.id.item_orderlist_lotName);
                issuetime = (TextView) itemView.findViewById(R.id.item_orderlist_time);
                stoptime = (TextView) itemView.findViewById(R.id.item_orderlist_stoptime);
                ticketcount = (TextView) itemView.findViewById(R.id.item_orderlist_ticketcount);
                stakecount = (TextView) itemView.findViewById(R.id.item_orderlist_stckecount);
                money = (TextView) itemView.findViewById(R.id.item_orderlist_money);
                lotteryLogo = (ImageView) itemView.findViewById(R.id.item_orderlist_lotteryLogo);
                bonusLogo = (ImageView) itemView.findViewById(R.id.item_orderlist_bonusLogo);
                view = itemView;
            }

        }
    }

    private void createDeletePop(View v, int position) {
        LogUtils.i("position createDeletePop :" + position);
        initDeletePopView(position);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int dalta = (300 - v.getWidth()) / 2;
        Log.e(this.getClass().getName(), ":" + location[0] + "=======" + location[1]);
        mDeletePopupWindow.showAtLocation(v, Gravity.CENTER | Gravity.TOP, location[0], location[1] - 100);
    }

    private void initDeletePopView(final int position) {
        mPopContentViw = LayoutInflater.from(this).inflate(R.layout.popwindow_orderlistdelete, null);
        TextView cancel = (TextView) mPopContentViw.findViewById(R.id.pop_order_cancel);
        TextView delete = (TextView) mPopContentViw.findViewById(R.id.pop_order_delete);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeletePopupWindow.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.i("position initDeletePopView :" + position);
                mDelatePositopm = position;
                deleteOrder(mDeleteStakeId);
                mDeletePopupWindow.dismiss();
            }
        });
        mDeletePopupWindow = new PopupWindow(mPopContentViw, 300, 100);
        mDeletePopupWindow.setOutsideTouchable(true);
        mDeletePopupWindow.setFocusable(true);
        //让pop可以点击外面消失掉
        mDeletePopupWindow.setBackgroundDrawable(new ColorDrawable(0));

    }

    private void createFlitratePop(View v) {
        LogUtils.i("createFlitratePop");
        restoreStatus();
        mFlitratePopupWindow.showAsDropDown(v);
        //        mFlitratePopupWindow.show
    }

    private void initFlitratePopView() {
        View view = View.inflate(this, R.layout.pop_flitrate, null);
        mFlitratePopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        mFlitratePopupWindow.setOutsideTouchable(true);
        mFlitratePopupWindow.setFocusable(true);
        //让pop可以点击外面消失掉
        mFlitratePopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        jinzuTv = (CheckBox) view.findViewById(R.id.flitrate_order_jinzu);
        jinlanTv = (CheckBox) view.findViewById(R.id.flitrate_order_jinlan);
        sfTv = (CheckBox) view.findViewById(flitrate_order_sf);
        ren9Tv = (CheckBox) view.findViewById(R.id.flitrate_order_ren9);
        bqcTv = (CheckBox) view.findViewById(R.id.flitrate_order_bqc);
        jinqiuTv = (CheckBox) view.findViewById(R.id.flitrate_order_jqc);
        sh11x5Tv = (CheckBox) view.findViewById(R.id.flitrate_order_11x5);
        lottoTv = (CheckBox) view.findViewById(R.id.flitrate_order_lotto);
        pl3Tv = (CheckBox) view.findViewById(R.id.flitrate_order_pl3);
        pl5Tv = (CheckBox) view.findViewById(R.id.flitrate_order_pl5);
        qxTv = (CheckBox) view.findViewById(R.id.flitrate_order_qixing);
        Button cancle = (Button) view.findViewById(R.id.base_dialog_cancle_btn);
        Button submit = (Button) view.findViewById(R.id.base_dialog_submit_btn);

        jinzuTv.setOnCheckedChangeListener(mListener);
        jinlanTv.setOnCheckedChangeListener(mListener);
        sfTv.setOnCheckedChangeListener(mListener);
        ren9Tv.setOnCheckedChangeListener(mListener);
        bqcTv.setOnCheckedChangeListener(mListener);
        jinqiuTv.setOnCheckedChangeListener(mListener);
        sh11x5Tv.setOnCheckedChangeListener(mListener);
        lottoTv.setOnCheckedChangeListener(mListener);
        pl3Tv.setOnCheckedChangeListener(mListener);
        pl5Tv.setOnCheckedChangeListener(mListener);
        qxTv.setOnCheckedChangeListener(mListener);
        mCheckedStatuseMap.put("jinzu", false);
        mCheckedStatuseMap.put("jinlan", false);
        mCheckedStatuseMap.put("sf", false);
        mCheckedStatuseMap.put("jinqiu", false);
        mCheckedStatuseMap.put("ren9", false);
        mCheckedStatuseMap.put("bqc", false);
        mCheckedStatuseMap.put("sh115", false);
        mCheckedStatuseMap.put("lotto", false);
        mCheckedStatuseMap.put("pl3", false);
        mCheckedStatuseMap.put("pl5", false);
        mCheckedStatuseMap.put("qixin", false);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFlitratePopupWindow.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Iterator<String> iterator = mFlitrateMap.keySet().iterator();
                mLotId = "";
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    LogUtils.i("popwindow submit key:" + key);
                    mLotId += mFlitrateMap.get(key) + ",";
                }
                LogUtils.i("popwindow submit lotid  1:" + mLotId);
                if (mLotId != null && mLotId.length() > 1) {
                    mLotId = mLotId.substring(0, mLotId.length() - 1);
                }
                LogUtils.i("popwindow submit lotid:" + mLotId);
                saveStatus();
                beginLoad();
                mFlitratePopupWindow.dismiss();
            }
        });
    }

    //保存选中状态
    private void saveStatus() {
        if (jinzuTv.isChecked()) {
            mCheckedStatuseMap.put("jinzu", true);
        } else {
            mCheckedStatuseMap.put("jinzu", false);
        }
        if (jinlanTv.isChecked()) {
            mCheckedStatuseMap.put("jinlan", true);
        } else {
            mCheckedStatuseMap.put("jinlan", false);
        }
        if (sfTv.isChecked()) {
            mCheckedStatuseMap.put("sf", true);
        } else {
            mCheckedStatuseMap.put("sf", false);
        }
        if (ren9Tv.isChecked()) {
            mCheckedStatuseMap.put("ren9", true);
        } else {
            mCheckedStatuseMap.put("ren9", false);
        }
        if (bqcTv.isChecked()) {
            mCheckedStatuseMap.put("bqc", true);
        } else {
            mCheckedStatuseMap.put("bqc", false);
        }
        if (jinqiuTv.isChecked()) {
            mCheckedStatuseMap.put("jinqiu", true);
        } else {
            mCheckedStatuseMap.put("jinqiu", false);
        }
        if (sh11x5Tv.isChecked()) {
            mCheckedStatuseMap.put("sh115", true);
        } else {
            mCheckedStatuseMap.put("sh115", false);
        }
        if (lottoTv.isChecked()) {
            mCheckedStatuseMap.put("lotto", true);
        } else {
            mCheckedStatuseMap.put("lotto", false);
        }
        if (pl3Tv.isChecked()) {
            mCheckedStatuseMap.put("pl3", true);
        } else {
            mCheckedStatuseMap.put("pl3", false);
        }
        if (pl5Tv.isChecked()) {
            mCheckedStatuseMap.put("pl5", true);
        } else {
            mCheckedStatuseMap.put("pl5", false);
        }
        if (qxTv.isChecked()) {
            mCheckedStatuseMap.put("qixin", true);
        } else {
            mCheckedStatuseMap.put("qixin", false);
        }
    }

    //恢复选中状态
    private void restoreStatus() {
        jinzuTv.setChecked(mCheckedStatuseMap.get("jinzu"));
        jinlanTv.setChecked(mCheckedStatuseMap.get("jinlan"));
        sfTv.setChecked(mCheckedStatuseMap.get("sf"));
        ren9Tv.setChecked(mCheckedStatuseMap.get("ren9"));
        bqcTv.setChecked(mCheckedStatuseMap.get("bqc"));
        jinqiuTv.setChecked(mCheckedStatuseMap.get("jinqiu"));
        sh11x5Tv.setChecked(mCheckedStatuseMap.get("sh115"));
        lottoTv.setChecked(mCheckedStatuseMap.get("lotto"));
        pl3Tv.setChecked(mCheckedStatuseMap.get("pl3"));
        pl5Tv.setChecked(mCheckedStatuseMap.get("pl5"));
        qxTv.setChecked(mCheckedStatuseMap.get("qixin"));
    }

    @Override
    protected String getBaidutitle() {
        return "模拟注单";
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
