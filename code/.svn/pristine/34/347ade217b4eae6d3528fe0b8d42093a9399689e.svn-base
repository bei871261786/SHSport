package shlottery.gov.cn.lotterycenter.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.callback.DispatchListener;
import shlottery.gov.cn.lotterycenter.ui.widget.ObservableScrollView;
import shlottery.gov.cn.lotterycenter.ui.widget.Observers.ObserverImageView;
import shlottery.gov.cn.lotterycenter.ui.widget.Observers.ObserverTextView;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

import static shlottery.gov.cn.lotterycenter.R.id.match_head;


public class CoordinatorTestActivity extends AppCompatActivity {

    @BindView(R.id.match_back)
    ImageView matchBack;
    @BindView(R.id.match_scrollView)
    ObservableScrollView matchScrollView;
    @BindView(R.id.match_hostLogo)
    ObserverImageView matchHostLogo;
    @BindView(R.id.match_visitLogo)
    ObserverImageView matchVisitLogo;
    @BindView(R.id.titleLine)
    RelativeLayout titleLine;
    @BindView(match_head)
    LinearLayout matchHead;
    @BindView(R.id.mCoordinatorLayout)
    RelativeLayout mCoordinatorLayout;
    @BindView(R.id.match_title)
    ObserverTextView matchTitle;
    @BindView(R.id.match_goal)
    ObserverTextView matchGoal;
    @BindView(R.id.match_hotName)
    ObserverTextView matchHotName;
    @BindView(R.id.match_visitName)
    ObserverTextView matchVisitName;
    @BindView(R.id.match_line)
    View matchLine;

