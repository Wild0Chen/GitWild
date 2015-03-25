package com.example.event.jafo.event;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Jafo on 2015/3/25.
 */
public class PlaneEnemy extends View {
    public float currentX;
    public float currentY;
    Bitmap planeEnemy;

    public PlaneEnemy(Context context)
    {
        super(context);
        planeEnemy=BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
        setFocusable(true);
    }


    @Override
      public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint p = new Paint();
        canvas.drawBitmap(planeEnemy,currentX,currentY,p);
    }
}
