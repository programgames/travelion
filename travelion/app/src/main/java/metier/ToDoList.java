package metier;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe represenant une To Do Liste
 * Created by alvillafra on 13/02/18.
 */

public class ToDoList implements Serializable{

    ArrayList<ToDoItem> items;

    /**
     * Getter Des items.
     * @return items Liste des items
     */
    ArrayList<ToDoItem> getItems() {
        return items;
    }

    /**
     * Setter de la liste d'Item ToDo
     *
     * @param items Liste des items
     */
    public void setItems(ArrayList<ToDoItem> items) {
        this.items = items;
    }

    /**
     * Setter de
     * @param item Liste des Items
     */
    public void addItem(ToDoItem item){
        this.addItem(item);
    }
}
