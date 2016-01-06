package com.jeney.uicomponent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2016/1/6
 */
public class BrushView extends View {
    private Paint brush = new Paint();
    private Path path = new Path();
    private Region region = new Region();
    private Region viewRegion = new Region();

    private boolean isOver;

    public BrushView(Context context) {
        this(context, null);
    }

    public BrushView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrushView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBrush();
    }

    private void initBrush(){
        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStyle(Paint.Style.STROKE);
        brush.setAntiAlias(true);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(10f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_UP:
                isOver = true;
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isOver) {
            path.close();
            viewRegion.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
            region.setPath(path, viewRegion);
            brush.setStyle(Paint.Style.FILL);
            drawRegion(canvas, region, brush);
        } else {
            canvas.drawPath(path, brush);
        }
    }

    private void drawRegion(Canvas canvas, Region rgn, Paint paint) {
        RegionIterator iter = new RegionIterator(rgn);
        Rect r = new Rect();
        while (iter.next(r)) {
            canvas.drawRect(r, paint);
        }
    }

    public void resetView(){
        isOver = false;
        initBrush();
        path.reset();
        postInvalidate();
    }

    public boolean contains(Point point){
        return region.contains(point.x,point.y);
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        this.draw(canvas);
        return bitmap;
    }
}
