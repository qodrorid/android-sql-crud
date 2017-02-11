package qodr.sqlitecrud.activity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import qodr.sqlitecrud.R;
import qodr.sqlitecrud.helper.MyDataBaseHelper;
import qodr.sqlitecrud.model.SantriModel;

public class UpdateActivity extends AppCompatActivity {

    private List<String> listSantri;
    private List<SantriModel> listModelSantri;
    private MyDataBaseHelper myDataBaseHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update");setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.lvSantri);

        listSantri = new ArrayList<>();
        listModelSantri = new ArrayList<>();

        //initiate data
        myDataBaseHelper = new MyDataBaseHelper(this);

        //show the data
        showData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                String nama_santri = listModelSantri.get(position).getNama_santri();
                builder.setTitle("Update nama : "+ nama_santri);

                // Set up the input
                final EditText input = new EditText(UpdateActivity.this);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT); // | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                input.setText(nama_santri);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String namUpdate = input.getText().toString();

                        //update the nama
                        myDataBaseHelper.updateNamaSantri(listModelSantri.get(position).getId_santri(), namUpdate);

                        //clear the list
                        listModelSantri.clear();
                        listSantri.clear();

                        //show data
                        showData();

                    }
                });
                builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    private void showData() {
        //get data
        Cursor cursor = myDataBaseHelper.readAllDataSantri();

        if (cursor.moveToFirst()){
            do {
                SantriModel santriModel = new SantriModel();

                //get the value
                String id = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.ID_SANTRI));
                String namaSantri = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.NAMA_SANTRI));
                String asalKota = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.ASAL_KOTA));
                String skill = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.COL5_SKILL_SANTRI));

                //add to list
                listSantri.add(namaSantri+" - "+asalKota+" - "+skill);

                //set to the model
                santriModel.setId_santri(id);
                santriModel.setNama_santri(namaSantri);
                santriModel.setAsal_kota(asalKota);
                santriModel.setSkill_santri(skill);

                //add model to data
                listModelSantri.add(santriModel);

            }  while (cursor.moveToNext());
        }

        //prepare adapter
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSantri);

        //set adapter to listview
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
