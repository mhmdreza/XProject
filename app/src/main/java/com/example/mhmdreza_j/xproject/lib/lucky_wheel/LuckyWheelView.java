package com.example.mhmdreza_j.xproject.lib.lucky_wheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.lib.lucky_wheel.model.LuckyItem;

import java.util.List;
import java.util.Random;


/**
 * Created by kiennguyen on 11/5/16.
 */

public class LuckyWheelView extends RelativeLayout implements PieView.PieRotateListener {
    private int mBackgroundColor;
    private int mTextColor;
    private int mTopTextSize;
    private int mSecondaryTextSize;
    private int mBorderColor;
    private int mTopTextPadding;
    private int mEdgeWidth;
    private Drawable mCenterImage;
    private Drawable mCursorImage;

    private PieView pieView;

    private LuckyRoundItemSelectedListener mLuckyRoundItemSelectedListener;

    @Override
    public void rotateDone(int index) {
        if (mLuckyRoundItemSelectedListener != null) {
            mLuckyRoundItemSelectedListener.LuckyRoundItemSelected(index);
        }
    }

    public interface LuckyRoundItemSelectedListener {
        void LuckyRoundItemSelected(int index);
    }

    public void setLuckyRoundItemSelectedListener(LuckyRoundItemSelectedListener listener) {
        this.mLuckyRoundItemSelectedListener = listener;
    }

    public LuckyWheelView(Context context) {
        super(context);
        init(context, null);
    }

    public LuckyWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * @param ctx
     * @param attrs
     */
    private void init(Context ctx, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.LuckyWheelView);
            mBackgroundColor = typedArray.getColor(R.styleable.LuckyWheelView_lkwBackgroundColor, 0xffcc0000);
            mTopTextSize = typedArray.getDimensionPixelSize(R.styleable.LuckyWheelView_lkwTopTextSize, (int) LuckyWheelUtils.convertDpToPixel(10f, getContext()));
            mSecondaryTextSize = typedArray.getDimensionPixelSize(R.styleable.LuckyWheelView_lkwSecondaryTextSize, (int) LuckyWheelUtils.convertDpToPixel(20f, getContext()));
            mTextColor = typedArray.getColor(R.styleable.LuckyWheelView_lkwTopTextColor, 0);
            mTopTextPadding = typedArray.getDimensionPixelSize(R.styleable.LuckyWheelView_lkwTopTextPadding, (int) LuckyWheelUtils.convertDpToPixel(10f, getContext())) + (int) LuckyWheelUtils.convertDpToPixel(10f, getContext());
            mCursorImage = typedArray.getDrawable(R.styleable.LuckyWheelView_lkwCursor);
            mCenterImage = typedArray.getDrawable(R.styleable.LuckyWheelView_lkwCenterImage);
            mEdgeWidth = typedArray.getInt(R.styleable.LuckyWheelView_lkwEdgeWidth, 10);
            mBorderColor = typedArray.getColor(R.styleable.LuckyWheelView_lkwEdgeColor, 0);
            typedArray.recycle();
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.lucky_wheel_layout, this, false);

        pieView = frameLayout.findViewById(R.id.pieView);

        pieView.setPieRotateListener(this);
        pieView.setPieBackgroundColor(mBackgroundColor);
        pieView.setTopTextPadding(mTopTextPadding);
        pieView.setTopTextSize(mTopTextSize);
        pieView.setSecondaryTextSizeSize(mSecondaryTextSize);
        pieView.setPieCenterImage(mCenterImage);
        pieView.setBorderColor(mBorderColor);
        pieView.setBorderWidth(mEdgeWidth);

        if (mTextColor != 0)
            pieView.setPieTextColor(mTextColor);

        addView(frameLayout);
    }


    public boolean isTouchEnabled() {
        return pieView.isTouchEnabled();
    }

    public void setTouchEnabled(boolean touchEnabled) {
        pieView.setTouchEnabled(touchEnabled);
    }

    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //This is to control that the touch events triggered are only going to the PieView
        for (int i = 0; i < getChildCount(); i++) {
            if (isPieView(getChildAt(i))) {
                return super.dispatchTouchEvent(ev);
            }
        }
        return false;
    }

    private boolean isPieView(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < getChildCount(); i++) {
                if (isPieView(((ViewGroup) view).getChildAt(i))) {
                    return true;
                }
            }
        }
        return view instanceof PieView;
    }

    /**
     * @param data
     */
    public void setData(List<LuckyItem> data) {
        pieView.setData(data);
    }

    /**
     * @param numberOfRound
     */
    public void setRound(int numberOfRound) {
        pieView.setRound(numberOfRound);
    }

    public void startLuckyWheelWithTargetIndex(int index) {
        pieView.rotateTo(index);
    }
    
    public void startLuckyWheelWithRandomTarget() {
        Random r = new Random();
        pieView.rotateTo(r.nextInt(pieView.getLuckyItemListSize() - 1));
    }
}
