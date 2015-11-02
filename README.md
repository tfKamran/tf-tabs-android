tfTabs
======

[ ![Download](https://api.bintray.com/packages/tfkamran/maven/tf-tabs-android/images/download.svg) ](https://bintray.com/tfkamran/maven/tf-tabs-android/_latestVersion)

A clone of `ActionBarTabs` in Android but is compatible with `Toolbar`

![Screenshot](./Screenshot.png)

You may get this library by adding the following in the dependency section of your `build.gradle` file:

    compile 'com.tf.library.tabs:tftabs:1.1.0'

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
        app:titleInactiveColor="@color/accent_color"
        app:selectionColor="@color/accent_color" />

Add a few lines in your activity code:

    // Set up the tabs
    tabsHolder = (TabsHolder) findViewById(R.id.tabs);
    tabsHolder.setViewPager(mViewPager);

    // Optionally setup the colors programmatically
    tabsHolder.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
    tabsHolder.setTitleColor(Color.WHITE);
    tabsHolder.setTitleInactiveColor(getResources().getColor(R.color.accent_color));
    tabsHolder.setSelectionColor(getResources().getColor(R.color.accent_color));

    // When swiping between different sections, select the corresponding tab.
    mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            tabsHolder.setCurrentTabIndex(position);
        }
    });
    
And you're done!

**Note**: Before `tabs.setViewPager(mViewPager);` you must have your `FragmentPagerAdapter` in your `ViewPager`

Tags: #ActionBarTab, #Tabs, #Android, #Toolbar, #TabsLibrary
