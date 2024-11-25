package org.jetsettersv2.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetsettersv2.collections.ArrayListGeneric;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JacksonUtil {
    public static final String PATH_RESOURCES = System.getProperty("user.dir") + "/src/main/resources";
    public static final String PATH_FLOTA = "/aviones.json";
    public static final String PATH_VUELOS = "/vuelos.json";
    public static final String PATH_USUARIOSCLIENTE = "/usuariosClientes.json";
    public static final String PATH_ADMINISTRADORES = "/administradores.json";
    public static final String PATH_TRIPULACIONCABINA = "/tripulacioncabina.json";
    public static final String PATH_TRIPULACIONTECNICA = "/tripulaciontecnica.json";
    public static final String PATH_RUTAS = "/rutas.json";
    public static final String PATH_RESERVAS = "/reservas.json";
    public static final String PATH_AER = "/aeropuertos.json";

    private static final ObjectMapper objectMapper = createObjectMapper();

    /**
     * Configura y retorna un ObjectMapper con las opciones necesarias para manejar JSON.
     */
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registrar el módulo para manejo de fechas y tiempos de Java 8
        mapper.registerModule(new JavaTimeModule());
        // Deshabilitar fallo en serialización de fechas como timestamps
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // Ignorar propiedades desconocidas al deserializar
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Aceptar valores nulos durante la deserialización
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return mapper;
    }

    /**
     * Lee un archivo JSON y lo convierte en una lista de objetos del tipo especificado.
     *
     * @param filePath Ruta del archivo JSON.
     * @param clazz    Clase de los objetos dentro de la lista.
     * @param <T>      Tipo genérico de los objetos.
     * @return Lista de objetos deserializados.
     * @throws IOException Si ocurre un error al leer o parsear el archivo.
     */
    public static <T> ArrayList<T> getJsonToList(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz)
        );
    }

    /**
     * Variante alternativa para convertir JSON a lista utilizando un TypeReference.
     *
     * @param filePath Ruta del archivo JSON.
     * @param clazz    Clase de los objetos dentro de la lista.
     * @param <T>      Tipo genérico de los objetos.
     * @return Lista de objetos deserializados.
     * @throws IOException Si ocurre un error al leer o parsear el archivo.
     */
    public static <T> ArrayList<T> getJsonToList2(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                new File(filePath),
                new TypeReference<ArrayList<T>>() {}
        );
    }

    /**
     * Escribe una lista de objetos en un archivo JSON con formato legible.
     *
     * @param list     Lista de objetos a serializar.
     * @param filePath Ruta donde se escribirá el archivo JSON.
     * @param <T>      Tipo genérico de los objetos.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public static <T> void writeListToJsonFile(ArrayListGeneric<T> list, String filePath) throws IOException {
        // Verificar si la lista tiene elementos
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("La lista está vacía o es nula. No se escribió ningún archivo.");
        }

        // Escribir la lista completa en el archivo JSON con formato legible
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
    }
}
