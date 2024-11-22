package org.jetsettersv2.enums;

public enum Aeropuerto {
    EZE("EZE", "Aeropuerto Internacional Ministro Pistarini", "Buenos Aires"),
    AEP("AEP", "Aeroparque Jorge Newbery", "Buenos Aires"),
    COR("COR", "Aeropuerto Internacional Ingeniero Ambrosio Taravella", "Córdoba"),
    MDZ("MDZ", "Aeropuerto Internacional El Plumerillo", "Mendoza"),
    ROS("ROS", "Aeropuerto Internacional Rosario Islas Malvinas", "Rosario"),
    SLA("SLA", "Aeropuerto Internacional Martín Miguel de Güemes", "Salta"),
    BRC("BRC", "Aeropuerto Internacional Teniente Luis Candelaria", "San Carlos de Bariloche"),
    REL("REL", "Aeropuerto Internacional Almirante Marcos A. Zar", "Trelew"),
    USH("USH", "Aeropuerto Internacional Malvinas Argentinas", "Ushuaia"),
    CRD("CRD", "Aeropuerto Internacional General Enrique Mosconi", "Comodoro Rivadavia"),
    TUC("TUC", "Aeropuerto Internacional Teniente Benjamín Matienzo", "San Miguel de Tucumán"),
    FTE("FTE", "Aeropuerto Internacional Comandante Armando Tola", "El Calafate"),
    NQN("NQN", "Aeropuerto Internacional Presidente Perón", "Neuquén"),
    RGL("RGL", "Aeropuerto Internacional Piloto Civil Norberto Fernández", "Río Gallegos");

    private String codigo;
    private String nombre;
    private String ciudad;

    // Constructor
    Aeropuerto(String codigo, String nombre, String ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    // Métodos getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", codigo, nombre, ciudad);
    }

}
