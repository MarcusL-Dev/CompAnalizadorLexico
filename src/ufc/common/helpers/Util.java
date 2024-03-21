package ufc.common.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ufc.common.data.Location;

public class Util {
    public static boolean em(Character c, String str) {
	    for (char x : str.toCharArray()) {
		    if(x==c) return true;
	    }
	    return false;
    }
    public static boolean em(String s, String[] strs) {
	    for (String x : strs) {
		    if(x!=null && x.equals(s)) return true;
	    }
	    return false;
    }
    public static boolean em(String k, String v, Tuple2<String, String>[] tps) {
        for (Tuple2<String, String> t : tps) {
            if (t.Item1.equals(k) && t.Item2.equals(v)) return true;
        }
        return false;
    }

    public static String StringsLocation(String text, Location locIni, Location locEnd) {
        String result = "";
        //Calcula indices
        int idxIni = 0;
        try {
            idxIni = Math.max(text.lastIndexOf('\n', locIni.Idx), 0);
        } catch (Exception e) {
            idxIni = 0;
        }
        int idxEnd = text.indexOf('\n', idxIni + 1);

        if (idxEnd < 0) idxEnd = text.length();

        //Gera cada linha;
        int line_count = locEnd.Line - locIni.Line + 1;
        for (int i = 0; i < line_count; i++) {
            //# Calculate line columns;
            String line = text.substring(idxIni, idxEnd - idxIni);
            int colIni = i == 0 ? locIni.Col : 0;

            int colEnd = i == line_count - 1 ? locEnd.Col : line.length() - 1;

            //Anexa para resultado;
            result += line + '\n';
            String space = "", space2="";
            for (int k = 0; k < colIni; k++) space = space + ' ';
            for (int k = 0; k < (colEnd - colIni); k++) space2 = space2 + '^';
            result += space + space2;

            //ReCalcula indices
            idxIni = idxEnd;

            try {
                idxEnd = text.indexOf('\n', idxIni + 1);
            } catch (Exception e) {
                if (idxEnd < 0) idxEnd = text.length();
            }
        }
        return result.replace("\t", "");
    }
    public static String shell(String prompt) {
        System.out.print(prompt+" ");
        String msn = "Warn: shell function can't read input keyboard";
		try {
			msn = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return msn;
    }
}
