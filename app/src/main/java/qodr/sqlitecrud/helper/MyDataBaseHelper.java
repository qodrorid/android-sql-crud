package qodr.sqlitecrud.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by zainal on 2/10/17 - 9:15 PM.
 * Zainal Fahrudin
 * fnzainal@gmail.com
 */
public class MyDataBaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "SantriQodr.db";

    private static final String TABLE_SANTRI = "tabel_santri";
    public static final String ID_SANTRI = "id_santri";
    public static final String NAMA_SANTRI = "nama_santri";
    public static final String ASAL_KOTA = "asal_kota";
    public static final String COL5_SKILL_SANTRI = "skill";

    public MyDataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table santri
        db.execSQL("create table " + TABLE_SANTRI + " ( " +
                ID_SANTRI +" INTEGER PRIMARY KEY," +
                NAMA_SANTRI +" TEXT, " +
                ASAL_KOTA +" TEXT," +
                COL5_SKILL_SANTRI +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_SANTRI);
    }

    public boolean createDataSantri(String id_santri,
                                      String nama_santri,
                                      String asal_kota,
                                      String skill){
        //initiate sql db
        SQLiteDatabase db = this.getWritableDatabase();

        //prepare content values
        ContentValues contentValues = new ContentValues();

        //put value to content values
        contentValues.put(ID_SANTRI, id_santri);
        contentValues.put(NAMA_SANTRI, nama_santri);
        contentValues.put(ASAL_KOTA, asal_kota);
        contentValues.put(COL5_SKILL_SANTRI, skill);

        //insert to db
        long result = db.insert(TABLE_SANTRI,null, contentValues);

        return result != -1;
    }

    //get All santri
    public Cursor readAllDataSantri(){
        SQLiteDatabase db  = this.getWritableDatabase();

        //query to get the data
        Cursor result = db.rawQuery("select * from "+TABLE_SANTRI,null);
        return result;
    }

    // Updating single santriModel
    public int updateNamaSantri(String id, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();

        //prepare content values
        ContentValues values = new ContentValues();

        //put the value
        values.put(NAMA_SANTRI, nama);

        // updating row
        return db.update(TABLE_SANTRI, values, ID_SANTRI + " = ?",
                new String[] { id });
    }

    //delete data santri
    public void deleteDataSantri(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        //delete
        db.delete(TABLE_SANTRI, ID_SANTRI + " = ?",
                new String[] { id});
        db.close();
    }

}
