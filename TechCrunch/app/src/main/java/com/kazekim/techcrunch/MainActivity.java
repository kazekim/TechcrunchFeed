package com.kazekim.techcrunch;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kazekim.techcrunch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public enum ActivityPage {
        MAIN,
        DETAIL
    }

    private ActivityPage currentPage;
    Toolbar toolbar;
    TextView tvToolBarTitle;

    private static int fragmentContainerResId = R.id.contentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        this.binding.toolbar.setTitle(R.string.app_name);

    }
}
