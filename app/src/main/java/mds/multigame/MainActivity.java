package mds.multigame;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import mds.multigame.fragment.DragnDropFragment;
import mds.multigame.fragment.FastTapGameFragment;
import mds.multigame.fragment.IpacGameFragment;
import mds.multigame.fragment.SettingsFragment;
import mds.multigame.fragment.SwipeAndTapFragment;
import mds.multigame.utils.CustomViewPager;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    public CustomViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.activity_main_tab_layout);

        viewPager.addOnPageChangeListener(this);
        tabLayout.addOnTabSelectedListener(this);


        final ArrayList<Fragment> fragments = new ArrayList<>();



        Bundle bundle3 = new Bundle();
        bundle3.putString("drag", "ok");
        SwipeAndTapFragment drag = new SwipeAndTapFragment();
        drag.setArguments(bundle3);
        fragments.add(drag);

        Bundle bundle = new Bundle();
        bundle.putBoolean("isSwape", false);
        SwipeAndTapFragment swipeAndTapFragment = new SwipeAndTapFragment();
        swipeAndTapFragment.setArguments(bundle);
        fragments.add(swipeAndTapFragment);

        Bundle bundle1 = new Bundle();
        bundle1.putBoolean("isSwape", true);
        SwipeAndTapFragment swipeAndTapFragment1 = new SwipeAndTapFragment();
        swipeAndTapFragment1.setArguments(bundle1);
        fragments.add(swipeAndTapFragment1);

        fragments.add(new IpacGameFragment());
        fragments.add(new SettingsFragment());

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "DragnDrop";
                    case 1:
                        return "Swipe";
                    case 2:
                        return "Fast Tap";
                    case 3:
                        return "Ipac Game";
                    case 4:
                        return "Settings";
                    default:
                        return "";
                }
            }
        };
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {


        for (Fragment currentfragment : getSupportFragmentManager().getFragments()) {

            for (Fragment subfragment : currentfragment.getChildFragmentManager().getFragments()) {

                if (subfragment.getTag() != null && subfragment.getTag().equals(FastTapGameFragment.TAG))
                {
                    subfragment.getFragmentManager().popBackStack();
                }
            }
        }
//        getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getFragments();
//        Fragment fragment = getSupportFragmentManager().getFragments();
//        for (int i = 0; i<fragments.size(); i++)
//        {
//            Fragment fragment = fragments.get(i);
//            if (fragment instanceof FastTapGameFragment)
//            {
//                fragment.getFragmentManager().popBackStack();
//            }
//        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
