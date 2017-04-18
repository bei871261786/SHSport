package shlottery.gov.cn.lotterycenter.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/21 16:07
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;

    public DividerItemDecoration(Context context, int resId) {
        //在这里我们传入作为Divider的Drawable对象
        mDrawable = context.getResources().getDrawable(resId);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //以下计算主要用来确定绘制的位置
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int position, RecyclerView parent) {
        outRect.set(0, 0, 0, mDrawable.getIntrinsicWidth());
    }
}