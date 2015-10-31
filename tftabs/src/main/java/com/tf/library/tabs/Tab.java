package com.tf.library.tabs;

import android.view.View;

/**
 * Created by kamran on 10/27/15.
 */
public interface Tab {
    View getView();

    boolean isSelected();

    void setSelected(boolean selected);

    CharSequence getTitle();

    void setTitle(CharSequence title);

    void setBackgroundColor(int color);

    void setTitleColor(int color);

    void setSelectionColor(int color);
}
