package ada.osc.myfirstweatherapp.presentation;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.model.LocationWrapper;
import ada.osc.myfirstweatherapp.ui.addLocation.add.AddLocationContract;
import io.realm.Realm;

public class AddLocationPresenter implements AddLocationContract.Presenter{

    private AddLocationContract.View addLocationView;
    private Realm realm;

    @Override
    public void setView(AddLocationContract.View addLocationView) {
        this.addLocationView = addLocationView;
        realm = App.getRealm();
    }

    @Override
    public void saveNewCity(String cityName) {
        if (cityName == null || cityName.isEmpty()){
            addLocationView.onEmptyStringRequestError();
        }else if (realm.where(LocationWrapper.class).equalTo("location", cityName).findFirst() != null){
            addLocationView.onLocationAlreadyExistsError();
        }else {
            realm.beginTransaction();
            realm.createObject(LocationWrapper.class, cityName);
            realm.commitTransaction();
            addLocationView.onSuccess();
        }
    }
}
