package com.example.tholab.zakatcount;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tholab.zakatcount.Adapter.ZF_Adapter;
import com.example.tholab.zakatcount.model.Mdl_ZF;

import java.util.ArrayList;

public class List_ZF extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fabAdd;

    Db_helper db_helper;
    ArrayList<Mdl_ZF> listTangkap;

    ZF_Adapter adapter;

    //buat static instance
    public static List_ZF activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__zf);
        recyclerView = findViewById(R.id.recycleView);
        fabAdd = findViewById(R.id.floatingAdd);
        activity=this;

        //icon back button di toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //tangakap data dari database
        db_helper = new Db_helper(List_ZF.this);
        listTangkap= db_helper.getAllDataFilter();

        // inisiasi adapter
        adapter = new ZF_Adapter(listTangkap, List_ZF.this);

        //setting layout manager dan set adapternya
        recyclerView.setLayoutManager(new LinearLayoutManager(List_ZF.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List_ZF.this,Count_ZF.class));
                finish();


            }
        });
    }

    public void deleteFromList(Mdl_ZF mdl_zf){
        db_helper = new Db_helper(List_ZF.this);
        //delet dari database
        db_helper.deleteDataZF(mdl_zf);
        //delete dari list
        listTangkap.remove(mdl_zf);

        //Kabari adapter bahwa data sudah berubah
        adapter.notifyDataSetChanged();

        Snackbar.make(recyclerView, "Data Berhasil di hapus", Snackbar.LENGTH_LONG).show();
    }


}
