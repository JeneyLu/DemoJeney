package com.jeney.demojeney;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/10/14
 */
public class CanvasDemoActivity extends AppCompatActivity {
    @Bind(R.id.iv)
    ImageView imageView;

    @Bind(R.id.draw_circle)
    Button drawCircle;

    Paint paint;

    Bitmap bitmap;

    Canvas canvas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_demo);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private void drawCircle() {
        int picw = 200;
        int pich = 200;
        bitmap = Bitmap.createBitmap(picw, pich, Bitmap.Config.ARGB_8888).copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(bitmap);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(picw / 2, pich / 2, 90, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.p1), 0, 0, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setXfermode(null);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(picw / 2, pich / 2, 93, paint);
        paint.setTextSize(16);
        paint.setStrokeWidth(1);
        canvas.drawText("水印", picw / 2, pich / 2, paint);
        imageView.setImageBitmap(bitmap);
    }

    @OnClick(R.id.draw_circle)
    void drawCircleBtnOnclick() {
        drawCircle();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
