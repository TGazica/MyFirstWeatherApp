package ada.osc.myfirstweatherapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import ada.osc.myfirstweatherapp.model.LocationWrapper;
import ada.osc.myfirstweatherapp.ui.weather.WeatherFragment;

public class CustomViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private final ArrayList<LocationWrapper> citiesList = new ArrayList<>();

    public CustomViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(citiesList.get(position).getLocation());
    }

    @Override
    public int getCount() {
        return citiesList.size();
    }

    public void setAdapterData(ArrayList<LocationWrapper> dataSource) {
        this.citiesList.clear();
        this.citiesList.addAll(dataSource);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return citiesList.get(position).getLocation();
    }
}