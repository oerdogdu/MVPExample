package com.example.atoi.mvptrial.data.source.local;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.atoi.mvptrial.data.News;
import com.example.atoi.mvptrial.data.source.DataStore;
import com.example.atoi.mvptrial.di.scopes.ForApplication;
import com.example.atoi.mvptrial.util.SchedulerProvider;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Atoi on 4.01.2018.
 */

public class NewsLocalDataStore implements DataStore {

    private static NewsLocalDataStore INSTANCE;
    private final NewsDbHelper newsDbHelper;
    private Function<Cursor, News> mNewsMapperFunction;
    private final BriteDatabase mDatabaseHelper;

    @Inject
    public NewsLocalDataStore(@ForApplication Context context) {
        newsDbHelper = new NewsDbHelper(context);
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(newsDbHelper, SchedulerProvider.getInstance().io());
        mNewsMapperFunction = this::getSingleNews;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private News getSingleNews(@NonNull Cursor c) {
        String itemId = c.getString(c.getColumnIndexOrThrow(NewsPersistenceContract.NewsEntry.COLUMN_NAME_ENTRY_ID));
        String text = c.getString(c.getColumnIndexOrThrow(NewsPersistenceContract.NewsEntry.COLUMN_NAME_TEXT));
        return new News(itemId, text);
    }

    @SuppressLint("NewApi")
    public Observable<Optional<News>> getNews(@NonNull String newsId) {
        String[] projection = {
                NewsPersistenceContract.NewsEntry.COLUMN_NAME_ENTRY_ID,
                NewsPersistenceContract.NewsEntry.COLUMN_NAME_TYPE,
                NewsPersistenceContract.NewsEntry.COLUMN_NAME_TEXT
        };
        String sql = String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection), NewsPersistenceContract.NewsEntry.TABLE_NAME,
                NewsPersistenceContract.NewsEntry.COLUMN_NAME_ENTRY_ID);
        return mDatabaseHelper.createQuery(NewsPersistenceContract.NewsEntry.TABLE_NAME, sql, newsId)
                .mapToOneOrDefault(cursor -> Optional.of(mNewsMapperFunction.apply(cursor)), Optional.<News> empty())
                .toFlowable(BackpressureStrategy.BUFFER)
                .toObservable();
    }

    @Override
    public Observable<List<News>> getNews() {
        String[] projection = {
                NewsPersistenceContract.NewsEntry.COLUMN_NAME_ENTRY_ID,
                NewsPersistenceContract.NewsEntry.COLUMN_NAME_TYPE,
                NewsPersistenceContract.NewsEntry.COLUMN_NAME_TEXT,
        };
        String sql = String.format("SELECT %s FROM %s", TextUtils.join(",", projection), NewsPersistenceContract.NewsEntry.TABLE_NAME);
        return mDatabaseHelper.createQuery(NewsPersistenceContract.NewsEntry.TABLE_NAME, sql)
                .mapToList(mNewsMapperFunction)
                .toFlowable(BackpressureStrategy.BUFFER).toObservable();
    }

    public void saveNews(News news) {
        ContentValues values = new ContentValues();
        values.put(NewsPersistenceContract.NewsEntry.COLUMN_NAME_ENTRY_ID, news.getId());
        values.put(NewsPersistenceContract.NewsEntry.COLUMN_NAME_TEXT, news.getText());
        mDatabaseHelper.insert(NewsPersistenceContract.NewsEntry.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
}
