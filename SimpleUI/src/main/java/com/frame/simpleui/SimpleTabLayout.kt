package com.frame.simpleui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable.Orientation
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.frame.simpleui.R.*
import com.google.android.material.tabs.TabLayout
import java.util.jar.Attributes


/**
 * 自定义的一个TabLayout
 * @author <a href="https://github.com/karai-oss">Mr.xie</a>
 * @Date 2025/3/23
 */

class SimpleTabLayout : LinearLayout {

    var tabList : List<String> = listOf()
    constructor(context: Context) : this(context, null) {

    }
    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0) {

    }
    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    ) {

        orientation =  HORIZONTAL
        val typedArray =
            context.obtainStyledAttributes(attributes, styleable.SimpleTabLayout)

        typedArray.recycle()

    }



    fun setData(tabData : List<String>){
        this.tabList = tabData
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.tabList.forEachIndexed{ index: Int, text: String ->
            
        }
    }


}