package com.dev.ehnyn.mediaviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.app.Activity.RESULT_OK;


public class PictureFragment extends Fragment implements PictureGalleryAdapter.ImageClickListener {

    public static final String TAG = "TAG";
    private int IMAGE_PICKED = 1;
    private Set<String> imagePaths = new HashSet<>();
    RecyclerView recyclerView;
    PictureGalleryAdapter galleryAdapter;
    GridLayoutManager gridLayoutManager;
    List<String> imagePath = new ArrayList<>();

    public PictureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_picture, container, false);
        recyclerView = rootview.findViewById(R.id.rl_images_recyclerview);
        galleryAdapter = new PictureGalleryAdapter(imagePath,this,getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setAdapter(galleryAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);




        FloatingActionButton fab = rootview.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICKED);
            }
        });
        return rootview;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "IT GOT HERE1");

        if (requestCode == IMAGE_PICKED && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            imagePaths.add(String.valueOf(uri));
            imagePath.add(String.valueOf(uri));
            galleryAdapter.notifyDataSetChanged();



        }
    }

    @Override
    public void onImageClicked() {

    }
}
