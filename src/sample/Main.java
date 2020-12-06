package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;
import java.util.Random;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Password generator");

        // to add children
        Group root = new Group();

        //label
        Label passGenerator = new Label("Password generator ");

        passGenerator.setTextFill(Color.BLUE);
        passGenerator.setFont(new Font("Arial", 24));

        passGenerator.setTranslateX(100);
        passGenerator.setTranslateY(75);
        //textfield
        TextField textpassword = new TextField();
        textpassword.setTranslateX(75);
        textpassword.setTranslateY(150);

        //Button
        Button generate = new Button("generate");
        generate.setTooltip(new Tooltip("generate a new Password "));
        generate.setTranslateX(75);
        generate.setTranslateY(180);
        generate.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            generate.setScaleX(1.2);
        });
        generate.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            generate.setScaleX(1);
        });
        generate.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            int length = 6;
            final char[] lowerCase = "abcdefghijklmnopqrstuwwxyz".toCharArray();
            final char[] upperCase = "ABCDEFJHIJKLMNOPQRSTUVWXYZ".toCharArray();
            final char[] numbers = "0123456789".toCharArray();
            final char[] sympol = "^$?!@#+*%".toCharArray();
            final char[] allAllowed = "abcdefghijklmnopqrstuwwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ0123456789^$?!@#+*%".toCharArray();
            //build the password
            Random random = new SecureRandom();
            StringBuilder passwords = new StringBuilder();
            for (int i = 0; i < length; i++) {
                passwords.append(allAllowed[random.nextInt(allAllowed.length)]);
            }
            //ensure password policy is met by inserting required random chars in random positions
            passwords.insert(random.nextInt(passwords.length()), lowerCase[random.nextInt(lowerCase.length)]);
            passwords.insert(random.nextInt(passwords.length()), lowerCase[random.nextInt(upperCase.length)]);
            passwords.insert(random.nextInt(passwords.length()), lowerCase[random.nextInt(numbers.length)]);
            StringBuilder insert = passwords.insert(random.nextInt(passwords.length()), lowerCase[random.nextInt(sympol.length)]);
            textpassword.setText(passwords.toString());

        });//pass generate
        Button copy = new Button("Copy");
        copy.setTooltip(new Tooltip("Copy to Clipboard"));
        copy.setTranslateX(145);
        copy.setTranslateY(180);
        copy.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            copy.setScaleX(1.2);
        });
        copy.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            copy.setScaleX(1);
        });
        copy.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            //copy a text in a clip board
            StringSelection stringSelection = new StringSelection(textpassword.getText());
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);

        });

        Button clear = new Button("Clear");
        clear.setTranslateX(195);
        clear.setTranslateY(180);
        clear.setTooltip(new Tooltip("to clear the text Field"));
        clear.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            clear.setScaleX(1.2);
        });
        clear.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            clear.setScaleX(1);
        });
        clear.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e) ->{
            textpassword.setText("");
        });

        //add the element


        root.getChildren().add(passGenerator);
        root.getChildren().add(textpassword);
        root.getChildren().add(generate);
        root.getChildren().add(copy);
        root.getChildren().add(clear);
        //add the scene
        Scene scene = new Scene(root, 400, 450);
        primaryStage.setScene(scene);

        primaryStage.show();
    }//start

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {


        launch(args);
    }
}
