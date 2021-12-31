package com.example.motionsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;



public class Accelermeter {
    public  interface  listener{
        void onTranslation(float x,float y, float z);

    }
  private listener listener;
    public  void setListener(listener l){
        listener=l;
    }
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    Accelermeter(Context context){
        sensorManager=(SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
    public void register(){

    }
    public  void unregister(){
        sensorManager.unregisterListener(sensorEventListener);
    }



}
