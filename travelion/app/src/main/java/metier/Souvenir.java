package metier;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe representant un souvenir.
 * Created by jumarcon1 on 02/03/18.
 */

public class Souvenir extends ISouvenir implements Serializable,Comparable<Souvenir>{

    CachedBitmap image;
    @Override
    public CachedBitmap getImage() {
        return image ;
    }

    @Override
    public void setImage(Bitmap image) {

        this.image = new CachedBitmap(image);

    }


    Date dateSouvenir;
    @Override
    public Date getDate() {
        return dateSouvenir;
    }

    @Override
    public void setDate(Date dateSouvenir) {
        this.dateSouvenir = dateSouvenir;
    }


    String anecdote;
    @Override
    public String getAnecdote() {
        return anecdote;
    }

    @Override
    public void setAnecdote(String anecdote) {
        this.anecdote = anecdote;
    }


    String titre;
    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public void setTitre(String titre2) {
        titre = titre2;
    }



    public Souvenir(Bitmap image,Date dateSouvenir,String anecdote,String titre)
    {

        this.image = new CachedBitmap(image);

        this.dateSouvenir = dateSouvenir;
        this.anecdote = anecdote;
        this.titre = titre;

    }


    @Override
    public int compareTo(Souvenir o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }



    }

