package com.kazekim.techcrunch.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kazekim.techcrunch.R;
import com.kazekim.techcrunch.adapter.NewsRecyclerAdapter;
import com.kazekim.techcrunch.databinding.FragmentMainBinding;
import com.kazekim.techcrunch.viewholder.NewsViewHolder;

import java.util.List;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/7/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class MainFragment extends Fragment {

    /**
     * Variables
     */

    private FragmentMainBinding binding;
    private NewsRecyclerAdapter recyclerAdapter;

    NewsRecyclerAdapter.OnRecyclerViewItemClickedListener listener = new NewsRecyclerAdapter.OnRecyclerViewItemClickedListener() {
        @Override
        public void onRecyclerViewItemClicked(NewsViewHolder viewHolder, int position) {

        }
    };

    /**
     * Methods
     */

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View rootView = binding.getRoot();
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        recyclerAdapter = new NewsRecyclerAdapter(getContext());

        recyclerAdapter.setOnRecyclerViewItemClickedListener(listener);
        binding.recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }
}
