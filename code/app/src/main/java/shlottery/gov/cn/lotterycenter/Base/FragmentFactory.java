package shlottery.gov.cn.lotterycenter.Base;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment.CalSf14Fragment;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment.CalSf9Fragment;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment.CalaculateBqcFragment;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment.CalaculateJqcFragment;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment.CalculateDltFragment;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment.CalculatePl3Fragment;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment.Pl5CalculateFragment;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment.QxcCalculateFragment;
import shlottery.gov.cn.lotterycenter.ui.fragment.HomeFragment;
import shlottery.gov.cn.lotterycenter.ui.fragment.LotterysFragment;
import shlottery.gov.cn.lotterycenter.ui.fragment.MatchFragment;
import shlottery.gov.cn.lotterycenter.ui.fragment.NewsFragment;
import shlottery.gov.cn.lotterycenter.ui.fragment.UserCenterFragment;
import shlottery.gov.cn.lotterycenter.ui.fragment.UserLoginFragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.PL3NomalFragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.PL3Zu3Fragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.PL3Zu6Fragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.Syx5DanFragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.Syx5RenxFragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.Syx5Zhix2Fragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.Syx5Zhix3Fragment;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;

public class FragmentFactory {

    // 首页
    private static final int TAB_HOME = 0;
    // 资讯
    private static final int TAB_NEWS = 1;
    // 彩种
    private static final int TAB_LOTTERY = 2;
    // 赛事
    private static final int TAB_MATCH = 3;
    // 用户
    private static final int TAB_USER = 4;

    // 排列三直选
    private static final int PL3_ZX = 5;
    //排列三组选三
    private static final int PL3_Z3 = 6;
    //排列三组选六
    private static final int PL3_Z6 = 7;
    /*
    // 11选5任选2
    private static final int SYX5_RX2 = 0;
    //11选5任选3
    private static final int SYX5_RX3 = 1;
    //11选5任选4
    private static final int SYX5_RX4 = 2;
    // 11选5任选5
    private static final int SYX5_RX5 = 3;
    //11选5任选6
    private static final int SYX5_RX6 = 4;
    //11选5任选7
    private static final int SYX5_RX7 = 5;
    // 11选5任选8
    private static final int SYX5_RX8 = 6;
    //11选5前一
    private static final int SYX5_QY = 7;
    //11选5前二直选
    private static final int SYX5_Q2ZHIX = 8;
    // 11选5前二组选
    private static final int SYX5_Q2ZUX = 9;
    //11选5前三直选
    private static final int SYX5_Q3ZHIX = 10;
    //11选5前三组选
    private static final int SYX5_Q3ZUX = 11;
*/

    private static HashMap<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();
    private static HashMap<Integer, BaseFragment> mPL3Fragments = new HashMap<Integer, BaseFragment>();
    private static HashMap<Integer, BaseFragment> mSyx5Fragments = new HashMap<Integer, BaseFragment>();
    private static HashMap<Integer, BaseFragment> mSyx5DanFragments = new HashMap<Integer, BaseFragment>();
    private static HashMap<Integer, BaseFragment> mCalculateFragments = new HashMap<Integer, BaseFragment>();
    private static boolean loginStatus = false;
    private static boolean isFirstOpen = true;//是否第一次打开app

    public static BaseFragment createFragment(int position, boolean isLogin) {
//		 从缓存中取出
        BaseFragment fragment = null;
        if (position == 4) {
            LogUtils.i("FragmentFactory createFragment:" + isLogin + ":::" + loginStatus);
            if (isFirstOpen) {
                LogUtils.i("FragmentFactory isFirstOpen");
                if (isLogin) {
                    LogUtils.i("FragmentFactory login");
                    fragment = new UserCenterFragment();
                } else {
                    LogUtils.i("FragmentFactory nologin");
                    fragment = new UserLoginFragment();
                }
                mFragments.put(position, fragment);
            } else if (loginStatus != isLogin) {
                LogUtils.i("FragmentFactory loginStatus != isLogin");
                if (isLogin) {
                    LogUtils.i("FragmentFactory login");
                    fragment = new UserCenterFragment();
                } else {
                    LogUtils.i("FragmentFactory nologin");
                    fragment = new UserLoginFragment();
                }
                mFragments.put(position, fragment);
            }
            isFirstOpen = false;
            loginStatus = isLogin;
        }
        fragment = mFragments.get(position);
        if (null == fragment) {
            switch (position) {
                case TAB_HOME:
                    fragment = new HomeFragment();
                    break;
                case TAB_NEWS:
//                    fragment = new HomeFragment();
                    fragment = new NewsFragment();
                    break;
                case TAB_LOTTERY:
                    fragment = new LotterysFragment();
                    break;
                case TAB_MATCH:
                    fragment = new MatchFragment();
                    break;
                case TAB_USER:
                    fragment = new UserCenterFragment();
                    break;
                default:
                    break;
            }
            // 把frament加入到缓存中
            if (position != TAB_NEWS) {
                if (position != TAB_MATCH) {
                    Logger.e(position + "当前位置");
                    mFragments.put(position, fragment);
                }
            }
        }

        return fragment;
    }

