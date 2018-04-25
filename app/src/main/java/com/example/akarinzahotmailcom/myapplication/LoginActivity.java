package com.example.akarinzahotmailcom.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by akarinzahotmail.com on 4/12/18.
 */

public class LoginActivity extends AppCompatActivity{
    private Button Login;
    private EditText Username;
    private EditText Password;
    private TextView Register;
    private Context Context;
    private UserManager Manager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //use content from activity_login.xml

        Manager = new UserManager(this);
        Context = this;
        Login = (Button)findViewById(R.id.button_login);
        Username = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Register = (TextView)findViewById(R.id.register);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkLogin();
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Context,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin(){
        String username = Username.getText().toString().trim().toLowerCase();
        String password = Password.getText().toString().trim();
        User user = new User(username,password);
        User validateUser = Manager.checkUserLogin(user);

        if(null == validateUser){
            String message = getString(R.string.login_error_message);
            Toast.makeText(Context, message, Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(Context, MainActivity.class);
            intent.putExtra(User.Column.USERNAME, validateUser.getUsername());
            intent.putExtra(User.Column.ID, validateUser.getId());
            startActivity(intent);
            finish();
        }
    }
}
