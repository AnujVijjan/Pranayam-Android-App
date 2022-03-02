package com.example.pranayama;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
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

public class ActivityAnulomVilom extends AppCompatActivity {
    NumberPicker exhale;
    public Boolean fabExpanded;
    FabSubMenu fabSubMenu;
    NumberPicker hold;
    NumberPicker hold1;
    TextView hr;
    NumberPicker inhale;
    LinearLayout layoutFabBenefits, layoutFabHelp, layoutFabInfo;
    TextView min;
    String path;
    FloatingActionButton read_more;
    NumberPicker rounds;
    TextView sec;
    int sr_no;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anulom_vilom);

        path = "report/AnulomVilom";

        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.anulom_vilom);
        layoutFabInfo = (LinearLayout) findViewById(R.id.layoutFabInfo);
        layoutFabHelp = (LinearLayout) findViewById(R.id.layoutFabHelp);
        layoutFabBenefits = (LinearLayout) findViewById(R.id.layoutFabBenefits);
        read_more = (FloatingActionButton) findViewById(R.id.read_more);

        layoutFabHelp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityAnulomVilomHelp.class);
                startActivity(intent);
            }
        });

        layoutFabBenefits.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AnulomVilomBenefitsActivity.class));
            }
        });

        layoutFabInfo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AnulomVilomInfoActivity.class));
            }
        });

        fabSubMenu = new FabSubMenu();
        read_more = (FloatingActionButton) findViewById(R.id.read_more);
        fabExpanded = Boolean.valueOf(fabSubMenu.closeSubMenusFab(layoutFabInfo, layoutFabBenefits, layoutFabHelp, read_more));
        inhale = findViewById(R.id.inhale);
        hold = findViewById(R.id.hold);
        exhale = findViewById(R.id.exhale);
        hold1 = findViewById(R.id.hold1);
        rounds = findViewById(R.id.rounds);
        inhale.setMinValue(1);
        inhale.setMaxValue(50);
        inhale.setValue(1);
        hold.setMinValue(0);
        hold.setMaxValue(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        hold.setValue(4);
        exhale.setMinValue(1);
        exhale.setMaxValue(100);
        exhale.setValue(2);
        hold1.setMinValue(0);
        hold1.setMaxValue(10);
        hold1.setValue(0);
        rounds.setMinValue(1);
        rounds.setMaxValue(50);
        rounds.setValue(1);
        hr = (TextView) findViewById(R.id.hr);
        min = (TextView) findViewById(R.id.min);
        sec = (TextView) findViewById(R.id.sec);
        int count = inhale.getChildCount();

        for (int i = 0; i < count; i++) {
            ((EditText) inhale.getChildAt(i)).setTextColor(getResources().getColor(R.color.primaryTitleColor));
        }
        inhale.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                findTheTotalTime();
            }
        });

        exhale.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                findTheTotalTime();
            }
        });
        hold.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                findTheTotalTime();
            }
        });
        hold1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                findTheTotalTime();
            }
        });
        rounds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                findTheTotalTime();
            }
        });

        ActivityPranayam.initializeFirebase(path);


    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onResetButtonClicked(View view) {
        inhale.setValue(1);
        exhale.setValue(2);
        hold.setValue(4);
        hold1.setValue(0);
        rounds.setValue(1);
        findTheTotalTime();
    }

    public void findTheTotalTime() {
        changeTimerValues((inhale.getValue() + exhale.getValue() + hold.getValue() + hold1.getValue()) * 2 * rounds.getValue());
    }

    public void rightButton(View view) {
        int inhale_value = inhale.getValue();
        int hold_value = hold.getValue();
        int exhale_value = exhale.getValue();
        if (inhale_value < 50 && exhale_value < 100 && hold_value < 200) {
            inhale.setValue(inhale_value + 1);
            hold.setValue(hold_value + 4);
            exhale.setValue(exhale_value + 2);
            findTheTotalTime();
        }
    }

    public void leftButton(View view) {
        int inhale_value = inhale.getValue();
        int hold_value = hold.getValue();
        int exhale_value = exhale.getValue();
        if (inhale_value > 1 && exhale_value > 2 && hold_value > 4) {
            inhale.setValue(inhale_value - 1);
            hold.setValue(hold_value - 4);
            exhale.setValue(exhale_value - 2);
            findTheTotalTime();
        }
    }

    public void changeTimerValues(int seconds) {
        Date d = new Date(((long) seconds) * 1000);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = df.format(d);
        hr.setText(time.split(":")[0]);
        min.setText(time.split(":")[1]);
        sec.setText(time.split(":")[2]);
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

        report_activity.onPranayamaPlayButtonClicked(inhale.getValue(), hold.getValue(), exhale.getValue(), rounds.getValue(), databaseReference.child(Integer.toString(sr_no)));
        Intent intent = new Intent(this, AnulomVilomCountDownActivity.class);
        intent.putExtra("hr", hr.getText().toString());
        intent.putExtra("min", min.getText().toString());
        intent.putExtra("sec", sec.getText().toString());
        intent.putExtra("inhale_count", Integer.toString(inhale.getValue()));
        intent.putExtra("hold_count", Integer.toString(hold.getValue()));
        intent.putExtra("exhale_count", Integer.toString(exhale.getValue()));
        startActivity(intent);
    }

    public void onReadMore(View view) {
        if (fabExpanded.booleanValue()) {
            fabExpanded = Boolean.valueOf(fabSubMenu.closeSubMenusFab(layoutFabInfo, layoutFabBenefits, layoutFabHelp, read_more));
        } else {
            fabExpanded = Boolean.valueOf(fabSubMenu.openSubMenusFab(layoutFabInfo, layoutFabBenefits, layoutFabHelp, read_more));
        }
    }

    public void startReportActivity(View view) {
        Intent intent = new Intent(this, reportActivity.class);
        intent.putExtra("path", path);
        startActivity(intent);
    }
}
