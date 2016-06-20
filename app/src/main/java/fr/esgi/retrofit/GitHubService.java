package fr.esgi.retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by mohsan on 10/05/16.
 */
public interface GitHubService {

    String END_POINT = "https://api.github.com";
    String TOKEN = "25a11c74f91031d9ca6ec59940fe7731c4f564df";

    @Headers("Authorization: token "+TOKEN)
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("users/{user}/repos")
    Call<ArrayList<Repo>> getListRepo(@Path("user") String user);
}
