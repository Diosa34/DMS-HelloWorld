package collection;

import Annotations.ClassAnnotation;
import Annotations.Convertible;
import classes.Vehicle;


import java.util.LinkedList;

/**
 * Instance collection
 */
@ClassAnnotation(value = "Vehicle")
public class CollectionOfVehicles extends LinkedList<Vehicle> implements Convertible {
    public static CollectionOfVehicles globalCollection = new CollectionOfVehicles();
}
