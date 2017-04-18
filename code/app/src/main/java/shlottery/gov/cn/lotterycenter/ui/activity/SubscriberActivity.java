package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.DingYueBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.adapter.SubscriberAdapter;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JCDX;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JLRSF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JLSF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JZRSPF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JZSPF;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-26-0026 14:02
 * 描    述：订阅列表
 * 修订历史：
 * ================================================
 */

public class SubscriberActivity extends BaseActivity {
    @BindView(R.id.titlebar_back)
    ImageView titlebarBack;
    @BindView(R.id.titlebar_back_ll)
    LinearLayout titlebarBackLl;
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.titlebar_indicator)
    ImageView titlebarIndicator;
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.titlebar_title)
    LinearLayout titlebarTitle;
    @BindView(R.id.filtrate_tv)
    ImageView filtrateTv;
    @BindView(R.id.main_calendar)
    ImageView mainCalendar;
    @BindView(R.id.main_setting)
    ImageView mainSetting;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.subscriber_lsv)
    ListView subscriberLsv;
    @BindView(R.id.dingyue_ref)
    SwipeRefreshLayout dingyueRef;


    private String type = "";//类型(1-通知 2-推荐)
    private String mPageNo;//页码
    private String pageSize = "20";//每页数量 每页二十条

    private DingYueBean dingYueBean;//订阅返回的数据
    private DingYueBean baseDingYueBean = new DingYueBean();//加载后的总的DingyueBean的数据
    private DingYueBean.DataBean dataBean;
    private SubscriberAdapter subscriberAdapter;
    private OnScroollistener onscroollistener;

    private PopupWindow mDeletePopupWindow;
    private View mPopContentViw;

    private Map<Integer, Boolean> filteData = new HashMap();

    private PopupWindow pop;
    private boolean isBottom = false;//是否在最底部

    @Override
    protected void initView() {
        setContentView(R.layout.activity_subscriber);
        ButterKnife.bind(this);
        titlebarTv.setText("订阅");
        mainSetting.setImageResource(R.mipmap.filtrate);
        mainSetting.setVisibility(View.VISIBLE);
        isBottom = false;
        loadData(type, mPageNo, pageSize, filteData, false);
        dingyueRef.setColorSchemeResources(R.color.homeblue);
        onscroollistener = new OnScroollistener();
        baseDingYueBean.setData(dataBean);
        dingyueRef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                baseDingYueBean.getData().getList().clear();
                mPageNo = "1";
                isBottom = false;
                loadData(type, mPageNo, pageSize, filteData, false);
            }
        });

    }

    @OnClick(R.id.titlebar_back_ll)
    void back() {
        setResult(Configure.RESULT_UPDATE);
        closeThisActivity();
    }

    @OnClick(R.id.main_setting)
    void shaixuan() {
        Logger.e("筛选被点击了");
        showFilterpop();
    }

    @Override
    public void onBackPressed() {
        if (pop != null) {
            pop.dismiss();
            pop = null;
            return;
        }
        if (mDeletePopupWindow != null) {
            mDeletePopupWindow.dismiss();
            mDeletePopupWindow = null;
            return;
        }
        setResult(Configure.RESULT_UPDATE);
        closeThisActivity();
    }


    /* 设置添加屏幕的背景透明度
     * @param bgAlpha
     * 让popupWindow实现dialog效果
     */

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }

    //筛选弹出框
    private void showFilterpop() {
        View view = UIUtils.inflate(R.layout.dingyue_pop);
        final Map<Integer, Boolean> map = new HashMap<>();
        map.put(0, false);
        map.put(1, false);

        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final CheckedTextView zhuanjiaCheck = (CheckedTextView) view.findViewById(R.id.zhuanjia_check);
        final CheckedTextView gonggaoCheck = (CheckedTextView) view.findViewById(R.id.gonggao_check);
        TextView dingyueOkTv = (TextView) view.findViewById(R.id.dingyue_ok_tv);
        TextView dingyueCancleTv = (TextView) view.findViewById(R.id.dingyue_cancle_tv);

        gonggaoCheck.setChecked(filteData.get(0));
        map.put(0, filteData.get(0));

        zhuanjiaCheck.setChecked(filteData.get(1));
        map.put(1, filteData.get(1));

        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        //让pop可以点击外面消失掉
        pop.setBackgroundDrawable(new ColorDrawable(0));
        pop.showAsDropDown(baseTitleBar);
        backgroundAlpha(0.5f);
        dingyueCancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        dingyueOkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDingYueBean.getData().getList().clear();
                filteData.put(0, map.get(0));
                filteData.put(1, map.get(1));
                isBottom = false;
                loadData(type, mPageNo, pageSize, filteData, false);
                if (subscriberAdapter != null) {
                    subscriberAdapter.notifyDataSetChanged();
                }
                pop.dismiss();
            }
        });
        gonggaoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gonggaoCheck.isChecked()) {
                    map.put(0, false);
                    gonggaoCheck.setChecked(false);
                } else {
                    map.put(0, true);
                    gonggaoCheck.setChecked(true);
                }
            }
        });
        zhuanjiaCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zhuanjiaCheck.isChecked()) {
                    map.put(1, false);
                    zhuanjiaCheck.setChecked(false);
                } else {
                    map.put(1, true);
                    zhuanjiaCheck.setChecked(true);
                }
            }
        });

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((float) 1);
                pop = null;
            }
        });
    }

    //加载网络数据
    private void loadData(String type, final String pageNo, String pageSize, Map<Integer, Boolean> hashMap, final
    boolean loadMore) {
        type = initType(hashMap);
        OkGo.get(UrlManager.getSubscribeList(type, pageNo, pageSize)).connTimeOut(5000).cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        dingYueBean = gson.fromJson(s, DingYueBean.class);
                        if (dingYueBean != null) {
                            if (dingYueBean.getRet() == 100) {
                                if (dingYueBean.getData().getList() != null && dingYueBean.getData().getList().size() > 0) {
                                    baseDingYueBean.getData().getList().addAll(dingYueBean.getData().getList());
                                    subscriberAdapter = new SubscriberAdapter(baseDingYueBean);
                                    subscriberLsv.setAdapter(subscriberAdapter);
                                    subscriberLsv.setOnScrollListener(onscroollistener);
                                    subscriberLsv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                        @Override
                                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                                                id) {

                                            createDeletePop(view, position);
                                            return true;
                                        }
                                    });
                                    subscriberLsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                        //类型(0-文字通知 1-资讯通知 2-专家推荐)
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            if (baseDingYueBean.getData().getList().get(position).getType() == 0) {
                                                return;
                                            } else if (baseDingYueBean.getData().getList().get(position).getType() == 1) {
                                                Intent intent = new Intent(SubscriberActivity.this, NewsDetailActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putInt("id", baseDingYueBean.getData().getList().get(position)
                                                        .getNewsId());
                                                Logger.e(baseDingYueBean.getData().getList().get(position).getId() + "体彩分析id");
                                                bundle.putString("title", "体彩分析");
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else {
                                                Intent intent1 = new Intent(SubscriberActivity.this, Rmd_famousDetailActivity
                                                        .class);
                                                Bundle bundle = getDetailBundle(baseDingYueBean.getData().getList().get
                                                        (position).getLotId(), baseDingYueBean.getData().getList().get
                                                        (position).getRecomId(), baseDingYueBean.getData().getList().get
                                                        (position).getLotName());
                                                intent1.putExtras(bundle);
                                                startActivity(intent1);
                                            }
                                        }
                                    });
                                } else {
                                    if (!StringUtils.isEmpty(mPageNo)) {
                                        mPageNo = (Integer.parseInt(pageNo) - 1) + "";
                                    }
                                    if (loadMore) {
                                        if (!isBottom) {
                                            isBottom = true;
                                            UIUtils.showToastSafe("服务器没数据啦");
                                        }
                                    }
                                }
                            } else {
                                UIUtils.showToastSafe(dingYueBean.getMsg());
                            }
                        }
                        if (dingyueRef != null) {
                            dingyueRef.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        if (dingyueRef != null) {
                            dingyueRef.setRefreshing(true);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        UIUtils.showToastSafe("网络异常,请检查网络设置");
                        if (dingyueRef != null) {
                            dingyueRef.setRefreshing(false);
                        }
                    }
                });

    }

    private String initType(Map<Integer, Boolean> hashMap) {
        if (hashMap.get(0) && !hashMap.get(1)) {
            return "0,1";
        } else if (hashMap.get(1) && !hashMap.get(0)) {
            return "2";
        } else {
            return "";
        }
    }

    private void deleData(String id, final int position) {
        OkGo.get(UrlManager.userdelSubscribe(id)).connTimeOut(5000).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                if (vcodeBean != null) {
                    if (vcodeBean.getRet() == 100) {
                        UIUtils.showToastSafe("删除成功");
                        baseDingYueBean.getData().getList().remove(position);
                        subscriberAdapter.notifyDataSetChanged();
                    } else {
                        UIUtils.showToastSafe(vcodeBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络");
            }
        });
    }

    @Override
    protected void init() {
        dataBean = new DingYueBean.DataBean();
        dataBean.setList(new ArrayList<DingYueBean.DataBean.ListBean>());
        filteData.put(0, false);
        filteData.put(1, false);//初始化筛选数据
    }


    private class OnScroollistener implements AbsListView.OnScrollListener {

        private int visibleItemCount;
        private int visibleLastIndex;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            int itemsLastIndex = subscriberAdapter.getCount();    //数据集最后一项的索引
            int lastIndex = itemsLastIndex;             //加上底部的loadMoreView项
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
                if (StringUtils.isEmpty(mPageNo)) {
                    mPageNo = 2 + "";
                } else {
                    mPageNo = (Integer.parseInt(mPageNo) + 1) + "";
                }
                loadData(type, mPageNo, pageSize, filteData, true);
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            this.visibleItemCount = visibleItemCount;
            visibleLastIndex = firstVisibleItem + visibleItemCount;
        }
    }

    private void createDeletePop(View v, int position) {
        LogUtils.i("position createDeletePop :" + position);
        initDeletePopView(position);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int dalta = (500 - v.getWidth()) / 2;
        Log.e(this.getClass().getName(), ":" + location[0] + "=======" + location[1]);
        mDeletePopupWindow.showAtLocation(v, Gravity.CENTER | Gravity.TOP, location[0], location[1] - 100);
    }

    private void initDeletePopView(final int position) {
        mPopContentViw = LayoutInflater.from(this).inflate(R.layout.popwindow_orderlistdelete, null);
        TextView cancel = (TextView) mPopContentViw.findViewById(R.id.pop_order_cancel);
        TextView delete = (TextView) mPopContentViw.findViewById(R.id.pop_order_delete);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeletePopupWindow.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleData(baseDingYueBean.getData().getList().get(position).getId() + "", position);
                mDeletePopupWindow.dismiss();
            }
        });
        mDeletePopupWindow = new PopupWindow(mPopContentViw, 500, 150);
        mDeletePopupWindow.setOutsideTouchable(true);
        mDeletePopupWindow.setFocusable(true);
        //让pop可以点击外面消失掉
        mDeletePopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mDeletePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mDeletePopupWindow = null;
            }
        });

    }


    private Bundle getDetailBundle(String lotId, int id, String lotName) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        String title = "";
        //        String lotName = bean.getLotName();
        //        String lotId = bean.getLotId();
        int activityType = 0;
        LogUtils.i("getDetailBundle" + lotName + ":::" + lotId);
        switch (lotId) {
            case "jldx":
                activityType = ACTIVITYTYPE_JCDX;
                break;
            case "jlrsf":
                activityType = ACTIVITYTYPE_JLRSF;
                break;
            case "jzxspf":
                activityType = ACTIVITYTYPE_JZRSPF;
                break;
            case "jzspf":
                activityType = ACTIVITYTYPE_JZSPF;
                break;
            case "jlsf":
                activityType = ACTIVITYTYPE_JLSF;
                break;
            case "sf14":
                activityType = Configs.ACTIVITYTYPE_R9;
                break;
            case "sf9":
                activityType = Configs.ACTIVITYTYPE_R9;
                break;

            //竞彩足球 半全场
            case "jzbqc":
                activityType = Configs.ACTIVITYTYPE_JZBQC;
                break;
            //竞彩足球 比分
            case "jzbf":
                activityType = Configs.ACTIVITYTYPE_JZBF;
                break;
            //竞彩足球 总进球数
            case "jzjqs":
                activityType = Configs.ACTIVITYTYPE_JZJQS;
                break;
            //竞彩足球 混合过关
            case "jzhh":
                activityType = Configs.ACTIVITYTYPE_JZHH;
                break;

            default:
                break;
        }
