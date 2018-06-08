package ada.osc.myfirstweatherapp.interactor;

import retrofit2.Callback;

import ada.osc.myfirstweatherapp.model.WeatherResponse;

public interface ApiInteractor {

    void getWeather(Callback<WeatherResponse> callback, String city);

}
