package mds.multigame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import mds.multigame.manager.PlayerManager;
import mds.multigame.model.Player;

public class ProfilActivity extends AppCompatActivity {

    private TextView nom;
    private TextView prenom;
    private TextView age;
    private TextView localisation;
    private Button modifier;
    private Button supprimer;
    private ImageView imageProfil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilactivity);
        nom = findViewById(R.id.profilnom);
        prenom = findViewById(R.id.profilprenom);
        age = findViewById(R.id.profilage);
        localisation = findViewById(R.id.profillocalisation);
        modifier = findViewById(R.id.btn_modifier);
        supprimer = findViewById(R.id.btn_supprimer);
        imageProfil = findViewById(R.id.profilimg);


        nom.setText(PlayerManager.getInstance().getPlayer().getName());
        prenom.setText(PlayerManager.getInstance().getPlayer().getFirstname());
        age.setText(String.valueOf(PlayerManager.getInstance().getPlayer().getAge()));
        localisation.setText(PlayerManager.getInstance().getPlayer().getLocalisation());
        Picasso.get().load(PlayerManager.getInstance().getPlayer().getPicture()).into(imageProfil);

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUserDbb();
            }
        });

    }

    private void deleteUserDbb() {
        Realm mRealmInstance = Realm.getDefaultInstance();
        mRealmInstance.beginTransaction();
        Player currentPlayer = PlayerManager.getInstance().getPlayer();
        try {

            RealmResults<Player> rows= mRealmInstance.where(Player.class).equalTo("name", PlayerManager.getInstance().getPlayer().getName()).findAll();;
            rows.deleteAllFromRealm();
            mRealmInstance.commitTransaction();
        } catch (Exception e) {
            String a = "";
        }
    }
}
