package mds.multigame.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mds.multigame.R;
import mds.multigame.manager.PlayerManager;
import mds.multigame.model.Player;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private ArrayList<String> gameNames;
    private Fragment theFragment;

    public ScoreAdapter(Fragment fragment, ArrayList<String> gamelist) {
        this.gameNames = gamelist;
        this.theFragment = fragment;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout rowScore;
        private ImageView smiley;
        private TextView gamename;
        private TextView score;

        ViewHolder(View itemView) {
            super(itemView);
            rowScore = itemView.findViewById(R.id.liste_score);
            smiley = itemView.findViewById(R.id.smiley);
            gamename = itemView.findViewById(R.id.nomdujeu);
            score = itemView.findViewById(R.id.res);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.settingrow, viewGroup, false);
        return new ScoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final String listjeu = gameNames.get(position);
        viewHolder.gamename.setText(listjeu);

            if (listjeu .equals( "DragnDrop")) {
                viewHolder.score.setText( String.valueOf(PlayerManager.getInstance().getPlayer().getScoreDnD()));

            } else if (listjeu .equals( "Swipe")) {
                viewHolder.score.setText( String.valueOf(PlayerManager.getInstance().getPlayer().getScoreSwipe()));

            } else if (listjeu .equals( "Fast Tap")) {
                viewHolder.score.setText( String.valueOf(PlayerManager.getInstance().getPlayer().getScoreFTG()));

            } else if (listjeu .equals( "Ipac Game")) {
                viewHolder.score.setText( String.valueOf(PlayerManager.getInstance().getPlayer().getScoreIpac()));
            }

    }

    @Override
    public int getItemCount() {
        return gameNames.size();
    }




}