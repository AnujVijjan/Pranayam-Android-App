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

public class AnulomVilomCountDownActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    int active = 0;
    int active_button = 0;
    CountDownTimer anulom_vilom_count_down_timer;
    long anulom_vilom_count_down_timer_sec;
    int[] count;
    CountDownTimer count_down_timer;
    long count_down_timer_sec;
    int exhale;
    int[] flag;
    int hold;
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
    String step_side = "Left";
    Button stop;
    TextToSpeech textToSpeech;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_count_down);
        this.textToSpeech = new TextToSpeech(this, this);
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.anulom_vilom);
        this.speaker_button = (ImageView) findViewById(R.id.reset_button);
        this.speaker_button.setImageResource(R.drawable.speaker_on);
        this.start_pause_button = (Button) findViewById(R.id.start_pause_button);
        Intent intent = getIntent();
        String hrValue = intent.getStringExtra("hr");
        String minValue = intent.getStringExtra("min");
        String secValue = intent.getStringExtra("sec");
        this.inhale = Integer.parseInt(intent.getStringExtra("inhale_count"));
        this.hold = Integer.parseInt(intent.getStringExtra("hold_count"));
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
        this.count = new int[]{this.inhale, 0, 0};
        this.flag = new int[]{0};
        this.anulom_vilom_count_down_timer = new CountDownTimer(this.anulom_vilom_count_down_timer_sec, 1000) {
            /* class com.example.pranayama.AnulomVilomCountDownActivity.AnonymousClass1 */

            public void onTick(long millisUntilFinished) {
                AnulomVilomCountDownActivity AnulomVilomCountDownActivity = AnulomVilomCountDownActivity.this;
                AnulomVilomCountDownActivity.anulom_vilom_count_down_timer_sec = millisUntilFinished / 1000;
                AnulomVilomCountDownActivity.infoTime.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                if (AnulomVilomCountDownActivity.this.speaker_flag) {
                    TextToSpeech textToSpeech = AnulomVilomCountDownActivity.this.textToSpeech;
                    textToSpeech.speak("Inhale " + AnulomVilomCountDownActivity.this.step_side, 1, null);
                }
                AnulomVilomCountDownActivity.this.startMainCountDownTimer();
                AnulomVilomCountDownActivity.this.active = 1;
            }
        }.start();
    }

    public void startMainCountDownTimer() {
        this.count_down_timer = new CountDownTimer(this.count_down_timer_sec, 1000) {
            /* class com.example.pranayama.AnulomVilomCountDownActivity.AnonymousClass2 */

            public void onTick(long millisUntilFinished) {
                AnulomVilomCountDownActivity AnulomVilomCountDownActivity = AnulomVilomCountDownActivity.this;
                AnulomVilomCountDownActivity.count_down_timer_sec = millisUntilFinished;
                AnulomVilomCountDownActivity.hr.setText(String.format("%02d", (millisUntilFinished / 3600000) % 24));
                AnulomVilomCountDownActivity.this.min.setText(String.format("%02d", (millisUntilFinished / 60000) % 60));
                AnulomVilomCountDownActivity.this.sec.setText(String.format("%02d", (millisUntilFinished / 1000) % 60));
                if (AnulomVilomCountDownActivity.this.count_down_timer_sec / 1000 == 1) {
                    return;
                }
                if (AnulomVilomCountDownActivity.this.count[0] != 0) {
                    AnulomVilomCountDownActivity.this.infoTime.setText(Integer.toString(AnulomVilomCountDownActivity.this.count[0]));
                    if (AnulomVilomCountDownActivity.this.flag[0] == 0) {
                        AnulomVilomCountDownActivity.this.info.setText("Inhale Left");
                        AnulomVilomCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.left_image);
                    } else {
                        AnulomVilomCountDownActivity.this.info.setText("Inhale Right");
                        AnulomVilomCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.right_image);
                    }
                    int[] iArr = AnulomVilomCountDownActivity.this.count;
                    iArr[0] = iArr[0] - 1;
                    if (AnulomVilomCountDownActivity.this.count[0] == 0) {
                        AnulomVilomCountDownActivity.this.count[1] = AnulomVilomCountDownActivity.this.hold;
                        if (AnulomVilomCountDownActivity.this.speaker_flag) {
                            AnulomVilomCountDownActivity.this.textToSpeech.speak("Hold", 1, null);
                        }
                        if (AnulomVilomCountDownActivity.this.flag[0] == 0) {
                            AnulomVilomCountDownActivity.this.flag[0] = 1;
                            AnulomVilomCountDownActivity.this.step_side = "Right";
                            return;
                        }
                        AnulomVilomCountDownActivity.this.flag[0] = 0;
                        AnulomVilomCountDownActivity.this.step_side = "Left";
                    }
                } else if (AnulomVilomCountDownActivity.this.count[1] != 0) {
                    AnulomVilomCountDownActivity.this.infoTime.setText(Integer.toString(AnulomVilomCountDownActivity.this.count[1]));
                    AnulomVilomCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.hold_image);
                    AnulomVilomCountDownActivity.this.info.setText("Hold");
                    int[] iArr2 = AnulomVilomCountDownActivity.this.count;
                    iArr2[1] = iArr2[1] - 1;
                    if (AnulomVilomCountDownActivity.this.count[1] == 0) {
                        AnulomVilomCountDownActivity.this.count[2] = AnulomVilomCountDownActivity.this.exhale;
                        if (AnulomVilomCountDownActivity.this.speaker_flag) {
                            TextToSpeech textToSpeech = AnulomVilomCountDownActivity.this.textToSpeech;
                            textToSpeech.speak("Exhale " + AnulomVilomCountDownActivity.this.step_side, 1, null);
                        }
                    }
                } else if (AnulomVilomCountDownActivity.this.count[2] != 0) {
                    if (AnulomVilomCountDownActivity.this.flag[0] == 0) {
                        AnulomVilomCountDownActivity.this.info.setText("Exhale Left");
                        AnulomVilomCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.left_image);
                    } else {
                        AnulomVilomCountDownActivity.this.info.setText("Exhale Right");
                        AnulomVilomCountDownActivity.this.pranayamcurrentpose.setImageResource(R.drawable.right_image);
                    }
                    AnulomVilomCountDownActivity.this.infoTime.setText(Integer.toString(AnulomVilomCountDownActivity.this.count[2]));
                    int[] iArr3 = AnulomVilomCountDownActivity.this.count;
                    iArr3[2] = iArr3[2] - 1;
                    if (AnulomVilomCountDownActivity.this.count[2] == 0) {
                        AnulomVilomCountDownActivity.this.count[0] = AnulomVilomCountDownActivity.this.inhale;
                        if (AnulomVilomCountDownActivity.this.speaker_flag) {
                            TextToSpeech textToSpeech2 = AnulomVilomCountDownActivity.this.textToSpeech;
                            textToSpeech2.speak("Inhale " + AnulomVilomCountDownActivity.this.step_side, 1, null);
                        }
                    }
                }
            }

            public void onFinish() {
                AnulomVilomCountDownActivity AnulomVilomCountDownActivity = AnulomVilomCountDownActivity.this;
                AnulomVilomCountDownActivity.active = 0;
                AnulomVilomCountDownActivity.finish();
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onDestroy() {
        this.textToSpeech.shutdown();
        super.onDestroy();
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
            startMainCountDownTimer();
        }
        this.start_pause_button.setBackgroundResource(R.drawable.pause_button);
        this.active_button = 0;
    }

    public void onBackButtonClicked(View view) {
        finish();
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
