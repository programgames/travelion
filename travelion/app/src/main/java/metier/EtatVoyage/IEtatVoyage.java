package metier.EtatVoyage;

import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

/**
 * Interface de l'etat d'un voyage.
 * Created by alvillafra on 07/02/18.
 */

public interface IEtatVoyage extends Serializable {

    MarkerOptions getMarker(double lat, double longi, String country);

    /**
     * LE voyage est futur ?
     * @return
     */
    boolean isFutur();

    /**
     * Le voyage est en cours ?
     * @return
     */
    boolean isEnCours();

    /**
     * El voyage est passé ?
     * @return
     */
    boolean isPasse();

}
