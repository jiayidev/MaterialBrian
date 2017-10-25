package dev.brian.materialbrian.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev.brian.materialbrian.R;
import dev.brian.materialbrian.bean.table.Bookshelf;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/25 0025
 * Description:
 */

public class BookShelfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_DEFAULT = 1;
    private final List<Bookshelf> bookshelfs;
    private Context context;
    private int columns;

    public BookShelfAdapter(Context context, List<Bookshelf> bookshelfs, int columns) {
        this.bookshelfs = bookshelfs;
        this.context = context;
        this.columns = columns;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_DEFAULT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
            return new BookListHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false);
            return new EmptyHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (bookshelfs == null || bookshelfs.isEmpty()) {
            return TYPE_EMPTY;
        } else {
            return TYPE_DEFAULT;
        }
    }

    public int getItemColumnSpan(int position) {
        switch (getItemViewType(position)) {
            case TYPE_DEFAULT:
                return 1;
            default:
                return columns;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookListHolder) {
            final Bookshelf bookshelf = bookshelfs.get(position);
        }
    }


    @Override
    public int getItemCount() {
        if (bookshelfs.isEmpty()) {
            return 1;
        }
        return bookshelfs.size();
    }

    class BookListHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_book_img;
        private final TextView tv_book_title;
        private final AppCompatRatingBar ratingBar_hots;
        private final TextView tv_hots_num;
        private final TextView tv_book_info;
        private final TextView tv_book_description;

        public BookListHolder(View itemView) {
            super(itemView);
            iv_book_img = (ImageView) itemView.findViewById(R.id.iv_book_img);
            tv_book_title = (TextView) itemView.findViewById(R.id.tv_book_title);
            ratingBar_hots = (AppCompatRatingBar) itemView.findViewById(R.id.ratingBar_hots);
            tv_hots_num = (TextView) itemView.findViewById(R.id.tv_hots_num);
            tv_book_info = (TextView) itemView.findViewById(R.id.tv_book_info);
            tv_book_description = (TextView) itemView.findViewById(R.id.tv_book_description);
        }
    }

    class EmptyHolder extends RecyclerView.ViewHolder {
        public EmptyHolder(View itemView) {
            super(itemView);
        }
    }
}
