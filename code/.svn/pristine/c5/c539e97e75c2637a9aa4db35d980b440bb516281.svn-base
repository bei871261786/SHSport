package shlottery.gov.cn.lotterycenter.ui.widget.Observers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import shlottery.gov.cn.lotterycenter.callback.DispatchListener;
import shlottery.gov.cn.lotterycenter.callback.ObservableScrollCallback;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/3/24 17:02
 * 描    述：作为观察者的imageview，观察scrollview的滚动，搭配ObservableScrollView使用
 * 修订历史：
 * ************************************************
 */

public class ObserverImageView extends ImageView implements ObservableScrollCallback {
    private DispatchListener listener;

    public ObserverImageView(Context context) {
        super(context);
    }

    public ObserverImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObserverImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void dispatchOnScroll(int x, int y, int oldx, int oldy) {
        if (listener != null)
        {
            listener.dispatch(this,x, y, oldx, oldy);
        }

    }

    public void setDispatchOnScrollListener(DispatchListener listener) {
        this.listener = listener;
    }
}
