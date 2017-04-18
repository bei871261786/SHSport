package shlottery.gov.cn.lotterycenter.manager;

import android.support.v7.app.AppCompatActivity;

import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.UserBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerBaseDialogUtil;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/15 14:55
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class LoginManager implements LoadCallback<UserBean> {
    private static LoginManager instance = new LoginManager();
    private boolean loginStatus = false;
    private AppCompatActivity activity;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        return instance;
    }

//和服务器核对登陆状态,如果连接不上服务器再根据本地登录状态判定
    public boolean login(AppCompatActivity activity) {
        this.activity = activity;
//        UpdateUserinfoProtocol protocol = new UpdateUserinfoProtocol();
//        protocol.load(this, this);
//        synchronized (this) {
//            try {
//                LogUtils.i("LoginManager wait:");
//                //当前线程等待
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        return loginLocation();
    }

    private boolean loginLocation() {
        //BaseApplication中的登陆状态在进入程序时从sharedperference中得到，并且会在程序中操作登陆退出时改变
        if (!BaseApplication.ismLoginStatus()) {
            SoccerBaseDialogUtil dialogUtil = new SoccerBaseDialogUtil();
            dialogUtil.createLoginDialog(activity);
            return false;
        }
        return true;
    }

    @Override
    public void onSucess(UserBean o) {
        if (o.getRet() == 100) {
            loginStatus = true;
            LogUtils.i("LoginManager notify:");
            UserBean.DataBean dataBean = o.getData();
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
                    mUserInfoBean.setIntegral(dataBean.getIntegral() + "");
                    SharedPreferencesUtils.putObject(Configure.SPKEY_USERINFO, mUserInfoBean);
                    LogUtils.i("UpdateUserinfoService onsuccess 更新成功");
                } catch (Exception e) {
                }
            }
        } else if (o.getMsg().contains("未登录")) {
            loginStatus = false;

        }
    }

    @Override
    public void onError() {

    }
}
