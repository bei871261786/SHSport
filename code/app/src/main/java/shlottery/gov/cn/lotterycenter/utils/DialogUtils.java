package shlottery.gov.cn.lotterycenter.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import shlottery.gov.cn.lotterycenter.R;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/21 15:39
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class DialogUtils {
    private static AlertDialog mLoaddingDialog;

    public static void showLoadingDialog(Context context) {
        View dialogView = UIUtils.inflate(R.layout.dialog_loadding);
        mLoaddingDialog = new AlertDialog.Builder(context).create();
        mLoaddingDialog.setView(dialogView);
        mLoaddingDialog.show();
        mLoaddingDialog.setCancelable(false);// 不可以用“返回键”取消
        mLoaddingDialog.setCanceledOnTouchOutside(false);
        LogUtils.i("dialogutils loadding");
    }

    public static void cancelLoaddingDialog() {
        if (mLoaddingDialog != null && mLoaddingDialog.isShowing()) {
            mLoaddingDialog.dismiss();
        }
    }

    public static void ShowXieYiDialog(Context context) {
        View mDiaglogView = UIUtils.inflate(R.layout.dialog_dantuo_zhushi);
        ImageView cancle;
        final android.support.v7.app.AlertDialog mAlertDialog = new android.support.v7.app.AlertDialog.Builder(context).create();
        mAlertDialog.setView(mDiaglogView);
        mAlertDialog.show();
        cancle = (ImageView) mDiaglogView.findViewById(R.id.dialog_cancel);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });

    }
}
