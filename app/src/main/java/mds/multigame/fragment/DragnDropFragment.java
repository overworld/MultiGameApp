package mds.multigame.fragment;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import mds.multigame.EndActivity;
import mds.multigame.R;
import mds.multigame.utils.ActivityUtils;

public class DragnDropFragment extends Fragment {

    public static final String TAG = "DragnDropFragment";

    private FrameLayout blue;
    private FrameLayout red;
    private FrameLayout purple;
    private FrameLayout yellow;
    private FrameLayout deepBlue;
    private FrameLayout green;
    private ImageView circle;
    private RelativeLayout center;
    private int result;
    private TextView score;
    private String scorefinal;
    private Timer timer;
    private int nb = 0;
    private int temps = 10;
    private TextView tempsrestant;
    private String nomdujeu = "Drag N Drop";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag_n_drop, container, false);
        tempsrestant = view.findViewById(R.id.timedrag);
        score = view.findViewById(R.id.scoredrag);
        circle = view.findViewById(R.id.shape);
        blue = view.findViewById(R.id.blue);
        red = view.findViewById(R.id.red);
        purple = view.findViewById(R.id.purple);
        yellow = view.findViewById(R.id.yellow);
        deepBlue = view.findViewById(R.id.deepblue);
        green = view.findViewById(R.id.green);
        center = view.findViewById(R.id.centerdrag);

        circle.setOnTouchListener(new DragDropOnTouchListener());
        blue.setOnDragListener(new DragDropOnDragListener());
        red.setOnDragListener(new DragDropOnDragListener());
        purple.setOnDragListener(new DragDropOnDragListener());
        yellow.setOnDragListener(new DragDropOnDragListener());
        deepBlue.setOnDragListener(new DragDropOnDragListener());
        green.setOnDragListener(new DragDropOnDragListener());


        Random rand = new Random();
        result = rand.nextInt(6);
        if (result == 0) {
            circle.setBackgroundColor(getResources().getColor(R.color.deepblue));
        }
        if (result == 1) {
            circle.setBackgroundColor(getResources().getColor(R.color.green));

        }
        if (result == 2) {
            circle.setBackgroundColor(getResources().getColor(R.color.red));

        }
        if (result == 3) {
            circle.setBackgroundColor(getResources().getColor(R.color.purple));

        }
        if (result == 4) {
            circle.setBackgroundColor(getResources().getColor(R.color.yellow));

        }
        if (result == 5) {
            circle.setBackgroundColor(getResources().getColor(R.color.blue));

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
                            Intent intent = new Intent((AppCompatActivity) DragnDropFragment.this.getActivity(), EndActivity.class);
                            intent.putExtra(EndActivity.SCORE, scorefinal);
                            intent.putExtra(EndActivity.NOMDUJEU, nomdujeu);
                            ActivityUtils.launchActivity((AppCompatActivity) DragnDropFragment.this.getActivity(), intent, false, 0);
                            getFragmentManager().popBackStack();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 2, 1000);

        return view;


    }


    public class DragDropOnTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            String tag = (String) view.getTag();

            ClipData clipData = ClipData.newPlainText("", tag);

            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);

            view.startDrag(clipData, dragShadowBuilder, view, 0);

            view.setVisibility(View.INVISIBLE);

            return true;
        }
    }

    public class DragDropOnDragListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            // Get the drag drop action.
            int dragAction = dragEvent.getAction();

            if (dragAction == dragEvent.ACTION_DRAG_STARTED) {
                // Check whether the dragged view can be placed in this target view or not.

                ClipDescription clipDescription = dragEvent.getClipDescription();

                if (clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                    // Return true because the target view can accept the dragged object.
                    return true;

                }
            } else if (dragAction == dragEvent.ACTION_DRAG_ENTERED) {


                return true;
            } else if (dragAction == dragEvent.ACTION_DRAG_EXITED) {

                return true;
            } else if (dragAction == dragEvent.ACTION_DRAG_ENDED) {


                circle.setVisibility(View.VISIBLE);

                boolean result = dragEvent.getResult();


                return true;


            } else if (dragAction == DragEvent.ACTION_DROP) {

                if (view == deepBlue && 0 == result) {
                    nb = nb + 1;
                    score.setText("Score : " + nb);
                    randomGenerator();

                }
                if (view == green && 1 == result) {
                    nb = nb + 1;
                    score.setText("Score : " + nb);
                    randomGenerator();

                }
                if (view == red && 2 == result) {
                    nb = nb + 1;
                    score.setText("Score : " + nb);
                    randomGenerator();

                }
                if (view == purple && 3 == result) {
                    nb = nb + 1;
                    score.setText("Score : " + nb);
                    randomGenerator();

                }
                if (view == yellow && 4 == result) {
                    nb = nb + 1;
                    score.setText("Score : " + nb);
                    randomGenerator();

                }
                if (view == blue && 5 == result) {
                    nb = nb + 1;
                    score.setText("Score : " + nb);
                    randomGenerator();

                }
                return true;

            }
            return false;

        }

        public void randomGenerator() {
            Random rand = new Random();
            result = rand.nextInt(6);
            if (result == 0) {
                circle.setBackgroundColor(getResources().getColor(R.color.deepblue));
            }
            if (result == 1) {
                circle.setBackgroundColor(getResources().getColor(R.color.green));

            }
            if (result == 2) {
                circle.setBackgroundColor(getResources().getColor(R.color.red));

            }
            if (result == 3) {
                circle.setBackgroundColor(getResources().getColor(R.color.purple));

            }
            if (result == 4) {
                circle.setBackgroundColor(getResources().getColor(R.color.yellow));

            }
            if (result == 5) {
                circle.setBackgroundColor(getResources().getColor(R.color.blue));

            }
            int deplacementX = 0;
            int deplacementY = 0;
            Random randoom = new Random();

            double hauteurcercle = circle.getWidth();
            double largeurcercle = circle.getHeight();
            double width = center.getWidth();
            double height = center.getHeight();
            deplacementX = randoom.nextInt((int) width - (int) largeurcercle);
            deplacementY = randoom.nextInt((int)height - (int) hauteurcercle);
            circle.setY(deplacementY);
            circle.setX(deplacementX);
        }


    }

}



