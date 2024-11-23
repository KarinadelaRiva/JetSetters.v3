package org.jetsettersv2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import org.jetsettersv2.models.abstracts.Persona;
import org.jetsettersv2.models.concrete.Direccion;
import org.jetsettersv2.models.concrete.UsuarioCliente;



public class AirlineLandingPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Título y estilo
        primaryStage.setTitle("Bienvenidos a JetSetters Airlines");
        // Encabezado
        Label encabezado = new Label("JetSetters");
        encabezado.setStyle("-fx-font-size: 35px; -fx-font-weight: bold; -fx-text-fill: #394b73;");
        encabezado.setFont(Font.font("Inconsolata", 60));

        // Descripción
        Label descripcion = new Label("Tu viaje hacia el cielo comienza aquí. Vuela a destinos con comodidad y seguridad incomparables.");
        descripcion.setWrapText(true);
        descripcion.setStyle("-fx-font-size: 15px; -fx-text-fill: #394b73;");
        descripcion.setFont(Font.font("Inconsolata", 45)); //

        // Botones
        Button botonReservarVuelo = new Button("Iniciar Sesion");
        botonReservarVuelo.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #d4b4e1; -fx-text-fill: black;");

        Button botonCheckIn = new Button("Registrarse");
        botonCheckIn.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #f5a8c8; -fx-text-fill: black;");

        Button botonEstadoVuelo = new Button("Continuar sin iniciar sesion");
        botonEstadoVuelo.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #fdadb6; -fx-text-fill: black;");

        Button botonAdministrador = new Button("Administrador");
        botonAdministrador.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #a4d9f8; -fx-text-fill: black;");


        // Espaciador
        Region spacer = new Region();
        spacer.setMinHeight(20); // Altura de espaciado

        // Diseño
        VBox layout = new VBox(20, encabezado, descripcion, botonReservarVuelo, botonCheckIn,botonEstadoVuelo,spacer,botonAdministrador);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setStyle("-fx-padding: 20px; -fx-background-color: #fcf0f0;");

        // Escena
        Scene scene = new Scene(layout, 800, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //launch(args);
        //menuUsuario menuUsuario = new menuUsuario();

        Direccion direccion = new Direccion()
                .calle("Calle Falsa")
                .numero(123)
                .ciudad("Springfield")
                .codigoPostal("12345")
                .pais("EE.UU.");

        UsuarioCliente usuario = (UsuarioCliente) new UsuarioCliente()
                .nombre("carla")
                .apellido("perex")
                .dni("lalal")
                .pasaporte("asasda")
                .telefono("asdasdsa")
                .email("sadasd")
                .direccion(direccion)
                .password("asdasda");


    }
}