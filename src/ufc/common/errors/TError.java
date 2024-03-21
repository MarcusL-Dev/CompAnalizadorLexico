package ufc.common.errors;

import ufc.common.data.Location;
import ufc.common.helpers.Util;

@SuppressWarnings("serial")
public class TError extends Exception {
    public Location LocIni = null;
    public Location LocEnd = null;
    public String Kind;
    public String Info;

    public TError(Location _locIni, Location _locEnd, String _kind, String _info) {
        this.LocIni = _locIni;
        this.LocEnd = _locEnd;
        this.Kind = _kind;
        this.Info = _info;
    }
    @Override
    public String toString() {
	    String ret  = this.Kind + ": " + this.Info+"\n";
	    ret += "On "+" line "+(this.LocIni.Line + 1);
        ret += "\n\n" + Util.StringsLocation(this.LocIni.Txt, this.LocIni, this.LocEnd);
	    return ret;
    }
}
