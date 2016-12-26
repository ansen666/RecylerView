package com.ansen.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

import com.ansen.recyclerview.R;

/**
 * Created by  ansen
 * Create Time 2016-08-23
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {
    /*
    * RecyclerView的布局方向，默认先赋值 为纵向布局
    * RecyclerView 布局可横向，也可纵向
    * 横向和纵向对应的分割线画法不一样
    * */
    private int mOrientation = LinearLayoutManager.VERTICAL;

    private int mItemSize = 1;//item之间分割线的size，默认为1

    private Paint mPaint;//绘制item分割线的画笔，和设置其属性

    public DividerGridItemDecoration(Context context) {
        this(context,LinearLayoutManager.VERTICAL,R.color.colorAccent);
    }

    public DividerGridItemDecoration(Context context, int orientation) {
        this(context,orientation, R.color.colorAccent);
    }

    public DividerGridItemDecoration(Context context, int orientation, int dividerColor){
        this(context,orientation,dividerColor,1);
    }

    /**
     * @param context
     * @param orientation 绘制方向
     * @param dividerColor 分割线颜色 颜色资源id
     * @param mItemSize 分割线宽度 传入dp值就行
     */
    public DividerGridItemDecoration(Context context, int orientation, int dividerColor, int mItemSize){
        this.mOrientation = orientation;
        if(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL){
            throw new IllegalArgumentException("请传入正确的参数") ;
        }
        //把dp值换算成px
        this.mItemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,mItemSize,context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setColor(context.getResources().getColor(dividerColor));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontal(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + mItemSize;
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mItemSize;
            canvas.drawRect(left,top,right,bottom,mPaint);
        }
    }

    public void drawVertical(Canvas canvas, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mItemSize;
            canvas.drawRect(left,top,right,bottom,mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();

        if (isLastRow(parent, itemPosition, spanCount, childCount)){//如果是最后一行，不需要绘制底部
            outRect.set(0, 0, mItemSize, 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount)){// 如果是最后一列，不需要绘制右边
            outRect.set(0, 0, 0, mItemSize);
        } else {
            outRect.set(0, 0, mItemSize,mItemSize);
        }
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0){// 如果是最后一列，则不需要绘制右边
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0){// 如果是最后一列，则不需要绘制右边
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRow(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)//最后一行
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL){//纵向
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)//最后一行
                    return true;
            } else{ //横向
                if ((pos + 1) % spanCount == 0) {//是最后一行
                    return true;
                }
            }
        }
        return false;
    }
}
