package fr.esgi.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pierre on 14/06/2016.
 */
public class Repo {
    public String name;
    @SerializedName("html_url")
    public String htmlURL;

    public Owner owner;

    public Repo(String name, String htmlURL, Owner owner) {
        this.name = name;
        this.htmlURL = htmlURL;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
