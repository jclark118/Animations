package com.jclark.animations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
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
     * Boolean to track light or dark mode
     */
    private boolean darkMode = false;

    /**
     * Dark / light mode toggle button
     */
    Button darkButton;



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
        darkButton = findViewById(R.id.button_dark);

        // Set initial text for dark mode toggle button
        setDarkButtonText();

        // Create button listeners
        createButtonListeners();
    }



    /**
     * Create listeners for animate and dark mode toggle buttons
     */
    private void createButtonListeners(){
        // Animation listener
        Button animateButton = findViewById(R.id.button_animate);
        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate();
            }
        });

        // Dark mode listener
        darkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleDarkMode();
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
        } else if (button.getText().toString().equalsIgnoreCase(getString(R.string.scale_from_huge))) {
            // Scale from huge
            Animator.scaleFromHuge(androidImage, miliseconds);
        } else if (button.getText().toString().equalsIgnoreCase(getString(R.string.scale_from_small))) {
            // Scale from small
            Animator.scaleFromSmall(androidImage, miliseconds);
        }
    }



    /**
     * Toggle dark mode
     */
    private void toggleDarkMode(){

        if(isDarkMode()){
            // Set to light
            darkButton.setText(R.string.go_dark);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            // Set to light
            darkButton.setText(R.string.go_light);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }



    /**
     * Sets the text of the dark mode toggle button according to the current state
     */
    private void setDarkButtonText(){
        if(isDarkMode()){
            darkButton.setText(R.string.go_light);
        } else{
            darkButton.setText(R.string.go_dark);
        }
    }



    /**
     * Find out if we're currently set to dark mode
     * @return true if the app is set to dark mode
     */
    private boolean isDarkMode(){
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                return false;// Night mode is not active, we're in day time
            case Configuration.UI_MODE_NIGHT_YES:
                return true;// Night mode is active, we're at night!
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                return false;// We don't know what mode we're in, assume notnight
            default :
                return false;
        }
    }
}
