package shlottery.gov.cn.lotterycenter.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.UserBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.protool.UpdateUserinfoProtocol;
import shlottery.gov.cn.lotterycenter.ui.view.CircleImageView;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.NetUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

import static shlottery.gov.cn.lotterycenter.R.color.black_gray1;
import static shlottery.gov.cn.lotterycenter.R.color.dantuo_zhushi_blue;

public class UpdateUserinfoService extends Service implements LoadCallback<UserBean> {
    private TextView userinfoNicknameTv;
    private TextView userinfo_phoneNumber_tv;
    private TextView userinfo_jifen_tv;
    private TextView userinfo_realname_tv;
    private TextView userinfo_idnumber_tv;
    private CircleImageView userinfoIconIv;
    private TextView dingyueMessage;
    private TextView singinTv;


    public UpdateUserinfoService() {
    }

    //通过start启动服务调用的处理方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i("UpdateUserinfoService threadName:" + Thread.currentThread().getName());
        updateSharedUserinfo();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i("UpdateUserinfoService onBind:");
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    private void updateSharedUserinfo() {
        UpdateUserinfoProtocol protocol = new UpdateUserinfoProtocol();
        protocol.load(this, this);

    }

    public void excute() {
        LogUtils.i("UpdateUserinfoService excute:");
        updateSharedUserinfo();
    }

