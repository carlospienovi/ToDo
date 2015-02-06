package com.carlospienovi.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactsListFragment extends ListFragment {


    private static final String LOG_TAG = ContactsListFragment.class.getSimpleName();
    private static final int REQUEST_CODE_CREATE_CONTACT = 1;

//    private static List<Contact> entries = new ArrayList<>();

    ContactAdapter mAdapter;
    DatabaseHelper mDBHelper = null;

    public DatabaseHelper getDBHelper() {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return mDBHelper;
    }

    public ContactsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_stars_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                toNewTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toNewTask() {
        Intent i = new Intent(getActivity(), CreateNewContactActivity.class);
        startActivityForResult(i, REQUEST_CODE_CREATE_CONTACT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CREATE_CONTACT:
                if (resultCode == Activity.RESULT_OK) {
                    Contact newContact = data
                            .getParcelableExtra(CreateNewContactActivity.NEW_CONTACT);
                    addNewTask(newContact);
                }
                break;
        }
    }

    private void addNewTask(Contact contact) {
        mAdapter.add(contact);
        mAdapter.notifyDataSetChanged();
        saveContact(contact);
    }

    private void saveContact(Contact contact) {
        try {
            Dao<Contact, Integer> dao = getDBHelper().getDocumentDao();
            dao.create(contact);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Failed to create DAO.", e);
        }
    }

    private List<Contact> getContacts() throws SQLException {
        Dao<Contact, Integer> dao = getDBHelper().getDocumentDao();
        return dao.queryForAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
        List<Contact> entries;
        try {
            entries = getContacts();
        } catch (SQLException e) {
            entries = new ArrayList<Contact>();
        }
        mAdapter = new ContactAdapter(getActivity(), entries);
        setListAdapter(mAdapter);
    }
}
