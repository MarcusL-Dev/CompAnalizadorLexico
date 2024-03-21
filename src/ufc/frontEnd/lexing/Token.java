package ufc.frontEnd.lexing;

import ufc.common.data.Location;
import ufc.common.data.Node;
import ufc.common.errors.TError;

public class Token extends Node {
    public String Type;
    public String Value;

    public Token(String _type){
        this(_type, null, null, null);
    }

    public Token(String _type, String _value){
        this(_type, _value, null, null);
    }

    public Token(String _type, String _value, Location _locIni){
        this(_type, _value, _locIni, null);
    }

    public Token(String _type, String _value, Location _locIni, Location _locEnd, TError _error){
        this(_type, _value, _locIni, _locEnd);
        this.Error = _error;
    }

    public Token(String _type, String _value, Location _locIni, Location _locEnd){
        this.Type = _type;
        this.Value = _value;

        if (_locIni != null) {
            this.LocIni = _locIni.Copy();
            this.LocEnd = _locIni.Copy();
            this.LocEnd.Advance();
        }
        if (_locEnd != null) {
            this.LocEnd = _locEnd.Copy();
        }
    }

    public boolean matches(String _type, String _value){
        return this.Type.equals(_type) && this.Value.equals(_value);
    }

    @Override
    public String toString(){
        if (this.Value != null)
            return "" + this.Type + ":" + this.Value + "";
        return this.Type;
    }

}
