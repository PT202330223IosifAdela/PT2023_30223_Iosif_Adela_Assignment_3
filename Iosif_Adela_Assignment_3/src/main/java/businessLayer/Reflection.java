package businessLayer;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Reflection<T> {
    /**
     * Se face reflection
     * Se creeaza un tabel de elemente dintr-o lista de obiecte, iterand pe toate campurile clasei
     * si inserand toate valorile
     * @param list - lista de obiecte
     * @return tabel de elemente
     */
    public JTable create1(List<T> list) {
        //parcurgere elemente si creare vector
        List<String> numeCol = new ArrayList<>();
        int i = 0;

        for (Field f : list.get(0).getClass().getDeclaredFields()) {
            try {
                numeCol.add(f.getName());
                i++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        Object[][] table = new Object[list.size()][i];
        int  j = 0;
        for(T  obj: list){
            int k = 0;
            for(Field f: obj.getClass().getDeclaredFields()){
                f.setAccessible(true);
                try{
                    table[j][k++] = f.get(obj);
                }
                catch(IllegalAccessException e){
                    System.out.println("Eroare la accesare");
                }
            }
            j++;
        }
        return new JTable(table, numeCol.toArray());
    }
}
