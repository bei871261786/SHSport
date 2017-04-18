package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.MyQuestionsBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.MyQuestionsProtocol;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadDataScrollController;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

public class SelfQuestionActivity extends BaseActivity implements LoadCallback, LoadDataScrollController.OnRecycleRefreshListener {
    private int mPageNo = 1;
    private int mPageSize = 20;
    private int mPageCount;
    private SwipeRefreshLayout mRefreshLayout;
    private LoadDataScrollController mController;
    private boolean isLoadMore = false;
    private RecyclerView mRecycleview;
    private ArrayList<MyQuestionsBean.DataBean.ListBean> mAdapterData = new ArrayList<>();
    private MyAdapter mAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_self_question);
        mRecycleview = (RecyclerView) findViewById(R.id.selfQuestions_recycleview);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        initRefreshLayout();
        initTitleBar();
        beginLoad();
    }

    @Override
    protected void init() {
        mAdapter = new MyAdapter();
    }

    //在recycleview被初始化之后调用
    private void initRefreshLayout() {
        mController = new LoadDataScrollController(this);
        mRecycleview.addOnScrollListener(mController);
        mRecycleview.setAdapter(mAdapter);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.account_jifen_refreshLayout);
        mRefreshLayout.setOnRefreshListener(mController);
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText("我的提问");
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }

    public void beginLoad() {
        MyQuestionsProtocol protocol = new MyQuestionsProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
    }

    @Override
    public void onSucess(Object o) {
        mController.setLoadDataStatus(false);
        mRefreshLayout.setRefreshing(false);
        if (o != null) {
            MyQuestionsBean questionsBean = (MyQuestionsBean) o;
            if (questionsBean.getRet() == 100) {
                if (!isLoadMore) {
                    mAdapterData.clear();
                }

                mAdapterData.addAll(questionsBean.getData().getList());
                mAdapter.notifyDataSetChanged();
            }
        }
        isLoadMore = false;
    }

    @Override
    public void onError() {
        mController.setLoadDataStatus(false);
        mRefreshLayout.setRefreshing(false);
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
        LogUtils.i("accountVoucherfragmnt loadMore" + mPageNo);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SelfQuestionActivity.this).inflate(R.layout.adapter_selfquestion, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            long timeLog = mAdapterData.get(position).getQuestionTime();
            holder.date.setText(DateUtils.formatSimpleDateAndTime(timeLog * 1000));
            String questionStr = mAdapterData.get(position).getQuestion();
            String answerStr = mAdapterData.get(position).getAnswer();
            Resources resources = BaseApplication.getApplication().getResources();
            if (answerStr != null && !answerStr.isEmpty()) {
                holder.diver.setVisibility(View.VISIBLE);
                holder.answer.setVisibility(View.VISIBLE);
                TextUtils.setStrColor(holder.question, "提问: " + questionStr + "?", "提问:", resources.getColor(R.color.circle_blue));
                TextUtils.setStrColor(holder.answer, "回复: " + answerStr, "回复:", resources.getColor(R.color.circle_blue));
            } else {
                holder.answer.setVisibility(View.GONE);
                TextUtils.setStrColor(holder.question, "提问: " + questionStr + "?", "提问:", resources.getColor(R.color.circle_blue));
                holder.diver.setVisibility(View.GONE);
            }

        }

        @Override
        public int getItemCount() {
            return mAdapterData.size();

        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView date;
            TextView question;
            TextView answer;
            View diver;

            public MyHolder(View itemView) {

                super(itemView);
                date = (TextView) itemView.findViewById(R.id.selfquestion_date);
                question = (TextView) itemView.findViewById(R.id.selfquestion_question);
                answer = (TextView) itemView.findViewById(R.id.selfquestion_answer);
                diver = itemView.findViewById(R.id.selfquestion_view);
            }
        }
    }
    @Override
    protected String getBaidutitle() {
        return "我的提问";
    }
}
