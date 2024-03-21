package ufc.frontEnd.lexing;

import java.util.List;

import ufc.common.errors.TError;

public class LexerOut {
	public List<Token> Tokens;
    public TError Error;

	public LexerOut(List<Token> _tokens, TError _error){
		this.Tokens = _tokens;
		this.Error = _error;
	}

	@Override
	public String toString(){
		String sb = "[";
		if (Tokens != null) {
			var it = Tokens.iterator();
			if (it.hasNext()) 
				sb = sb + it.next().toString();
			while (it.hasNext()) {
				sb = sb + ", " + it.next().toString();
			}
		}
		sb = sb + "]\n";
		if (Error != null) {
			sb = sb + Error;
		}
		return sb;
	}
}
