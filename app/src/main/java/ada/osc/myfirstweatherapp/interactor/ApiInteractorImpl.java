package ada.osc.myfirstweatherapp.interactor;

import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.networking.ApiService;
import ada.osc.myfirstweatherapp.uitil.Constants;
import retrofit2.Callback;

public class ApiInteractorImpl implements ApiInteractor {

    private final ApiService apiService;

    public ApiInteractorImpl(ApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public void getWeather(Callback<WeatherResponse> callback, String city) {
        apiService.getWeather(Constants.APP_ID, city).enqueue(callback);
    }
}
