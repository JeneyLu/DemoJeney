package com.jeney.uicomponent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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

    private boolean isOver;

    public BrushView(Context context) {
        this(context, null);
    }

    public BrushView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrushView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStyle(Paint.Style.STROKE);
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
            case MotionEvent.ACTION_POINTER_UP:
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
        if(isOver){
            path.close();
        }
        canvas.drawPath(path, brush);
    }

    public Bitmap getBitmap() {
        this.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        this.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        this.buildDrawingCache();
        Bitmap bitmap = this.getDrawingCache();
        return bitmap;
    }
}
