package shlottery.gov.cn.lotterycenter.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-13-0013 21:32
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SyncHorizontalScrollView extends HorizontalScrollView {
    private ScrollView mView;
    private long mLastTime;
    private int mLastX;
    private int mLastY;

    /**
     * Runnable延迟执行的时间
     */
    private long delayMillis = 100;

    /**
     * 上次滑动的时间
     */
    private long lastScrollUpdate = -1;


    private Runnable scrollerTask = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastScrollUpdate) > 1000) {
                lastScrollUpdate = -1;
                onScrollEnd();
            } else {
                postDelayed(this, delayMillis);
            }
        }
    };


    public SyncHorizontalScrollView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        super.setOnScrollChangeListener(l);
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        int x = 0;
        int y = 0;
//        if (mView != null) {
//            x = mView.getScrollX();
//            y = mView.getScrollY();
//            LogUtils.i("Basketball syncScroll changed  1:" + x + "::::" + y + ":::" + mLastTime);
//
//            //如果在20毫秒内y坐标移动超过了20，就判定失效，说明外层scrollview产生跳动，此次x，y为异常，不记录
//            if ((System.currentTimeMillis() - mLastTime) > 20 && (y - mLastY) > 20&&mLastY!=0) {
//                mView.scrollTo(mLastX, mLastY);
//            } else {
//                mLastX = x;
//                mLastY = y;
//            }
//        }
        //间隔超时证明退出滑动,打开scrollview滑动限制
//        if ((System.currentTimeMillis() - mLastTime) > 200) {
//            openScroll(mView);
//        } else {
//            closeScroll(mView);
//        }
//        mLastTime = System.currentTimeMillis();

        super.onScrollChanged(l, t, oldl, oldt);
        //设置控件滚动监听，得到滚动的距离，然后让传进来的view也设置相同的滚动具体
//        if(mView!=null) {
//            mView.scrollTo(l, t);
//        }

//        if (lastScrollUpdate == -1) {
//            onScrollStart();
//            postDelayed(scrollerTask, delayMillis);
//        }
        // 更新ScrollView的滑动时间
//        lastScrollUpdate = System.currentTimeMillis();

        LogUtils.i("Basketball syncScroll changed   2:" + x + "::::" + y);
    }

    /**
     * 设置跟它联动的view
     *
     * @param view
     */
    public void setScrollView(ScrollView view) {
        mView = view;
    }

    private void closeScroll(ScrollView view) {
        if (view != null) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    return true;
                }
            });
        }
    }

    private void openScroll(ScrollView view) {
        if (view != null) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    return false;
                }
            });
        }
    }

    /**
     * 滑动开始
     */
    private void onScrollStart() {
        closeScroll(mView);
    }

    /**
     * 滑动结束
     */
    private void onScrollEnd() {
        openScroll(mView);
    }

    //调节滑动速度
//    @Override
//    public void fling(int velocityY) {
//        super.fling(velocityY / 105);
//    }
}
