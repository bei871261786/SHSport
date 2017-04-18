package shlottery.gov.cn.lotterycenter.ui.activity;

import android.graphics.Color;
import android.graphics.PointF;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapDrawFrameCallback;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.orhanobut.logger.Logger;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.MyLatLng;
import shlottery.gov.cn.lotterycenter.utils.map.OverlayManager;
import shlottery.gov.cn.lotterycenter.utils.map.WalkingRouteOverlay;

/***
 * @author libiao
 * @ClassName: GLMapActivity
 * @Description: 得到搜索结果界面传递过来的经纬度，进行线路绘制的界面
 * @date 2015-8-24 上午9:56:13
 */
public class GLMapActivity extends BaseActivity implements BaiduMap.OnMapClickListener {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private List<MyLatLng> listLat = new ArrayList<>();
    private float[] vertexs;
    private FloatBuffer vertexBuffer;
    private List<RouteLine> listRoute = new ArrayList<RouteLine>();
    boolean useDefaultIcon = false;
    OverlayManager routeOverlay = null;
    private TextView popupText = null; // 泡泡view
    String nodeTitle = null;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_glmap);
        mMapView = (MapView) findViewById(R.id.glmap);
        initMap();
    }

    @Override
    protected void init() {
        List<WalkingRouteLine> list = BaseApplication.walkingRouteResult.getRouteLines();
        Logger.e(list.size() + "getRouteLines的长度");
        for (int i = 0; i < list.size(); i++) {
            RouteLine lines = list.get(i);
            listRoute.add(lines);
        }
//        RouteStep step = (RouteStep) listRoute.get(0).getAllStep();
//        List<LatLng> listLat1 = step.getWayPoints();
//		List<MyLatLng> mListLat = new ArrayList<MyLatLng>();
        for (int m = 0; m < listRoute.get(0).getAllStep().size(); m++) {
            RouteStep step = (RouteStep) listRoute.get(0).getAllStep().get(m);
            List<LatLng> listLat1 = step.getWayPoints();
            for (int i = 0; i < listLat1.size(); i++) {
                MyLatLng mll = new MyLatLng(listLat1.get(i).latitude, listLat1.get(i).longitude);
                listLat.add(mll);
            }
        }
    }

    private void initMap() {
        mBaiduMap = mMapView.getMap();
        // 地图点击事件处理
        mBaiduMap.setOnMapClickListener(this);


//		listLat = (List<MyLatLng>) getIntent().getSerializableExtra("latlng");
        // 设置地图类型 MAP_TYPE_NORMAL 普通图； MAP_TYPE_SATELLITE 卫星图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启交通图
        mBaiduMap.setTrafficEnabled(true);
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.zoomTo(15);//比例1:500
        mBaiduMap.setMapStatus(statusUpdate);
        MyLatLng myLatLng = listLat.get(0);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(new LatLng(myLatLng.getLatitude(), myLatLng.getLongitude()));
        mBaiduMap.setMapStatus(u);
        // mBaiduMap.setOnMapDrawFrameCallback(callback);
        List<LatLng> lists = new ArrayList<LatLng>();
        for (MyLatLng mll : listLat) {
            LatLng ll = new LatLng(mll.getLatitude(), mll.getLongitude());
            lists.add(ll);
        }

        popupText = new TextView(GLMapActivity.this);
        popupText.setBackgroundResource(R.drawable.popup);
        popupText.setTextColor(0xFF000000);
        popupText.setText(nodeTitle);
        LatLng latLng = new LatLng(listLat.get(0).getLatitude(), listLat.get(0).getLongitude());
        mBaiduMap.showInfoWindow(new InfoWindow(popupText, latLng, 0));
        /**
         * 地图SDK提供多种结合图形覆盖物，利用这些图形，可帮助您构建更加丰富多彩的地图应用。目前提供的几何图形有：点（Dot）、折线（
         * Polyline）、弧线（Arc）、圆（Circle）、多边形（Polygon）。 此处绘制折线
         */
        OverlayOptions polygonOption = new PolylineOptions().points(lists).color(Color.parseColor("#4169E1")).width(15);
        // 在地图上添加多边形Option，用于显示
        WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);
        mBaiduMap.setOnMarkerClickListener(overlay);
        routeOverlay = overlay;
        overlay.setData(BaseApplication.walkingRouteResult.getRouteLines().get(0));
        overlay.addToMap();
        overlay.zoomToSpan();
        mBaiduMap.addOverlay(polygonOption);

    }

    /********************
     * 使用OpenGl绘制，是出现Bug，坐标的转换和屏幕上的点的转换，会随着地图大小的拉伸，OpenGl的线不拉伸的情况，建议不要使用此方法
     *********************/
    // 定义地图绘制每一帧时 OpenGL 绘制的回调接口
    OnMapDrawFrameCallback callback = new OnMapDrawFrameCallback() {
        public void onMapDrawFrame(GL10 gl, MapStatus drawingMapStatus) {
            if (mBaiduMap.getProjection() != null) {
                // 计算折线的 opengl 坐标
                calPolylinePoint(drawingMapStatus);
                // 绘制折线
                drawPolyline(gl, Color.argb(255, 255, 0, 0), vertexBuffer, 10, 3, drawingMapStatus);
            }
        }
    };

    // 计算折线 OpenGL 坐标
    public void calPolylinePoint(MapStatus mspStatus) {
        PointF[] polyPoints = new PointF[listLat.size()];
        vertexs = new float[3 * listLat.size()];
        int i = 0;
        for (MyLatLng xy : listLat) {
            // 将地理坐标转换成 openGL 坐标
            polyPoints[i] = mBaiduMap.getProjection().toOpenGLLocation(new LatLng(xy.getLatitude(), xy.getLongitude()), mspStatus);
            vertexs[i * 3] = polyPoints[i].x;
            vertexs[i * 3 + 1] = polyPoints[i].y;
            vertexs[i * 3 + 2] = 0.0f;
            i++;
        }
        vertexBuffer = makeFloatBuffer(vertexs);
    }

    // 创建OpenGL绘制时的顶点Buffer
    private FloatBuffer makeFloatBuffer(float[] fs) {
        ByteBuffer bb = ByteBuffer.allocateDirect(fs.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(fs);
        fb.position(0);
        return fb;
    }

    // 绘制折线
    private void drawPolyline(GL10 gl, int color, FloatBuffer lineVertexBuffer, float lineWidth, int pointSize, MapStatus drawingMapStatus) {

        gl.glEnable(GL10.GL_BLEND);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        float colorA = Color.alpha(color) / 255f;
        float colorR = Color.red(color) / 255f;
        float colorG = Color.green(color) / 255f;
        float colorB = Color.blue(color) / 255f;

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, lineVertexBuffer);
        gl.glColor4f(colorR, colorG, colorB, colorA);
        gl.glLineWidth(lineWidth);
        gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, pointSize);

        gl.glDisable(GL10.GL_BLEND);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mBaiduMap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }
    @Override
    protected String getBaidutitle() {
        return "";
    }

}
