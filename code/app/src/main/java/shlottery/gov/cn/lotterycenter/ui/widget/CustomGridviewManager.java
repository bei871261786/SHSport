package shlottery.gov.cn.lotterycenter.ui.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/8 17:15
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class CustomGridviewManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomGridviewManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomGridviewManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CustomGridviewManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
