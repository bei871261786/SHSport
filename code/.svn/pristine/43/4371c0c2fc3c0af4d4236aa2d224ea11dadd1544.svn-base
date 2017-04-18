package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.activity.SystemSettingActivity;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/23 13:59
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class UserLoginFragment extends LoginBaseFragment {
    private ImageView mSetting;
    @Override
    protected View prepareView() {
        View view = UIUtils.inflate(R.layout.login_layout);
        mSetting = (ImageView) view.findViewById(R.id.main_setting);
        mSetting.setVisibility(View.VISIBLE);

        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SystemSettingActivity.class));
            }
        });
        return view;
    }

    @Override
    protected void handleSuccess(Activity mLoginActivity) {
    }
    @Override
    protected String getTitle() {
        return "登陆";
    }
}
