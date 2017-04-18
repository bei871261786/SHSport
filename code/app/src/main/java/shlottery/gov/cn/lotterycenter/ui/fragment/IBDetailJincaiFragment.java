package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusJCDetailBean;
import shlottery.gov.cn.lotterycenter.callback.SimpleEventBus;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.IssueBonusJCDetailProtocol;
import shlottery.gov.cn.lotterycenter.ui.adapter.IBDetailJLAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.IBDetailJZAdapter;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/28 13:51
 * 描    述：
 * 修订历史：
 * ************************************************
 */
public class IBDetailJincaiFragment extends IBDetailBaseFragment {
    private ListView mInfoListview;
    private BaseAdapter mInfoAdapter;
    private ArrayList<IssueBonusJCDetailBean.DataBean.MatchListBean> mInfoData = new ArrayList<>();
    private Intent mIntent;
    private TextView mCloseDate;
    private TextView mTitleDate;
    private View emptyView;
    private LinearLayout mJinzuDescriLayout;
    private LinearLayout mJinlanDescriLayout;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.i("IBDetailJincaiFragment handleMessage");
            emptyView.setVisibility(View.VISIBLE);
            mInfoListview.setVisibility(View.GONE);
        }
    };

    @Override
    protected void init() {
        mIntent = getActivity().getIntent();
        Bundle bundle = mIntent.getExtras();
        mLotid = (String) bundle.getSerializable("lotid");
        mInfoAdapter = getAdapter(mLotid);
    }

    private void initTitle(IssueBonusJCDetailBean dataBean) {
        if (dataBean.getData() != null) {
            if (dataBean.getData().getMatchList() != null && dataBean.getData().getMatchList().size() > 0) {
                String date = dataBean.getData().getIssueNo();
                String year = date.substring(0, 4);
                String month = date.substring(4, 6);
                String day = date.substring(6, 8);
                date = year + "-" + month + "-" + day;
                String week = dataBean.getData().getMatchList().get(0).getWeekNo();
                week = week.substring(1, 2);
                mTitleDate.setText(date + "  (星期" + week + ")");
            } else {
                String date = dataBean.getData().getIssueNo();
                String year = date.substring(0, 4);
                String month = date.substring(4, 6);
                String day = date.substring(6, 8);
                date = year + "-" + month + "-" + day;
                mTitleDate.setText(date);
            }
        }
    }

    private void initAdapter() {
        //重新选择日期后清除adapter中的展开状态
        if (mInfoAdapter != null && mInfoAdapter instanceof IBDetailJLAdapter) {
            IBDetailJLAdapter adapter = (IBDetailJLAdapter) mInfoAdapter;
            adapter.clearExpandStatus();
        }
    }

    @Override
    protected void handleMessge(Message msg) {
    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_ib_jincaidetail, null);
//        mScrollview = (ScrollView) view.findViewById(R.id.issueDetail_jincaiScrollview);
        mInfoListview = (ListView) view.findViewById(R.id.issueDetail_inInformationListview);
        emptyView = view.findViewById(R.id.emptytiple);
        mTitleDate = (TextView) view.findViewById(R.id.issueDetail_date);
        mInfoListview.setAdapter(mInfoAdapter);
        mJinzuDescriLayout = (LinearLayout) view.findViewById(R.id.issueDetail_jzDescri);
        mJinlanDescriLayout = (LinearLayout) view.findViewById(R.id.issueDetail_jlDescri);
        if (mLotid.equals("jczq")) {
            mJinzuDescriLayout.setVisibility(View.VISIBLE);
        }
        if (mLotid.equals("jclq")) {
            mJinlanDescriLayout.setVisibility(View.VISIBLE);
        }
        mInfoListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mInfoAdapter != null && mInfoAdapter instanceof IBDetailJLAdapter) {
                    IBDetailJLAdapter adapter = (IBDetailJLAdapter) mInfoAdapter;
                    adapter.changeExpandStatus(i);
                }
            }
        });
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    protected boolean beginLoad() {
        initAdapter();
        IssueBonusJCDetailProtocol protocol = new IssueBonusJCDetailProtocol(getActivity());
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> parmas = new LinkedHashMap<String, String>();
                parmas.put("lotId", mLotid);
                parmas.put("issueNo", mIssueNo);
                return parmas;
            }
        }, this);

        return false;
    }

    @Override
    protected void handleSucess(Object loadData) {
        if (null != loadData) {
            IssueBonusJCDetailBean dataBean = (IssueBonusJCDetailBean) loadData;
            if (dataBean.getRet() == 100) {
                mInfoData.clear();
                mInfoData.addAll(dataBean.getData().getMatchList());
                if (mInfoData == null || mInfoData.isEmpty()) {
                    LogUtils.i("IBDetailJincaiFragment beginempty");
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else {
                    emptyView.setVisibility(View.GONE);
                    mInfoListview.setVisibility(View.VISIBLE);
                }
                initTitle(dataBean);
                mInfoAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void handleError() {
    }

    private BaseAdapter getAdapter(String lotid) {
        BaseAdapter adapter = null;
        if (lotid.equals("jczq")) {
            adapter = new IBDetailJZAdapter(getActivity(), mInfoData);
        }
        if (lotid.equals("jclq")) {
            adapter = new IBDetailJLAdapter(getActivity(), mInfoData);
        }
        return adapter;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void calculateItemHeight(SimpleEventBus eventBus) {
        LogUtils.i("calculateItemHeight");
        if (eventBus.getFlag()) {
            mInfoAdapter.notifyDataSetChanged();
            //setListViewHeightBasedOnChildren(mInfoListview);
        }
    }

    /**
     * 动态测量list view item的高度
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        LogUtils.i("setListViewHeightBasedOnChildren");
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight +
                (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        params.height = 50;
        listView.setLayoutParams(params);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }



    @Override
    protected String getTitle() {
        return "竞彩开奖详情";
    }
}
