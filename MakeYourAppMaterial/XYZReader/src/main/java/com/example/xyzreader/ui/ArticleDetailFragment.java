package com.example.xyzreader.ui;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.data.ArticleLoader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


/**
 * A fragment representing a single Article detail screen. This fragment is
 * either contained in a {@link ArticleListActivity} in two-pane mode (on
 * tablets) or a {@link ArticleDetailActivity} on handsets.
 */
public class ArticleDetailFragment extends Fragment implements
        android.app.LoaderManager.LoaderCallbacks<Cursor> {
    public static final String ARG_ITEM_ID = "ARG_ITEM_ID";

    private long mItemId;
    ImageView mPhotoView;
    LinearLayout metaBar;
    TextView mTitleView;
    TextView mAuthorView;
    TextView mBodyView;
    FloatingActionButton mShareFab;
    Toolbar mToolbar;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    AppBarLayout mAppBarLayout;
    CardView mCard;
    private View view;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleDetailFragment() {
    }

    public static ArticleDetailFragment newInstance(long itemId) {
        Bundle arguments = new Bundle();
        arguments.putLong(ARG_ITEM_ID, itemId);
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItemId = getArguments().getLong(ARG_ITEM_ID);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // In support library r8, calling initLoader for a fragment in a FragmentPagerAdapter in
        // the fragment's onCreate may cause the same LoaderManager to be dealt to multiple
        // fragments because their mIndex is -1 (haven't been added to the activity yet). Thus,
        // we do this in onActivityCreated.
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        mPhotoView = (ImageView) view.findViewById(R.id.photo);
        metaBar = (LinearLayout) view.findViewById(R.id.meta_bar);
        mTitleView = (TextView) view.findViewById(R.id.article_title);
        mAuthorView = (TextView) view.findViewById(R.id.article_author);
        mBodyView = (TextView) view.findViewById(R.id.article_body);
        mShareFab = (FloatingActionButton) view.findViewById(R.id.share_fab);
        mToolbar = (Toolbar) view.findViewById(R.id.detail_toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.app_bar);
        mCard = (CardView) view.findViewById(R.id.card);

        return view;
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return ArticleLoader.newInstanceForItemId(getActivity(), mItemId);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> cursorLoader, Cursor cursor) {
        if (cursor == null || cursor.isClosed() || !cursor.moveToFirst()) {
            return;
        }

        final String title = cursor.getString(ArticleLoader.Query.TITLE);
        String author = Html.fromHtml(
                DateUtils.getRelativeTimeSpanString(
                        cursor.getLong(ArticleLoader.Query.PUBLISHED_DATE),
                        System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                        DateUtils.FORMAT_ABBREV_ALL).toString()
                        + " by "
                        + cursor.getString(ArticleLoader.Query.AUTHOR)).toString();
        final String body = Html.fromHtml(cursor.getString(ArticleLoader.Query.BODY)).toString();
        String photo = cursor.getString(ArticleLoader.Query.PHOTO_URL);

        if (mToolbar != null) {
            if (mCard == null) {
                mToolbar.setTitle(title);
            }
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        } else{
            System.out.println("TOOLBAR IS NULLLLLLLLLLLLLL");
        }

        mTitleView.setText(title);
        mAuthorView.setText(author);
        mBodyView.setText(body);

        Picasso.with(getActivity()).load(photo)
                .error(android.R.drawable.ic_delete)
                .fit().
                into(mPhotoView, new Callback() {
                    @Override
                    public void onSuccess() {

                        Bitmap bitmap =((BitmapDrawable)mPhotoView.getDrawable()).getBitmap();
                        changeUIColors(bitmap);
                        //dynamicColor(mPhotoView,(LinearLayout) view.findViewById(R.id.meta_bar));
                    }

                    @Override
                    public void onError() {

                    }
                });

        mShareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setText(body)
                        .getIntent(), getString(R.string.action_share)));
            }
        });
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> cursorLoader) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void changeUIColors(Bitmap bitmap) {
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int defaultColor = 0xFF333333;
                int darkMutedColor = palette.getDarkMutedColor(defaultColor);
                metaBar.setBackgroundColor(darkMutedColor);
                if (mCollapsingToolbarLayout != null) {
                    mCollapsingToolbarLayout.setContentScrimColor(darkMutedColor);
                    mCollapsingToolbarLayout.setStatusBarScrimColor(darkMutedColor);
                }
            }
        });
    }
}
