package com.example.akarinzahotmailcom.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by akarinzahotmail.com on 4/13/18.
 */
public class RegisterActivity extends AppCompatActivity{
    private EditText Username;
    private EditText Password;
    private EditText ConfirmPassword;
    private Button Register;

    private Context Context;
    private UserManager Manager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Manager = new UserManager(this);
        Context = this;

        Username = (EditText)findViewById(R.id.fillUsername);
        Password = (EditText)findViewById(R.id.fillPassword);
        ConfirmPassword = (EditText)findViewById(R.id.confirmPassword);
        Register = (Button)findViewById(R.id.button_register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim().toLowerCase();
                String password = Password.getText().toString();
                String confirmPassword = ConfirmPassword.getText().toString();

                if(password.equals(confirmPassword)){
                   User user = new User(username,password);
                   long rowId = Manager.registerUser(user);
                    if(rowId == -1){
                       String message = getString(R.string.register_error_message);
                       Toast.makeText(Context, message, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String message = getString(R.string.register_success);
                        Toast.makeText(Context,message,Toast.LENGTH_SHORT).show();
                        finish();
                    }
            }
            else{
                  String message = getString(R.string.register_password_error);
                  Toast.makeText(Context,message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
