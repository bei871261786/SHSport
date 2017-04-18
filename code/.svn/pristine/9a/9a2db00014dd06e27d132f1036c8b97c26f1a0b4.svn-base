package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.JingCaiLanqiuInfo;
import shlottery.gov.cn.lotterycenter.bean.Jincai.JingCaiZuqiuInfo;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.protool.BaseProtocol;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.FB_DingDanActivity;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.JInCaiBaseActivity;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.MatchViewPagerAdapter;
import shlottery.gov.cn.lotterycenter.utils.EventBusUtil;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * Created by yongchao.bei on 2016/8/19.
 */
public abstract class JcBaseFragment extends BaseFragment2<MatchsBean> {
    protected JingCaiZuqiuInfo.DataBean  mSoccerDatas;
    protected JingCaiLanqiuInfo.DataBean mBasketballerDatas;
    protected List<MatchsBean>           mGuoguanDatas;
    protected List<MatchsBean>           mDanGuanDatas;
    protected MatchsBean                 mMatchsBean;
    protected List<String> mMyMatchDays = new ArrayList<String>();
    protected BaseExpandableListAdapter mGuoGuanAdapter;
    protected BaseExpandableListAdapter mDanGuanAdapter;
    protected MatchViewPagerAdapter     viewPagerAdapter;
    protected List<View> mViewList = new ArrayList<>();
    protected ViewPager          viewPager;
    protected View               mIndicatorGuoGuan;
    protected View               mIndicatorDanGuan;
    protected TextView           mQueren_button;
    protected TextView           mDeleteAll_button;
    protected TextView           mMatchTotal;
    protected TextView           mMatchLeast;
    protected ExpandableListView mGuoguanListView;
    protected ExpandableListView mDanguanListView;
    protected SwipeRefreshLayout mRefresh_Guoguan;
    protected SwipeRefreshLayout mRefresh_Danguan;
    protected List<MatchsBean> mDanGuanSelectList = new ArrayList<>();
    protected List<MatchsBean> mGuoGuanSelectList = new ArrayList<>();
    protected int              mSelectPosition    = 0;//viewpager的位置,0为过关,1为单关
    //protected final int mSpsSize = 9;
    protected BaseProtocol mProtocol;
    protected View         view;
    protected MyListener   mListener;
    private   TextView     mIndicatorDan;
    private   TextView     mIndicatorGuon;
    private   TextView     mIndicatorDanSub;
    private   TextView     mIndicatorGuonSub;
    //    private int mType;

    public abstract View getView();

    public abstract String getSubLotId();

    public abstract int getJcType();

    public abstract BaseProtocol getProtocal();

    public abstract BaseExpandableListAdapter getMyAdapter(Context context, List<List<MatchsBean>>
            mFilterGuoGuanList, Handler handler);

    protected void init() {
        //        mType = getActivity().getIntent().getExtras().getInt("mType");
        LogUtils.i("fragment data view:" + mFilterGuoGuanList.size() + "::" + mFilterDanGuanList.size() + ":::");
        mFilterGuoGuanList = JcDataUtils.getFlatrateData(callback.getFlitrateData(), mGuoGuanList, mFilterGuoGuanList);
        mFilterDanGuanList = JcDataUtils.getFlatrateData(callback.getFlitrateData(), mDanGuanList, mFilterDanGuanList);
        handler.sendEmptyMessage(0);
    }

