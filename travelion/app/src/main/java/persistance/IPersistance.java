package persistance;

import android.content.Context;

import metier.Carnet;

/**
 * Interface de la persistance.
 * Created by alvillafra on 13/02/18.
 */

public interface IPersistance {

    /**
     * Retourne un carnet
     * @param context Context de l'application
     * @return
     */
    Carnet loadCarnet(Context context);

    /**
     * Sauvegarde d'un carnet
     * @param c le carnet
     * @param context context de l'application
     */
    void saveCarnet(Carnet c, Context context);


}
