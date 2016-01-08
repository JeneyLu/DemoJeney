package com.jeney.uicomponent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jeney.uicomponent.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:画圈圈自定义View
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2016/1/6
 */
public class BrushView extends View {
    private static final int BLOD_STROKE_WITH = 200;
    private static final int STRAIGHT_LINE_OFFSET = 50;
    //画笔
    private Paint brush = new Paint();
    //画笔路径
    private Path path = new Path();
    //划线的不规则区域
    private Region region = new Region();
    //整个view的矩形区域
    private Region viewRegion = new Region();
    //手指移动所有点的集合
    private List<FloatPoint> floatPoints = new ArrayList<>();
    //一次画圈动作结束后的回调接口
    private OnFinishListener onFinishListener;
    //一次画圈动作结束后的flag
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
        brush.setColor(getResources().getColor(R.color.light_blue));
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeCap(Paint.Cap.ROUND);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(10.0f);
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
                            resetView();
                        }
                    }, 100);
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
            //画线结束后
            if (nearLine(floatPoints)) {
                //画的线接近一条直线，加粗画笔，画线
                brush.setStrokeWidth(BLOD_STROKE_WITH);
                canvas.drawPath(path, brush);
            } else {
                FloatPoint start = floatPoints.get(0);
                FloatPoint end = floatPoints.get(floatPoints.size() - 1);
                viewRegion.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
                region.setPath(path, viewRegion);
                Rect rect = region.getBounds();
                if ((Math.pow((end.x - start.x), 2) + Math.pow((end.y - start.y), 2) > (rect.height() * rect.width()))) {
                    //画线的起点和终点之间距离较大，加粗画笔，画线
                    brush.setStrokeWidth(BLOD_STROKE_WITH);
                    canvas.drawPath(path, brush);
                } else {
                    //画线的起点和终点之间距离较小，填充path区域
                    brush.setStyle(Paint.Style.FILL);
                    drawRegion(canvas, region, brush);
                }
            }
        } else {
            canvas.drawPath(path, brush);
        }
    }

    /**
     * canvas上画region
     *
     * @param canvas
     * @param rgn
     * @param paint
     */
    private void drawRegion(Canvas canvas, Region rgn, Paint paint) {
        RegionIterator iterator = new RegionIterator(rgn);
        Rect r = new Rect();
        while (iterator.next(r)) {
            canvas.drawRect(r, paint);
        }
    }

    /**
     * 重置view
     */
    public void resetView() {
        floatPoints.clear();
        isOver = false;
        initBrush();
        path.reset();
        postInvalidate();
    }

    /**
     * 判断一系列点是否接近在一条直线上
     *
     * @param floatPoints
     * @return
     */
    private boolean nearLine(List<FloatPoint> floatPoints) {
        FloatPoint startPoint = floatPoints.get(0);
        FloatPoint endPoint = floatPoints.get(floatPoints.size() - 1);
        float t = (endPoint.y - startPoint.y) / (endPoint.x - startPoint.x);
        float c = (endPoint.x * startPoint.y - startPoint.x * endPoint.y) / (endPoint.x - startPoint.x);
        for (FloatPoint floatPoint : floatPoints) {
            if (Math.abs(floatPoint.y - (t * floatPoint.x + c)) > UIUtils.dip2px(getContext(), STRAIGHT_LINE_OFFSET)) {
                return false;
            }
        }
        return true;
    }

    /**
     * View转化成Bitmap
     *
     * @return
     */
    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        this.draw(canvas);
        return bitmap;
    }

    /**
     * 判断此view的parentView中的一点坐标是否在view的有色区域中
     *
     * @param point
     * @return
     */
    public boolean contain(Point point) {
        Bitmap bitmap = getBitmap();
        //屏幕坐标转成Bitmap上的坐标
        int x = point.x - getLeft();
        int y = point.y - getTop();
        if (x < 0 || y < 0 || x > bitmap.getWidth() || y > bitmap.getHeight()) {
            return false;
        }
        int pixel = bitmap.getPixel(x, y);
        if (pixel == Color.TRANSPARENT) {
            return false;
        } else {
            return true;
        }
    }

    class FloatPoint {
        FloatPoint(float x, float y) {
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
