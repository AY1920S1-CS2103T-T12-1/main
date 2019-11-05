package seedu.address.ui.views;

import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.member.Member;
import seedu.address.model.task.Task;
import seedu.address.ui.UiPart;

public class IndivMemberCard extends UiPart<Region> {
    private static final String FXML = "IndivMemberCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ProjectDashboard level 4</a>
     */

    public final Member member;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label displayId;
    @FXML
    private ImageView imageView;
    @FXML
    private FlowPane tags;
    @FXML
    private Label listTasks;
    @FXML
    private SplitPane split_pane_indiv;
    @FXML
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    public IndivMemberCard(Member member, int displayedIndex) {
        super(FXML);
        this.member = member;
        id.setText(displayedIndex + ". ");
        displayId.setText("Member ID: " + member.getId().getDisplayId());
        name.setText(member.getName().fullName);
        member.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (member.getImage() == null) {
            imageView.setImage(new Image(this.getClass().getResourceAsStream("/images/DaUser.png")));
        } else {
            imageView.setImage(member.getImage());
        }

        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
    }

    public IndivMemberCard(Member member, int displayedIndex, List<Task> tasks) {
        super(FXML);
        this.member = member;
        id.setText(displayedIndex + ". ");
        displayId.setText("Member ID: " + member.getId().getDisplayId());
        name.setText(member.getName().fullName);
        member.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        alert.setHeaderText("Image not found.");
        Label label = new Label("Image file for member with Member ID: " + member.getId().getDisplayId() +
                " has been moved. \nPlease move the file back, or set a new image for the member.");
        label.setWrapText(true);
        alert.getDialogPane().setContent(label);

        if (member.getImage() == null) {
            imageView.setImage(new Image(this.getClass().getResourceAsStream("/images/DaUser.png")));
        } else if (member.getImage().errorProperty().getValue()) {
            imageView.setImage(new Image(this.getClass().getResourceAsStream("/images/DaUser.png")));
            alert.showAndWait();
        } else {
            imageView.setImage(member.getImage());
        }

        imageView.setFitHeight(120);
        imageView.setFitWidth(120);


        String listOfTasks = "";

        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks += (i+1) + ". " + tasks.get(i).toStringShort() + "\n";
        }

        listTasks.setText(listOfTasks);
        listTasks.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        IndivMemberCard card = (IndivMemberCard) other;
        return id.getText().equals(card.id.getText())
                && member.equals(card.member);
    }
}
