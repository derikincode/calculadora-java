public class CalculadoraOperacao {
    public double realizarOperacao(double num1, double num2, char operador) {
        return switch (operador) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> 0;
        };
    }
}
