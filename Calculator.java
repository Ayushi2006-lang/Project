import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField textField;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 40, 320, 40);
        add(textField);

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+"
        };

        int x = 30, y = 100;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton btn = new JButton(buttonLabels[i]);
            btn.setBounds(x, y, 70, 40);
            btn.addActionListener(this);
            add(btn);
            x += 80;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 60;
            }
        }

        JButton clear = new JButton("C");
        clear.setBounds(30, y, 320, 40);
        clear.addActionListener(e -> textField.setText(""));
        add(clear);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = ((JButton)e.getSource()).getText();

        if (input.charAt(0) >= '0' && input.charAt(0) <= '9' || input.equals(".")) {
            textField.setText(textField.getText() + input);
        } else if (input.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 == 0) {
                        textField.setText("Error: Divide by 0");
                        return;
                    } else {
                        result = num1 / num2;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
        } else {
            num1 = Double.parseDouble(textField.getText());
            operator = input.charAt(0);
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}