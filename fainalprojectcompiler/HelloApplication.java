package com.example.fainalprojectcompiler;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;
import javafx.stage.FileChooser;

public class HelloApplication extends Application {
    public void start(Stage stage)
    {

        try {
            stage.setTitle("FileChooser");
            FileChooser fil_chooser = new FileChooser();
            TextArea code = new TextArea("");
            code.setEditable(false);
            code.setMinHeight(300);
            Font font = Font.font("Monospaced");
            code.setFont(font);

            Label label = new Label("Consol");
            TextArea consol = new TextArea("");
            consol.setEditable(false);
            consol.setMaxHeight(20);
            Button button = new Button("Load your file");
            Button run = new Button("Run");
            button.setOnAction(n->{
                File file = fil_chooser.showOpenDialog(stage);
                if (file != null) {
                    try {
                        StringBuilder content = new StringBuilder();
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();
                        code.setText(content.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            );
            run.setOnAction(x->{
                Scanner scan = new Scanner(code.getText());
                Parser parser = new Parser(scan);//input=code , 0 , m

                try {
                    parser.parse();
                    consol.setText("Parsing completed successfully.");
                } catch (Exception e) {
                   consol.setText(e.getMessage());
                }
            });
            VBox vbox = new VBox(15, code, button,run ,label,consol);
            vbox.setAlignment(Pos.TOP_CENTER);
            Scene scene = new Scene(vbox, 800, 490);
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    public static void main(String args[])
    {
        launch(args);

    }
}