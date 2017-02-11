package qodr.sqlitecrud.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import qodr.sqlitecrud.R;
import qodr.sqlitecrud.helper.MyDataBaseHelper;

public class CreateActivity extends AppCompatActivity {

    private MyDataBaseHelper myDataBaseHelper;
    private EditText etIdSantri, etNamaSantri, etAsalKota,etSkill; // etTanggalLahir,

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getSupportActionBar().setTitle("Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initiate edit text
        etIdSantri = (EditText) findViewById(R.id.etIdSantri);
        etNamaSantri = (EditText) findViewById(R.id.etNamaSantri);
        etAsalKota = (EditText) findViewById(R.id.etAsalKota);
        etSkill = (EditText) findViewById(R.id.etSkillSantri);

        //intiate db helper
        myDataBaseHelper = new MyDataBaseHelper(this);
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

    public void doSimpan(View view) {

        //get text from edit text
        String id = etIdSantri.getText().toString();
        String nama = etNamaSantri.getText().toString();
        String asal = etAsalKota.getText().toString();
        String skill = etSkill.getText().toString();

        //validate input
        if (id.length()>1 &&
                nama.length()>2 &&
                asal.length()>2 &&
                skill.length()>2){

            //save the value
            boolean isSaved = myDataBaseHelper.createDataSantri(id, nama, asal, skill);

            // is saved?
            if (isSaved){
                Toast.makeText(getApplicationContext(), "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();

                //go Read
                startActivity(new Intent(CreateActivity.this, ReadActivity.class));
            }else{
                Toast.makeText(getApplicationContext(), "Data gagal disimpan!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Lengkapi data dahulu", Toast.LENGTH_SHORT).show();
        }


    }
}
