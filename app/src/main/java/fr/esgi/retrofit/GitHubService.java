package fr.esgi.retrofit;

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
    String TOKEN = "e8a27efc2e0094f7f1d2b414dad96656cd34a224";

    @Headers("Authorization: token "+TOKEN)
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("users/{user}/repos")
    Call<List<Repo>> getListRepo(@Path("user") String user);
}
