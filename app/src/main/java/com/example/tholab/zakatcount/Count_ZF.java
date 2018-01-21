package com.example.tholab.zakatcount;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tholab.zakatcount.model.Mdl_ZF;

public class Count_ZF extends AppCompatActivity {

    EditText edtJumlahKel;
    Button hitung,reset;
    TextView textTotalZakat;
    LinearLayout linearhasil;
    EditText edtNamaKepKel;

    int jumlahKel;
    static final int TAKARAN = 3; //nilai tidak bisa dirubah
    int totalzakat = 0;
    String nKepKel;
    Db_helper db_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count__zf);

        edtJumlahKel = findViewById(R.id.edtJumlahKeluarga);
        hitung = findViewById(R.id.btnHitungZakatFitr);
        reset = findViewById(R.id.btnResetZfitr);
        textTotalZakat = findViewById(R.id.txtTotalZakatF);
        edtNamaKepKel = findViewById(R.id.edtkepalaKeluarga);
        linearhasil = findViewById(R.id.linearHasil);
        linearhasil.setVisibility(View.GONE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nampilin icon back

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtJumlahKel.getText().toString().isEmpty()){ //gunanya untuk jika di klik hitung kosongan tidak
                                                                    //force close
                    edtJumlahKel.setError("Masukan Jumlah Keluarga");
                    return;
                }

                String strjumlahKel = edtJumlahKel.getText().toString();
                jumlahKel = Integer.parseInt(strjumlahKel);

                totalzakat = jumlahKel*TAKARAN;

                textTotalZakat.setText(String.valueOf(totalzakat));
                linearhasil.setVisibility(View.VISIBLE);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtJumlahKel.setText("");
                edtNamaKepKel.setText("");
                linearhasil.setVisibility(View.INVISIBLE);
            }
        });





    }
// method buat aksi di toolbar
    @Override
    //get item
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            finish();
        }

        if (id==R.id.item_save){
            if (totalzakat==0){
                Toast.makeText(this, "Silahkan hitung Terlebih Dahulu",Toast.LENGTH_SHORT).show();
                return false;
            }
            if (edtNamaKepKel.getText().toString().isEmpty()){
                edtNamaKepKel.setError("Masukan Nama K");
                return false;
            }

            Db_helper db_helper = new Db_helper(Count_ZF.this);
            Mdl_ZF mdl_temp = new Mdl_ZF();
            mdl_temp.setJumlahKel(jumlahKel);
            mdl_temp.setTotalZakat(totalzakat);
            mdl_temp.setNamaKK(edtNamaKepKel.getText().toString());

            db_helper.saveDataZF(mdl_temp);
            Snackbar.make(linearhasil, "Data Berhasil Disimpan", Snackbar.LENGTH_SHORT).show();

        }

        return true;
    }

// buat inflate toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }
}
