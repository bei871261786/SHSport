package shlottery.gov.cn.lotterycenter.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/27 17:22
 * 描    述：不能滑动的listview
 * 修订历史：
 * ************************************************
 */

public class ListViewWithoutScroll extends ListView {
    public ListViewWithoutScroll(Context context) {
        super(context);
    }

    public ListViewWithoutScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewWithoutScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
