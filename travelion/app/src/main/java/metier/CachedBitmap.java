package metier;

/**
 * Classe D'une bitmap serializable.
 * Created by julien on 24/03/18.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CachedBitmap implements Serializable{

    private static final long serialVersionUID = -6298516694275121291L;

    Date inserted;
    transient Bitmap bitmap;
    String url;

    public CachedBitmap(){};

    public CachedBitmap(Bitmap b){
        bitmap = b;

    }
    public Bitmap getImage()
    {
        return bitmap;
    }
    public void setImage(Bitmap m)
    {
        bitmap = m;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException{
        // This will serialize all fields that you did not mark with 'transient'
        // (Java's default behaviour)
        oos.defaultWriteObject();
        // Now, manually serialize all transient fields that you want to be serialized
        if(bitmap!=null){
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            boolean success = bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
            if(success){
                oos.writeObject(byteStream.toByteArray());
            }
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        // Now, all again, deserializing - in the SAME ORDER!
        // All non-transient fields
        ois.defaultReadObject();
        // All other fields that you serialized
        byte[] image = (byte[]) ois.readObject();
        if(image != null && image.length > 0){
            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        }
    }

}