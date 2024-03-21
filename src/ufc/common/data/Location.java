package ufc.common.data;

public class Location implements Cloneable {
        public int Idx;
        public int Line;
        public int Col;
        public String Txt;

        public Location(int idx, int line, int col, String txt) {
            this.Idx = idx;
            this.Line = line;
            this.Col = col;
            this.Txt = txt;
        }
        public Location Advance() {
            return Advance(null);
        }
        public Location Advance(Character _currentChar) {
            this.Idx += 1;
            this.Col += 1;
            if (_currentChar != null)
                if (_currentChar == '\n') {
                    this.Line += 1;
                    this.Col = 0;
                }
            return this;
        }
        public Location Copy() {
            return new Location(Idx, Line, Col, Txt);
        }
        
        public Object Clone() {
            return this.Copy();
        }
        
        @Override
        public String toString() {
            return "[Idx: "+Idx+", Line: "+Line+", Col: "+Col+", Txt: "+ Txt+"]";
        }
    }

