package com.tf.library.tabs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by kamran on 10/29/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TabTest {

    private ViewPager viewPager;
    private TabsHolder tabsHolder;

    @Before
    public void setup() {
        viewPager = new ViewPager(RuntimeEnvironment.application.getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        tabsHolder = new TabsHolder(RuntimeEnvironment.application.getApplicationContext());

        tabsHolder.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabsHolder.setCurrentTabIndex(position);
            }
        });
    }

    @Test
    public void clickingOnATab_shouldChangeViewPagerCurrentItem() {
        tabsHolder.getChildAt(2).performClick();

        assertEquals(2, viewPager.getCurrentItem());
    }

    @Test
    public void changingViewPagerCurrentItem_shouldChangeTabSelection() {
        viewPager.setCurrentItem(1);

        assertEquals(1, tabsHolder.getCurrentTabIndex());
    }

    @Test
    public void changingTabSelection_shouldChangeViewPagerCurrentItem() {
        tabsHolder.setCurrentTabIndex(2);

        assertEquals(2, viewPager.getCurrentItem());
    }

    @Test
    public void changingTabSelection_shouldReflectOnUI() {
        int selectionColor = RuntimeEnvironment.application.getResources().getColor(R.color.primary_material_light);

        Drawable selectionDrawable = new ColorDrawable(selectionColor);

        tabsHolder.setSelectionColor(selectionColor);
        tabsHolder.setCurrentTabIndex(0);

        assertEquals(selectionDrawable, tabsHolder.getChildAt(0).findViewById(R.id.selection).getBackground());
    }

    @Test
    public void changingBackgroundColor_shouldReflectOnUI() {
        int backgroundColor = Color.WHITE;

        Drawable backgroundDrawable = new ColorDrawable(backgroundColor);

        tabsHolder.setBackgroundColor(backgroundColor);

        assertEquals(backgroundDrawable, tabsHolder.getChildAt(0).getBackground());
    }

    @Test
    public void changingTitleColor_shouldReflectOnUI() {
        int titleColor = RuntimeEnvironment.application.getResources().getColor(R.color.primary_material_light);

        tabsHolder.setTitleColor(titleColor);

        assertEquals(titleColor, ((TextView) tabsHolder.getChildAt(0).findViewById(R.id.lblTitle)).getTextColors().getDefaultColor());
    }

    @Test
    public void changingSelectionColor_shouldReflectOnUI() {
        int selectionColor = RuntimeEnvironment.application.getResources().getColor(R.color.primary_material_light);

        Drawable selectionDrawable = new ColorDrawable(selectionColor);

        tabsHolder.setSelectionColor(selectionColor);
        tabsHolder.setCurrentTabIndex(0);

        assertEquals(selectionDrawable, tabsHolder.getChildAt(0).findViewById(R.id.selection).getBackground());
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    };
}