    protected void initSoccerData(Object data) {
        JingCaiZuqiuInfo.DataBean loadData = (JingCaiZuqiuInfo.DataBean) data;
        if (null != loadData && null != loadData.getMatchs()) {
            mSoccerDatas = loadData;
            //加载比赛信息到竞猜activity中
            callback.setFlitrateData(false, JcDataUtils.loadFlitrateData(mSoccerDatas));
         /*
        获取比赛的时间集合
         */
            LogUtils.i("page 加载开始 1");
            if (mSoccerDatas != null && null != mSoccerDatas.getMatchs()) {
                //获取彩票的开奖时间集合去除重复的时间
                for (int i = 0; i < mSoccerDatas.getMatchs().size(); i++) {
                    mMyMatchDays.add(mSoccerDatas.getMatchs().get(i).getMatchDay());
                }
                Log.e("mMyMatchDays", mMyMatchDays.size() + "");
                for (int i = 0; i < mMyMatchDays.size() - 1; i++) {
                    for (int j = mMyMatchDays.size() - 1; j > i; j--) {
                        if (mMyMatchDays.get(j).equals(mMyMatchDays.get(i))) {
                            mMyMatchDays.remove(j);
                        }
                    }
                }
                /**
                 * 根据获取的数据判断是否为胜平负;然后将数据分类,分为单关集合和过关集合
                 */
                String subLotId = getSubLotId();
                LogUtils.i("loaddata:" + mSoccerDatas);
                mDanGuanDatas = JcDataUtils.getDanGuanData(subLotId, mSoccerDatas);
                mGuoguanDatas = JcDataUtils.getGuoGuanData(subLotId, mSoccerDatas);
                /**
                 * 根据时间将过关和单关数据根据时间分类,方便expandablelistview加载
                 */
                if (mDanGuanDatas != null && mMyMatchDays.size() > 0) {
                    for (int m = 0; m < mMyMatchDays.size(); m++) {
                        List<MatchsBean> list = new ArrayList<>();
                        for (int n = 0; n < mDanGuanDatas.size(); n++) {
                            if (!mDanGuanDatas.get(n).getMatchDay().isEmpty() && mDanGuanDatas.get(n).getMatchDay()
                                    .equals(mMyMatchDays.get(m))) {
                                list.add(mDanGuanDatas.get(n));
                            }
                        }
                        if (!list.isEmpty()) {
                            mDanGuanList.add(list);
                        }

                        JcDataUtils.removeAllSelected(mDanGuanList);
                        LogUtils.i("danguan长度" + mDanGuanList.size());
                    }
                }
                if (mGuoguanDatas != null && mMyMatchDays.size() > 0) {
                    for (int j = 0; j < mMyMatchDays.size(); j++) {
                        List<MatchsBean> list = new ArrayList<>();
                        for (int i = 0; i < mGuoguanDatas.size(); i++) {
                            LogUtils.i("jcBaseFragment matchDay:" + mGuoguanDatas.get(i).getMatchDay() + "::::" +
                                    mMyMatchDays.get(j));
                            if (mGuoguanDatas.get(i).getMatchDay() != null && mGuoguanDatas.get(i).getMatchDay()
                                    .equals(mMyMatchDays.get(j))) {
                                list.add(mGuoguanDatas.get(i));
                            }
                        }
                        LogUtils.i("guoguan child长度" + list.size());
                        if (!list.isEmpty()) {
                            mGuoGuanList.add(list);
                        }
                        JcDataUtils.removeAllSelected(mGuoGuanList);
                        LogUtils.i("guoguan长度" + mGuoGuanList.size());
                    }
                }
            }
        }
    }


