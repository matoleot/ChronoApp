package edu.matoleot.android.chronometer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import edu.matoleot.android.chronometer.utils.TimerUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Constants: max time to count --> 10 hours
    private final int MAX_TIME_TO_COUNT_IN_MILLIS = 36000000;

    // Views
    private Button mButtonStart;
    private TextView mTextViewTime;
    private CountDownTimer countDownTimer;

    private TimerUtil timerUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerUtil = new TimerUtil();

        // set Button
        mButtonStart = (Button) findViewById(R.id.buttonStartCount);
        mButtonStart.setOnClickListener(this);
        // set TextView
        mTextViewTime = (TextView) findViewById(R.id.textViewTime);
        mTextViewTime.setText(timerUtil.getCronoStyleTextFromMillis(TimerUtil.CRONO_FORMAT_MILLIS, 0));

        setViewsDependingTheState(false);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonStart) {
            if (countDownTimer == null) {
                setViewsDependingTheState(true);
                startTimer();
            } else {
                Toast.makeText(this, R.string.activity_main_toast_stop_timer, Toast.LENGTH_SHORT).show();
                setViewsDependingTheState(false);
                // Cancel the countdown.
                countDownTimer.cancel();
                countDownTimer = null;
            }
        }
    }

    private void setViewsDependingTheState(boolean isCounting) {
        Drawable icon;
        if (isCounting) {
            mButtonStart.setText(R.string.activity_main_button_stop);
            // get play icon from android resource
            icon = ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause);
        } else {
            mButtonStart.setText(R.string.activity_main_button_start);
            // get pause icon from android resource
            icon = ContextCompat.getDrawable(this, android.R.drawable.ic_media_play);
        }
        // add an icon in left part of the button
        mButtonStart.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
    }

    private void startTimer() {
        // this code is just to check some debugging tools from Android Studio. Please see e)
        // paragraph of exercise 1.
        boolean valueToBeModifiedUsingWatches = false;
        if (!valueToBeModifiedUsingWatches) {
            Toast.makeText(this, R.string.activity_main_toast_start_timer, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.activity_main_toast_hacking_timer, Toast.LENGTH_LONG).show();
        }

        countDownTimer = new CountDownTimer(MAX_TIME_TO_COUNT_IN_MILLIS, 10) {

            // callback fired on regular interval.
            public void onTick(long millisUntilFinished) {
                long timeToBeConverted = MAX_TIME_TO_COUNT_IN_MILLIS-millisUntilFinished;
                mTextViewTime.setText(timerUtil.getCronoStyleTextFromMillis(TimerUtil.CRONO_FORMAT_MILLIS, timeToBeConverted));
            }

            // callback fired when the time is up.
            public void onFinish() {
                Toast.makeText(getApplicationContext(), R.string.activity_main_toast_finish_timer, Toast.LENGTH_LONG).show();
                setViewsDependingTheState(false);
                countDownTimer = null;
            }
        // start the countdown.
        }.start();
    }
}
