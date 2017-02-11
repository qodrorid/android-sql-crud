package qodr.sqlitecrud.activity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import qodr.sqlitecrud.R;
import qodr.sqlitecrud.helper.MyDataBaseHelper;
import qodr.sqlitecrud.model.SantriModel;

public class DeleteActivity extends AppCompatActivity {

    private List<String> listSantri;
    private String idSantri;
    private MyDataBaseHelper myDataBaseHelper;
    private String TAG = getClass().getSimpleName();
    private ListView listView;
    private List<SantriModel> listModelSantri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delete");


        listView = (ListView) findViewById(R.id.lvSantri);

        listSantri = new ArrayList<>();
        listModelSantri = new ArrayList<>();


        //show the data
        showData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

                //pop up confirmation delete
                new AlertDialog.Builder(DeleteActivity.this)
                        .setTitle("Konfirmasi")
                        .setMessage("Hapus data "+listSantri.get(position)+"?" )
                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //get the id
                                String itemId = listModelSantri.get(position).getId_santri();

                                //delete the data
                                myDataBaseHelper.deleteDataSantri(itemId);

                                //clear data on list
                                listSantri.clear();

                                //show message its deleted
                                Toast.makeText(getApplicationContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

                                //show data again > refresh
                                showData();
                            }
                        })
                        .show();

            }
        });
    }

    private void showData() {

        //initiate db helper
        myDataBaseHelper = new MyDataBaseHelper(this);

        //get all data
        Cursor cursor = myDataBaseHelper.readAllDataSantri();

        //is any data?
        if (cursor.moveToFirst()){

            do {
                SantriModel santriModel = new SantriModel();

                //get the data
                String id = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.ID_SANTRI));
                String namaSantri = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.NAMA_SANTRI));
                String asalKota = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.ASAL_KOTA));
                String skill = cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.COL5_SKILL_SANTRI));

                //add to list
                listSantri.add(namaSantri+" - "+asalKota+" - "+skill);


                //set the data to model
                santriModel.setId_santri(id);
                santriModel.setNama_santri(namaSantri);
                santriModel.setAsal_kota(asalKota);
                santriModel.setSkill_santri(skill);

                //add model to list
                listModelSantri.add(santriModel);

            }  while (cursor.moveToNext());
        }

        //prepare the adapter
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
