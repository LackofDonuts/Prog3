package prog3.uppg1_Nordstrom_40880_Nordman_40867;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Dannyy on 6.3.2017.
 */
public class Uppg1Model {
    /*
    Det enda denhär metoden atm gör är fetchar csv filen från urlen... Antar att model-delen av programmet egentligen
    borde bestå av snäppet mera logik/data etc
     */

    Uppg1Model() {

    }

    public String[] fixDateInput(String in) throws NumberFormatException {      //Ta -1 från den inmatade månaden
        String[] temp = null;
        temp = in.split("\\.");
        int date = Integer.parseInt(temp[1]);
        date -= 1;
        temp[1] = Integer.toString(date);
        return temp;
    }

    public ArrayList<String> downloadCsv(String ticker, String startDateIn, String endDateIn) throws IOException {
        String[] startDate = fixDateInput(startDateIn);
        String[] endDate = fixDateInput(endDateIn);

        URL csvUrl = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker + "&a=" + startDate[1] + "&b=" + startDate[0]
                + "&c=" + startDate[2] + "&d=" + endDate[1] + "&e=" + endDate[0] + "&f=" + endDate[2] + "&g=d&ignore=.csv");

        InputStreamReader csvFile = new InputStreamReader(csvUrl.openStream());
        BufferedReader buf = new BufferedReader (csvFile);
        ArrayList<String> csvList = new ArrayList<>();

        String line;
        while( (line = buf.readLine()) != null){
            csvList.add(line);
        }
        buf.close();

        return csvList;
    }

}
