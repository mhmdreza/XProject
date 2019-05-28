//package com.example.mhmdreza_j.xproject;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.support.v7.widget.AppCompatImageView;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.Interpolator;
//import android.view.animation.RotateAnimation;
//
//import java.util.Random;
//
//public class StaggeredProgress extends AppCompatImageView {
//
//    private Animation staggered;
//
//    public StaggeredProgress(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        setAnimation(attrs);
//    }
//
//    public StaggeredProgress(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        setAnimation(attrs);
//    }
//
//    public StaggeredProgress(Context context) {
//        super(context);
//    }
//
//    private void setAnimation(AttributeSet attrs) {
//        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StaggeredProgress);
//        int frameCount = a.getInt(R.styleable.StaggeredProgress_frameCount, 12);
//        int duration = a.getInt(R.styleable.StaggeredProgress_duration, 1000);
//        a.recycle();
//
//        setAnimation(frameCount);
//    }
//
//    public void setAnimation(final int frameCount) {
//        final Random random = new Random();
//        int spinAmount = random.nextInt() % 720 + 720;
//        final RotateAnimation animRotate = new RotateAnimation(0.0f, -390.0f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.504f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.442f);
//        animRotate.setDuration(5000);
//        animRotate.setInterpolator(new Interpolator() {
//
//            @Override
//            public float getInterpolation(float input) {
//                return (float)Math.floor(input*frameCount)/frameCount;
//            }
//        });
//        staggered = animRotate;
//
//        //startAnimation(a);
//    }
//
//    @Override
//    public void setVisibility(int visibility) {
//        super.setVisibility(visibility);
//        if( visibility == View.VISIBLE )
//            startAnimation(staggered);
//        else
//            clearAnimation();
//
//    }
//
//
//}