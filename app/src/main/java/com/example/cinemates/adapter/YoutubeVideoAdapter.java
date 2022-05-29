package com.example.cinemates.adapter;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ListItemYtVideoBinding;
import com.example.cinemates.model.Video;
import com.example.cinemates.util.Constants;
import com.example.cinemates.util.RecyclerViewEmptySupport;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 29/05/2022 at 07:59
 */
public class YoutubeVideoAdapter extends RecyclerViewEmptySupport.Adapter<YoutubeVideoAdapter.YoutubeViewHolder> {

    private static final String TAG = YoutubeVideoAdapter.class.getSimpleName();
    private ArrayList<Video> dataList;

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemYtVideoBinding mBinding = ListItemYtVideoBinding.inflate(layoutInflater, parent, false);
        return new YoutubeViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, final int position) {

        final Video video = dataList.get(position);
        holder.mBinding.setVideo(video);
        holder.mBinding.executePendingBindings();
        /*  initialize the thumbnail image view , we need to pass Developer Key */
        holder.mBinding.thumbnailView.initialize(Constants.YT_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is success, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(video.getKey());

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed
                Log.e(TAG, "Youtube Initialization Failure");

            }
        });
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getKey()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + video.getKey()));
                try {
                    view.getContext().startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    view.getContext().startActivity(webIntent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public void setDataList(ArrayList<Video> dataList) {
        this.dataList = dataList;
    }

    static class YoutubeViewHolder extends RecyclerView.ViewHolder {
        private ListItemYtVideoBinding mBinding;

        public YoutubeViewHolder(ListItemYtVideoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }
    }
}
