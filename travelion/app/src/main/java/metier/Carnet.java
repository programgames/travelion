package metier;

import android.database.DataSetObservable;
import android.util.Log;

import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import metier.EtatVoyage.EtatVoyageEnCours;
import metier.EtatVoyage.EtatVoyageFutur;
import metier.EtatVoyage.EtatVoyagePasse;

/**
 * Classe répresentant le carnet.
 * Created by jumarcon1 on 31/01/18.
 */

public class Carnet extends DataSetObservable implements Serializable {

    /**
     * Getter de la liste des markers.
     * @return lm Liste des markers
     */
    public List<MarkerOptions> getMarkers(){
        List<MarkerOptions> lm = new ArrayList<MarkerOptions>();
        for(IVoyage v : voyages ){
            lm.add(v.getEtatMarker());
        }
        return lm;
    }


    private List<IVoyage> voyages;

    /**
     *
     * Getter de la liste des voyages.
     * @return this.voyages
     */
    public List<IVoyage> getVoyages() {
        return Collections.unmodifiableList(voyages);
    }

    /**
     * Constructeur du carnet instanciant une liste de voyage.
     */
    public Carnet(){
        this.voyages = new ArrayList<IVoyage>();

    }


    /**
     * getter de la liste des voyages futur.
     * @return futurVoyages.
     */
    public List<IVoyage> getFuturVoyages(){
        List<IVoyage> futurVoyages = new ArrayList<>();
        for (IVoyage v : voyages){
            if(v.getEtat().isFutur() ){
                futurVoyages.add(v);
            }
        }
        return futurVoyages;
    }

    /**
     * Getter de la liste des voyages en cours.
     * @return presentVoyages.
     */
    public List<IVoyage> getVoyagesEnCours(){
        List<IVoyage> presentVoyages = new ArrayList<>();
        for (IVoyage v : voyages){
            if(v.getEtat().isEnCours() ){
                presentVoyages.add(v);
            }
        }
        return presentVoyages;
    }

    /**
     * getter de la liste des voyages passes.
     * @return passeVoyages
     */
    public List<IVoyage> getPasseVoyages(){
        List<IVoyage> passeVoyages = new ArrayList<>();
        for (IVoyage v : voyages){
            if(v.getEtat().isPasse() ){
                passeVoyages.add(v);
            }
        }
        return passeVoyages;
    }


    /**
     * met à jour l'état des voyages
     */
    public void testEtatVoyages(){

        Date now = new Date();

        for(IVoyage v : getVoyages()){
            if(v.getDateDepart().compareTo(now) < 0) {
                if(v.getDateRetour().compareTo(now) > 0){
                    v.setEtat(new EtatVoyageEnCours());
                }
                else{
                    v.setEtat(new EtatVoyagePasse());
                }

            }
            else{
                v.setEtat(new EtatVoyageFutur());
            }
        }
    }


    /**
     * Ajouter un voyage et determiner si il est passe, en cours our present.
     * @param v Voyages
     * @throws Exception
     */
    public void addVoyage(IVoyage v) throws Exception{
        if(v.getDateDepart() == null || v.getDateRetour() == null || v.getNom() == null || v.getNomLieu() == null ){
            throw new Exception("Assurez-vous d'avoir bien rempli tous les champs");
        }
        Date now = Calendar.getInstance().getTime();
        now.setYear(now.getYear());
        Log.i("DateNow",String.valueOf(now.getYear()));

        if(v.getDateDepart().compareTo(now) < 0) {
            if(v.getDateRetour().compareTo(now) > 0){
                v.setEtat(new EtatVoyageEnCours());
            }
            else{
                v.setEtat(new EtatVoyagePasse());
            }

        }
        else{
            v.setEtat(new EtatVoyageFutur());
        }


        v.setToDo(new ArrayList<ToDoList>());

        this.voyages.add(v);
    }

}
