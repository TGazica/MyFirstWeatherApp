package ada.osc.myfirstweatherapp.presentation;

import ada.osc.myfirstweatherapp.interactor.ApiInteractor;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.ui.weather.WeatherContract;
import ada.osc.myfirstweatherapp.uitil.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.Presenter {

    private final ApiInteractor apiInteractor;

    private WeatherContract.View weatherView;

    public WeatherPresenter(ApiInteractor apiInteractor){
        this.apiInteractor = apiInteractor;
    }

    @Override
    public void setView(WeatherContract.View weatherView) {
        this.weatherView = weatherView;
    }


    @Override
    public void getWeatherFromNet(String city) {
        apiInteractor.getWeather(getWeatherCallback(), city);
    }

    private Callback<WeatherResponse> getWeatherCallback() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.body() != null) {
                    getWeather(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    weatherView.onFailure();
            }
        };
    }


    @Override
    public void getWeather(WeatherResponse weatherResponse) {
        getCurrentTemperatureValues(weatherResponse.getMain().getTemp_max());
        getPressureValues(weatherResponse.getMain().getPressure());
        getDescriptionValues(weatherResponse.getWeatherObject().getDescription());
        createWeatherIconValue(weatherResponse.getWeatherObject().getMain());
        getMinTemperatureValues(weatherResponse.getMain().getTemp_min());
        getMaxTemperatureValues(weatherResponse.getMain().getTemp_max());
        getWindValues(weatherResponse.getWind().getSpeed());
    }

    @Override
    public void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    getWeatherIcon(Constants.SNOW);
                    break;
                }
                case Constants.RAIN_CASE: {
                    getWeatherIcon(Constants.RAIN);
                    break;
                }
                case Constants.CLEAR_CASE: {
                    getWeatherIcon(Constants.SUN);
                    break;
                }
                case Constants.MIST_CASE: {
                    getWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.FOG_CASE: {
                    getWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.HAZE_CASE: {
                    getWeatherIcon(Constants.FOG);
                    break;
                }

                case Constants.CLOUD_CASE: {
                    getWeatherIcon(Constants.CLOUD);
                    break;
                }

                case Constants.THUNDERSTORM_CASE: {
                    getWeatherIcon(Constants.CLOUD);
                    break;
                }
            }
    }

    @Override
    public void getCurrentTemperatureValues(double temperatureValues) {
        weatherView.setCurrentTemperatureValues(toCelsiusFromKelvin(temperatureValues));
    }

    @Override
    public void getMinTemperatureValues(double minTemperatureValues) {
        weatherView.setMinTemperatureValues(toCelsiusFromKelvin(minTemperatureValues));
    }

    @Override
    public void getMaxTemperatureValues(double maxTemperatureValues) {
        weatherView.setMaxTemperatureValues(toCelsiusFromKelvin(maxTemperatureValues));
    }

    @Override
    public void getPressureValues(double pressureValues) {
        weatherView.setPressureValues(pressureValues);
    }

    @Override
    public void getWindValues(double windValues) {
        weatherView.setWindValues(windValues);
    }

    @Override
    public void getWeatherIcon(String iconPath) {
        weatherView.setWeatherIcon(iconPath);
    }

    @Override
    public void getDescriptionValues(String descriptionValues) {
        weatherView.setDescriptionValues(descriptionValues);
    }

    private double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }
}
