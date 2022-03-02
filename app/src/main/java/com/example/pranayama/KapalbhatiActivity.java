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

public class KapalbhatiActivity extends AppCompatActivity {
    public Boolean fabExpanded;
    FabSubMenu fabSubMenu;
    TextView hr;
    LinearLayout layoutFabBenefits, layoutFabHelp, layoutFabInfo;
    TextView min;
    String path;
    FloatingActionButton read_more;
    DatabaseReference report;
    int roundValue;
    NumberPicker rounds;
    TextView sec;
    int sr_no;
    NumberPicker time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kapalbhati);
        this.path = "report/Kapalbhati";
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.kapalbhati);
        this.layoutFabInfo = (LinearLayout) findViewById(R.id.layoutFabInfo);
        this.layoutFabHelp = (LinearLayout) findViewById(R.id.layoutFabHelp);
        this.layoutFabBenefits = (LinearLayout) findViewById(R.id.layoutFabBenefits);

        this.layoutFabHelp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KapalbhatiHelpActivity.class));
            }
        });
        this.layoutFabBenefits.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KapalbhatiBenefitsActivity.class));
            }
        });
        this.layoutFabInfo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KapalbhatiInfoActivity.class));
            }
        });
        this.fabSubMenu = new FabSubMenu();
        this.read_more = (FloatingActionButton) findViewById(R.id.read_more);
        this.fabExpanded = Boolean.valueOf(this.fabSubMenu.closeSubMenusFab(this.layoutFabInfo, this.layoutFabBenefits, this.layoutFabHelp, this.read_more));
        this.time = (NumberPicker) findViewById(R.id.time);
        this.time.setMinValue(1);
        this.time.setMaxValue(20);
        this.time.setValue(1);
        this.hr = (TextView) findViewById(R.id.hr);
        this.min = (TextView) findViewById(R.id.min);
        this.sec = (TextView) findViewById(R.id.sec);
        this.time.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /* class com.example.pranayama.KapalbhatiActivity.AnonymousClass4 */

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                KapalbhatiActivity.this.min.setText(Integer.toString(KapalbhatiActivity.this.time.getValue()));
            }
        });

        ActivityPranayam.initializeFirebase(path);
    }


    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onResetButtonClicked(View view) {
        this.time.setValue(1);
        this.min.setText(Integer.toString(this.time.getValue()));
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
        report_activity.onPranayamaPlayButtonClicked(0, 0, 0, 0, databaseReference.child(Integer.toString(sr_no)));
        Intent intent = new Intent(this, KapalbhatiCountDownActivity.class);
        intent.putExtra("hr", this.hr.getText().toString());
        intent.putExtra("min", this.min.getText().toString());
        intent.putExtra("sec", this.sec.getText().toString());
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
