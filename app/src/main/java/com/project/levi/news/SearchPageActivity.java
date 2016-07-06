package com.project.levi.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.project.levi.news.adapter.PageAdapter;
import com.project.levi.news.adapter.listener.OnItemSelectListener;
import com.project.levi.news.common.Constant;
import com.project.levi.news.common.ParseJsonUtils;
import com.project.levi.news.common.Utils;
import com.project.levi.news.data.models.Page;
import com.project.levi.news.ui.SimpleDividerItemDecoration;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPageActivity extends AppCompatActivity implements
        SearchView.OnQueryTextListener, OnItemSelectListener<Page> {
    private static final String TAG = "SearchPageActivity";

    @BindView(R.id.recycler_pages)
    RecyclerView mPagesRecycler;
    /* @BindView(R.id.txt_search)
     EditText mTxtSearch;*/
    private PageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPageAdapter = new PageAdapter(this);
        mPagesRecycler.setLayoutManager(new LinearLayoutManager(this));
        mPagesRecycler.setHasFixedSize(true);
        mPagesRecycler.addItemDecoration(new SimpleDividerItemDecoration(this, -1));
        mPagesRecycler.setAdapter(mPageAdapter);

    }

    private void searchPage(String s) {
        Bundle params = new Bundle();
        params.putString("type", "page");
        params.putString("q", s);
        params.putString("fields", "id,name,about,picture");
        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/search",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        if (response.getError() == null) {
                            JSONObject object = response.getJSONObject();
                            List<Page> pageList = ParseJsonUtils.parseJsonObjectToListPage(object);
                            mPageAdapter.setPageList(pageList);
                            mPageAdapter.notifyDataSetChanged();
                        }
                    }
                }).executeAsync();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_page, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setIconifiedByDefault(false);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams
                .MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        searchView.setLayoutParams(params);
        MenuItemCompat.expandActionView(searchItem);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchPage(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemClick(Page page) {
        Intent intent = new Intent(SearchPageActivity.this, PageDetailActivity.class);
        intent.putExtra(Constant.KEY_PAGE_ID, page.getId());
        startActivity(intent);
    }
}
