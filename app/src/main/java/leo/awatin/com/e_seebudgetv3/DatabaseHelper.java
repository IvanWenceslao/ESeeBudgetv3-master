package leo.awatin.com.e_seebudgetv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Easybudget.db";
    public static final String TABLE_NAME = "expense_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "VALUE";
    public static final String COL_3 = "TIME";
    public static final String COL_4 = "DATE";
    public static final String COL_5 = "TYPE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, VALUE INTEGER, TIME TEXT, DATE TEXT, TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String value, String time, String date, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, value);
        contentValues.put(COL_3, time);
        contentValues.put(COL_4, date);
        contentValues.put(COL_5, type);
        long result = db.insert(TABLE_NAME,null , contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME, null);
        return res;
    }

    public int getValueTotalExpenses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_2, DatabaseHelper.COL_5};
        int total = 0;
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, DatabaseHelper.COL_5+ "= 'Expense'",
                null, null, null, null);
            if(cursor.moveToFirst()){
                total = cursor.getInt(0);// get final total
                }

                /* script for rawQuery, old and not useful
                 Cursor cursor = db.rawQuery("SELECT SUM(VALUE) FROM " + TABLE_NAME + "WHERE TYPE =?", new String[] {"Expense"} );
                    if(cursor.moveToFirst()){
                total = cursor.getInt(0);// get final total
                }
                 */

        return total;
    }

    public int getValueTotalIncome() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_2, DatabaseHelper.COL_5};
        int total = 0;
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, DatabaseHelper.COL_5+ "= 'Income'",
                null, null, null, null);
        if(cursor.moveToFirst()){
            total = cursor.getInt(0);// get final total
        }

                /* script for rawQuery, old and not useful
                 Cursor cursor = db.rawQuery("SELECT SUM(VALUE) FROM " + TABLE_NAME + "WHERE TYPE =?", new String[] {"Expense"} );
                    if(cursor.moveToFirst()){
                total = cursor.getInt(0);// get final total
                }
                 */

        return total;
    }

}
