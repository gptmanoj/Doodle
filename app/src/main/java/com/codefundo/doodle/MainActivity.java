package com.codefundo.doodle;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
GestureLibrary lib;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.txt);
        lib=GestureLibraries.fromRawResource(this,R.raw.gesture);
        if(!lib.load())finish();

        GestureOverlayView gestureOverlayView=findViewById(R.id.gesture);
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                ArrayList<Prediction> predictionArrayList=lib.recognize(gesture);
                for(Prediction prediction:predictionArrayList){
                    //Toast.makeText(MainActivity.this, prediction.score+""+prediction.name, Toast.LENGTH_SHORT).show();
                    if(prediction.score>3){
                        Toast.makeText(MainActivity.this," "+ prediction.name, Toast.LENGTH_SHORT).show();
                        txt.setText(prediction.name);
                    }
                }
            }
        });

    }
}
