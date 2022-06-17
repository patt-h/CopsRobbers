import javax.imageio.ImageIO;
import java.io.IOException;

public class DrobnyZlodziej extends Zlodziej {
    Mapa map;

    /**
     * Ustalenie tekstury i szybkosci drobnego zlodzieja
     * @param map - mapa
     */
    public DrobnyZlodziej(Mapa map) {
        this.map = map;
        szybkosc = 1;

        try {
            tekstura = ImageIO.read(getClass().getResourceAsStream("robber.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda wyliczajaca pierwsze polozenie
     */
    public void Polozenie() {
        for (int i = 0; i < liczebnosc; i++) {
            map.drobnyZlodziejList[i] = new DrobnyZlodziej(map);
            map.drobnyZlodziejList[i].zlodziejX = (random.nextInt(map.kolumny-1)) * map.tileSize;
            map.drobnyZlodziejList[i].zlodziejY = (random.nextInt(map.rzedy-1)) * map.tileSize;
        }
    }
}
