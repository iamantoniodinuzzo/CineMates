package com.example.cinemates.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ListItemBackdropBinding;
import com.example.cinemates.databinding.ListItemPosterBinding;
import com.example.cinemates.model.data.ImagesResponse;
import com.example.cinemates.util.Constants;
import com.example.cinemates.util.DialogFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 23/06/2022 at 17:23
 */
public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ImageViewHolder> {
    private final List<ImagesResponse.Image> dataList = new ArrayList<>();
    private final Context mContext;

    public ImageRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == ImagesResponse.Image.POSTER) {
            ListItemPosterBinding mediaBinding = ListItemPosterBinding.inflate(layoutInflater, parent, false);
            return new ImageViewHolder(mediaBinding);
        } else {
            ListItemBackdropBinding mediaBinding = ListItemBackdropBinding.inflate(layoutInflater, parent, false);
            return new ImageViewHolder(mediaBinding);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImagesResponse.Image image = dataList.get(position);
        if (getItemViewType(position) == ImagesResponse.Image.POSTER) {
            holder.mBindingPoster.setImage(image);
            holder.mBindingPoster.executePendingBindings();
            holder.mBindingPoster.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    downloadImageNew(String.valueOf(image.getWidth() + image.getHeight()), Constants.ImageBaseURL + image.getFile_path());
                }
            });
        } else {
            holder.mBindingBackdrop.setImage(image);
            holder.mBindingBackdrop.executePendingBindings();
            holder.mBindingBackdrop.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    downloadImageNew(null, Constants.ImageBaseURL + image.getFile_path());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<ImagesResponse.Image> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ImagesResponse.Image image = dataList.get(position);
        return image.getImageType();
    }

    private void downloadImageNew(String filename, String downloadUrlOfImage) {
        DialogFactory.createSimpleOkCancelDialog(mContext,
                "Vuoi scaricare questa immagine?",
                "Scarica",
                "Annulla",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            DownloadManager dm = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri downloadUri = Uri.parse(downloadUrlOfImage);
                            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                                    .setAllowedOverRoaming(false)
                                    .setTitle(filename)
                                    .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + filename + ".jpg");
                            dm.enqueue(request);
                            Toast.makeText(mContext, "Image download started.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(mContext, "Image download failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        private ListItemPosterBinding mBindingPoster;
        private ListItemBackdropBinding mBindingBackdrop;


        ImageViewHolder(ListItemPosterBinding binding) {
            super(binding.getRoot());
            this.mBindingPoster = binding;

        }

        ImageViewHolder(ListItemBackdropBinding binding) {
            super(binding.getRoot());
            this.mBindingBackdrop = binding;
        }
    }
}