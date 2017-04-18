package shlottery.gov.cn.lotterycenter.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NewsTagsBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.callback.NewsFlitrateEventBus;
import shlottery.gov.cn.lotterycenter.protool.NeweTagsProtocol;
import shlottery.gov.cn.lotterycenter.ui.widget.CustomGridviewManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ScreenUtils;

public class NewsFlitrateActivity extends BaseActivity implements LoadCallback {

    private RecyclerView mWanfaRecycleview;
    private RecyclerView mLeibieRecycleview;
    private RecyclerView mZhuanjiaRecycleview;
    private RecyclerView mQuyuRecycleview;
    private ArrayList<String> mWanfaData = new ArrayList<>();
    private ArrayList<String> mLeibieData = new ArrayList<>();
    private ArrayList<String> mZhuanjiaData = new ArrayList<>();
    private ArrayList<String> mQuyuData = new ArrayList<>();
    private ArrayList<Integer> typeList = new ArrayList<>();

    private final int TAG_WANFA = 0;
    private final int TAG_LEIXING = 1;
    private final int TAG_ZHUANJIA = 2;
    private final int TAG_QUYU = 3;

    private MyAdapter mWanfaAdapter;
    private MyAdapter mLeibieAdapter;
    private MyAdapter mZhuanjiaAdapter;
    private MyAdapter mQuyuAdapter;
    private Button mCancel;
    private Button mSubmit;
    private MyListener mListener;
    private NewsFlitrateEventBus mEventbusData = new NewsFlitrateEventBus();
    private ArrayList<String> mTagList = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_news_flitrate);
        mWanfaRecycleview = (RecyclerView) findViewById(R.id.news_tag_wanfa);
        mLeibieRecycleview = (RecyclerView) findViewById(R.id.news_tag_leibie);
        mZhuanjiaRecycleview = (RecyclerView) findViewById(R.id.news_tag_zhuanjia);
        mQuyuRecycleview = (RecyclerView) findViewById(R.id.news_tag_quyu);
        mCancel = (Button) findViewById(R.id.base_dialog_cancle_btn);
        mSubmit = (Button) findViewById(R.id.base_dialog_submit_btn);
        CustomGridviewManager gridLayoutManager = new CustomGridviewManager(this, 4);
        gridLayoutManager.setScrollEnabled(false);
        mWanfaRecycleview.setLayoutManager(gridLayoutManager);
        mWanfaRecycleview.setAdapter(mWanfaAdapter);

        gridLayoutManager = new CustomGridviewManager(this, 4);
        gridLayoutManager.setScrollEnabled(false);
        mLeibieRecycleview.setLayoutManager(gridLayoutManager);
        mLeibieRecycleview.setAdapter(mLeibieAdapter);

        gridLayoutManager = new CustomGridviewManager(this, 4);
        gridLayoutManager.setScrollEnabled(false);
        mZhuanjiaRecycleview.setLayoutManager(gridLayoutManager);
        mZhuanjiaRecycleview.setAdapter(mZhuanjiaAdapter);

        gridLayoutManager = new CustomGridviewManager(this, 4);
        gridLayoutManager.setScrollEnabled(false);
        mQuyuRecycleview.setLayoutManager(gridLayoutManager);
        mQuyuRecycleview.setAdapter(mQuyuAdapter);
        mCancel.setOnClickListener(mListener);
        mSubmit.setOnClickListener(mListener);
        for(int hideType:typeList)
        {
            if (hideType == 1) {
                TextView wanfa = (TextView) findViewById(R.id.news_tag_wanfaTv);
                wanfa.setVisibility(View.GONE);
                mWanfaRecycleview.setVisibility(View.GONE);
            }
            if (hideType == 2) {
                TextView leibie = (TextView) findViewById(R.id.news_tag_leibieTv);
                leibie.setVisibility(View.GONE);
                mLeibieRecycleview.setVisibility(View.GONE);
            }
            if (hideType == 3) {
                TextView famous = (TextView) findViewById(R.id.news_tag_famousTv);
                famous.setVisibility(View.GONE);
                mZhuanjiaRecycleview.setVisibility(View.GONE);
            }
            if (hideType == 4) {
                TextView quyu = (TextView) findViewById(R.id.news_tag_quyuTv);
                quyu.setVisibility(View.GONE);
                mQuyuRecycleview.setVisibility(View.GONE);
            }
        }



        initTitleBar();
        beginLoad();
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
        titlebarTv.setText("标签选择");
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }

    @Override
    protected void init() {
        Bundle bundle = getIntent().getExtras();
        typeList = bundle.getIntegerArrayList("hideType");
        LogUtils.i("tag bundle:" + bundle.getSerializable("tagEventbus") + ":::" + typeList);
        if (bundle.getSerializable("tagEventbus") != null && !bundle.getBoolean("isClearFlitrateTag")) {
            mEventbusData = (NewsFlitrateEventBus) bundle.getSerializable("tagEventbus");
        }
        mListener = new MyListener();
        mWanfaAdapter = new MyAdapter(TAG_WANFA);
        mLeibieAdapter = new MyAdapter(TAG_LEIXING);
        mZhuanjiaAdapter = new MyAdapter(TAG_ZHUANJIA);
        mQuyuAdapter = new MyAdapter(TAG_QUYU);
    }

    private void beginLoad() {
        NeweTagsProtocol protocol = new NeweTagsProtocol(this);
        protocol.load(this, this);
    }

    @Override
    public void onSucess(Object o) {
        if (o != null) {
            NewsTagsBean tagsBean = (NewsTagsBean) o;
            if (tagsBean.getRet() == 100) {
                mWanfaData.clear();
                mLeibieData.clear();
                mZhuanjiaData.clear();
                mQuyuData.clear();

                mWanfaData.addAll(tagsBean.getData().getLottery());
                mLeibieData.addAll(tagsBean.getData().getCategory());
                mZhuanjiaData.addAll(tagsBean.getData().getFamous());
                mQuyuData.addAll(tagsBean.getData().getDistrict());

                LogUtils.i("tags :" + mWanfaData.size() + ":::" + mLeibieData.size() + ":::" + mZhuanjiaData.size() + ":::" + mQuyuData.size());
                mWanfaAdapter.notifyDataSetChanged();
                mLeibieAdapter.notifyDataSetChanged();
                mZhuanjiaAdapter.notifyDataSetChanged();
                mQuyuAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onError() {

    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.base_dialog_submit_btn:
                    EventBus.getDefault().post(mEventbusData);
                    LogUtils.i("tags submit");
                    finish();
                    break;
                case R.id.base_dialog_cancle_btn:
                    mEventbusData.clear();
                    mWanfaAdapter.notifyDataSetChanged();
                    mLeibieAdapter.notifyDataSetChanged();
                    mZhuanjiaAdapter.notifyDataSetChanged();
                    mQuyuAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        private int type;

        MyAdapter(int type) {
            this.type = type;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(NewsFlitrateActivity.this).inflate(R.layout.adapter_newsflitrat, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            LogUtils.i("tags onBindViewHolder:" + type);
            ArrayList<String> data = null;
            ArrayList<String> selectedData = null;
            if (type == TAG_WANFA) {
                data = mWanfaData;
                selectedData = mEventbusData.getLottery();
            } else if (type == TAG_LEIXING) {
                data = mLeibieData;
                selectedData = mEventbusData.getCategory();
            } else if (type == TAG_QUYU) {
                data = mQuyuData;
                selectedData = mEventbusData.getDistrict();
            } else if (type == TAG_ZHUANJIA) {
                data = mZhuanjiaData;
                selectedData = mEventbusData.getFamous();
            }
            if (data != null) {
                final String tagName = data.get(position);
                holder.tag.setText(tagName);
                if (selectedData.contains(tagName)) {
                    LogUtils.i("tags onBindViewHolder containName--------------:" + tagName);
                    holder.tag.setChecked(true);
                } else {
                    LogUtils.i("tags onBindViewHolder noContain:" + tagName);
                    holder.tag.setChecked(false);
                }
                holder.tag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (type == TAG_WANFA) {
                                mEventbusData.addLottery(tagName);
                            } else if (type == TAG_LEIXING) {
                                mEventbusData.addCategory(tagName);
                            } else if (type == TAG_QUYU) {
                                mEventbusData.addDistrict(tagName);
                            } else if (type == TAG_ZHUANJIA) {
                                mEventbusData.addFamous(tagName);
                            }
                        } else {
                            if (type == TAG_WANFA) {
                                mEventbusData.removeLottery(tagName);
                            } else if (type == TAG_LEIXING) {
                                mEventbusData.removeCategory(tagName);
                            } else if (type == TAG_QUYU) {
                                mEventbusData.removeDistrict(tagName);
                            } else if (type == TAG_ZHUANJIA) {
                                mEventbusData.removeFamous(tagName);
                            }
                        }
                    }
                });
            } else {
                holder.tag.setChecked(false);
            }

        }

        @Override
        public int getItemCount() {
            LogUtils.i("tags getItemCount:" + type);
            if (type == TAG_WANFA) {
                LogUtils.i("tags getItemCount:" + type + mWanfaData.size());
                return mWanfaData.size();
            } else if (type == TAG_LEIXING) {
                LogUtils.i("tags getItemCount:" + type + mLeibieData.size());
                return mLeibieData.size();
            } else if (type == TAG_QUYU) {
                return mQuyuData.size();
            } else if (type == TAG_ZHUANJIA) {
                return mZhuanjiaData.size();
            }
            return 0;
        }

        class MyHolder extends RecyclerView.ViewHolder {
            CheckBox tag;

            public MyHolder(View itemView) {
                super(itemView);
                tag = (CheckBox) itemView.findViewById(R.id.flitrate_tab);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tag.getLayoutParams();
                params.width = (int) (ScreenUtils.getScreenWidth(NewsFlitrateActivity.this) * 0.22);
                tag.setLayoutParams(params);
            }
        }
    }
    @Override
    protected String getBaidutitle() {
        return "筛选";
    }
}
