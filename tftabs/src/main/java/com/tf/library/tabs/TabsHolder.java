package com.tf.library.tabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TabsHolder extends LinearLayout {
    private Context context;
    private ViewPager pager;
    private ArrayList<Tab> tabs;
    private int backgroundColor;
    private int selectionColor;

    public TabsHolder(Context context) {
        super(context);

        initialize(context);
    }

    public TabsHolder(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TabsHolder,
                0, 0);

        try {
            setTitleColor(attrs.getAttributeIntValue(R.styleable.TabsHolder_titleColor, Color.TRANSPARENT));

            setBackgroundColor(attrs.getAttributeIntValue(R.styleable.TabsHolder_backgroundColor, Color.TRANSPARENT));

            setBackgroundColor(attrs.getAttributeIntValue(R.styleable.TabsHolder_selectionColor, Color.TRANSPARENT));
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }

        initialize(context);
    }

    private void initialize(Context context) {
        setOrientation(LinearLayout.HORIZONTAL);

        this.context = context;
    }

    public void setViewPager(ViewPager pager) {
        this.pager = pager;

        int count = pager.getAdapter().getCount();
        tabs = new ArrayList<>(count);

        for (int position = 0; position < count; position++) {
            Tab tab = new TabView(context, position, pager);
            tab.setSelected(position == 0);

            tabs.add(tab);
            addView(tab.getView());

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab.getView().getLayoutParams();
            layoutParams.weight = 1;
            tab.getView().setLayoutParams(layoutParams);
        }
    }

    public int getCurrentTabIndex() {
        int count = tabs.size();
        for (int index = 0; index < count; index++)
            if (tabs.get(index).isSelected())
                return index;

        return -1;
    }

    public void setCurrentTabIndex(int position) {
        int count = tabs.size();
        for (int index = 0; index < count; index++)
            tabs.get(index).setSelected(index == position);

        if (pager.getCurrentItem() != position)
            pager.setCurrentItem(position);
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

    private class TabView implements Tab {
        private View tabView;
        private boolean selected;

        public TabView(Context context, final int position, ViewPager pager) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            tabView = layoutInflater.inflate(R.layout.tab, null);

            setTitle(pager.getAdapter().getPageTitle(position));

            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TabsHolder.this.pager.setCurrentItem(position);
                }
            });
        }

        @Override
        public View getView() {
            return tabView;
        }

        @Override
        public boolean isSelected() {
            return selected;
        }

        @Override
        public void setSelected(boolean selected) {
            this.selected = selected;

            if (selected)
                tabView.findViewById(R.id.selection).setVisibility(View.VISIBLE);
            else
                tabView.findViewById(R.id.selection).setVisibility(View.GONE);
        }

        @Override
        public CharSequence getTitle() {
            return ((TextView) tabView.findViewById(R.id.lblTitle)).getText();
        }

        @Override
        public void setTitle(CharSequence title) {
            ((TextView) tabView.findViewById(R.id.lblTitle)).setText(title);
        }

        @Override
        public void setBackgroundColor(int color) {
            tabView.setBackgroundColor(color);
        }

        @Override
        public void setTitleColor(int color) {
            ((TextView) tabView.findViewById(R.id.lblTitle)).setTextColor(color);
        }

        @Override
        public void setSelectionColor(int color) {
            tabView.findViewById(R.id.selection).setBackgroundColor(color);
        }
    }
}