package com.example.asmaa.unsplash_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Context context;
     EditText email;
    EditText password;
    Button login;
    TextView signup;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        email =(EditText)findViewById(R.id.edit_email);
        password=(EditText)findViewById(R.id.edit_password);
        login =(Button)findViewById(R.id.btn_login);
        signup=(TextView)findViewById(R.id.txt_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss =new Intent(context,signUpActivity.class);
               context.startActivity(ss);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storedPassword = loginDataBaseAdapter
                        .getSinlgeEntry(email.getText().toString());



                boolean valid = true;
                if (email.getText().toString().isEmpty()|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() ) {
                    email.setError("enter a valid email address");
                    valid = false;
                }else {
                    email.setError(null);
                }

                if (password.getText().toString().isEmpty() || password.length() < 4 || password.length() > 8) {
                    password.setError("between 4 and 8 alphanumeric characters");
                    valid = false;
                } else {
                    password.setError(null);
                }
                if (password.getText().toString().equals(storedPassword)) {
                    Toast.makeText(LoginActivity.this,
                            " Login Successfull", Toast.LENGTH_LONG)
                            .show();

                    Intent intent = new Intent(LoginActivity.this, detailActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,
                            "User Name or Password does not match",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }


}

