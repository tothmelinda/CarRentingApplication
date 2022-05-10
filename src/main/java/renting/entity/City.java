package renting.entity;

public enum City {
    Iasi("Iasi"),
    ClujNapoca("Cluj-Napoca"),
    Bucuresti("Bucuresti"),
    Pitesti("Ploiesti"),
    Timisoara("Timisoara"),
    Bacau("Bacau"),
    Oradea("Oradea"),
    Sibiu("Sibiu"),
    TarguMures("Targu Mures"),
    Galati("Galati"),
    Suceava("Suceava"),
    DrobetaTurnuSeverin("Drobeta Turnu Severin"),
    Ploiesti ("Ploiesti"),
    Arad("Arad"),
    Constanta("Constanta"),
    AlbaIulia("Alba-Iulia"),
    SatuMare("Satu Mare"),
    BaiaMare("Baia Mare"),
    Brasov("Brasov"),
    Bistrita("Bistrita"),
    Buzau("Buzau"),
    TarguJiu("Targu Jiu"),
    RamnicuValcea ("Ramnicu Valcea"),
    Giurgiu("Giurgiu"),
    Vaslui("Vaslui"),
    Tulcea("Tulcea"),
    Slatina("Slatina"),
    Resita("Resita"),
    Calarasi("Calarasi"),
    Targoviste("Targoviste"),
    Botosani("Botosani"),
    Braila("Braila"),
    Focsani("Focsani"),
    Deva("Deva");

    private final String displayEnumCity;

    City(String displayEnumCity) {
        this.displayEnumCity = displayEnumCity;
    }

    public String getDisplayEnumCity() {
        return displayEnumCity;
    }
}
