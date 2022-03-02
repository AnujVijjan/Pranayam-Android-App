// Generated by view binder compiler. Do not edit!
package com.example.pranayama.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.pranayama.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityBhramariCountDownBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView hr;

  @NonNull
  public final TextView info;

  @NonNull
  public final TextView infoTime;

  @NonNull
  public final TextView min;

  @NonNull
  public final ImageView pranayamcurrentpose;

  @NonNull
  public final TextView sec;

  @NonNull
  public final Button startPauseButton;

  private ActivityBhramariCountDownBinding(@NonNull LinearLayout rootView, @NonNull TextView hr,
      @NonNull TextView info, @NonNull TextView infoTime, @NonNull TextView min,
      @NonNull ImageView pranayamcurrentpose, @NonNull TextView sec,
      @NonNull Button startPauseButton) {
    this.rootView = rootView;
    this.hr = hr;
    this.info = info;
    this.infoTime = infoTime;
    this.min = min;
    this.pranayamcurrentpose = pranayamcurrentpose;
    this.sec = sec;
    this.startPauseButton = startPauseButton;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBhramariCountDownBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBhramariCountDownBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_bhramari_count_down, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBhramariCountDownBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.hr;
      TextView hr = rootView.findViewById(id);
      if (hr == null) {
        break missingId;
      }

      id = R.id.info;
      TextView info = rootView.findViewById(id);
      if (info == null) {
        break missingId;
      }

      id = R.id.infoTime;
      TextView infoTime = rootView.findViewById(id);
      if (infoTime == null) {
        break missingId;
      }

      id = R.id.min;
      TextView min = rootView.findViewById(id);
      if (min == null) {
        break missingId;
      }

      id = R.id.pranayamcurrentpose;
      ImageView pranayamcurrentpose = rootView.findViewById(id);
      if (pranayamcurrentpose == null) {
        break missingId;
      }

      id = R.id.sec;
      TextView sec = rootView.findViewById(id);
      if (sec == null) {
        break missingId;
      }

      id = R.id.start_pause_button;
      Button startPauseButton = rootView.findViewById(id);
      if (startPauseButton == null) {
        break missingId;
      }

      return new ActivityBhramariCountDownBinding((LinearLayout) rootView, hr, info, infoTime, min,
          pranayamcurrentpose, sec, startPauseButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
