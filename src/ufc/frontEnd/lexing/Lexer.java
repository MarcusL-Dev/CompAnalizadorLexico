package ufc.frontEnd.lexing;

import java.util.ArrayList;
import java.util.List;

import ufc.common.data.Consts;
import ufc.common.data.Location;
import ufc.common.errors.TCharError;
import ufc.common.errors.TError;
import ufc.common.helpers.Util;

public class Lexer {
	private char[] Txt;
	private Location Loc;
	private Character CurrentChar;

	public Lexer(String text) {
		this.Txt = text.toCharArray();
		this.Loc = new Location(-1, 0, -1, text);
		this.CurrentChar = null;
		this.Advance();
	}

	private void Advance(){
		this.Loc.Advance(this.CurrentChar);
		this.CurrentChar = Loc.Idx < Txt.length ? Txt[Loc.Idx] : null;
	}

	public LexerOut MakeTokens() {
		List<Token> tokens = new ArrayList<Token>();

		while (this.CurrentChar != null) {
			if (Util.em(this.CurrentChar, " \t")) {
				this.Advance();
			}else if (this.CurrentChar == '#'){
				this.SkipComment();
			}else if (Util.em(this.CurrentChar, ";\n\r\n")) {
				tokens.add(new Token(Consts.NEWLINE, null, this.Loc));
				this.Advance();
			}else if (Util.em(this.CurrentChar, Consts.DIGITS)) {
				tokens.add(this.MakeNumber());
			}else if (Util.em(this.CurrentChar, Consts.LETTERS)) {
				tokens.add(this.MakeIdentifier());
			}else if (this.CurrentChar == '"') {
				tokens.add(this.MakeString());
			}else if (this.CurrentChar == '+') {
				tokens.add(new Token(Consts.PLUS, null, this.Loc));
				this.Advance();
			}else if (this.CurrentChar == '-') {
				tokens.add(MakeMinusOrArrow());
			}else if (this.CurrentChar == '*') {
				tokens.add(new Token(Consts.MUL, null, this.Loc));
				this.Advance();
			}else if (this.CurrentChar == '/') {
				tokens.add(new Token(Consts.DIV, null, this.Loc));
				Advance();
			}else if (this.CurrentChar == '^') {
				tokens.add(new Token(Consts.POW, null, this.Loc));
				this.Advance();
			}else if (this.CurrentChar == '(') {
				tokens.add(new Token(Consts.LPAR, null, this.Loc));
				this.Advance();
			}else if (this.CurrentChar == ')') {
				tokens.add(new Token(Consts.RPAR, null, this.Loc));
				this.Advance();
			}else if (this.CurrentChar == '[') {
				tokens.add(new Token(Consts.RSQUARE, null, this.Loc));
				this.Advance();
			}else if (this.CurrentChar == ']') {
				tokens.add(new Token(Consts.LSQUARE, null, this.Loc));
				this.Advance();
			}else if (this.CurrentChar == '!') {
				Token token = this.MakeNotEquals();
				if (token.Error != null) return new LexerOut(new ArrayList<Token>(), token.Error);
				tokens.add(token);
			}else if (this.CurrentChar == '=') {
				tokens.add(this.MakeEquals());
			}else if (this.CurrentChar == '<') {
				tokens.add(this.MakeLessThan());
			}else if (this.CurrentChar == '>') {
				tokens.add(this.MakeGreaterThan());
			}else if (this.CurrentChar == ',') {
				tokens.add(new Token(Consts.COMMA, null, this.Loc));
				this.Advance();
			}else if (this.CurrentChar == 65279) { this.Advance(); }
			else { 
				Location locIni = this.Loc.Copy();
				Character _char = this.CurrentChar;
				this.Advance();
				TError erro = new TCharError(locIni, this.Loc, "erro lexico: ilegal Character", "'" + _char + "'");
				return new LexerOut(new ArrayList<Token>(), erro);
			}
		}
		tokens.add(new Token(Consts.EOF, null, this.Loc, null));
		return new LexerOut(tokens, null);
	}

	private Token MakeMinusOrArrow(){
		String tokType = Consts.MINUS;
		Location locIni = this.Loc.Copy();
		this.Advance();

		if (this.CurrentChar == '>') {
			this.Advance();
			tokType = Consts.ARROW;
		}
		return new Token(tokType, null, locIni, this.Loc);
	}

	private Token MakeNotEquals(){
		Location locIni = this.Loc.Copy();
		this.Advance();

		if (this.CurrentChar == '=') {
			this.Advance();
			return new Token(Consts.NE, null, locIni, this.Loc);
		}

		return new Token(null, null, null, null, new TCharError(locIni, this.Loc, "Expected Character", "'=' (after '!')"));
	}

	private Token MakeEquals(){
		String tokType = Consts.EQ;
		Location locIni = this.Loc.Copy();
		this.Advance();

		if (this.CurrentChar == '=') {
			this.Advance();
			tokType = Consts.EQ;
		}

		return new Token(tokType, null, locIni, this.Loc);
	}

	private Token MakeLessThan(){
		String tokType = Consts.LT;
		Location locIni = this.Loc.Copy();

		if (this.CurrentChar == '=') {
			this.Advance();
			tokType = Consts.LT;
		}

		return new Token(tokType, null, locIni, this.Loc);
	}

	private Token MakeGreaterThan(){
		String tokType = Consts.GT;
		Location locIni = this.Loc.Copy();
		this.Advance();

		if (this.CurrentChar == '=') {
			this.Advance();
			tokType = Consts.GT;
		}

		return new Token(tokType, null, locIni, this.Loc);
	}

	private Token MakeIdentifier(){
		String idStr = "";
		Location locIni = this.Loc.Copy();

		while (this.CurrentChar != null && Util.em(this.CurrentChar, Consts.LETTERS_DIGITS + "_")) {
			idStr += this.CurrentChar;
			this.Advance();
		}

		String tokType = Consts.IDENTIFIER;
		for (String keyword : Consts.KEYWORDS) {
			if (idStr.equalsIgnoreCase(keyword)) {
				tokType = Consts.KEYWORD;
				break;
			}
		} 

		return new Token(tokType, idStr, locIni, this.Loc);
	}

	private Token MakeNumber(){
		String numStr = "";
		int dotCount = 0;
		Location locIni = this.Loc.Copy();

		while (this.CurrentChar != null && Util.em(this.CurrentChar, Consts.DIGITS + ".")) {
			if (Util.em(this.CurrentChar, ".")) {
				if (dotCount == 1) break;
				dotCount += 1;
				numStr += ".";
			}else{
				numStr += this.CurrentChar;
			}
			this.Advance();
		}
		if (dotCount == 0) {
			return new Token(Consts.INT, numStr, locIni, this.Loc);
		}else{
			return new Token(Consts.FLOAT, numStr, this.Loc);
		}
	}

	private Token MakeString(){
		String str = "";
		Location locIni = this.Loc.Copy();

		this.Advance();

		while (this.CurrentChar != null && Util.em(this.CurrentChar, Consts.LETTERS + Consts.DIGITS)) {
			str += this.CurrentChar;
			this.Advance();
		}

		return new Token(Consts.STRING, str, locIni, this.Loc);
	}

	private void SkipComment(){
		this.Advance();
		while (this.CurrentChar != '\n')
			this.Advance();
		this.Advance();
	}

}

