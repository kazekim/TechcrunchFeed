package com.kazekim.techcrunch.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kazekim.techcrunch.R;
import com.kazekim.techcrunch.adapter.NewsRecyclerAdapter;
import com.kazekim.techcrunch.callback.NewsCallBack;
import com.kazekim.techcrunch.databinding.FragmentMainBinding;
import com.kazekim.techcrunch.datahelper.Contextor;
import com.kazekim.techcrunch.datahelper.NewsDataHelper;
import com.kazekim.techcrunch.datatype.MutableInteger;
import com.kazekim.techcrunch.factory.TCAPIFactory;
import com.kazekim.techcrunch.model.NewsDao;
import com.kazekim.techcrunch.utils.JHToast;
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

    private NewsDataHelper newsDataHelper;
    MutableInteger lastPositionInteger;

    protected boolean isLoading = false;

    public interface MainFragmentListener
    {
        void onNewsItemClicked(int position, NewsDao dao);
    }

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        NewsCallBack.cancelAll();
        newsDataHelper.destroy();

    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here

        newsDataHelper = NewsDataHelper.getInstance();
        lastPositionInteger = new MutableInteger(-1);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        recyclerAdapter = new NewsRecyclerAdapter(getContext());

        recyclerAdapter.setOnRecyclerViewItemClickedListener(onRecyclerViewItemClickedListener);
        binding.recyclerView.setHasFixedSize(false);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(llm);

        binding.swipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);

        reloadData();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here

        outState.putBundle("newsDataHelper",
                newsDataHelper.onSaveInstanceState());
        outState.putBundle("newsLastPositionInteger", lastPositionInteger.onSaveInstanceState());
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here

        newsDataHelper.onRestoreInstanceState(savedInstanceState.getBundle("newsDataHelper"));
        lastPositionInteger.onRestoreInstanceState(savedInstanceState.getBundle("newsLastPositionInteger"));
    }

    /**
     * Layout Handler
     */

    private void showButtonNewPhotos() {
        binding.btnNewPhotos.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(
                Contextor.getInstance().getContext(),
                R.anim.fade_in
        );
        binding.btnNewPhotos.startAnimation(anim);

    }

    private void hideButtonNewPhotos() {
        binding.btnNewPhotos.setVisibility(View.GONE);
        Animation anim = AnimationUtils.loadAnimation(
                Contextor.getInstance().getContext(),
                R.anim.fade_out
        );
        binding.btnNewPhotos.startAnimation(anim);
    }

    private void setupData()
    {
        recyclerAdapter.setNewsDataHelper(newsDataHelper);
        binding.recyclerView.setAdapter(recyclerAdapter);
    }

    /**
     * Data Handler
     */

    private void reloadDataNewer() {
        if (isLoading)
            return;
        isLoading = true;


        TCAPIFactory.newsLoadList(loadNewsCallBackListener);
    }

    private void reloadData() {
        if (isLoading)
            return;
        isLoading = true;
        TCAPIFactory.newsLoadList(loadNewsCallBackListener);
    }

    private void loadMoreData() {
        if (isLoading)
            return;
        isLoading = true;

        TCAPIFactory.newsLoadList(loadNewsCallBackListener);
    }

    /**
     * Listener
     */

    NewsCallBack.NewsListCallBackListener loadNewsCallBackListener = new NewsCallBack.NewsListCallBackListener() {
        @Override
        public void onNewsListLoadSuccess(NewsCallBack.Mode mode, List<NewsDao> newsList) {
            binding.swipeRefreshLayout.setRefreshing(false);
            isLoading = false;
            newsDataHelper.setNewsList(newsList);

            setupData();
        }

        @Override
        public void onNewsListLoadFail(NewsCallBack.Mode mode, String message) {
            JHToast.show(message);

            binding.swipeRefreshLayout.setRefreshing(false);
            isLoading = false;

            if(newsDataHelper.getNewsList() != null && newsDataHelper.getNewsCount() > 0){
                setupData();
            }
        }

        @Override
        public void onConnectionFail(NewsCallBack.Mode callBack, String message) {

        }
    };

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == binding.btnNewPhotos) {
                binding.recyclerView.smoothScrollToPosition(0);
                hideButtonNewPhotos();
            }
        }
    };

    SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            reloadData();
        }
    };

    NewsRecyclerAdapter.OnRecyclerViewItemClickedListener onRecyclerViewItemClickedListener = new NewsRecyclerAdapter.OnRecyclerViewItemClickedListener() {
        @Override
        public void onRecyclerViewItemClicked(NewsViewHolder viewHolder, int position) {

            MainFragmentListener listener = (MainFragmentListener) getActivity();
            listener.onNewsItemClicked(position, newsDataHelper.getNewsAtPosition(position));


        }

    };

    View.OnClickListener onBackButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().onBackPressed();
        }
    };

}
