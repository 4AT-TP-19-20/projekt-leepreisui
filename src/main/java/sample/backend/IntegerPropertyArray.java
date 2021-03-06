package sample.backend;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

public class IntegerPropertyArray {
    private final int size;
    private final IntegerProperty[] properties;

    public IntegerPropertyArray(int size, int[] initialValues) {
        this.size = size;
        boolean isEmpty = initialValues[0] == -1;
        properties = new IntegerProperty[size];

        for(int i = 0; i < size; i++) {
            properties[i] = new SimpleIntegerProperty(isEmpty ? 2 : initialValues[i]);
        }
    }

    public IntegerProperty getByIndex(int index) {
        return properties[index];
    }

    public int[] get() {
        int[] values = new int[size];

        for(int i = 0; i < size; i++) {
            values[i] = properties[i].get();
        }

        return values;
    }

    public void set(int[] array) {
        for (int i = 0; i < size; i++) {
            properties[i].set(array[i]);
        }
    }

    public void addListener(ChangeListener<? super Number> changeListener) {
        for(IntegerProperty property : properties) {
            property.addListener(changeListener);
        }
    }

    public String toCompactString() {
        String ret = "";

        for(IntegerProperty property : properties) {
            ret += String.valueOf(property.get());
        }

        return ret;
    }
}
