package com.carlospienovi.contacts;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by carlos.pienovi on 04/02/2015.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

    Context mContext;
    List<Contact> mTaskList;

    public ContactAdapter(Context context, List<Contact> tasks) {
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
            Contact contact = mTaskList.get(position);

            TextView textViewFirstName = (TextView) rowView.findViewById(R.id.list_contact_first_name);
            TextView textViewLastName = (TextView) rowView.findViewById(R.id.list_contact_last_name);
            TextView textViewNickname = (TextView) rowView.findViewById(R.id.list_contact_nickname);
            ImageView imageContact = (ImageView) rowView.findViewById(R.id.list_contact_image);

            if (!TextUtils.isEmpty(contact.getFirstName())) {
                textViewFirstName.setText(contact.getFirstName());
            }

            if (!TextUtils.isEmpty(contact.getLastName())) {
                textViewLastName.setText(contact.getLastName());
            }

            if (!TextUtils.isEmpty(contact.getNickname())) {
                textViewNickname.setText(contact.getNickname());
            }

            if (contact.getImage() != null) {
                imageContact.setImageBitmap(contact.getImage());
            }

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
