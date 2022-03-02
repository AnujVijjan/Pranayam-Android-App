// Generated by view binder compiler. Do not edit!
package com.example.pranayama.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.pranayama.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import pl.droidsonroids.gif.GifImageView;

public final class ContentMainBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageView airplane;

  @NonNull
  public final ImageView bus;

  @NonNull
  public final ImageView bus1;

  @NonNull
  public final ImageView cycle;

  @NonNull
  public final GifImageView gifImageVew;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final CardView pranayamaCard;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final ImageView train;

  private ContentMainBinding(@NonNull ScrollView rootView, @NonNull ImageView airplane,
      @NonNull ImageView bus, @NonNull ImageView bus1, @NonNull ImageView cycle,
      @NonNull GifImageView gifImageVew, @NonNull ImageView imageView3,
      @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2,
      @NonNull CardView pranayamaCard, @NonNull TextView textView, @NonNull TextView textView2,
      @NonNull TextView textView3, @NonNull TextView textView4, @NonNull TextView textView6,
      @NonNull TextView textView7, @NonNull TextView textView8, @NonNull TextView textView9,
      @NonNull ImageView train) {
    this.rootView = rootView;
    this.airplane = airplane;
    this.bus = bus;
    this.bus1 = bus1;
    this.cycle = cycle;
    this.gifImageVew = gifImageVew;
    this.imageView3 = imageView3;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.pranayamaCard = pranayamaCard;
    this.textView = textView;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.textView4 = textView4;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.textView9 = textView9;
    this.train = train;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ContentMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContentMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.content_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContentMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.airplane;
      ImageView airplane = rootView.findViewById(id);
      if (airplane == null) {
        break missingId;
      }

      id = R.id.bus;
      ImageView bus = rootView.findViewById(id);
      if (bus == null) {
        break missingId;
      }

      id = R.id.bus1;
      ImageView bus1 = rootView.findViewById(id);
      if (bus1 == null) {
        break missingId;
      }

      id = R.id.cycle;
      ImageView cycle = rootView.findViewById(id);
      if (cycle == null) {
        break missingId;
      }

      id = R.id.gifImageVew;
      GifImageView gifImageVew = rootView.findViewById(id);
      if (gifImageVew == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = rootView.findViewById(id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = rootView.findViewById(id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = rootView.findViewById(id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.pranayama_card;
      CardView pranayamaCard = rootView.findViewById(id);
      if (pranayamaCard == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = rootView.findViewById(id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = rootView.findViewById(id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = rootView.findViewById(id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = rootView.findViewById(id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = rootView.findViewById(id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = rootView.findViewById(id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = rootView.findViewById(id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = rootView.findViewById(id);
      if (textView9 == null) {
        break missingId;
      }

      id = R.id.train;
      ImageView train = rootView.findViewById(id);
      if (train == null) {
        break missingId;
      }

      return new ContentMainBinding((ScrollView) rootView, airplane, bus, bus1, cycle, gifImageVew,
          imageView3, linearLayout, linearLayout2, pranayamaCard, textView, textView2, textView3,
          textView4, textView6, textView7, textView8, textView9, train);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}