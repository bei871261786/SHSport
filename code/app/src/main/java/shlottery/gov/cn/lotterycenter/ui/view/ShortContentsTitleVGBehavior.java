package shlottery.gov.cn.lotterycenter.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ScreenUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/3/24 10:54
 * 描    述：自定义Behavier，自定义协调者的行为
 * 修订历史：
 * ************************************************
 */

public class ShortContentsTitleVGBehavior extends CoordinatorLayout.Behavior<FrameLayout> {

    private static final String TAG = "ShortContentsVGBehavior";
    private ViewGroup.MarginLayoutParams params;
//    private int top = DensityUtil.dip2px(BaseApplication.getApplication(),48);//导航栏高度
    private int top = 50;//导航栏高度
    private View child_w;
    private View child_b;
    private View child_line;
    private int count;

    public ShortContentsTitleVGBehavior() {
    }

    public ShortContentsTitleVGBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FrameLayout child, View dependency) {
        //获取View的params
        params = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        //获取子View
        child_w = child.getChildAt(0);
        child_b = child.getChildAt(1);
        child_line = child.getChildAt(2);
        //依赖的View 这里选择RecyclerView
        return dependency instanceof RecyclerView;
    }

    /**
     * 依赖的View发生了变化
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FrameLayout child, View dependency) {
        //依赖的View距离顶部的距离
        float y = dependency.getY();
        //图片的高度为屏幕宽度,所以此处做一次判断.
        if(y == ScreenUtils.getScreenWidth(BaseApplication.getApplication())){
            //count的作用:由于开场动画会设置列表距离顶部距离为0,此时应不做处理,所以用count来判断是否开始执行导航栏动画.
            if(count <= 1){
                count ++;
            }
        }

      LogUtils.i("y:" + y + "  count:" + count);
        if(count >= 1) {
            //出场动画已经结束,可以开始监听顶部距离来实现导航栏动画
            changePostion(child, y);
        }

        return super.onDependentViewChanged(parent, child, dependency);
    }

    /**
     * 导航栏动画
     * @param child
     * @param y
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changePostion(FrameLayout child, float y) {
        if(y >= 0 && y <= top){//列表与导航栏接触,导航栏随着列表的移动而移动
            //计算导航栏上移距离
            float dy = y - top;
            params.topMargin = (int) dy;
            child.setLayoutParams(params);
            //显示黑色icon的标题栏
            child_b.setAlpha(1);
            //隐藏白色icon的标题栏
            child_w.setAlpha(0);
            //显示分割线
            child_line.setVisibility(View.VISIBLE);
        }else {//列表未接触到导航栏(导航栏高度为top)
            if(y <= top * 2) {//列表距离顶部距离时导航栏高度两倍时,开启动画(这里可以自己决定)
                //top ~ top * 2过程中计算alpha值
                float f = 2 - y / top;
                child_b.setAlpha(f);
                child_w.setAlpha(1 - f);
            }else {
                child_b.setAlpha(0);
                child_w.setAlpha(1);
            }
            //还原导航栏的位置
            params.topMargin = 0;
            child.setLayoutParams(params);
            child_line.setVisibility(View.GONE);
        }

        //此处用来给RecyclerView滚动监听用,只有列表在顶部,列表滚动时才执行导航栏的显示和隐藏.列表不在顶部时,
        // 通过上面代码来控制导航栏动画效果
        if(y <= 0) {
            child.setTag(true);//在顶部
        }else {
            child.setTag(false);//不在顶部
        }
        child_w.setBackgroundColor(Color.parseColor("#e11f1d"));
    }
}