//        if (lotId.equals("jldx")) {
//            activityType = ACTIVITYTYPE_JCDX;
//        }
//        if (lotId.equals("jlrsf")) {
//            activityType = ACTIVITYTYPE_JLRSF;
//        }
//        if (lotId.equals("jzxspf")) {
//            activityType = ACTIVITYTYPE_JZRSPF;
//        }
//        if (lotId.equals("jzspf")) {
//            activityType = ACTIVITYTYPE_JZSPF;
//        }
//        if (lotId.equals("jlsf")) {
//            activityType = ACTIVITYTYPE_JLSF;
//        }
//        if (lotId.equals("sf14") || lotId.equals("sf9")) {
//            activityType = Configs.ACTIVITYTYPE_R9;
//        }

        if (lotName.contains("竞彩")) {
            LogUtils.i("getDetailBundle contains jincai");
            title = lotName;
            //            title = title.replace("球", "");
            title = title.replace(" ", "-");
        } else if (lotName.contains("胜负彩")) {
            LogUtils.i("getDetailBundle contains shengfu");
            title = lotName.replace(" ", "");

        }
        title = title + "推荐";
        bundle.putInt(Configs.SFCDINGDAN_INT_KEY, activityType);
        bundle.putString("title", title);
        return bundle;
    }

    @Override
    protected String getBaidutitle() {
        return "订阅";
    }
}
