package com.example.tina.tayto;

/**
 * Created by Tina on 10/4/2015.
 */
public class SpecificProduct {
    String product, person, description;
    String productPicture, profilePicture;


    public SpecificProduct(String product, String person, String productPicture, String profilePicture, String description) {
        this.product = product;
        this.person = person;
        this.productPicture = productPicture;
        this.profilePicture = profilePicture;
        this.description = description;
    }
}