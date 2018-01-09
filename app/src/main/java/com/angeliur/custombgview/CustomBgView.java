package com.angeliur.custombgview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/1/9 0009.
 *
 * 一个自定义view用来代替一些简单的规则背景图，节省内存
 */

public class CustomBgView extends View {

    private int bg_color;
    private float right_ratio;
    private Paint mPaint;

    public CustomBgView(Context context) {
        this(context,null);
    }

    public CustomBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们所定义的自定义样式属性 
         */
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomBgView, defStyleAttr, 0);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr){
                //获取自定义的背景色，如果不设置默认是蓝色
                case R.styleable.CustomBgView_bg_color:
                    bg_color = typedArray.getColor(attr, Color.BLUE);
                    break;
                //获取自定义的右侧所占比例，如果不设置默认是2.0
                case R.styleable.CustomBgView_right_ratio:
                    right_ratio = typedArray.getFloat(attr,2.0f);
                    break;
            }
        }
        typedArray.recycle();

        //初始化画笔
        mPaint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //自定义view的宽高测量模式都为EXACTLY,所以onMeasure使用默认的就可以
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取view的测量宽高
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //将获取的自定义的背景色设置给画笔
        mPaint.setColor(bg_color);
        //设置style为填充
        mPaint.setStyle(Paint.Style.FILL);
        //设置抗锯齿，否则图形边缘会有"毛刺"
        mPaint.setAntiAlias(true);

        Path path = new Path();
        //通过path连接几条边来绘制view的形状
        path.lineTo(0,height);
        path.lineTo(width, height/right_ratio);
        path.lineTo(width, 0);
        //path.close();     paint的style为FILL填充，所以可以不用调用close方法
        canvas.drawPath(path,mPaint);
    }

    protected void setBgColor(int color){
        bg_color = color;
        invalidate();
    }

    protected void setRightRatio(float ratio){
        right_ratio = ratio;
        invalidate();
    }
}
