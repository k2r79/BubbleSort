package fr.epsi.tri_bulle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {
    private static int NOMBRE_DE_VALEURS = 500;

    private static ObservableList<XYChart.Data> tableau;

    @Override
    public void start(Stage primaryStage) throws Exception{
        resetTableau();

        GridPane root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Tri à bulle");
        primaryStage.setScene(new Scene(root, 800, 500));

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        ScatterChart<Number, Number> chart = new ScatterChart<Number, Number>(xAxis, yAxis);
        chart.setTitle("Tri");
        chart.setAnimated(false);

        XYChart.Series serie = new XYChart.Series(tableau);
        serie.setName("Valeurs");

        chart.getData().add(serie);

        ((VBox) root.lookup("#progressVBox")).getChildren().add(chart);

        primaryStage.show();
    }

    public synchronized static void resetTableau() {
        Random random = new Random();
        ArrayList<ScatterChart.Data> tmpTableau = new ArrayList<ScatterChart.Data>();
        for (int i = 0; i < NOMBRE_DE_VALEURS; i++) {
            tmpTableau.add(new ScatterChart.Data(i, random.nextInt()));
        }

        tableau = FXCollections.observableArrayList(tmpTableau);
    }

    public static synchronized ObservableList<ScatterChart.Data> getTableau() {
        return tableau;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
