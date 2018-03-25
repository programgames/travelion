package metier;

import java.io.Serializable;

import metier.EtatItem.IEtatItem;
import metier.EtatItem.EtatItemAFaire;

/**
 * CLasse representant un Item De la ToDo.
 * Created by alvillafra on 13/02/18.
 */

class ToDoItem implements Serializable{

    private IEtatItem etat;

    private String nom;

    /**
     * Constructeur, D'un ToDo Item
     * @param nom nom de ce todo
     */
    public ToDoItem(String nom) {
        this.nom = nom;
        this.etat = new EtatItemAFaire();
    }
}
