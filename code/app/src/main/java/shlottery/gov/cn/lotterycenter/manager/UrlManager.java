package shlottery.gov.cn.lotterycenter.manager;

import android.content.Context;

import com.orhanobut.logger.Logger;

import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.BuildConfig;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.SystemUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 15:22
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class UrlManager {
    public static boolean flag = !BuildConfig.DEBUG;//当flag为true时，为正式地址，为false时，为测试地址
    public static String HostUrl = getHostUrl();
    public static final String TESTURl = "http://vpn.gooooal.com/shtcapi/";//app测试网址
    public static final String RELEASEURL = "http://139.224.133.50/api/";//app正式网址
    public static final String TokenURL = "http://vpn.gooooal.com/cpapi/getToken";//token获取地址
    public static final String DownLoadURL = "http://vpn.gooooal.com/cpweb/download/app.apk";//新版apk下载地址
    public static final String regClient = "regClient";//获取Client的接口 注册客户端
    public static final String sendVerify = "sendVerify";//获取短信验证码的接口
    public static final String getRegister = "user/register";//获取注册的接口
    public static final String getLogin = "user/login";//获取登陆的接口
    public static final String findUser = "user/findUser";//找回密码
    public static final String resetPwd = "user/resetPwd";//重置密码
    public static final String chkPassword = "user/chkPassword";//校验密码
    public static final String chkPwdCode = "user/chkPwdCode";//校验验证码
    public static final String updatePassword = "user/updatePassword";//更改登陆密码
    public static final String getSaleList = "lot/getSaleList";//彩种当前期列表
    public static final String getSaleIssue = "lot/getSaleIssue";//彩种当前期列表
    public static final String getSaleMatch = "lot/getSaleMatch";//彩种当前期列表
    public static final String getIssuebonusList = "lot/getBonusList";//开奖列表
    public static final String getBonusDetail = "lot/getBonusDetail";//开奖详情列表
    public static final String getSignin = "user/signIn";//用户签到
    public static final String updateNickName = "user/updateNickName";//修改昵称
    public static final String getUserInfo = "user/getInfo";//获取用户信息
    public static final String chkVersion = "chkVersion";//获取用户信息
    public static final String getIntegralList = "user/getIntegralList";//积分查询
    public static final String getVoucherList = "user/getVoucherList";//代金券查询
    public static final String getExchangeVoucher = "user/getExchangeVoucher";//可兑换代金券查询
    public static final String voucherExchange = "user/voucherExchange";//代金券兑换
    public static final String getPushItem = "getPushItem";//获取推送设置(根据设备不根据用户)
    //    public static final String getUserPushItem = "user/getPushItem";//获取推送设置(根据用户)
    public static final String savePushItem = "savePushItem";//保存推送设置
    //    public static final String saveUserPushItem = "user/savePushItem";//保存推送设置根据用户)
    public static final String getStakeDetail = "user/getStakeDetail";//投注明细
    public static final String getBonusIssue = "lot/getBonusIssue";//开奖期号列表
    public static final String getLiveIssue = "match/getLiveIssue";//赛事开奖期号列表
    public static final String calcBonus = "lot/calcBonus";//奖金计算
    public static final String getJczqLive = "match/getJczqLive";//足球赛事列表
    public static final String getJclqLive = "match/getJclqLive";//蓝球赛事列表
    public static final String getSuperGList = "chaog/getList";//超G竞彩列表
    public static final String getSuperDetail = "chaog/getDetail";//超G竞彩详情
    public static final String getGuest = "chaog/getGuest";//超G竞彩嘉宾名单
    public static final String getFamousList = "recom/getList";//专家看彩
    public static final String getFamousDetail = "recom/getDetail";//专家看彩详情
    public static final String getList = "site/getList";//网点导航查询列表

    public static final String getMatchList = "lot/getMatchList";//获取奖期对阵的接口
    public static final String getMatchList2x1 = "lot/getMatchList2x1";//获取2选1的接口
    public static final String getIssueBonus = "lot/getIssueBonus";//获取开奖数据的接口
    public static final String addStake = "lot/addStake";//保存注单的接口
    public static final String addScheme = "lot/addScheme";//获取保存投注的接口
    public static final String getInfo = "user/getInfo";//获取用户信息的接口
    public static final String getAccount = "user/etAccount";//获取账户信息的接口
    public static final String updateUserLogo = "user/updateUserLogo";//获取更换头像的接口
    public static final String getChangeList = "account/getChangeList";//获账户变动的接口
    public static final String updateUserInfo = "user/updateUserInfo";//修改用户信息(真实姓名，身份证号)
    public static final String updatePhone = "user/updatePhone";//更改大奖号码
    public static final String getNewsList = "news/getList";//获取新闻列表
    public static final String getNewsDetail = "news/getDetail";//获取新闻详情
    public static final String getNewsTags = "news/getTags";//获取新闻标签
    public static final String getJczqMatchInfo = "match/getJczqMatchInfo";//获取足球比赛信息
    public static final String getDelStake = "user/delStake";//删除注单
    public static final String getJclqMatchInfo = "match/getJclqMatchInfo";//获取足球比赛信息
    public static final String getrecomendStake = "recom/addStake";//推荐投注
    public static final String getSubscribeList = "user/getSubscribeList";//订阅列表
    public static final String userdelSubscribe = "user/delSubscribe";//订阅信息删除
    public static final String regPushToken = "regPushToken";//保存推送的注册号
    public static final String getBootPic = "getBootPic";//广告页

    public static final String updatePayPassword = "account/updatePayPwd";//更改支付密码
    public static final String getVoucher = "account/getVoucherList";//获得彩金券接口
    public static final String getBankList = "account/getBankList";//获得彩金券接口
    public static final String getHome = "getHome";//获得首页数据
    public static final String getBankCity = "data/getBankCity";//获得银行城市列表
    public static final String addBank = "account/addBank";//绑定银行卡
    public static final String delBank = "account/delBank";//删除银行卡
    public static final String setBankDefault = "account/setBankDefault";//设置默认银行卡
    public static final String getgetBonusIssueDate = "lot/getBonusIssueList";//获得开奖期号
    public static final String getgsetPayPwd = "account/setPayPwd";//设置支付密码
    public static final String getStakeList = "user/getStakeList";//投注记录
    public static final String addSubscribe = "recom/addSubscribe";//订阅
    public static final String delSubscribe = "recom/delSubscribe";//取消订阅
    public static final String getMyQuestions = "qa/getMyList";//我的问题
    public static final String addQuestion = "qa/addQuestion";//客服反馈
    public static final String getLotList = "lot/getLotList";//客服反馈
    public static final String getlogout = "user/logout";//退出登录

    public static final String getBonusList = "user/getBonusList";//中奖记录
    public static final String getSchemeList = "user/getSchemeList";//方案记录
    public static final String getSchemeDetail = "user/getSchemeDetail";//方案明细
    public static final String delScheme = "user/delScheme";//方案删除
    public static final String addSchemeStake = "user/addSchemeStake";//方案投注
    public static final String getChartCode = "getChartCode";//获取图形验证码
    public static final String chkhartCode = "chkChartCode";//校验图形验证码
    public static final String drawMoney = "account/drawMoney";//提现
    private static String currenttime;
    public static String signKey = "shgo12al";
    public static String secret = "";

    public static String getHostUrl() {
        return getUrl(flag);
    }

    //获取地址:当flag为false时,使用测试地址,为true是使用正式地址
    public static String getUrl(boolean flag) {
        String s;
        if (flag) {
            s = RELEASEURL;
        } else {
            s = TESTURl;
        }
        return s;
    }

    public static String url = HostUrl + regClient;

    //获取token时需要的参数
    public static String getParames() {
        int mType = 2;//1代表ios,2代表安卓
        String deviceId = SystemUtils.getMyUUID();
        String pushToken = (String) SharedPreferencesUtils.get(BaseApplication.getApplication(), "registrationId", "");
        Logger.e(deviceId + "UUid");
        //版本号
        String ver = BaseApplication.getVersionCode() + "";
        String mMobileModle = SystemUtils.getDeviceModel();//部分手机名称可能含有非法字符,可能导致请求地址报错,所以去掉了手机型号
        String mOSVersion = SystemUtils.getOSVersion();
        currenttime = DateUtils.formatDateAndTimeS(System.currentTimeMillis());

        String mMD5 = StringUtils.getMD5(signKey + mType + deviceId + mMobileModle + mOSVersion + ver + pushToken +
                currenttime);
        String s = "type=" + mType + "&deviceId=" + deviceId + "&model=" + mMobileModle + "&version=" + mOSVersion +
                "&verNum=" + ver + "&pushToken=" + pushToken + "&timestamp=" + currenttime + "&sign=" + mMD5;
        LogUtils.i("UrlManager params:" + s);
        return s;
    }

    //获取数字彩数据
    public static String getSaleIssueUrl(Context mContext, String lotid) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + lotid);
        LogUtils.i("urlmanager params:" + Client + ":::" + sign);
        return UrlManager.HostUrl + UrlManager.getSaleIssue + "?client=" + Client + "&lotId=" + lotid + "&sign=" + sign;
    }

    //获取验证码
    public static String getVcodeUrl(Context mContext, int type, String phoneNum) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + phoneNum + type);
        //type类型(1-注册 2-找回密码 3-修改密码)
        LogUtils.i("urlmanager params:" + Client + ":::" + sign);
        return UrlManager.HostUrl + UrlManager.sendVerify + "?client=" + Client + "&mobile=" + phoneNum + "&type=" +
                type + "&sign=" + sign;
    }

    public static String getPassword(String phoneNum, String password) {
        return StringUtils.getMD5(phoneNum + password);
    }

    //注册账号
    public static String getRegister(Context mContext, String phoneNum, String smsCode, String nickName, String
            password) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        password = getPassword(phoneNum, password);
        String sign = StringUtils.getMD5(Client + signKey + phoneNum + smsCode + nickName + password);
        //type类型(1-注册 2-找回密码 3-修改密码)
        return UrlManager.HostUrl + UrlManager.getRegister + "?client=" + Client + "&mobile=" + phoneNum +
                "&smsCode=" + smsCode + "&nickName=" + nickName + "&password=" + password + "&sign=" + sign;
    }

    //校验原始密码
    public static String chkPassword(Context mContext, String phoneNum, String password) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        initSecret(mContext);
        password = getPassword(phoneNum, password);
        String sign = StringUtils.getMD5(Client + secret + signKey + password);

        return UrlManager.HostUrl + UrlManager.chkPassword + "?client=" + Client + "&password=" + password + "&sign="
                + sign;
    }

    //校验验证码
    public static String chkPwdCode(Context mContext, String smsCode) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        initSecret(mContext);
        String sign = StringUtils.getMD5(Client + secret + signKey + smsCode);

        return UrlManager.HostUrl + UrlManager.chkPwdCode + "?client=" + Client + "&smsCode=" + smsCode + "&sign=" +
                sign;
    }

    //初始化secret
    private static void initSecret(Context mContext) {
        UserInfoBean infoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        if (infoBean != null && infoBean.getSecret() != null) {
            secret = infoBean.getSecret();
        }
    }


    //修改登录密码
    public static String updatePassword(Context mContext, String phoneNum, String oldPwd, String newPwd, String
            smsCode) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        oldPwd = getPassword(phoneNum, oldPwd);
        newPwd = getPassword(phoneNum, newPwd);
        initSecret(mContext);
        String sign = StringUtils.getMD5(Client + secret + signKey + oldPwd + newPwd + smsCode);
        //type类型(1-注册 2-找回密码 3-修改密码)
        //        return UrlManager.HostUrl + UrlManager.getRegister + "?client=" + Client + "&mobile=" + phoneNum +
        // "&smsCode=" + smsCode + "&nickName=" + nickName + "&password=" + password + "&sign=" + sign;
        return UrlManager.HostUrl + UrlManager.updatePassword + "?client=" + Client + "&oldPwd=" + oldPwd +
                "&newPwd=" + newPwd + "&smsCode=" + smsCode + "&sign=" + sign;
    }

    //校验验证码
    public static String findUser(Context mContext, String phoneNum, String smsCode) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + phoneNum + smsCode);

        return UrlManager.HostUrl + UrlManager.findUser + "?client=" + Client + "&mobile=" + phoneNum + "&smsCode=" +
                smsCode + "&sign=" + sign;
    }

    //重置密码
    public static String resetPwd(Context mContext, String phoneNum, String passWord, String smsCode) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        passWord = getPassword(phoneNum, passWord);

        String sign = StringUtils.getMD5(Client + signKey + phoneNum + smsCode + passWord);
        //type类型(1-注册 2-找回密码 3-修改密码)
        //        return UrlManager.HostUrl + UrlManager.getRegister + "?client=" + Client + "&mobile=" + phoneNum +
        // "&smsCode=" + smsCode + "&nickName=" + nickName + "&password=" + password + "&sign=" + sign;
        return UrlManager.HostUrl + UrlManager.resetPwd + "?client=" + Client + "&mobile=" + phoneNum + "&smsCode=" +
                smsCode + "&password=" + passWord + "&sign=" + sign;
    }

    //上传头像
    public static String updateUserLogo() {
        return UrlManager.HostUrl + UrlManager.updateUserLogo;
    }

    //检查新版本

    /**
     * @param mContext
     * @param type     app类型 1为ios,2为android
     * @param verNum   当前版本号
     * @return 返回地址
     */
    public static String chkVersion(Context mContext, int type, String verNum) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + type + verNum);
        return UrlManager.HostUrl + UrlManager.chkVersion + "?client=" + Client + "&type=" + type + "&verNum=" +
                verNum + "&sign=" + sign;
    }


  /*  //注单保存
    public static String addStake(Context mContext, String lotId, String issueNo, String multiple, String stakeAdd,
    String totalMoney, String stakeCode) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        initSecret(mContext);
        String sign = StringUtils.getMD5(Client + secret + signKey + lotId + issueNo + multiple + stakeAdd +
        totalMoney + stakeCode);
        return UrlManager.HostUrl + UrlManager.addStake + "?client=" + Client + "&lotId=" + lotId + "&issueNo=" +
        issueNo + "&multiple=" + "&stakeAdd=" + stakeAdd + "&totalMoney=" + totalMoney + "&stakeCode=" + stakeCode +
        "&sign=" + sign;
    }*/

    //注单保存
    public static String addStake() {
        return UrlManager.HostUrl + UrlManager.addStake;
    }

    //获取推送设置详情
    public static String getPushItem(Context mContext) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        String sign = StringUtils.getMD5(Client + signKey);
        return UrlManager.HostUrl + UrlManager.getPushItem + "?client=" + Client + "&sign=" + sign;
    }

    //保存 推送设置
    public static String savePushItem(Context mContext, String push) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + push);
        return UrlManager.HostUrl + UrlManager.savePushItem + "?client=" + Client + "&push=" + push + "&sign=" +
                sign;
    }

    //投注明细
    public static String getStakeDetail(Context mContext, String stakeId) {
        String Client = PrefUtils.getString(mContext, "Client", "");
        initSecret(mContext);
        String sign = StringUtils.getMD5(Client + secret + signKey + stakeId);
        return UrlManager.HostUrl + UrlManager.getStakeDetail + "?client=" + Client + "&stakeId=" + stakeId +
                "&sign=" + sign;
    }

    //开奖列表
    public static String getIssuebonusList(Context mContext) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        initSecret(mContext);
        String sign = StringUtils.getMD5(Client + signKey);
        return UrlManager.HostUrl + UrlManager.getIssuebonusList + "?client=" + Client + "&sign=" + sign;
    }

    //开奖期号
    public static String getBonusIssue(Context mContext, String lotId) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + lotId);
        return UrlManager.HostUrl + UrlManager.getBonusIssue + "?client=" + Client + "&lotId=" + lotId + "&sign=" +
                sign;
    }

    //开奖期号
    public static String getLiveIssue(Context mContext, String lotId) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + lotId);
        return UrlManager.HostUrl + UrlManager.getLiveIssue + "?client=" + Client + "&lotId=" + lotId + "&sign=" + sign;
    }

    //某一期号对应的开奖详细数据
    public static String getBonusDetail(Context mContext, String lotId, String issueNo) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + lotId + issueNo);
        return UrlManager.HostUrl + UrlManager.getBonusDetail + "?client=" + Client + "&lotId=" + lotId + "&issueNo="
                + issueNo + "&sign=" + sign;
    }

    //赛事获取  type==0 时为足球赛事  等于1时篮球赛事
    public static String getJcLive(Context mContext, int type, String issueNo) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + issueNo);
        if (type == 0) {
            return UrlManager.HostUrl + UrlManager.getJczqLive + "?client=" + Client + "&issueNo=" + issueNo +
                    "&sign=" + sign;
        } else {
            return UrlManager.HostUrl + UrlManager.getJclqLive + "?client=" + Client + "&issueNo=" + issueNo +
                    "&sign=" + sign;
        }

    }

    //奖金计算
    public static String calcBonus() {
        return UrlManager.HostUrl + UrlManager.calcBonus;
    }


    //足球详情
    public static String getJczqMatchInfo(Context mContext, String id) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + id);
        return UrlManager.HostUrl + UrlManager.getJczqMatchInfo + "?client=" + Client + "&id=" + id + "&sign=" + sign;
    }

    //篮球详情
    public static String getJclqMatchInfo(Context mContext, String id) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + id);
        return UrlManager.HostUrl + UrlManager.getJclqMatchInfo + "?client=" + Client + "&id=" + id + "&sign=" + sign;
    }

    //网点导航查询列表
    public static String getList(String keyword, String latitude, String longitude, String pageNo, String pageSize) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String sign = StringUtils.getMD5(Client + signKey + keyword + latitude + longitude + pageNo + pageSize);
        return UrlManager.HostUrl + UrlManager.getList + "?client=" + Client + "&keyword=" + keyword + "&latitude=" +
                latitude + "&longitude=" + longitude + "&pageNo=" + pageNo + "&pageSize=" + pageSize + "&sign=" + sign;
    }

    //获取首页
    public static String getHome() {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String sign = StringUtils.getMD5(Client + signKey);
        return UrlManager.HostUrl + UrlManager.getHome + "?client=" + Client + "&sign=" + sign;
    }

    //获取订阅列表
    public static String getSubscribeList(String type, String pageNo, String pageSize) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        initSecret(BaseApplication.getApplication());
        String sign = StringUtils.getMD5(Client + secret + signKey + type + pageNo + pageSize);
        return UrlManager.HostUrl + UrlManager.getSubscribeList + "?client=" + Client + "&type=" + type + "&pageNo="
                + pageNo + "&pageSize=" + pageSize + "&sign=" + sign;
    }

    //删除订阅条目
    public static String userdelSubscribe(String id) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        initSecret(BaseApplication.getApplication());
        String sign = StringUtils.getMD5(Client + secret + signKey + id);
        return UrlManager.HostUrl + UrlManager.userdelSubscribe + "?client=" + Client + "&id=" + id + "&sign=" + sign;
    }

    //保存推送的注册号
    public static String regPushToken(String pushToken) {
        String Client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        initSecret(BaseApplication.getApplication());
        String sign = StringUtils.getMD5(Client + signKey + pushToken);
        return UrlManager.HostUrl + UrlManager.regPushToken + "?client=" + Client + "&pushToken=" + pushToken +
                "&sign=" + sign;
    }


}