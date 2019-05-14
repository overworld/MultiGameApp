package mds.multigame.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import mds.multigame.MainActivity;
import mds.multigame.R;
import mds.multigame.utils.ActivityUtils;

public class SwipeAndTapFragment extends Fragment {

    private Button start;
    private RelativeLayout relativeLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).viewPager.setPagingEnabled(false);
        View view = inflater.inflate(R.layout.fragment_fast_tap, container, false);
        start = view.findViewById(R.id.commencer);
        relativeLayout = view.findViewById(R.id.tapstart);
        //Instancier vos composants graphique ici (faîtes vos findViewById)

        boolean bool = getArguments().getBoolean("isSwape");
        String game = getArguments().getString("drag");
        String ipac = getArguments().getString("ipac");



        if(game == "ok") {
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragment = new DragnDropFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("drag", "drag");
                    fragment.setArguments(bundle);
                    ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragment, relativeLayout.getId(), DragnDropFragment.TAG);
                }
            });
        } else if (ipac == "ok") {
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new IpacGameFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("ipac", "ipac");
                    fragment.setArguments(bundle);
                    ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragment, relativeLayout.getId(), IpacGameFragment.TAG);
                }
            });

        } else if (bool == false) {
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragment = new FastTapGameFragment();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("key", false);
                    fragment.setArguments(bundle);
                    ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragment, relativeLayout.getId(), FastTapGameFragment.TAG);
                }
            });
        } else if (bool == true) {
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new FastTapGameFragment();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("key", true);
                    fragment.setArguments(bundle);
                    ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragment, relativeLayout.getId(), FastTapGameFragment.TAG);
                }
            });
        }



        return view;

    }
}
