package shlottery.gov.cn.lotterycenter.utils;

import android.content.Context;
import android.content.Intent;

import shlottery.gov.cn.lotterycenter.service.UpdateUserinfoService;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/22 14:12
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class DataUtils {
    public static void updateSharesUserinf(Context context) {
        Intent service = new Intent(context, UpdateUserinfoService.class);
        context.startService(service);
    }


}
