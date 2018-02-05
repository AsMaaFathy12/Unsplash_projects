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

public class signUpActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button signup;
    TextView login;
    Context context;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        context = this;
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        name = (EditText) findViewById(R.id.edit_name);
        email = (EditText) findViewById(R.id.edit_email);
        password = (EditText) findViewById(R.id.edit_password);
        confirmPassword = (EditText) findViewById(R.id.edit_confirmPassword);
        signup = (Button) findViewById(R.id.btn_signup);
        login = (TextView) findViewById(R.id.txt_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("") || email.getText().toString().equals("")
                        || password.getText().toString().equals("") || confirmPassword.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();

                    return;

                } else {

                    loginDataBaseAdapter.insertEntry(name.getText().toString(), email.getText().toString(), password.getText().toString());
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(signUpActivity.this,
                            detailActivity.class);
                    startActivity(i);



                }
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

    public void logg(View view) {
        Intent i = new Intent(signUpActivity.this,
                LoginActivity.class);
        startActivity(i);
    }
}




