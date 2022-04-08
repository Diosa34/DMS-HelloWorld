package com.github.Diosa34.DMS_HelloWorld.parsing;

import com.github.Diosa34.DMS_HelloWorld.classes.*;
import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles;
import com.github.Diosa34.DMS_HelloWorld.commands.FunctionsKt;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Read class from xml
 */
public class XMLToJava {
    private final HashMap<String, String> entity;

    /**
     * @param vehicle - entity field HashMap
     */
    public XMLToJava(HashMap<String, String> vehicle) {
        this.entity = vehicle;
    }

    /**
     * Reads a file line by line and listens for events
     */
    public void decoder(String fileName) {
        boolean child_tag_state = false;
        boolean field_tag = false;
        boolean root = false;
        try {
            XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedReader(new FileReader(fileName)));
            String tag = "";
            String text = "";
            while (xmlReader.hasNext()) {
                xmlReader.next();
                if (xmlReader.isStartElement()) {
                    tag = xmlReader.getLocalName(); // открывающийся тег
                    if (!root) {
                        root = true;
                    } else if (!child_tag_state) {
                        child_tag_state = true;
                    } else if (!field_tag) {
                        field_tag = true;
                    }
                } else if (xmlReader.isEndElement()) {
                    if (field_tag) {
                        if (text.equals("")) {
                            System.out.println(String.format("Тег %s пуст", tag));
                            break;
                        }
                        field_tag = false;
                        text = "";
                    } else if (child_tag_state) {
                        child_tag_state = false;
                        Vehicle instance = newInstance();
                        if (instance != null) {
                            addInstance(instance);
                            System.out.println(String.format("Количество элементов в коллекции: %s", CollectionOfVehicles.globalCollection.size()));
                        }
                        entity.replaceAll((i, v) -> "");
                    }
                } else if (xmlReader.hasText() && xmlReader.getText().trim().length() == 0 && field_tag) {
                    break;
                } else if (xmlReader.hasText() && xmlReader.getText().trim().length() > 0) {
                    text = xmlReader.getText().trim();
                    if (child_tag_state && !field_tag) {
                        field_tag = true;
                    }
                    try {
                        field_initialization(tag, xmlReader.getText()); // содержание тега
                    } catch (TagNameException ex) {
                        System.out.printf("Ошибка в имени поля, поле %s не найдено", tag);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Некорректный путь к файлу, файл не найден");
        } catch (XMLStreamException ex) {
            System.out.println("Файл с данными для коллекции пуст");
        }
    }

    /**
     * Adding a tag value
     */
    private void field_initialization(String key, String value) throws TagNameException {
        if (entity.containsKey(key)) {
            entity.put(key, value);
        } else {
            throw new TagNameException();
        }
    }

    private Vehicle newInstance() {
        return FunctionsKt.instanceCreate(entity.get("id"), entity.get("creationDate"), entity.get("vehicleType"),
                entity.get("name"), entity.get("coordinates").split(" ")[0], entity.get("coordinates").split(" ")[1],
                entity.get("enginePower"), entity.get("fuelType"), 1);
    }

    private void addInstance(Vehicle instance) {
        CollectionOfVehicles.globalCollection.add(instance);
    }
}

