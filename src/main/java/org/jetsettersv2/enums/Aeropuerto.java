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
    JUJ("JUJ", "Aeropuerto Internacional Gobernador Horacio Guzmán", "San Salvador de Jujuy"),
    FTE("FTE", "Aeropuerto Internacional Comandante Armando Tola", "El Calafate"),
    NQN("NQN", "Aeropuerto Internacional Presidente Perón", "Neuquén"),
    RGL("RGL", "Aeropuerto Internacional Piloto Civil Norberto Fernández", "Río Gallegos"),
    PSS("PSS", "Aeropuerto Internacional Libertador General José de San Martín", "Posadas"),
    AFA("AFA", "Aeropuerto Suboficial Ayudante Santiago Germano", "San Rafael"),
    RCU("RCU", "Aeropuerto Área Material Río Cuarto", "Río Cuarto"),
    VDM("VDM", "Aeropuerto Gobernador Edgardo Castello", "Viedma"),
    RGA("RGA", "Aeropuerto Internacional Gobernador Ramón Trejo Noel", "Río Grande"),
    RES("RES", "Aeropuerto Internacional Resistencia", "Resistencia"),
    CNQ("CNQ", "Aeropuerto Internacional Doctor Fernando Piragine Niveyro", "Corrientes"),
    IGR("IGR", "Aeropuerto Internacional Cataratas del Iguazú", "Puerto Iguazú"),
    BHI("BHI", "Aeropuerto Comandante Espora", "Bahía Blanca"),
    SDE("SDE", "Aeropuerto Vicecomodoro Ángel de la Paz Aragonés", "Santiago del Estero"),
    PMY("PMY", "Aeropuerto El Tehuelche", "Puerto Madryn"),
    CTC("CTC", "Aeropuerto Coronel Felipe Varela", "Catamarca"),
    VVI("VVI", "Aeropuerto Internacional General Enrique Mosconi", "Villa Mercedes"),
    FMA("FMA", "Aeropuerto Formosa", "Formosa");

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
