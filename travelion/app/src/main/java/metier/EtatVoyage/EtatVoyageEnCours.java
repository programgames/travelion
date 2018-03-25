package metier.EtatVoyage;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/** Classe d'un voyage en cours.
 * Created by alvillafra on 14/02/18.
 */

public class EtatVoyageEnCours implements IEtatVoyage {

    @Override
    public MarkerOptions getMarker(double lat, double longi, String country) {

        MarkerOptions m = new MarkerOptions()
                .position(new LatLng(lat,longi))
                .title(country)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        return m;
    }

    @Override
    public boolean isFutur() {
        return false;
    }

    @Override
    public boolean isEnCours() {
        return true;
    }

    @Override
    public boolean isPasse() {
        return false;
    }
}
