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

public class KapalbhatiCountDownActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    int active = 0;
    int active_button = 0;
    CountDownTimer anulom_vilom_count_down_timer;
    long anulom_vilom_count_down_timer_sec;
    int[] count;
    CountDownTimer count_down_timer;
    long count_down_timer_sec;
    TextView hr;
    TextView info;
    TextView infoTime;
    TextView min;
    ImageView pranayamcurrentpose;
    TextView rounds;
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

        setContentView(R.layout.activity_kapalbhati_count_down);
        this.textToSpeech = new TextToSpeech(this, this);
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        this.speaker_button = (ImageView) findViewById(R.id.reset_button);
        this.speaker_button.setImageResource(R.drawable.speaker_on);
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.kapalbhati);
        this.start_pause_button = (Button) findViewById(R.id.start_pause_button);
        Intent intent = getIntent();
        String hrValue = intent.getStringExtra("hr");
        String minValue = intent.getStringExtra("min");
        String secValue = intent.getStringExtra("sec");
        this.pranayamcurrentpose = (ImageView) findViewById(R.id.pranayamcurrentpose);
        this.hr = (TextView) findViewById(R.id.hr);
        this.min = (TextView) findViewById(R.id.min);
        this.sec = (TextView) findViewById(R.id.sec);
        this.info = (TextView) findViewById(R.id.info);
        this.infoTime = (TextView) findViewById(R.id.infoTime);
        this.hr.setText(hrValue);
        this.min.setText(minValue);
        this.sec.setText(secValue);
        this.anulom_vilom_count_down_timer_sec = 3000;
        this.count_down_timer_sec = (long) (Integer.parseInt(this.min.getText().toString()) * 60000);
        this.count = new int[]{1, 2};
        this.anulom_vilom_count_down_timer = new CountDownTimer(this.anulom_vilom_count_down_timer_sec, 1000) {
            /* class com.example.pranayama.KapalbhatiCountDownActivity.AnonymousClass1 */

            public void onTick(long millisUntilFinished) {
                KapalbhatiCountDownActivity kapalbhatiCountDownActivity = KapalbhatiCountDownActivity.this;
                kapalbhatiCountDownActivity.anulom_vilom_count_down_timer_sec = millisUntilFinished / 1000;
                kapalbhatiCountDownActivity.infoTime.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                if (KapalbhatiCountDownActivity.this.speaker_flag) {
                    KapalbhatiCountDownActivity.this.textToSpeech.speak("Inhale Normally", 1, null);
                }
                KapalbhatiCountDownActivity.this.startMainCountDown();
                KapalbhatiCountDownActivity.this.active = 1;
            }
        }.start();
    }

    public void startMainCountDown() {
        this.count_down_timer = new CountDownTimer(this.count_down_timer_sec, 1000) {
            /* class com.example.pranayama.KapalbhatiCountDownActivity.AnonymousClass2 */

            public void onTick(long millisUntilFinished) {
                KapalbhatiCountDownActivity kapalbhatiCountDownActivity = KapalbhatiCountDownActivity.this;
                kapalbhatiCountDownActivity.count_down_timer_sec = millisUntilFinished;
                kapalbhatiCountDownActivity.hr.setText(Integer.toString((int) ((millisUntilFinished / 3600000) % 24)));
                KapalbhatiCountDownActivity.this.min.setText(Integer.toString((int) ((millisUntilFinished / 60000) % 60)));
                KapalbhatiCountDownActivity.this.sec.setText(Integer.toString(((int) (millisUntilFinished / 1000)) % 60));
                KapalbhatiCountDownActivity.this.infoTime.setText(" ");
                if (KapalbhatiCountDownActivity.this.count_down_timer_sec / 1000 == 1) {
                    return;
                }
                if (KapalbhatiCountDownActivity.this.count[0] != 0) {
                    KapalbhatiCountDownActivity.this.info.setText("Inhale Normally");
                    KapalbhatiCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.kapalbhati_inhale);
                    int[] iArr = KapalbhatiCountDownActivity.this.count;
                    iArr[0] = iArr[0] - 1;
                    if (KapalbhatiCountDownActivity.this.count[0] == 0) {
                        KapalbhatiCountDownActivity.this.count[1] = 1;
                        if (KapalbhatiCountDownActivity.this.speaker_flag) {
                            KapalbhatiCountDownActivity.this.textToSpeech.speak("Exhale Forcefully", 1, null);
                        }
                    }
                } else if (KapalbhatiCountDownActivity.this.count[1] != 0) {
                    KapalbhatiCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.kapalbhati_exhale);
                    KapalbhatiCountDownActivity.this.info.setText("Exhale Forcefully");
                    int[] iArr2 = KapalbhatiCountDownActivity.this.count;
                    iArr2[1] = iArr2[1] - 1;
                    if (KapalbhatiCountDownActivity.this.count[1] == 0) {
                        if (KapalbhatiCountDownActivity.this.speaker_flag) {
                            KapalbhatiCountDownActivity.this.textToSpeech.speak("Inhale Normally", 1, null);
                        }
                        KapalbhatiCountDownActivity.this.count[0] = 1;
                    }
                }
            }

            public void onFinish() {
                KapalbhatiCountDownActivity kapalbhatiCountDownActivity = KapalbhatiCountDownActivity.this;
                kapalbhatiCountDownActivity.active = 0;
                kapalbhatiCountDownActivity.finish();
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
