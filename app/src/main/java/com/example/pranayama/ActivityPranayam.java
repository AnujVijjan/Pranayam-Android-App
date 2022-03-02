package com.example.pranayama;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pixplicity.easyprefs.library.Prefs;

public class ActivityPranayam extends AppCompatActivity {
    RelativeLayout anulom_vilom;
    TextView avTitle;
    CardView bhastrika, bhramari, chandra_bhedana, kapalbhati, mbreathing, sheetali, surya_bhedana, ujjayi;
    public static DatabaseReference report;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        setContentView(R.layout.activity_pranayam);


        anulom_vilom = findViewById(R.id.anulom_vilom);
        kapalbhati = findViewById(R.id.kapalbhati);
        bhramari = findViewById(R.id.bhramari);
        surya_bhedana = findViewById(R.id.surya_bhedana);
        chandra_bhedana = findViewById(R.id.chandra_bhedana);
        bhastrika = findViewById(R.id.bhastrika);
        sheetali = findViewById(R.id.sheetali);
        ujjayi = findViewById(R.id.ujjayi);
        mbreathing = findViewById(R.id.mbreathing);
        (findViewById(R.id.reset_button)).setVisibility(4);
        avTitle = findViewById(R.id.avTitle);

        avTitle.setText(R.string.app_name);

        FirebaseApp.initializeApp(this);

        this.anulom_vilom.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), ActivityAnulomVilom.class));
            }
        });
        this.kapalbhati.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), KapalbhatiActivity.class));
            }
        });
        this.bhramari.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), BhramariActivity.class));
            }
        });
        this.surya_bhedana.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), SuryaBhedanaActivity.class));
            }
        });
        this.chandra_bhedana.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), ChandraBhedanaActivity.class));
            }
        });
        this.bhastrika.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), BhastrikaActivity.class));
            }
        });
        this.sheetali.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), SheetaliActivity.class));
            }
        });
        this.ujjayi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), UjjayiActivity.class));
            }
        });
        this.mbreathing.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ActivityPranayam activityPranayam = ActivityPranayam.this;
                activityPranayam.startActivity(new Intent(activityPranayam.getApplicationContext(), MBreathingActivity.class));
            }
        });
    }

    public static void initializeFirebase(String path){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        report = database.getReference(MainActivity.androidId + '/' + path);


        report.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Prefs.putInt(path, (int)dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    public void onBackButtonClicked(View view) {
        navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
    }
}
