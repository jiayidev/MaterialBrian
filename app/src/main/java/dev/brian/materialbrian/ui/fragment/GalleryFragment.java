package dev.brian.materialbrian.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.brian.materialbrian.R;
import dev.brian.materialbrian.api.presenter.impl.BookshelfPresenterImpl;
import dev.brian.materialbrian.api.view.IBookListView;
import dev.brian.materialbrian.bean.table.Bookshelf;
import dev.brian.materialbrian.ui.activity.MainActivity;
import dev.brian.materialbrian.ui.adapter.BookShelfAdapter;
import dev.brian.materialbrian.utils.common.DensityUtils;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/24 0024
 * Description:
 */

public class GalleryFragment extends BaseFragment implements IBookListView,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private GridLayoutManager mLayoutManager;
    private BookShelfAdapter mListAdapter;
    private List<Bookshelf> mBookshelfs;
    private BookshelfPresenterImpl mBookshelfPresenter;
    private int spanCount = 1;

    public static GalleryFragment mInstance;

    public static GalleryFragment newInstance() {
        Bundle args = new Bundle();

        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.gallery_fragment, container, false);
    }

    @Override
    protected void initEvents() {
        mToolbar.setTitle("Bookshelf");
        spanCount = getResources().getInteger(R.integer.gallery_span_count);
        mBookshelfPresenter = new BookshelfPresenterImpl(this);
        mBookshelfs = new ArrayList<>();
        mSwipeRefreshLayout.setColorSchemeColors(R.color.recycler_color1, R.color.recycler_color2,
                R.color.recycler_color3, R.color.recycler_color4);

        // 设置布局管理
        mLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mListAdapter.getItemColumnSpan(position);
            }
        });
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // 设置Adapter
        mListAdapter = new BookShelfAdapter(getActivity(), mBookshelfs, spanCount);
        mRecyclerView.setAdapter(mListAdapter);

        // 设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbar(mToolbar);
        ((MainActivity) getActivity()).setFab(mFab);
        init();
    }

    private void init() {

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof List) {
            mBookshelfs.clear();
            mBookshelfs.addAll((List<Bookshelf>) result);
            mListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addData(Object result) {

    }

    @Override
    public void onRefresh() {
        mBookshelfPresenter.loadBookshelf();
    }

    private void onLoadMore() {

    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;
        private int mScrollThreshold = DensityUtils.dp2px(getActivity(), 1);

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mListAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;

            if (isSignificantDelta) {
                if (dy > 0) {
                    ((MainActivity) getActivity()).hideFloatingBar();
                } else {
                    ((MainActivity) getActivity()).showFloatingBar();
                }
            }

            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }
    }
}
