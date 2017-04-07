package com.kazekim.techcrunch;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.kazekim.techcrunch.fragment.MainFragment;
import com.kazekim.techcrunch.databinding.ActivityMainBinding;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public enum ActivityPage {
        MAIN,
        DETAIL
    }

    private ActivityPage currentPage;

    private static int fragmentContainerResId = R.id.contentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.binding.toolbar.setTitle(R.string.app_name);

        currentPage = ActivityPage.MAIN;

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(fragmentContainerResId, MainFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        switch (currentPage) {
            case MAIN:

                break;
            case DETAIL:
                this.currentPage = ActivityPage.MAIN;
                break;
        }
        super.onBackPressed();
    }

    /**
     * Navigate
     */

    private void openFragment(Fragment fragment)
    {
        JHFragmentManager.replaceFragmentWithAnimation(this, fragment, fragmentContainerResId);
    }
}
