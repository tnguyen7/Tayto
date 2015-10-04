package com.example.tina.tayto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Tina on 10/4/2015.
 */
public class RVAdapterSpecificProduct extends RecyclerView.Adapter<RVAdapterSpecificProduct.SpecProdViewHolder>{

    List<SpecificProduct> specificProducts;
    Context context;

    public class SpecProdViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView product, person, description;
        ImageView productPicture;

        SpecProdViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), Timeline.class));
                }
            });

            cv = (CardView)itemView.findViewById(R.id.specific_product_cv);
            product = (TextView)itemView.findViewById(R.id.product_name);
            person = (TextView) itemView.findViewById(R.id.username);
            productPicture = (ImageView)itemView.findViewById(R.id.imageView);
            description = (TextView) itemView.findViewById(R.id.description);
            //profilePicture = (ImageView)itemView.findViewById(R.id.imageView3);
        }
    }

    public RVAdapterSpecificProduct(List<SpecificProduct> specificProducts, Context context){
        this.specificProducts = specificProducts;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return this.specificProducts.size();
    }

    @Override
    public RVAdapterSpecificProduct.SpecProdViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_product_card, viewGroup, false);
        SpecProdViewHolder spvh = new SpecProdViewHolder(v);
        return spvh;
    }
    @Override
    public void onBindViewHolder(RVAdapterSpecificProduct.SpecProdViewHolder genProdViewHolder, int i) {
        genProdViewHolder.product.setText(specificProducts.get(i).product);
        genProdViewHolder.person.setText(specificProducts.get(i).person);
        genProdViewHolder.description.setText(specificProducts.get(i).description);

        Picasso.with(context)
                .load(specificProducts.get(i).productPicture)
                .into(genProdViewHolder.productPicture);
        //insert picasso code here
        //genProdViewHolder.profilePicture.setImageBitmap(specificProducts.get(i).productPicture);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
