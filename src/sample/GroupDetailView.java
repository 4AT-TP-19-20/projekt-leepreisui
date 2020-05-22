package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.Map;

public class GroupDetailView extends BorderPane {
    public GroupDetailView(Map.Entry<String, Group> entry) {
        //Top
        HBox topItems = new HBox();
        topItems.getChildren().add(new Label("Klasse"));
        TextField txt_grade = new TextField(entry.getKey());
        txt_grade.setEditable(false);
        topItems.getChildren().add(txt_grade);
        topItems.getChildren().add(new Label("Qualifiziert"));
        SwitchBox sbx_qualified = new SwitchBox(entry.getValue().qualifiedProperty(), "small", false);
        topItems.getChildren().add(sbx_qualified);
        topItems.setSpacing(10);
        topItems.setAlignment(Pos.CENTER_LEFT);
        this.setTop(topItems);

        //Center
        CustomTableView<Contestant> tbv_members = new CustomTableView<>();
        tbv_members.addColumn("Vorname", "", new PropertyValueFactory<>("firstName"));
        tbv_members.addColumn("Nachname", "", new PropertyValueFactory<>("lastName"));
        tbv_members.addColumn("Gelesene Bücher", 0, new PropertyValueFactory<>("bookCount"));
        tbv_members.addColumn("Punkte", 0, new PropertyValueFactory<>("points"));
        tbv_members.getItems().addAll(entry.getValue().getMembers());
        this.setCenter(tbv_members);

        BorderPane.setMargin(this.getCenter(), new Insets(10,0,0,0));
        this.setPadding(new Insets(10));
    }
}
