package ufc.common.errors;

import ufc.common.data.Location;

@SuppressWarnings("serial")
public class TCharError extends TError {
    public TCharError(Location _locIni, Location _locEnd, String _kind, String _info) {
        super(_locIni, _locEnd, _kind, _info);
    }
}
