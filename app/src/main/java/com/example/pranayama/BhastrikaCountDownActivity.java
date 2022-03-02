package com.example.pranayama;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

public class BhastrikaCountDownActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    int active = 0;
    int active_button = 0;
    CountDownTimer anulom_vilom_count_down_timer;
    long anulom_vilom_count_down_timer_sec;
    int[] count;
    CountDownTimer count_down_timer;
    long count_down_timer_sec;
    int exhale;
    int[] flag;
    TextView hr;
    TextView info;
    TextView infoTime;
    int inhale;
    TextView min;
    ImageView pranayamcurrentpose;
    TextView sec;
    ImageView speaker_button;
    boolean speaker_flag = true;
    Button start_pause_button;
    Button stop;
    TextToSpeech textToSpeech;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bhastrika_count_down);
        this.textToSpeech = new TextToSpeech(this, this);
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.bhastrika);
        this.speaker_button = (ImageView) findViewById(R.id.reset_button);
        this.speaker_button.setImageResource(R.drawable.speaker_on);
        this.start_pause_button = (Button) findViewById(R.id.start_pause_button);
        Intent intent = getIntent();
        String hrValue = intent.getStringExtra("hr");
        String minValue = intent.getStringExtra("min");
        String secValue = intent.getStringExtra("sec");
        this.inhale = Integer.parseInt(intent.getStringExtra("inhale_count"));
        this.exhale = Integer.parseInt(intent.getStringExtra("exhale_count"));
        this.pranayamcurrentpose = (ImageView) findViewById(R.id.pranayamcurrentpose);
        this.hr = (TextView) findViewById(R.id.hr);
        this.min = (TextView) findViewById(R.id.min);
        this.sec = (TextView) findViewById(R.id.sec);
        this.info = (TextView) findViewById(R.id.info);
        this.infoTime = (TextView) findViewById(R.id.infoTime);
        this.hr.setText(hrValue);
        this.min.setText(minValue);
        this.sec.setText(secValue);
        int hrtoMiliSec = Integer.parseInt(this.hr.getText().toString()) * 3600000;
        int mintoMiliSec = Integer.parseInt(this.min.getText().toString()) * 60000;
        int sectoMiliSec = Integer.parseInt(this.sec.getText().toString()) * 1000;
        this.anulom_vilom_count_down_timer_sec = 3000;
        this.count_down_timer_sec = (long) (hrtoMiliSec + mintoMiliSec + sectoMiliSec);
        this.count = new int[]{this.inhale, 0};
        this.flag = new int[]{0};
        this.anulom_vilom_count_down_timer = new CountDownTimer(this.anulom_vilom_count_down_timer_sec, 1000) {
            /* class com.example.pranayama.BhastrikaCountDownActivity.AnonymousClass1 */

            public void onTick(long millisUntilFinished) {
                BhastrikaCountDownActivity bhastrikaCountDownActivity = BhastrikaCountDownActivity.this;
                bhastrikaCountDownActivity.anulom_vilom_count_down_timer_sec = millisUntilFinished / 1000;
                bhastrikaCountDownActivity.infoTime.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                if (BhastrikaCountDownActivity.this.speaker_flag) {
                    BhastrikaCountDownActivity.this.textToSpeech.speak("Inhale Forcefully", 1, null);
                }
                BhastrikaCountDownActivity.this.startMainCountDown();
                BhastrikaCountDownActivity.this.active = 1;
            }
        }.start();
    }

    public void startMainCountDown() {
        this.count_down_timer = new CountDownTimer(this.count_down_timer_sec, 1000) {
            /* class com.example.pranayama.BhastrikaCountDownActivity.AnonymousClass2 */

            public void onTick(long millisUntilFinished) {
                BhastrikaCountDownActivity bhastrikaCountDownActivity = BhastrikaCountDownActivity.this;
                bhastrikaCountDownActivity.count_down_timer_sec = millisUntilFinished;
                bhastrikaCountDownActivity.hr.setText(Integer.toString((int) ((millisUntilFinished / 3600000) % 24)));
                BhastrikaCountDownActivity.this.min.setText(Integer.toString((int) ((millisUntilFinished / 60000) % 60)));
                BhastrikaCountDownActivity.this.sec.setText(Integer.toString(((int) (millisUntilFinished / 1000)) % 60));
                if (BhastrikaCountDownActivity.this.count_down_timer_sec / 1000 == 1) {
                    return;
                }
                if (BhastrikaCountDownActivity.this.count[0] != 0) {
                    if (BhastrikaCountDownActivity.this.flag[0] == 0) {
                        BhastrikaCountDownActivity.this.info.setText("Inhale Forcefully");
                        BhastrikaCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.kapalbhati_inhale);
                    }
                    BhastrikaCountDownActivity.this.infoTime.setText(Integer.toString(BhastrikaCountDownActivity.this.count[0]));
                    int[] iArr = BhastrikaCountDownActivity.this.count;
                    iArr[0] = iArr[0] - 1;
                    if (BhastrikaCountDownActivity.this.count[0] == 0) {
                        BhastrikaCountDownActivity.this.count[1] = BhastrikaCountDownActivity.this.exhale;
                        if (BhastrikaCountDownActivity.this.speaker_flag) {
                            BhastrikaCountDownActivity.this.textToSpeech.speak("Exhale Forcefully", 1, null);
                        }
                    }
                } else if (BhastrikaCountDownActivity.this.count[1] != 0) {
                    BhastrikaCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.kapalbhati_exhale);
                    BhastrikaCountDownActivity.this.info.setText("Exhale Forcefully");
                    BhastrikaCountDownActivity.this.infoTime.setText(Integer.toString(BhastrikaCountDownActivity.this.count[1]));
                    int[] iArr2 = BhastrikaCountDownActivity.this.count;
                    iArr2[1] = iArr2[1] - 1;
                    if (BhastrikaCountDownActivity.this.count[1] == 0) {
                        BhastrikaCountDownActivity.this.count[0] = BhastrikaCountDownActivity.this.inhale;
                        if (BhastrikaCountDownActivity.this.speaker_flag) {
                            BhastrikaCountDownActivity.this.textToSpeech.speak("Inhale Forcefully", 1, null);
                        }
                    }
                }
            }

            public void onFinish() {
                BhastrikaCountDownActivity bhastrikaCountDownActivity = BhastrikaCountDownActivity.this;
                bhastrikaCountDownActivity.active = 0;
                bhastrikaCountDownActivity.finish();
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onDestroy() {
        this.textToSpeech.shutdown();
        super.onDestroy();
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onStartOrPause(View view) {
        if (this.active == 0) {
            this.anulom_vilom_count_down_timer.cancel();
        } else {
            this.count_down_timer.cancel();
            this.textToSpeech.stop();
        }
        if (this.active_button == 0) {
            this.start_pause_button.setBackgroundResource(R.drawable.play_button);
            this.active_button = 1;
            return;
        }
        if (this.active == 0) {
            this.anulom_vilom_count_down_timer.start();
        } else {
            startMainCountDown();
        }
        this.start_pause_button.setBackgroundResource(R.drawable.pause_button);
        this.active_button = 0;
    }

    public void onStop(View view) {
        finish();
    }

    public void onResetButtonClicked(View view) {
        if (this.speaker_flag) {
            this.speaker_button.setImageResource(R.drawable.speaker_off);
        } else {
            this.speaker_button.setImageResource(R.drawable.speaker_on);
        }
        this.speaker_flag = !this.speaker_flag;
    }

    public void onInit(int i) {
    }
}
