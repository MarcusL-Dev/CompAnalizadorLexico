package ufc;

import ufc.common.helpers.Util;
import ufc.frontEnd.lexing.Lexer;
import ufc.frontEnd.lexing.LexerOut;

public class AppMain {
    public static String prompt = "ufcLang";
    public static LexerOut run(String text) { // Gerar Tokens
        Lexer lexer = new Lexer(text);
        LexerOut lr = lexer.MakeTokens();
        if (lr.Error != null)
            return new LexerOut(null, lr.Error);
        return lr;
    }
    public static void main(String[] args) {
        String line = Util.shell(prompt + ">").trim();
        while (line != ":q") {
            if (line != "") {
                LexerOut lr = run(line);
                if (lr.Error != null) {
                    System.out.println(lr);
                } else {
                    System.out.println(lr);
                    //TODO: Parser
                }
            }
            line = Util.shell(prompt + ">").trim();
        }
    }
}
