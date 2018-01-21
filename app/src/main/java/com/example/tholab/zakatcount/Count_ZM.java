package com.example.tholab.zakatcount;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Count_ZM extends AppCompatActivity {

    EditText edtJumlahHarta, edtHargaEmas;
    CheckBox konfirm;
    Button btnHitung, btnReset;

    TextView nishabEmasSaatini;
    TextView totalZakat;
    EditText namaPem;

    //hukum islamnya nishabnya Emas 20 dinas = 85gr murni
    final static int nishabPatokan = 85;

    String strJmlHarta;
    String strHargaEmas;
    String strTotalZakat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count__zm);
        initView(); // pemanggilan Method



        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtJumlahHarta.getText().toString().isEmpty()){
                    edtJumlahHarta.setError("Masukan Jumlah Harta");
                    return;
                }
                if (edtHargaEmas.getText().toString().isEmpty()){
                    edtHargaEmas.setError("Masukan Harga Emas");
                    return;
                }

                if (!konfirm.isChecked()){
                    //bikin alert dialog
                    return;
                }





                int hargaEmas= Integer.parseInt(edtHargaEmas.getText().toString());
                long jumlahHarta = Long.parseLong(edtJumlahHarta.getText().toString());

                int convertedNishab = hargaEmas*nishabPatokan;
                if (jumlahHarta<convertedNishab){
                    AlertDialog.Builder builder= new AlertDialog.Builder(Count_ZM.this);
                    builder.setTitle("Attention");
                    builder.setMessage("Anda Belum wajib Mengeluarkan Wajib Zakat mal \n"+
                    "Harta anda belum mencapai nishab !");
                    builder.setPositiveButton("Siap", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();
                    return;
                }

                // kesepakatan Ulama , Ketika udah melewati nishab, zakat yang harus di keluarkan
                // adalah 0.025 atau 2,5%
                long totalZakatReal = jumlahHarta/40;

                //make formater
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setDecimalSeparator('.');

                DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                mataUangIndonesia.setDecimalFormatSymbols(formatRp);

                //convert int and long ke string dengan formater
                strHargaEmas = mataUangIndonesia.format(convertedNishab);
                strJmlHarta = mataUangIndonesia.format(jumlahHarta);
                strTotalZakat = mataUangIndonesia.format(totalZakatReal);

                //setText hasil
                nishabEmasSaatini.setText(strHargaEmas);
                totalZakat.setText(strTotalZakat);


            }
        });

    }
    public void initView(){
        edtJumlahHarta = findViewById(R.id.edtJmlKekayaan);
        edtHargaEmas = findViewById(R.id.edtHrgEmas);

        konfirm = findViewById(R.id.cbConfirm);
        btnHitung = findViewById(R.id.btnHitungZakatMal);
        btnReset = findViewById(R.id.btnresetZmal);

        nishabEmasSaatini = findViewById(R.id.txtNishabEmas);
        totalZakat = findViewById(R.id.txtTotalZMal);
        namaPem = findViewById(R.id.edtNp);



    }
}
