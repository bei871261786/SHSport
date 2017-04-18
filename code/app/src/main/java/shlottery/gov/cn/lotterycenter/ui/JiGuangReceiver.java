package shlottery.gov.cn.lotterycenter.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.JPushExtraBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.ui.activity.DingdanDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.IssueBounsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.NewsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.RecommendActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.Rmd_famousDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.SplashActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.SubscriberActivity;
import shlottery.gov.cn.lotterycenter.utils.ClientUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PackageUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;

import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JCDX;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JLRSF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JLSF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JZRSPF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JZSPF;


/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-08-0008 10:33
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class JiGuangReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //接收到推送token 存入sp中, 向服务器发送注册推送id
            SharedPreferencesUtils.put(BaseApplication.getApplication(), "registrationId", regId);
            ClientUtils.regPushToken();
            //send the Registration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            //跳转指定底层页面
            jump2Activity(context, bundle);
            //暂时修改为跳转到splash页面
            //            jump2Splash(context);
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    /**
     * 跳转到欢迎页
     *
     * @param context
     */
    private void jump2Splash(Context context) {
        if (!PackageUtils.isAppIsInBackground(context)) {
            return;
        }
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 跳转到指定页面
     *
     * @param context
     * @param bundle
     */
    private void jump2Activity(Context context, Bundle bundle) {
        String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        boolean isError = false;
        Gson gson = new Gson();
        JPushExtraBean jPushExtraBean = gson.fromJson(extra, JPushExtraBean.class);
        LogUtils.d("用户点击通知 : " + jPushExtraBean.toString());
        Intent intent = new Intent(context, IssueBounsDetailActivity.class);
        Bundle intentBundle = new Bundle();
        Resources res = context.getResources();
        String type = jPushExtraBean.getType();
        if (type == null) {
            jump2Splash(context);
            return;
        }
        switch (type) {
            case JPushExtraBean.MSG_TYPE_DLT:
                intentBundle.putInt("detailType", Configure.DETAIL_NUMBER);
                intentBundle.putSerializable("issueName", res.getString(R.string.lotterys_lotto));
                intentBundle.putSerializable("lotid", JPushExtraBean.MSG_TYPE_DLT);
                break;
            case JPushExtraBean.MSG_TYPE_PL3:
                intentBundle.putInt("detailType", Configure.DETAIL_NUMBER);
                intentBundle.putSerializable("issueName", res.getString(R.string.lotterys_pl3));
                intentBundle.putSerializable("lotid", JPushExtraBean.MSG_TYPE_PL3);
                break;
            case JPushExtraBean.MSG_TYPE_PL5:
                intentBundle.putInt("detailType", Configure.DETAIL_NUMBER);
                intentBundle.putSerializable("issueName", res.getString(R.string.lotterys_pl5));
                intentBundle.putSerializable("lotid", JPushExtraBean.MSG_TYPE_PL5);
                break;
            case JPushExtraBean.MSG_TYPE_QXC:
                intentBundle.putInt("detailType", Configure.DETAIL_NUMBER);
                intentBundle.putSerializable("issueName", res.getString(R.string.lotterys_qixing));
                intentBundle.putSerializable("lotid", JPushExtraBean.MSG_TYPE_QXC);
                break;
            case JPushExtraBean.MSG_TYPE_SH115:
                intentBundle.putInt("detailType", Configure.DETAIL_SH115);
                intentBundle.putSerializable("issueName", res.getString(R.string.lotterys_sh11x5));
                intentBundle.putSerializable("lotid", JPushExtraBean.MSG_TYPE_SH115);
                break;
            case JPushExtraBean.MSG_TYPE_SFC:
                intentBundle.putInt("detailType", Configure.DETAIL_SOCCER);
                intentBundle.putSerializable("issueNumber", null);
                intentBundle.putSerializable("issueName", res.getString(R.string.lotterys_sf));
                intentBundle.putSerializable("lotid", JPushExtraBean.MSG_TYPE_SFC);
                break;
            case JPushExtraBean.MSG_TYPE_BQC:
                intentBundle.putInt("detailType", Configure.DETAIL_SOCCER);
                intentBundle.putSerializable("issueNumber", null);
                intentBundle.putSerializable("issueName", res.getString(R.string.lotterys_bqc6));
                intentBundle.putSerializable("lotid", JPushExtraBean.MSG_TYPE_BQC);
                break;
            case JPushExtraBean.MSG_TYPE_JQC:
                intentBundle.putInt("detailType", Configure.DETAIL_SOCCER);
                intentBundle.putSerializable("issueNumber", null);
                intentBundle.putSerializable("issueName", res.getString(R.string.lotterys_jqc4));
                intentBundle.putSerializable("lotid", JPushExtraBean.MSG_TYPE_JQC);
                break;
            case JPushExtraBean.MSG_TYPE_SYSMSG:
                intent = new Intent(context, SubscriberActivity.class);
                break;
            case JPushExtraBean.MSG_TYPE_MSG:
                intent = new Intent(context, SubscriberActivity.class);
                break;
            //中奖到模拟注单具体某个页面
            case JPushExtraBean.MSG_TYPE_BONUS:
//                if (!PackageUtils.isAppIsInBackground(context)) {
//                    return;
//                }
//                intent = new Intent(context, SubscriberActivity.class);
                intent = new Intent(context, DingdanDetailActivity.class);
                // TODO: 2017/4/10 stakeId 需要获取
                int stakeId = 0;
                intent.putExtra(Configs.STAKEID_KEY, stakeId + "");
                break;

            //专家看彩列表
            case JPushExtraBean.MSG_TYPE_EXPERT:
                intent = new Intent(context, RecommendActivity.class);
                break;

            //专家推荐详情页
            case JPushExtraBean.MSG_TYPE_RECOM:
                String recomId = jPushExtraBean.getId();
                String lotId = jPushExtraBean.getLotId();
                int intRecomId = 0;
                if (!TextUtils.isEmpty(recomId)) {
                    try {
                        intRecomId = Integer.parseInt(recomId);
                    } catch (Exception e) {
                    }
                }
                intent = new Intent(context, Rmd_famousDetailActivity.class);
                intentBundle.putInt("id", intRecomId);
                if (!TextUtils.isEmpty(lotId) && intRecomId != 0) {
                    intentBundle.putInt(Configs.SFCDINGDAN_INT_KEY, getActivityType(lotId));
                } else {
                    isError = true;
                }
                break;

            //体彩分析页
            case JPushExtraBean.MSG_TYPE_NEWS:
                String newsId = jPushExtraBean.getId();
                int intNewsId = 0;
                if (!TextUtils.isEmpty(newsId)) {
                    try {
                        intNewsId = Integer.parseInt(newsId);
                    } catch (Exception e) {
                    }
                }
                intent = new Intent(context, NewsDetailActivity.class);
                intentBundle.putInt("id", intNewsId);
                intentBundle.putString("title", "体彩分析");
                intentBundle.putBoolean("canSwipe", false);
                break;
            default:
                //未知类型通知 默认操作
                if (!PackageUtils.isAppIsInBackground(context)) {
                    return;
                }
                intent = new Intent(context, SplashActivity.class);
                break;
        }

        if (isError) {
            if (!PackageUtils.isAppIsInBackground(context)) {
                return;
            }
            intent = new Intent(context, SplashActivity.class);
        }
        intent.putExtras(intentBundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private int getActivityType(String lotId) {
        int activityType = 0;
        switch (lotId) {
            case "jldx":
                activityType = ACTIVITYTYPE_JCDX;
                break;
            case "jlrsf":
                activityType = ACTIVITYTYPE_JLRSF;
                break;
            case "jzxspf":
                activityType = ACTIVITYTYPE_JZRSPF;
                break;
            case "jzspf":
                activityType = ACTIVITYTYPE_JZSPF;
                break;
            case "jlsf":
                activityType = ACTIVITYTYPE_JLSF;
                break;
            case "sf14":
                activityType = Configs.ACTIVITYTYPE_R9;
                break;
            case "sf9":
                activityType = Configs.ACTIVITYTYPE_R9;
                break;

            //竞彩足球 半全场
            case "jzbqc":
                activityType = Configs.ACTIVITYTYPE_JZBQC;
                break;
            //竞彩足球 比分
            case "jzbf":
                activityType = Configs.ACTIVITYTYPE_JZBF;
                break;
            //竞彩足球 总进球数
            case "jzjqs":
                activityType = Configs.ACTIVITYTYPE_JZJQS;
                break;
            //竞彩足球 混合过关
            case "jzhh":
                activityType = Configs.ACTIVITYTYPE_JZHH;
                break;

            default:
                break;
        }
        //        if (lotId.equals("jldx")) {
        //            activityType = ACTIVITYTYPE_JCDX;
        //        }
        //        if (lotId.equals("jlrsf")) {
        //            activityType = ACTIVITYTYPE_JLRSF;
        //        }
        //        if (lotId.equals("jzxspf")) {
        //            activityType = ACTIVITYTYPE_JZRSPF;
        //        }
        //        if (lotId.equals("jzspf")) {
        //            activityType = ACTIVITYTYPE_JZSPF;
        //        }
        //        if (lotId.equals("jlsf")) {
        //            activityType = ACTIVITYTYPE_JLSF;
        //        }
        //        if (lotId.equals("sf14") || lotId.equals("sf9")) {
        //            activityType = Configs.ACTIVITYTYPE_R9;
        //        }
        return activityType;
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {

    }
}
