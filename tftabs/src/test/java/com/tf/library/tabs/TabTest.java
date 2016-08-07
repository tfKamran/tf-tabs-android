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

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by kamran on 10/29/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TabTest {

    private ArrayList<String> pageTitles = new ArrayList<>();
    private ViewPager viewPager;
    private TabsHolder tabsHolder;

    @Before
    public void setUp() {
        pageTitles.add("Page 1");
        pageTitles.add("Page 2");
        pageTitles.add("Page 3");

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
        tabsHolder.setSelectionVisible(true);
        tabsHolder.setCurrentTabIndex(0);

        assertEquals(View.VISIBLE, tabsHolder.getChildAt(0).findViewById(R.id.selection).getVisibility());
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
        int titleColor = Color.BLACK;

        tabsHolder.setTitleColor(titleColor);

        assertEquals(titleColor, ((TextView) tabsHolder.getChildAt(0).findViewById(R.id.lblTitle)).getTextColors().getDefaultColor());
    }

    @Test
    public void changingTitleInactiveColor_shouldReflectOnUI() {
        int titleInactiveColor = Color.BLACK;

        tabsHolder.setTitleInactiveColor(titleInactiveColor);

        assertEquals(titleInactiveColor, ((TextView) tabsHolder.getChildAt(1).findViewById(R.id.lblTitle)).getTextColors().getDefaultColor());
    }

    @Test
    public void changingTitleColor_shouldNotReflectOnInactiveTabs() {
        int titleColor = Color.BLACK;

        tabsHolder.setTitleInactiveColor(Color.WHITE);
        tabsHolder.setTitleColor(titleColor);

        assertTrue(titleColor != ((TextView) tabsHolder.getChildAt(1).findViewById(R.id.lblTitle)).getTextColors().getDefaultColor());
    }

    @Test
    public void changingSelectionColor_shouldReflectOnUI() {
        int selectionColor = Color.RED;

        Drawable selectionDrawable = new ColorDrawable(selectionColor);

        tabsHolder.setSelectionColor(selectionColor);
        tabsHolder.setCurrentTabIndex(0);

        assertEquals(selectionDrawable, tabsHolder.getChildAt(0).findViewById(R.id.selection).getBackground());
    }

    @Test
    public void changingTitle_shouldReflectOnUI() {
        String originalPageTitle = pageTitles.get(0);

        String modifiedTitle = "Page Title Modified";
        pageTitles.set(0, modifiedTitle);

        pagerAdapter.notifyDataSetChanged();

        String tabTitleInUI = ((TextView) tabsHolder.getChildAt(0)
                .findViewById(R.id.lblTitle)).getText().toString();

        assertEquals(tabTitleInUI, modifiedTitle);

        pageTitles.set(0, originalPageTitle);
    }

    @Test
    public void addNewTab_shouldReflectOnUI() {
        String tabTitle = "New Tab";
        pageTitles.add(tabTitle);

        pagerAdapter.notifyDataSetChanged();

        String tabTitleInUI = ((TextView) tabsHolder.getChildAt(3).findViewById(R.id.lblTitle)).getText().toString();
        assertEquals(tabTitleInUI, tabTitle);

        pageTitles.remove(pageTitles.size() - 1);
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return pageTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitles.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    };
}