    @Override
    public void onSucess(UserBean userBean) {
        UserBean.DataBean dataBean = userBean.getData();
        UserInfoBean mUserInfoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        if (null == mUserInfoBean) {
            mUserInfoBean = new UserInfoBean();
        }
        try {
            LogUtils.i("UpdateUserinfoService mUserInfoBean:" + dataBean + ":::" + mUserInfoBean);
            mUserInfoBean.setLogoUrl(dataBean.getLogoUrl());
            mUserInfoBean.setMobile(dataBean.getMobile());
            mUserInfoBean.setNickName(dataBean.getNickName());
            mUserInfoBean.setUserType(dataBean.getUserType());
            mUserInfoBean.setIntegral(dataBean.getIntegral() + "");
            mUserInfoBean.setSubscribeNum(dataBean.getSubscribeNum());
            mUserInfoBean.setSiteRegUrl(dataBean.getSiteRegUrl());
            mUserInfoBean.setRealName(dataBean.getRealName());
            mUserInfoBean.setIdNumber(dataBean.getIdNumber());
            mUserInfoBean.setSignIn(dataBean.isSignIn());
            SharedPreferencesUtils.putObject(Configure.SPKEY_USERINFO, mUserInfoBean);
            LogUtils.i("UpdateUserinfoService onsuccess 更新成功");
            UserInfoBean bean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
            if (null != userinfoNicknameTv) {
                LogUtils.i("UpdateUserinfoService nickName:" + dataBean.getNickName() + "::::" + bean.getNickName());
                userinfoNicknameTv.setText(bean.getNickName());
            }
            if (null != userinfo_phoneNumber_tv) {
                userinfo_phoneNumber_tv.setText(bean.getMobile());
            }
            if (null != singinTv) {
                if (bean.isSignIn()) {
                    singinTv.setText("已签到");
                } else {
                    singinTv.setText("签到");
                }

            }
            if (null != userinfoIconIv) {
                LogUtils.i("UpdateUserinfoService logo:" + bean.getLogoUrl());
                Picasso.with(this).load(bean.getLogoUrl()).into(userinfoIconIv);
            }
            if (null != userinfo_jifen_tv) {
                userinfo_jifen_tv.setText("积分：" + bean.getIntegral());
            }

            if (!StringUtils.isEmpty(bean.getSubscribeNum()) && !bean.getSubscribeNum().equals("0")) {
                dingyueMessage.setText(bean.getSubscribeNum());
                dingyueMessage.setVisibility(View.VISIBLE);
            } else {
                dingyueMessage.setVisibility(View.INVISIBLE);
            }
            if (null != userinfo_realname_tv) {
                LogUtils.i("UpdateUserinfoService realname:" + bean.getRealName());
                if (bean.getRealName() != null && !bean.getRealName().isEmpty()) {
                    userinfo_realname_tv.setText(bean.getRealName());
                    userinfo_realname_tv.setTextColor(BaseApplication.getApplication().getResources().getColor(black_gray1));
                } else {
                    userinfo_realname_tv.setText("未填写");
                    userinfo_realname_tv.setTextColor(BaseApplication.getApplication().getResources().getColor(dantuo_zhushi_blue));
                }
            }
            if (null != userinfo_idnumber_tv) {
                LogUtils.i("UpdateUserinfoService idnumber:" + bean.getIdNumber());
                if (bean.getIdNumber() != null && !bean.getIdNumber().isEmpty()) {
                    String mIdNumber = bean.getIdNumber();
                    String num1 = "";
                    StringBuilder temp = new StringBuilder();
                    if (mIdNumber.length() > 7) {
                        for (int i = 0; i < mIdNumber.length() - 7; i++) {
                            temp.append("*");
                        }
                    }
                    num1 = temp.toString();
                    String num2 = mIdNumber.substring(0, 3);
                    String num3 = mIdNumber.substring(mIdNumber.length() - 4, mIdNumber.length());
                    String number = num2 + num1 + num3;

                    userinfo_idnumber_tv.setText(number);
                    userinfo_idnumber_tv.setTextColor(BaseApplication.getApplication().getResources().getColor(black_gray1));
                } else {
                    userinfo_idnumber_tv.setText("未填写");
                    userinfo_idnumber_tv.setTextColor(BaseApplication.getApplication().getResources().getColor(dantuo_zhushi_blue));
                }
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void onError() {
        if (!NetUtils.isConnected(BaseApplication.getApplication())) {
            updateByLocal();
        }
        LogUtils.i("UpdateUserinfoService onsuccess 更新失败怪我咯");
    }


    //更新个人资料中信息
    public void init(TextView userinfoNicknameTv, TextView userinfo_phoneNumber_tv, CircleImageView userinfoIconIv, TextView userinfo_realname_tv, TextView userinfo_idnumber_tv) {
        UpdateUserinfoService.this.userinfoNicknameTv = userinfoNicknameTv;
        UpdateUserinfoService.this.userinfo_phoneNumber_tv = userinfo_phoneNumber_tv;
        UpdateUserinfoService.this.userinfoIconIv = userinfoIconIv;
        UpdateUserinfoService.this.userinfo_realname_tv = userinfo_realname_tv;
        UpdateUserinfoService.this.userinfo_idnumber_tv = userinfo_idnumber_tv;
    }

    //更新用户中心信息
    public void init(TextView userinfoNicknameTv, CircleImageView userinfoIconIv, TextView userinfo_jifen_tv, TextView dingyueMessage, TextView singinTv) {
        UpdateUserinfoService.this.userinfoNicknameTv = userinfoNicknameTv;
        UpdateUserinfoService.this.userinfo_jifen_tv = userinfo_jifen_tv;
        UpdateUserinfoService.this.userinfoIconIv = userinfoIconIv;
        UpdateUserinfoService.this.dingyueMessage = dingyueMessage;
        UpdateUserinfoService.this.singinTv = singinTv;
    }

    private void updateByLocal() {
        UserInfoBean mUserinfoData = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        if (null != mUserinfoData) {
            String logoUrl = "";
            String nickName = "";
            String mobile = "";
            String jifen = "";
            if (mUserinfoData.getNickName() != null && userinfoNicknameTv != null) {
                nickName = mUserinfoData.getNickName();
                userinfoNicknameTv.setText(nickName);
            }
            if (mUserinfoData.getMobile() != null && userinfo_phoneNumber_tv != null) {
                mobile = mUserinfoData.getMobile();
                userinfo_phoneNumber_tv.setText(nickName);
            }
            if (mUserinfoData.getIntegral() != null && userinfo_jifen_tv != null) {
                jifen = mUserinfoData.getIntegral();
                userinfo_jifen_tv.setText("积分：" + jifen);
            }
            if (mUserinfoData.getLogoUrl() != null && userinfoIconIv != null) {
                logoUrl = mUserinfoData.getLogoUrl();
                Picasso.with(BaseApplication.getApplication()).load(logoUrl).into(userinfoIconIv);
            }
            if (dingyueMessage != null) {
                if (!StringUtils.isEmpty(mUserinfoData.getSubscribeNum()) && !mUserinfoData.getSubscribeNum().equals("0")) {
                    dingyueMessage.setText(mUserinfoData.getSubscribeNum());
                    dingyueMessage.setVisibility(View.VISIBLE);
                } else {
                    dingyueMessage.setVisibility(View.INVISIBLE);
                }
            }
            LogUtils.i("UsercentrFragment updateUserInfo" + mUserinfoData.toString());
        } else {
            LogUtils.i("UsercentrFragment updateUserInfo  null");
        }

    }

    public class MyBinder extends Binder {
        public UpdateUserinfoService getService() {
            return UpdateUserinfoService.this;
        }
    }
}
