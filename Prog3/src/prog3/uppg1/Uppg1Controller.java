package prog3.uppg1_Nordstrom_40880_Nordman_40867;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dannyy on 6.3.2017.
 */
public class Uppg1Controller {
    private Uppg1View view;
    private Uppg1Model model;
    String currency, ticker1, ticker2, start, end, outText;
    ArrayList<StockData> tickers;

    public Uppg1Controller(Uppg1View view, Uppg1Model model) {
        this.model = model;
        this.view = view;
        this.view.addListener(new QueryListener());
    }

    class QueryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                start();
                System.out.println("Success!");
            } catch (Exception ex) {
                ex.printStackTrace();
                view.displayErrorMessage(ex.toString());
            }
        }
    }

    void start() throws Exception {
        tickers = new ArrayList<>();
        ticker1 = view.getTicker1();
        ticker2 = view.getTicker2();
        currency = view.getCurrency();
        start = view.getStartDate();
        end = view.getEndDate();
        outText = "";

        makeStockObject(ticker1);
        makeStockObject(ticker2);

        try {                           //Inne i try blocket bestäms det om man skall använda 2st tickers eller 1
            if (tickers.size() > 1) {
                view.setGraph(tickers.get(0).currencyList, tickers.get(1).currencyList);
                for (int i = 0; i < tickers.get(0).finalList.size(); i++) {
                    outText += tickers.get(0).finalList.get(i) + " " + currency + " --- " + tickers.get(1).finalList.get(i) + " " + currency + "\n";
                }
            } else {
                view.setGraph(tickers.get(0).currencyList);
                for (int i = 0; i < tickers.get(0).finalList.size(); i++) {
                    outText += tickers.get(0).finalList.get(i) + " " + currency + "\n";
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            outText += "Ingen info hittad";
        }
        view.setInfoArea(outText);
    }

    void makeStockObject(String ticker) throws IOException {        //Laga ett objekt om det finns någonting i ticker-fältet
        if (ticker.length() > 0) {
            try {
                StockData temp = new StockData(model.downloadCsv(ticker, start, end), ticker);
                convertSekEur(temp);
                tickers.add(temp);
            } catch (FileNotFoundException e) {
                outText += ticker + " är ingen giltig ticker." + "\n";
            }
        }
    }

    void convertSekEur(StockData in) throws IOException {       //Kollar vad som är valt och sätter finalList i StockData till den slutgiltiga listan som kan outputtas
        if (view.getCurrency().equals("USD")) {
            in.setFinalList();
            return;
        } else if (view.getCurrency().equals("EUR")) {
            in.setCurrencyCsv(model.downloadCsv("EUR=X", start, end));
            in.convertFinalList();
        } else if (view.getCurrency().equals("SEK")) {
            in.setCurrencyCsv(model.downloadCsv("SEK=X", start, end));
            in.convertFinalList();
        }
    }
}
