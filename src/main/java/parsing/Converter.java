package parsing;



import Annotations.ClassAnnotation;
import Annotations.Convertible;
import Annotations.FieldAnnotation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;


public class Converter {
    FileOutputStream fos;

    public Converter(String fileName) throws IOException {
        /** Creating a file to write to */
        this.fos = new FileOutputStream(fileName);
    }

    private void writeToFile(Convertible instance, Integer indent) throws IllegalAccessException, IOException {
        /** If an object that does not contain other objects is passed to the method */
        String className = instance.getClass().getAnnotation(ClassAnnotation.class).value();
        String text1 = String.join("", Collections.nCopies(indent, " "))+"<" + className + ">\n";
        try {
            // перевод строки в байты
            byte[] buffer = text1.getBytes();
            this.fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }


        if (!Iterable.class.isAssignableFrom(instance.getClass())) {
            for (Field field : instance.getClass().getDeclaredFields()) {
                /** If the field contains data that needs to be written to the .xml file, write it to the file */
                FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
                if (fieldAnnotation != null) {
                    field.setAccessible(true);
                    String text2 = "        <" + fieldAnnotation.value() + ">"
                            + field.get(instance) + "</" + fieldAnnotation.value() + ">\n";
                    try {
                        // перевод строки в байты
                        byte[] buffer = text2.getBytes();
                        this.fos.write(buffer, 0, buffer.length);
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

        /** If an object that contain other objects is passed to the method */
        else {
            for (Object elem : (Iterable) instance) {
                /** For each object, we add a child-tag and call {@link com.github.Diosa34.ObjectConverter.Converter#writeToFile(Convertible, Integer)} */
                if (Convertible.class.isAssignableFrom(elem.getClass())){
                    writeToFile((Convertible) elem, indent + 4);
                }
                else {
                    throw new IllegalArgumentException("The objects contained in an instance do not implements interface 'Convertible'");
                }
            }
        }
        String text3 = String.join("", Collections.nCopies(indent, " "))+"</" + className + ">\n";
        try {
            // перевод строки в байты
            byte[] buffer = text3.getBytes();
            this.fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /** Call {@link parsing.Converter#writeToFile(Convertible, Integer)} to convert an object, write data to the file
     *  and close file */
    public void xmlInitialization(Convertible instance, Integer indent) throws IOException, IllegalAccessException {
        writeToFile(instance, indent);
    }
}
