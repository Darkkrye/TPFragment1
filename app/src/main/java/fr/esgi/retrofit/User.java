package fr.esgi.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohsan on 10/05/16.
 */

public class User {

    public String name;
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;
    public String followers;
    public String following;

    @SerializedName("public_repos")
    public String publicRepos;
    public String email;

    public User(String name, String login, String avatarUrl, String followers, String following, String publicRepos, String email) {
        this.name = name;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.followers = followers;
        this.following = following;
        this.publicRepos = publicRepos;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(String publicRepos) {
        this.publicRepos = publicRepos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
