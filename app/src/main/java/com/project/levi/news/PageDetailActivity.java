package com.project.levi.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.JsonObject;
import com.project.levi.news.adapter.FeedAdapter;
import com.project.levi.news.common.Constant;
import com.project.levi.news.common.ParseJsonUtils;
import com.project.levi.news.common.Utils;
import com.project.levi.news.data.models.Page;
import com.project.levi.news.ui.SimpleDividerItemDecoration;

import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PageDetailActivity extends AppCompatActivity {
    private static final String TAG = "PageDetailActivity";

    @BindView(R.id.recycler_feeds)
    RecyclerView mFeedRecycler;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.img_cover)
    ImageView imgCover;
    @BindView(R.id.img_avatar)
    CircleImageView imgAvatar;
    @BindView(R.id.text_name)
    TextView textName;

    private FeedAdapter mFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail);
        ButterKnife.bind(this);

        loadPageData();

        mFeedRecycler.setLayoutManager(new LinearLayoutManager(this));
        mFeedRecycler.setHasFixedSize(true);
        mFeedRecycler.addItemDecoration(new SimpleDividerItemDecoration(null, (int) Utils.convertDpToPixel(16)));
        mFeedAdapter = new FeedAdapter();
        mFeedRecycler.setAdapter(mFeedAdapter);
    }

    private void loadPageData() {
        Intent intent = getIntent();
        if (intent != null) {
            String pageId = intent.getStringExtra(Constant.KEY_PAGE_ID);
            if (pageId != null) {
                Bundle params = new Bundle();
                params.putString("fields", "id,name,about,picture,cover,posts");

                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/" + pageId,
                        params,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                JSONObject jsonObject = response.getJSONObject();
                                Page page = ParseJsonUtils.parseJsonObjectPage(jsonObject);
                                if(page != null){
                                    textName.setText(page.getName());
                                    if(page.getCover() != null){
                                        Glide.with(getBaseContext()).load(page.getCover().getSource())
                                                .thumbnail(0.5f)
                                                .crossFade()
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(imgCover);
                                    }
                                    if(page.getPicture() != null){
                                        Glide.with(getBaseContext()).load(page.getPicture().getUrl())
                                                .thumbnail(0.5f)
                                                .crossFade()
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(imgAvatar);
                                    }
                                }
                            }
                        }
                ).executeAsync();

            }
        }
    }
}
