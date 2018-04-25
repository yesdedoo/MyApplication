package com.example.akarinzahotmailcom.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    Button ChangePassword;
    private UserManager Manager;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Manager = new UserManager(this);
        mUser = new User();

        ChangePassword = (Button)findViewById(R.id.change_password);
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPassword();
            }
        });
    }
    private void showDialogPassword(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
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
                    goToProfile();
                }
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), null);
        builder.show();
    }
    private void goToProfile(){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}
