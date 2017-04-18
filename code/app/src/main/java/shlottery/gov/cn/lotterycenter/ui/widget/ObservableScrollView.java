package shlottery.gov.cn.lotterycenter.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.ArrayList;

import shlottery.gov.cn.lotterycenter.callback.ObservableScrollCallback;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/3/3 16:17
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class ObservableScrollView extends ScrollView {

    private ScrollViewListener scrollViewListener = null;
    private ArrayList<ObservableScrollCallback> observers = new ArrayList<>();

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        LogUtils.i("");
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
        for (int i = 0; i <observers.size() ; i++) {
            observers.get(i).dispatchOnScroll(x,y,oldx,oldy);
        }
    }

    //禁用scrollview，防止跳动
    @Override
    public void scrollTo(int x, int y) {
    }

    public interface ScrollViewListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    }


    //注册观察者
    public void regeistObserver(ObservableScrollCallback observer) {
        observers.add(observer);
    }
}
