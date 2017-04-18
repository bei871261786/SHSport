package shlottery.gov.cn.lotterycenter.Base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mobstat.StatService;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.JcActivityFragment_DataCallback;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

public abstract class BaseFragment2<Data> extends Fragment implements LoadCallback<Data> {
    protected List<List<Data>> mDanGuanList = new ArrayList<>();
    protected List<List<Data>> mGuoGuanList = new ArrayList<>();
    protected List<List<Data>> mFilterGuoGuanList = new ArrayList<>();
    protected List<List<Data>> mFilterDanGuanList = new ArrayList<>();
    protected JcActivityFragment_DataCallback callback;
    private boolean isLoaded = false;
    private View mContentView;
    private String baiduTitle = "";
    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMessge(msg);
        }
    };

    @Override
    @Subscribe
    final public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.i("fragment oncreate");
        super.onCreate(savedInstanceState);

        if (baiduTitle != null && !baiduTitle.isEmpty()) {

        }
        registEnevtBus();
        if (getActivity() instanceof JcActivityFragment_DataCallback) {
            callback = (JcActivityFragment_DataCallback) getActivity();
        }
        handler.sendEmptyMessage(Configure_JC.HANDLER_INIT);
        isLoaded = false;
        baiduTitle = getTitle();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {
        }
        init();
    }

    @Nullable
    @Override
    final public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        if (!isLoaded) {
            mContentView = beginCreateView(inflater, container, savedInstanceState);
            refreshLoad();
        }

        baiduTitle = getTitle();

        return mContentView;
    }

    @Override
    @Subscribe
    public void onDestroy() {
        super.onDestroy();
        unRegistEnevtBus();
    }

    public void refreshLoad() {
        if (beginLoad()) {
//            DialogUtils.showLoadingDialog(getActivity());
        }
    }


    protected void registEnevtBus() {
    }

    ;

    /**
     * 初始化操作，在base的oncreate里面
     */
    protected abstract void init();

    protected void unRegistEnevtBus() {
    }

    ;

    /**
     * 处理handler的操作
     *
     * @param msg
     */
    protected abstract void handleMessge(Message msg);

    /**
     * 加载布局，在base的oncreateview里面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    protected abstract View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 开始加载数据
     *
     * @return
     */
    protected abstract boolean beginLoad();

    /**
     * 加载成功后处理
     *
     * @param loadData
     */
    protected abstract void handleSucess(Object loadData);

    /**
     * 加载失败后处理
     */
    protected abstract void handleError();

    @Override
    public void onSucess(Data loadData) {
        isLoaded = true;
        LogUtils.i("onSucess loadData:" + loadData);
        handleSucess(loadData);
    }

    @Override
    public void onError() {
        isLoaded = true;
        handleError();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {
            StatService.onPageStart(getActivity(), baiduTitle);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {
            StatService.onPageEnd(getActivity(), baiduTitle);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {
        }
    }

    protected abstract String getTitle();
}
