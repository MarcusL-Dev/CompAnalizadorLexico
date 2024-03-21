package ufc.common.data;

public final class Consts { // Classe estatica de constantes
    public static final String LETTERS_LOWER = "abcdefghijlmnopqrstuvxzwyk";
    public static final String LETTERS_UPPER = LETTERS_LOWER.toUpperCase();
    public static final String DIGITS        = "0123456789";
    public static final String LETTERS       = LETTERS_LOWER + LETTERS_UPPER;
    public static final String LETTERS_DIGITS = DIGITS + LETTERS;
    public static final String INT           = "INT",                // Tipo Inteiro
                                  FLOAT 	 = "FLOAT",              // Tipo Float
                                  STRING     = "STRING",             // Tipo String
                                  IDENTIFIER = "ID",                 // Tipagem de Identificador
                                  KEYWORD    = "KEYWORD",            // Tipagem de Palavra Chave
                                  PLUS       = "PLUS",               // Mais: +
		                          MINUS      = "MINUS",              // Menos: -
		                          MUL        = "MUL",                // Multiplicacao: *
		                          DIV        = "DIV",                // Divisao: /
                                  POW        = "POW",                // Potencia: ^
                                  EQ         = "EQ",                 // Igual: =
		                          LPAR       = "LPAR",               // Parenteses esquerdo: (
		                          RPAR       = "RPAR",               // Parenteses direito: )
                                  LSQUARE    = "LSQUARE",            // Colchetes esquerdo: [
                                  RSQUARE    = "RSQUARE",            // Colchetes direito: ]
                                  EE         = "EE",                 // Igual Igual: ==
                                  NE         = "NE",                 // Diferentes: !=
                                  LT         = "LT",                 // Menor: <
                                  GT         = "GT",                 // Maior: >
                                  LTE        = "LTE",                // Menor Igual: <=
                                  GTE        = "GTE",                // Maior Igual: >=
                                  COMMA      = "COMMA",              // Virgula: ,
                                  ARROW      = "ARROW",              // Flexa: ->
                                  NEWLINE    = "NEWLINE",            // Nova linha: /n
                                  EOF		 = "EOF";                // Final de arquivo
    public static final String[] KEYWORDS = new String[RID.SIZE_KEY];
    public static final String[] EMBEDDED = new String[RID.SIZE_EMB];
    static {
        KEYWORDS[RID.VAR]           = "VAR".toLowerCase();
        KEYWORDS[RID.AND]           = "AND".toLowerCase();
        KEYWORDS[RID.OR]            = "OR".toLowerCase();
        KEYWORDS[RID.NOT]           = "NOT".toLowerCase();
        KEYWORDS[RID.WHILE]         = "WHILE".toLowerCase();
        KEYWORDS[RID.DO]            = "DO".toLowerCase();
        KEYWORDS[RID.END]           = "END".toLowerCase();

        // TODO: Evaluate False, True, Null on Keywords
        EMBEDDED[RID.FALSE]         = "FALSE".toLowerCase();
        EMBEDDED[RID.TRUE]          = "TRUE".toLowerCase();
        EMBEDDED[RID.NULL]          = "NULL".toLowerCase();
    }
    public static class RID { // Reserved Identifier
        public static final int
            // Reserved Native Words
            VAR         = 0,
            AND         = 1,
            OR          = 2,
            NOT         = 3,
            WHILE       = 4,
            DO          = 5,
            END         = 6,
            // Reserved Native Symbols
            FALSE       = 0,
            TRUE        = 1,
            NULL        = 2;
        public static final int SIZE_KEY = 7;//17;
        public static final int SIZE_EMB = 3;//19;
    }    
}


