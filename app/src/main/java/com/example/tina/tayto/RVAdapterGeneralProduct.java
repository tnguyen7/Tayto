package com.example.tina.tayto;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tina on 10/3/15.
 */
public class RVAdapterGeneralProduct extends RecyclerView.Adapter<RVAdapterGeneralProduct.GenProdViewHolder> {

    List<GeneralProduct> generalProducts;
    String productName;

    public static class GenProdViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView product;
        ImageView productPicture;

        GenProdViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.general_product_cv);
            product = (TextView)itemView.findViewById(R.id.product_name);
            productPicture = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    public RVAdapterGeneralProduct(List<GeneralProduct> generalProducts){
        this.generalProducts = generalProducts;
    }

    @Override
    public int getItemCount() {
        return generalProducts.size();
    }

    @Override
    public RVAdapterGeneralProduct.GenProdViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.general_product_card, viewGroup, false);
        GenProdViewHolder gpvh = new GenProdViewHolder(v);
        return gpvh;
    }
    @Override
    public void onBindViewHolder(RVAdapterGeneralProduct.GenProdViewHolder genProdViewHolder, int i) {
        genProdViewHolder.product.setText(generalProducts.get(i).product);

        genProdViewHolder.productPicture.setImageBitmap(generalProducts.get(i).productPicture);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
