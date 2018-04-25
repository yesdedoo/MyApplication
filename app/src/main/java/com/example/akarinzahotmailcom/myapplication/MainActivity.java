package com.example.akarinzahotmailcom.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button ChangePassword;
    TextView Username;
    private UserManager Manager;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navigation bar
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //End of bottomNav
        Manager = new UserManager(this);
        mUser = new User();

        ChangePassword = (Button)findViewById(R.id.change_password);
        Username = (TextView)findViewById(R.id.say_hi);

        Bundle args = getIntent().getExtras();

        if (null == args){
            Toast.makeText(this, getString(R.string.welcome_error_message),Toast.LENGTH_SHORT).show();

        }
        else{
            Username.setText(getString(R.string.say_hi)+ " "+ args.getString(User.Column.USERNAME));
            mUser.setId(args.getInt(User.Column.ID));
            mUser.setUsername(args.getString(User.Column.USERNAME));
        }
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPassword();
            }
        });
    }
    private void showDialogPassword(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        final EditText newPassword = (EditText)view.findViewById(R.id.password);
        builder.setView(view);

        builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = newPassword.getText().toString();
                if(!TextUtils.isEmpty(password)){
                    mUser.setPassword(password);
                    Manager.changePassword(mUser);
                    Toast.makeText(getApplicationContext(), getString(R.string.change_password_message),Toast.LENGTH_SHORT).show();
                    goToLogin();
                }
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), null);
        builder.show();
    }
    //Nav bar with intent changing activity
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent1);
                            break;
                        case R.id.nav_profile:
                            Intent intent2 = new Intent(MainActivity.this,ProfileActivity.class);
                            startActivity(intent2);
                            break;
                                            }
                    return true;
                }
            };

    private void goToLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
