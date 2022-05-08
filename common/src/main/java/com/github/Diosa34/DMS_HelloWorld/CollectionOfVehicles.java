package com.github.Diosa34.DMS_HelloWorld;

import com.github.Diosa34.ObjectConverter.ClassAnnotation;
import com.github.Diosa34.ObjectConverter.Convertible;

import java.util.LinkedList;

/**
 * Instance com.github.Diosa34.DMS_HelloWorld.collection
 * CollectionOfVehicles contain collection of vehicles
 */
@ClassAnnotation(value = "Vehicle")
public class CollectionOfVehicles extends LinkedList<Vehicle> implements Convertible {
    public static CollectionOfVehicles globalCollection = new CollectionOfVehicles();
}
