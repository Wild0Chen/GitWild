package com.example.event.jafo.event;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.*;
import android.view.MenuItem;

public class MainActivity extends Activity {
    private int nSpeed=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去掉窗口标题
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final PlaneView plv=new PlaneView(this);
        setContentView(plv);
        //获取窗口管理器
        WindowManager windowManager=getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        DisplayMetrics me=new DisplayMetrics();
        //获得屏幕宽和高
        display.getMetrics(me);
        //飞机初始位置
        plv.currentX=me.widthPixels/2;
        plv.currentY=me.heightPixels/2+10;
       plv.setOnTouchListener(new View.OnTouchListener()
       {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               plv.currentX=motionEvent.getX();
               plv.currentY=motionEvent.getY();
               plv.invalidate();
               return true;
           }
       });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
