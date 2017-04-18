package shlottery.gov.cn.lotterycenter.callback;

import com.baidu.location.BDLocation;

public interface LocationFace {
	void locationSuccess(BDLocation location);
	void locationError();
}
