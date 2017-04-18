package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.manager;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by yongchao.bei on 2016/7/11.
 */
public class ActivityManager {
    private List<Activity> activityList = new LinkedList<Activity>();

    private static ActivityManager instance;

    // 单例模式中获取唯一的MyApplication实例
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    //关闭相应名字的activity
    public void finishActivity(String name) {
        for (int i = 0; i < activityList.size(); i++) {
            String contextString = activityList.get(i).toString();
            String activityName = contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
            if (name.equals(activityName)) {
                activityList.get(i).finish();
                activityList.remove(i);
            }
        }
    }

    //展示所有在管理器中的activity
    public void showAllActivity() {
        for (Activity activity : activityList) {
            String contextString = activity.toString();
            LogUtils.i("activity :" + contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@")));
        }
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
}