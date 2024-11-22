package org.jetsettersv2.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetsettersv2.collections.ArrayListGeneric;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonUtil {
    public static final String PATH_RESOURCES = System.getProperty("user.dir") + "/src/main/resources/org.jetsettersv2";
    public static final String PATH_FLOTA = "/aviones.json";
    public static final String PATH_VUELOS = "/vuelos.json";
    public static final String PATH_USUARIOSCLIENTE = "/usuarioscliente.json";
    public static final String PATH_PERSONAS = "/personas.json";
    public static final String PATH_USUARIOSEMPLEADOS = "/usuariosempleados.json";
    public static final String PATH_RUTAS = "/rutas.json";;
    public static final String PATH_RESERVAS = "/reservas.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Metodo para leer un archivo JSON y convertirlo en un ArrayList de objetos Java
    public static <T> List<T> getJsonToList(String filePath, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            // Leer el archivo JSON y convertirlo en una lista de objetos
            list = objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<T>>() {}
            );
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        return list;
    }

    // Metodo para escribir un ArrayList de objetos Java en un archivo JSON
    public static void writeListToJsonFile(ArrayListGeneric list, String filePath) {
       try {
            // Escribir el ArrayList en el archivo JSON
            objectMapper.writeValue(new File(filePath), list);
            System.out.println("Archivo JSON escrito exitosamente en: " + filePath);
       } catch (IOException e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
       }
    }


}
