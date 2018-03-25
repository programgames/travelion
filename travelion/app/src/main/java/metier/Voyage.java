package metier;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import metier.EtatVoyage.IEtatVoyage;
import metier.Exceptions.MissingArgumentException;
import metier.Exceptions.WrongDateException;

/**
 * Classe représentant un voyage.
 * Contenant un nom , une latitude, longitude,pays,un lieu.
 * Created by alvillafra on 14/02/18.
 */

public class Voyage extends IVoyage{

    private String nom;
    public  String getNom(){return nom;}
    public void setNom(String nom){ this.nom = nom; }


    private double latitude;
    public double getLatitude(){ return latitude; }
    public void setLatitude(double i){ latitude=i; }


    private double longitude;
    public double getLongitude(){ return longitude; }
    public void setLongitude(double i){ longitude=i; }

    private String country;
    public String getCountry(){return country;}
    public void setCountry(String s){country = s;}

    private String nomLieu;
    public String getNomLieu(){return nomLieu;}
    public void setNomLieu(String s){nomLieu = s;}

    private IEtatVoyage etat;
    public IEtatVoyage getEtat() {return etat;}
    public void setEtat(IEtatVoyage etat) {this.etat = etat;}

    private ArrayList<ToDoList> toDo;
    public ArrayList<ToDoList> getToDo() {return toDo;}
    public void setToDo(ArrayList<ToDoList> toDo) {this.toDo = toDo;}
    private ArrayList<ISouvenir> souvenirs = new ArrayList<>();
    public ArrayList<ISouvenir> getSouvenirs(){
        return souvenirs;
    }

    private Date dateDepart;

    /**
     * Retourne la date de depart
     * @return
     */
    public Date getDateDepart(){ return this.dateDepart;}

    /**
     *Setter de la date de départ.
     * @param dp
     * @throws WrongDateException
     */
    public void setDateDepart(Date dp) throws WrongDateException {
        if(dateRetour != null) {

            if (dp.compareTo(dateRetour) > 0) {
                throw new WrongDateException("La date de départ saisie est incorrecte par rapport à la date de retour ");
            }
        }
        dateDepart = dp;
        //Log.i("Year","Année dans setDateDepart: " + dateDepart.getYear());
    }

    private Date dateRetour;

    /**
     * Getter de la date de retour.
     * @return
     */
    public Date getDateRetour(){return this.dateRetour;}

    /**
     * Setter de la date de retour.
     * @param dr
     * @throws WrongDateException
     */
    public void setDateRetour(Date dr) throws WrongDateException {
        if(dateDepart != null){
            if (dr.compareTo(dateDepart) < 0) {
                throw new WrongDateException("La date de retour saisie est incorrecte par rapport à la date de départ");
            }
        }
        dateRetour = dr;
    }


    public Voyage(){

    }


    /**
     * Constructeur du voyage
     * @param etat Etat du voyage.
     */
    public Voyage(IEtatVoyage etat) {
        //this.lieu = place;
        this.etat = etat;
        this.toDo = new ArrayList<>();
    }


    @Override
    public MarkerOptions getEtatMarker() {
        return etat.getMarker(this.latitude,this.longitude,this.country);
    }

    /**
     * @param image image du souvenir
     * @param date date du souvenir
     * @param titre date du souvenir
     * @param anecdote anecdote du souvenir
     * @throws MissingArgumentException
     */
    public void addSouvenir(Bitmap image, Date date, String titre, String anecdote)throws MissingArgumentException{
        if(image == null || date == null || titre == null || anecdote == null){

            throw new MissingArgumentException("Assurez-vous d'avoir rempli tous les champs");
        }
        Souvenir s = new Souvenir(image,date,anecdote,titre);

        souvenirs.add(s);

    }
}
