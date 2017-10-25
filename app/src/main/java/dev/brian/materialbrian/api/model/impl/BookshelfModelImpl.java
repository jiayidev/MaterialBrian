package dev.brian.materialbrian.api.model.impl;

import android.content.ContentValues;
import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import dev.brian.materialbrian.api.ApiCompleteListener;
import dev.brian.materialbrian.api.common.DatabaseHelper;
import dev.brian.materialbrian.api.model.IBookshelfModel;
import dev.brian.materialbrian.bean.http.douban.BaseResponse;
import dev.brian.materialbrian.bean.table.Bookshelf;
import dev.brian.materialbrian.utils.common.UIUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/25 0025
 * Description:
 */

public class BookshelfModelImpl implements IBookshelfModel {
    private SqlBrite sqlBrite = SqlBrite.create();
    private BriteDatabase db = sqlBrite.wrapDatabaseHelper(
            DatabaseHelper.getInstance(UIUtils.getContext()), Schedulers.io());

    private Subscription subscribe;


    @Override
    public void loadBookshelf(ApiCompleteListener listener) {
        if (db != null) {
            Observable<SqlBrite.Query> bookshelf = db.createQuery("bookshelf", "SELECT * " +
                    "FROM bookshelf");
            subscribe = bookshelf.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<SqlBrite.Query>() {
                @Override
                public void call(SqlBrite.Query query) {
                    Cursor cursor = query.run();
                    if (cursor == null || cursor.getCount() < 0) {
                        return;
                    }
                    List<Bookshelf> bookshelfs = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        Bookshelf bookshelfBean = new Bookshelf();
                        bookshelfBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                        bookshelfBean.setBookCount(cursor.getInt(cursor.getColumnIndex("bookCount")));
                        bookshelfBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                        bookshelfBean.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
                        bookshelfs.add(bookshelfBean);
                    }
                    listener.onComplected(bookshelfs);
                }
            });
        } else {
            listener.onFailed(new BaseResponse(500, "db error : init"));
        }
    }

    @Override
    public void addBookshelf(String title, String remark, ApiCompleteListener listener) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("title", title);
            values.put("remark", remark);
            db.insert("bookshelf", values);
        } else {
            listener.onFailed(new BaseResponse(500, "db error : add"));
        }
    }

    @Override
    public void updateBookshelf(String id, String title, String remark, ApiCompleteListener listener) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("title", title);
            values.put("remark", remark);
            db.update("bookshelf", values, "id = ?", id);
        } else {
            listener.onFailed(new BaseResponse(500, "db error : update"));
        }
    }

    @Override
    public void deleteBookshelf(String id, ApiCompleteListener listener) {
        if (db != null) {
            db.delete("bookshelf", "id = ?", id);
        } else {
            listener.onFailed(new BaseResponse(500, "db error : delete"));
        }
    }

    @Override
    public void unSubscribe() {
        if (subscribe != null) {
            subscribe.unsubscribe();
            db.close();
        }
    }
}
