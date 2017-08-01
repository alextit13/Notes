package com.bingerdranch.android.notes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditText extends Activity {

    private android.widget.EditText editText;
    private Button OK;
    private Button Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        Intent intent = getIntent();

        editText = (android.widget.EditText)findViewById(R.id.editText);
        editText.setText(intent.getStringExtra("text"));

        OK = (Button) findViewById(R.id.OK);
        Cancel = (Button) findViewById(R.id.Cancel);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOk();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelBtn();
            }
        });
    }

    private void btnOk() {

    }
    private void CancelBtn() {

    }
}
