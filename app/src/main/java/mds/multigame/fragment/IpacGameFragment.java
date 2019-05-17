package mds.multigame.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mds.multigame.EndActivity;
import mds.multigame.R;
import mds.multigame.utils.ActivityUtils;


public class IpacGameFragment extends Fragment{
    public static final String TAG = "IpacGameFragment";
    protected static final int NUMBERTRY = 10;
    private TextView moreLess;
    private TextView numbertryTV;
    private Button btnValidate;
    protected EditText numberInsert;
    protected String scorefinal;
    private int numberfind;
    private int numberoftry = NUMBERTRY;
    private String nomdujeu = "Ipac Game";
    public static final String NAME_IPAC = "Ipac Game";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ipac_game, container, false);

            numbertryTV = view.findViewById(R.id.main_nbtry);
            numberInsert = view.findViewById(R.id.main_nbInsert);
            moreLess = view.findViewById(R.id.main_more_less);
            btnValidate = view.findViewById(R.id.main_validate);
            moreLess.setVisibility(View.VISIBLE);

            numbertryTV.setText(getResources().getString(R.string.nb_essaie, numberoftry));
            final int numberfind = (int) (Math.random() * 100);



            btnValidate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!numberInsert.getText().toString().isEmpty()) {
                        int number = Integer.valueOf(numberInsert.getText().toString());
                        if (number == numberfind) {
                            scorefinal = String.valueOf(numberoftry) + " essais" ;
                            Intent intent = new Intent((AppCompatActivity) IpacGameFragment.this.getActivity(), EndActivity.class);
                            intent.putExtra(EndActivity.SCORE, scorefinal);
                            intent.putExtra(EndActivity.NOMDUJEU, nomdujeu);
                            intent.putExtra(EndActivity.RESULTAT, numberoftry );
                            ActivityUtils.launchActivity((AppCompatActivity) IpacGameFragment.this.getActivity(), intent, false, 0);
                            getFragmentManager().popBackStack();
                        } else {
                            numberoftry--;

                            if (numberoftry == 0) {

                            }

                            numbertryTV.setText(getResources().getString(R.string.nb_essaie, numberoftry));
                            numberInsert.setText("");
                            moreLess.setText(getResources().getString(number < numberfind ? R.string.indicator_plus : R.string.indicator_moins));
                            displayTextWithFade(moreLess);

                        }
                    }

                }
            });

            numberInsert.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
                    if ( (i == EditorInfo.IME_ACTION_DONE)) {
                        btnValidate.performClick();
                        //closeKeyboard();
                    }

                    return false;
                }
            });

        return view;

        }

/*        private void closeKeyboard()
        {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(),0);
        }
*/


        private void displayTextWithFade(final  View view)
        {
            view.setVisibility(view.VISIBLE);
            view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hideTextWithFade(view);
                }
            },400);
        }

        private void hideTextWithFade(View view)
        {
            view.setVisibility(view.INVISIBLE);
            view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        }


    }


