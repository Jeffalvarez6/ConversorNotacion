package ejercicios.notacion;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConversorNotacion {

    public String infijaAPostfija(String expresion) {
        StringBuilder salida = new StringBuilder();
        Deque<Character> pila = new ArrayDeque<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (Character.isLetterOrDigit(c)) {
                salida.append(c).append(" ");
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    salida.append(pila.pop()).append(" ");
                }
                if (!pila.isEmpty() && pila.peek() == '(') {
                    pila.pop(); // Retirar '(' sin agregar a salida
                }
            } else if (esOperador(c)) {
                while (!pila.isEmpty() && pila.peek() != '(' &&
                        obtenerPrecedencia(pila.peek()) >= obtenerPrecedencia(c)) {
                    salida.append(pila.pop()).append(" ");
                }
                pila.push(c);
            }
        }

        while (!pila.isEmpty()) {
            salida.append(pila.pop()).append(" ");
        }

        return salida.toString().trim();
    }

    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private int obtenerPrecedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
}