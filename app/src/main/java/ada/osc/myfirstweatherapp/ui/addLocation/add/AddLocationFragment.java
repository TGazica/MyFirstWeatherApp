package ada.osc.myfirstweatherapp.ui.addLocation.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.model.LocationWrapper;
import ada.osc.myfirstweatherapp.presentation.AddLocationPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class AddLocationFragment extends Fragment implements AddLocationContract.View{

    @BindView(R.id.fragment_add_location_enter_city_edit_text)
    EditText enterLocation;

    private AddLocationContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = new AddLocationPresenter();
        presenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void onLocationAlreadyExistsError() {
        enterLocation.setError(getActivity().getString(R.string.location_already_exists_error_message));
    }

    @Override
    public void onEmptyStringRequestError() {
        enterLocation.setError(getActivity().getString(R.string.empty_location_string_error_message));
    }

    @OnClick(R.id.fragment_add_location_button)
    public void onClick() {
        presenter.saveNewCity(enterLocation.getText().toString());
}
}
