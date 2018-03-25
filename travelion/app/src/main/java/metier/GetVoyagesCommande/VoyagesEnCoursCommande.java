package metier.GetVoyagesCommande;

import com.example.alvillafra.travelion.vues.MainPage;

import java.util.List;

import metier.IVoyage;

/**
 * Created by alicevillafranca on 21/03/2018.
 */

public class VoyagesEnCoursCommande implements IVoyagesCommande {
    @Override
    public List<IVoyage> getVoyages() {
        return MainPage.getCarnet().getVoyagesEnCours();
    }
}
