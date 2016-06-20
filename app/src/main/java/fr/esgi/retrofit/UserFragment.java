package fr.esgi.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Pierre on 14/06/2016.
 */
public class UserFragment extends Fragment {

    @BindView(R.id.thumbnail) ImageView ivThumbnail;
    @BindView(R.id.name) TextView tvName;
    @BindView(R.id.pseudo) TextView tvPseudo;
    @BindView(R.id.followers) TextView tvFollowers;

    /*@BindView(R.id.civ_profile_image)
    CircleImageView civProfileImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_pseudo)
    TextView tvPseudo;
    @BindView(R.id.tv_followers)
    TextView tvFollowers;*/

    //Create a new fragment with parameters
    public static Fragment newInstance(){
        return new UserFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        if (MyVariables.user.getName() == null)
            tvName.setText("Nom : (Aucun nom n'a été renseigné)");
        else
            tvName.setText("Nom : " + MyVariables.user.getName());

        tvPseudo.setText("Pseudo : " + MyVariables.user.getLogin());
        tvFollowers.setText("Nombre de followers : " + MyVariables.user.getFollowers());
        Glide.with(this).load(MyVariables.user.getAvatarUrl()).into(ivThumbnail);

        /*if (MyVariables.user.getName() == null)
            tvName.setText("Nom : (Aucun nom n'a été renseigné)");
        else
            tvName.setText("Nom : " + MyVariables.user.getName());

        tvPseudo.setText("Pseudo : " + MyVariables.user.getLogin());
        tvFollowers.setText("Nombre de followers : " + MyVariables.user.getFollowers());
        Glide.with((Fragment)this).load(MyVariables.user.getAvatarUrl()).into(civProfileImage);*/
    }
}
