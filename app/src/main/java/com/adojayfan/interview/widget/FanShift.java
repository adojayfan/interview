package com.adojayfan.interview.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FanShift extends LinearLayout {

    private int mShiftCounts = 9;
    private int mCurrentShift = 0;
    private int mShiftColor = Color.BLACK;
    private int mBgStartColor = Color.BLACK, mBgEndColor = Color.WHITE;

    private FrameLayout flFanontainer;
    private LinearLayout linearLayout;
    private FrameLayout flContentView;
    private ImageView mIvFan;
    private RotateAnimation animation;

    private ShiftChangeListener shiftChangeListener;

    public FanShift(Context context) {
        this(context, null);
    }

    public FanShift(Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FanShift(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FanShift(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        flFanontainer = new FrameLayout(getContext());
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        flContentView = new FrameLayout(getContext());
        mIvFan = new ImageView(getContext());

        //background
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setColors(new int[]{mBgStartColor, mBgEndColor});
        drawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        drawable.setCornerRadius(70.0f);
        drawable.setStroke(1, Color.GRAY);
        linearLayout.setBackground(drawable);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        flFanontainer.addView(linearLayout,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(flFanontainer,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(flContentView,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        for (int i = 1; i <= mShiftCounts; i++) {
            TextView tvShift = new TextView(getContext());
            tvShift.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
            tvShift.setTextColor(mShiftColor);
            tvShift.setText(String.valueOf(i));
            tvShift.setGravity(Gravity.CENTER);
            linearLayout.addView(tvShift, params);
        }
    }

    /**
     * 添加档位说明页面
     *
     * @param view
     */
    public void addContentView(View view) {
        if (flContentView.getChildCount() > 0) {
            flContentView.removeAllViews();
        }
        flContentView.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setFanImage(@DrawableRes int resId) {
        mIvFan.setImageResource(resId);
    }

    /**
     * 设置档位
     *
     * @param currentShift
     */
    public void setCurrentShift(final int currentShift) {
        mCurrentShift = currentShift;

        if (mIvFan.getParent() != null) {
            int width = getMeasuredWidth() / mShiftCounts;
//            int height = linearLayout.getHeight();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mIvFan.getLayoutParams();
            params.leftMargin = width * (currentShift - 1);
            mIvFan.setLayoutParams(params);
        }

        if (shiftChangeListener != null) {
            shiftChangeListener.shiftChanged(currentShift);
        }

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mIvFan.getParent() != null) {
                    return;
                }
                int width = getMeasuredWidth() / mShiftCounts;
                int height = linearLayout.getHeight();
                LayoutParams params = new LayoutParams(width, height);
                params.leftMargin = width * (currentShift - 1);
                flFanontainer.addView(mIvFan, params);
            }
        });

    }

    /**
     * 是否旋转
     *
     * @param isRotate
     */
    public void rotateFan(boolean isRotate) {
        if (!isRotate) {
            return;
        }
        animation = new RotateAnimation(0f, 36000f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(100000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        mIvFan.startAnimation(animation);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animation == null) {
            return;
        }
        if (animation.hasEnded()) {
            return;
        }
        animation.cancel();
        animation = null;
    }

    public interface ShiftChangeListener {

        void shiftChanged(int currentShift);
    }

    public void setShiftChangeListener(ShiftChangeListener shiftChangeListener) {
        this.shiftChangeListener = shiftChangeListener;
    }
}
