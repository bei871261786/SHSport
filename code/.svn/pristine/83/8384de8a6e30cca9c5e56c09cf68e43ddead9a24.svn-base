package shlottery.gov.cn.lotterycenter.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.LocationBean;
import shlottery.gov.cn.lotterycenter.callback.LocationFace;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.adapter.DituAdapter;
import shlottery.gov.cn.lotterycenter.utils.LocationFaceUtil;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.ToastUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-16-0016 13:14
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class WangdianActivity extends BaseActivity implements BaiduMap.OnMarkerDragListener, BaiduMap.OnMarkerClickListener, OnGetGeoCoderResultListener, EasyPermissions.PermissionCallbacks {
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
    @BindView(R.id.mapview)
    MapView mBaiduMapView;
    @BindView(R.id.search_lsv)
    ListView searchLsv;
    @BindView(R.id.activity_wangdian)
    LinearLayout activityWangdian;
    @BindView(R.id.search_edt)
    EditText searchEdt;
    @BindView(R.id.search_button_im)
    ImageView searchButtonIm;
    @BindView(R.id.search_ll)
    LinearLayout searchLl;
    //    private MapView mBaiduMapView;  //地图界面
    private ProgressDialog progressDialog;//进度条
    private View popupView; // 弹框
    private TextView tvpopup;//弹框上的文字
    private BaiduMap mBaiduMap; //地图的管理类
    private BDLocation bdLocation; //定位类
    private GeoCoder geoCoder; // 经纬度地理位置坐标反转类
    private String latitude;//纬度
    private String longitude;//经度
    private String keyword = "";
    private String pageNo = "";
    private String pageSize = "10";
    private LocationBean locationbean;
    private LocationBean baseLocationbean = new LocationBean();
    LocationBean.DataBean databean;
    private DituAdapter dituadapter;
    private OnScroollistener onscroollistener;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_SETTINGS_SCREEN = 125;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_wangdian);
        ButterKnife.bind(this);
        //  mBaiduMapView = (MapView) findViewById(R.id.mapview);
        //        WangdianActivityPermissionsDispatcher.showToastWithCheck(this);

        titlebarTv.setText("网点导航");
//        routeSearch.setOnGetRoutePlanResultListener(WangdianActivity.this);
        onscroollistener = new OnScroollistener();
        searchLsv.setOnScrollListener(onscroollistener);
        databean = new LocationBean.DataBean();
        databean.setList(new ArrayList<LocationBean.DataBean.ListBean>());

        searchLsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LatLng startAddr;
                LatLng endAddr;
                startAddr = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                endAddr = new LatLng(baseLocationbean.getData().getList().get(position).getLatitude(), baseLocationbean.getData().getList().get(position).getLongitude());
//                Logger.e(baseLocationbean.getData().getList().get(position).getLatitude() + "终点纬度" + baseLocationbean.getData().getList().get(position).getLongitude() + "终点经度");
                startRouterResult(startAddr, endAddr);

            }
        });
        cheackLocation();

    }

