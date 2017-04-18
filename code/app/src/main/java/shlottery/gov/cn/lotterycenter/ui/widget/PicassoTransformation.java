package shlottery.gov.cn.lotterycenter.ui.widget;


import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

import shlottery.gov.cn.lotterycenter.utils.DensityUtil;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ScreenUtils;

/**
 * @创建者 Tyler Wang.
 * @创建时间 2017/3/23  9:10.
 * @描述 ${Picasso图片转换器,等比例缩放,宽度固定,高度可变}.
 */
public class PicassoTransformation implements Transformation {

    private final int mTargetWidth;

    public PicassoTransformation(Context context) {
        //计算目标宽度
        mTargetWidth = ScreenUtils.getScreenWidth(context) - DensityUtil.dip2px(context, 30);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        LogUtils.d("source.getHeight()=" + source.getHeight() + ",source.getWidth()=" + source.getWidth() + "," +
                "targetWidth=" + mTargetWidth);

        if (source.getWidth() == 0) {
            return source;
        }
        //按照设置的宽度比例来缩放
        double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
        int targetHeight = (int) (mTargetWidth * aspectRatio);
        if (targetHeight != 0 && mTargetWidth != 0) {
            Bitmap result = Bitmap.createScaledBitmap(source, mTargetWidth, targetHeight, false);
            if (result != source) {
                // Same bitmap is returned if sizes are the same
                source.recycle();
            }
            return result;
        } else {
            return source;
        }
    }

    @Override
    public String key() {
        return "transformation" + " desiredWidth";
    }
}
