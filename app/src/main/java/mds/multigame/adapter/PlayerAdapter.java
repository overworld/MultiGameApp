package mds.multigame.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mds.multigame.MainActivity;
import mds.multigame.ProfilActivity;
import mds.multigame.R;
import mds.multigame.ShowPlayerActivity;
import mds.multigame.manager.PlayerManager;
import mds.multigame.model.Player;
import mds.multigame.utils.ActivityUtils;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private ArrayList<Player> listplayer;
    private AppCompatActivity theActivity;

    public PlayerAdapter(AppCompatActivity activity, ArrayList<Player> players) {
        this.listplayer = players;
        this.theActivity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.showplayerrow, viewGroup, false);
        return new PlayerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Player player = listplayer.get(position);
        viewHolder.firstName.setText(player.getFirstname());
        viewHolder.name.setText(player.getName());
        Picasso.get().load(Uri.parse(player.getPicture())).into(viewHolder.image);
        viewHolder.rowplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerManager.getInstance().setPlayer(player);
                ActivityUtils.launchActivity(theActivity,MainActivity.class);
            }
        });
        viewHolder.rowplayer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PlayerManager.getInstance().setPlayer(player);
                ActivityUtils.launchActivity(theActivity,ProfilActivity.class);
                return false;
            }
        });
    }



    @Override
    public int getItemCount() {
        return listplayer.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout rowplayer;
        private ImageView image;
        private TextView name;
        private TextView firstName;

        ViewHolder(View itemView) {
            super(itemView);
            rowplayer = itemView.findViewById(R.id.player_row);
            image = itemView.findViewById(R.id.imgjoueur);
            name = itemView.findViewById(R.id.nom);
            firstName = itemView.findViewById(R.id.prenom);
        }

    }

}