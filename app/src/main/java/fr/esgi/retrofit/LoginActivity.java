package fr.esgi.retrofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String USERNAME = "username";
    @BindView(R.id.et_username) EditText etUsername;
    @BindView(R.id.btn_search) Button btnSearch;

    SharedPreferences sharedPrefs; //Correction stocker les shareprefs en field
    GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        service = GithubWebService.get();
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String username;
        if ((username = sharedPrefs.getString(USERNAME, null)) != null) {
            this.etUsername.setText(username);
        }
    }

    @OnClick(R.id.btn_search)
    public void onBtnSearchClick() {
        if (!this.etUsername.getText().toString().isEmpty()) {
            String username = etUsername.getText().toString();

            service.getUser(username).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    //displayUser(user);

                    if (user != null) {
                        sharedPrefs.edit().putString(USERNAME, etUsername.getText().toString()).apply();  //Correction apply
                        MyVariables.user = user;
                        Intent intent = new Intent(getApplicationContext(), AppActivity.class);
                        startActivity(intent);
                    } else {
                        sharedPrefs.edit().remove(USERNAME).apply(); //Correction apply
                        Toast.makeText(getApplicationContext(), "Aucun utilisateur trouvé", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("retrofit", "failure" + t);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Vous n'avez pas saisi de nom d'utilisateur", Toast.LENGTH_SHORT).show();
            sharedPrefs.edit().remove(USERNAME).apply();  //Correction apply
        }
    }
}
