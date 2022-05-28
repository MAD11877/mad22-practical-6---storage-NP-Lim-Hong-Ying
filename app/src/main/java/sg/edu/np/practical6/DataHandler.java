package sg.edu.np.practical6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FOLLOWED = "followed";

    public DataHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void addUser(User u) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, u.getId());
        values.put(COLUMN_NAME, u.getName());
        values.put(COLUMN_DESCRIPTION, u.getDescription());
        values.put(COLUMN_FOLLOWED, u.getFollowed());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User findUser(String username) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_NAME + " = \"" + username + "\"";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setDescription(cursor.getString(2));
            user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            cursor.close();
        }

        else {
            user = null;
        }

        db.close();
        return user;
    }

    public User findUserById(int id) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = \"" + id + "\"";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setDescription(cursor.getString(2));
            user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            cursor.close();
        }

        else {
            user = null;
        }

        db.close();
        return user;
    }

    public boolean updateUser(User user) {
        boolean result = false;

        int id = user.getId();
        String name = user.getName();
        String description = user.getDescription();
        Boolean followed = user.getFollowed();
        //String followedstring = followed.toString().toUpperCase();

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            ContentValues content = new ContentValues();
            content.put(COLUMN_NAME, name);
            content.put(COLUMN_DESCRIPTION, description);
            content.put(COLUMN_FOLLOWED, followed);
            db.update(TABLE_USERS, content, "id=?", new String[]{String.valueOf(id)});
            //String update = "UPDATE " + TABLE_USERS + " SET " + COLUMN_NAME + " = \"" + name + "\", " + "SET " + COLUMN_DESCRIPTION + " = \"" + description + "\", " + "SET " + COLUMN_FOLLOWED + " = " + followedstring + " WHERE " + COLUMN_ID + " = \"" + id + "\"";
            //db.execSQL(update);
            cursor.close();
            result = true;
        }

        db.close();

        return result;
    }

    public boolean deleteUser(String username) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_NAME + " = \"" + username + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[] { String.valueOf(user.getId()) });
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    public ArrayList getUsers() {
        ArrayList<User> userlist = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setDescription(cursor.getString(2));
            user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            userlist.add(user);
        }
        cursor.close();
        db.close();

        return userlist;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_NAMES_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_FOLLOWED + " BOOLEAN" + ")";
        sqLiteDatabase.execSQL(CREATE_NAMES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS); //NOT RECOMMENDED
        onCreate(sqLiteDatabase);
    }
}