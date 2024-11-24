package org.jetsettersv2.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    private static final ObjectMapper objectMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registrar el módulo de fechas y horas de Java 8
        mapper.registerModule(new JavaTimeModule());
        // Configuración para deshabilitar el fallo en fechas serializadas como timestamps
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    public static <T> ArrayList<T> getJsonToList2(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                new File(filePath),
                new TypeReference<ArrayList<T>>() {}
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
