package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import shlottery.gov.cn.lotterycenter.utils.IOUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;


/**
 * Created by booob on 2015/8/7.
 */
public class HttpHelper {

    boolean flag = false;//当flag为true时，为正式地址，为false时，为测试地址
    public static final String CeshiJCZQURL = "http://vpn.gooooal.com/cpapi/";//彩票测试网址
    public static final String TokenURL = "http://vpn.gooooal.com/cpapi/getToken";//token获取地址
    public static final String DownLoadURL = "http://vpn.gooooal.com/cpweb/download/app.apk";//新版apk下载地址

    public static final String getToken = "getToken";//获取token的接口
    public static final String sendVerify = "sendVerify";//获取短信验证码的接口
    public static final String getRegister = "user/register";//获取注册的接口
    public static final String getLogin = "user/login";//获取登陆的接口
    public static final String getMatchList = "lot/getMatchList";//获取奖期对阵的接口
    public static final String getMatchList2x1 = "lot/getMatchList2x1";//获取2选1的接口
    public static final String getIssueBonus = "lot/getIssueBonus";//获取开奖数据的接口
    public static final String addStake = "lot/addStake";//获取彩票投注的接口
    public static final String addScheme = "lot/addScheme";//获取保存投注的接口
    public static final String getInfo = "user/getInfo";//获取用户信息的接口
    public static final String getAccount = "user/etAccount";//获取账户信息的接口
    public static final String updateUserLogo = "user/updateUserLogo";//获取更换头像的接口
    public static final String getChangeList = "account/getChangeList";//获账户变动的接口
    public static final String updateUserInfo = "user/updateUserInfo";//获账户变动的接口
    public static final String updatePhone = "user/updatePhone";//更改大奖号码
    public static final String updatePassword = "user/updatePassword";//更改登陆密码
    public static final String updatePayPassword = "account/updatePayPwd";//更改支付密码
    public static final String getVoucher = "account/getVoucherList";//获得彩金券接口
    public static final String getBankList = "account/getBankList";//获得彩金券接口
    public static final String getHome = "data/getHome";//获得首页数据
    public static final String getBankCity = "data/getBankCity";//获得银行城市列表
    public static final String addBank = "account/addBank";//绑定银行卡
    public static final String delBank = "account/delBank";//删除银行卡
    public static final String setBankDefault = "account/setBankDefault";//设置默认银行卡
    public static final String getgetBonusIssueDate = "lot/getBonusIssueList";//获得开奖期号
    public static final String getgsetPayPwd = "account/setPayPwd";//设置支付密码
    public static final String getStakeList = "user/getStakeList";//投注记录
    public static final String getStakeDetail = "user/getStakeDetail";//投注明细
    public static final String getBonusList = "user/getBonusList";//中奖记录
    public static final String getSchemeList = "user/getSchemeList";//方案记录
    public static final String getSchemeDetail = "user/getSchemeDetail";//方案明细
    public static final String delScheme = "user/delScheme";//方案删除
    public static final String addSchemeStake = "user/addSchemeStake";//方案投注
    public static final String getNewsList = "data/getNewsList";//获取新闻列表
    public static final String drawMoney = "account/drawMoney";//提现
    public static final String findUser = "user/findUser";//找回密码
    public static final String resetPwd = "user/resetPwd";//重置密码


    //竞彩比分地址
    /**
     * 正式地址
     */

    public static final String URL_RUN = "http://www.gooooal.com";
    /**
     * 正式动态比分地址
     */
    public static final String URL_RUN_LIVE = "http://mobile.gooooal.com";


    public static final String getFbMatchJzUrl = "/mobile/app/phone/soccer/jz_score.json";//球比分初始数据-竞彩比分
    public static final String getFbJzScoreUrl = "/score/app/phone/data/soccer/jz_score.do";//球比分动态数据-竞彩比分
    //直播数据-竞彩比分   type为1时,为对阵信息 2为阵容信息 3为事件信息 4为统计信息
    public static final String getFootballMatchLiveUrl = "/score/app/phone/data/soccer/match_live.do";

