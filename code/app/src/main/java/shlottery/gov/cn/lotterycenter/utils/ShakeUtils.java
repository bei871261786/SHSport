package shlottery.gov.cn.lotterycenter.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-18-0018 10:25
 * 描    述： * 摇一摇工具类
 * 使用说明：
 * private ShakeUtils mShakeUtils = null;
 * 1、在需要使用摇一摇功能的Activity实例化该工具类并设置摇一摇监听：
 * mShakeUtils = new_sign ShakeUtils( this );
 * mShakeUtils.setOnShakeListener(new_sign OnShakeListener{
 * public void onShake(){
 * // 此处为摇一摇触发后的操作
 * }
 * });
 * <p>
 * 2、分别在Activity的onResume和onPause方法中调用该工具类的onResume和onPause方法：
 * mShakeUtils.onResume();
 * mShakeUtils.onPause();
 * 修订历史：
 * ================================================
 */


public class ShakeUtils implements SensorEventListener {
    private long lastTime;
    private boolean isCan=true;

    public void setCan(boolean can) {
        isCan = can;
    }

    public ShakeUtils(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void setOnShakeListener(OnShakeListener onShakeListener) {
        mOnShakeListener = onShakeListener;
    }

    public void onResume() {
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentTime = System.currentTimeMillis();
        if (isCan) {
            if (currentTime - lastTime > 1000 && isCan) {
                lastTime = currentTime;
                int sensorType = event.sensor.getType();
                //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
                float[] values = event.values;
                if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                    //这里可以调节摇一摇的灵敏度
                    if ((Math.abs(values[0]) > SENSOR_VALUE || Math.abs(values[1]) > SENSOR_VALUE || Math.abs(values[2]) > SENSOR_VALUE)) {
                        if (null != mOnShakeListener) {
                            mOnShakeListener.onShake();
                        }
                    }
                }
            }
        }
    }
    public interface OnShakeListener {
        public void onShake();
    }

    private SensorManager mSensorManager = null;
    private OnShakeListener mOnShakeListener = null;
    private static final int SENSOR_VALUE = 12;
}

