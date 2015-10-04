package com.example.tina.tayto;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tina on 10/3/15.
 */
public class RVAdapterUpdateProductPost extends RecyclerView.Adapter<RVAdapterUpdateProductPost.UpdProdPosViewHolder> {

    List<UpdateProductPost> updProdPos;
    Context context;

    public static class UpdProdPosViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName, productName, description, date;
        ImageView productImage;
        SurfaceView productVideo;

        UpdProdPosViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.update_cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            productName = (TextView)itemView.findViewById(R.id.product_name);
            description = (TextView)itemView.findViewById(R.id.description);
            date = (TextView)itemView.findViewById(R.id.date);

            productImage = (ImageView)itemView.findViewById(R.id.person_photo);

        }
    }

    public RVAdapterUpdateProductPost(List<UpdateProductPost> list, Context context) {
        this.updProdPos = list;
        this.context = context;
    }

    @Override
    public RVAdapterUpdateProductPost.UpdProdPosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.update_product_card, viewGroup, false);
        UpdProdPosViewHolder uppvh = new UpdProdPosViewHolder(v);
        return uppvh;
    }

    @Override
    public void onBindViewHolder(RVAdapterUpdateProductPost.UpdProdPosViewHolder updProdPosViewHolder, int i) {
        updProdPosViewHolder.personName.setText(updProdPos.get(i).personName);
        updProdPosViewHolder.productName.setText(updProdPos.get(i).productName);
        updProdPosViewHolder.description.setText(updProdPos.get(i).description);
        updProdPosViewHolder.date.setText(updProdPos.get(i).date);

        Picasso.with(context)
                .load(updProdPos.get(i).productPicture)
                .into(updProdPosViewHolder.productImage);
    }

    @Override
    public int getItemCount() {
        return updProdPos.size();
    }
}
