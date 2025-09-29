package com.example.thirddemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    String[] items;
    String [] prices;
    String [] descriptions;

    public ItemAdapter(Context context, String[] i, String[] p, String[] d){

        items = i;
        prices = p;
        descriptions = d;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){


        View view1 = layoutInflater.inflate(R.layout.my_listview_detail, null);
        TextView nameText = (TextView) view1.findViewById(R.id.textName);
        TextView priceText = (TextView) view1.findViewById(R.id.textPrice);
        TextView descText = (TextView) view1.findViewById(R.id.textDesc);

        String name = items[i];
        String price = prices[i];
        String desc = descriptions[i];

        nameText.setText(name);
        priceText.setText(price);
        descText.setText(desc);



        return view1;



    }
}
