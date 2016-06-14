package fr.esgi.retrofit;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppActivity extends AppCompatActivity {

    @BindView(R.id.drawer) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.profile_image) CircleImageView profileImage;
    @BindView(R.id.headerLayout) RelativeLayout headerLayout;

    @BindView(R.id.menu1) TextView menu1;
    @BindView(R.id.menu2) TextView menu2;
    @BindView(R.id.menu3) TextView menu3;

    ActionBarDrawerToggle drawerToggle;
    GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        ButterKnife.bind(this);

        /* -- Navigation Drawer -- */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);

        Glide.with(this).load(MyVariables.user.getAvatarUrl()).into(profileImage);
        Glide.with(this).load("https://lh3.googleusercontent.com/proxy/xm_1j45fU-DIHPPxZ7JUrkNcrP3Vt4g89c6EmJs9J-xRurCVcaDRka0Ds39IKpRYM3kKo36aSEjUtI9VMzODjpjA-LNQJ9nAPKRsk5_4AiYQkksM_1BYK90ijg=w506-h284").asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    headerLayout.setBackground(drawable);
                }
            }
        });

        menu1.setBackgroundColor(ContextCompat.getColor(this, R.color.divider));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentLayout, UserFragment.newInstance(), "userFragment")
                .commit();

        service = GithubWebService.get();

        service.getListRepo(MyVariables.user.getLogin()).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }

    /* OVERRIDED METHODS */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.menu1)
    public void onMenuOneClick() {
        menu1.setBackgroundColor(ContextCompat.getColor(this, R.color.divider));
        menu2.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));
        menu3.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentLayout, UserFragment.newInstance(), "userFragment")
                .commit();

        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @OnClick(R.id.menu2)
    public void onMenuTwoClick() {
        menu2.setBackgroundColor(ContextCompat.getColor(this, R.color.divider));
        menu1.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));
        menu3.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentLayout, RepoFragment.newInstance(), "reposFragment")
                .commit();

        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @OnClick(R.id.menu3)
    public void onMenuThreeClick() {
        menu3.setBackgroundColor(ContextCompat.getColor(this, R.color.divider));
        menu2.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));
        menu1.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentLayout, NumbersFragment.newInstance(), "numbersFragment")
                .commit();

        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}