/*    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//在需要获取权限的地方注释
    void showToast() {
        initLocation();
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
//提示用户为什么需要此权限
    void showWhy(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("需要位置权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//再次执行请求
                    }
                })
                .show();
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
            //一旦用户拒绝了
    void denied() {
        Toast.makeText(this, "没有位置权限", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
//用户选择的不再询问
    void notAsk() {
        Toast.makeText(this, "拒绝获取位置权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        WangdianActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }*/

    @OnClick(R.id.titlebar_back_ll)
    void back() {
        finish();
    }

    @OnClick(R.id.search_button_im)
    void search() {
        keyword = searchEdt.getText().toString();
        if (StringUtils.isEmpty(keyword)) {
            UIUtils.showToastSafe("搜索内容不能为空");
            return;
        }
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchEdt.getWindowToken(), 0);
//        latitude = "";
//        longitude = "";
        pageNo = "";
        baseLocationbean.getData().getList().clear();
        getLocation(keyword, "", "", pageNo, pageSize);
    }

    @Override
    protected void init() {

    }

    /**
     * @Title: initLocation
     * @Description: 发起定位
     * @return: void
     */
    private void initLocation() {
        mBaiduMap = mBaiduMapView.getMap();
        progressDialog = ProgressDialog.show(WangdianActivity.this, "定位", "正在定位......");
        new LocationFaceUtil(getBaseContext(), new LocationFace() {

            @Override
            public void locationSuccess(BDLocation location) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                bdLocation = location;
                addMarker(null);
                latitude = bdLocation.getLatitude() + "";
                longitude = bdLocation.getLongitude() + "";

                Logger.e(latitude + " 纬度 ");
                Logger.e(longitude + "经度");
                getLocation(keyword, latitude, longitude, pageNo, pageSize);
            }

            @Override
            public void locationError() {
                UIUtils.showToastSafe("定位失败");
                progressDialog.dismiss();
            }
        });
    }

    //添加当前位置的覆盖物,蓝色
    private void addMarker(Marker marker) {
        // 设置地图类型 MAP_TYPE_NORMAL 普通图； MAP_TYPE_SATELLITE 卫星图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启交通图
        mBaiduMap.setTrafficEnabled(true);
        // 设置地图当前级别
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.zoomTo(16);
        mBaiduMap.setMapStatus(statusUpdate);
        //构建覆盖物的信息
        LatLng latLng = null;
        if (marker == null) {
            latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        } else {
            latLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        }
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.mipmap.positionblue);
        OverlayOptions option = new MarkerOptions().position(latLng).icon(descriptor).title(bdLocation.getAddress().address).draggable(true);
        //清楚地图上所有的覆盖物
        mBaiduMap.clear();
        //将覆盖物添加到地图上
        mBaiduMap.addOverlay(option);
        // 将覆盖物设置为地图中心
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
        //以动画方式更新地图状态，动画耗时 300 ms
        mBaiduMap.animateMapStatus(u);
        mBaiduMap.setOnMarkerClickListener(WangdianActivity.this);
        mBaiduMap.setOnMarkerDragListener(WangdianActivity.this);
    }


    /**
     * @param arg0
     * @return
     * @Title: onMarkerClick
     * @Description:覆盖物的点击事件
     */
    @Override
    public boolean onMarkerClick(Marker arg0) {
        /*if (geoCoder == null) {
            geoCoder = GeoCoder.newInstance();
            geoCoder.setOnGetGeoCodeResultListener(WangdianActivity.this);// 设置反地理查询监听器
        }
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(arg0.getPosition()));*/
        addPoupMarker(arg0.getPosition(), arg0.getTitle());
        return true;
    }

    /**
     * @param arg0
     * @Title: onMarkerDrag
     * @Description:覆盖物拖拽过程中的方法
     */
    @Override
    public void onMarkerDrag(Marker arg0) {

    }

    /**
     * @param arg0
     * @Title: onMarkerDrag
     * @Description:覆盖物拖拽结束的方法
     */
    @Override
    public void onMarkerDragEnd(Marker arg0) {

    }

    /**
     * @param arg0
     * @Title: onMarkerDrag
     * @Description:覆盖物拖拽开始的方法
     */
    @Override
    public void onMarkerDragStart(Marker arg0) {
        mBaiduMap.hideInfoWindow();
    }

    /**
     * @param arg0
     * @Title: onGetGeoCodeResult
     * @Description: 坐标换算 根据地址得到坐标
     */
    @Override
    public void onGetGeoCodeResult(GeoCodeResult arg0) {

    }

    /**
     * @param arg0
     * @Title: onGetReverseGeoCodeResult
     * @Description: 坐标换算，根据坐标得到地质
     */
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
        addPoupMarker(arg0.getLocation(), arg0.getAddress());
    }

    private void addPoupMarker(LatLng pt, String address) {
        //创建InfoWindow展示的view
        if (popupView == null) {
            popupView = LayoutInflater.from(WangdianActivity.this).inflate(R.layout.popup_map, null);
            tvpopup = (TextView) popupView.findViewById(R.id.popup_text);
        }
        tvpopup.setText(address);
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(popupView, pt, -47);
        //显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);

    }


    private void getLocation(String keyword, final String latitude, final String longitude, String pageNo, String pageSize) {
        OkGo.get(UrlManager.getList(keyword, latitude, longitude, pageNo, pageSize)).connTimeOut(5000).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                locationbean = gson.fromJson(s, LocationBean.class);
//                Logger.e(s + "getlist");
//                Logger.e(locationbean.getData().getCount() + "长度");
                if (locationbean != null) {
                    if (locationbean.getRet() == 100) {
                        if (locationbean.getData().getList() != null && locationbean.getData().getList().size() > 0) {
                            databean.getList().addAll(locationbean.getData().getList());
                            baseLocationbean.setData(databean);
                            if (dituadapter == null) {
                                dituadapter = new DituAdapter(WangdianActivity.this, baseLocationbean);
                                searchLsv.setAdapter(dituadapter);
                                for (int i = 0; i < locationbean.getData().getList().size(); i++) {
                                    BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.mipmap.positionred);
                                    LatLng latLng = new LatLng(locationbean.getData().getList().get(i).getLatitude(), locationbean.getData().getList().get(i).getLongitude());
                                    OverlayOptions option = new MarkerOptions().position(latLng).icon(descriptor).title(locationbean.getData().getList().get(i).getAddress()).draggable(true);
                                    Marker mapMaker = (Marker) mBaiduMap.addOverlay(option);
//                               mapMaker.setTitle( locationbean.getData().getList().get(i).getAddress());
                                    mBaiduMap.setOnMarkerClickListener(WangdianActivity.this);
//                                mBaiduMap.setOnMarkerDragListener(WangdianActivity.this);
                                }
                            } else {
                                dituadapter.notifyDataSetChanged();
                            }
                        } else {
                            UIUtils.showToastSafe("没有更多数据了");
                        }

                    }
                }
            }
        });
    }

