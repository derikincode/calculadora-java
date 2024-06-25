import javax.swing.JButton;
import java.awt.*;

public class CalculadoraBotao extends JButton {
    private String valor;
    private boolean textoLaranja; 
    private boolean backgroundLaranja; 

    public CalculadoraBotao(String valor) {
        this(valor, false, false); 
    }

    public CalculadoraBotao(String valor, boolean textoLaranja, boolean backgroundLaranja) {
        super(valor);
        this.valor = valor;
        this.textoLaranja = textoLaranja; 
        this.backgroundLaranja = backgroundLaranja; 
        
        // Configurações padrão do botão
        setBackground(new Color(0x242424)); 
        setForeground(Color.white); 
        if (textoLaranja) {
            setForeground(new Color(0xed8114)); 
        }
        if (backgroundLaranja) {
            setBackground(new Color(0xed8114));
        }
        setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30)); 
        setFocusable(false);
        setBorder(null); 
        setContentAreaFilled(false); 
    }

    public String getValor() {
        return valor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        
        if (textoLaranja) {
            g2.setColor(new Color(0xed8114));
        } else {
            g2.setColor(getForeground());
        }
        
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);
        
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(70, 70); // Define o tamanho preferido do botão
    }
}