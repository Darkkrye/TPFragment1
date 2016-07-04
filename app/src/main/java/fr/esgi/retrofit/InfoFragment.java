package fr.esgi.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by OpenFieldMacMini on 20/06/2016.
 */
public class InfoFragment extends Fragment {

    public static final String INFORMATION = "information"; //Correction constant
    @BindView(R.id.tv_information) TextView tvInformation;

    private String information;

    public static Fragment newInstance(String information) {
        InfoFragment fragment = new InfoFragment();

        Bundle args = new Bundle();
        args.putString(INFORMATION, information);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.information = getArguments().getString(INFORMATION, "0");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        this.tvInformation.setText(this.information);
    }

}
