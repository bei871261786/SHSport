package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcScoreFilterGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.JcScoreEventBus;

/**
 * Created by booob on 2016-08-30-0030.
 */
public class JcScoreFiltrateActivity extends BaseActivity {


    @BindView(R.id.topButton_back_filtrate)
    ImageView topButtonBackFiltrate;
    @BindView(R.id.filtrate_head)
    RelativeLayout filtrateHead;
    @BindView(R.id.flitrate_match_selectall)
    Button flitrateMatchSelectall;
    @BindView(R.id.flitrate_match_reversalall)
    Button flitrateMatchReversalall;
    @BindView(R.id.flitrate_match_clear)
    Button flitrateMatchClear;
    @BindView(R.id.flitrate_fan_quan_xuan)
    LinearLayout flitrateFanQuanXuan;
    @BindView(R.id.flitrate_remen_saishi)
    TextView flitrateRemenSaishi;
    @BindView(R.id.flitrate_feiremen_tv)
    TextView flitratefeiremenTv;
    @BindView(R.id.flitrate_fb_hotmatch)
    GridView flitrateFbHotmatch;
    @BindView(R.id.flitrate_line)
    View flitrateLine;
    @BindView(R.id.flitrate_fb_allmatch)
    GridView flitrateFbAllmatch;
    @BindView(R.id.flitrate_total_bisai)
    TextView flitrateTotalBisai;
    @BindView(R.id.base_dialog_cancle_btn)
    Button flitrateCancel;
    @BindView(R.id.base_dialog_submit_btn)
    Button flitrateSubmit;
    private ArrayList<String> mLeagueListIs;//一级联赛
    private ArrayList<String> mLeagueListFalse;//非一级联赛
    public ArrayList<String> mLeagueList = new ArrayList<>();    //赛事筛选集合

    private JcScoreFilterGridViewAdapter mIsJcScoreFilterGridViewAdapter;
    private JcScoreFilterGridViewAdapter mFalseJcScoreFilterGridViewAdapter;
    private JcFilterOncliclistener mJcFilterOncliclistener;
    private int checkNum;//选中的比赛数目

