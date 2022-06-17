import javax.imageio.ImageIO;
import java.io.IOException;

public class RowerowyZlodziej extends Zlodziej {
    Mapa map;

    /**
     * Ustalenie tekstury i szybkosci rowerowego zlodzieja
     * @param map - mapa
     */
    public RowerowyZlodziej(Mapa map) {
        this.map = map;
        szybkosc = 2;

        try {
            tekstura = ImageIO.read(getClass().getResourceAsStream("zlodziej_rowerowy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda wyliczajaca pierwsze polozenie
     */
    public void Polozenie() {
        for (int i = 0; i < liczebnosc; i++) {
            map.rowerowyZlodziejList[i] = new RowerowyZlodziej(map);
            map.rowerowyZlodziejList[i].zlodziejX = (random.nextInt(map.kolumny-1)) * map.tileSize;
            map.rowerowyZlodziejList[i].zlodziejY = (random.nextInt(map.rzedy-1)) * map.tileSize;
        }
    }
    /**
     * Metoda ucieczki zlodzieja
     */
    public void ucieczka() {
        try {
            tekstura = ImageIO.read(getClass().getResourceAsStream("robber.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
