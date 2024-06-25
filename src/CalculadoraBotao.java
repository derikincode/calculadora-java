import javax.swing.JButton;

public class CalculadoraBotao extends JButton {
    private String valor;

    public CalculadoraBotao(String valor) {
        super(valor);
        this.valor = valor;
        this.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30));
        this.setFocusable(false);
    }

    public String getValor() {
        return valor;
    }
}
