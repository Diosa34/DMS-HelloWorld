package com.github.Diosa34.DMS_HelloWorld.collection;

import com.github.Diosa34.DMS_HelloWorld.classes.Vehicle;
import com.github.Diosa34.ObjectConverter.ClassAnnotation;
import com.github.Diosa34.ObjectConverter.Convertible;

import java.util.LinkedList;

/**
 * Instance com.github.Diosa34.DMS_HelloWorld.collection
 */
@ClassAnnotation(value = "Vehicle")
public class CollectionOfVehicles extends LinkedList<Vehicle> implements Convertible {
    public static CollectionOfVehicles globalCollection = new CollectionOfVehicles();
}
