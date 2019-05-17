package mds.multigame;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import mds.multigame.adapter.PlayerAdapter;
import mds.multigame.manager.PlayerManager;
import mds.multigame.model.Player;
import mds.multigame.utils.ActivityUtils;

public class ShowPlayerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayout rowplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showplayer);
        recyclerView = findViewById(R.id.listplayer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PlayerAdapter(ShowPlayerActivity.this,getAllPlayers()));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.slide_out);

    }



    private ArrayList<Player> getAllPlayers() {
        Realm mRealmInstance = Realm.getDefaultInstance();
        RealmQuery query = mRealmInstance.where(Player.class);
        return new ArrayList<Player>(query.findAll());
    }



}

