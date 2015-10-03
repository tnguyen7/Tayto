package com.example.tina.tayto;

import android.graphics.Bitmap;
import android.view.SurfaceView;

import java.net.URI;

/**
 * Created by tina on 10/3/15.
 */
public class UpdateProductPost {
    String personName, productName, description, date;
    Bitmap productPicture;
    URI productVideo;

    public UpdateProductPost(String personName, String productName, String description, String date, Bitmap personPicture, URI productVideo) {
        this.personName = personName;
        this.productName = productName;
        this.description = description;
        this.date = date;
        this.productPicture = personPicture;
        this.productVideo = productVideo;
    }
}
