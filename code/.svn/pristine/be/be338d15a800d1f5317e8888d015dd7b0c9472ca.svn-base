package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback;

import android.content.Context;
import android.support.annotation.Nullable;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.request.BaseRequest;

import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/5 14:56
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public abstract class DialogStringCallback extends AbsCallback<String> {
    private Context context;

    public DialogStringCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onBefore(BaseRequest request) {
//        DialogUtils.showLoadingDialog(context);
    }

    @Override
    public String convertSuccess(Response response) throws Exception {
        String s = StringConvert.create().convertSuccess(response);
        response.close();
        return s;
    }

    @Override
    public void onAfter(@Nullable String s, @Nullable Exception e) {
        LogUtils.i("callbacd OnAfter");
//        DialogUtils.cancelLoaddingDialog();
    }
}
