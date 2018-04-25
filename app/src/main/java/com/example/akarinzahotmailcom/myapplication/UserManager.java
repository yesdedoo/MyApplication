package com.example.akarinzahotmailcom.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by akarinzahotmail.com on 4/13/18.
 */
public class UserManager extends SQLiteOpenHelper implements UserManagerHelper {

    public static final String TAG = UserManager.class.getSimpleName();
    private SQLiteDatabase Database;


    public UserManager(Context context){
        super(context, UserManagerHelper.DATABASE_NAME, null, UserManagerHelper.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_USER = String.format("CREATE TABLE %s " + "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)" , User.TABLE, User.Column.ID, User.Column.USERNAME, User.Column.PASSWORD);
        db.execSQL(CREATE_TABLE_USER);
        Log.i(TAG, CREATE_TABLE_USER);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    String DROP_USER = "DROP TABLE IF EXISTS" + UserManagerHelper.DATABASE_VERSION;
    db.execSQL(DROP_USER);
    Log.i(TAG, DROP_USER);
    onCreate(Database);
    }

    public long registerUser(User user){
        Database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.Column.USERNAME, user.getUsername());
        values.put(User.Column.PASSWORD, user.getPassword());

        long result = Database.insert(User.TABLE, null, values);
        Database.close();

        return result;
    }

    public User checkUserLogin(User user){
        Database = this.getReadableDatabase();

        Cursor cursor = Database.query(User.TABLE, null, User.Column.USERNAME + " = ? AND " + User.Column.PASSWORD + " = ?", new String[]{user.getUsername(),user.getPassword()}, null, null, null);
        User currentUser = new User();

        if(cursor != null){
            if(cursor.moveToFirst()){
                currentUser.setId((int) cursor.getLong(0));
                currentUser.setUsername(cursor.getString(1));
                currentUser.setPassword(cursor.getString(2));
                Database.close();
                return currentUser;
            }
        }
        return null;
    }

    public int changePassword(User user){
        Database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.Column.USERNAME, user.getUsername());
        values.put(User.Column.PASSWORD, user.getPassword());

        int row = Database.update(User.TABLE,values,User.Column.ID + " = ?", new String[] {String.valueOf(user.getId())});

        Database.close();
        return row;
    }
}
