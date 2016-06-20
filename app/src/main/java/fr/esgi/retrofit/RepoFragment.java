package fr.esgi.retrofit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pierre on 14/06/2016.
 */
public class RepoFragment extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    public static ArrayList<Repo> repos;
    public static View.OnClickListener myOnClickListener;

    public static Fragment newInstance() {
        return new RepoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        /* -- RecyclerView - Set information -- */
        recyclerView = (RecyclerView) view.findViewById(R.id.repoRecycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.myOnClickListener = new MyOnClickListener(getContext());

        fillRecycler();
    }

    public void fillRecycler() {
        // Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService apiService = retrofit.create(GitHubService.class);

        // Set which json to call
        Call<ArrayList<Repo>> call;
        call = apiService.getListRepo(MyVariables.user.getLogin());

        // Add Callback to call queue
        call.enqueue(new Callback<ArrayList<Repo>>() {
            @Override
            public void onResponse(Call<ArrayList<Repo>> call, Response<ArrayList<Repo>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    ArrayList<Repo> repos = response.body();

                    if (repos != null)
                        showRepos(repos, statusCode);
                    else
                        showError("There's no repos !");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Repo>> call, Throwable t) {
                showError(String.valueOf(t));
                showError("Un problème est survenu. Vérifiez votre connexion internet");
            }
        });
    }

    public void showRepos(ArrayList<Repo> repos, int statusCode) {
        if (statusCode == 200) {
            this.repos = repos;
            adapter = new CustomAdapter(repos, getContext());
            recyclerView.setAdapter(adapter);
        } else
            showError("Code erreur : " + String.valueOf(statusCode));
    }

    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /* PRIVATE STATIC CLASSES */
    private static class MyOnClickListener implements View.OnClickListener {

        /* VARIABLES */
        private final Context context;

        /* CONSTRUCTOR */
        private MyOnClickListener(Context context) {
            this.context = context;
        }

        /* OVERRIDED METHODS */
        @Override
        public void onClick(View v) {
            Repo selectedRepo = repos.get(recyclerView.getChildAdapterPosition(v));

            // Open URL in web browser
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedRepo.getHtmlURL()));
            v.getContext().startActivity(browse);
        }
    }
}
