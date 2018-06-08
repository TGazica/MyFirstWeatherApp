package ada.osc.myfirstweatherapp.ui.weather;

import android.content.Context;

import ada.osc.myfirstweatherapp.model.WeatherResponse;

public interface WeatherContract {

    interface View{

        void onFailure();

        void setCurrentTemperatureValues(double temperatureValues);

        void setMinTemperatureValues(double minTemperatureValues);

        void setMaxTemperatureValues(double maxTemperatureValues);

        void setPressureValues(double pressureValues);

        void setWindValues(double windValues);

        void setWeatherIcon(String iconPath);

        void setDescriptionValues(String descriptionValues);

    }

    interface Presenter{

        void getWeatherFromNet(String city);

        void setView(View view);

        void getWeather(WeatherResponse weatherResponse);

        void createWeatherIconValue(String description);

        void getCurrentTemperatureValues(double temperatureValues);

        void getMinTemperatureValues(double minTemperatureValues);

        void getMaxTemperatureValues(double maxTemperatureValues);

        void getPressureValues(double pressureValues);

        void getWindValues(double windValues);

        void getWeatherIcon(String iconPath);

        void getDescriptionValues(String descriptionValues);

    }
}
