package calculatorproblem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * References:(1) https://stackoverflow.com/questions/2282728/java-replacelast
 *            (2) https://www.geeksforgeeks.org/java-swing-simple-calculator/
 */

public class SwingCalculator extends JFrame implements ActionListener {

    //Getting the screen dimension
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double height = screenSize.getHeight();
    private double width = screenSize.getWidth();

    //Setting the dimension for the frame
    private int windowsHeight = (int) (height / 2.2);
    private int windowsWidth = (int) (width / 3.2);
    private int fontSize = windowsHeight / 14;

    //Display text font
    private Font displayFont = new Font("Courier", Font.BOLD, (int) (fontSize / 1.1));

    //Font for buttons
    private Font otherFont = new Font("Arial", Font.BOLD, (int) (fontSize / 1.5));

    //Font for small display
    private Font smallFont = new Font("Arial", Font.PLAIN, (int) (fontSize / 2.1));

    //Font for menus
    private Font menuFont = new Font("Arial", Font.BOLD, fontSize / 2);

    //Small text display
    private JTextField textField = new JTextField();

    //Big text display
    private JTextField displayText = new JTextField();

    //Use to track the number of continuously pressed operator-buttons
    private int operatorCount = 0;

    //Keep track of current operator
    private String operator = "";

    //Keep track of first operand
    private String operand1 = "";

    //Use to set the displayText to a number pressed AFTER entering an operator
    private String tempOperator = "";

    //Keep track of second operand
    private String operand2 = "";

    //Constructor()
    public SwingCalculator() throws HeadlessException {
        GridLayout panelLayout = new GridLayout(1, 5);
        GridLayout frameLayout = new GridLayout(6, 2);
        setTitle("Swing Calculator");
        setSize(windowsWidth, windowsHeight);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel(); //Number Buttons and Function Buttons
        JPanel buttonPanel2 = new JPanel();
        JPanel buttonPanel3 = new JPanel();
        JPanel buttonPanel4 = new JPanel();

        JPanel clearButtons = new JPanel();
        JPanel buttonPanel5 = new JPanel(); //Backspace and clearButtons

        JPanel display = new JPanel();

        clearButtons.setLayout(new FlowLayout());


        displayText.setFont(displayFont);
        textField.setFont(smallFont);


        //Number buttons
        JButton one = new JButton("1");
        one.setFont(otherFont);
        one.addActionListener(this);

        JButton two = new JButton("2");
        two.setFont(otherFont);
        two.addActionListener(this);

        JButton three = new JButton("3");
        three.setFont(otherFont);
        three.addActionListener(this);

        JButton four = new JButton("4");
        four.addActionListener(this);
        four.setFont(otherFont);

        JButton five = new JButton("5");
        five.setFont(otherFont);
        five.addActionListener(this);

        JButton six = new JButton("6");
        six.setFont(otherFont);
        six.addActionListener(this);

        JButton seven = new JButton("7");
        seven.addActionListener(this);
        seven.setFont(otherFont);

        JButton eight = new JButton("8");
        eight.addActionListener(this);
        eight.setFont(otherFont);

        JButton nine = new JButton("9");
        nine.addActionListener(this);
        nine.setFont(otherFont);

        JButton zero = new JButton("0");
        zero.setFont(otherFont);
        zero.addActionListener(this);

        //Function buttons
        JButton divide = new JButton("/");
        divide.addActionListener(this);
        divide.setFont(otherFont);
        JButton multiply = new JButton("*");
        multiply.addActionListener(this);
        multiply.setFont(otherFont);
        JButton minus = new JButton("-");
        minus.addActionListener(this);
        minus.setFont(otherFont);
        JButton plus = new JButton("+");
        plus.addActionListener(this);
        plus.setFont(otherFont);
        JButton sqrt = new JButton("sqrt");
        sqrt.addActionListener(this);
        sqrt.setFont(otherFont);
        JButton sign = new JButton("+/-");
        sign.addActionListener(this);
        sign.setFont(otherFont);
        JButton decimal = new JButton(".");
        decimal.addActionListener(this);
        decimal.setFont(otherFont);
        JButton reciprocal = new JButton("1/x");
        reciprocal.addActionListener(this);
        reciprocal.setFont(otherFont);
        JButton equal = new JButton("=");
        equal.addActionListener(this);
        equal.setFont(otherFont);
        JButton percent = new JButton("%");
        percent.addActionListener(this);
        percent.setFont(otherFont);

        //clearing buttons
        JButton backspace = new JButton("Backspace");
        backspace.addActionListener(this);
        backspace.setFont(otherFont);
        JButton CE = new JButton("CE");
        JButton C = new JButton("C");

        //Adding the text fields to the display panel
        textField.setText("");
        textField.setEditable(false);
        displayText.setText("0");
        displayText.setEditable(false);
        display.setLayout(new BorderLayout());
        display.add(textField, BorderLayout.BEFORE_FIRST_LINE);
        display.add(displayText, BorderLayout.AFTER_LAST_LINE);


        //****Divide buttons into 5 layouts:****//

        //1
        buttonPanel5.setLayout(new BorderLayout());
        buttonPanel5.add(backspace, BorderLayout.WEST);
        buttonPanel5.add(clearButtons, BorderLayout.EAST);
        clearButtons.add(CE);
        C.setFont(otherFont);
        C.addActionListener(this);
        clearButtons.add(C);
        CE.setFont(otherFont);
        CE.addActionListener(this);

        //2
        buttonPanel4.setLayout(panelLayout);
        buttonPanel4.add(seven);
        buttonPanel4.add(eight);
        buttonPanel4.add(nine);
        buttonPanel4.add(divide);
        buttonPanel4.add(sqrt);

        //3
        buttonPanel3.setLayout(panelLayout);
        buttonPanel3.add(four);
        buttonPanel3.add(five);
        buttonPanel3.add(six);
        buttonPanel3.add(multiply);
        buttonPanel3.add(reciprocal);

        //4
        buttonPanel2.setLayout(panelLayout);
        buttonPanel2.add(one);
        buttonPanel2.add(two);
        buttonPanel2.add(three);
        buttonPanel2.add(minus);
        buttonPanel2.add(percent);

        //5
        buttonPanel.setLayout(panelLayout);
        buttonPanel.add(zero);
        buttonPanel.add(sign);
        buttonPanel.add(decimal);
        buttonPanel.add(plus);
        buttonPanel.add(equal);

        //Add layouts to the frame (from top to bottom)
        setLayout(frameLayout);
        add(display);
        add(buttonPanel5);
        add(buttonPanel4);
        add(buttonPanel3);
        add(buttonPanel2);
        add(buttonPanel);

        //****Java Menus****//

        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);

