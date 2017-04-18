//package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.protocol;
//
//import com.google.gson.Gson;
//
//import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.bean.TokenBean;
//import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
//import shlottery.gov.cn.lotterycenter.utils.DateUtils;
//import shlottery.gov.cn.lotterycenter.utils.LogUtils;
//import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
//import shlottery.gov.cn.lotterycenter.utils.StringUtils;
//import shlottery.gov.cn.lotterycenter.utils.SystemUtils;
//import shlottery.gov.cn.lotterycenter.utils.UIUtils;
//
///**
// * Created by booob on 2016-08-16-0016.
// */
//public class GetTokenProtocol {
//
//    private String currenttime;
//
//    public String getmToken() {
//        return mToken;
//    }
//
//    public void setmToken(String mToken) {
//        this.mToken = mToken;
//    }
//
//    private String mToken = "";
//
//    //获取token时需要的参数
//    public String getParames() {
//        int mType = 2;//1代表ios,2代表安卓
//        String deviceId = SystemUtils.getIMEI();
//        //版本号
//        String ver = BaseApplication.getApplication().getVersion();
//        String mMobileModle = SystemUtils.getDeviceModel();//部分手机名称可能含有非法字符,可能导致请求地址报错,所以去掉了手机型号
//        String mOSVersion = SystemUtils.getOSVersion();
//        currenttime = DateUtils.formatDateAndTimeS(System.currentTimeMillis());
//        String signKey = "go1234al";
//        String mMD5 = StringUtils.getMD5(signKey + mType + deviceId + ver + mOSVersion + currenttime);
//        String s = "type=" + mType + "&deviceId=" + deviceId + "&ver=" + ver + "&model=" + "&version=" + mOSVersion + "&timestamp=" + currenttime + "&sign=" + mMD5;
//        return s;
//    }
//
//    public void getTokenMethod() {
//        boolean retry = true;
//        int mRet = 0;
//        int mCount = 0;
//        while (retry) {
//            mCount++;
//            String result;
////                HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.TokenURL + "?" + "token=type=1&deviceId=test_118855510&ver=1&model=iPhone&version=ios9.1timestamp="+DateUtils.formatDateAndTimeS(System.currentTimeMillis())+"&sign="+StringUtils.getMD5("go1234al1test_1188555101iPhoneios9.1"  + DateUtils.formatDateAndTimeS(System.currentTimeMillis())));
//            HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.TokenURL + "?" + getParames());
//            String url = HttpHelper.TokenURL + "?" + getParames();
////                String url = HttpHelper.TokenURL + "?" +"token=type=1&deviceId=test_118855510&ver=1&model=iPhone&version=ios9.1timestamp="+DateUtils.formatDateAndTimeS(System.currentTimeMillis())+"&sign="+StringUtils.getMD5("go1234al1test_1188555101iPhoneios9.1"  + DateUtils.formatDateAndTimeS(System.currentTimeMillis()));
//            LogUtils.i(url);
//
//            // HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.URL + getKey() + "?index=" + index + getParames());
//
//            if (httpResult != null) {
//                result = httpResult.getString();
//                Gson gson = new Gson();
//                mToken = gson.fromJson(result, TokenBean.class).getData().getToken();
//
//                mRet = gson.fromJson(result, TokenBean.class).getRet();
//                httpResult.close();
//                LogUtils.e(mToken + "______GetTokenProtocol" + "____token");
//
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.TOKEN_KEY, mToken);
//                PrefUtils.setString(BaseApplication.getApplication(), Configure_JC.GETTOKENTIME, System.currentTimeMillis() + "");
//                /*BaseApplication.getApplication().setProperty("token", mToken);
//                BaseApplication.getApplication().setProperty("getTokenTime", System.currentTimeMillis() + "");
//                BaseApplication.getApplication().setmToken(mToken);*/
//                if (mRet == 100) {
//                    retry = false;
//                }
//            }
//            if (mCount >= 3) {
//                UIUtils.showToastSafe("网络异常,请检查网络");
//                retry = false;
//            }
//        }
//    }
//}
