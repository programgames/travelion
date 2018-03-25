package persistance;

import android.content.Context;

import com.google.android.gms.location.places.Place;

import java.io.FileOutputStream;

import metier.Carnet;
import metier.Voyage;

/**
 * Stub de la persistance.
 * Created by alvillafra on 14/02/18.
 * Classe de la persistance du carnet.
 *
 */

public class Stub implements IPersistance {
    @Override
    public Carnet loadCarnet(Context context) {
        Carnet c = new Carnet();
        return c;
    }

    @Override
    public void saveCarnet(Carnet c, Context context) {

    }
}
