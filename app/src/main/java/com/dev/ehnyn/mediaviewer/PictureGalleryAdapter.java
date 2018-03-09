package com.dev.ehnyn.mediaviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class PictureGalleryAdapter extends RecyclerView.Adapter<PictureGalleryAdapter.MyViewHolder> {

    private Set<String> imageLists;
    private ImageClickListener clickListener;
    private Context context;
    List<String> imageList;

    public PictureGalleryAdapter(List<String> imageList, ImageClickListener clickListener, Context context) {
        Log.d("YOYOYO", "entered, adapter");
        this.clickListener = clickListener;
        this.context = context;
        this.imageList = imageList;
    }

    public interface ImageClickListener{
        void onImageClicked();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(clickListener,imageList);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.iv_image_item);
        }

        public void bind(final ImageClickListener imageClickListener, List<String>imageList){
            int postion = getAdapterPosition();
            try {
                Log.d("YOYOYO","Did yo get here");
                Uri uri = Uri.parse(imageList.get(postion));
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                itemImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
            itemImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageClickListener.onImageClicked();
                }
            });
        }
    }
}
