package com.example.android.inventory;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.inventory.data.InventoryContract;

import java.text.NumberFormat;

public class InventoryCursorAdapter extends CursorAdapter {

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        NumberFormat format = NumberFormat.getCurrencyInstance();

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);

        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE);

        final String petName = cursor.getString(nameColumnIndex);
        int quantity = cursor.getInt(quantityColumnIndex);
        int price = cursor.getInt(priceColumnIndex);


//        if (TextUtils.isEmpty(quantity)) {
//            quantity = context.getString(R.string.unknown_breed);
//        }

        nameTextView.setText(petName);
        quantityTextView.setText("Quantity: " + String.valueOf(quantity));
        priceTextView.setText(format.format(price));

        Button btn = (Button) view.findViewById(R.id.click);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Rik", petName);
            }
        });
    }
}