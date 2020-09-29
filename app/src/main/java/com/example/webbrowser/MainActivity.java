package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText sedit=(EditText)findViewById(R.id.search_edit);
        Button sbutton=(Button)findViewById(R.id.search_button);
        sbutton.setOnClickListener(view -> openWebsite(sedit.getText().toString()));
        sedit.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_GO) {
                openWebsite(sedit.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void openWebsite(String data) {
        Intent intent=new Intent(getApplicationContext(),webview.class);
        intent.putExtra("message",data);
        startActivity(intent);
    }
}
