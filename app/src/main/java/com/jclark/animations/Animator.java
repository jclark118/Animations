package com.jclark.animations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Assign static animation to a view and run it
 */
public class Animator {


    /**
     * Assign a rotate + fade out animation to the given view and stay invisible when done
     * @param view the view to animate
     * @param duration duration of the animation
     */
    public static void rotateFadeOut(View view, long duration){
        // Fade out
        final ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, View.ALPHA, 1, 0);
        fadeAnim.setDuration(duration);

        // Rotate
        final ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(view, View.ROTATION, 0, 180);
        rotateAnim.setDuration(duration);

        // Create the animation set
        final AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(fadeAnim, rotateAnim);

        // Play then reverse
        playThenReverse(set1, view);
    }



    /**
     * Assign a rotate + fade in animation to the given view and stay visible when done
     * @param view the view to animate
     * @param duration duration of the animation
     */
    public static void rotateFadeIn(View view, long duration){
        // Fade in
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        fadeAnim.setDuration(duration);

        // Rotate
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(view, View.ROTATION, 180, 0);
        rotateAnim.setDuration(duration);

        // Play
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(fadeAnim, rotateAnim);
        set1.start();
    }


    /**
     * Assign a "scale to 120% and back bounce + fade out and in" animation to the given view
     * @param view the view to animate
     * @param duration duration of the animation
     */
    public static void setBounceAnimatiom(View view, long duration){
        ScaleAnimation grow = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        grow.setDuration(duration);
        ScaleAnimation shrink = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(duration);
        shrink.setStartOffset(duration);

        // Fade out then repeat to fade back in
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.3f);
        fadeOut.setInterpolator(new DecelerateInterpolator()); //and this
        fadeOut.setDuration(100);
        fadeOut.setRepeatMode(Animation.REVERSE);
        fadeOut.setRepeatCount(1);

        // Play
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(grow);
        set.addAnimation(shrink);
        set.addAnimation(fadeOut);
        view.startAnimation(set);
    }


    /**
     * Assign a bounce animation from right to left
     */
    public static void bounceRightToLeft(View view, long duration){
        ObjectAnimator slide = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 180, -50);
        slide.setDuration(duration);

        ObjectAnimator slide2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, -50, 80);
        slide2.setDuration(duration);

        ObjectAnimator slide3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 80, 0);
        slide3.setDuration(duration);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(slide, slide2, slide3);
        set.start();
    }



    /**
     * Assign a fade and slide in animation to the given view
     * @param view the view to animate
     * @param duration duration of the animation
     */
    public static void fadeInFromRight(View view, long duration){
        ObjectAnimator slide = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 180, 0);
        slide.setDuration(duration);

        ObjectAnimator fade = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        fade.setDuration(duration);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(slide, fade);
        set.start();
    }



    /**
     * Assign a "fade in" animation to the given view
     * @param view the view to animate
     * @param duration duration of the animation
     */
    public static void setFadeInAnimatiom(View view, long duration){
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(duration);
        view.startAnimation(anim);
    }



    /**
     * Assign a "scale from big into small" animation to the given view
     * @param view the view to animate
     * @param duration duration of the animation
     */
    public static void scaleFromHuge(View view, long duration){
        ScaleAnimation anim = new ScaleAnimation(10.0f, 1.0f, 10.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(duration);
        view.startAnimation(anim);
    }



    /**
     * Assign a "scale in from nothing" animation to the given view
     * @param view the view to animate
     * @param duration duration of the animation
     */
    public static void scaleFromSmall(View view, long duration){
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.2f, 0.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(duration);

        ScaleAnimation anim2 = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim2.setDuration(duration);
        anim2.setStartOffset(duration);

        ScaleAnimation anim3 = new ScaleAnimation(0.0f, 1.2f, 0.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim3.setDuration(duration);
        anim3.setStartOffset(duration*2);


        AnimationSet set = new AnimationSet(false);
        set.addAnimation(anim);
        set.addAnimation(anim2);
//        set.addAnimation(anim3);
        view.startAnimation(set);
    }


    /**
     * Collapse like a drawer
     * @param view view to animate
     */
    public static void collapse(final View view) {
        final int initialHeight = view.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration((int) (initialHeight / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(a);
    }


    /**
     * Play the given animation, then reverse it back to original state
     */
    private static void playThenReverse(AnimatorSet set, View view){
        final AnimatorSet reverse = set.clone();
        set.addListener(new android.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(android.animation.Animator animator) {
            }
            @Override
            public void onAnimationEnd(android.animation.Animator animator) {
                reverse.setDuration(1);
                reverse.reverse();

            }
            @Override
            public void onAnimationCancel(android.animation.Animator animator) {
            }
            @Override
            public void onAnimationRepeat(android.animation.Animator animator) {
            }
        });
        set.start();
    }

    /**
     * Show the Lottie fingerprint animation
     * @param lottieImage the lottie resource to show and animate
     */
    public static void showLottieFingerprint(View lottieImage){
        LottieAnimationView fingerprint = (LottieAnimationView)lottieImage;
        fingerprint.playAnimation();
    }

}
