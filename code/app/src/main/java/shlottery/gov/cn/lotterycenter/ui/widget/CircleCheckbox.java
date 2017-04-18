package shlottery.gov.cn.lotterycenter.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import shlottery.gov.cn.lotterycenter.R;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/18 11:28
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class CircleCheckbox extends View {
    private Paint mPaint;
    private  Context context;

    public CircleCheckbox(Context context) {
        super(context);
    }

    public CircleCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        mPaint=new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.circle_red));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
