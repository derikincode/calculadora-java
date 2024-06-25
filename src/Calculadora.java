import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    private final CalculadoraBotao[] numberButtons = new CalculadoraBotao[10];
    private final CalculadoraBotao[] functionButtons = new CalculadoraBotao[9];
    private final CalculadoraBotao addButton, subButton, mulButton, divButton;
    private final CalculadoraBotao decButton, equButton, delButton, clrButton, negButton;
    private final JTextField textfield;
    private String expression = "";
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private final CalculadoraOperacao operacao;

    public Calculadora() {
        operacao = new CalculadoraOperacao();

        setTitle("Calculadora");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(new Font("Arial", Font.PLAIN, 36));
        textfield.setEditable(false);

        addButton = new CalculadoraBotao("+");
        subButton = new CalculadoraBotao("-");
        mulButton = new CalculadoraBotao("*");
        divButton = new CalculadoraBotao("/");
        decButton = new CalculadoraBotao(".");
        equButton = new CalculadoraBotao("=");
        delButton = new CalculadoraBotao("Del");
        clrButton = new CalculadoraBotao("Clr");
        negButton = new CalculadoraBotao("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (CalculadoraBotao button : functionButtons) {
            button.addActionListener(this);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new CalculadoraBotao(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        add(panel);
        add(textfield);
        add(negButton).setBounds(50, 430, 100, 50);
        add(delButton).setBounds(150, 430, 100, 50);
        add(clrButton).setBounds(250, 430, 100, 50);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculadora();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                expression += numberButtons[i].getValor();
                textfield.setText(expression);
            }
        }
        if (e.getSource() == decButton) {
            expression += ".";
            textfield.setText(expression);
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            expression += " + ";
            textfield.setText(expression);
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            expression += " - ";
            textfield.setText(expression);
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            expression += " * ";
            textfield.setText(expression);
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            expression += " / ";
            textfield.setText(expression);
        }
        if (e.getSource() == equButton) {
            String[] parts = expression.split(" ");
            if (parts.length == 3) {
                num2 = Double.parseDouble(parts[2]);
                result = operacao.realizarOperacao(num1, num2, operator);
                expression += " = " + result;
                textfield.setText(expression);
            }
        }
        if (e.getSource() == clrButton) {
            expression = "";
            textfield.setText("");
        }
        if (e.getSource() == delButton) {
            if (expression.length() > 0) {
                expression = expression.substring(0, expression.length() - 1);
                textfield.setText(expression);
            }
        }
        if (e.getSource() == negButton) {
            if (textfield.getText().length() > 0) {
                double temp = Double.parseDouble(textfield.getText());
                temp *= -1;
                expression = String.valueOf(temp);
                textfield.setText(expression);
            }
        }
    }
}
