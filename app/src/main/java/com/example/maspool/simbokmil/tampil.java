package com.example.maspool.simbokmil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tampil extends AppCompatActivity {
    protected Cursor cursor;
    public  Datahelpert dbHelper;
    public Button btnKembali;
    public TextView text1,text2,text3,text4,text5,text6,text7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);
        dbHelper = new Datahelpert(this);
        text1 = (TextView) findViewById(R.id.idnya);
        text2 = (TextView) findViewById(R.id.nama2);
        text3 = (TextView) findViewById(R.id.alamat2);
        text4 = (TextView) findViewById(R.id.tensi2);
        text5 = (TextView) findViewById(R.id.bb2);
        text6 = (TextView) findViewById(R.id.ket2);
        text7 = (TextView) findViewById(R.id.kond2);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM bumil WHERE id= '" +
                getIntent().getStringExtra("id") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
            text6.setText(cursor.getString(5).toString());
            text7.setText(cursor.getString(6).toString());

        }
        btnKembali = (Button) findViewById(R.id.button3);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

}
