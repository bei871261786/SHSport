package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.BaseMainFragment;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.SigninBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.callback.LoginCallBack;
import shlottery.gov.cn.lotterycenter.protool.LogoutProtocol;
import shlottery.gov.cn.lotterycenter.protool.SigninProtocol;
import shlottery.gov.cn.lotterycenter.service.UpdateUserinfoService;
import shlottery.gov.cn.lotterycenter.ui.activity.AccountActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.SubscriberActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.SystemSettingActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.UserDataActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.UsercenterOrderActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.WdRegistActivity;
import shlottery.gov.cn.lotterycenter.ui.view.CircleImageView;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
public class UserCenterFragment extends BaseMainFragment implements LoadCallback {
    private RelativeLayout mDataLayout;
    private RelativeLayout mAccountLayout;
    private RelativeLayout mSubscribeLayout;
    private RelativeLayout mOrderLayout;
    private RelativeLayout mManagerLayout;
    private RelativeLayout mQrCoderLayout;
    private RelativeLayout mWddjrLayout;
    private View mSigninLayout;
    private Button mExit;
    private LoginCallBack mLoginCallback;
    private MyListener mListener;
    private UserInfoBean mUserinfoData;
    private CircleImageView mUserLogo;
    private TextView mNicknameTv;
    private TextView mJifenTv;
    private SigninBean mSignInfo;
    private ImageView mSetting;
    private TextView dingyueMessage;
    private TextView mSignTxt;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtils.i("UsercentrFragment onServiceConnected");
            UpdateUserinfoService service = ((UpdateUserinfoService.MyBinder) iBinder).getService();
            service.init(mNicknameTv, mUserLogo, mJifenTv, dingyueMessage,mSignTxt);
            service.excute();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtils.i("UsercentrFragment onServiceDisconnected");
        }
    };

    @Override
    protected View createLoadedView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_user_center, null);
        mExit = (Button) view.findViewById(R.id.usercenter_exit);
        mUserLogo = (CircleImageView) view.findViewById(R.id.userinfo_icon_iv);
        mJifenTv = (TextView) view.findViewById(R.id.usercenter_jifen);
        mSignTxt = (TextView) view.findViewById(R.id.usercenter_signin_text);
        mNicknameTv = (TextView) view.findViewById(R.id.userinfo_nickName);
        mSigninLayout = view.findViewById(R.id.usercenter_signin_layout);
        mDataLayout = (RelativeLayout) view.findViewById(R.id.userinfo_data_rl);
        mAccountLayout = (RelativeLayout) view.findViewById(R.id.userinfo_account_rl);
        mSubscribeLayout = (RelativeLayout) view.findViewById(R.id.userinfo_subscribe_rl);
        mOrderLayout = (RelativeLayout) view.findViewById(R.id.userinfo_order_rl);
        mWddjrLayout = (RelativeLayout) view.findViewById(R.id.userinfo_wd_rl);
        mManagerLayout = (RelativeLayout) view.findViewById(R.id.userinfo_manager_rl);
        mQrCoderLayout = (RelativeLayout) view.findViewById(R.id.userinfo_qrcode_rl);
        mSetting = (ImageView) view.findViewById(R.id.main_setting);
        dingyueMessage = (TextView) view.findViewById(R.id.dingyue_message_tv);
        mLoginCallback = (LoginCallBack) getActivity();
        mListener = new MyListener();
        mExit.setOnClickListener(mListener);
        mSigninLayout.setOnClickListener(mListener);
        mDataLayout.setOnClickListener(mListener);
        mAccountLayout.setOnClickListener(mListener);
        mSubscribeLayout.setOnClickListener(mListener);
        mOrderLayout.setOnClickListener(mListener);
        mManagerLayout.setOnClickListener(mListener);
        mQrCoderLayout.setOnClickListener(mListener);
        mUserLogo.setOnClickListener(mListener);
        mSetting.setOnClickListener(mListener);
        mWddjrLayout.setOnClickListener(mListener);
        mSetting.setVisibility(View.VISIBLE);
        updateUserInfo();
        return view;
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    @Override
    protected void initTitlebar() {
        mFiltrate.setVisibility(View.GONE);
        mCalendar.setVisibility(View.GONE);
        mSetting.setVisibility(View.VISIBLE);
    }

    private void updateSignJifen(SigninBean signInfo) {
        if (null != signInfo) {
            SigninBean.DataBean bean = signInfo.getData();
            if (bean != null) {
                mJifenTv.setText("积分：" + bean.getIntegral() + "");
                showSignDialog(bean.getGetIntegral() + "", true);
            } else {
                showSignDialog(signInfo.getMsg(), false);
            }
            Intent intent = new Intent(getActivity(), UpdateUserinfoService.class);
            getActivity().startService(intent);
        }
    }

    private void showSignDialog(String integral, boolean isSuccess) {
        String msg = "";
        if (isSuccess) {
            msg = "获赠" + integral + "积分";
        } else {
            msg = integral;
        }
        UIUtils.showToastSafe(msg);
    }

    private void updateUserInfo() {
        LogUtils.i("UsercentrFragment updateUserInfo");
//        if (isServiceRunning("shlottery.gov.cn.lotterycenter.service.UpdateUserinfoService")) {
        try {
            getActivity().unbindService(connection);
        } catch (Exception e) {

        }
//        }
        Intent intent = new Intent(getActivity(), UpdateUserinfoService.class);
        getActivity().startService(intent);
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void beginSignin() {
        SigninProtocol protocol = new SigninProtocol(getActivity());
        protocol.load(this, this);
    }

    @Override
    public void onSucess(Object signinBean) {
        if (signinBean instanceof SigninBean) {
            mSignInfo = (SigninBean) signinBean;
            updateSignJifen(mSignInfo);
            toggleSignStatus(mSignInfo.getMsg());
        }
        if (signinBean instanceof VcodeBean) {
            if (((VcodeBean) signinBean).getRet() == 100) {
                clearSecret();
                mLoginCallback.toggleLoginFragment(false);
            }
        }
    }

    @Override
    public void onError() {
        LogUtils.i("UsercenterFragment onerro");
    }

    private void toggleSignStatus(String msg) {
        if (msg != null) {
            if (msg.contains("签到过")) {
                mSignTxt.setText("已签到");
            } else {
                mSignTxt.setText("签到");
            }
        }
    }

    private void beginLogout() {
        LogoutProtocol protocol = new LogoutProtocol(getActivity());
        protocol.load(this, this);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.main_setting:
                    startActivity(new Intent(getActivity(), SystemSettingActivity.class));
                    break;
                case R.id.usercenter_exit:
                    beginLogout();
                    break;
                case R.id.usercenter_signin_layout:
                    beginSignin();
                    break;
                case R.id.userinfo_account_rl:
                    startActivityForResult(new Intent(getActivity(), AccountActivity.class), 0);
                    break;
                case R.id.userinfo_data_rl:
                    startActivityForResult(new Intent(getActivity(), UserDataActivity.class), 0);
                    break;
                case R.id.userinfo_order_rl:
                    startActivity(new Intent(getActivity(), UsercenterOrderActivity.class));
                    break;
                case R.id.userinfo_manager_rl:
                    break;
                case R.id.userinfo_subscribe_rl:
                    startActivityForResult(new Intent(getActivity(), SubscriberActivity.class), 0);
                    break;
                case R.id.userinfo_qrcode_rl:
                    break;
                case R.id.userinfo_wd_rl:
                    startActivityForResult(new Intent(getActivity(), WdRegistActivity.class), 0);
                    break;
                case R.id.userinfo_icon_iv:
                    startActivityForResult(new Intent(getActivity(), UserDataActivity.class), 0);
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.i("UsercentrFragment onActivityResult" + resultCode);
        if (resultCode == Configure.RESULT_UPDATE) {
            updateUserInfo();
        }
    }

    private void clearSecret() {
        UserInfoBean mUserInfoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        if (null != mUserInfoBean) {
            mUserInfoBean.setSecret("");
        }
        SharedPreferencesUtils.putObject(Configure.SPKEY_USERINFO, mUserInfoBean);
    }

    private boolean isServiceRunning(String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    @Override
    protected String getTitle() {
        return "用户中心";
    }
}
