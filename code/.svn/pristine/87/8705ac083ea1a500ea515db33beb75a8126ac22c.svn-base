package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * Created by yongchao.bei on 2016/8/4.
 */
public class LoginDialogFragment extends LoginBaseFragment {
    @Override
    protected View prepareView() {
        View view = UIUtils.inflate(R.layout.dialog_login);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close = (ImageView) view.findViewById(R.id.dialog_login_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialogFragment.this.dismiss();
            }
        });
        return view;
    }

    @Override
    protected void handleSuccess(Activity mLoginActivity) {
        LoginDialogFragment.this.dismiss();
    }
    @Override
    protected String getTitle() {
        return "登陆";
    }
}
