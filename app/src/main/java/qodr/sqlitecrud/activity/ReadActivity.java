package qodr.sqlitecrud.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import qodr.sqlitecrud.R;
import qodr.sqlitecrud.helper.MyDataBaseHelper;

public class ReadActivity extends AppCompatActivity {

    private List<String> listSantri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Read");

        ListView listView = (ListView) findViewById(R.id.lvSantri);

        listSantri = new ArrayList<>();

        //initiate db helper
        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this);

        //get the all data
        Cursor cursor = myDataBaseHelper.readAllDataSantri();

        // is any data?
        if (cursor.moveToFirst()){
            do {
                //get the data
                String namaSantri = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.NAMA_SANTRI));
                String asalKota = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.ASAL_KOTA));
                String skill = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.COL5_SKILL_SANTRI));

                //add data to list
                listSantri.add(namaSantri+" - "+asalKota+" - "+skill);

            }  while (cursor.moveToNext());
        }

        //prepare adapter
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSantri);

        //set the adapter
        listView.setAdapter(itemsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
