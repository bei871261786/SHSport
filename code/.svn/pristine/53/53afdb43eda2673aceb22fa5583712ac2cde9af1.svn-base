package shlottery.gov.cn.lotterycenter.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

/**
 * Created by yongchao.bei on 2016/6/29.
 */
public class BorderTextview extends CheckedTextView {

    public BorderTextview(Context context) {
        super(context);
    }
    public BorderTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private int sroke_width = 1;
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        //  将边框设为黑色
        paint.setColor(Color.parseColor("#333333"));
        //  画TextView的4个边
        //canvas.drawLine(0, 0, this.getWidth() - sroke_width, 0, paint);
       // canvas.drawLine(0, 0, 0, this.getHeight() - sroke_width, paint);
       // canvas.drawLine(this.getWidth() - sroke_width, 0, this.getWidth() - sroke_width, this.getHeight() - sroke_width, paint);
        canvas.drawLine(0, this.getHeight() - sroke_width, this.getWidth() - sroke_width, this.getHeight() - sroke_width, paint);
        super.onDraw(canvas);
    }
}
