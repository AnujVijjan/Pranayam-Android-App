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
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SuryaBhedanaActivity extends AppCompatActivity {
    NumberPicker exhale;
    public Boolean fabExpanded;
    FabSubMenu fabSubMenu;
    NumberPicker hold;
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

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_surya_bhedana);
        this.path = "report/SuryaBhedana";
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.surya_bhedana);
        this.layoutFabInfo = (LinearLayout) findViewById(R.id.layoutFabInfo);
        this.layoutFabHelp = (LinearLayout) findViewById(R.id.layoutFabHelp);
        this.layoutFabBenefits = (LinearLayout) findViewById(R.id.layoutFabBenefits);
        this.layoutFabHelp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SuryaBhedanaHelpActivity.class));
            }
        });
        this.layoutFabBenefits.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SuryaBhedanaBenefitsActivity.class));
            }
        });
        this.layoutFabInfo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SuryaBhedanaInfoActivity.class));
            }
        });
        this.fabSubMenu = new FabSubMenu();
        this.read_more = (FloatingActionButton) findViewById(R.id.read_more);
        this.fabExpanded = Boolean.valueOf(this.fabSubMenu.closeSubMenusFab(this.layoutFabInfo, this.layoutFabBenefits, this.layoutFabHelp, this.read_more));
        this.inhale = (NumberPicker) findViewById(R.id.inhale);
        this.hold = (NumberPicker) findViewById(R.id.hold);
        this.exhale = (NumberPicker) findViewById(R.id.exhale);
        this.rounds = (NumberPicker) findViewById(R.id.rounds);
        this.inhale.setMinValue(1);
        this.inhale.setMaxValue(50);
        this.inhale.setValue(1);
        this.hold.setMinValue(0);
        this.hold.setMaxValue(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        this.hold.setValue(4);
        this.exhale.setMinValue(1);
        this.exhale.setMaxValue(100);
        this.exhale.setValue(2);
        this.rounds.setMinValue(1);
        this.rounds.setMaxValue(50);
        this.rounds.setValue(1);
        this.hr = (TextView) findViewById(R.id.hr);
        this.min = (TextView) findViewById(R.id.min);
        this.sec = (TextView) findViewById(R.id.sec);
        this.inhale.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /* class com.example.pranayama.SuryaBhedanaActivity.AnonymousClass4 */

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SuryaBhedanaActivity.this.findTheTotalTime();
            }
        });
        this.exhale.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SuryaBhedanaActivity.this.findTheTotalTime();
            }
        });
        this.hold.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SuryaBhedanaActivity.this.findTheTotalTime();
            }
        });
        this.rounds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SuryaBhedanaActivity.this.findTheTotalTime();
            }
        });

        ActivityPranayam.initializeFirebase(path);
    }

    public void findTheTotalTime() {
        changeTimerValues((this.inhale.getValue() + this.exhale.getValue() + this.hold.getValue()) * this.rounds.getValue());
    }

    public void rightButton(View view) {
        int inhale_value = this.inhale.getValue();
        int hold_value = this.hold.getValue();
        int exhale_value = this.exhale.getValue();
        if (inhale_value < 50 && exhale_value < 100 && hold_value < 200) {
            this.inhale.setValue(inhale_value + 1);
            this.hold.setValue(hold_value + 4);
            this.exhale.setValue(exhale_value + 2);
            findTheTotalTime();
        }
    }

    public void leftButton(View view) {
        int inhale_value = this.inhale.getValue();
        int hold_value = this.hold.getValue();
        int exhale_value = this.exhale.getValue();
        if (inhale_value > 1 && exhale_value > 2 && hold_value > 4) {
            this.inhale.setValue(inhale_value - 1);
            this.hold.setValue(hold_value - 4);
            this.exhale.setValue(exhale_value - 2);
            findTheTotalTime();
        }
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
        report_activity.onPranayamaPlayButtonClicked(this.inhale.getValue(), this.hold.getValue(), this.exhale.getValue(), this.rounds.getValue(), databaseReference.child(Integer.toString(sr_no)));
        Intent intent = new Intent(this, SuryaBhedanaCountDownActivity.class);
        intent.putExtra("hr", this.hr.getText().toString());
        intent.putExtra("min", this.min.getText().toString());
        intent.putExtra("sec", this.sec.getText().toString());
        intent.putExtra("inhale_count", Integer.toString(this.inhale.getValue()));
        intent.putExtra("hold_count", Integer.toString(this.hold.getValue()));
        intent.putExtra("exhale_count", Integer.toString(this.exhale.getValue()));
        startActivity(intent);
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onResetButtonClicked(View view) {
        this.inhale.setValue(1);
        this.exhale.setValue(2);
        this.hold.setValue(4);
        this.rounds.setValue(1);
        findTheTotalTime();
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
