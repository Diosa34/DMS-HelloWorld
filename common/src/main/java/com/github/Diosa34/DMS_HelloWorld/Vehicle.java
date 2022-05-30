package com.github.Diosa34.DMS_HelloWorld;

import com.github.Diosa34.DMS_HelloWorld.collection.FuelType;
import com.github.Diosa34.DMS_HelloWorld.collection.VehicleType;
import com.github.Diosa34.DMS_HelloWorld.users.User;
import com.github.Diosa34.ObjectConverter.ClassAnnotation;
import com.github.Diosa34.ObjectConverter.Convertible;
import com.github.Diosa34.ObjectConverter.FieldAnnotation;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

/**
 * Description of the entity, objects in the com.github.Diosa34.DMS_HelloWorld.collection
 */
@ClassAnnotation("element")
public class Vehicle implements Convertible, Comparable<String> {
    @FieldAnnotation("id")
    public Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @FieldAnnotation("name")
    private final String name; //Поле не может быть null, Строка не может быть пустой
    @FieldAnnotation("coordinates")
    private final Coordinates coordinates; //Поле не может быть null
    @FieldAnnotation("creationDate")
    private final ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @FieldAnnotation("enginePower")
    private final Float enginePower; //Поле не может быть null, Значение поля должно быть больше 0
    @FieldAnnotation("vehicleType")
    private final VehicleType vehicleType; //Поле не может быть null
    @FieldAnnotation("fuelType")
    private final FuelType fuelType; //Поле может быть null
    private final String username;

    /**
     * Constructor for creating an instance based on console input
     */
    public Vehicle(String name, Coordinates coordinates, Float enginePower, VehicleType type, FuelType fuelType, String username) {
        this.username = username;
        this.id = null;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.enginePower = enginePower;
        this.vehicleType = type;
        this.fuelType = fuelType;
    }

    public Vehicle(Integer id, String name, Coordinates coordinates, Float enginePower, VehicleType type, FuelType fuelType, String username) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.username = username;
        this.creationDate = ZonedDateTime.now();
        this.enginePower = enginePower;
        this.vehicleType = type;
        this.fuelType = fuelType;
    }

    /**
     * Constructor for creating an instance based on file
     */
    public Vehicle(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, Float enginePower, VehicleType type, FuelType fuelType, String author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.vehicleType = type;
        this.fuelType = fuelType;
        this.username = author;
    }


    @Override
    public String toString() {
        return "Номер: " + getId() + "\n" + "Марка: " + getName() + "\n" + "Координаты: " + getCoordinates().toString() + "\n" +
                "Дата создания: " + getCreationDate() + "\n" + "Мощность двигателя: " + getEnginePower() + "\n" +
                "Тип средства передвижения: " + getType() + "\n" + "Тип топлива: " + getFuelType();
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
        return vehicleType;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public String getUserName() { return this.username; }
    /**
     * Comparison of vehicle names
     */
    @Override
    public int compareTo(@NotNull String o) {
        return name.length() - o.length();
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
