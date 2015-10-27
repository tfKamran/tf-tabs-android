package com.tf.library.tabs;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Tabs extends LinearLayout {
    private Context context;
    private ArrayList<Tab> tabs;
    private int backgroundColor;
    private int selectionColor;

    public Tabs(Context context) {
        super(context);

        initialize(context);
    }

    public Tabs(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize(context);
    }

    private void initialize(Context context) {
        setOrientation(LinearLayout.HORIZONTAL);

        this.context = context;
    }

    public void setViewPager(ViewPager pager) {
        int count = pager.getAdapter().getCount();
        tabs = new ArrayList<Tab>(count);

        for (int position = 0; position < count; position++) {
            Tab tab = new Tab(context, position, pager);
            tab.setSelected(position == 0);

            tabs.add(tab);
            addView(tab.getView());

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab.getView().getLayoutParams();
            layoutParams.weight = 1;
            tab.getView().setLayoutParams(layoutParams);
        }
    }

    public void setCurrentTab(int position) {
        int count = tabs.size();
        for (int index = 0; index < count; index++)
            tabs.get(index).setSelected(index == position);
    }

    public void setBackgroundColor(int color) {
        backgroundColor = color;

        for (Tab tab : tabs)
            tab.setBackgroundColor(color);
    }

    public void setTitleColor(int color) {
        backgroundColor = color;

        for (Tab tab : tabs)
            tab.setTitleColor(color);
    }

    public void setSelectionColor(int color) {
        selectionColor = color;

        for (Tab tab : tabs)
            tab.setSelectionColor(color);
    }

    private class Tab {
        private View tabView;

        public Tab(Context context, final int position, final ViewPager pager) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            tabView = layoutInflater.inflate(R.layout.tab, null);

            setTitle(pager.getAdapter().getPageTitle(position));

            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pager.setCurrentItem(position);
                }
            });
        }

        public View getView() {
            return tabView;
        }

        public void setSelected(boolean selected) {
            if (selected)
                tabView.findViewById(R.id.selection).setVisibility(View.VISIBLE);
            else
                tabView.findViewById(R.id.selection).setVisibility(View.GONE);
        }

        public CharSequence getTitle() {
            return ((TextView) tabView.findViewById(R.id.lblTitle)).getText();
        }

        public void setTitle(CharSequence title) {
            ((TextView) tabView.findViewById(R.id.lblTitle)).setText(title);
        }

        public void setBackgroundColor(int color) {
            tabView.setBackgroundColor(color);
        }

        public void setTitleColor(int color) {
            ((TextView) tabView.findViewById(R.id.lblTitle)).setTextColor(color);
        }

        public void setSelectionColor(int color) {
            tabView.findViewById(R.id.selection).setBackgroundColor(color);
        }
    }
}