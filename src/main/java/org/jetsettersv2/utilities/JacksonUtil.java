package org.jetsettersv2.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetsettersv2.collections.ArrayListGeneric;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonUtil {
    public static final String PATH_RESOURCES = System.getProperty("user.dir") + "/src/main/resources";
    public static final String PATH_FLOTA = "/aviones.json";
    public static final String PATH_VUELOS = "/vuelos.json";
    public static final String PATH_USUARIOSCLIENTE = "/usuariosClientes.json";
    public static final String PATH_USUARIOSEMPLEADOS = "/usuariosempleados.json";
    public static final String PATH_RUTAS = "/rutas.json";
    public static final String PATH_RESERVAS = "/reservas.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> ArrayList<T> getJsonToList2(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                new File(filePath),
                new TypeReference<ArrayList<T>>() {}  // Aquí es donde especificamos ArrayList<T> explícitamente
        );
    }

    public static <T> ArrayList<T> getJsonToList(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz)
        );
    }

    public static <T> void writeListToJsonFile(ArrayListGeneric<T> list, String filePath) throws IOException {
        // Verificar si la lista tiene elementos
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("La lista está vacía o es nula. No se escribió ningún archivo.");
        }

        // Escribir la lista completa en el archivo JSON con formato legible
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
    }

}
