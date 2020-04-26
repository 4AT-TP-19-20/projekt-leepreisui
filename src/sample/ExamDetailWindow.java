package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ExamDetailWindow {
    private static Stage stage;
    private static TextField txt_title;
    private static TextField txt_authorFirstName;
    private static TextField txt_authorLastName;
    private static TextField txt_language;
    private static TextField txt_date;
    private static TextField txt_librarian;
    private static SwitchBox sbx_passed;
    private static TextField txt_points;
    private static AnswerBoxes abx_answers;

    public static void showNewWindow(Exam exam) {
        stage = new Stage();
        GridPane gridPane = new GridPane();

        gridPane.add(new Label("Titel"),0,0);
        txt_title = new TextField(exam.getBook().getTitle());
        txt_title.setEditable(false);
        gridPane.add(txt_title,1,0,3,1);

        gridPane.add(new Label("Autor"),0,1);
        txt_authorFirstName = new TextField(exam.getBook().getAuthorFirstName());
        txt_authorFirstName.setEditable(false);
        gridPane.add(txt_authorFirstName,1,1,2,1);
        txt_authorLastName = new TextField(exam.getBook().getAuthorLastName());
        txt_authorLastName.setEditable(false);
        gridPane.add(txt_authorLastName, 3,1);

        gridPane.add(new Label("Sprache"), 0, 2);
        txt_language = new TextField(exam.getBook().getLanguage());
        txt_language.setEditable(false);
        gridPane.add(txt_language, 1, 2,3,1);

        gridPane.add(new Label("Datum"), 0, 3);
        txt_date = new TextField(exam.getDate());
        txt_date.setEditable(false);
        gridPane.add(txt_date,1,3,3,1);

        gridPane.add(new Label("BibliothekarIn"), 0, 4);
        txt_librarian = new TextField(exam.getLibrarian());
        txt_librarian.setEditable(false);
        gridPane.add(txt_librarian,1,4,3,1);

        gridPane.add(new Label("Bestanden"),0,5);
        sbx_passed = new SwitchBox(exam.passedProperty(), "small", false, true);
        gridPane.add(sbx_passed,1,5);

        HBox hbx_pointsLabelAligner = new HBox(new Label("Erhaltene Punkte"));
        hbx_pointsLabelAligner.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(hbx_pointsLabelAligner,2,5);
        txt_points = new TextField();
        txt_points.textProperty().bindBidirectional(exam.pointsProperty(), new StringToInt());
        txt_points.setEditable(false);
        gridPane.add(txt_points,3,5);

        gridPane.add(new Canvas(1,20),0,6);

        abx_answers = new AnswerBoxes(exam.answersProperty(), true, "big");
        gridPane.add(abx_answers,0,7,4,1);

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));

        stage.setTitle("Detailansicht Prüfung");
        stage.setScene(new Scene(gridPane, 410, 300));
        stage.show();
    }
}