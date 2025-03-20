package com.githu.comm.components.DownLoadingProgress;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.githu.frame.R;


/**
 * 自定义下载进度条
 *
 * @author Mr.xie
 * @Date 2025/3/20
 */
public class DownloadProgress extends ProgressBar {


    public interface DownloadProcessListener {
        void onProgress(int currentProgress);

        void onFinish();
    }

    private String text = "下载中";

    private float fontSize = 10;
    private int contentColor = Color.BLACK;

    private int fillColor = Color.parseColor("#479AD9");

    private DownloadProcessListener listener = null;

    public DownloadProgress(Context context) {
        this(context, null);
    }

    public DownloadProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownloadProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DownloadProgress);

        text = typedArray.getString(R.styleable.DownloadProgress_text);
        contentColor = typedArray.getColor(R.styleable.DownloadProgress_contextColor, Color.BLACK);
        fontSize = typedArray.getDimension(R.styleable.DownloadProgress_contextSize, 10);
        Drawable drawable =  getResources().getDrawable(R.drawable.drawable_download_loading);
        setProgressDrawable(drawable);

        typedArray.recycle();
    }


    public void setListener(DownloadProcessListener downloadProcessListener) {
        if (this.listener == null) {
            this.listener = downloadProcessListener;
        }

    }


    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        if (this.listener != null) {
            if (progress == this.getMax()) {

                this.listener.onFinish();
            } else {
                this.listener.onProgress(progress);
            }
        }


    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int componentHeight = getMeasuredHeight();
        int componentWidth = getMeasuredWidth();
        paint.setColor(this.contentColor);
        paint.setTextSize(this.fontSize);
        paint.setAntiAlias(true);

        // 测量
        float x = (componentWidth / 2) - computeTextWidth(this.text) / 2;
        float y = (componentHeight / 2) + this.fontSize / 2;
        canvas.drawText(this.text, x, y, paint);
    }


    public float computeTextWidth(String text) {
        return text.length() * this.fontSize;
    }
}
