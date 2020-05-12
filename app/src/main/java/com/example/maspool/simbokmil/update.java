package com.example.maspool.simbokmil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class update extends AppCompatActivity {
    protected Cursor cursor;
    Datahelpert dbHelper;
    Button button1, button2;
    EditText text1, text2, text3,text4,text5, text6, text7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbHelper = new Datahelpert(this);
        text1 = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);
        text6 = (EditText) findViewById(R.id.editText6);
        text7 = (EditText) findViewById(R.id.editText7);

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
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update bumil set nama='"+text2.getText().toString()+"',alamat='" +text3.getText().toString() + "',tensi='" + text4.getText().toString() +"',beratbdn='"+ text5.getText().toString() +"',ket='" + text6.getText().toString() + "',kondisi='"+ text7.getText().toString() +"' where id='" + text1.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Berhasil diupdate", Toast.LENGTH_LONG).show();
                listnih.ma.RefreshList();
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

}