    public static BaseFragment createPL3Fragment(int position, ShakeUtils mShakeUtils, List<NumLotOrderBean> mNumLotOrderBeans) {
        BaseFragment fragment = mPL3Fragments.get(position);
        if (null == fragment) {
            switch (position) {
                case PL3_ZX:
                    fragment = new PL3NomalFragment(mShakeUtils, mNumLotOrderBeans);
                    break;
                case PL3_Z3:
                    fragment = new PL3Zu3Fragment(mShakeUtils, mNumLotOrderBeans);
                    break;
                case PL3_Z6:
                    fragment = new PL3Zu6Fragment(mShakeUtils, mNumLotOrderBeans);
                    break;
                default:
                    break;
            }
            // 把frament加入到缓存中
//            mPL3Fragments.put(position, fragment);
        }
        return fragment;
    }

    //新建任选等11选5fragment
    public static BaseFragment createSyx5Fragment(int position, ShakeUtils mShakeUtils, List<NumLotOrderBean> mNumLotOrderBeans) {
        BaseFragment fragment = mSyx5Fragments.get(position);
        if (null == fragment) {
            switch (position) {
                case Configs.SYX5_RX2:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_RX2, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RX3:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_RX3, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RX4:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_RX4, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RX5:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_RX5, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RX6:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_RX6, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RX7:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_RX7, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RX8:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_RX8, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_QY:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_QY, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_Q2ZHIX:
                    fragment = new Syx5Zhix2Fragment(mShakeUtils, Configs.SYX5_Q2ZHIX, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_Q2ZUX:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_Q2ZUX, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_Q3ZHIX:
                    fragment = new Syx5Zhix3Fragment(mShakeUtils, Configs.SYX5_Q3ZHIX, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_Q3ZUX:
                    fragment = new Syx5RenxFragment(mShakeUtils, Configs.SYX5_Q3ZUX, mNumLotOrderBeans);
                    break;
                default:
                    break;
            }
            // 把frament加入到缓存中
//            mSyx5Fragments.put(position, fragment);
        }
        return fragment;
    }

    //新建胆拖等11选5fragment
    public static BaseFragment createSyx5DanFragment(int position, ShakeUtils mShakeUtils, List<NumLotOrderBean> mNumLotOrderBeans) {
        BaseFragment fragment = mSyx5DanFragments.get(position);
        if (null == fragment) {
            switch (position) {
                case Configs.SYX5_RXDAN2:
                    fragment = new Syx5DanFragment(Configs.SYX5_RXDAN2, mShakeUtils, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RXDAN3:
                    fragment = new Syx5DanFragment(Configs.SYX5_RXDAN3, mShakeUtils, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RXDAN4:
                    fragment = new Syx5DanFragment(Configs.SYX5_RXDAN4, mShakeUtils, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RXDAN5:
                    fragment = new Syx5DanFragment(Configs.SYX5_RXDAN5, mShakeUtils, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RXDAN6:
                    fragment = new Syx5DanFragment(Configs.SYX5_RXDAN6, mShakeUtils, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RXDAN7:
                    fragment = new Syx5DanFragment(Configs.SYX5_RXDAN7, mShakeUtils, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_RXDAN8:
                    fragment = new Syx5DanFragment(Configs.SYX5_RXDAN8, mShakeUtils, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_Q2ZUXDAN:
                    fragment = new Syx5DanFragment(Configs.SYX5_Q2ZUXDAN, mShakeUtils, mNumLotOrderBeans);
                    break;
                case Configs.SYX5_Q3ZUXDAN:
                    fragment = new Syx5DanFragment(Configs.SYX5_Q3ZUXDAN, mShakeUtils, mNumLotOrderBeans);
                    break;
                default:
                    break;
            }
            // 把frament加入到缓存中
//            mSyx5DanFragments.put(position, fragment);
        }
        return fragment;
    }

    //新建奖金计算的fragment
    public static BaseFragment createCalculateFragment(int position) {
        BaseFragment fragment = mCalculateFragments.get(position);
        if (null == fragment) {
            switch (position) {
                case Configs.CALCULATE_DLT:
                    fragment = new CalculateDltFragment();
                    break;
                case Configs.CALCULATE_PL3:
                    fragment = new CalculatePl3Fragment();
                    break;
                case Configs.CALCULATE_PL5:
                    fragment = new Pl5CalculateFragment();
                    break;
                case Configs.CALCULATE_QXC:
                    fragment = new QxcCalculateFragment();
                    break;
                case Configs.CALCULATE_SFC9:
                    fragment = new CalSf9Fragment();
                    break;
                case Configs.CALCULATE_SFC14:
                    fragment = new CalSf14Fragment();
                    break;
                case Configs.CALCULATE_BQC:
                    fragment = new CalaculateBqcFragment();
                    break;
                case Configs.CALCULATE_JQC:
                    fragment = new CalaculateJqcFragment();
                    break;
                default:
                    break;
            }
            // 把frament加入到缓存中
//            mCalculateFragments.put(position, fragment);
        }
        return fragment;
    }

    public static BaseFragment createUserCenterFragment(boolean isLogin) {
        BaseFragment fragment = null;
        if (isLogin) {
            fragment = new UserCenterFragment();
        } else {
            fragment = new UserLoginFragment();
        }
        return fragment;
    }


    /**
     * 清除缓存的Fragment
     */
    public static void removeFragments() {
        mFragments.clear();
    }

    /**
     * 清除排列三中缓存的Fragment
     */
    public static void removePl3Fragments() {
        mPL3Fragments.clear();
    }

    /**
     * 清除十一选五中缓存的Fragment
     */
    public static void removeSyx5Fragments() {
        mSyx5Fragments.clear();
    }

    /**
     * 清除十一选5胆中缓存的Fragment
     */
    public static void removeSyx5DanFragments() {
        mSyx5DanFragments.clear();
    }
}
