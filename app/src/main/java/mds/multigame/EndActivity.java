package mds.multigame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mds.multigame.utils.ActivityUtils;


public class EndActivity extends AppCompatActivity {

    private TextView nomdujeu;
    private TextView scoreaffichage;
    private Button quitter;
    private Button menu;

    public static final String SCORE = "score : ";
    public static final String NOMDUJEU = "";


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
