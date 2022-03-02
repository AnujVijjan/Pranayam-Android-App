// Generated by view binder compiler. Do not edit!
package com.example.pranayama.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.pranayama.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMbreathingBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView exhale;

  @NonNull
  public final FrameLayout fabFrame;

  @NonNull
  public final RelativeLayout fabReadMore;

  @NonNull
  public final TextView hold;

  @NonNull
  public final TextView hr;

  @NonNull
  public final TextView inhale;

  @NonNull
  public final LinearLayout layoutFabBenefits;

  @NonNull
  public final LinearLayout layoutFabHelp;

  @NonNull
  public final LinearLayout layoutFabInfo;

  @NonNull
  public final TextView leftButton;

  @NonNull
  public final TextView min;

  @NonNull
  public final Button playButton;

  @NonNull
  public final FloatingActionButton readMore;

  @NonNull
  public final Button reportButton;

  @NonNull
  public final NumberPicker rounds;

  @NonNull
  public final TextView sec;

  private ActivityMbreathingBinding(@NonNull LinearLayout rootView, @NonNull TextView exhale,
      @NonNull FrameLayout fabFrame, @NonNull RelativeLayout fabReadMore, @NonNull TextView hold,
      @NonNull TextView hr, @NonNull TextView inhale, @NonNull LinearLayout layoutFabBenefits,
      @NonNull LinearLayout layoutFabHelp, @NonNull LinearLayout layoutFabInfo,
      @NonNull TextView leftButton, @NonNull TextView min, @NonNull Button playButton,
      @NonNull FloatingActionButton readMore, @NonNull Button reportButton,
      @NonNull NumberPicker rounds, @NonNull TextView sec) {
    this.rootView = rootView;
    this.exhale = exhale;
    this.fabFrame = fabFrame;
    this.fabReadMore = fabReadMore;
    this.hold = hold;
    this.hr = hr;
    this.inhale = inhale;
    this.layoutFabBenefits = layoutFabBenefits;
    this.layoutFabHelp = layoutFabHelp;
    this.layoutFabInfo = layoutFabInfo;
    this.leftButton = leftButton;
    this.min = min;
    this.playButton = playButton;
    this.readMore = readMore;
    this.reportButton = reportButton;
    this.rounds = rounds;
    this.sec = sec;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMbreathingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMbreathingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_mbreathing, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMbreathingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.exhale;
      TextView exhale = rootView.findViewById(id);
      if (exhale == null) {
        break missingId;
      }

      id = R.id.fabFrame;
      FrameLayout fabFrame = rootView.findViewById(id);
      if (fabFrame == null) {
        break missingId;
      }

      id = R.id.fabReadMore;
      RelativeLayout fabReadMore = rootView.findViewById(id);
      if (fabReadMore == null) {
        break missingId;
      }

      id = R.id.hold;
      TextView hold = rootView.findViewById(id);
      if (hold == null) {
        break missingId;
      }

      id = R.id.hr;
      TextView hr = rootView.findViewById(id);
      if (hr == null) {
        break missingId;
      }

      id = R.id.inhale;
      TextView inhale = rootView.findViewById(id);
      if (inhale == null) {
        break missingId;
      }

      id = R.id.layoutFabBenefits;
      LinearLayout layoutFabBenefits = rootView.findViewById(id);
      if (layoutFabBenefits == null) {
        break missingId;
      }

      id = R.id.layoutFabHelp;
      LinearLayout layoutFabHelp = rootView.findViewById(id);
      if (layoutFabHelp == null) {
        break missingId;
      }

      id = R.id.layoutFabInfo;
      LinearLayout layoutFabInfo = rootView.findViewById(id);
      if (layoutFabInfo == null) {
        break missingId;
      }

      id = R.id.leftButton;
      TextView leftButton = rootView.findViewById(id);
      if (leftButton == null) {
        break missingId;
      }

      id = R.id.min;
      TextView min = rootView.findViewById(id);
      if (min == null) {
        break missingId;
      }

      id = R.id.play_button;
      Button playButton = rootView.findViewById(id);
      if (playButton == null) {
        break missingId;
      }

      id = R.id.read_more;
      FloatingActionButton readMore = rootView.findViewById(id);
      if (readMore == null) {
        break missingId;
      }

      id = R.id.report_button;
      Button reportButton = rootView.findViewById(id);
      if (reportButton == null) {
        break missingId;
      }

      id = R.id.rounds;
      NumberPicker rounds = rootView.findViewById(id);
      if (rounds == null) {
        break missingId;
      }

      id = R.id.sec;
      TextView sec = rootView.findViewById(id);
      if (sec == null) {
        break missingId;
      }

      return new ActivityMbreathingBinding((LinearLayout) rootView, exhale, fabFrame, fabReadMore,
          hold, hr, inhale, layoutFabBenefits, layoutFabHelp, layoutFabInfo, leftButton, min,
          playButton, readMore, reportButton, rounds, sec);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
