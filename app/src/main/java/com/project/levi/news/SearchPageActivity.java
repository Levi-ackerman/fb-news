package com.project.levi.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.levi.news.adapter.PageAdapter;
import com.project.levi.news.data.models.Page;
import com.project.levi.news.ui.SimpleDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPageActivity extends AppCompatActivity implements Response.ErrorListener {
    private static final String TAG = "SearchPageActivity";

    @BindView(R.id.recycler_pages)
    RecyclerView mPagesRecycler;
    @BindView(R.id.txt_search)
    EditText mTxtSearch;
    private PageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPageAdapter = new PageAdapter();
        mPagesRecycler.setLayoutManager(new LinearLayoutManager(this));
        mPagesRecycler.addItemDecoration(new SimpleDividerItemDecoration(8));
        mPagesRecycler.setHasFixedSize(true);
        mPagesRecycler.setAdapter(mPageAdapter);

        mTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchPage(s.toString());
            }
        });

    }

    private void searchPage(String s) {
        String[] fields = new String[]{"id", "about"};
       /* String url = Utils.createSearchQuery("page", s, fields);
        Log.d(TAG, "Url : " + url);
        BaseRequest<BaseListResponse<Page>> request = new BaseRequest<>(Request.Method.GET, url, this, null);

        request.setListener(new Response.Listener<BaseListResponse<Page>>() {
            @Override
            public void onResponse(BaseListResponse<Page> response) {
                Log.d(TAG, "Data : " + response.getData());
            }
        });
        App.getInstance().addToRequestQueue(request);*/

        Bundle params = new Bundle();
        params.putString("type", "page");
        params.putString("q", s);
        params.putString("fields", "id,name,about");
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
                            try {
                                String json = object.getString("data");
                                Type listType = new TypeToken<ArrayList<Page>>() {
                                }.getType();

                                List<Page> pageList = new Gson().fromJson(json, listType);
                                mPageAdapter.setPageList(pageList);
                                mPageAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).executeAsync();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_page, menu);
        return true;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
