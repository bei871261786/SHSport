package shlottery.gov.cn.lotterycenter.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-18-0018 10:22
 * 描    述：验证码倒计时
 * 修订历史：
 * ================================================
 */

//倒计时的类
public class MyCountDownTimer extends CountDownTimer {
    TextView textView;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    //倒计时时执行的动作
    @Override
    public void onTick(long millisUntilFinished) {
        int i;
        if (Math.floor(millisUntilFinished / 1000) == 0) {
            i = 0;
        } else {
            i = (int) (millisUntilFinished / 1000);
        }
        updateDialog(textView, "剩余" + i + "s", 0);
    }

    //倒计时结束执行的动作
    @Override
    public void onFinish() {
        updateDialog(textView, "获取验证码", 1);
    }

    private void updateDialog(TextView textView, String s, int type) {
        if (type == 0) {
            textView.setText(s);
            textView.setEnabled(false);
        } else if (type == 1) {
            textView.setText(s);
            textView.setEnabled(true);
        }
    }
}
