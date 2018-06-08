package ada.osc.myfirstweatherapp.persistance;

import android.content.Context;

import io.realm.Realm;

public class RealmDatabase {

    public static Realm createRealm(Context context){
        Realm.init(context);
        return Realm.getDefaultInstance();
    }

}