   /* *//**
     * 足球比分初始数据-竞彩比分
     *
     * <pre>
     *
     * </pre>
     *
     * @return
     *//*
    public static String getFbMatchJzUrl() {
        if (GLOBAL_CONTROL_IS_RUN) {
            return URL_RUN + "/mobile/app/phone/soccer/jz_score.json";
        } else {
            return URL_TEST + ":8023/mobile/app/fbMatch.json?type=jz";
        }
    }


    *//**
     * 足球比分动态数据-竞彩比分
     *
     * <pre>
     *
     * </pre>
     *
     * @return
     *//*
    public static String getFbJzScoreUrl() {
        if (GLOBAL_CONTROL_IS_RUN) {
            return URL_RUN_LIVE + "/score/app/phone/data/soccer/jz_score.do";
        } else {
            return URL_TEST + ":8023/mobile/app/jz_score.do";
        }
    }
*/


    /**
     * get请求，获取返回字符串内容
     */
    public static HttpResult get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return execute(url, httpGet);
    }

    /**
     * post请求，获取返回字符串内容
     */
    public static HttpResult post(String url, List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(url);
      /*  ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bytes);
        httpPost.setEntity(byteArrayEntity);*/
        String s = "?";
        for (int i = 0; i < params.size(); i++) {
            if (i == params.size() - 1) {
                s = s + params.get(i);
            } else {
                s = s + params.get(i) + "&";
            }
        }
        LogUtils.e(url + s + "网络请求post地址");
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return execute(url, httpPost);
    }

    /**
     * 下载
     */
    public static HttpResult download(String url) {
        HttpGet httpGet = new HttpGet(url);
        return execute(url, httpGet);
    }

    /**
     * 执行网络访问
     */
    private static HttpResult execute(String url, HttpRequestBase requestBase) {
        boolean isHttps = url.startsWith("https://");//判断是否需要采用https
        AbstractHttpClient httpClient = HttpClientFactory.create(isHttps);
        HttpContext httpContext = new SyncBasicHttpContext(new BasicHttpContext());
        HttpRequestRetryHandler retryHandler = httpClient.getHttpRequestRetryHandler();//获取重试机制
        int retryCount = 0;
        boolean retry = true;
        while (retry) {
            try {
                LogUtils.i("load retry not null:" + retry);
                HttpResponse response = httpClient.execute(requestBase, httpContext);//访问网络
                LogUtils.i("load response:" + response + ":::");
                if (response != null) {
                    return new HttpResult(response, httpClient, requestBase);
                }
            } catch (Exception e) {
                IOException ioException = new IOException(e.getMessage());
                retry = retryHandler.retryRequest(ioException, ++retryCount, httpContext);//把错误异常交给重试机制，以判断是否需要采取从事
                LogUtils.e(e);
            }
        }
        LogUtils.i("net retry is null:" + retry);
        return null;
    }

    /**
     * http的返回结果的封装，可以直接从中获取返回的字符串或者流
     */
    public static class HttpResult {
        private HttpResponse mResponse;
        private InputStream mIn;
        private String mStr;
        private HttpClient mHttpClient;
        private HttpRequestBase mRequestBase;

        public HttpResult(HttpResponse response, HttpClient httpClient, HttpRequestBase requestBase) {
            mResponse = response;
            mHttpClient = httpClient;
            mRequestBase = requestBase;
        }

        public int getCode() {
            StatusLine status = mResponse.getStatusLine();
            return status.getStatusCode();
        }

        /**
         * 从结果中获取字符串，一旦获取，会自动关流，并且把字符串保存，方便下次获取
         */
        public String getString() {
            if (!StringUtils.isEmpty(mStr)) {
                return mStr;
            }
            InputStream inputStream = getInputStream();
            ByteArrayOutputStream out = null;
            if (inputStream != null) {
                try {
                    out = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    byte[] data = out.toByteArray();
                    mStr = new String(data, "utf-8");
                } catch (Exception e) {
                    LogUtils.e(e);
                } finally {
                    IOUtils.close(out);
                    close();
                }
            }
            return mStr;
        }

        /**
         * 获取流，需要使用完毕后调用close方法关闭网络连接
         */
        public InputStream getInputStream() {
            if (mIn == null && getCode() < 300) {
                HttpEntity entity = mResponse.getEntity();
                try {
                    mIn = entity.getContent();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            return mIn;
        }

        /**
         * 关闭网络连接
         */
        public void close() {
            if (mRequestBase != null) {
                mRequestBase.abort();
            }
            IOUtils.close(mIn);
            if (mHttpClient != null) {
                mHttpClient.getConnectionManager().closeExpiredConnections();
            }
        }
    }

    //网络请求的token
    private static String appToken;

    public static void setToken(String token) {

    }

}
