package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.utility.AppInstalled;
import com.treebricks.ewuhub.utility.ChromeCustomTab;
import com.treebricks.ewuhub.utility.EwuHubHelper;
import com.treebricks.ewuhub.view.AcademicCalendarEvent;
import com.treebricks.ewuhub.view.AcademicCalendarModel;
import com.treebricks.ewuhub.view.NewsFeedModel;
import com.treebricks.ewuhub.view.NoticeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ApplicationHome extends AppCompatActivity {
    private ProgressDialog progressDialog;
    int versionCode;
    ChromeCustomTab chromeCustomTab;
    private boolean doubleBackToExitPressedOnce;
    private Drawer homePageDrawer = null;
    private AppInstalled chromeBrowser;

    SharedPreferences getPrefs;

    TextView calendarEvent;
    TextView calendarDay;
    TextView calendarDate;
    TextView noticeTitle;
    TextView noticeDate;
    TextView feedTitle;
    TextView feedDate;
    CardView noticeCard;
    CardView feedCard;

    CollapsingToolbarLayout collapsingToolbarLayout;

    // Gson
    Gson gson;

    FirebaseDatabase databaseRef;

    DatabaseReference noticeReference;
    DatabaseReference feedReference;

    ArrayList<NewsFeedModel> allFeeds = new ArrayList<>();
    ArrayList<NoticeView> allNotices = new ArrayList<>();

    String noticeUrl;
    String feedData;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        // Intro Activity Start
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // Create a new Boolean and preference and set it true
                boolean isFirstRun = getPrefs.getBoolean("intro_first_start", true);
                // if the activity never start before
                if (isFirstRun) {
                    Intent i = new Intent(ApplicationHome.this, ApplicationIntro.class);
                    startActivity(i);
                    finish();
                    // make a new preference editor
                    SharedPreferences.Editor e = getPrefs.edit();
                    // edit preference to make it false because we don't want this run again
                    e.putBoolean("intro_first_start", false);
                    e.apply();
                }
            }
        });
        t.start();

        setContentView(R.layout.activity_application_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LogUtils.d("On Create ApplicationHome");

        databaseRef = FirebaseDatabase.getInstance();

        noticeReference = databaseRef.getReference("notices");
        feedReference = databaseRef.getReference("newsfeeds");

        // Gson initialization
        gson = new Gson();

        feedCard = findViewById(R.id.newsfeed_card);
        feedDate = findViewById(R.id.feeddate);
        feedTitle = findViewById(R.id.feedtitle);

        noticeCard = findViewById(R.id.home_notice_card);
        noticeDate = findViewById(R.id.noticedate);
        noticeTitle = findViewById(R.id.noticetitle);

        calendarEvent = findViewById(R.id.calendar_event);
        calendarDay = findViewById(R.id.calendar_day);
        calendarDate = findViewById(R.id.calendar_date);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle("EwuHub");
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(ApplicationHome.this, android.R.color.transparent));


        doubleBackToExitPressedOnce = false;
        chromeCustomTab = new ChromeCustomTab(getApplicationContext(), ApplicationHome.this);

        final KenBurnsView homeImage = findViewById(R.id.kbv_image);

        versionCode = getPrefs.getInt("version_code", 1);

        newVersion();

        chromeBrowser = new AppInstalled(this);

        // Navigation Drawer Header
        AccountHeader homePageAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.main_drawer)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .build();
        // Navigation Drawer
        homePageDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(homePageAccountHeader)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIcon(R.drawable.salvation).withName(R.string.advising_helper).withIdentifier(1),
                        new PrimaryDrawerItem().withIcon(R.drawable.clipboard).withName(R.string.course_list).withIdentifier(2),
                        new PrimaryDrawerItem().withIcon(R.drawable.diploma).withName(R.string.result).withIdentifier(3),
                        new PrimaryDrawerItem().withIcon(R.drawable.calendar).withName(R.string.academic_calender).withIdentifier(4),
                        new PrimaryDrawerItem().withIcon(R.drawable.notes).withName(R.string.notice_board).withIdentifier(5),
                        new PrimaryDrawerItem().withIcon(R.drawable.library).withName(R.string.ewu_library).withIdentifier(6),
                        new PrimaryDrawerItem().withIcon(R.drawable.torch).withName(R.string.ewuspirit).withIdentifier(7),
                        new PrimaryDrawerItem().withIcon(R.drawable.ic_rss_feed_black_24dp).withName(R.string.newsfeed).withIdentifier(8)
