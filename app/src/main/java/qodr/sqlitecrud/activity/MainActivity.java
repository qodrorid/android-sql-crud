package qodr.sqlitecrud.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import qodr.sqlitecrud.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);
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