    private boolean mSortAsTime = true; //默认按照时间排序
    private int mMatchStatus = Configs.MATCHALL;   //比赛状态默认为全部
    private String mMatchTime;   //比赛期号
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int count = 0;
            for (int i = 0; i < mLeagueListFalse.size(); i++) {
                if (mFalseJcScoreFilterGridViewAdapter.getIsSelected().get(i)) {
                    count++;
                }
            }
            for (int i = 0; i < mLeagueListIs.size(); i++) {
                if (mIsJcScoreFilterGridViewAdapter.getIsSelected().get(i)) {
                    count++;
                }
            }
            checkNum = count;
            if(checkNum<=0)
            {
                flitrateSubmit.setEnabled(false);
            }
            else
            {
                flitrateSubmit.setEnabled(true);
            }
            flitrateTotalBisai.setText("已选择" + checkNum + "场联赛");
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_match_flitrate);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        mLeagueListIs = mIntent.getStringArrayListExtra("mLeagueListIs");
        mLeagueListFalse = mIntent.getStringArrayListExtra("mLeagueListFalse");
        mSortAsTime = mIntent.getBooleanExtra("mSortAsTime", true);
        mMatchStatus = mIntent.getIntExtra("mMatchStatus", -1);
        mLeagueList = mIntent.getStringArrayListExtra("mLeagueList");
        Logger.e(mLeagueList.size() + "总长度" + mLeagueListIs.size() + "热门长度" + mLeagueList.size() + "非热门长度");
        mIsJcScoreFilterGridViewAdapter = new JcScoreFilterGridViewAdapter(this, mLeagueListIs, handler, mLeagueList);
        mFalseJcScoreFilterGridViewAdapter = new JcScoreFilterGridViewAdapter(this, mLeagueListFalse, handler, mLeagueList);
        flitrateFbHotmatch.setAdapter(mIsJcScoreFilterGridViewAdapter);
        flitrateFbAllmatch.setAdapter(mFalseJcScoreFilterGridViewAdapter);


     /*   flitrateFbHotmatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mIsJcScoreFilterGridViewAdapter.getIsSelected().get(position)) {
                    checkNum--;
                } else {
                    checkNum++;
                }
                dataChanged();
            }
        });
        flitrateFbAllmatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mFalseJcScoreFilterGridViewAdapter.getIsSelected().get(position)) {
                    checkNum--;
                } else {
                    checkNum++;
                }
                dataChanged();
            }
        });*/

        flitrateRemenSaishi.setText("热门联赛");
        flitratefeiremenTv.setText("普通联赛");
        mJcFilterOncliclistener = new JcFilterOncliclistener();
        topButtonBackFiltrate.setOnClickListener(mJcFilterOncliclistener);//返回
        flitrateMatchSelectall.setOnClickListener(mJcFilterOncliclistener);//全选
        flitrateMatchReversalall.setOnClickListener(mJcFilterOncliclistener);//反选
        flitrateMatchClear.setOnClickListener(mJcFilterOncliclistener); //清空
        flitrateCancel.setOnClickListener(mJcFilterOncliclistener); //取消
        flitrateSubmit.setOnClickListener(mJcFilterOncliclistener); //确定
        int count = mFalseJcScoreFilterGridViewAdapter.getCount() + mIsJcScoreFilterGridViewAdapter.getCount();
        flitrateTotalBisai.setText("共" + count + "场联赛可选");
    }

    @Override
    protected void init() {

    }

    private class JcFilterOncliclistener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.topButton_back_filtrate://返回
                    finish();
                    break;
                case R.id.flitrate_match_selectall://全选
                    for (int i = 0; i < mLeagueListIs.size(); i++) {
                        mIsJcScoreFilterGridViewAdapter.getIsSelected().put(i, true);
                    }
                    for (int i = 0; i < mLeagueListFalse.size(); i++) {
                        mFalseJcScoreFilterGridViewAdapter.getIsSelected().put(i, true);
                    }
                    checkNum = mIsJcScoreFilterGridViewAdapter.getCount() + mFalseJcScoreFilterGridViewAdapter.getCount();
                    dataChanged();
                    break;
                case R.id.flitrate_match_clear://清空
                    for (int i = 0; i < mLeagueListIs.size(); i++) {
                        if (mIsJcScoreFilterGridViewAdapter.getIsSelected().get(i)) {
                            mIsJcScoreFilterGridViewAdapter.getIsSelected().put(i, false);
                            checkNum--;
                        }
                    }
                    for (int i = 0; i < mLeagueListFalse.size(); i++) {
                        if (mFalseJcScoreFilterGridViewAdapter.getIsSelected().get(i)) {
                            mFalseJcScoreFilterGridViewAdapter.getIsSelected().put(i, false);
                            checkNum--;
                        }
                    }
                    dataChanged();
                    break;
                case R.id.flitrate_match_reversalall://反全选
                    for (int i = 0; i < mLeagueListFalse.size(); i++) {
                        if (mFalseJcScoreFilterGridViewAdapter.getIsSelected().get(i)) {
                            mFalseJcScoreFilterGridViewAdapter.getIsSelected().put(i, false);
                            checkNum--;
                        } else {
                            mFalseJcScoreFilterGridViewAdapter.getIsSelected().put(i, true);
                            checkNum++;
                        }
                    }
                    for (int i = 0; i < mLeagueListIs.size(); i++) {
                        if (mIsJcScoreFilterGridViewAdapter.getIsSelected().get(i)) {
                            mIsJcScoreFilterGridViewAdapter.getIsSelected().put(i, false);
                            checkNum--;
                        } else {
                            mIsJcScoreFilterGridViewAdapter.getIsSelected().put(i, true);
                            checkNum++;
                        }
                    }
                    dataChanged();
                    break;
                case R.id.base_dialog_cancle_btn://取消
                    finish();
                    break;
                case R.id.base_dialog_submit_btn://确定
                    mLeagueList.clear();
                    for (int i = 0; i < mFalseJcScoreFilterGridViewAdapter.getIsSelected().size(); i++) {
                        if (mFalseJcScoreFilterGridViewAdapter.getIsSelected().get(i)) {
                            mLeagueList.add(mLeagueListFalse.get(i));
                        }
                    }
                    for (int i = 0; i < mIsJcScoreFilterGridViewAdapter.getIsSelected().size(); i++) {
                        if (mIsJcScoreFilterGridViewAdapter.getIsSelected().get(i)) {
                            mLeagueList.add(mLeagueListIs.get(i));
                        }
                    }

                    EventBus.getDefault().post(new JcScoreEventBus(mMatchStatus, mSortAsTime, mMatchTime, mLeagueList));
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

    // 刷新listview和TextView的显示
    private void dataChanged() {
        mFalseJcScoreFilterGridViewAdapter.notifyDataSetChanged();
        mIsJcScoreFilterGridViewAdapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        flitrateTotalBisai.setText("已选择" + checkNum + "场联赛");
    }

    @Override
    protected String getBaidutitle() {
        return "筛选";
    }
}
