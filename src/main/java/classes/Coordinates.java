package classes;

/**
 * Entity coordinate class
 */
public class Coordinates {
    private Float x; //Поле не может быть null
    private Integer y; //Поле не может быть null

    public Coordinates(Float x, Integer y){
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}