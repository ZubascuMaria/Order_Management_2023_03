package Connection;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionClass {
    /**
     *
     * @param object
     * Aceasta metoda primeste un obicet si parcurge toate atributele clasei obiectului
     */


    public static ArrayList<Object> retrieveProperties(Object object) {

        // trebuie sa faci sa-ti returneze proprietatile ( O LISTA)
        ArrayList<Object> list=new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true); // set modifier to public
            Object value;
            try {
                value = field.get(object);
                list.add(field.getName());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

}
