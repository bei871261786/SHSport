//package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.protocol;
//
//import android.annotation.SuppressLint;
//
//import org.apache.http.NameValuePair;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.util.List;
//
//import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
//import shlottery.gov.cn.lotterycenter.utils.FileUtils;
//import shlottery.gov.cn.lotterycenter.utils.IOUtils;
//import shlottery.gov.cn.lotterycenter.utils.LogUtils;
//import shlottery.gov.cn.lotterycenter.utils.StringUtils;
//
///**
// * Created by booob on 2016-05-23-0023.
// * 将保存到本地注释了
// */
//public abstract class BaseProtocol<Data> {
//    public static final String cachePath = "";
//    String currenttime;
//    protected String Token = BaseApplication.getApplication().getmToken();
//    protected String signKey = BaseApplication.signKey;
//    protected String private_key = BaseApplication.PRIVATE_KEY;
//    protected String lotId = BaseApplication.getCurrentLotId();
//    private long delaTime = 1000 * 10;
//
//    /**
//     * 加载协议
//     */
//    public Data load(int index, List<NameValuePair> parames) {
//        //SystemClock.sleep(1000);// 休息1秒，防止加载过快，看不到界面变化效果
//        String json = null;
//        // 1.从本地缓存读取数据，查看缓存时间
//        json = loadFromLocal(index);
//        // 2.如果缓存时间过期，从网络加载
//        if (StringUtils.isEmpty(json)) {
//            json = loadFromNet(index, parames);
//            LogUtils.i("load json" + "::" + json + "::::" + index + "getkey:" + getKey());
//            if (json == null) {
//                // 网络出错
//                return null;
//            } else {
//                // 3.把数据保存到本地保存到本地
//              //  saveToLocal(json, index);
//            }
//
//        }
//        return parseFromJson(json);
//    }
//
//    /**
//     * 是否只从网络加载协议
//     */
//    public Data load(int index, List<NameValuePair> parames, boolean isJustNet) {
//        //SystemClock.sleep(1000);// 休息1秒，防止加载过快，看不到界面变化效果
//        String json = null;
//        if (!isJustNet) {
//            // 1.从本地缓存读取数据，查看缓存时间
//            json = loadFromLocal(index);
//        }
//        // 2.如果缓存时间过期，从网络加载
//        if (StringUtils.isEmpty(json)) {
//            json = loadFromNet(index, parames);
//            LogUtils.i("load json" + "::" + json + "::::" + index + "getkey:" + getKey());
//            if (json == null) {
//                // 网络出错
//                return null;
//            } else {
//                // 3.把数据保存到本地保存到本地
//                saveToLocal(json, index);
//            }
//
//        }
//        return parseFromJson(json);
//    }
//
//    /**
//     * 从本地加载协议
//     */
//    protected String loadFromLocal(int index) {
//        String path = FileUtils.getCacheDir();
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(path + getFilePath() + "_" + index));
//            String line = reader.readLine();// 第一行是时间
//            Long time = Long.valueOf(line);
//            if (time > System.currentTimeMillis()) {//如果时间未过期
//                StringBuilder sb = new StringBuilder();
//                String result;
//                while ((result = reader.readLine()) != null) {
//                    sb.append(result);
//                }
//                return sb.toString();
//            }
//        } catch (Exception e) {
////            LogUtils.e(e);
//        } finally {
//            IOUtils.close(reader);
//        }
//        return null;
//    }
//
//    /**
//     * 从网络加载协议
//     */
//    @SuppressLint("LongLogTag")
//    protected String loadFromNet( List<NameValuePair> parames) {
//        String result = null;
//        HttpHelper.HttpResult httpResult;
//        if (null == parames) {//如果参数为空,get请求,否则post请求
//                httpResult = HttpHelper.get(HttpHelper.URL_RUN + getKey() + getSign());
//                LogUtils.e(HttpHelper.URL_RUN + getKey() + getSign() + "地址竞彩比分___httpResult+index=1");
//        } else {
//            if (index == 1) {
//                httpResult = HttpHelper.post(HttpHelper.URL_RUN_LIVE + getKey(), parames);
//                LogUtils.e(HttpHelper.URL_RUN_LIVE + getKey() + getSign() + "地址竞彩比分___httpResult+index=1");
//            } else {
//                httpResult = HttpHelper.post(HttpHelper.CeshiJCZQURL + getKey(), parames);
//            }
//        }
//
//        if (httpResult != null) {
//            result = httpResult.getString();
//            httpResult.close();
//        }
//        LogUtils.i("load String:" + result);
//        return result;
//    }
//
//    /**
//     * 保存到本地
//     */
//    protected void saveToLocal(String str, int index) {
//        String path = FileUtils.getCacheDir();
//        BufferedWriter writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter(path + getFilePath() + "_" + index));
//            long time = getDelayTime();//先计算出过期时间，写入第一行
//            writer.write(time + "\r\n");
//            writer.write(str.toCharArray());
//            writer.flush();
//        } catch (Exception e) {
//            LogUtils.e(e);
//        } finally {
//            IOUtils.close(writer);
//        }
//    }
//
//    protected long getDelayTime() {
//        return System.currentTimeMillis() + delaTime;
//    }
//
//    protected void setDelayTime(long delaTime) {
//        this.delaTime = delaTime;
//    }
//
//
//    /**
//     * 该协议的访问地址
//     */
//    protected abstract String getHeadUrl();
//
//    /**
//     * sign的MD5值的获取
//     */
//    protected abstract String getSign();
//
//    /**
//     * 保存到本地的文件名
//     */
//    protected abstract String getFilePath();
//
//    /**
//     * 从json中解析
//     */
//    protected abstract Data parseFromJson(String json);
//}
//
//
//
//
