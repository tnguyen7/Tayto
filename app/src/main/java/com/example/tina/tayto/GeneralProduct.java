package com.example.tina.tayto;

import android.graphics.Bitmap;

/**
 * Created by tina on 10/3/15.
 */
public class GeneralProduct {
    String product;
    Bitmap productPicture;

    public GeneralProduct(String product, Bitmap picture) {
        this.product = product;
        this.productPicture = picture;
    }
}
