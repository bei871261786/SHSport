package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment.SwitchJingCaiFangShiFragment;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/7 17:28
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class JingcaiSoccerActivity extends JInCaiBaseActivity {

    @Override
    protected void SwitchFragment(int i) {
        fragment = SwitchJingCaiFangShiFragment.createSoccerFragment(i);
        LogUtils.e(i + "=i");
    }

    @Override
    protected void prepareInit() {
        mTitleName = "竞足-";
        setLotid("jczq");
        mString = BaseApplication.getApplication().getResources().getStringArray(R.array.title_jincaizuqiu);
    }

    @Override
    protected int getTypeName() {
        return Configure_JC.TYPE_SOCCER;
    }
    @Override
    protected String getBaidutitle() {
        return "";
    }
}
