package ada.osc.myfirstweatherapp.ui.addLocation.add;

import ada.osc.myfirstweatherapp.ui.weather.WeatherContract;

public interface AddLocationContract {

    interface View{

        void onSuccess();

        void onLocationAlreadyExistsError();

        void onEmptyStringRequestError();

    }

    interface Presenter{

        void setView(AddLocationContract.View addLocationView);

        void saveNewCity(String cityName);

    }

}
