package shlottery.gov.cn.lotterycenter.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by yongchao.bei on 2016/8/1.
 */
public class CornerDialog extends Dialog {

    private static int default_width = 180;//默认宽度
    private static int default_height = 100;//默认高度

    public CornerDialog(Context context, View layout, int style) {
        this(context, default_width, default_height, layout, style);
    }

    public CornerDialog(Context context, int width, int height, View layout, int style) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
//        params.height = height;
//        params.width = width;
        window.setAttributes(params);
    }


}
