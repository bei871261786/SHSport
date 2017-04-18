package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.protool.BaseProtocol;
import shlottery.gov.cn.lotterycenter.protool.JincaiBasketballProtocol;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.DaXiaoFenExpandableListviewAdapter;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


/**
 * Created by booob on 2016-05-21-0021.
 * 足球竞彩胜平负页面
 */


public class DaXiaoFenFragment extends JcBaseFragment {

    @Override
    public View getView() {
        View view = UIUtils.inflate(R.layout.football_shengpingfu_fragment);
        return view;
    }

    @Override
    public String getSubLotId() {
        return "jldx";
    }

    @Override
    public int getJcType() {
        return Configure_JC.TAB_DXF;
    }

    @Override
    public BaseExpandableListAdapter getMyAdapter(Context context, List<List<MatchsBean>> mFilterGuoGuanList, Handler handler) {
        return new DaXiaoFenExpandableListviewAdapter(mFilterGuoGuanList, handler);
    }

    @Override
    public BaseProtocol getProtocal() {
        return new JincaiBasketballProtocol(getActivity());
    }
    @Override
    protected String getTitle() {
        return "大小分";
    }
}
