package com.example.webbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logoutBtn = findViewById(R.id.logout_button);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(null != user) {
            logoutBtn.setVisibility(View.VISIBLE);
            System.out.println(user.getDisplayName());
            Toast.makeText(this, "Hello "+user.getDisplayName(), Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Hello Guest!");
            Toast.makeText(this, "Hello Guest!", Toast.LENGTH_LONG).show();
        }
        final EditText sedit=(EditText)findViewById(R.id.search_edit);
        Button sbutton=(Button)findViewById(R.id.search_button);
        logoutBtn.setOnClickListener(v -> {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(task -> {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MainActivity.this, "Signed Out Successfully", Toast.LENGTH_LONG).show();
                    });
        });
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
