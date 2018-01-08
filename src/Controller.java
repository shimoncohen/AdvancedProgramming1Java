import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private
    TextField usernameTextField;
    @FXML
    private
    PasswordField passwordField;
    @FXML
    private
    Text messageText;
    @FXML
    protected void signIn() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (username.equals("roi") && password.equals("123456")) {
            messageText.setText("You are approved!");
            messageText.setFill(Color.BLUE);
        } else {
            messageText.setText("Invalid username or password");
            messageText.setFill(Color.RED);
        }
    }

    @FXML
    protected void settingsButton() {
    }

    @FXML
    void exitWindow() {
        this.exitWindow();
    }
}