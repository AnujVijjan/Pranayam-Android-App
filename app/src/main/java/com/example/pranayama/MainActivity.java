package com.example.pranayama;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.pixplicity.easyprefs.library.Prefs;

import org.jetbrains.annotations.NotNull;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static String androidId;
    public static boolean start;
    DrawerLayout drawer;
//    private AdView mAdView;
    ImageView ib_dower;
    Switch nightModeSwitch;
    GifImageView gifImageView;
    CardView pranayam;
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        ((TextView) findViewById(R.id.avTitle)).setText(R.string.menu_home);

        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();


        setupDrawer();


        ib_dower = findViewById(R.id.back_button);
        ib_dower.setImageResource(R.drawable.ic_menu_24dp);

        findViewById(R.id.reset_button).setVisibility(View.INVISIBLE);

        gifImageView = findViewById(R.id.gifImageVew);

        pranayam = findViewById(R.id.pranayama_card);
        pranayam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityPranayam.class));
            }
        });

        nightModeSwitch = this.navigationView.getMenu().findItem(R.id.nav_night_mode).getActionView().findViewById(R.id.night_mode_switch);

        checkNightModeActivated();

        androidId = Settings.Secure.getString(getContentResolver(), "android_id");

        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Prefs.putBoolean("state", true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    Prefs.putBoolean("state", false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        ib_dower.setOnClickListener(view -> drawer.openDrawer(GravityCompat.START));
    }

    private void setupDrawer() {
        drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, null, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
    }

    public void onBackButtonClicked(View view) {}

    public void checkNightModeActivated(){

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                nightModeSwitch.setChecked(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                gifImageView.setImageResource(R.drawable.intro_night);
                Prefs.putBoolean("state", true);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                nightModeSwitch.setChecked(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                gifImageView.setImageResource(R.drawable.intro);
                Prefs.putBoolean("state", false);
                break;
        }

    }

    public void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_mudras)
            startActivity(new Intent(this, MudrasActivity.class));
        else if(id == R.id.nav_sitting_pose)
            startActivity(new Intent(this, SittingPoseActivity.class));

        return false;
    }
}
