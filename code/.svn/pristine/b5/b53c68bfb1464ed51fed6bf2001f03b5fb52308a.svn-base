package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.protool.BaseProtocol;
import shlottery.gov.cn.lotterycenter.protool.JincaiSoccerProtocol;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.BifenExpandableListviewAdapter;
import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


/**
 * Created by booob on 2016-05-26-0026.
 */
public class BiFenFragment extends JcBaseFragment {


    @Override
    public View getView() {
        View view = UIUtils.inflate(R.layout.football_bifen_fragment);
        return view;
    }

    @Override
    public String getSubLotId() {
        return "jzbf";
    }

    @Override
    public int getJcType() {
        return Configure_JC.TAB_BF;
    }

    @Override
    public BaseExpandableListAdapter getMyAdapter(Context context, List<List<MatchsBean>> mFilterGuoGuanList, Handler handler) {
        return new BifenExpandableListviewAdapter(context,mFilterGuoGuanList,handler);
    }
    @Override
    public BaseProtocol getProtocal() {
        return new JincaiSoccerProtocol(getActivity());
    }
    @Override
    protected String getTitle() {
        return "比分";
    }
}

