package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;

/**
 * @创建者 Tyler Wang.
 * @创建时间 2017/4/7  16:08.
 * @描述 ${获取短信验证码}.
 */
public class GetSmsVcodeProtocol extends BaseProtocol {

    public GetSmsVcodeProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.sendVerify;
    }

    @Override
    protected Object parseData(String json) {
        Gson gson = new Gson();
        VcodeBean vcodeBean = gson.fromJson(json, VcodeBean.class);
        return vcodeBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}
