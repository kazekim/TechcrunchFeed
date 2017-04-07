package com.kazekim.techcrunch.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kazekim.techcrunch.R;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/7/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class JHFragmentHelper {

    public enum JHFragmentNavigateType{
        Replace,
        RemoveRecentAll
    }

    public static Fragment findFragmentById(AppCompatActivity activity, int resId )
    {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(resId);
        return fragment;
    }

    public static void navigateFragmentWithAnimation(AppCompatActivity activity, Fragment fragment, int targetResId, JHFragmentNavigateType navigateType)
    {
        switch (navigateType){
            case Replace:
                JHFragmentHelper.replaceFragmentWithAnimation(activity, fragment, targetResId);
                break;
            case RemoveRecentAll:
                JHFragmentHelper.removeAllFragmentsAndReplaceNew(activity, fragment, targetResId);
                break;
        }
    }

    public static void replaceFragmentWithAnimation(AppCompatActivity activity, Fragment fragment, int targetResId)
    {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();

        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        ft.replace(targetResId, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void removeAllFragmentsAndReplaceNew(AppCompatActivity activity, Fragment fragment, int targetResId)
    {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        for(int i = 0; i < fragmentManager.getBackStackEntryCount() - 1 ; i++){
            fragmentManager.popBackStack();
        }
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(targetResId, fragment)
                .commit();
    }

    public static void addFragment(AppCompatActivity activity, Fragment fragment, int targetResId)
    {
        activity.getSupportFragmentManager().beginTransaction()
                .add(targetResId,fragment)
                .commit();
    }

    public static void addFragmentOverlay(AppCompatActivity activity, Fragment fragment)
    {
        FragmentTransaction ft  = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.add(android.R.id.content, fragment);
        ft.attach(fragment);
        ft.commit();
    }

    public static void removeFragmentOverlay(AppCompatActivity activity, Fragment fragment)
    {
        FragmentTransaction ft  = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.remove(fragment);
        ft.commit();

    }
}
