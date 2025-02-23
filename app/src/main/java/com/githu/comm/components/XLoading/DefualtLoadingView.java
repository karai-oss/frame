package com.githu.comm.components.XLoading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.githu.frame.R;

/**
 * @author <a href="https://github.com/karai-oss">Mr.xie</a>
 * @Date 2025/2/23
 */
public class DefualtLoadingView extends View {


    private int loadingBackgroundColor = Color.BLACK;

    private int loadingTextColor = Color.WHITE;

    private int loadingPointerColor = Color.WHITE;
    private float angle = 0; // 当前旋转角度
    private final int speed = 8; // 旋转速度（单位：角度/帧）

    private String loadingMessage = "加载中...";

    public DefualtLoadingView(Context context) {
        super(context);
        super.setBackgroundColor(Color.parseColor("#8c898d"));
        setLayoutParams(new LinearLayout.LayoutParams(250, 260));

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(loadingBackgroundColor);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rectF, 20, 20, paint);
        // 绘制 加载动画
        loadingAnimal(canvas);
        paint.setTextSize(30);
        paint.setColor(loadingTextColor);
        canvas.drawText(loadingMessage, (getWidth() / 2 - 60), getHeight() - 30, paint);
    }


    /**
     * 设置加载动画
     */
    private void loadingAnimal(Canvas canvas) {
        Paint linePaint = new Paint();
        linePaint.setTextSize(20);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setStrokeWidth(10);
        linePaint.setColor(loadingPointerColor);
        int width = getWidth();
        int height = getHeight();
        canvas.drawCircle(width / 2, height / 2 - 20, 15, linePaint);
        int radius = Math.min(width, height) / 3; // 圆的半径为视图大小的1/3
        // 绘制旋转的线条
        canvas.drawLine(width / 2, height / 2 - 20,
                width / 2 + radius * (float) Math.cos(Math.toRadians(angle)),
                (height / 2 - 20) + radius * (float) Math.sin(Math.toRadians(angle)),
                linePaint);
        // 更新角度并重新绘制
        angle += speed;
        if (angle >= 360) {
            angle = 0;
        }
        invalidate(); // 触发下次绘制
    }

    public void setBackGroundColor(int color) {
        setBackgroundColor(color);
    }


    public DefualtLoadingView setLoadingBackgroundColor(int color){
        this.loadingBackgroundColor = color;
        return this;
    }


    public DefualtLoadingView setLoadingTextColor(int color){
        this.loadingTextColor = color;
        return this;
    }

    public DefualtLoadingView setLoadingPointerColor(int color){
        this.loadingPointerColor = color;
        return this;
    }

    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }
}
