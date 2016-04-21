package android.healthifyme.com.activities;

import android.healthifyme.com.AppConstants;
import android.healthifyme.com.R;
import android.healthifyme.com.adapters.ViewPagerAdapter;
import android.healthifyme.com.api.HealthifyMeApi;
import android.healthifyme.com.api.response.ApiResponse;
import android.healthifyme.com.core.models.DaySlot;
import android.healthifyme.com.core.utils.Utils;
import android.healthifyme.com.fragments.SlotFragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
    TextView month;
    TabLayout tabLayout;
    LinearLayout tab1, tab2, tab3;
    ViewPager viewpager;
    ApiResponse apiResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ((TextView)toolbar.findViewById(R.id.title)).setText(getResources().getString(R.string.title));
        month = (TextView) findViewById(R.id.month);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewpager = (ViewPager) findViewById(R.id.viewPager);

        tab1 = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab2 = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab3 = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        fetchSlotDetails();
    }

    private void fetchSlotDetails() {
        Map<String, String> options = new HashMap<>();
        options.put("username", AppConstants.USER_NAME);
        options.put("vc", AppConstants.VC);
        options.put("expert_username", AppConstants.EXPERT_USER_NAME);
        options.put("format", AppConstants.FORMAT);
        HealthifyMeApi.getService().getSlotDetails(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ApiResponse>() {
                    @Override
                    public void call(ApiResponse apiResponse) {
                        buildScreen(apiResponse);
                        // buildScreen(apiResponse);
                    }
                }, new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {
                        Log.e("ERROR", throwable.getMessage());
                    }
                });
    }

    private void buildScreen(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
        List<DaySlot> daySlots = apiResponse.getSlots();
        setUpViewPager(daySlots);
        setTabs();
        tabLayout.getTabAt(0).setCustomView(tab1);
        tabLayout.getTabAt(1).setCustomView(tab2);
        tabLayout.getTabAt(2).setCustomView(tab3);
    }

    private void setTabs() {
        TextView day1,week1, day2, week2, day3, week3;

        day1 = (TextView) tab1.findViewById(R.id.day);
        week1 = (TextView) tab1.findViewById(R.id.week);
        day2 = (TextView) tab2.findViewById(R.id.day);
        week2 = (TextView) tab2.findViewById(R.id.week);
        day3 = (TextView) tab3.findViewById(R.id.day);
        week3 = (TextView) tab3.findViewById(R.id.week);

        Iterator iterator = apiResponse.slots.keySet().iterator();
        Utils utils = new Utils(this);
        Calendar cal = utils.getDate((String ) iterator.next());
        int day = cal.get(Calendar.DATE);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        day1.setText(day + "");
        week1.setText(utils.getWeekDay(week));

        cal = utils.getDate((String) iterator.next());
         day = cal.get(Calendar.DATE);
         week = cal.get(Calendar.DAY_OF_WEEK);
        day2.setText(day + "");
        week2.setText(utils.getWeekDay(week));

        cal = utils.getDate((String) iterator.next());
        day = cal.get(Calendar.DATE);
        week = cal.get(Calendar.DAY_OF_WEEK);
        day3.setText(day + "");
        week3.setText(utils.getWeekDay(week));

        month.setText(utils.getMonthString(cal.get(Calendar.MONTH)));
    }

    private void setUpViewPager(List<DaySlot> daySlots) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for(int i = 0; i < daySlots.size();i++) {
            Fragment fragment = new SlotFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Slots", daySlots.get(i));
            fragment.setArguments(bundle);
            adapter.addFrag(fragment, "");
        }
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }


}
