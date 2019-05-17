package mds.multigame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import mds.multigame.fragment.DragnDropFragment;
import mds.multigame.fragment.FastTapGameFragment;
import mds.multigame.fragment.IpacGameFragment;
import mds.multigame.manager.PlayerManager;
import mds.multigame.model.Player;


public class EndActivity extends AppCompatActivity {

    private TextView nomdujeu;
    private TextView scoreaffichage;
    private Button quitter;
    private Button menu;

    public static final String SCORE = "kayak";
    public static final String NOMDUJEU = "banana";
    public static final String RESULTAT = "res";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endlayout);
        scoreaffichage = findViewById(R.id.scorefin);
        nomdujeu = findViewById(R.id.nomdujeu);
        menu = findViewById(R.id.menu);
        quitter = findViewById(R.id.quitter);

        String score = getIntent().getExtras().getString(SCORE);
        scoreaffichage.setText(score);
        String name = getIntent().getExtras().getString(NOMDUJEU);
        nomdujeu.setText(name);
        int result = getIntent().getExtras().getInt(RESULTAT);
        Realm mRealmInstance = Realm.getDefaultInstance();
        mRealmInstance.beginTransaction();

        if ( nomdujeu.getText() .equals( DragnDropFragment.NAME_DND) && PlayerManager.getInstance().getPlayer().getScoreDnD() < result)
        {

            PlayerManager.getInstance().getPlayer().setScoreDnD(result);
            String a = "";
        }
        else if(nomdujeu.getText() .equals(FastTapGameFragment.NAME_SWIPE) && PlayerManager.getInstance().getPlayer().getScoreSwipe() < result)
        {
            PlayerManager.getInstance().getPlayer().setScoreSwipe(result);

        }
        else if(nomdujeu.getText() .equals( FastTapGameFragment.NAME_FTG ) && PlayerManager.getInstance().getPlayer().getScoreFTG() < result)
        {
            PlayerManager.getInstance().getPlayer().setScoreFTG(result);

        }
        else if(nomdujeu.getText() .equals(IpacGameFragment.NAME_IPAC)  && PlayerManager.getInstance().getPlayer().getScoreIpac() < result)
        {
            PlayerManager.getInstance().getPlayer().setScoreIpac(result);

        }
        mRealmInstance.copyToRealmOrUpdate(PlayerManager.getInstance().getPlayer());
        mRealmInstance.commitTransaction();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                onBackPressed();
            }

        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });


    }

}
