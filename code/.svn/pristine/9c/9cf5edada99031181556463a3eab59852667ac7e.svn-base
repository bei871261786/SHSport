package shlottery.gov.cn.lotterycenter.ui.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/1/3 13:49
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class EmptyRecycleview extends RecyclerView {

    private View emptyView;
    private static final String TAG = "hiwhitley";

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public EmptyRecycleview(Context context) {
        super(context);
    }

    public EmptyRecycleview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecycleview(Context context, AttributeSet attrs,
                            int defStyle) {
        super(context, attrs, defStyle);
    }

    private void checkIfEmpty() {
        LogUtils.i("emptyRecycoeview checkIfEmpty:"+emptyView+":::"+getAdapter());
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible =
                    getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
            LogUtils.i("emptyRecycoeview checkIfEmpty  VISIBLE:"+emptyView.getVisibility()+":::"+this.getVisibility());
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View emptyView, String emptyTiple) {
        LogUtils.i("emptyRecycoeview  setEmptyView"  +emptyView+":::"+emptyTiple);
        this.emptyView = emptyView;
        if (emptyTiple != null && !emptyTiple.isEmpty()) {
            TextView empty = (TextView) emptyView.findViewById(R.id.emptytiple);
            empty.setText(emptyTiple);
        }
        checkIfEmpty();
    }
}
