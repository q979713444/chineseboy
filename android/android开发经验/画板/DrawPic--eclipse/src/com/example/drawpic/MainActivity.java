package com.example.drawpic;

import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;
import android.app.Activity;
import android.os.Bundle;



public class MainActivity extends Activity {
    private DrawableView drawableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawableView = (DrawableView) findViewById(R.id.paintView);
        DrawableViewConfig config = new DrawableViewConfig();
        config.setStrokeColor(getResources().getColor(android.R.color.black));
        config.setStrokeWidth(5.0f);
        config.setMinZoom(1.0f);
        config.setMaxZoom(3.0f);
        config.setCanvasHeight(700);
        config.setCanvasWidth(400);
        config.setShowCanvasBounds(true);
        drawableView.setConfig(config);
    }

}
