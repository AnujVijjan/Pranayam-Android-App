package com.example.pranayama;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MBreathingActivity extends AppCompatActivity {
    TextView exhale;
    public Boolean fabExpanded;
    FabSubMenu fabSubMenu;
    TextView hold;
    TextView hr;
    TextView inhale;
    LinearLayout layoutFabBenefits, layoutFabHelp, layoutFabInfo;
    TextView min;
    String path;
    FloatingActionButton read_more;
    DatabaseReference report;
    NumberPicker rounds;
    TextView sec;
    int sr_no;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mbreathing);
        this.path = "report/MBreathing";
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.m_breathing);
        this.layoutFabInfo = (LinearLayout) findViewById(R.id.layoutFabInfo);
        this.layoutFabHelp = (LinearLayout) findViewById(R.id.layoutFabHelp);
        this.layoutFabBenefits = (LinearLayout) findViewById(R.id.layoutFabBenefits);
        this.layoutFabHelp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MBreathingHelpActivity.class));
            }
        });
        this.layoutFabBenefits.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MBreathingBenefitsActivity.class));
            }
        });
        this.layoutFabInfo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MBreathingInfoActivity.class));
            }
        });
        this.fabSubMenu = new FabSubMenu();
        this.read_more = (FloatingActionButton) findViewById(R.id.read_more);
        this.fabExpanded = Boolean.valueOf(this.fabSubMenu.closeSubMenusFab(this.layoutFabInfo, this.layoutFabBenefits, this.layoutFabHelp, this.read_more));
        this.inhale = (TextView) findViewById(R.id.inhale);
        this.hold = (TextView) findViewById(R.id.hold);
        this.exhale = (TextView) findViewById(R.id.exhale);
        this.rounds = (NumberPicker) findViewById(R.id.rounds);
        this.rounds.setMinValue(1);
        this.rounds.setMaxValue(50);
        this.rounds.setValue(1);
        this.hr = (TextView) findViewById(R.id.hr);
        this.min = (TextView) findViewById(R.id.min);
        this.sec = (TextView) findViewById(R.id.sec);
        this.rounds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /* class com.example.pranayama.MBreathingActivity.AnonymousClass4 */

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                MBreathingActivity.this.findTheTotalTime();
            }
        });

        ActivityPranayam.initializeFirebase(path);
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onResetButtonClicked(View view) {
        this.rounds.setValue(1);
        findTheTotalTime();
    }

    public void findTheTotalTime() {
        changeTimerValues((Integer.parseInt(this.inhale.getText().toString()) + Integer.parseInt(this.hold.getText().toString()) + Integer.parseInt(this.exhale.getText().toString())) * this.rounds.getValue());
    }

    public void changeTimerValues(int seconds) {
        Date d = new Date(((long) seconds) * 1000);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = df.format(d);
        this.hr.setText(time.split(":")[0]);
        this.min.setText(time.split(":")[1]);
        this.sec.setText(time.split(":")[2]);
    }

    public void bounceButton(Button button) {
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        myAnim.setInterpolator(new MyBounceInterpolator(0.2d, 20.0d));
        button.startAnimation(myAnim);
    }

    public void startCountDownActivity(View view) {
        bounceButton((Button) findViewById(R.id.play_button));
        reportActivity report_activity = new reportActivity();
        DatabaseReference databaseReference = ActivityPranayam.report;

        sr_no = Prefs.getInt(path, 0);

        sr_no += 1;
        report_activity.onPranayamaPlayButtonClicked(Integer.parseInt(this.inhale.getText().toString()), Integer.parseInt(this.hold.getText().toString()), Integer.parseInt(this.exhale.getText().toString()), this.rounds.getValue(), databaseReference.child(Integer.toString(sr_no)));
        Intent intent = new Intent(this, MBreathingCountDownActivity.class);
        intent.putExtra("hr", this.hr.getText().toString());
        intent.putExtra("min", this.min.getText().toString());
        intent.putExtra("sec", this.sec.getText().toString());
        intent.putExtra("inhale_count", this.inhale.getText().toString());
        intent.putExtra("hold_count", this.hold.getText().toString());
        intent.putExtra("exhale_count", this.exhale.getText().toString());
        startActivity(intent);
    }

    public void onReadMore(View view) {
        if (this.fabExpanded.booleanValue()) {
            this.fabExpanded = Boolean.valueOf(this.fabSubMenu.closeSubMenusFab(this.layoutFabInfo, this.layoutFabBenefits, this.layoutFabHelp, this.read_more));
        } else {
            this.fabExpanded = Boolean.valueOf(this.fabSubMenu.openSubMenusFab(this.layoutFabInfo, this.layoutFabBenefits, this.layoutFabHelp, this.read_more));
        }
    }

    public void startReportActivity(View view) {
        Intent intent = new Intent(this, reportActivity.class);
        intent.putExtra("path", this.path);
        startActivity(intent);
    }
}
