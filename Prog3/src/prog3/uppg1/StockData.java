package prog3.uppg1_Nordstrom_40880_Nordman_40867;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by d on 8.3.2017.
 */
public class StockData {
    /*
    Fixade en till klass som lagrar arrayer och data1 så man inte behöver clutter up model eller controllern (dunno if this is necessary though)
     */
    String tickName;
    private ArrayList<String> currencyCsv = new ArrayList<>();
    private ArrayList<String> stockCsv = new ArrayList<>();
    ArrayList<String> finalList = new ArrayList<>();
    ArrayList<Double> currencyList = new ArrayList<>();

    StockData() {
        currencyCsv = null;
        stockCsv = null;
    }

    StockData(ArrayList<String> tick, String name) {
        stockCsv = tick;
        tickName = name;
    }

    public void setCurrencyCsv(ArrayList<String> list) {
        currencyCsv = list;
    }


    public void convertFinalList() {            //Om sek eller eur
        DecimalFormat df = new DecimalFormat(".##");
        for (int i = 1; i < stockCsv.size(); i++) {
            String[] c = currencyCsv.get(i).split(",");
            String[] s = stockCsv.get(i).split(",");
            Double converted = Double.parseDouble(c[4]) * Double.parseDouble(s[4]);
            currencyList.add(converted);
            finalList.add(c[0] + ": " + tickName + ": " + df.format(converted));
        }
    }

    public void setFinalList() {            //Om usd
        DecimalFormat df = new DecimalFormat(".##");
        for (int i = 1; i < stockCsv.size(); i++) {
            String[] s = stockCsv.get(i).split(",");
            currencyList.add(Double.parseDouble(s[4]));
            finalList.add(s[0] + ": " + tickName + ": " + df.format(Double.parseDouble(s[4])));
        }
    }
}
