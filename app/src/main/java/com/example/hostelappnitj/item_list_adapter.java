package com.example.hostelappnitj;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;

import com.example.hostelappnitj.R;

import java.util.List;

public class item_list_adapter extends ArrayAdapter<String> {
    private List<String> items;
    private boolean[] checkedItems;

    public item_list_adapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.items = items;
        checkedItems = new boolean[items.size()];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.list_item_checkbox, parent, false);
        }

        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        checkBox.setText(items.get(position));
        checkBox.setChecked(checkedItems[position]);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedItems[position] = checkBox.isChecked();
            }
        });

        return view;
    }

    public boolean[] getCheckedItems() {
        return checkedItems;
    }
}
