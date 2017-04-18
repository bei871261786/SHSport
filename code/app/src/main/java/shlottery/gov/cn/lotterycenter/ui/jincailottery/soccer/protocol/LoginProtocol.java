//package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.protocol;
//
//import android.content.Context;
//
//import com.google.gson.Gson;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.bean.RegistBean;
//import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
//import shlottery.gov.cn.lotterycenter.utils.LogUtils;
//import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
//import shlottery.gov.cn.lotterycenter.utils.StringUtils;
//import shlottery.gov.cn.lotterycenter.utils.UIUtils;
//
///**
// * Created by booob on 2016-08-16-0016.
// */
//public class LoginProtocol {
//
//    private String mToken = BaseApplication.getApplication().getmToken();
//    protected String signkey = BaseApplication.signKey;
//    protected String phoneNum;
//    private String mPassword;
//
//    public int RequestLogin(Context mContext) {
//        List<NameValuePair> params = new ArrayList<>();
//        mPassword = PrefUtils.getString(mContext, Configure_JC.SECRET_PSW, "");
//        phoneNum = PrefUtils.getString(mContext, Configure_JC.MOBILE_KEY, "");
//
//        String sign = StringUtils.getMD5(mToken + signkey + phoneNum + mPassword);
//        LogUtils.e(mToken + signkey + phoneNum + mPassword + ":" + "参数");
//        LogUtils.e(sign);
//        params.add(new BasicNameValuePair("token", mToken));
//        params.add(new BasicNameValuePair("mobile", phoneNum));
//        params.add(new BasicNameValuePair("password", mPassword));
//        params.add(new BasicNameValuePair("sign", sign));
//        String mRegisterUrl = HttpHelper.CeshiJCZQURL + HttpHelper.getLogin;
//        HttpHelper.HttpResult result = HttpHelper.post(mRegisterUrl, params);
//        if (result != null) {
//            LogUtils.e(result.getString() + "登陆返回值");
//            String json = result.getString();
//            Gson gson = new Gson();
//            RegistBean registBean = gson.fromJson(json, RegistBean.class);
//            int mResult = registBean.getRet();
//            if (100 == mResult) {
//                String mSecret = registBean.getData().getSecret();
//                String mUserImageUrl = registBean.getData().getLogoUrl();
//                String mUserNickName = registBean.getData().getNickName();
//                String mAvailMoney = registBean.getData().getAvailMoney();
//                String mMobile = registBean.getData().getMobile();
//                int mVoucherNum = registBean.getData().getVoucherNum();
//                String mHasPayPwd = registBean.getData().getHasPayPwd();
//                int mUserId = registBean.getData().getUserId();
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.MUSERIMAGEURL_KEY, mUserImageUrl);
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.USERNICKNAME_KEY, mUserNickName);
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.AVAILMONEY_KEY, mAvailMoney);
//                PrefUtils.setInt(BaseApplication.getApplication(), Configure_JC.VOUCHERNUM_KEY, mVoucherNum);
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.HASPAYPWD_KEY, mHasPayPwd);
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.MOBILE_KEY, mMobile);
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.SECRET_KEY, mSecret);
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.SECRET_PSW, mPassword);//保存密码
//                BaseApplication.setmSecret(mSecret);//将secret添加到baseApplication中
//                BaseApplication.setmLoginStatus(true);//登录状态
////                BaseApplication.setmUserId(mUserId);//用户id
////                BaseApplication.setmUserPhoneNumber(phoneNum);//用户手机号
////                UIUtils.showToastSafe("登陆成功");
//                LogUtils.e("登陆成功");
//                return 100;
//            } else {
//                String msg = gson.fromJson(json, RegistBean.class).getMsg();
////                UIUtils.showToastSafe(msg);//提示错误信息
//                return 201;
//            }
//        } else {
//            UIUtils.showToastSafe("网络异常,请检查网络");//提示错误信息
//        }
//        return 0;//网络错误}
//    }
//}
