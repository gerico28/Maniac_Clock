package com.maniacclock;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Paolo on 10/02/16.
 */
public class CellAdapter extends BaseAdapter {

    private Context context;
    private static LayoutInflater inflater=null;
    private ArrayList<Sveglia> listaSveglie;

    public CellAdapter(Context c, ArrayList<Sveglia> lista){

        context = c;
        listaSveglie = lista;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public class Holder{
        Sveglia sveglia;
        TextView ora;
        ImageButton stato;
        Button del;
    }

    @Override
    public int getCount() {
        return listaSveglie.size();
    }

    @Override
    public Object getItem(int position) {return position;}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.cell,null);

        holder.sveglia = (Sveglia) listaSveglie.get(position);
        holder.ora = (TextView) rowView.findViewById(R.id.ora);
        holder.stato = (ImageButton) rowView.findViewById(R.id.stato);
        holder.del = (Button) rowView.findViewById(R.id.deleteButton);

        holder.ora.setText(listaSveglie.get(position).getOra()+":"+listaSveglie.get(position).getMinuti());

        // gestione tasto cancellazione
        holder.del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listaSveglie.remove(position);
                Snackbar.make(v, "Sveglia Cancellata", Snackbar.LENGTH_LONG)
                        .setAction("Cancellazione", null).show();
                notifyDataSetChanged();
            }
        });

        // gestione bottone attivazione/disattivazione sveglia
        if(listaSveglie.get(position).getStato())
            holder.stato.setImageResource(R.drawable.clock_on);
        else
            holder.stato.setImageResource(R.drawable.clock);

        holder.stato.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                holder.sveglia.setStato(); // cambio stato della sveglia al click del bottone
                if(holder.sveglia.getStato()) {
                    holder.stato.setImageResource(R.drawable.clock_on);
                    Snackbar.make(v, "Sveglia Attivata", Snackbar.LENGTH_LONG)
                            .setAction("Attivazione", null).show();
                }
                else {
                    holder.stato.setImageResource(R.drawable.clock);
                    Snackbar.make(v, "Sveglia Disattivata", Snackbar.LENGTH_LONG)
                            .setAction("Disattivazione", null).show();
                }
            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Cliccato " + listaSveglie.get(position), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;

    }


}
