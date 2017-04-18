package shlottery.gov.cn.lotterycenter.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/3/8 14:38
 * 描    述：RecyclerView
 * 修订历史：
 * ************************************************
 */

public class NoToucLinearLayout extends LinearLayout {


    public NoToucLinearLayout(Context context) {
        super(context);
    }

    public NoToucLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoToucLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //不处理按下事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }
}
