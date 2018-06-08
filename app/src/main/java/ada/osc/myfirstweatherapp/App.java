package ada.osc.myfirstweatherapp;

import android.app.Application;
import android.support.annotation.NonNull;

import ada.osc.myfirstweatherapp.interactor.ApiInteractor;
import ada.osc.myfirstweatherapp.interactor.ApiInteractorImpl;
import ada.osc.myfirstweatherapp.networking.ApiService;
import ada.osc.myfirstweatherapp.networking.RetrofitUtil;
import ada.osc.myfirstweatherapp.uitil.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static App sInstance;
    private static ApiInteractor apiInteractor;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        final Retrofit retrofit = RetrofitUtil.createRetrofit();
        final ApiService apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);

    }

    public static App getInstance() {
        return sInstance;
    }

    public static ApiInteractor getApiInteractor(){
        return apiInteractor;
    }

}
