package qodr.sqlitecrud.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import qodr.sqlitecrud.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id== R.id.action_github){
            String gitHubLink = "https://github.com/qodrorid/android-sql-crud";

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(gitHubLink));
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);

    }

    public void gotoCreate(View view) {
        moveToActivity(CreateActivity.class);

    }
    public void goRead(View view) {
        moveToActivity(ReadActivity.class);

    }
    public void goUpdate(View view) {
        moveToActivity(UpdateActivity.class);
    }

    public void goDelete(View view) {
        moveToActivity(DeleteActivity.class);
    }

    private void moveToActivity(Class className) {
        startActivity(new Intent(MainActivity.this, className));
    }


}
