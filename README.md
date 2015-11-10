tfTabs
======

[ ![Download](https://api.bintray.com/packages/tfkamran/maven/tf-tabs-android/images/download.svg) ](https://bintray.com/tfkamran/maven/tf-tabs-android/_latestVersion)
[![Build Status](https://travis-ci.org/tfKamran/tf-tabs-android.svg?branch=master)](https://travis-ci.org/tfKamran/tf-tabs-android)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/9b40dac2977445f2b38e59ebeaa88da6)](https://www.codacy.com/app/team-radiant/tf-tabs-android)

A clone of `ActionBarTabs` in Android but is compatible with `Toolbar`

![Screenshot](./Screenshot.png)

You may get this library by adding the following in the dependency section of your `build.gradle` file:

    compile 'com.tf.library.tabs:tftabs:2.1.0'

Or you may copy the following files from the given example code into your project:

    src/com.tf.library.tabs.Tab.java
    src/com.tf.library.tabs.TabsHolder.java
    res/layout/tab.xml
    
Add it in your layout xml file:

    <com.tf.library.tabs.TabsHolder
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/toolbar"
        app:backgroundColor="@color/toolbar_color"
        app:titleColor="@android:color/white"
        app:titleInactiveColor="@android:color/darker_gray"
        app:selectionColor="@color/accent_color"
        app:selectionVisible="true" />

Add a few lines in your activity code:

    // Set up the tabs
    tabsHolder = (TabsHolder) findViewById(R.id.tabs);
    tabsHolder.setViewPager(mViewPager);

    // Optionally setup the colors and properties programmatically
    tabsHolder.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
    tabsHolder.setTitleColor(Color.WHITE);
    tabsHolder.setTitleInactiveColor(Color.GRAY);
    tabsHolder.setSelectionColor(getResources().getColor(R.color.accent_color));
    tabsHolder.setSelectionVisible(true);

    // When swiping between different sections, select the corresponding tab.
    mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            tabsHolder.setCurrentTabIndex(position);
        }
    });

And if you wish to have icons on your tabs:

    public class SectionsPagerAdapter extends TabsPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Drawable getPageIcon(int position) {
            return getDrawable(R.drawable.ic_launcher);
        }

        @Override
        public Drawable getPageInactiveIcon(int position) {
            Drawable pageIcon = getDrawable(R.drawable.ic_launcher).mutate();

            pageIcon.setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);

            return pageIcon;
        }

        ...

    }

If you don't wish to have icons in the tabs, you may use any other derivatives of `PagerAdapter`.

And you're done!

**Note**: Before `tabs.setViewPager(mViewPager);` you must have your `FragmentPagerAdapter` in your `ViewPager`

Tags: #ActionBarTab, #Tabs, #Android, #Toolbar, #TabsLibrary
