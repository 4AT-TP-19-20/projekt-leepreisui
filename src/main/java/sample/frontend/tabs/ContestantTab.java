package sample.frontend.tabs;

import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sample.backend.data.Data;
import sample.Main;
import sample.backend.Searchables;
import sample.backend.data.Contestant;
import sample.frontend.controls.CustomTableView;
import sample.frontend.detailviews.ContestantDetailView;
import sample.frontend.controls.SwitchBox;

public class ContestantTab extends BorderPane {
    private final CustomTableView<Contestant> tbv_contestants;

    public ContestantTab() {
        tbv_contestants = new CustomTableView<>();

        //Top
        TextField txt_search = new TextField();
        txt_search.setPromptText("Suche nach Name, Klasse, ...");
        txt_search.setMaxWidth(10000);
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> textChangeListener(newValue));
        this.setTop(txt_search);

        //Center
        tbv_contestants.<String>addColumn("Vorname", new PropertyValueFactory<>("firstName"));
        tbv_contestants.<String>addColumn("Nachname", new PropertyValueFactory<>("lastName"));
        tbv_contestants.<String>addColumn("Klasse", new PropertyValueFactory<>("grade"));
        tbv_contestants.<Integer>addColumn("Gelesene Bücher", new PropertyValueFactory<>("bookCount"));
        tbv_contestants.<Integer>addColumn("Punkte", new PropertyValueFactory<>("points"));
        tbv_contestants.<StackPane>addColumn("Qualifiziert", param -> new SwitchBox(param.getValue().qualifiedProperty(), "small", false));

        tbv_contestants.getItems().addAll(Data.contestants);
        tbv_contestants.setOnMouseClicked((MouseEvent event) -> {
            if(event.getTarget() instanceof TableColumnHeader) {
                tbv_contestants.getSelectionModel().clearSelection();
                return;
            }
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Contestant selected = tbv_contestants.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    Main.getCurrentContentStack().push(new ContestantDetailView(selected));
                }
            }
        });
        this.setCenter(tbv_contestants);

        //Bottom
        VBox bottomItems = new VBox();
        Button btn_addContestant = new Button("Neuer Teilnehmer");
        btn_addContestant.setId("custom-button");
        btn_addContestant.setOnAction(e->{
            Contestant toAdd = new Contestant();
            Data.contestants.add(toAdd);
            textChangeListener(txt_search.getText());
            Main.getCurrentContentStack().push(new ContestantDetailView(toAdd));
        });
        Button btn_removeContestant = new Button("Teilnehmer löschen");
        btn_removeContestant.setId("red-button");
        btn_removeContestant.setOnAction(e->{
            Contestant selected = tbv_contestants.getSelectionModel().getSelectedItem();
            if(selected != null) {
                Data.contestants.remove(selected);
                textChangeListener(txt_search.getText());
            }
        });
        bottomItems.getChildren().addAll(btn_addContestant, btn_removeContestant);
        bottomItems.setSpacing(5);
        this.setBottom(bottomItems);

        BorderPane.setMargin(this.getCenter(), new Insets(10, 0, 10, 0));
        this.setPadding(new Insets(10));
    }

    private void textChangeListener(String newValue) {
        newValue = newValue.trim().toLowerCase();
        tbv_contestants.getItems().clear();

        if(newValue.trim().isEmpty()) {
            tbv_contestants.getItems().addAll(Data.contestants);
            return;
        }

        for(Contestant contestant : Data.contestants) {
            if(Searchables.contain(newValue, contestant)) {
                tbv_contestants.getItems().add(contestant);
            }
        }
    }
}
