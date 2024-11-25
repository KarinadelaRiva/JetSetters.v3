package org.jetsettersv2.utilities;

import com.github.javafaker.Faker;
import kotlin.collections.ArrayDeque;
import org.jetsettersv2.mock.UsuarioClienteMock;
import org.jetsettersv2.models.concrete.Direccion;
import org.jetsettersv2.models.concrete.UsuarioCliente;

import java.util.*;

import static org.jetsettersv2.utilities.Tipografias.printTitulo;

public class CollectionTest {
    private static final int DATA_SIZE = 170_000;

    public static void main(String[] args) {
        int dataSize = DATA_SIZE; // Número de objetos a insertar

        // Crear datos de prueba
        List<UsuarioCliente> usuarios = generateUsuarios(dataSize);


        // Testear listas
        printTitulo("Testear listas", 30);
        System.out.println(" ");
        testList(new ArrayList<>(), usuarios, "ArrayList");
        System.out.println(" ");
        testList(new Vector<>(), usuarios, "Vector");
        System.out.println(" ");
        testList(new ArrayDeque<>(), usuarios, "Deque");
        System.out.println(" ");

        // Testear conjuntos
        printTitulo("Testear conjuntos", 30);
        System.out.println(" ");
        testSet(new HashSet<>(), usuarios, "HashSet");
        System.out.println(" ");
        testSet(new LinkedHashSet<>(), usuarios, "LinkedHashSet");
        System.out.println(" ");
        testSet(new TreeSet<>(Comparator.comparing(UsuarioCliente::toString)), usuarios, "TreeSet");
        System.out.println(" ");

        // Testear mapas
        printTitulo("Testear mapas", 30);
        System.out.println(" ");
        testMap(new HashMap<>(), usuarios, "HashMap");
        System.out.println(" ");
        testMap(new LinkedHashMap<>(), usuarios, "LinkedHashMap");
        System.out.println(" ");
        testMap(new TreeMap<>(), usuarios, "TreeMap");



    }

    // Generar lista de usuarios

//    private static List<UsuarioCliente> generateUsuarios(int size) {
//        List<UsuarioCliente> usuarios = new ArrayList<>();
//        for (int i = 0; i < size; i++) {
//            Direccion direccion = new Direccion()
//                    .calle("Calle " + i)
//                    .numero(i)
//                    .ciudad("Ciudad " + i)
//                    .codigoPostal("CP" + i)
//                    .pais("País " + i);
//
//            UsuarioCliente usuario = (UsuarioCliente) new UsuarioCliente()
//                    .nombre("Nombre " + i)
//                    .apellido("Apellido " + i)
//                    .dni("DNI" + i)
//                    .pasaporte("Pasaporte" + i)
//                    .telefono("Telefono" + i)
//                    .direccion(direccion)
//                    .email("Email" + i)
//                    .password("Password" + i);
//
//            usuarios.add(usuario);
//        }
//        return usuarios;
//    }

    private static List<UsuarioCliente> generateUsuarios(int size) {
        List<UsuarioCliente> usuarios = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            usuarios.add(UsuarioClienteMock.generarUsuarioAleatorio());
        }
        return usuarios;
    }

    // Test de listas
    private static void testList(List<UsuarioCliente> list, List<UsuarioCliente> usuarios, String listName) {
        System.out.println("Testing " + listName);

        // Tiempo de carga
        long loadTime = TimerUtil.measureTime(() -> list.addAll(usuarios));
        System.out.println("Tiempo de Carga: " + loadTime / DATA_SIZE + " ms");

        // Tiempo de búsqueda
        UsuarioCliente last = usuarios.get(usuarios.size() - 1);
        long searchTime = TimerUtil.measureTime(() -> list.contains(last));
        System.out.println("Tiempo de búsqueda del último elemento: " + searchTime / DATA_SIZE + " ms");
    }

    // Test de conjuntos
    private static void testSet(Set<UsuarioCliente> set, List<UsuarioCliente> usuarios, String setName) {
        System.out.println("Testing " + setName);

        // Tiempo de carga
        long loadTime = TimerUtil.measureTime(() -> set.addAll(usuarios));
        System.out.println("Tiempo de Carga: " + loadTime / DATA_SIZE + " ms");

        // Tiempo de búsqueda
        UsuarioCliente last = usuarios.get(usuarios.size() - 1);
        long searchTime = TimerUtil.measureTime(() -> set.contains(last));
        System.out.println("Tiempo de búsqueda del último elemento: " + searchTime / DATA_SIZE + " ms");
    }

    // Test de mapas
    private static void testMap(Map<String, UsuarioCliente> map, List<UsuarioCliente> usuarios, String mapName) {
        System.out.println("Testing " + mapName);

        // Tiempo de carga
        long loadTime = TimerUtil.measureTime(() -> {
            for (UsuarioCliente u : usuarios) {
                map.put(u.getIdPersona(), u);
            }
        });
        System.out.println("Tiempo de Carga: " + loadTime / DATA_SIZE + " ms");

        // Tiempo de búsqueda
        UsuarioCliente last = usuarios.get(usuarios.size() - 1);
        long searchTime = TimerUtil.measureTime(() -> map.get(last.getIdPersona()));
        System.out.println("Tiempo de búsqueda del último elemento: " + searchTime / DATA_SIZE + " ms");
    }




}