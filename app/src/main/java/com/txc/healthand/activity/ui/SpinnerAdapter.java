package com.txc.healthand.activity.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.txc.healthand.R;
import com.txc.healthand.activity.models.Filter;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Filter> {
    private Context context;
    private Callback callback;
    private List<Filter> filters;
    private SpinnerAdapter spinnerAdapter;
    private boolean boolFromView = false;

    public SpinnerAdapter(@NonNull Context context, Callback callback, int resource, @NonNull List<Filter> objects) {
        super(context, resource, objects);
        this.context = context;
        this.callback = callback;
        this.filters = objects;
        this.spinnerAdapter = this;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCheckboxDropdown(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCheckboxDropdown(position, convertView, parent);
    }

    public View getCheckboxDropdown(final int position, View view, ViewGroup parent){
        final ViewHolder viewHolder;

        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.spinner_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.filter_title);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.filter_checkbox);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(filters.get(position).getTitle());
        boolFromView = true;
        viewHolder.checkbox.setChecked(filters.get(position).isSelected());
        boolFromView = false;

        if(position == 0){
            viewHolder.checkbox.setVisibility(View.INVISIBLE);
        }

        viewHolder.checkbox.setTag(position);

        viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int position = (int) compoundButton.getTag();

                if(!boolFromView){
                    filters.get(position).setSelected(b);
                    callback.onCheckboxChanged();
                }
            }
        });

        return view;
    }

    private class ViewHolder{
        private TextView title;
        private CheckBox checkbox;
    }

    public interface Callback{
        void onCheckboxChanged();
    }
}