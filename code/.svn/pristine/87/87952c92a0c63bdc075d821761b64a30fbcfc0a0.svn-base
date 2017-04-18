package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusJCDetailBean;
import shlottery.gov.cn.lotterycenter.callback.SimpleEventBus;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/28 14:56
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IBDetailJLAdapter extends BaseAdapter {

    private Context context;
    private List<IssueBonusJCDetailBean.DataBean.MatchListBean> mDatas;
    private HashMap<Integer, Boolean> visibleMap = new HashMap<>();

    public IBDetailJLAdapter(Context context, ArrayList<IssueBonusJCDetailBean.DataBean.MatchListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LogUtils.i("getview");
        Holder holder = null;
        if (null == convertView) {
            convertView = UIUtils.inflate(R.layout.adapter_ib_jincaibasket);
            holder = new Holder();
            holder.sfc = (TextView) convertView.findViewById(R.id.issueDetail_shengfencha);
            holder.sfc_result = (TextView) convertView.findViewById(R.id.issueDetail_sfc_result);
            holder.dx = (TextView) convertView.findViewById(R.id.issueDetail_dx_result);
            holder.dx_layout = (LinearLayout) convertView.findViewById(R.id.issueDetail_dx_layout);
            holder.sf = (TextView) convertView.findViewById(R.id.issueDetail_shengfu);
            holder.expand = (LinearLayout) convertView.findViewById(R.id.lotterys_sh11x5_indicatorlayout);
            holder.indicator = (ImageView) convertView.findViewById(R.id.lotterys_sh11x5_indicator);
            holder.sf_result = (TextView) convertView.findViewById(R.id.issueDetail_sf_result);
            holder.rqsf = (TextView) convertView.findViewById(R.id.issueDetail_rqsf_result);
            holder.rqsf_layout = (LinearLayout) convertView.findViewById(R.id.issueDetail_rqsf_layout);
            holder.hostname = (TextView) convertView.findViewById(R.id.issueDetail_hostname);
            holder.visitname = (TextView) convertView.findViewById(R.id.issueDetail_visitname);
            holder.ping = (TextView) convertView.findViewById(R.id.issueDetail_ping);
            holder.ping2 = (TextView) convertView.findViewById(R.id.issueDetail_ping2);
            holder.weekno = (TextView) convertView.findViewById(R.id.issueDetail_weekno);
            holder.leaguname = (TextView) convertView.findViewById(R.id.issueDetail_leagueName);
            holder.resultLayout = (LinearLayout) convertView.findViewById(R.id.issueDetail_resultLayout);
            convertView.setTag(holder);
        }

        holder = (Holder) convertView.getTag();
        LogUtils.i("issuebonus adapter:" + holder.hostname + ":::" + mDatas.get(position).getHostName());
        holder.hostname.setText(mDatas.get(position).getHostName() + "(主)");
        holder.visitname.setText(mDatas.get(position).getVisitName());
        holder.weekno.setText(mDatas.get(position).getWeekNo());
        holder.leaguname.setText(mDatas.get(position).getLeagueName());
        holder.rqsf_layout.removeAllViews();
        holder.dx_layout.removeAllViews();
        final long time = mDatas.get(position).getMatchTime();
        String pingText = "";
        String pingHalfText = "";
        final LinearLayout findalResultLayout = holder.resultLayout;
        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.i("IBDetailAdapter expand:" + findalResultLayout.getVisibility());
                if (findalResultLayout.getVisibility() == View.GONE) {
                    visibleMap.put(position, true);
                } else {
                    visibleMap.put(position, false);
                }
                SimpleEventBus eventBus = new SimpleEventBus();
                eventBus.setFlag(true);
                EventBus.getDefault().post(eventBus);
                LogUtils.i("IBDetailAdapter expand______________:" + findalResultLayout.getVisibility());
            }
        });
        if (visibleMap.containsKey(position)) {
            LogUtils.i("IBDetailAdapter  containKey:" + position);
            if (visibleMap.get(position)) {
                LogUtils.i("IBDetailAdapter value  true:");
                toggleExpand(holder.resultLayout, holder.indicator, true);
            } else {
                LogUtils.i("IBDetailAdapter value  false");
                toggleExpand(holder.resultLayout, holder.indicator, false);
            }
        } else {
            LogUtils.i("IBDetailAdapter  notcontainKey:" + position);
            visibleMap.put(position, false);
            toggleExpand(holder.resultLayout, holder.indicator, false);
        }

        for (int i = 0; i < mDatas.get(position).getBonusList().size(); i++) {
            String result = (mDatas.get(position).getBonusList().get(i).getBonusResult() == null) ? "--" : mDatas.get(position).getBonusList().get(i).getBonusResult();
            LogUtils.i("results item total:" + position + "::::" + i + ":::::::" + result);
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jlsf")) {
                holder.sf_result.setText(result);
            }
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jlrsf")) {
                if (result != "--") {
                    String[] results = result.split(",");
                    LogUtils.i("results item:" + position + "::::" + results.length + ":::" + result);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int j = 0; j < results.length; j++) {
                        String hand = results[j].substring(2, results[j].length());
                        String resultName = results[j].substring(0, 2);
                        if (j == 0) {
                            if (hand.charAt(0) == '+') {
                                TextUtils.setStrColor(holder.rqsf
                                        , "[" + hand + "]" + resultName, "[" + hand + "]", context.getResources().getColor(R.color.select_red));
                            } else if (hand.charAt(0) == '-') {
                                TextUtils.setStrColor(holder.rqsf
                                        , "[" + hand + "]" + resultName, "[" + hand + "]", context.getResources().getColor(R.color.standard_textcolor_c9));
                            }
                            continue;
                        }
                        LogUtils.i("results item  2:" + results[j]);
                        TextView textView = new TextView(context);
                        textView.setLayoutParams(params);
                        textView.setGravity(Gravity.CENTER);
                        textView.setPadding(0, 5, 0, 5);
                        textView.setTextColor(context.getResources().getColor(R.color.black));
                        textView.setTextSize(14);
                        if (hand.charAt(0) == '+') {
                            TextUtils.setStrColor(textView, "[" + hand + "]" + resultName, "[" + hand + "]", context.getResources().getColor(R.color.select_red));
                        } else if (hand.charAt(0) == '-') {
                            TextUtils.setStrColor(textView, "[" + hand + "]" + resultName, "[" + hand + "]", context.getResources().getColor(R.color.standard_textcolor_c9));
                        }
                        holder.rqsf_layout.addView(textView);
                    }
                }
            }
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jlfc")) {
                holder.sfc_result.setText(result);

            }
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jldx")) {
                if (result != "--") {
                    String[] results = result.split(",");
                    LogUtils.i("results item:" + position + "::::" + results.length + ":::" + result);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int j = 0; j < results.length; j++) {
                        LogUtils.i("results item  2:" + results[j]);
                        String hand = results[j].substring(1, results[j].length());
                        String resultName = results[j].substring(0, 1);
                        if (j == 0) {
                            holder.dx.setText("[" + hand + "]" + resultName);
                            continue;
                        }

                        TextView textView = new TextView(context);
                        textView.setLayoutParams(params);
                        textView.setGravity(Gravity.CENTER);
                        textView.setPadding(0, 5, 0, 5);
                        textView.setTextColor(context.getResources().getColor(R.color.black));
                        textView.setTextSize(14);
                        textView.setText("[" + hand + "]" + resultName);
                        LogUtils.i("results item  3:" + textView.getText());
                        holder.dx_layout.addView(textView);
                    }
                }
            }

            LogUtils.i("");
            if (holder.sf_result.getText() == null || holder.sf_result.getText().toString().isEmpty()) {
                holder.sf_result.setText("--");
            }
            if (holder.rqsf.getText() == null || holder.rqsf.getText().toString().isEmpty()) {
                holder.rqsf.setText("--");
            }
            if (holder.sfc_result.getText() == null || holder.sfc_result.getText().toString().isEmpty()) {
                holder.sfc_result.setText("--");
            }
            if (holder.dx.getText() == null || holder.dx.getText().toString().isEmpty()) {
                holder.dx.setText("--");
            }
        }
        int hostGoal = mDatas.get(position).getHostGoal();
        int visitGoal = mDatas.get(position).getVisitGoal();
        int haltHostGoal = mDatas.get(position).getHostHalfGoal();
        int haltVisitGoal = mDatas.get(position).getVisitHalfGoal();
        pingText = visitGoal + "-" + hostGoal;
        pingHalfText = "(" + haltVisitGoal + "-" + haltHostGoal + ")";
        holder.ping2.setText(pingHalfText);
        holder.ping.setText(pingText);
        if (hostGoal > visitGoal) {
            holder.ping.setTextColor(context.getResources().getColor(R.color.select_red));
        } else if (hostGoal == visitGoal) {
            holder.ping.setTextColor(context.getResources().getColor(R.color.standard_textcolor_c7));
        } else {
            holder.ping.setTextColor(context.getResources().getColor(R.color.standard_textcolor_c9));
        }
        return convertView;
    }

    public void changeExpandStatus(int position) {
        LogUtils.i("changeExpandStatus:" + position);
        if (visibleMap.containsKey(position)) {
            boolean status = visibleMap.get(position);
            visibleMap.put(position, !status);
        } else {
            visibleMap.put(position, false);
        }
        SimpleEventBus eventBus = new SimpleEventBus();
        eventBus.setFlag(true);
        EventBus.getDefault().post(eventBus);
    }

    class Holder {
        TextView hostname;
        TextView ping;
        TextView ping2;
        TextView visitname;
        TextView weekno;
        TextView leaguname;
        TextView sf;
        TextView sf_result;
        TextView rqsf;
        LinearLayout rqsf_layout;
        TextView sfc;
        TextView sfc_result;
        TextView dx;
        LinearLayout dx_layout;
        LinearLayout expand;
        ImageView indicator;
        LinearLayout resultLayout;
    }

    private void toggleExpand(LinearLayout expandLayout, ImageView indicator, boolean flag) {
        if (flag) {
            expandLayout.setVisibility(View.VISIBLE);
            indicator.setEnabled(false);
        } else {
            expandLayout.setVisibility(View.GONE);
            indicator.setEnabled(true);
        }
    }

    //清除展开状态
    public void clearExpandStatus() {
        visibleMap.clear();
    }
}

