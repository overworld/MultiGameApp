package mds.multigame.utils;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import mds.multigame.EndActivity;
import mds.multigame.R;
import mds.multigame.fragment.FastTapGameFragment;

public abstract class TimerUtils {

    private boolean istap;
    private int nb = 0;
    private Timer timer;
    private int temps = 10;
    private String scorefinal;

    final Handler handler = new Handler();


   /* timer = new Timer();
    final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    temps = temps - 1;
                    tempsrestant.setText("Il reste : " + temps + " secondes");

                    if (temps == 0) {
                        scorefinal = score.getText().toString();
                        Intent intent = new Intent((AppCompatActivity) FastTapGameFragment.this.getActivity(), EndActivity.class);
                        intent.putExtra(EndActivity.SCORE, scorefinal);
                        intent.putExtra(EndActivity.NOMDUJEU, nomdujeu);
                        ActivityUtils.launchActivity((AppCompatActivity) FastTapGameFragment.this.getActivity(), intent, false, 0);
                        getFragmentManager().popBackStack();
                    }
                }
            });
        }
    };
        timer.schedule(timerTask, 2, 1000);
*/


    public abstract void endGame();



}
