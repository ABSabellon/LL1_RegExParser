import ui.GUI;

import javax.swing.*;

public class MainWithoutGui {

    public static void main(String[] args) throws IOException {
        ExprLexer lexer = new ExprLexer(new InputStreamReader(System.in));
        ExprParser parser = new ExprParser(lexer);
        parser.expr();
        System.out.println("ACCEPTED");  // prints if no exception
    }
}
