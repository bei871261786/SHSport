//package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.protocol;
//
//import android.util.Log;
//
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.bean.JingCaiZuqiuInfo;
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
//import shlottery.gov.cn.lotterycenter.utils.LogUtils;
//import shlottery.gov.cn.lotterycenter.utils.StringUtils;
//
///**
// * Created by booob on 2016-06-17-0017.
// */
//public class JingCaiZuQiuProtocol extends BaseProtocol<List<JingCaiZuqiuInfo.DataBean>> {
//
//
//    @Override
//    protected String getKey() {
//
//        String s = HttpHelper.getMatchList + "?token=" + Token + "&lotId=jczq" + "&subId=" + "&sign=";
//        return s;
//    }
//
//    @Override
//    protected String getSign() {
//        String mMd5 = StringUtils.getMD5(Token + signKey +lotId);
//        return mMd5;
//    }
//
//    @Override
//    protected String getFilePath() {
//        String path = "getMatchList";
//        return path;
//    }
//
//    @Override
//    protected List<JingCaiZuqiuInfo.DataBean> parseFromJson(String json) {
//        try {
//            Gson mGson = new Gson();
//            Log.i(json, "parseFromJson: ");
//            JingCaiZuqiuInfo.DataBean dataBean;
//            List<JingCaiZuqiuInfo.DataBean> list = new ArrayList<>();
//            JingCaiZuqiuInfo mJingCaiZuQiuInfo = mGson.fromJson(json, JingCaiZuqiuInfo.class);
//            dataBean = mJingCaiZuQiuInfo.getData();
//            // Log.i("mDatas的长度" + list.size() + "", "parseFromJson: list长度");
//            list.add(dataBean);
//            LogUtils.i("parseFromJson jincai"+list.size());
//            return list;
//        } catch (Exception e) {
//            LogUtils.e(e);
//            return null;
//        }
//    }
//}
