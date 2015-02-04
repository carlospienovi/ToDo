package com.carlospienovi.todo;

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
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TasksListFragment extends ListFragment {

    private static final int REQUEST_CODE = 1;

    private static List<Task> entries = new ArrayList<>();

    TaskAdapter mAdapter;

    public TasksListFragment() {
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
        Intent i = new Intent(getActivity(), CreateTaskActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        addNewTask(data.getStringExtra(CreateTaskActivity.NEW_TASK_TITLE));
    }

    private void addNewTask(String title) {
        mAdapter.add(new Task(title));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stars_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
//        List<Task> entries = new ArrayList<>();
        mAdapter = new TaskAdapter(getActivity(), entries);
        setListAdapter(mAdapter);
    }
}
