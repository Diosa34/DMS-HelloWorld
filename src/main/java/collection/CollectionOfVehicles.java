package collection;

import classes.Vehicle;

import java.util.LinkedList;

public class CollectionOfVehicles extends LinkedList<Vehicle> {
    public static CollectionOfVehicles globalCollection = new CollectionOfVehicles();
}
