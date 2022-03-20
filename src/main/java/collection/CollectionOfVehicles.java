package collection;

import classes.Vehicle;
import com.github.Diosa34.ObjectConverter.ClassAnnotation;
import com.github.Diosa34.ObjectConverter.Convertible;

import java.util.LinkedList;

@ClassAnnotation(value = "Vehicle")
public class CollectionOfVehicles extends LinkedList<Vehicle> implements Convertible {
    public static CollectionOfVehicles globalCollection = new CollectionOfVehicles();
}
