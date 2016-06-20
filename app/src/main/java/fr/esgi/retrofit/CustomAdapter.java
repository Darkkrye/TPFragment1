package fr.esgi.retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OpenFieldMacMini on 20/06/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    /* VARIABLES */
    Context context;
    private ArrayList<Repo> repos;

    /* CONSTRUCTOR */
    public CustomAdapter(ArrayList<Repo> repos, Context context) {
        this.repos = repos;
        this.context = context;
    }

    /* ONCREATE / ONBIND / GETITEMCOUNT */

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_layout, parent, false);
        view.setOnClickListener(RepoFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(holder);
        // Fill with data
        TextView tvname = holder.tvName;
        TextView tvAuthor = holder.tvAuthor;
        ImageView civProfile = holder.civProfile;

        tvname.setText(repos.get(position).getName());
        tvAuthor.setText(repos.get(position).getOwner().getLogin());

        Glide.with(context).
                load(MyVariables.user.getAvatarUrl()).
                override(120, 120).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(civProfile);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        /* VARIABLES */
        TextView tvName;
        TextView tvAuthor;
        ImageView civProfile;

        /* CONSTRUCTOR */
        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.textViewName);
            this.tvAuthor = (TextView) itemView.findViewById(R.id.textViewAuthor);
            this.civProfile = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
