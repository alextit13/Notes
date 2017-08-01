package com.bingerdranch.android.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NoteText extends Activity {

    private TextView  textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_text);

        textView = (TextView)findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText();
            }
        });
    }

    private void editText() {
        Intent intent = new Intent(NoteText.this,EditText.class);
        intent.putExtra("text",textView.getText().toString());
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data==null){return;}
        String name = data.getStringExtra("name");
        textView.setText(name);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
