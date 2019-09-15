package com.example.testappusers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ImageSimpleExpandableList extends SimpleExpandableListAdapter {


    private Context ctx;
    private LayoutInflater layoutInflater;
    private TextView tvItemName;
    private TextView tvItem;
    private ImageView ivItemImage;

    private final String USER_DATA_ITEM_A = "itemName";
    private final String USER_DATA_ITEM_B = "itemData";
    private final String USER_DATA_ITEM_C = "itemImage";

    public ImageSimpleExpandableList(Context context, ArrayList<Map<String, Object>> groupData,
                                     int groupLayout, String[] groupFrom, int[] groupTo,
                                     ArrayList<ArrayList<Map<String, Object>>> childData,
                                     int childLayout, String[] childFrom, int[] childTo) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
        ctx = context;
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_child_lay_list_view, null);
        }

        tvItemName = convertView.findViewById(R.id.tvItemName);
        tvItemName.setText((String) ((Map<String, Object>) getChild(groupPosition, childPosition)).get(USER_DATA_ITEM_A));

        tvItem = convertView.findViewById(R.id.tvItemData);
        tvItem.setText((String) ((Map<String, Object>) getChild(groupPosition, childPosition)).get(USER_DATA_ITEM_B));

        ivItemImage = convertView.findViewById(R.id.ivItemImg);
        ivItemImage.setImageResource((Integer) ((Map<String, Object>) getChild(groupPosition, childPosition)).get(USER_DATA_ITEM_C));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        if (childPosition == 13 || childPosition == 15 || childPosition == 9 || childPosition == 8){
            return true;
        } else {
            return false;
        }
    }


}
