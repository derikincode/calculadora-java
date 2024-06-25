import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calculadora extends JFrame implements ActionListener {
    private final CalculadoraBotao[] numberButtons = new CalculadoraBotao[10];
    private final CalculadoraBotao[] functionButtons = new CalculadoraBotao[10];
    private final CalculadoraBotao addButton, subButton, mulButton, divButton;
    private final CalculadoraBotao decButton, equButton, delButton, clrButton, percButton, negButton;
    private final JTextField textfield;
    private String expression = "0"; // Inicializa com "0"
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private final CalculadoraOperacao operacao;

    public Calculadora() {
        operacao = new CalculadoraOperacao();

        setTitle("Calculadora by Derik");
        setSize(400, 550); // Aumenta um pouco o tamanho da janela para dar mais espaço
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Adiciona um espaçamento entre os componentes

        textfield = new JTextField(expression);
        textfield.setEditable(false);
        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.WHITE);
        textfield.setFont(new Font("Arial", Font.PLAIN, 50));
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona margens ao campo de texto

        // Inicializando os botões
        addButton = new CalculadoraBotao("+", true, false);
        subButton = new CalculadoraBotao("-", true, false);
        mulButton = new CalculadoraBotao("x", true, false);
        divButton = new CalculadoraBotao("÷", true, false);
        decButton = new CalculadoraBotao(".", false, false);
        equButton = new CalculadoraBotao("=", false, true);
        delButton = new CalculadoraBotao("C", true, false);
        clrButton = new CalculadoraBotao("AC", true, false);
        percButton = new CalculadoraBotao("%", true, false);
        negButton = new CalculadoraBotao("(-)", true, false);

        // Adicionando botões à matriz de funções
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = percButton;
        functionButtons[9] = negButton;

        // Adicionando ActionListeners aos botões de função
        for (CalculadoraBotao button : functionButtons) {
            if (button != null) {
                button.addActionListener(this);
                addHoverEffect(button);
            }
        }

        // Inicializando os botões numéricos
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new CalculadoraBotao(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            addHoverEffect(numberButtons[i]);
        }

        // Painel para os botões
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10)); // Ajusta o espaçamento entre os botões
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona margens ao painel

        // Adicionando botões ao painel
        panel.add(clrButton);
        panel.add(delButton);
        panel.add(percButton);
        panel.add(divButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(negButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);

        // Adicionando componentes ao frame
        add(textfield, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // Configurando o fundo do frame como preto
        getContentPane().setBackground(Color.BLACK);

        // Bloquear a maximização da tela
        setResizable(false);

        setVisible(true);
    }

    private void addHoverEffect(CalculadoraBotao button) {
        button.addMouseListener(new MouseAdapter() {
            Color originalColor = button.getBackground();

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(Color.DARK_GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(Color.GRAY);
            }
        });
    }

    public static void main(String[] args) {
        new Calculadora();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (expression.equals("0")) {
                    expression = ""; // Remove o zero inicial se houver um número digitado
                }
                expression += numberButtons[i].getValor();
                textfield.setText(expression);
            }
        }
        if (e.getSource() == decButton) {
            if (!expression.contains(".")) { // Evita adicionar múltiplos pontos decimais
                expression += ".";
                textfield.setText(expression);
            }
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(expression);
            operator = '+';
            expression = "";
            textfield.setText(expression);
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(expression);
            operator = '-';
            expression = "";
            textfield.setText(expression);
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(expression);
            operator = '*';
            expression = "";
            textfield.setText(expression);
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(expression);
            operator = '/';
            expression = "";
            textfield.setText(expression);
        }
        if (e.getSource() == percButton) {
            double temp = Double.parseDouble(expression);
            result = temp / 100;
            expression = String.valueOf(result);
            textfield.setText(expression);
        }
        if (e.getSource() == negButton) {
            if (!expression.isEmpty() && !expression.equals("0")) {
                double temp = Double.parseDouble(expression);
                temp = temp * -1;
                expression = String.valueOf(temp);
                textfield.setText(expression);
            }
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(expression);
            result = operacao.realizarOperacao(num1, num2, operator);
            expression = String.valueOf(result);
            textfield.setText(expression);
        }
        if (e.getSource() == clrButton) {
            expression = "0"; // Reinicia para "0"
            textfield.setText(expression);
        }
        if (e.getSource() == delButton) {
            if (!expression.equals("0") && expression.length() > 0) {
                expression = expression.substring(0, expression.length() - 1);
                if (expression.isEmpty()) {
                    expression = "0";
                }
                textfield.setText(expression);
            }
        }
    }
}
