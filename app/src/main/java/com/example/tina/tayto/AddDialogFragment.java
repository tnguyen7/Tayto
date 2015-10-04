package com.example.tina.tayto;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by richellevital on 10/3/15.
 */
public class AddDialogFragment extends DialogFragment {

    public AddDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.add_dialog_box, null);


        String[] optionsArr = {"Add a Product" };


        Spinner dynamicSpinner = (Spinner) view.findViewById(R.id.dynamic_spinner);

        String[] items = new String[] { "Post an Updated Review", "Green Tea", "Black Tea" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        builder.setTitle("Choose Action")
                .setView(view)
                .setItems(optionsArr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                //((MainActivity)getActivity()).useCamera();
                                Intent intent = new Intent (getActivity(), AddProduct.class);
                                startActivity(intent);
                                return;

                        }
                    }


                });

        return builder.create();
    }
}
