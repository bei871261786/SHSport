package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.JingCaiZuqiuInfo;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.protool.BaseProtocol;
import shlottery.gov.cn.lotterycenter.protool.JincaiSoccerProtocol;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.FB_DingDanActivity_Mix;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.JInCaiBaseActivity;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.MixGuoGuanExpandableListviewAdapter;
import shlottery.gov.cn.lotterycenter.utils.EventBusUtil;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * Created by booob on 2016-05-26-0026.
 */
public class HunHeGuoGuanFragment extends BaseFragment2 {
    private List<List<HashMap<Integer, MatchsBean>>> mDatas = new ArrayList<>();
    private List<List<HashMap<Integer, MatchsBean>>> mFlitrateDatas = new ArrayList<>();
    protected SwipeRefreshLayout mRefresh_Guoguan;
    private List<MatchsBean> mGuoguanDatas = new ArrayList<>();
    /*
    混合过关中的不同方式的数据map集合
    * */
    private List<String> mMyMatchDays = new ArrayList<String>();
    private MixGuoGuanExpandableListviewAdapter mGuoGuanAdapter;
    private TextView mQueren_button;
    private TextView mDeleteAll_button;
    private TextView mMatchTotal;
    private TextView mMatchLeast;
    private ExpandableListView listview1;
    protected BaseProtocol mProtocol;
    private List<List<MatchsBean>> mGuoGuanList = new ArrayList<>();
    private List<HashMap<Integer, MatchsBean>> mGuoGuanSelectList = new ArrayList<>();
    protected MyListener mListener;

    /*
    混合过关中的不同方式的单关过关expandable数据map集合
    * */
    private HashMap<Integer, List<List<MatchsBean>>> mGuoguanListMap = new HashMap<>();
    /*
   混合过关中的不同方式的单关过关选中数据map集合
   * */
    private HashMap<Integer, List<MatchsBean>> mGuoguanSelectedListMap = new HashMap<>();


    protected void initData(Object data) {
        JingCaiZuqiuInfo.DataBean loadData = (JingCaiZuqiuInfo.DataBean) data;
        List<MatchsBean> guoguanDatas = new ArrayList<>();
          /*
        获取比赛的时间集合
         */
        if (null != loadData && null != loadData.getMatchs()) {
            //加载比赛信息到竞猜activity中
            callback.setFlitrateData(false, JcDataUtils.loadFlitrateData(loadData));
            if (null != loadData.getMatchs()) {
                //获取彩票的开奖时间集合去除重复的时间
                for (int i = 0; i < loadData.getMatchs().size(); i++) {
                    mMyMatchDays.add(loadData.getMatchs().get(i).getMatchDay());
                }
                Log.e("mMyMatchDays", mMyMatchDays.size() + "");
                for (int i = 0; i < mMyMatchDays.size() - 1; i++) {
                    for (int j = mMyMatchDays.size() - 1; j > i; j--) {
                        if (mMyMatchDays.get(j).equals(mMyMatchDays.get(i))) {
                            mMyMatchDays.remove(j);
                        }
                    }
                }
            }
            /**
             * 根据获取的数据判断是否为胜平负;然后将数据分类,分为单关集合和过关集合
             */
            for (int i = 0; i < Configure_JC.FB_HHGG_TYPES.length; i++) {
                guoguanDatas = JcDataUtils.getGuoGuanData(Configure_JC.FB_HHGG_TYPES[i], loadData);
                LogUtils.i("mix fragment getguanData" + mGuoguanDatas.size() + ":::" + Configure_JC.FB_HHGG_TYPES[i]);
                mGuoguanDatas.addAll(guoguanDatas);
            }
            LogUtils.i("mix fragment guoguanData" + mGuoguanDatas.size());
            /**
             * 根据时间将数据根据时间分类,方便expandablelistview加载
             */
            if (mGuoguanDatas != null && mMyMatchDays.size() > 0) {
                for (int j = 0; j < mMyMatchDays.size(); j++) {
                    List<MatchsBean> list = new ArrayList<>();
                    for (int i = 0; i < mGuoguanDatas.size(); i++) {
                        if (mGuoguanDatas.get(i).getMatchDay() != null && mGuoguanDatas.get(i).getMatchDay().equals(mMyMatchDays.get(j))) {
                            list.add(mGuoguanDatas.get(i));
                        }
                    }
                    if (!list.isEmpty()) {
                        LogUtils.i("mix fragment guoguanData add " + list.size());
                        mGuoGuanList.add(list);
                    }
                    JcDataUtils.removeAllSelected(mGuoGuanList);
                    LogUtils.i("mix guoguan长度" + mGuoGuanList.size());
                }
            }
            /**
             * 将相同时间内的比赛再进行处理，如果是一场比赛的话就合并成一个Hashmap
             * */
            List<HashMap<Integer, MatchsBean>> list = null;
            for (int i = 0; i < mGuoGuanList.size(); i++) {
                LogUtils.i("mix lotid " + mGuoGuanList.get(i).get(0).getLotId() + ":::" + mGuoGuanList.get(i).get(0).getHostName());
                list = JcDataUtils.getSameMatch(mGuoGuanList.get(i));
                mDatas.add(list);
            }
        }
    }

