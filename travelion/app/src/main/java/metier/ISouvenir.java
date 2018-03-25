package metier;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Classe abstract de souvenir.
 * Created by jumarcon1 on 02/03/18.
 */

public abstract class ISouvenir {

    /**
     * Getter de l'image.
     * @return
     */
    abstract public CachedBitmap getImage();

    /**
     * Setter de l'image.
     * @param image
     */
    abstract public void setImage(Bitmap image);

    /**
     * Getter de la date?
     * @return
     */
    abstract public java.util.Date getDate();

    /**
     * Setter de la date.
     * @param dateSouvenir
     */
    abstract public void setDate(java.util.Date dateSouvenir);

    /**
     * Getter de la date.
     * @return
     */
    abstract public String getAnecdote();

    /**
     * Setter de l'anecdote.
     * @param anecdote
     */
    abstract public void setAnecdote(String anecdote);

    /** Getter du titre.
     * @return
     */
    abstract  public String getTitre();

    /**
     * Setter du titre.
     * @param titre2
     */
    abstract  public void setTitre(String titre2);


}
