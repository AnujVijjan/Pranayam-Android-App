package com.example.pranayama;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DateFormat;
import java.util.Date;

public class reportActivity extends AppCompatActivity {
    LinearLayout records;
    DatabaseReference report;
    int sr;
    DatabaseReference sr_no_child;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.report);
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String path = getIntent().getStringExtra("path");
        this.report = database.getReference(MainActivity.androidId + '/' + path);
        this.records = (LinearLayout) findViewById(R.id.records);
        this.report.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    LinearLayout record = new LinearLayout(reportActivity.this);
                    LinearLayout.LayoutParams recordLayoutParams = new LinearLayout.LayoutParams(-1, -1, 1.0f);
                    recordLayoutParams.setMargins(0, 0, 0, 20);
                    record.setLayoutParams(recordLayoutParams);
                    record.setOrientation(0);
                    TextView no = new TextView(reportActivity.this);
                    TextView rounds = new TextView(reportActivity.this);
                    TextView dateandtime = new TextView(reportActivity.this);
                    TextView level = new TextView(reportActivity.this);
                    LinearLayout.LayoutParams noParams = new LinearLayout.LayoutParams(-2, -1, 1.0f);
                    noParams.setMargins(83, 0, 0, 0);
                    no.setLayoutParams(noParams);
                    no.setGravity(17);
                    no.setText(child.getKey());
                    no.setTextColor(reportActivity.this.getResources().getColor(R.color.primaryTitleColor));
                    record.addView(no);
                    LinearLayout.LayoutParams levelParams = new LinearLayout.LayoutParams(-2, -1, 1.0f);
//                    levelParams.setMargins(50, 0, 0, 0);
                    level.setLayoutParams(levelParams);
                    level.setGravity(17);
                    level.setText(child.child("LEVEL").getValue().toString());
                    no.setTextColor(reportActivity.this.getResources().getColor(R.color.primaryTitleColor));
                    record.addView(level);
                    LinearLayout.LayoutParams roundsParams = new LinearLayout.LayoutParams(-2, -1, 1.0f);
                    roundsParams.setMargins(20, 0, 0, 0);
                    rounds.setLayoutParams(roundsParams);
                    rounds.setGravity(17);
                    rounds.setText(child.child("ROUNDS").getValue().toString());
                    no.setTextColor(reportActivity.this.getResources().getColor(R.color.primaryTitleColor));
                    record.addView(rounds);
                    LinearLayout.LayoutParams dateandtimeParams = new LinearLayout.LayoutParams(-2, -1, 1.0f);
                    dateandtimeParams.setMargins(30, 0, 0, 0);
                    dateandtime.setLayoutParams(dateandtimeParams);
                    dateandtime.setGravity(17);
                    dateandtime.setText(child.child("DATE & TIME").getValue().toString());
                    no.setTextColor(reportActivity.this.getResources().getColor(R.color.primaryTitleColor));
                    record.addView(dateandtime);
                    reportActivity.this.records.addView(record);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void onPranayamaPlayButtonClicked(int inhale, int hold, int exhale, int rounds, DatabaseReference sr_no_child2) {
        DatabaseReference child = sr_no_child2.child("LEVEL");
        child.setValue(inhale + " : " + hold + " : " + exhale);
        sr_no_child2.child("ROUNDS").setValue(Integer.valueOf(rounds));
        sr_no_child2.child("DATE & TIME").setValue(DateFormat.getDateTimeInstance().format(Long.valueOf(new Date().getTime())));
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onResetButtonClicked(View view) {
        this.report.removeValue();
        recreate();
    }
}
