import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;

public class TipCalculatorController {

    @FXML
    private TextField billAmountField;

    @FXML
    private TextField tipPercentageField;

    @FXML
    private Label tipAmountLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private void calculateTip() {
        try {
            String billText = billAmountField.getText().trim();
            String tipText = tipPercentageField.getText().trim();

            if (billText.isEmpty() || tipText.isEmpty()) {
                errorLabel.setText("Error: Please enter both values.");
                tipAmountLabel.setText("Tip Amount:");
                return;
            }

            double billAmount = Double.parseDouble(billText);
            double tipPercentage = Double.parseDouble(tipText);

            if (!Double.isFinite(billAmount)
                    || !Double.isFinite(tipPercentage)
                    || billAmount < 0
                    || tipPercentage < 0) {
                errorLabel.setText(
                        "Error: Enter valid non-negative numbers."
                );
                tipAmountLabel.setText("Tip Amount:");
                return;
            }

            double tipAmount = billAmount * tipPercentage / 100.0;

            tipAmountLabel.setText(
                    String.format(Locale.US, "Tip Amount: $%.2f",
                            tipAmount)
            );

            errorLabel.setText("");

        } catch (NumberFormatException e) {
            tipAmountLabel.setText("Tip Amount:");
            errorLabel.setText(
                    "Error: Invalid input. Please enter numbers."
            );
        }
    }

    @FXML
    private void reset() {
        billAmountField.clear();
        tipPercentageField.clear();
        tipAmountLabel.setText("Tip Amount:");
        errorLabel.setText("");
    }
}
