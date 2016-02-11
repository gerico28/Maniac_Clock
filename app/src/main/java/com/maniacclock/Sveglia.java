package com.maniacclock;

import android.media.Image;
import android.widget.ImageButton;

/**
 * Created by Paolo on 11/02/16.
 */
public class Sveglia {

    private boolean stato; // stato dell'attivazione della sveglia

    // TODO
    // gestire l'orario

    // per il momento l'orario è una stringa
    private String ora;
    private String minuti;

    public Sveglia(String h, String m){
        ora = h;
        minuti = m;
        stato = false;
    }

    public String getOra(){ return ora; }
    public void setOra(String o){ ora = o; }

    public String getMinuti(){ return minuti; }
    public void setMinuti(String m){ minuti = m; }

    public boolean getStato(){ return stato; }

    public void setStato(){
        if(stato)
            stato = false; // disattiva sveglia
        else
            stato = true; // attiva sveglia
    }
}
