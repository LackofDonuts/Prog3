package prog3.uppg1_Nordstrom_40880_Nordman_40867;

/**
 * Created by Dannyy on 6.3.2017.
 */
public class Uppg1Main {

    public static void main(String[] args) {

        //OBS, grafen har ibland en bugg som gör att man måste trycka Do Query pånytt för att visa grafen.

        Uppg1Model model = new Uppg1Model();
        Uppg1View view = new Uppg1View(model);
        Uppg1Controller controller = new Uppg1Controller(view, model);
    }

}
