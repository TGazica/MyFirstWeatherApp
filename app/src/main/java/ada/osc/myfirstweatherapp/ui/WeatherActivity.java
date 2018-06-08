package ada.osc.myfirstweatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import ada.osc.myfirstweatherapp.adapter.CustomViewPagerFragmentAdapter;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.model.LocationWrapper;
import ada.osc.myfirstweatherapp.ui.addLocation.AddNewLocationActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class WeatherActivity extends AppCompatActivity{

    @BindView(R.id.main_activity_drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.main_activity_view_pager) ViewPager viewPager;
    @BindView(R.id.main_activity_navigation_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private CustomViewPagerFragmentAdapter adapter;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        initUI();
        initToolbar();

        adapter = new CustomViewPagerFragmentAdapter(getSupportFragmentManager());

        ArrayList<LocationWrapper> locationWrappers = new ArrayList<>();
        locationWrappers.addAll(realm.where(LocationWrapper.class).findAll());

        adapter.setAdapterData(locationWrappers);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initNavigationDrawer();
    }

    @Override
    protected void onResume(){
        super.onResume();
        ArrayList<LocationWrapper> locationWrappers = new ArrayList<>();
        locationWrappers.addAll(realm.where(LocationWrapper.class).findAll());

        adapter.setAdapterData(locationWrappers);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void initNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleItemSelectedClick(item.getItemId());
                return false;
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.main_activity_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void handleItemSelectedClick(int itemID) {
        switch (itemID) {
            case R.id.menu_add_new_location: {
                startActivity(new Intent(this, AddNewLocationActivity.class));
                drawerLayout.closeDrawers();
                break;
            }
        }
    }
}