package shlottery.gov.cn.lotterycenter.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.UserBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.protool.UpdateUserinfoProtocol;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;

public class UpdateLoginStatusService extends Service implements LoadCallback<UserBean> {
    private Timer timer = new Timer();

    public UpdateLoginStatusService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i("updateLoginstatus onStartCommand");
        startTimeTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onSucess(UserBean o) {
        if (o.getRet() == 100) {
            LogUtils.i("updateLoginstatus onSucess");
            UserBean.DataBean dataBean = o.getData();
            BaseApplication.setmLoginStatus(true);
            if (dataBean != null) {
                UserInfoBean mUserInfoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
                if (null == mUserInfoBean) {
                    mUserInfoBean = new UserInfoBean();
                }
                try {
                    LogUtils.i("UpdateUserinfoService mUserInfoBean:" + dataBean + ":::" + mUserInfoBean);
                    mUserInfoBean.setLogoUrl(dataBean.getLogoUrl());
                    mUserInfoBean.setMobile(dataBean.getMobile());
                    mUserInfoBean.setNickName(dataBean.getNickName());
                    mUserInfoBean.setRealName(dataBean.getRealName());
                    mUserInfoBean.setUserType(dataBean.getUserType());
                    mUserInfoBean.setSignIn(dataBean.isSignIn());
                    mUserInfoBean.setSiteRegUrl(dataBean.getSiteRegUrl());
                    mUserInfoBean.setIntegral(dataBean.getIntegral() + "");
                    mUserInfoBean.setIdNumber(dataBean.getIdNumber() + "");
                    SharedPreferencesUtils.putObject(Configure.SPKEY_USERINFO, mUserInfoBean);
                    LogUtils.i("UpdateUserinfoService onsuccess 更新成功");
                } catch (Exception e) {
                }
            }
        } else if (o.getMsg().contains("未登录")) {
            LogUtils.i("updateLoginstatus 未登录");
            BaseApplication.setmLoginStatus(false);
            clearSecret();
        } else {
            LogUtils.i("updateLoginstatus " + o.getMsg());
        }
    }

    @Override
    public void onError() {
        LogUtils.i("updateLoginstatus onError");
    }

    private void startTimeTask() {
        LogUtils.i("updateLoginstatus startTimeTask");
        // 更新 倒计时以及期号
        if (null != timer) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    LogUtils.i("updateLoginstatus schedule");
                    UpdateUserinfoProtocol protocol = new UpdateUserinfoProtocol();
                    protocol.load(this, UpdateLoginStatusService.this);
                }
            }, 10000, 2 * 60 * 1000);
        }
    }

    private void clearSecret() {
        UserInfoBean mUserInfoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        if (null != mUserInfoBean) {
            mUserInfoBean.setSecret("");
        }
        SharedPreferencesUtils.putObject(Configure.SPKEY_USERINFO, mUserInfoBean);
    }

}
