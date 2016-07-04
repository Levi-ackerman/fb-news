package com.project.levi.news.data.providers;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import com.project.levi.news.data.models.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levi on 7/4/2016.
 */

public class PageSuggestionProvider extends ContentProvider {
    private static final String TAG = "PageSuggestionProvider";

    private static final String AUTHORITY = "com.project.levi.news.pagesuggestion";

    private static final int TYPE_ALL_SUGGESTIONS = 1;
    private static final int TYPE_SINGLE_SUGGESTION = 2;

    private UriMatcher mUriMatcher;
    private List<Page> mPageList;

    @Override
    public boolean onCreate() {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "/#", TYPE_SINGLE_SUGGESTION);
        mUriMatcher.addURI(AUTHORITY, "suggestions/search_suggest_query/*", TYPE_ALL_SUGGESTIONS);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (mPageList == null || mPageList.isEmpty()) {
            Log.d(TAG, "[query] load from API");
            dummy();
        } else {
            Log.d(TAG, "[query] Cached");
        }
        MatrixCursor cursor = new MatrixCursor(new String[]{
                BaseColumns._ID,
                SearchManager.SUGGEST_COLUMN_TEXT_1,
                SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID
        });
        if (mUriMatcher.match(uri) == TYPE_ALL_SUGGESTIONS) {
            if (mPageList != null) {
                String query = uri.getLastPathSegment().toUpperCase();
                int limit = Integer.parseInt(uri.getQueryParameter(SearchManager.SUGGEST_PARAMETER_LIMIT));
                int length = mPageList.size();
                Page page;
                for (int i = 0; i < length && cursor.getCount() < limit; i++) {
                    page = mPageList.get(i);
                    cursor.addRow(new Object[]{i, page.getAbout(), page.getId()});
                }
            }
        }else if(mUriMatcher.match(uri) == TYPE_SINGLE_SUGGESTION){
            int position = Integer.parseInt(uri.getLastPathSegment());
            Page page = mPageList.get(position);
            cursor.addRow(new Object[]{position, page.getAbout(), page.getId()});
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private void dummy() {
        Page page;
        mPageList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            page = new Page();
            page.setId("id_" + i);
            page.setAbout("About _ " + i);
            mPageList.add(page);
        }
    }
}
