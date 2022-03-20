package classes;

import collection.CollectionOfVehicles;
import com.github.Diosa34.ObjectConverter.ClassAnnotation;
import com.github.Diosa34.ObjectConverter.Convertible;
import com.github.Diosa34.ObjectConverter.FieldAnnotation;
import enums.FuelType;
import enums.VehicleType;

import java.time.ZonedDateTime;

@ClassAnnotation("element")
public class Vehicle implements Convertible {
    @FieldAnnotation("id")
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @FieldAnnotation("name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @FieldAnnotation("coordinates")
    private Coordinates coordinates; //Поле не может быть null
    @FieldAnnotation("creationDate")
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @FieldAnnotation("enginePower")
    private Float enginePower; //Поле не может быть null, Значение поля должно быть больше 0
    @FieldAnnotation("type")
    private VehicleType type; //Поле не может быть null
    @FieldAnnotation("fuelType")
    private FuelType fuelType; //Поле может быть null

    public Vehicle(String name, Coordinates coordinates, Float enginePower, VehicleType type, FuelType fuelType) {
        this.id = 0;
        Integer max_id = 0;
        for (Vehicle vehicle : CollectionOfVehicles.globalCollection) {
            if (vehicle.getId() >= max_id) {
                max_id = vehicle.getId();
            }
        }
        if (this.id <= max_id) {
            this.id = max_id + 1;
        }
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
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
}
