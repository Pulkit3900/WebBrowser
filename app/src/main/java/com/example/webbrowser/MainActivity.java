package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText sedit=(EditText)findViewById(R.id.search_edit);
        Button sbutton=(Button)findViewById(R.id.search_button);
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data=sedit.getText().toString();
                Intent intent=new Intent(getApplicationContext(),webview.class);
                intent.putExtra("message",data);
                startActivity(intent);
            }
        });
    }

}
