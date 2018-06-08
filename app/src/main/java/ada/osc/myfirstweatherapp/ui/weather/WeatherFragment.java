package ada.osc.myfirstweatherapp.ui.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.presentation.WeatherPresenter;
import ada.osc.myfirstweatherapp.uitil.NetworkUtils;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.uitil.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment implements WeatherContract.View {

    @BindView(R.id.weather_display_current_temperature_text_view)
    TextView currentTemp;
    @BindView(R.id.weather_fragment_min_temperature_text_view)
    TextView minTemp;
    @BindView(R.id.weather_fragment_max_temperature_text_view)
    TextView maxTemp;
    @BindView(R.id.weather_display_pressure_text_view)
    TextView pressure;
    @BindView(R.id.weather_display_wind_text_view)
    TextView windSpeed;
    @BindView(R.id.weather_display_detailed_description_text_view)
    TextView description;
    @BindView(R.id.weather_display_weather_icon_image_view)
    ImageView weatherIcon;

    private WeatherContract.Presenter presenter;
    private String cityToDisplay;

    public static WeatherFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        WeatherFragment f = new WeatherFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        cityToDisplay = getArguments().getString(Constants.CITY_BUNDLE_KEY);

        presenter = new WeatherPresenter(App.getApiInteractor());
        presenter.setView(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (isAdded() && getActivity() != null) {
            presenter.getWeatherFromNet(cityToDisplay);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isAdded() && getActivity() != null) {
            refreshCurrentData();
        }
    }

    @Override
    public void setCurrentTemperatureValues(double temperatureValues) {
        currentTemp.setText(getString(R.string.current_temperature_message, temperatureValues));
    }

    public void setMinTemperatureValues(double minTemperatureValues) {
        minTemp.setText(getString(R.string.minimum_temperature_message, minTemperatureValues));
    }

    @Override
    public void setMaxTemperatureValues(double maxTemperatureValues) {
        maxTemp.setText(getString(R.string.maximum_temperature_message, maxTemperatureValues));
    }

    @Override
    public void setPressureValues(double pressureValues) {
        pressure.setText(getString(R.string.pressure_message, pressureValues));

    }

    @Override
    public void setWindValues(double windValues) {
        windSpeed.setText(getString(R.string.wind_speed_message, windValues));
    }

    public void setWeatherIcon(String iconPath) {
        Picasso.get().load(Constants.IMAGE_BASE_URL + iconPath).into(weatherIcon);
    }

    @Override
    public void setDescriptionValues(String descriptionValues) {
        description.setText(descriptionValues);
    }

    public void onFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.weather_fragment_loading_failure_toast_message), Toast.LENGTH_SHORT).show();
    }

    private void refreshCurrentData() {
        if (NetworkUtils.checkIfInternetConnectionIsAvailable(getActivity())) {
            presenter.getWeatherFromNet(cityToDisplay);
        }
    }

}
