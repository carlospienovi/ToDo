package com.carlospienovi.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateTaskActivity extends ActionBarActivity {

    public static final String NEW_TASK_TITLE = "NEW_TASK_TITLE";

    Button mDoneButton;
    EditText mTaskTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        mTaskTitle = (EditText) findViewById(R.id.new_task_title);
        mTaskTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mDoneButton.setEnabled(!TextUtils.isEmpty(s));
            }
        });
        prepareDoneButton();
    }

    private void prepareDoneButton() {
        mDoneButton = (Button) findViewById(R.id.button_new_task_done);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(NEW_TASK_TITLE, mTaskTitle.getText().toString());
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}
