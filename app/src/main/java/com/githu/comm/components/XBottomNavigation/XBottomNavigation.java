package com.githu.comm.components.XBottomNavigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.githu.frame.R;

import java.util.ArrayList;
import java.util.List;

import kotlin.Triple;

/**
 * Android 自研框架 底部导航
 *
 * @author <a href="https://github.com/karai-oss">Mr.xie</a>
 * @Date 2025/2/22
 */
public class XBottomNavigation extends ViewGroup {


    /**
     * 默认选中颜色
     */
    private int tab_selected_color = Color.BLACK;


    /**
     * 默认没有选中颜色
     */
    private int tab_unselect_color = Color.GRAY;


    /**
     * 阴影
     */
    private int navigation_shadow_color = Color.GRAY;


    /**
     * Navigation 监听器
     */
    private INavigationListener navigationListener;


    /**
     * 最多只允许设置四个底部导航
     */
    private final int MAX_MENUS = 4;


    /**
     * 导航菜单数据
     */
    List<Triple<String, Integer, Integer>> menus = new ArrayList<>();


    /**
     * 默认固定的高度
     */
    private int fixedHeight = 200;


    /**
     * 记录前一个选中的tab的位置信息
     */
    private int preIndex = 0;

    public XBottomNavigation(Context context) {
        this(context, null);
    }

    public XBottomNavigation(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XBottomNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XBottomNavigation);
        tab_selected_color = typedArray.getColor(R.styleable.XBottomNavigation_tab_item_selected_color, Color.BLACK);
        tab_unselect_color = typedArray.getColor(R.styleable.XBottomNavigation_tab_item_unselect_color, Color.GRAY);
        navigation_shadow_color = typedArray.getColor(R.styleable.XBottomNavigation_navigation_shadow_color, Color.GRAY);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取父布局指定的宽度模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 宽度完全由父布局决定
        int width = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize; // 父布局指定了的确切宽度
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = widthSize; // 父布局指定了最大宽度，这里直接使用最大宽度
        } else {
            width = widthSize; // 父布局没有限制，使用父布局的默认宽度
        }

        if (getLayoutParams().height == LayoutParams.MATCH_PARENT
                || getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
            fixedHeight = 200;
        } else {
            fixedHeight = MeasureSpec.getSize(heightMeasureSpec);
        }

        // 高度由自己指定，不考虑父布局的模式和大小
        int height = fixedHeight;

        // 设置测量结果
        setMeasuredDimension(width, height);


        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int itemWidth = measuredWidth / 4;

            int measureSpec_height = MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.AT_MOST);
            int measureSpec_itemWidth = MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.AT_MOST);
            measureChild(child, measureSpec_itemWidth, measureSpec_height);
        }
    }


    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        super.dispatchDraw(canvas);
        setShadow(canvas);
        super.dispatchDraw(canvas); // 绘制子View
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount == 0) {
            throw new IllegalArgumentException("请添加子布局");
        }
        if (childCount > MAX_MENUS){
            throw new IllegalArgumentException("most not over 4 tab item (最多不能超过四个的导航菜单)");
        }
        int parentWidth = r - l; // 父容器的宽度
        int childWidth = parentWidth / childCount; // 每个子 View 的宽度
        int childHeight = b - t; // 父容器的高度
        int currentX = 0; // 当前子 View 的起始 X 坐标
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                // 设置子 View 的位置
                child.layout(currentX, 0, currentX + childWidth, childHeight);
                currentX += childWidth; // 更新下一个子 View 的起始 X 坐标
            }
        }

        addNavigationClickListener();
    }


    /**
     * 添加点击监听
     */
    private void addNavigationClickListener() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int pos = i;
            ViewGroup child = (ViewGroup) getChildAt(i);
            child.setOnClickListener(v -> {
                // 切换状态
                resetStyleState();
                settingsNewsStyleStatus(pos);
                requestLayout();
                invalidate();
                preIndex = pos;
                // 通知
                this.navigationListener.selectTab(pos, child);
            });
        }
    }


    /**
     * 重新设置选中状态样式
     *
     * @param pos 选中的tab位置pos
     */
    private void settingsNewsStyleStatus(int pos) {
        ViewGroup pos_child = (ViewGroup) getChildAt(pos);
        ImageView pos_iv = (ImageView) pos_child.getChildAt(0);
        TextView pos_tv = (TextView) pos_child.getChildAt(1);
        pos_iv.setBackgroundResource(this.menus.get(pos).getSecond());
        pos_tv.setTextColor(tab_selected_color);
    }

    /**
     * 回溯样式状态
     */
    private void resetStyleState() {
        ViewGroup firstChild = (ViewGroup) getChildAt(preIndex);
        ImageView first_iv = (ImageView) firstChild.getChildAt(0);
        TextView first_tv = (TextView) firstChild.getChildAt(1);
        first_iv.setBackgroundResource(this.menus.get(preIndex).getThird());
        first_tv.setTextColor(tab_unselect_color);
    }

    /**
     * 设置背景阴影
     */
    public void setShadow(Canvas canvas) {
        Paint shadowPaint = new Paint();
        shadowPaint.setColor(Color.WHITE); // 阴影背景颜色
        shadowPaint.setAntiAlias(true);
        shadowPaint.setShadowLayer(30f, 0f, 5f, navigation_shadow_color);

        float left = 0;
        float top = 10f;
        float right = getWidth();
        float bottom = getHeight();

        RectF rectF = new RectF(left, top, right, bottom);
        canvas.drawRoundRect(rectF, 10f, 10f, shadowPaint); // 绘制圆角矩形阴影
    }


    /**
     * 设置底部导航的子布局
     *
     * @param id    输入子布局的布局ID 需要是 <code>@LayoutRes</code>
     * @param menus 底部导航栏的信息  使用 Triple 定义   需要包括
     *              <desc>
     *              1. 第一个参数是 :  title 标题
     *              2.第二个参数是 : selected_icon : 选中的Icon
     *              2.第三个参数是 :unselected_icon : 未选中的Icon
     *              </desc>
     */
    public void setInnerLayoutView(@LayoutRes int id, List<Triple<String, Integer, Integer>> menus) {
        // 获取所有标题
        if (menus == null || menus.size() > MAX_MENUS || menus.size() == 0) {
            throw new IllegalArgumentException("底部导航菜单列表不能尾款 或者 不能超过4个导航菜单");
        }
        this.menus = menus;
        // 根据标题获取 图标
        for (int i = 0; i < menus.size(); i++) {
            Triple<String, Integer, Integer> menu = menus.get(i);
            LinearLayout view = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(id, null, false);

            ImageView image = (ImageView) view.getChildAt(0);
            TextView label = (TextView) view.getChildAt(1);

            String title = menu.getFirst();
            Integer selected_icon = menu.getSecond();
            Integer unselected_icon = menu.getThird();
            if (i == 0 && selected_icon != null) {
                image.setBackgroundResource(selected_icon);
                label.setTextColor(tab_selected_color);
            } else if (i != 0 && unselected_icon != null) {
                image.setBackgroundResource(unselected_icon);
                label.setTextColor(tab_unselect_color);
            }
            label.setText(title);
            addView(view);
        }

    }


    public void setNavigationListener(INavigationListener navigationListener) {
        this.navigationListener = navigationListener;
    }
}
