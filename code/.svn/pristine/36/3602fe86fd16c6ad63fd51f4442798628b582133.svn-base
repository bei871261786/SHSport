package shlottery.gov.cn.lotterycenter.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.fragment.LoginDialogFragment;
import shlottery.gov.cn.lotterycenter.ui.widget.CornerDialog;

/**
 * Created by yongchao.bei on 2016/7/29.
 */
public class SoccerBaseDialogUtil {

    public void createPaymentDialog(Context context, final Handler handler) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_payment, null);
        final CornerDialog dialog = new CornerDialog(context, view, R.style.corner_dialog);
//        dialog.setView(view);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        Button submit = (Button) view.findViewById(R.id.payment_dialog_payButton);

        final EditText edt = (EditText) view.findViewById(R.id.payment_dialog_pwd);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != edt.getText()) {
                    String pwd = edt.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("password", pwd);
                    Message msg = new Message();
                    msg.obj = bundle;
                    handler.sendMessage(msg);
                }
                dialog.dismiss();
            }
        });
    }

    public void createLoginDialog(AppCompatActivity activity) {
        View view = UIUtils.inflate(R.layout.dialog_login);
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(activity.getSupportFragmentManager(), "login...111.");
    }
}
