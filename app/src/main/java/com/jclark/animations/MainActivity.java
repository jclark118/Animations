package com.jclark.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Show a button, duration field, and a list of different animations.  Allow a user to select an animation type and
 * run that animation on a text label
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Radio button group
     */
    private RadioGroup radioGroup;

    /**
     * Duration setting
     */
    private EditText duration;

    /**
     * Image to animate
     */
    private ImageView androidImage;



    /**
     * Initialize and set up click listener for animate button
     * @param savedInstanceState instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        duration = findViewById(R.id.text_duration);
        radioGroup = findViewById(R.id.group_radios);
        androidImage = findViewById(R.id.android_image);
        Button animateButton = findViewById(R.id.button_animate);
        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate();
            }
        });
    }



    /**
     * Animate the main label based on which radio button is selected
     */
    private void animate(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton button = findViewById(selectedId);
        int miliseconds = Integer.parseInt(duration.getText().toString());
        if(button.getText().toString().equalsIgnoreCase(getString(R.string.radio_bounce_anim))){
            // Bounce
            Animator.setBounceAnimatiom(androidImage, miliseconds);
        } else if (button.getText().toString().equalsIgnoreCase(getString(R.string.radio_rotate_fade_out))) {
            // Rotate and fade out
            Animator.rotateFadeOut(androidImage, miliseconds);
        } else if (button.getText().toString().equalsIgnoreCase(getString(R.string.radio_rotate_fade_in))) {
            // Rotate and fade in
            Animator.rotateFadeIn(androidImage, miliseconds);
        } else if (button.getText().toString().equalsIgnoreCase(getString(R.string.radio_bounce_right_left))) {
            // Bounce from right to left
            Animator.bounceRightToLeft(androidImage, miliseconds);
        } else if (button.getText().toString().equalsIgnoreCase(getString(R.string.radio_fade_in_from_right))) {
            // Fade in from right
            Animator.fadeInFromRight(androidImage, miliseconds);
        } else if (button.getText().toString().equalsIgnoreCase(getString(R.string.radio_fade_in))) {
            // Fade in from right
            Animator.setFadeInAnimatiom(androidImage, miliseconds);
        }
    }
}
