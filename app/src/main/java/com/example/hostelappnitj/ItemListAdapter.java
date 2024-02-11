package com.example.hostelappnitj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelappnitj.R;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    private Context context;
    private List<String> itemList;
    private List<String> checkedItems;

    public ItemListAdapter(Context context, List<String> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.checkedItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_checkbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = itemList.get(position);
        holder.checkBox.setText(item);
        // Set listener for checkbox state change
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle checkbox state change
            // You can store the checked items in a list or perform any other action
            if (isChecked) {
                checkedItems.add(item);
            } else {
                checkedItems.remove(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_item);
        }
    }

    // Method to retrieve checked items
    public List<String> getCheckedItems() {
        return checkedItems;
    }
}





//package com.example.hostelappnitj;
//
//import android.content.Context;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.ArrayAdapter;
//        import android.widget.CheckBox;
//
//import com.example.hostelappnitj.R;
//
//import java.util.List;
//
//public class item_list_adapter extends ArrayAdapter<String> {
//    private List<String> items;
//    private boolean[] checkedItems;
//
//    public item_list_adapter(Context context, int resource, List<String> items) {
//        super(context, resource, items);
//        this.items = items;
//        checkedItems = new boolean[items.size()];
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        View view = convertView;
//        if (view == null) {
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            view = inflater.inflate(R.layout.list_item_checkbox, parent, false);
//        }
//
//        final CheckBox checkBox = view.findViewById(R.id.checkBox);
//        checkBox.setText(items.get(position));
//        checkBox.setChecked(checkedItems[position]);
//
//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkedItems[position] = checkBox.isChecked();
//            }
//        });
//
//        return view;
//    }
//
//    public boolean[] getCheckedItems() {
//        return checkedItems;
//    }
//}