/*    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        BaseApplication.walkingRouteResult = walkingRouteResult;
        Intent intent = new Intent(WangdianActivity.this, GLMapActivity.class);
        startActivity(intent);
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }*/

    private void startRouterResult(LatLng startAddr, LatLng endAddr) {
        PlanNode stNode = PlanNode.withLocation(startAddr);
        PlanNode enNode = PlanNode.withLocation(endAddr);

        Intent intent = new Intent(WangdianActivity.this, RoutePlanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("stNode", stNode);
        bundle.putParcelable("enNode", enNode);
        intent.putExtras(bundle);
        startActivity(intent);
//        routeSearch.walkingSearch(new WalkingRoutePlanOption().from(stNode).to(enNode));

    }

    private class OnScroollistener implements AbsListView.OnScrollListener {

        private int visibleItemCount;
        private int visibleLastIndex;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            int itemsLastIndex = dituadapter.getCount();    //数据集最后一项的索引
            int lastIndex = itemsLastIndex;             //加上底部的loadMoreView项
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
                if (StringUtils.isEmpty(pageNo)) {
                    pageNo = 2 + "";
                } else {
                    pageNo = (Integer.parseInt(pageNo) + 1) + "";
                }
                getLocation(keyword, latitude, longitude, pageNo, pageSize);
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            this.visibleItemCount = visibleItemCount;
            visibleLastIndex = firstVisibleItem + visibleItemCount;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }


    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cheackLocation() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Have permission, do the thing!

            initLocation();
        } else {
            // Ask for one permission
            Toast.makeText(this, "网点查询需要位置权限", Toast.LENGTH_LONG).show();
            EasyPermissions.requestPermissions(this, "需要位置权限",
                    RC_CAMERA_PERM, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "如果没有位置权限,无法进行网点查询。打开应用程序设置界面修改应用程序权限?")
                    .setTitle("应用设置")
                    .setPositiveButton("设置")
                    .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SETTINGS_SCREEN) {
            ToastUtils.showShort(WangdianActivity.this, "退出当前页面后重试");
        }
    }
    @Override
    protected String getBaidutitle() {
        return "网点导航";
    }
}