    private float mMoveTotalLogo;//控件移动总距离
    private float mMoveTotalGoal;//控件移动总距离
    private float mMoveTotalName;//控件移动总距离
    private float mMoveToName;//控件移动总距离
    private float mScaleTotal;//控件缩放最小比例
    private float mScrollTotal;//scrollview移动总距离
    private float mTransScaleLogo;//scrollvierw移动距离和控件移动距离的比例
    private float mTransScaleGoal;//scrollvierw移动距离和控件移动距离的比例
    private float mTransScaleName;//scrollvierw移动距离和控件移动距离的比例
    private final int LEFT = 0;
    private final int RIGHT = 1;
    private boolean mColorChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_test2);
        ButterKnife.bind(this);
        matchScrollView.regeistObserver(matchVisitLogo);
        matchScrollView.regeistObserver(matchHostLogo);
        matchScrollView.regeistObserver(matchTitle);
        matchScrollView.regeistObserver(matchGoal);
        matchScrollView.regeistObserver(matchHotName);
        matchScrollView.regeistObserver(matchVisitName);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        initArguments();
        initAnim();
        super.onWindowFocusChanged(hasFocus);
    }

    private void initAnim() {
        matchVisitLogo.setDispatchOnScrollListener(new DispatchListener() {
            @Override
            public void dispatch(View view, int x, int y, int oldx, int oldy) {
                LogUtils.i("dispatch :" + ":::::" + y);
                handleLogo(view, y, RIGHT);
            }
        });
        matchHostLogo.setDispatchOnScrollListener(new DispatchListener() {
            @Override
            public void dispatch(View view, int x, int y, int oldx, int oldy) {
                LogUtils.i("dispatch :" + ":::::" + y);
                handleLogo(view, y, LEFT);
            }
        });

        matchGoal.setDispatchOnScrollListener(new DispatchListener() {
            @Override
            public void dispatch(View view, int x, int y, int oldx, int oldy) {
                handleGoal(view, y);
            }
        });
        matchTitle.setDispatchOnScrollListener(new DispatchListener() {
            @Override
            public void dispatch(View view, int x, int y, int oldx, int oldy) {
                handleTitle(view, y);
            }
        });
        matchHotName.setDispatchOnScrollListener(new DispatchListener() {
            @Override
            public void dispatch(View view, int x, int y, int oldx, int oldy) {
                handleName(view, y, LEFT);
            }
        });
        matchVisitName.setDispatchOnScrollListener(new DispatchListener() {
            @Override
            public void dispatch(View view, int x, int y, int oldx, int oldy) {
                handleName(view, y, RIGHT);
            }
        });
    }

    //初始化各项参数,放在onWindowFocusChanged方法内部，否则view的长宽等值因为没有初始化完成会获取不到
    private void initArguments() {
        mScrollTotal = matchHead.getMeasuredHeight();
        mMoveTotalLogo = matchHostLogo.getTop() + matchHostLogo.getMeasuredHeight() / 2 - titleLine.getMeasuredHeight() / 2;
        mMoveTotalGoal = matchGoal.getTop() + matchGoal.getMeasuredHeight() / 2 - titleLine.getMeasuredHeight() / 2;
        mMoveTotalName = matchLine.getTop() +titleLine.getMeasuredHeight()- matchHotName.getTop() - matchHotName.getMeasuredHeight();
        mMoveToName = mMoveTotalName*2;
        LogUtils.i("initArguments totalName:" + matchLine.getTop() + "::::" + titleLine.getMeasuredHeight() + "::::" + matchHotName.getTop() + "::::" + matchHotName.getMeasuredHeight());
        mTransScaleLogo = mMoveTotalLogo / mScrollTotal;
        mTransScaleGoal = mMoveTotalGoal / mScrollTotal;
        mTransScaleName = mMoveToName / mMoveTotalName;
        LogUtils.i("dispatch  height:" + mScrollTotal + ":::::" + mMoveTotalLogo + "::::::" + mTransScaleLogo);
        mScaleTotal = (float) titleLine.getMeasuredHeight() / (float) matchHead.getMeasuredHeight();
        mScaleTotal = mScaleTotal * 8 / 7;
    }

    private void handleTitle(View view, int y) {
        float alphaValue = 0;
        if (y >= mScrollTotal) {
            alphaValue = 0;
        } else if (y <= 0) {
            alphaValue = 1;
        } else {
            alphaValue = 1 - y / mScrollTotal;
        }
        LogUtils.i("handleTitle alphaValue:" + y + ":::::" + alphaValue);
        view.setAlpha(alphaValue);
    }

    private void handleName(View view, int y, int orientation) {
        float alphaValue = 0;
        float moveValue = 0;
        if (y >= mMoveTotalName) {
            moveValue = mMoveToName;
            alphaValue = 0;
        } else if (y <= 0) {
            moveValue = 0;
            alphaValue = 1;
        } else {
            moveValue = mTransScaleName * y;
            alphaValue = 1 - moveValue / mMoveToName;
        }
        LogUtils.i("dispatch scaleValue handleName:" + y + "::::" + alphaValue + "::::" + moveValue + "::::" + mMoveTotalName);
        if (orientation == LEFT) {
            view.setTranslationX(moveValue);
        } else {
            view.setTranslationX(-moveValue);
        }
        view.setAlpha(alphaValue);
    }

    private void handleGoal(View view, int y) {
        float moveValue = 0;
        TextView tv = (TextView) view;
        int scrollToY = 0;
        if (y >= mScrollTotal) {
            moveValue = mMoveTotalGoal;
            mColorChanged = true;
            tv.setTextColor(getResources().getColor(R.color.white));
        } else if (y <= 0) {
            moveValue = 0;
        } else {
            if (mColorChanged) {
                tv.setTextColor(getResources().getColor(R.color.black));
                mColorChanged = false;
            }
            moveValue = y * mTransScaleGoal;
        }
        LogUtils.i("dispatch scaleValue handleGoal:" + y + "::::" + scrollToY + ":::::" + mScrollTotal + "::::" + moveValue + "::::" + mMoveTotalGoal);
        view.setTranslationY(-moveValue);
    }

    //根据滑动处理两个logo
    private void handleLogo(View view, int y, int orientation) {
        float scaleValue;
        float moveValue = 0;
        int scrollToX = 0;
        int scrollToY = 0;
        if (y >= mScrollTotal) {
            scaleValue = mScaleTotal;
            moveValue = -mMoveTotalLogo;
            scrollToY = (int) -mScrollTotal;
        } else if (y <= 0) {
            scaleValue = 1;
            moveValue = 0;
            scrollToY = 0;
        } else {
            float fullScaleValue = (float) (mMoveTotalLogo - y * 0.6) / (float) mMoveTotalLogo;
            scaleValue = 1 - (1 - mScaleTotal) * (y * mTransScaleLogo / mMoveTotalLogo);
            moveValue = -y * mTransScaleLogo;
            scrollToY = -y;
            LogUtils.i("dispatch scaleValue 1:" + y + ":::::" + (scaleValue) + ":::::" + fullScaleValue);
        }
        LogUtils.i("dispatch scaleValue 2:" + y + "::::" + scrollToY + ":::::" + mScrollTotal + "::::" + moveValue + "::::" + mMoveTotalLogo);
        view.setTranslationY(moveValue);
        if (orientation == LEFT) {
            view.setTranslationX(-moveValue);
        } else {
            view.setTranslationX(moveValue);
        }
        view.setScaleX(scaleValue);
        view.setScaleY(scaleValue);
        matchScrollView.scrollTo(scrollToX, scrollToY);
    }


}
