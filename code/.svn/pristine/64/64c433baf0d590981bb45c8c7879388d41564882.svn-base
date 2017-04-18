//package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.protocol;
//
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
//import shlottery.gov.cn.lotterycenter.bean.JincaiSoccer.VoucherBean;
//import shlottery.gov.cn.lotterycenter.protool.BaseProtocol;
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
//import shlottery.gov.cn.lotterycenter.utils.LogUtils;
//import shlottery.gov.cn.lotterycenter.utils.StringUtils;
//
///**
// * Created by booob on 2016-07-27-0027.
// */
//public class VoucherProtocol extends BaseProtocol<List<VoucherBean.DataBean>> {
//    private String secret = BaseApplication.getmSecret();
//    private String ststus = "";
//    private String pageNo = "";
//
//    public void setStstus(String ststus) {
//        this.ststus = ststus;
//    }
//
//    public void setPageNo(String pageNo) {
//        this.pageNo = pageNo;
//    }
//
//    public VoucherProtocol() {
//        setDelayTime(0);
//    }
//
//    @Override
//
//    protected String getKey() {
//        String s = HttpHelper.getVoucher + "?token=" + Token + "&status=" + ststus + "&pageNo=" + pageNo + "&sign=";
//        return s;
//    }
//
//    @Override
//    protected String getSign() {
//        String mMd5 = StringUtils.getMD5(Token + secret + signKey + ststus + pageNo);
//        LogUtils.i("getkey:" + "token:  " + Token + "   status" + ststus + "   pageNo" + pageNo);
//        return mMd5;
//    }
//
//    @Override
//    protected String getFilePath() {
//        String path = "getVoucher";//账户变动
//        return path;
//    }
//
//    @Override
//    protected List<VoucherBean.DataBean> parseFromJson(String json) {
//        try {
//            Gson mGson = new Gson();
//            LogUtils.i("parseFromJson:" + json);
//            VoucherBean.DataBean dataBean;
//            List<VoucherBean.DataBean> list = new ArrayList<>();
//            VoucherBean mIssueBonus = mGson.fromJson(json, VoucherBean.class);
//            dataBean = mIssueBonus.getData();
//            list.add(dataBean);
//            LogUtils.i("parseFromJson voucher" + list.size());
//            return list;
//        } catch (Exception e) {
//            LogUtils.e(e);
//            return null;
//        }
//    }
//}
