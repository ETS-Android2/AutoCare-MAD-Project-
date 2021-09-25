package com.example.madautocare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

class Signin extends AppCompatActivity {

    private EditText username,password;
    private Button signin;
    private DbHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.edittext_usname);
        password = findViewById(R.id.edittext_pw);
        signin = findViewById(R.id.btn_signin);
        context = this;
        dbHandler = new DbHandler(context);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(Signin.this,"please enter all fields",Toast.LENGTH_SHORT).show();
                else {
                    boolean CheckCustomer = dbHandler.CheckCustomerPassword(user, pass);
                    if (CheckCustomer == true) {
                        Toast.makeText(Signin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, MainActivity.class));

                    }else {
                        Toast.makeText(Signin.this,"Wrong Password or Name",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}