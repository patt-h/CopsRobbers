import javax.imageio.ImageIO;
import java.io.IOException;

public class Piechota extends Policjant {
    Mapa map;

    /**
     * Ustalenie tekstury i szybkosci piechoty
     * @param map - mapa
     */
    public Piechota(Mapa map) {
        this.map = map;
        szybkosc = 1;

        try {
            tekstura = ImageIO.read(getClass().getResourceAsStream("cop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda wyliczajaca pierwsze polozenie
     */
    public void Polozenie() {
        for (int i = 0; i < liczebnosc; i++) {
            map.piechotaList[i] = new Piechota(map);
            map.piechotaList[i].policjantX = (random.nextInt(map.kolumny-1)) * map.tileSize;
            map.piechotaList[i].policjantY = (random.nextInt(map.rzedy-1)) * map.tileSize;
        }
    }
}