    private void removeAllSelected() {
        mGuoGuanSelectList = setmSelectedMatchsBeans(mDatas);
        for (int i = 0; i < mGuoGuanSelectList.size(); i++) {
            JcDataUtils.removeAllSelected(mGuoGuanSelectList.get(i).get(Configure_JC.TAB_RQSFP));
            JcDataUtils.removeAllSelected(mGuoGuanSelectList.get(i).get(Configure_JC.TAB_SPF));
            JcDataUtils.removeAllSelected(mGuoGuanSelectList.get(i).get(Configure_JC.TAB_BF));
            JcDataUtils.removeAllSelected(mGuoGuanSelectList.get(i).get(Configure_JC.TAB_ZJQ));
            JcDataUtils.removeAllSelected(mGuoGuanSelectList.get(i).get(Configure_JC.TAB_BQC));
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
        mFlitrateDatas = JcDataUtils.getMixFlatrateData(callback.getFlitrateData(), mDatas, mFlitrateDatas);
        LogUtils.i("mix handle message");
        switch (msg.what) {
            case Configure_JC.HANDLER_NORMAL:
                int selectedLength = setmSelectedMatchsBeans(mDatas).size();
                mMatchTotal.setText("已选" + selectedLength + "场比赛");
                if (selectedLength != 0) {
                    mMatchLeast.setVisibility(View.GONE);
                } else if (selectedLength == 0) {
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
        LogUtils.i("mix   mdatas:" + mDatas);
        if (null != mGuoGuanAdapter) {
            LogUtils.i("fragment handler notify");
            mGuoGuanAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.inflate(R.layout.football_mix_fragment);
        LogUtils.e("getActivity是：" + getActivity());
        listview1 = (ExpandableListView) view.findViewById(R.id.mix_match_elistview);

        mGuoGuanAdapter = new MixGuoGuanExpandableListviewAdapter(getContext(), mFlitrateDatas, handler);
        listview1.setAdapter(mGuoGuanAdapter);
        mListener = new MyListener();
        mGuoGuanAdapter.notifyDataSetChanged();
        LogUtils.i("mix adapter notify");
        listview1.setGroupIndicator(null);//去掉默认箭头
        LogUtils.e("ji进入总进球fragment5");

        mDeleteAll_button = (TextView) view.findViewById(R.id.mix_delete_all_tv);//清空
        mMatchTotal = (TextView) view.findViewById(R.id.mix_match_total_tv);//选择了几场比赛
        mMatchLeast = (TextView) view.findViewById(R.id.mix_least_tv);//最少选择
        mQueren_button = (TextView) view.findViewById(R.id.mix_queren_button);//确认按钮
        mRefresh_Guoguan = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        mRefresh_Guoguan.setOnRefreshListener(mListener);
        mDeleteAll_button.setOnClickListener(mListener);
        mQueren_button.setOnClickListener(mListener);
        return view;
    }

    protected void init() {
        mFlitrateDatas = JcDataUtils.getMixFlatrateData(callback.getFlitrateData(), mDatas, mFlitrateDatas);
        if (!mFlitrateDatas.isEmpty()) {
            for (int j = 0; j < mFlitrateDatas.size(); j++) {
                listview1.expandGroup(j);//默认展开groupitem
            }
        }
    }

    @Override
    protected boolean beginLoad() {
        LogUtils.i("page 加载开始");
        mProtocol = new JincaiSoccerProtocol(getActivity());
        LogUtils.i("page 加载开始 1");
        mRefresh_Guoguan.setRefreshing(true);
        mProtocol.load(this, this);
        return true;
    }


    @Override
    protected void handleSucess(Object loadData) {
        if (null != loadData) {
            mGuoGuanList.clear();
            initData(loadData);
            mFilterGuoGuanList = JcDataUtils.getFlatrateData(callback.getFlitrateData(), mGuoGuanList, mFilterGuoGuanList);
            LogUtils.i("test childsize:"+mFilterGuoGuanList.get(0));
            mFlitrateDatas = JcDataUtils.getBaskMixFlatrateData(callback.getFlitrateData(), mDatas, mFlitrateDatas);
            JcDataUtils.removeAllSelected(mFilterGuoGuanList);
            LogUtils.i("refresh guoguan");
            if (!mFlitrateDatas.isEmpty()) {
                for (int j = 0; j < mFlitrateDatas.size(); j++) {
                    listview1.expandGroup(j);//默认展开groupitem
                }
            }
            if (null != mGuoGuanAdapter) {
                mGuoGuanAdapter.notifyDataSetChanged();
            }

            handler.sendEmptyMessage(0);
            LogUtils.i("refresh over");
        }
        mRefresh_Guoguan.setRefreshing(false);
    }

    @Override
    protected void handleError() {

    }

    @Subscribe
    public void onEventMainThread(EventBusUtil.DingdanMixResultEvent event) {
        String msg = "onEventMainThread收到了消息：";
        LogUtils.i("eventbus" + msg);
        LogUtils.i("dingdan mix mDatas=null?" + (mDatas == null));
        List<HashMap<Integer, MatchsBean>> dataByDingdan = event.getDingdanData();
        LogUtils.i("dingdan mix " + (mDatas == null) + ":::" + (dataByDingdan == null));
        String guoguanfangshi = event.getDingdanGuoguanfangshi();
        if (guoguanfangshi.equals("guoguan")) {
            removeAllSelected();
            LogUtils.i("dingdan mix" + dataByDingdan.size());
            for (int k = 0; k < dataByDingdan.size(); k++) {
                LogUtils.i("dingdan mix   k" + k);
                int indictor = JcDataUtils.getIndicator(dataByDingdan.get(k));
                labelA:
                //for循环标签，便于break跳出多重循环
                for (int i = 0; i < mDatas.size(); i++) {
                    LogUtils.i("dingdan mix   i" + i);
                    for (int j = 0; j < mDatas.get(i).size(); j++) {
                        LogUtils.i("dingdan mix suitable?     " + k + "::" + dataByDingdan.get(k).get(indictor).getLeagueId() + " :::" + j + ":::" + mDatas.get(i).get(j).get(indictor).getLeagueId());
                        if (JcDataUtils.compareData(dataByDingdan.get(k).get(indictor), mDatas.get(i).get(j).get(indictor))) {
                            LogUtils.i("dingdan mix suitable" + k + ":::" + j);
                            mDatas.get(i).get(j).put(indictor, dataByDingdan.get(k).get(indictor));
                            break labelA;
                        }
                    }
                }
            }
        }
        handler.sendEmptyMessage(0);
    }

    @Subscribe
    public void onEventMainThread(EventBusUtil.FilterResultEvent event) {
        callback.setFlitrateData(true, event.getFilterMatch());
        LogUtils.i("fragment data:" + mFilterGuoGuanList.size() + "::" + mFilterGuoGuanList.size() + "::" + mFilterDanGuanList.size() + ":::");
        handler.sendEmptyMessage(Configure_JC.HANDLER_NORMAL);
    }

    private void refresh() {
        mDatas.clear();
        refreshLoad();
    }

    private class MyListener implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mix_queren_button:
                    if (setmSelectedMatchsBeans(mDatas).size() < 1) {
                        UIUtils.showToastSafe("请选择2-8场比赛");
                    } else if (setmSelectedMatchsBeans(mDatas).size() < 2) {
                        UIUtils.showToastSafe("请至少选择2场比赛");
                    } else {
                        JInCaiBaseActivity activity = (JInCaiBaseActivity) getActivity();
                        String titleNmae = activity.getTitleName();
                        Intent intent = new Intent(getActivity(),
                                FB_DingDanActivity_Mix.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mSelectHashMap", (Serializable) setmSelectedMatchsBeans(mDatas));
                        bundle.putString("guoguanfangshi", "guoguan");
                        bundle.putInt("mJingcaiType", Configure_JC.TAB_HHGG);
                        bundle.putInt("mSubType", Configure_JC.TYPE_SOCCER);
                        bundle.putString("mTitleName", titleNmae);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }    // TODO Auto-generated method stub

                    break;
                case R.id.mix_delete_all_tv:
                    removeAllSelected();
                    //跟新下方有机场的信息
                    handler.sendEmptyMessage(0);
                    break;
            }
        }

        @Override
        public void onRefresh() {
            LogUtils.i("refresh begin...");
            refresh();
        }
    }


    public List<HashMap<Integer, MatchsBean>> setmSelectedMatchsBeans(List<List<HashMap<Integer, MatchsBean>>> mMatchsBeans) {
        List<HashMap<Integer, MatchsBean>> list = new ArrayList<>();
        for (int i = 0; i < mMatchsBeans.size(); i++) {
            for (int j = 0; j < mMatchsBeans.get(i).size(); j++) {
                if ((null != JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_RQSFP)) && JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_RQSFP)).containsValue(true))
                        || (null != JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_SPF)) && JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_SPF)).containsValue(true))
                        || (null != JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_BF)) && JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_BF)).containsValue(true))
                        || (null != JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_BQC)) && JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_BQC)).containsValue(true))
                        || (null != JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_ZJQ)) && JcDataUtils.getSpsStateMap(mMatchsBeans.get(i).get(j).get(Configure_JC.TAB_ZJQ)).containsValue(true))) {
                    list.add(mMatchsBeans.get(i).get(j));
                }
            }
        }
        return list;
    }
    @Override
    protected String getTitle() {
        return "足球混合过关";
    }
}
