package shlottery.gov.cn.lotterycenter.Base;

import android.os.Bundle;
import android.view.View;
import com.gooooal.app.swipebacklayout.SwipeBackActivityBase;
import com.gooooal.app.swipebacklayout.SwipeBackActivityHelper;
import com.gooooal.app.swipebacklayout.SwipeBackLayout;
import com.gooooal.app.swipebacklayout.Utils;
/**
 * @创建者 Tyler Wang.
 * @创建时间 2017/3/21  16:02.
 * @描述 ${侧滑返回Activity}.
 */
public abstract class SlideBackActivity extends BaseActivity implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void initView() {
        initPageView();
    }

    @Override
    protected void init() {
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    /**
     * 初始化view 替代initView
     */
    protected abstract void initPageView();

    /**
     * 初始化data 替代init
     */
    protected abstract void initData();

}
