package renting.entity;

public enum CarBrand {
    Audi("Audi"),
    BMW ("BMW"),
    Dacia("Dacia"),
    Ford("Ford"),
    Kia("Kia"),
    MercedesBenz("Mercedes Benz"),
    Peugeot("Peugeot"),
    Renault("Renault"),
    Toyota("Toyota"),
    Volkswagen("Volkswagen"),
    Volvo("Volvo");

    private final  String displayEnum;

    CarBrand(String displayEnum){this.displayEnum = displayEnum;}
    public String getDisplayEnum() {
        return displayEnum;
    }
}
