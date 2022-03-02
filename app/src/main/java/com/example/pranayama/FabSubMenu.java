package com.example.pranayama;

import android.widget.LinearLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabSubMenu {
    public boolean closeSubMenusFab(LinearLayout layoutFabInfo, LinearLayout layoutFabBenefits, LinearLayout layoutFabHelp, FloatingActionButton read_more) {
        layoutFabBenefits.setVisibility(4);
        layoutFabHelp.setVisibility(4);
        layoutFabInfo.setVisibility(4);
        read_more.setImageResource(R.drawable.add);
        return false;
    }

    public boolean openSubMenusFab(LinearLayout layoutFabInfo, LinearLayout layoutFabBenefits, LinearLayout layoutFabHelp, FloatingActionButton read_more) {
        layoutFabBenefits.setVisibility(0);
        layoutFabHelp.setVisibility(0);
        layoutFabInfo.setVisibility(0);
        read_more.setImageResource(R.drawable.cancel);
        return true;
    }
}
