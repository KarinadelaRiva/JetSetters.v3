package org.jetsettersv2.utilities;

public class Tipografias {

    public static void printTitulo(String title, int width) {
        if (title.length() >= width) {
            System.out.println(title); // Si es más largo, solo imprime el título
            return;
        }
        int padding = (width - title.length()) / 2;
        String line = "=".repeat(width);
        String formattedTitle = " ".repeat(padding) + title + " ".repeat(padding);
        System.out.println(line);
        System.out.println(formattedTitle);
        System.out.println(line);
    }

    // Uso
//    printTitleCentered("Testing Vector", 30);

    public static void printTituloEstrella(String title) {
        String line = "*".repeat(title.length() + 6);
        System.out.println(line);
        System.out.println("*  " + title + "  *");
        System.out.println(line);
    }

    // Uso
    // printBanner("Performance Testing");
}
