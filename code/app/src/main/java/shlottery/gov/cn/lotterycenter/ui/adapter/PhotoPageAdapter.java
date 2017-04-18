package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;

import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * @创建者 Tyler Wang.
 * @创建时间 2017/3/22  17:09.
 * @描述 ${资讯详情显示可缩放图片页面Adapter}.
 */
public class PhotoPageAdapter extends PagerAdapter {

    private List<String>         mImgUrls;
    private Context              mContext;
    private onItemClickListener  mOnItemClickListener;

    public interface onItemClickListener {
        void onItemClick();
    }

    public PhotoPageAdapter(Context context, List<String> imgUrls) {
        this.mContext = context;
        this.mImgUrls = imgUrls;
    }

    @Override
    public int getCount() {
        return mImgUrls == null ? 0 : mImgUrls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(mContext);
        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        String imgUrl = mImgUrls.get(position);
        if (!TextUtils.isEmpty(imgUrl)) {
            Picasso.with(mContext)
                    .load(imgUrl)
                    .into(photoView);
        }
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick();
                }
            }
        });
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
