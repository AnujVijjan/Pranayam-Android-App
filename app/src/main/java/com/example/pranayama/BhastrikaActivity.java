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

public class BhastrikaActivity extends AppCompatActivity {
    NumberPicker exhale;
    public Boolean fabExpanded;
    FabSubMenu fabSubMenu;
    TextView hr;
    NumberPicker inhale;
    LinearLayout layoutFabBenefits, layoutFabHelp, layoutFabInfo;
    TextView min;
    String path;
    FloatingActionButton read_more;
    DatabaseReference report;
    NumberPicker rounds;
    TextView sec;
    int sr_no;
    DatabaseReference sr_no_child;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bhastrika);
        this.path = "report/Bhastrika";
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.bhastrika);
        this.layoutFabInfo = (LinearLayout) findViewById(R.id.layoutFabInfo);
        this.layoutFabHelp = (LinearLayout) findViewById(R.id.layoutFabHelp);
        this.layoutFabBenefits = (LinearLayout) findViewById(R.id.layoutFabBenefits);
        this.layoutFabHelp.setOnClickListener(new View.OnClickListener() {
            /* class com.example.pranayama.BhastrikaActivity.AnonymousClass1 */

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BhastrikaHelpActivity.class));
            }
        });
        this.layoutFabBenefits.setOnClickListener(new View.OnClickListener() {
            /* class com.example.pranayama.BhastrikaActivity.AnonymousClass2 */

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BhastrikaBenefitsActivity.class));
            }
        });
        this.layoutFabInfo.setOnClickListener(new View.OnClickListener() {
            /* class com.example.pranayama.BhastrikaActivity.AnonymousClass3 */

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BhastrikaInfoActivity.class));
            }
        });
        this.fabSubMenu = new FabSubMenu();
        this.read_more = (FloatingActionButton) findViewById(R.id.read_more);
        this.fabExpanded = Boolean.valueOf(this.fabSubMenu.closeSubMenusFab(this.layoutFabInfo, this.layoutFabBenefits, this.layoutFabHelp, this.read_more));
        this.inhale = (NumberPicker) findViewById(R.id.inhale);
        this.exhale = (NumberPicker) findViewById(R.id.exhale);
        this.rounds = (NumberPicker) findViewById(R.id.rounds);
        this.inhale.setMinValue(1);
        this.inhale.setMaxValue(20);
        this.inhale.setValue(5);
        this.exhale.setMinValue(1);
        this.exhale.setMaxValue(40);
        this.exhale.setValue(10);
        this.rounds.setMinValue(1);
        this.rounds.setMaxValue(50);
        this.rounds.setValue(4);
        this.hr = (TextView) findViewById(R.id.hr);
        this.min = (TextView) findViewById(R.id.min);
        this.sec = (TextView) findViewById(R.id.sec);
        this.inhale.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /* class com.example.pranayama.BhastrikaActivity.AnonymousClass4 */

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                BhastrikaActivity.this.findTheTotalTime();
            }
        });
        this.exhale.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /* class com.example.pranayama.BhastrikaActivity.AnonymousClass5 */

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                BhastrikaActivity.this.findTheTotalTime();
            }
        });
        this.rounds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /* class com.example.pranayama.BhastrikaActivity.AnonymousClass6 */

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                BhastrikaActivity.this.findTheTotalTime();
            }
        });

        ActivityPranayam.initializeFirebase(path);

    }

    public void findTheTotalTime() {
        changeTimerValues((this.inhale.getValue() + this.exhale.getValue()) * this.rounds.getValue());
    }

    public void rightButton(View view) {
        int inhale_value = this.inhale.getValue();
        int exhale_value = this.exhale.getValue();
        if (inhale_value < 20 && exhale_value < 40) {
            this.inhale.setValue(inhale_value + 1);
            this.exhale.setValue(exhale_value + 2);
            findTheTotalTime();
        }
    }

    public void leftButton(View view) {
        int inhale_value = this.inhale.getValue();
        int exhale_value = this.exhale.getValue();
        if (inhale_value > 1 && exhale_value > 2) {
            this.inhale.setValue(inhale_value - 1);
            this.exhale.setValue(exhale_value - 2);
            findTheTotalTime();
        }
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onResetButtonClicked(View view) {
        findTheTotalTime();
        this.inhale.setValue(5);
        this.exhale.setValue(10);
        this.rounds.setValue(4);
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

        this.sr_no_child = databaseReference.child(Integer.toString(sr_no));
        report_activity.onPranayamaPlayButtonClicked(this.inhale.getValue(), 0, this.exhale.getValue(), this.rounds.getValue(), this.sr_no_child);
        Intent intent = new Intent(this, BhastrikaCountDownActivity.class);
        intent.putExtra("hr", this.hr.getText().toString());
        intent.putExtra("min", this.min.getText().toString());
        intent.putExtra("sec", this.sec.getText().toString());
        intent.putExtra("inhale_count", Integer.toString(this.inhale.getValue()));
        intent.putExtra("exhale_count", Integer.toString(this.exhale.getValue()));
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
