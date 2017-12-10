package bornbaby.com.sqlitedatabase.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import bornbaby.com.sqlitedatabase.model.Shop;

/**
 * Created by madhu on 10-Dec-17.
 */

public class SqliteHelper extends SQLiteOpenHelper {


    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "shopsInfo";
    // table name
    private static final String TABLE_NAME = "shops";
    // Shops table columns name
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";


    private static final String CREATE_TABLE_NAME = "CREATE TABLE "
            + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_ADDRESS + " TEXT" + ")";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addShop(Shop shop) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, shop.getName());
        contentValues.put(KEY_ADDRESS, shop.getAddress());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();


    }

    public Shop getShop(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{KEY_ID, KEY_NAME, KEY_ADDRESS};
        String selection = KEY_ID + "=?";
        String[] args = new String[]{String.valueOf(id)};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, selection, args, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        Shop contact = new Shop();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setAddress(cursor.getString(2));
        return contact;


    }

    public List<Shop> getAllShops() {
        List<Shop> shopsList = new ArrayList<>();

        // Select Query
        String selectQuery = " SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shop shop = new Shop();
                shop.setId(Integer.parseInt(cursor.getString(0)));
                shop.setName(cursor.getString(1));
                shop.setAddress(cursor.getString(2));
                // Adding contact to list
                shopsList.add(shop);
            } while (cursor.moveToNext());
        }
        return shopsList;
    }

    public int getShopsCount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String selectQuery = " SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        cursor.close();

        return cursor.getCount();


    }

    // Updating a shop
    public int updateShop(Shop shop) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, shop.getName());
        values.put(KEY_ADDRESS, shop.getAddress());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(shop.getId())});
    }

    // Deleting a shop
    public void deleteShop(Shop shop) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(shop.getId())});
        db.close();
    }

}
