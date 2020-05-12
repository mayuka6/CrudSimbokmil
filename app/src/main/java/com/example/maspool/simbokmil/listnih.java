package com.example.maspool.simbokmil;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class listnih extends AppCompatActivity {
    private String[] daftar;
    public ListView ListView01;
    protected Cursor cursor;
    private Datahelpert dbcenter;
    public static listnih ma;
    public ListView lv;
    public ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listnih);
        lv = (ListView) findViewById(R.id.list1);
        ma = this;

        dbcenter = new Datahelpert(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM bumil", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daftar);
        lv.setAdapter(adapter);
        lv.setSelected(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {

                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Detail", "Update", "Hapus"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(listnih.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), tampil.class);
                                i.putExtra("id", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), update.class);
                                in.putExtra("id", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from bumil where id = '" + selection + "'");
                                RefreshList();
                                Toast.makeText(listnih.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) lv.getAdapter()).notifyDataSetInvalidated();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM bumil", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
        }
        ListView01 = (ListView) findViewById(R.id.list1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
    }

}
