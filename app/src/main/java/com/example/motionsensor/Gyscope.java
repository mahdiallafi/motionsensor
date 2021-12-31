package com.example.motionsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyscope {
    public  interface  listener{
        void onTranslation(float x,float y, float z);

    }
    private Gyscope.listener listener;
    public  void setListener(Gyscope.listener l){
        listener=l;
    }
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    Gyscope(Context context){
        sensorManager=(SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(listener !=null){
                    listener.onTranslation(sensorEvent.values[0],
                            sensorEvent.values[1],sensorEvent.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(sensorEventListener,sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public  void unregister(){
        sensorManager.unregisterListener(sensorEventListener);
    }

}
