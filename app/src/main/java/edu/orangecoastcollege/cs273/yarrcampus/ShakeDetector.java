package edu.orangecoastcollege.cs273.yarrcampus;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by kevin_000 on 12/12/2016.
 */

public class ShakeDetector implements SensorEventListener{

    private static final float SHAKE_THRESHOLD = 30f;
    private static final int SHAKE_TIME_LAPSE = 1000;
    private long timeOfLastShake;
    private OnShakeListener shakeListener;
    public ShakeDetector(OnShakeListener listener){
        shakeListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            float gForceX = x - SensorManager.GRAVITY_EARTH;
            float gForceY = y - SensorManager.GRAVITY_EARTH;
            float gForceZ = z - SensorManager.GRAVITY_EARTH;

            double vector = Math.pow(gForceX, 2.0) + Math.pow(gForceY, 2.0) + Math.pow(gForceZ, 2.0);

            float gForce = (float) Math.sqrt(vector);

            if(gForce > SHAKE_THRESHOLD){
                long now = System.currentTimeMillis();
                if(now - timeOfLastShake > SHAKE_TIME_LAPSE)
                    shakeListener.onShake();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public interface OnShakeListener{
        void onShake();
    }
}
