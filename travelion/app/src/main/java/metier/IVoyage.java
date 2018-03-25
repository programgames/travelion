package metier;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import metier.EtatVoyage.IEtatVoyage;
import metier.Exceptions.MissingArgumentException;
import metier.Exceptions.WrongDateException;

/**
 * Created by alvillafra on 07/02/18.
 */


public abstract class IVoyage implements Serializable {

    abstract public  String getNom();
    abstract public void setNom(String nom);

    abstract public double getLatitude();
    abstract public void setLatitude(double i);



    abstract public double getLongitude();
    abstract public void setLongitude(double i);


    abstract public String getNomLieu();
    abstract public void setNomLieu(String s);

    abstract public List<ISouvenir> getSouvenirs();

    abstract public IEtatVoyage getEtat();
    abstract public void setEtat(IEtatVoyage etat);

    abstract public ArrayList<ToDoList> getToDo() ;
    abstract public void setToDo(ArrayList<ToDoList> toDo);

    abstract public Date getDateDepart();
    abstract public void setDateDepart(Date dp) throws WrongDateException;

    abstract public Date getDateRetour();
    abstract public void setDateRetour(Date dr) throws WrongDateException;

    abstract public MarkerOptions getEtatMarker();

    abstract public void addSouvenir(Bitmap image, Date date, String titre, String anecdote)throws MissingArgumentException;


}

