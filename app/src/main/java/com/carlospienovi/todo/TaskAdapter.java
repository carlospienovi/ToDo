package com.carlospienovi.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by carlos.pienovi on 04/02/2015.
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    Context mContext;
    List<Task> mTaskList;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.list_item_entry, tasks);
        mContext = context;
        this.mTaskList = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = reuseOrGenerateRowView(convertView, parent);
        displayContentInRowView(position, rowView);
        return rowView;
    }

    private void displayContentInRowView(int position, View rowView) {
        if (rowView != null) {
            TextView textViewStar = (TextView) rowView.findViewById(R.id.text_view_title);
            textViewStar.setText(mTaskList.get(position).getTitle());
            CheckBox checkBox = (CheckBox)rowView.findViewById(R.id.checkBox);
            checkBox.setChecked(mTaskList.get(position).isDone());
        }
    }

    private View reuseOrGenerateRowView(View convertView, ViewGroup parent) {
        View rowView;
        if (convertView != null) {
            rowView = convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item_entry, parent, false);
        }
        return rowView;
    }
}
