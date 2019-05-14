package mds.multigame.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import mds.multigame.R;

public class ActivityUtils {


    public static final int FADE = 0;
    public static final int SLIDE_RIGHT = 1;


    public static void launchActivity(AppCompatActivity activity, Class cls, boolean finish, int animation) {
        Intent intent = new Intent(activity, cls);
        launchActivity(activity, intent, finish, animation);
    }

    public static void launchActivity(AppCompatActivity activity, Intent intent, boolean finish, int animation) {
        activity.startActivity(intent);
        switch (animation) {
            case FADE:
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case SLIDE_RIGHT:
                activity.overridePendingTransition(R.anim.slide_right, R.anim.nothing);
                break;

        }
        if (finish) {
            activity.finish();
        }
    }

    public static void launchActivity(AppCompatActivity activity, Class cls) {
        launchActivity(activity, cls, true, 0);

    }

    public static void launchActivity(AppCompatActivity activity, Intent intent) {
        launchActivity(activity, intent, true, 0);

    }


    public static void addFragmentToActivity(FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(frameId, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void addFragmentToFragment(Fragment parentFragment, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction;
        transaction = parentFragment.getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(frameId, fragment, "coucou");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
