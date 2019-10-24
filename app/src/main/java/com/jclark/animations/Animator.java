package com.jclark.animations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

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

        // Play
        playThenReverse(set1, view);
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

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(grow);
        set.addAnimation(shrink);
        set.addAnimation(fadeOut);
        view.startAnimation(set);
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

}
