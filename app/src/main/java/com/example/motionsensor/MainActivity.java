package com.example.motionsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  private Accelermeter accelermeter;
  private  Gyscope gyscope;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Accelermeter accelermeter=new Accelermeter(this);
        Gyscope gyscope=new Gyscope(this);
      accelermeter.setListener(new Accelermeter.listener() {
          @Override
          public void onTranslation(float x, float y, float z) {
          if(x > 1.0f){
              getWindow().getDecorView().setBackgroundColor(Color.BLUE);
          }else if(x < -1.0f){
              getWindow().getDecorView().setBackgroundColor(Color.GREEN);
          }
          }
      });
      ////for rotation
     gyscope.setListener(new Gyscope.listener() {
         @Override
         public void onTranslation(float x, float y, float z) {
             if(x > 1.0f){
                 getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
             }else if(x < -1.0f){
                 getWindow().getDecorView().setBackgroundColor(Color.RED);
             }
         }
     });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    accelermeter.unregister();
    gyscope.unregister();
    }
}