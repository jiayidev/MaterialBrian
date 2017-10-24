package dev.brian.materialbrian.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.util.List;

import dev.brian.materialbrian.R;
import dev.brian.materialbrian.bean.http.douban.BookInfoResponse;
import dev.brian.materialbrian.ui.activity.BaseActivity;
import dev.brian.materialbrian.utils.common.UIUtils;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/24 0024
 * Description:
 */

public class BookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_DEFAULT = 1;
    private final List<BookInfoResponse> bookInfoResponses;
    private Context mContext;
    private int columns;

    public BookListAdapter(Context context, List<BookInfoResponse> responses, int columns) {
        this.bookInfoResponses = responses;
        this.columns = columns;
        this.mContext = context;
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
        if (bookInfoResponses == null || bookInfoResponses.isEmpty()) {
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookListHolder) {
            final BookInfoResponse bookInfo = bookInfoResponses.get(position);
            Glide.with(mContext)
                    .load(bookInfo.getImages().getLarge())
                    .into(((BookListHolder) holder).iv_book_img);
            ((BookListHolder) holder).tv_book_title.setText(bookInfo.getTitle());
            ((BookListHolder) holder).ratingBar_hots.setRating(Float.valueOf(bookInfo.getRating().getAverage()) / 2);
            ((BookListHolder) holder).tv_hots_num.setText(bookInfo.getRating().getAverage());
            ((BookListHolder) holder).tv_book_info.setText(bookInfo.getInfoString());
            ((BookListHolder) holder).tv_book_description.setText("\u3000" + bookInfo.getSummary());
            ((BookListHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (bookInfoResponses.isEmpty()) {
            return 1;
        }
        return bookInfoResponses.size();
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