        // File
        JMenu file = new JMenu();
        file.setFont(menuFont);
        file.setText("File");
        jMenuBar.add(file);

        // Help
        JMenu help = new JMenu();
        help.setFont(menuFont);
        help.setText("Help");
        jMenuBar.add(help);

        //  JMenu Items
        JMenuItem closeItem = new JMenuItem();
        closeItem.setFont(menuFont);
        closeItem.setText("Exit");
        closeItem.addActionListener(this);
        file.add(closeItem);

        JMenuItem helpItem = new JMenuItem();
        helpItem.setFont(menuFont);
        helpItem.setText("Help");
        helpItem.addActionListener(this);
        help.add(helpItem);


        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toUpperCase()) {
            case "EXIT":
                System.exit(0);
                break;
            case "HELP":
                JLabel help = new JLabel("Just use this as a normal calculator");
                help.setFont(menuFont);
                JOptionPane.showMessageDialog(null, help, "Help", JOptionPane.INFORMATION_MESSAGE);
                break;

            //*****Handling Numbers******//
            case "1":
                acceptNumbers("1");
                break;
            case "2":
                acceptNumbers("2");
                break;
            case "3":
                acceptNumbers("3");
                break;
            case "4":
                acceptNumbers("4");
                break;
            case "5":
                acceptNumbers("5");
                break;
            case "6":
                acceptNumbers("6");
                break;
            case "7":
                acceptNumbers("7");
                break;
            case "8":
                acceptNumbers("8");
                break;
            case "9":
                acceptNumbers("9");
                break;
            case "0":
                if (!textField.getText().equals("0"))
                    acceptNumbers("0");
                break;

            //*****Handling Operations*****//

            case "+":
                acceptOperators("+");
                break;

            case "-":
                acceptOperators("-");
                break;

            case "*":
                acceptOperators("*");
                break;

            case "/":
                acceptOperators("/");
                break;

            case "=":
                operatorCount = 0;
                textField.setText("");
                result();
                break;

            case "C": //Handling C-Button
                displayText.setText("0");
                textField.setText("");
                operand1 = "";
                operand2 = "";
                operator = "";
                tempOperator = "";
                operatorCount = 0;
                break;

            case "CE": //Handling CE-Button
                if (displayText.getText().equals("Invalid input") || displayText.getText().equals("Cannot divide by zero")) {
                    textField.setText("");
                }
                displayText.setText("0");
                break;

            case "SQRT": //Handling Square Root of number
                tempOperator = "sqrt";
                try {
                    if (operand1.equals("")) {
                        try {
                            operand1 = displayText.getText();
                            double num = Double.parseDouble(operand1);
                            if (num < 0) {
                                throw new SquareRootNegativeNumber();
                            }
                            double square = Math.sqrt(num);

                            displayText.setText(Double.toString(square));
                            String symbol = "sqrt(" + operand1 + ")";
                            textField.setText(replaceLast(textField.getText(), operand1, symbol));
                        } catch (SquareRootNegativeNumber m) {
                            displayText.setText(m.getMessage());
                        }

                    } else {
                        try {
                            String temp = displayText.getText();
                            double num = Double.parseDouble(temp);

                            if (num < 0) {
                                throw new SquareRootNegativeNumber();
                            }
                            double square = Math.sqrt(num);
                            displayText.setText(Double.toString(square));
                            String symbol = "sqrt(" + temp + ")";
                            textField.setText(replaceLast(textField.getText(), temp, symbol));
                        } catch (SquareRootNegativeNumber m) {
                            displayText.setText(m.getMessage());
                        }
                    }
                } catch (NumberFormatException k) {
                    System.err.println(k.getMessage());
                }
                break;

            case "1/X": //Handling reciprocal of number
                tempOperator = "1/x";
                try {
                    if (operand1.equals("")) {
                        String temp = displayText.getText();
                        double num = Double.parseDouble(temp);
                        operand1 = Double.toString(num);

                        //Check if the number is an integer
                        if (!textField.getText().contains(operand1)) {
                            textField.setText(replaceLast(textField.getText(), temp, operand1));
                        }

                        double complement = 1.0 / (num);
                        displayText.setText(Double.toString(complement));
                        String symbol = "1/" + operand1;
                        if (!textField.getText().contains("1/")) {
                            textField.setText(replaceLast(textField.getText(), operand1, symbol));
                        } else {
                            textField.setText(replaceLast(textField.getText(), "1/", ""));
                        }
                    } else {
                        String temp1 = displayText.getText();
                        double num = Double.parseDouble(temp1);
                        String temp2 = Double.toString(num);

                        //Check if the number is an integer
                        if (!textField.getText().contains(temp2)) {
                            textField.setText(replaceLast(textField.getText(), temp1, temp2));
                        }

                        double complemennt = 1.0 / (num);
                        displayText.setText(Double.toString(complemennt));
                        String symbol = "1/" + temp2 + "";
                        if (!textField.getText().contains("1/")) {
                            textField.setText(replaceLast(textField.getText(), temp2, symbol));
                        } else {
                            textField.setText(replaceLast(textField.getText(), "1/", ""));
                        }
                    }
                } catch (NumberFormatException k) {
                    System.err.println(k.getMessage());
                }
                break;

            case "BACKSPACE": //Handling Backspace
                if (!displayText.getText().equals("Invalid input") && !displayText.getText().equals("Cannot divide by zero")) {
                    if (displayText.getText().length() > 0 && !displayText.getText().equals("0")) {

                        if (operatorCount == 0) {
                            displayText.setText("" + displayText.getText().substring(0, displayText.getText().length() - 1));
                            textField.setText("" + textField.getText().substring(0, textField.getText().length() - 1));
                        }
                    }
                }
                if (displayText.getText().isEmpty()) {
                    displayText.setText("0");
                }
                break;

            case ".": //Handling decimal point
                char point = '.';
                if (tempOperator.equals("")) {
                    if (countOccurrences(point, displayText.getText()) == 0) {
                        displayText.setText(displayText.getText() + ".");
                        if (displayText.getText().equals("0.") && textField.getText().equals("")) {
                            textField.setText(textField.getText() + displayText.getText());
                        } else {
                            textField.setText(textField.getText() + ".");
                        }
                    }
                } else if (!displayText.getText().equals("") && !tempOperator.equals("") && !displayText.getText().contains(".")) {
                    displayText.setText("0.");
                    textField.setText(textField.getText() + "0.");
                    operatorCount = 0;
                }
                break;

            case "+/-": //Handling negation
                try {
                    double original = Double.parseDouble(displayText.getText());
                    double sign = original * -1.0;
                    if (textField.getText().isEmpty()) {
                        textField.setText(displayText.getText());
                    }
                    if (original != 0) {
                        displayText.setText(Double.toString(sign));
                    }

                    if (!textField.getText().contains("(-1)")) {
                        textField.setText(textField.getText() + "(-1)");
                    } else {
                        textField.setText(replaceLast(textField.getText(), "(-1)", ""));
                    }

                } catch (NumberFormatException k) {
                    System.err.println(k.getMessage());
                }
                break;

            case "%": //Handling Percentage
                tempOperator = "%";
                try {
                    double num1 = Double.parseDouble(operand1);
                    double percentage = Double.parseDouble(displayText.getText());
                    double result = (num1 * percentage) / 100.0;
                    displayText.setText(Double.toString(result));
                    textField.setText(textField.getText() + "%");

                } catch (NumberFormatException k) {
                    System.err.println(k.getMessage());
                }
                break;
        }
    }

    //Do calculation
    public void result() {
        if (operatorCount == 0) {
            operand2 = displayText.getText();
            try {
                double a = Double.parseDouble(operand1);
                double b = Double.parseDouble(operand2);

                switch (operator) {
                    case "+":
                        Double result = a + b;
                        displayText.setText(Double.toString(result));
                        break;
                    case "-":
                        result = a - b;
                        displayText.setText(Double.toString(result));
                        break;
                    case "*":
                        result = a * b;
                        displayText.setText(Double.toString(result));
                        break;
                    case "/":
                        try {
                            result = a / b;
                            displayText.setText(Double.toString(result));
                            if (b == 0) {
                                throw new DivideByZeroException();
                            }
                        } catch (DivideByZeroException k) {
                            displayText.setText(k.getMessage());
                        }
                        break;
                }

            } catch (NumberFormatException k) {
                System.err.println(k.getMessage());
            }

            operator = "";
            operand1 = displayText.getText();
            operand2 = "";
        }
    }

    //Replacing a substring of a string
    public String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }

    //Count how many decimal point there are in the textField
    public int countOccurrences(char key, String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (key == s.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    //Handling numbers
    public void acceptNumbers(String num) {
        if ((displayText.getText().equals("0") || !tempOperator.equals("") || textField.getText().equals("")) && !displayText.getText().equals("0.")) {
            displayText.setText(num);
            if (tempOperator.equals("sqrt") || tempOperator.equals("1/x") || tempOperator.equals("%")) {
                textField.setText("");
            }
            tempOperator = "";
            operatorCount = 0;
        } else {
            displayText.setText(displayText.getText() + num);
        }
        textField.setText(textField.getText() + num);
    }

    //Handling operators
    public void acceptOperators(String op) {

        //If the displayText is equal to "Invalid input" or "Cannot divide by zero", don't do anything
        if (!displayText.getText().equals("Invalid input") && !displayText.getText().equals("Cannot divide by zero")) {
            if (textField.getText().equals("")) {
                textField.setText(displayText.getText());
            }

            result();
            operatorCount++;
            if (operatorCount == 1) {

                textField.setText(textField.getText() + " " + op + " ");
                tempOperator = op;
                operator = op;
                operand1 = displayText.getText();
            }
        }
    }

    //Main Function
    public static void main(String[] args) {
        new SwingCalculator();
    }
}