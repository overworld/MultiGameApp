package mds.multigame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import mds.multigame.EndActivity;
import mds.multigame.R;
import mds.multigame.utils.ActivityUtils;
import mds.multigame.utils.OnSwipeTouchListener;

public class FastTapGameFragment extends Fragment {

    public static final String TAG = "SwipeAndTapFragment";

    private LinearLayout tapzone;
    private TextView tempsrestant;
    private TextView taporlongtap;
    private TextView score;
    private boolean istap;
    private int nb = 0;
    private Timer timer;
    private int temps = 10;
    private String scorefinal;
    private String nomdujeu = "";
    private int result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fast_tap_game, container, false);
        tempsrestant = view.findViewById(R.id.timeleft);
        taporlongtap = view.findViewById(R.id.taptextchange);
        score = view.findViewById(R.id.score);
        tapzone = view.findViewById(R.id.gametap);

        boolean bool = getArguments().getBoolean("key");


        if (bool == false) {
            nomdujeu = "Swipe Game";

            randomgenerator();

            tapzone.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {


                public void onSwipeTop() {
                    //Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
                    if (result == 0) {
                        nb = nb + 1;
                        score.setText("Score : " + nb);
                        randomgenerator();

                    }
                }


                public void onSwipeRight() {
                    //Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();

                    if (result == 1) {
                        nb = nb + 1;
                        score.setText("Score : " + nb);
                        randomgenerator();
                    }
                }


                public void onSwipeLeft() {
                    //Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
                    if (result == 2) {
                        nb = nb + 1;
                        score.setText("Score : " + nb);
                        randomgenerator();
                    }
                }


                public void onSwipeBottom() {
                    // Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();

                    if (result == 3) {
                        nb = nb + 1;
                        score.setText("Score : " + nb);
                        randomgenerator();
                    }
                }
            });
        }

        if (bool == true) {

            nomdujeu = "Fast Tap Game";

            Random rand = new Random();
            final int result = rand.nextInt(2);
            if (result <= 0) {
                String tap = "Tap";
                taporlongtap.setText(tap);
                istap = true;
                score.setText("Score : " + nb);

            } else {
                String taplong = "Tap long";
                taporlongtap.setText(taplong);
                istap = false;
                score.setText("Score : " + nb);

            }

            tapzone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (istap == true) {
                        Random rand = new Random();
                        int result = rand.nextInt(2);
                        if (result <= 0) {
                            String tap = "Tap";
                            taporlongtap.setText(tap);
                            istap = true;
                            nb = nb + 1;
                            score.setText("Score : " + nb);
                        } else {
                            String taplong = "Tap long";
                            taporlongtap.setText(taplong);
                            istap = false;
                            nb = nb + 1;
                            score.setText("Score : " + nb);
                        }
                    }


                }
            });

            tapzone.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if (istap == false) {
                        Random rand = new Random();
                        int result = rand.nextInt(2);
                        if (result <= 0) {
                            String tap = "Tap";
                            taporlongtap.setText(tap);
                            istap = true;
                            nb = nb + 1;
                            score.setText("Score : " + nb);
                        } else {
                            String taplong = "Tap long";
                            taporlongtap.setText(taplong);
                            istap = false;
                            nb = nb + 1;
                            score.setText("Score : " + nb);
                        }
                    }
                    return true;
                }
            });
        }

        final Handler handler = new Handler();
        timer = new Timer();
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
        return view;
    }

    private void randomgenerator() {
        Random rand = new Random();
        result = rand.nextInt(4);
        if (result == 0) {
            String f = "Swipe Top";
            taporlongtap.setText(f);
        }
        if (result == 1) {
            String f = "Swipe Right";
            taporlongtap.setText(f);
        }
        if (result == 2) {
            String f = "Swipe Left";
            taporlongtap.setText(f);
        }
        if (result == 3) {
            String f = "Swipe Bottom";
            taporlongtap.setText(f);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
