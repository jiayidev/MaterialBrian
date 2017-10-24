package dev.brian.materialbrian.api.common.service;

import dev.brian.materialbrian.bean.http.douban.BookListResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/24 0024
 * Description:
 */

public interface IBookService {
    @GET("book/search")
    Observable<Response<BookListResponse>> getBookList(@Query("q") String q,
                                                       @Query("tag") String tag,
                                                       @Query("start") int start,
                                                       @Query("count") int count,
                                                       @Query("fields") String fields);
}