    @Override
    protected void registEnevtBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void unRegistEnevtBus() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void handleMessge(Message msg) {
        LogUtils.i("onEventMainThread handler spf");
        if (mSelectPosition == 1) {
            mFilterDanGuanList = JcDataUtils.getFlatrateData(callback.getFlitrateData(), mDanGuanList,
                    mFilterDanGuanList);
            switch (msg.what) {
                case Configure_JC.HANDLER_NORMAL:
                    int selectedLength = setmSelectedMatchsBeans(mDanGuanList).size();
                    mMatchTotal.setText("已选择" + selectedLength + "场比赛");
                    if (selectedLength != 0) {
                        mMatchLeast.setVisibility(View.GONE);
                    } else if (selectedLength == 0) {
                        mMatchLeast.setVisibility(View.VISIBLE);
                        mMatchLeast.setText("(请至少选择1场比赛)");
                    }
                    if (selectedLength > 8 || selectedLength < 1) {
                        mQueren_button.setEnabled(false);
                    } else {
                        mQueren_button.setEnabled(true);
                    }
                    break;
                case Configure_JC.HANDLER_INIT:
                    //在加载布局之前,加载数据之后筛选数据
                    break;
            }
            if (null != mDanGuanAdapter) {
                LogUtils.i("fragment handler notify");
                mDanGuanAdapter.notifyDataSetChanged();
            }
        } else {
            mFilterGuoGuanList = JcDataUtils.getFlatrateData(callback.getFlitrateData(), mGuoGuanList,
                    mFilterGuoGuanList);
            LogUtils.i("fragment handler adapter:" + mGuoGuanAdapter);
            switch (msg.what) {
                case Configure_JC.HANDLER_NORMAL:
                    int selectedLength = setmSelectedMatchsBeans(mGuoGuanList).size();
                    mMatchTotal.setText("已选择" + selectedLength + "场比赛");
                    if (selectedLength > 1) {
                        mMatchLeast.setVisibility(View.GONE);
                    } else if (selectedLength < 2) {
                        mMatchLeast.setVisibility(View.VISIBLE);
                        mMatchLeast.setText("(请至少选择2场比赛)");
                    }
                    if (selectedLength > 8 || selectedLength < 2) {
                        mQueren_button.setEnabled(false);
                    } else {
                        mQueren_button.setEnabled(true);
                    }
                    break;
                case Configure_JC.HANDLER_INIT:
                    //在加载布局之前,加载数据之后筛选数据
                    break;
            }
            if (null != mGuoGuanAdapter) {
                LogUtils.i("fragment handler notify");
                mGuoGuanAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = getView();
        LogUtils.e("getActivity是：" + getActivity());
        View view1 = inflater.inflate(R.layout.listviewempty, null);
        View view2 = inflater.inflate(R.layout.listviewempty, null);
        mGuoguanListView = (ExpandableListView) view1.findViewById(R.id.mlistview);
        mDanguanListView = (ExpandableListView) view2.findViewById(R.id.mlistview);
        viewPager = (ViewPager) view.findViewById(R.id.football_base_viewpager);
        LinearLayout mLinearLayoutGuoGuan = (LinearLayout) view.findViewById(R.id.football_base_guoguan);
        LinearLayout mLinearLayoutDanGuan = (LinearLayout) view.findViewById(R.id.football_base_danguan_ll);
        mIndicatorGuoGuan = view.findViewById(R.id.football_base_guoguan_select_view);
        mIndicatorDanGuan = view.findViewById(R.id.football_base_danguan_view);
        mDeleteAll_button = (TextView) view.findViewById(R.id.baselottery_deleteall_tv);//清空
        mMatchTotal = (TextView) view.findViewById(R.id.baseBottom_matchCount_txt);//选择了几场比赛
        mMatchLeast = (TextView) view.findViewById(R.id.baseBottom_sub_txt);//最少选择
        mQueren_button = (TextView) view.findViewById(R.id.baselottery_sum_bt);//确认按钮
        mRefresh_Guoguan = (SwipeRefreshLayout) view1.findViewById(R.id.refresh_layout);//下拉刷新
        mRefresh_Danguan = (SwipeRefreshLayout) view2.findViewById(R.id.refresh_layout);//下拉刷新
        mIndicatorDan = (TextView) view.findViewById(R.id.football_base_danguan_tv);
        mIndicatorGuon = (TextView) view.findViewById(R.id.football_base_guoguan_tv);
        mIndicatorDanSub = (TextView) view.findViewById(R.id.bfootball_base_danguantiaojian_tv);
        mIndicatorGuonSub = (TextView) view.findViewById(R.id.bfootball_base_guoguantiaojian_tv);

        mRefresh_Guoguan.setColorSchemeResources(R.color.homeblue);
        mRefresh_Danguan.setColorSchemeResources(R.color.homeblue);
        mGuoGuanAdapter = getMyAdapter(getContext(), mFilterGuoGuanList, handler);
        mDanGuanAdapter = getMyAdapter(getContext(), mFilterDanGuanList, handler);
        mListener = new MyListener();

        mGuoguanListView.setAdapter(mGuoGuanAdapter);
        mGuoguanListView.setGroupIndicator(null);//去掉默认箭头
        mDanguanListView.setAdapter(mDanGuanAdapter);
        mDanguanListView.setGroupIndicator(null);
        mViewList.add(view1);
        mViewList.add(view2);
        mLinearLayoutDanGuan.setOnClickListener(new MyOnViewPagerListener(1));
        mLinearLayoutGuoGuan.setOnClickListener(new MyOnViewPagerListener(0));
        viewPagerAdapter = new MatchViewPagerAdapter(mViewList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(mListener);
        mDeleteAll_button.setOnClickListener(mListener);
        mQueren_button.setOnClickListener(mListener);
        mRefresh_Guoguan.setOnRefreshListener(mListener);
        mRefresh_Danguan.setOnRefreshListener(mListener);
        mGuoGuanAdapter.notifyDataSetChanged();
        return view;
    }

    //处理订单返回信息的eventbus
    @Subscribe
    public void onEventMainThread(EventBusUtil.DingdanResultEvent event) {
        String msg = "onEventMainThread收到了消息：";
        LogUtils.i("eventbus" + msg);
        int type = event.getType();
        int subType = event.getSubType();
        if (type == Configure_JC.TYPE_SOCCER) {
            //足球
            switch (subType) {
                case Configure_JC.TAB_BF:
                    if (!(this instanceof BiFenFragment)) {
                        return;
                    }
                    break;
                case Configure_JC.TAB_BQC:
                    if (!(this instanceof BanQuanChangFragment)) {
                        return;
                    }
                    break;
                case Configure_JC.TAB_RQSFP:
                    if (!(this instanceof RangQiuShengPingFuFragment)) {
                        return;
                    }
                    break;
                case Configure_JC.TAB_SPF:
                    if (!(this instanceof ShengPingfuFragment)) {
                        return;
                    }
                    break;
                case Configure_JC.TAB_ZJQ:
                    if (!(this instanceof ZongJinQiuFragment)) {
                        return;
                    }
                    break;

                default:
                    break;

            }
        } else if (type == Configure_JC.TYPE_BASKETBALL) {
            //篮球
            switch (subType) {
                case Configure_JC.TAB_SF:
                    if (!(this instanceof ShenguFragment)) {
                        return;
                    }
                    break;
                case Configure_JC.TAB_SFC:
                    if (!(this instanceof ShenFenChaFragment)) {
                        return;
                    }
                    break;
                case Configure_JC.TAB_XSF:
                    if (!(this instanceof RangFenShengfuFragment)) {
                        return;
                    }
                    break;
                case Configure_JC.TAB_DXF:
                    if (!(this instanceof DaXiaoFenFragment)) {
                        return;
                    }
                    break;

                default:
                    break;

            }
        }

        List<MatchsBean> dataByDingdan = event.getDingdanData();
        String guoguanfangshi = event.getDingdanGuoguanfangshi();
        LogUtils.i("eventbus getData" + dataByDingdan.size());
        if (guoguanfangshi.equals("guoguan")) {
            JcDataUtils.removeAllSelected(mGuoGuanList);
            for (int k = 0; k < dataByDingdan.size(); k++) {
                for (int i = 0; i < mGuoGuanList.size(); i++) {
                    for (int j = 0; j < mGuoGuanList.get(i).size(); j++) {
                        if (JcDataUtils.compareData(dataByDingdan.get(k), mGuoGuanList.get(i).get(j))) {
                            mGuoGuanList.get(i).remove(j);
                            mGuoGuanList.get(i).add(j, dataByDingdan.get(k));
                        }
                    }
                }
            }
            mGuoGuanAdapter.notifyDataSetChanged();
        } else {
            if (guoguanfangshi.equals("danguan")) {
                JcDataUtils.removeAllSelected(mDanGuanList);
                for (int k = 0; k < dataByDingdan.size(); k++) {
                    for (int i = 0; i < mDanGuanList.size(); i++) {
                        for (int j = 0; j < mDanGuanList.get(i).size(); j++) {
                            if (JcDataUtils.compareData(dataByDingdan.get(k), mDanGuanList.get(i).get(j))) {
                                mDanGuanList.get(i).remove(j);
                                mDanGuanList.get(i).add(j, dataByDingdan.get(k));
                            }
                        }
                    }
                }
            }
        }
        handler.sendEmptyMessage(0);
    }

    // 处理筛选返回信息的eventbus
    @Subscribe
    public void onEventMainThread(EventBusUtil.FilterResultEvent event) {
        callback.setFlitrateData(true, event.getFilterMatch());
        LogUtils.i("fragment data:" + mFilterGuoGuanList.size() + "::" + "::" + mFilterDanGuanList.size() + ":::");
        handler.sendEmptyMessage(0);
    }

    //viewpager指示器的点击事件监听器
    private class MyOnViewPagerListener implements View.OnClickListener {
        private int index = 0;

        public MyOnViewPagerListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }

    private void refresh() {
        refreshLoad();
    }

    private class MyListener implements View.OnClickListener, ViewPager.OnPageChangeListener, SwipeRefreshLayout
            .OnRefreshListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.baselottery_sum_bt:
                    if (setmSelectedMatchsBeans(mGuoGuanList).size() < 2 && mSelectPosition == 0) {
                        UIUtils.showToastSafe("请至少选择2场比赛");
                    } else if (setmSelectedMatchsBeans(mDanGuanList).size() < 1 && mSelectPosition == 1) {
                        UIUtils.showToastSafe("请至少选择1场比赛");
                    } else {
                        Intent intent = new Intent(getActivity(),
                                FB_DingDanActivity.class);
                        Bundle bundle = new Bundle();
                        JInCaiBaseActivity activity = (JInCaiBaseActivity) getActivity();
                        int type = activity.getType();
                        String titleNmae = activity.getTitleName();
                        LogUtils.i("jcbaseFragment type:" + type);
                        if (mSelectPosition == 0) {
                            bundle.putSerializable("mSelectHashMap", (Serializable) setmSelectedMatchsBeans
                                    (mGuoGuanList));
                            bundle.putString("guoguanfangshi", "guoguan");
                        } else {
                            bundle.putSerializable("mSelectHashMap", (Serializable) setmSelectedMatchsBeans
                                    (mDanGuanList));
                            bundle.putString("guoguanfangshi", "danguan");
                        }
                        bundle.putInt("mType", type);
                        bundle.putInt("mSubType", getJcType());
                        bundle.putString("mTitleName", titleNmae);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 0);
                    }
                    break;
                case R.id.baselottery_deleteall_tv:
                    if (mSelectPosition == 0) {
                        mGuoGuanSelectList = setmSelectedMatchsBeans(mGuoGuanList);
                        JcDataUtils.removeListAllSelected(mGuoGuanSelectList);
                        mGuoGuanAdapter.notifyDataSetChanged();
                    } else {
                        mDanGuanSelectList = setmSelectedMatchsBeans(mDanGuanList);
                        JcDataUtils.removeListAllSelected(mDanGuanSelectList);
                        mDanGuanAdapter.notifyDataSetChanged();
                    }
                    //跟新下方有机场的信息
                    handler.sendEmptyMessage(0);
                    break;
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }


        @Override
        public void onPageSelected(int position) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            LogUtils.i("viewpager send message");
            message.setData(bundle);
            mSelectPosition = position;
            handler.sendMessage(message);
            if (position == 0) {
                mIndicatorDanGuan.setVisibility(View.GONE);
                mIndicatorGuoGuan.setVisibility(View.VISIBLE);
                mIndicatorDan.setEnabled(false);
                mIndicatorDanSub.setEnabled(false);
                mIndicatorGuon.setEnabled(true);
                mIndicatorGuonSub.setEnabled(true);
            } else if (position == 1) {
                mIndicatorDanGuan.setVisibility(View.VISIBLE);
                mIndicatorGuoGuan.setVisibility(View.GONE);
                mIndicatorDan.setEnabled(true);
                mIndicatorDanSub.setEnabled(true);
                mIndicatorGuon.setEnabled(false);
                mIndicatorGuonSub.setEnabled(false);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onRefresh() {
            LogUtils.i("refresh begin...");
            refresh();
        }
    }

    //得到选中的数据
    public List<MatchsBean> setmSelectedMatchsBeans(List<List<MatchsBean>> mMatchsBeans) {
        List<MatchsBean> list = new ArrayList<>();
        for (int i = 0; i < mMatchsBeans.size(); i++) {
            for (int j = 0; j < mMatchsBeans.get(i).size(); j++) {
                if (null != JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j)) && JcDataUtils.getSpsStateMap
                        (mMatchsBeans.get(i).get(j)).containsValue(true)) {
                    list.add(mMatchsBeans.get(i).get(j));
                }
            }
        }
        return list;
    }

    @Override
    protected boolean beginLoad() {
        mRefresh_Guoguan.setRefreshing(true);
        mRefresh_Danguan.setRefreshing(true);
        mProtocol = getProtocal();
        LogUtils.i("page 加载开始");
        mDanGuanDatas = new ArrayList<>();
        mSoccerDatas = new JingCaiZuqiuInfo.DataBean();
        mProtocol.load(this, this);
        return true;
    }

    @Override
    protected void handleSucess(Object loadDatas) {
        JingCaiZuqiuInfo.DataBean loadData = (JingCaiZuqiuInfo.DataBean) loadDatas;
        if (null != loadData) {
            mGuoGuanList.clear();
            mDanGuanList.clear();
            initSoccerData(loadData);
            mFilterGuoGuanList = JcDataUtils.getFlatrateData(callback.getFlitrateData(), mGuoGuanList,
                    mFilterGuoGuanList);
            mFilterDanGuanList = JcDataUtils.getFlatrateData(callback.getFlitrateData(), mDanGuanList,
                    mFilterDanGuanList);
            JcDataUtils.removeAllSelected(mFilterGuoGuanList);
            JcDataUtils.removeAllSelected(mFilterDanGuanList);


            if (!mGuoGuanList.isEmpty()) {
                for (int j = 0; j < mGuoGuanList.size(); j++) {
                    mGuoguanListView.expandGroup(j);//默认展开groupitem
                }
            }
            LogUtils.i("refresh guoguan");
            if (null != mGuoGuanAdapter) {
                mGuoGuanAdapter.notifyDataSetChanged();

            }
            if (!mDanGuanList.isEmpty()) {
                for (int i = 0; i < mDanGuanList.size(); i++) {
                    mDanguanListView.expandGroup(i);//默认展开groupitem
                }
            }
            LogUtils.i("refresh danguan");
            if (null != mDanGuanAdapter) {
                mDanGuanAdapter.notifyDataSetChanged();
            }
            handler.sendEmptyMessage(0);
            LogUtils.i("refresh over");
        }
        mRefresh_Guoguan.setRefreshing(false);
        mRefresh_Danguan.setRefreshing(false);
    }

    @Override
    protected void handleError() {
        mRefresh_Guoguan.setRefreshing(false);
        mRefresh_Danguan.setRefreshing(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //返回码为0不执行，返回码为1当前页面finish
        if (requestCode == 0 && resultCode == 1) {
            getActivity().finish();
        }
    }
}
