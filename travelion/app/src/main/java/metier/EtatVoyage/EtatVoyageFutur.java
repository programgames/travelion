package metier.EtatVoyage;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Classe d'un voyage futur.
 * Created by alvillafra on 14/02/18.
 */

public class EtatVoyageFutur implements IEtatVoyage {

    @Override
    public MarkerOptions getMarker(double lat, double longi, String country) {

        MarkerOptions m = new MarkerOptions()
            .position(new LatLng(lat,longi))
            .title(country)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        return m;
    }

    @Override
    public boolean isFutur() {
        return true;
    }

    @Override
    public boolean isEnCours() {
        return false;
    }

    @Override
    public boolean isPasse() {
        return false;
    }
}
