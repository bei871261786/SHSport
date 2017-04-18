package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.TreeSet;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.LeaguesBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.FootballFlitrateAdapter;
import shlottery.gov.cn.lotterycenter.utils.EventBusUtil;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by booob on 2016-05-23-0023.
 */
public class FootballFiltrateActivity extends BaseActivity {

    private FootballFlitrateAdapter mAdapter;
    private GridView mAllGrid;
    private ArrayList<LeaguesBean> mAllData;
    private FootballFlitrateAdapter mAllAdapter;
    private TreeSet<LeaguesBean> mFlitrateData;
    private TextView mLeagueCount;
    private Button cancel;
    private Button submit;
    private MyListener mListener;
    private ImageView mBackButton;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int count = 0;
            for (LeaguesBean bean : mFlitrateData) {
                if (bean.isChecked()) {
                    count++;
                }
            }
            if (count == 0) {
                submit.setEnabled(false);
            } else {
                submit.setEnabled(true);
            }
            mLeagueCount.setText("已选择了" + count + "场联赛");
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_flitrate);
        mLeagueCount = (TextView) findViewById(R.id.flitrate_total_bisai);
        cancel = (Button) findViewById(R.id.base_dialog_cancle_btn);
        submit = (Button) findViewById(R.id.base_dialog_submit_btn);
        Button selectAll = (Button) findViewById(R.id.flitrate_match_selectall);
        Button reversal = (Button) findViewById(R.id.flitrate_match_reversalall);
        Button clear = (Button) findViewById(R.id.flitrate_match_clear);
        mAllGrid = (GridView) findViewById(R.id.flitrate_fb_allmatch);
        mBackButton = (ImageView) findViewById(R.id.topButton_back_filtrate);
        mAllAdapter = new FootballFlitrateAdapter(mAllData, this, handler);

        mAllGrid.setAdapter(mAllAdapter);

        mAllAdapter.notifyDataSetChanged();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LeaguesBean bean : mFlitrateData) {
                    bean.setFlitrate(bean.isChecked());
                    LogUtils.i("flitrate activity submit:" + bean.isFlitrate());
                }
                EventBus.getDefault().post(new EventBusUtil().getFilterResultEvent(mFlitrateData));
                finish();
            }
        });
        selectAll.setOnClickListener(mListener);
        reversal.setOnClickListener(mListener);
        clear.setOnClickListener(mListener);
        mBackButton.setOnClickListener(mListener);
        handler.sendEmptyMessage(0);
    }

    @Override
    protected void init() {
        mFlitrateData = (TreeSet<LeaguesBean>) getIntent().getSerializableExtra("flitrateData");
        mListener = new MyListener();
        mAllData = new ArrayList<>();
        if (null != mFlitrateData) {
            for (LeaguesBean bean : mFlitrateData) {
                LogUtils.i("frlitrate isHot?" + bean.getIsHot());
                    mAllData.add(bean);
            }
        }
        LogUtils.i("flitrate data:"  + ":::" + mAllData.size());
    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.flitrate_match_selectall:
                    for (LeaguesBean bean : mFlitrateData) {
                        bean.setChecked(true);
                    }
                    mAllAdapter.notifyDataSetChanged();
                    break;
                case R.id.flitrate_match_reversalall:
                    for (LeaguesBean bean : mFlitrateData) {
                        LogUtils.i("flitrate getChecke?" + bean.getLeagueName() + ":::" + bean.isChecked());
                        bean.setChecked(!bean.isChecked());
                        LogUtils.i("flitrate getChecke______?" + bean.getLeagueName() + ":::" + bean.isChecked());
                    }
                    mAllAdapter.notifyDataSetChanged();
                    break;
                case R.id.flitrate_match_clear:
                    for (LeaguesBean bean : mFlitrateData) {
                        bean.setChecked(false);
                    }
                    mAllAdapter.notifyDataSetChanged();
                    break;
                case R.id.topButton_back_filtrate:
                    finish();
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected String getBaidutitle() {
        return "竞彩足篮筛选";
    }
}
