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

import java.util.ArrayList;
import java.util.List;

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
    private List<FloatPoint> floatPoints = new ArrayList<>();
    private OnFinishListener onFinishListener;

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

    private void initBrush() {
        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeCap(Paint.Cap.ROUND);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(10f);
    }

    public OnFinishListener getOnFinishListener() {
        return onFinishListener;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                isOver = true;
                if (onFinishListener != null) {
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onFinishListener.onFinish(getBitmap());
                        }
                    }, 500);
                }
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    private void touchDown(MotionEvent event) {
        path.reset();
        float pointX = event.getX();
        float pointY = event.getY();
        FloatPoint floatPoint = new FloatPoint(pointX, pointY);
        floatPoints.add(floatPoint);
        path.moveTo(pointX, pointY);
    }

    private void touchMove(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        float previousX = floatPoints.get(floatPoints.size() - 1).x;
        float previousY = floatPoints.get(floatPoints.size() - 1).y;
        float dx = Math.abs(pointX - previousX);
        float dy = Math.abs(pointY - previousY);
        //两点之间的距离大于等于3时，生成贝塞尔绘制曲线
        if (dx >= 3 || dy >= 3) {
            //设置贝塞尔曲线的操作点为起点和终点的一半
            float cX = (pointX + previousX) / 2;
            float cY = (pointY + previousY) / 2;
            //二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
            path.quadTo(previousX, previousY, cX, cY);
            FloatPoint floatPoint = new FloatPoint(pointX, pointY);
            floatPoints.add(floatPoint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isOver) {
            if (nearLine(floatPoints)) {
                brush.setStrokeWidth(200);
                canvas.drawPath(path, brush);
            } else {
                FloatPoint start = floatPoints.get(0);
                FloatPoint end = floatPoints.get(floatPoints.size() - 1);
                viewRegion.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
                region.setPath(path, viewRegion);
                Rect rect = region.getBounds();
                if ((Math.pow((end.x - start.x), 2) + Math.pow((end.y - start.y), 2) > (rect.height() * rect.width()))) {
                    brush.setStrokeWidth(200);
                    canvas.drawPath(path, brush);
                } else {
                    brush.setStyle(Paint.Style.FILL);
                    drawRegion(canvas, region, brush);
                }
            }
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

    public void resetView() {
        floatPoints.clear();
        isOver = false;
        initBrush();
        path.reset();
        postInvalidate();
    }

    private boolean nearLine(List<FloatPoint> floatPoints) {
        FloatPoint startPoint = floatPoints.get(0);
        FloatPoint endPoint = floatPoints.get(floatPoints.size() - 1);
        float t = (endPoint.y - startPoint.y) / (endPoint.x - startPoint.x);
        float c = (endPoint.x * startPoint.y - startPoint.x * endPoint.y) / (endPoint.x - startPoint.x);
        for (FloatPoint floatPoint : floatPoints) {
            if (Math.abs(floatPoint.y - (t * floatPoint.x + c)) > 100) {
                return false;
            }
        }
        return true;
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        this.draw(canvas);
        return bitmap;
    }

    class FloatPoint {
        public FloatPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        float x;
        float y;
    }

    public interface OnFinishListener {
        void onFinish(Bitmap bitmap);
    }
}
