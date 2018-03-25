package persistance;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import metier.Carnet;

/**
 * Classe de la persistance implement l'interface de la persistance.
 * Created by alicevillafranca on 25/03/2018.
 */

public class CarnetPersistance implements IPersistance {



    public CarnetPersistance() {
    }


    private static final String filename = "carnet_save";

    /**
     * Retourne le carnet depuis un fichier
     * @param context Context de l'application
     * @return
     */
    @Override
    public Carnet loadCarnet(Context context) {

        FileInputStream inputStream;

        try{
            inputStream = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Carnet c = (Carnet) ois.readObject();
            if(c!=null){
                Log.i("pers","C'est nickel");
                return c;
            }
            Log.i("pers","Bon au moins y a pas d'exception");
            return new Carnet();
        }
        catch (Exception e){
            e.printStackTrace();
            return new Carnet();
        }

    }

    /**
     * Sauvegarde le carnet dans un fichier
     * @param c       le carnet
     * @param context context de l'application
     */
    @Override
    public void saveCarnet(Carnet c, Context context) {

        FileOutputStream outputStream;


        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(c);
            outputStream.close();
            Log.i("pers","Pareil pas d'exception");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
