package classes;

import collection.CollectionOfVehicles;
import com.github.Diosa34.ObjectConverter.ClassAnnotation;
import com.github.Diosa34.ObjectConverter.Convertible;
import com.github.Diosa34.ObjectConverter.FieldAnnotation;
import enums.FuelType;
import enums.VehicleType;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.time.zone.ZoneRulesException;

/**
 * Description of the entity, objects in the collection
 */
@ClassAnnotation("element")
public class Vehicle implements Convertible {
    @FieldAnnotation("id")
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @FieldAnnotation("name")
    private final String name; //Поле не может быть null, Строка не может быть пустой
    @FieldAnnotation("coordinates")
    private final Coordinates coordinates; //Поле не может быть null
    @FieldAnnotation("creationDate")
    private final ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @FieldAnnotation("enginePower")
    private final Float enginePower; //Поле не может быть null, Значение поля должно быть больше 0
    @FieldAnnotation("type")
    private final VehicleType type; //Поле не может быть null
    @FieldAnnotation("fuelType")
    private final FuelType fuelType; //Поле может быть null

    /**
     * Constructor for creating an instance based on console input
     */
    public Vehicle(String name, Coordinates coordinates, Float enginePower, VehicleType type, FuelType fuelType) {
        this.id = idGenerator();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }

    /**
     * Constructor for creating an instance based on file
     */
    public Vehicle(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, Float enginePower, VehicleType type, FuelType fuelType) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Номер:" + getId() + "\n" + "Марка:" + getName() + "\n" + "Координаты" + getCoordinates().toString() + "\n" +
                "Дата создания:" + getCreationDate() + "\n" + "Мощность двигателя:" + getEnginePower() + "\n" +
                "Тип средства передвижения:" + getType() + "\n" + "Тип топлива:" + getFuelType();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public java.time.ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public VehicleType getType() {
        return type;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * A new ID is created that is one greater than the largest existing one.
     */
    public static Integer idGenerator(){
        Integer max_id = 0;
        for (Vehicle vehicle : CollectionOfVehicles.globalCollection) {
            if (vehicle.getId() >= max_id) {
                max_id = vehicle.getId();
            }
        }
        return max_id + 1;
    }
}
