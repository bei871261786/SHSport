package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment;

import java.util.HashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by booob on 2016-05-21-0021.
 */
public class SwitchJingCaiFangShiFragment {

//    // 胜平负
//    private static final int TAB_SPF = 0;
//    // 让球胜平负
//    private static final int TAB_RQSFP = 1;
//    // 比分
//    private static final int TAB_BF = 2;
//    // 总进球
//    private static final int TAB_ZJQ = 3;
//    // 半全场
//    private static final int TAB_BQC = 4;
//    // 混合过关
//    private static final int TAB_HHGG = 5;
//    // 2选1
//    private static final int TAB_TWOXONE = 6;

    private static HashMap<Integer, BaseFragment2> mSoccerFragments = new HashMap<Integer, BaseFragment2>();
    private static HashMap<Integer, BaseFragment2> mBasketBallFragments = new HashMap<Integer, BaseFragment2>();

    public static BaseFragment2 createSoccerFragment(int position) {
        LogUtils.i("");
        // 从缓存中取出
        BaseFragment2 fragment = mSoccerFragments.get(position);
        LogUtils.i("fragment == null?" + fragment);
        if (null == fragment) {
            switch (position) {
                case Configure_JC.TAB_SPF:
                    fragment = new ShengPingfuFragment();
                    break;
                case Configure_JC.TAB_RQSFP:
                    fragment = new RangQiuShengPingFuFragment();
                    break;
                case Configure_JC.TAB_BF:
                    fragment = new BiFenFragment();
                    break;
                case Configure_JC.TAB_ZJQ:
                    fragment = new ZongJinQiuFragment();
                    break;
                case Configure_JC.TAB_BQC:
                    fragment = new BanQuanChangFragment();
                    break;
                case Configure_JC.TAB_HHGG:
                    fragment = new HunHeGuoGuanFragment();
                    break;
                default:
                    break;
            }
            // 把frament加入到缓存中
            mSoccerFragments.put(position, fragment);
        }
        LogUtils.i("fragment create over");
        return fragment;
    }

    public static BaseFragment2 createBasketballFragment(int position) {
        // 从缓存中取出
        BaseFragment2 fragment = mBasketBallFragments.get(position);
        LogUtils.i("fragment == null?" + fragment+"::::"+position);
        if (null == fragment) {
            switch (position) {
                case Configure_JC.TAB_SPF:
                    fragment = new ShenguFragment();
                    break;
                case Configure_JC.TAB_XSF:
                    fragment = new RangFenShengfuFragment();
                    break;
                case Configure_JC.TAB_DXF:
                    fragment = new DaXiaoFenFragment();
                    break;
                case Configure_JC.TAB_SFC:
                    fragment = new ShenFenChaFragment();
                    break;
                case Configure_JC.TAB_HHGG_BK:
                    fragment = new HunHeGuoGuanBasketFragment();
                    break;
                default:
                    break;
            }
            // 把frament加入到缓存中
            mBasketBallFragments.put(position, fragment);
        }
        LogUtils.i("fragment create over");
        return fragment;
    }

    //清空fragments缓存
    public static void removeFragment() {
        mBasketBallFragments.clear();
        mSoccerFragments.clear();
    }
}
