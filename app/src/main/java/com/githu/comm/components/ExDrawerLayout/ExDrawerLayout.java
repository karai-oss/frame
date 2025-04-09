package com.githu.comm.components.ExDrawerLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.githu.frame.R;


/**
 * 抽屉布局  ExDrawerLayout
 *
 * @author Mr.xie
 */
public class ExDrawerLayout extends LinearLayout {


    private static final String TAG = "exDrawerLayout";


    /**
     * 子布局缓存
     */
    private ViewGroup childCache = null;

    /**
     * 最小滑动距离
     */
    private static int MIN_FLING_DISTANCE = 20;


    /**
     * 滑动距离 比例
     */
    private int FLING_DISTANCE_SCALE = 20;

    private ViewGroup parent;


    /**
     * 距离 顶部的间隙
     */
    private int topOffsetGap = 20;

    /**
     * 所允许的最小高度
     */
    private int minHeight = 500;


    /**
     * 缓存视图的高度
     */
    private int cacheViewPreHeight;


    private int bgColor;

    /**
     * 滑动的状态
     */
    private enum FlingState {
        UP,
        DOWN
    }


    public ExDrawerLayout(Context context) {
        this(context, null);
    }

    public ExDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExDrawerLayout);
        topOffsetGap = typedArray.getInt(R.styleable.ExDrawerLayout_topOffsetGap, 20);
        minHeight = typedArray.getInt(R.styleable.ExDrawerLayout__minHeight, 500);
        MIN_FLING_DISTANCE = typedArray.getInt(R.styleable.ExDrawerLayout_min_fling_distance, 20);
        FLING_DISTANCE_SCALE = typedArray.getInt(R.styleable.ExDrawerLayout_flingScale, 20);
        bgColor = typedArray.getColor(R.styleable.ExDrawerLayout_bgColor, Color.WHITE);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int windowHeight = displayMetrics.heightPixels;
        int windowWidth = displayMetrics.widthPixels;
        setMeasuredDimension(windowWidth, windowHeight);

        initLayout();
    }

    /**
     * 初始化布局
     */
    private void initLayout() {
        mask();
        checkChild();
        setOrientation(VERTICAL);
        setGravity(Gravity.BOTTOM);
        childCache = (ViewGroup) getChildAt(0);
        childCache.setBackgroundColor(bgColor);
    }

    private void checkChild() {
        if (getChildCount() > 1) {
            throw new IllegalArgumentException("child count most 1");
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 创建一个背景层
     */
    private void mask() {
        setBackgroundColor(Color.parseColor("#000000"));
        getBackground().mutate().setAlpha(40);

    }

    float startTouchY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouchY = event.getY(); // 记录初始位置
                cacheViewPreHeight = childCache.getHeight();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                float currentY = event.getY();
                float deltaY = startTouchY - currentY; // 计算垂直偏移量
                if (Math.abs(deltaY) > MIN_FLING_DISTANCE) {
                    if (deltaY > 0) {
                        handleFling(FlingState.UP, deltaY);
                    } else {
                        handleFling(FlingState.DOWN, Math.abs(deltaY));
                    }
                }
                break;
        }
        return true;
    }


    /**
     * 滑动处理方法
     *
     * @param state  滑动的状态  <code>FlingState<code/>
     * @param deltaY 滑动的距离
     */
    private void handleFling(FlingState state, float deltaY) {
        ViewGroup.LayoutParams layoutParams = childCache.getLayoutParams();
        int preChildHeight = layoutParams.height;
        int parentHeight = getMeasuredHeight();
        switch (state) {
            case UP:
                layoutParams.height += deltaY / FLING_DISTANCE_SCALE;
                int heightAtMost = parentHeight - topOffsetGap;
                if (layoutParams.height >= parentHeight / 1.2) {
                    layoutParams.height = heightAtMost;
                    flingAnimation(getHeight() - preChildHeight, topOffsetGap);
                }
                break;
            case DOWN:
                layoutParams.height -= deltaY / FLING_DISTANCE_SCALE;
                if (layoutParams.height <= parentHeight / 3) {
                    layoutParams.height = minHeight;
                }
                break;
        }
        childCache.setLayoutParams(layoutParams);
    }


    /**
     * 滑动动画
     *
     * @param start 开始位置
     * @param end   结束位置
     */
    public void flingAnimation(int start, float end) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                0, 0, start, end
        );
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setDuration(3000);
        childCache.setAnimation(translateAnimation);

    }
}