//                        new PrimaryDrawerItem().withIcon(R.drawable.ic_calendar_star_symbol).withName(R.string.events).withIdentifier(10)
                )
                .addStickyDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.preferences).withIcon(R.drawable.settings).withIdentifier(9)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            switch ((int) drawerItem.getIdentifier()) {
                                case 1: {
                                    Intent courseInputIntent = new Intent(ApplicationHome.this, CoursesInput.class);
                                    startActivity(courseInputIntent);
                                    break;
                                }
                                case 2: {
                                    Intent i = new Intent(ApplicationHome.this, AdvisingListViewer.class);
                                    startActivity(i);
                                    break;
                                }
                                case 3: {
                                    progressDialog = new ProgressDialog(ApplicationHome.this);
                                    progressDialog.setMessage("Connecting to the Result Server.");
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.setIndeterminate(true);
                                    progressDialog.show();

                                    if (isInternetAvailable()) {
                                        if (chromeBrowser.isChromeEnabled("com.android.chrome")) {
                                            chromeCustomTab.runOnCustomTab("http://result.ewubd.edu/");
                                            progressDialog.hide();
                                            progressDialog.cancel();
                                        } else {
                                            Intent i = new Intent(ApplicationHome.this, AllWebView.class);
                                            Bundle sentData = new Bundle();
                                            sentData.putString("URL", "http://result.ewubd.edu/");
                                            sentData.putString("AdvisingSheet", "No");
                                            i.putExtras(sentData);
                                            progressDialog.hide();
                                            progressDialog.cancel();
                                            startActivity(i);
                                        }
                                    } else {
                                        progressDialog.hide();
                                        progressDialog.cancel();
                                        Toast.makeText(ApplicationHome.this, "You are not connected to internet", Toast.LENGTH_LONG).show();
                                    }

                                    break;
                                }
                                case 4: {
                                    Intent academicCalender = new Intent(ApplicationHome.this, AcademicCalendar.class);
                                    startActivity(academicCalender);
                                    break;
                                }
                                case 5: {
                                    progressDialog = new ProgressDialog(ApplicationHome.this);
                                    progressDialog.setMessage("Checking Internet Connection..");
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.setIndeterminate(true);
                                    progressDialog.show();

                                    if (isInternetAvailable()) {
                                        final Intent notice = new Intent(ApplicationHome.this, NoticeActivity.class);
                                        progressDialog.hide();
                                        progressDialog.cancel();
                                        startActivity(notice);
                                    } else {
                                        progressDialog.hide();
                                        progressDialog.cancel();
                                        Toast.makeText(ApplicationHome.this, "You are not connected to internet", Toast.LENGTH_LONG).show();
                                    }

                                    break;
                                }
                                case 6: {
                                    progressDialog = new ProgressDialog(ApplicationHome.this);
                                    progressDialog.setMessage("Going to Ewu Library...");
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.setIndeterminate(true);
                                    progressDialog.show();
                                    if (isInternetAvailable()) {
                                        if (chromeBrowser.isChromeEnabled("com.android.chrome")) {
                                            chromeCustomTab.runOnCustomTab("http://lib.ewubd.edu/");
                                            progressDialog.hide();
                                            progressDialog.cancel();
                                        } else {
                                            Intent i = new Intent(ApplicationHome.this, AllWebView.class);
                                            Bundle sentData = new Bundle();
                                            sentData.putString("URL", "http://lib.ewubd.edu/");
                                            sentData.putString("AdvisingSheet", "No");
                                            i.putExtras(sentData);
                                            progressDialog.hide();
                                            progressDialog.cancel();
                                            startActivity(i);
                                        }

                                    } else {
                                        progressDialog.hide();
                                        progressDialog.cancel();
                                        Toast.makeText(ApplicationHome.this, "You are not connected to internet", Toast.LENGTH_LONG).show();
                                    }

                                    break;
                                }
                                case 7: {
                                    Intent ewuSpirt = new Intent(ApplicationHome.this, EwuSpirit.class);
                                    startActivity(ewuSpirt);
                                    break;
                                }
                                case 8: {
                                    Intent newsfeed = new Intent(ApplicationHome.this, Newsfeed.class);
                                    startActivity(newsfeed);
                                    break;
                                }
                                case 9: {
                                    Intent i = new Intent(ApplicationHome.this, Preferences.class);
                                    startActivity(i);
                                    break;
                                }
                            }
                        }
                        return true;
                    }
                })
                .build();

        DrawerLayout drawerLayout = homePageDrawer.getDrawerLayout();

        // Drawer Toggle Listener
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                homeImage.pause();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                homeImage.pause();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                homeImage.resume();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_SETTLING)
                    homeImage.resume();
            }
        });

        populateCalendar();

        if (isInternetAvailable()) {
            Query feedQuery = feedReference.orderByKey();
            feedQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> notices = dataSnapshot.getChildren();
                    allFeeds.clear();
                    for (DataSnapshot data : notices) {
                        NewsFeedModel newsFeedModel = data.getValue(NewsFeedModel.class);
                        allFeeds.add(newsFeedModel);
                    }
                    Collections.reverse(allFeeds);
                    SharedPreferences.Editor editor = getPrefs.edit();
                    editor.putString("feed_title", allFeeds.get(0).getFeed_title());
                    editor.putString("feed_date", allFeeds.get(0).getFeed_date());
                    editor.putString("feed_data", allFeeds.get(0).getFeed_data());
                    editor.apply();

                    feedTitle.setText(getPrefs.getString("feed_title", "Sorry No Feed"));
                    feedDate.setText(getPrefs.getString("feed_date", "2016/11/22"));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Query noticeQuery = noticeReference.orderByKey();
            noticeQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> notices = dataSnapshot.getChildren();
                    allNotices.clear();
                    for (DataSnapshot data : notices) {
                        NoticeView noticeView = data.getValue(NoticeView.class);
                        allNotices.add(noticeView);
                    }
                    Collections.reverse(allNotices);

                    SharedPreferences.Editor editor = getPrefs.edit();
                    editor.putString("notice_title", allNotices.get(0).getNotice_title());
                    editor.putString("notice_date", allNotices.get(0).getNotice_date());
                    editor.putString("notice_url", allNotices.get(0).getNotice_url());
                    editor.apply();

                    noticeTitle.setText(getPrefs.getString("notice_title", "Sorry No Notice"));
                    noticeDate.setText(getPrefs.getString("notice_date", "2016/11/22"));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            feedTitle.setText(getPrefs.getString("feed_title", "Sorry No Feed"));
            feedDate.setText(getPrefs.getString("feed_date", "2016/11/22"));

            noticeTitle.setText(getPrefs.getString("notice_title", "Sorry No Notice"));
            noticeDate.setText(getPrefs.getString("notice_date", "2016/11/22"));

        }

        feedTitle.setText(getPrefs.getString("feed_title", "Sorry No Feed"));
        feedDate.setText(getPrefs.getString("feed_date", "2016/11/22"));

        noticeTitle.setText(getPrefs.getString("notice_title", "Sorry No Notice"));
        noticeDate.setText(getPrefs.getString("notice_date", "2016/11/22"));


        noticeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticeUrl = getPrefs.getString("notice_url", "NULL");
                if ("NULL".equals(noticeUrl)) {
                    Toast.makeText(ApplicationHome.this, "Sorry No Notice", Toast.LENGTH_SHORT).show();
                } else {
                    Intent webView = new Intent(ApplicationHome.this, NoticeWebViewer.class);
                    webView.putExtra("URL", noticeUrl);
                    startActivity(webView);
                }
            }
        });

        feedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedData = getPrefs.getString("feed_data", "NULL");
                if ("NULL".equals(feedData)) {
                    Toast.makeText(ApplicationHome.this, "Sorry No News Feed", Toast.LENGTH_SHORT).show();
                } else {
                    Intent markdownView = new Intent(ApplicationHome.this, FeedMarkDownView.class);
                    markdownView.putExtra("FEED_DATA", feedData);
                    startActivity(markdownView);
                }
            }
        });

        final int[] flag = {0};

        countDownTimer = new CountDownTimer(30000, 5000) {
            public void onTick(long millisUntilFinished) {
                if (flag[0] == 0) {
                    homeImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.home_one, null));
                    flag[0] = 1;
                } else {
                    homeImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.home_two, null));
                    flag[0] = 0;
                }
            }

            public void onFinish() {
                countDownTimer.start();
            }
        };
        countDownTimer.start();
    }

    private boolean isInternetAvailable() {
        return NetworkUtils.isConnected() && NetworkUtils.isAvailableByPing("8.8.8.8");
    }

    void populateCalendar() {
        String calendarJsonString;
        String educationLevel = getPrefs.getString("level", "undergrad");
        switch (educationLevel) {
            case "undergrad": {
                calendarJsonString = EwuHubHelper.readJSONStringFromFile(ApplicationHome.this, "undergraduate.json");
                break;
            }
            case "grad": {
                calendarJsonString = EwuHubHelper.readJSONStringFromFile(ApplicationHome.this, "graduate.json");
                break;
            }
            case "pundergrad": {
                calendarJsonString = EwuHubHelper.readJSONStringFromFile(ApplicationHome.this, "pharmacyundergraduate.json");
                break;
            }
            case "pgrad": {
                calendarJsonString = EwuHubHelper.readJSONStringFromFile(ApplicationHome.this, "pharmacygraduate.json");
                break;
            }
            default: {
                calendarJsonString = EwuHubHelper.readJSONStringFromFile(ApplicationHome.this, "undergraduate.json");
                break;
            }
        }
        setCalendarEvent(calendarJsonString);
    }

    private void setCalendarEvent(String calendarJsonString) {
        AcademicCalendarModel academicCalendar = new AcademicCalendarModel();

        // Gson converting Json String to Object and setting Actionbar title
        if (calendarJsonString != null && !"".equals(calendarJsonString)) {
            academicCalendar = gson.fromJson(calendarJsonString, AcademicCalendarModel.class);
        }

        List<AcademicCalendarEvent> allEvents = academicCalendar.getAcademicCalendarEvents();

        int calendarIndex = nextEventIndex(allEvents);

        calendarDate.setText(allEvents.get(calendarIndex).getCalDate());
        calendarDay.setText(allEvents.get(calendarIndex).getCalDay());
        calendarEvent.setText(allEvents.get(calendarIndex).getCalEvent());
    }

    public void newVersion() {
        int version = 0;
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // Huh? Really?
        }
        // Create a new Boolean and preference and set it true
        if (version > versionCode) {
            SharedPreferences.Editor e = getPrefs.edit();
            // edit preference to make it false because we don't want this run again
            e.putInt("version_code", version);
            e.apply();
            EwuHubHelper.copyDatabase(ApplicationHome.this, "CoursesDatabase.db");
            EwuHubHelper.copyJsonFile(ApplicationHome.this, "graduate.json");
            EwuHubHelper.copyJsonFile(ApplicationHome.this, "undergraduate.json");
            EwuHubHelper.copyJsonFile(ApplicationHome.this, "pharmacyundergraduate.json");
            EwuHubHelper.copyJsonFile(ApplicationHome.this, "pharmacygraduate.json");
        }
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "Press again to Exit.", Toast.LENGTH_SHORT);
        if (homePageDrawer.isDrawerOpen()) {
            homePageDrawer.closeDrawer();
        } else {
            if (doubleBackToExitPressedOnce) {
                toast.cancel();
                super.onBackPressed();
            }
            this.doubleBackToExitPressedOnce = true;
            toast.show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }

    public int nextEventIndex(List<AcademicCalendarEvent> allEvents) {
        long differenceDates;
        int calendarIndex = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            Date currentDate;
            Date nextDate;
            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            String sCurrentDate = dates.format(calendar.getTime());
            currentDate = dates.parse(sCurrentDate);

            for (int i = 0; i < allEvents.size(); i++) {
                AcademicCalendarEvent academicCalendarEvent = allEvents.get(i);
                nextDate = dates.parse(academicCalendarEvent.getCalADate());

                long difference = nextDate.getTime() - currentDate.getTime();
                differenceDates = difference / (24 * 60 * 60 * 1000);

                if (differenceDates >= 0) {
                    calendarIndex = i;
                    break;
                }

            }
        } catch (Exception exception) {
            Log.e("DIDN'T WORK", "exception " + exception);
        }

        if (calendarIndex == 0) {
            return allEvents.size() - 1;
        }

        return calendarIndex;
    }

}
