package com.example.tholab.zakatcount.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tholab.zakatcount.List_ZF;
import com.example.tholab.zakatcount.R;
import com.example.tholab.zakatcount.model.Mdl_ZF;

import java.util.ArrayList;

/**
 * Created by tholab on 21/01/18.
 */

public class ZF_Adapter extends RecyclerView.Adapter<ZF_Adapter.ViewHolderRec> {

    ArrayList<Mdl_ZF> list;
    Context context;

    public ZF_Adapter(ArrayList<Mdl_ZF> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolderRec onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlist_zf, parent, false);
        ViewHolderRec holderRec = new ViewHolderRec(v);
        return holderRec;

    }

    @Override
    public void onBindViewHolder(ViewHolderRec holder, int position) {
        holder.jumlahKel.setText(String.valueOf(list.get(position).getJumlahKel()));
        holder.namaKK.setText(list.get(position).getNamaKK());
        holder.totalZakat.setText(String.valueOf(list.get(position).getTotalZakat()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderRec extends  RecyclerView.ViewHolder{

        TextView namaKK;
        TextView jumlahKel;
        TextView totalZakat;
        ImageView imageDelete;

        public ViewHolderRec(View itemView) {
            super(itemView);

            namaKK = itemView.findViewById(R.id.txtnamaKK);
            jumlahKel = itemView.findViewById(R.id.txtJumlahKel);
            totalZakat = itemView.findViewById(R.id.txtTZ);
            imageDelete = itemView.findViewById(R.id.deleteItemList);
            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Warning !");

                    builder.setMessage("Apakah Anda Yakin Menghapusnya ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            List_ZF.activity.deleteFromList(getItemModel(getAdapterPosition()));
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            });
        }
    }

    public Mdl_ZF getItemModel(int position){
        return list.get(position);
    }
}
