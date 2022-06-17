import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BankowyZlodziej extends Zlodziej {
    Mapa map;

    /**
     * Ustalenie tekstury i szybkosci bankowego zlodzieja
     * @param map - mapa
     */
    public BankowyZlodziej(Mapa map) {
        this.map = map;
        szybkosc = 4;

        try {
            tekstura = ImageIO.read(getClass().getResourceAsStream("zlodziej_bankowy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda wyliczajaca pierwsze polozenie
     */
    public void Polozenie() {
        for (int i = 0; i < liczebnosc; i++) {
            map.bankowyZlodziejList[i] = new BankowyZlodziej(map);
            map.bankowyZlodziejList[i].zlodziejX = (random.nextInt(map.kolumny-1)) * map.tileSize;
            map.bankowyZlodziejList[i].zlodziejY = (random.nextInt(map.rzedy-1)) * map.tileSize;
        }
    }
    /**
     * Metoda detekcji kolizji drogi
     * @param map - mapa
     */
    public void tylkoUlica(Mapa map) {
        this.map = map;
        zlodziejArea = new Rectangle(zlodziejX, zlodziejY, 64 ,64);

        if (zlodziejArea.intersects(map.tylkoUlica1) || zlodziejArea.intersects(map.tylkoUlica2) || zlodziejArea.intersects(map.tylkoUlica3)
                || zlodziejArea.intersects(map.tylkoUlica4) || zlodziejArea.intersects(map.tylkoUlica5) || zlodziejArea.intersects(map.tylkoUlica6)
                || zlodziejArea.intersects(map.tylkoUlica7) || zlodziejArea.intersects(map.tylkoUlica8) || zlodziejArea.intersects(map.tylkoUlica9)
                || zlodziejArea.intersects(map.tylkoUlica10) || zlodziejArea.intersects(map.tylkoUlica11) || zlodziejArea.intersects(map.tylkoUlica12)
                || zlodziejArea.intersects(map.tylkoUlica13) || zlodziejArea.intersects(map.tylkoUlica14) || zlodziejArea.intersects(map.tylkoUlica15)
                || zlodziejArea.intersects(map.tylkoUlica16) || zlodziejArea.intersects(map.tylkoUlica17) || zlodziejArea.intersects(map.tylkoUlica18)
                || zlodziejArea.intersects(map.tylkoUlica19) || zlodziejArea.intersects(map.tylkoUlica20) || zlodziejArea.intersects(map.tylkoUlica21)
                || zlodziejArea.intersects(map.tylkoUlica22) || zlodziejArea.intersects(map.tylkoUlica23) || zlodziejArea.intersects(map.tylkoUlica24)
                || zlodziejArea.intersects(map.tylkoUlica25) || zlodziejArea.intersects(map.tylkoUlica26) || zlodziejArea.intersects(map.tylkoUlica27)
                || zlodziejArea.intersects(map.tylkoUlica28) || zlodziejArea.intersects(map.tylkoUlica29) || zlodziejArea.intersects(map.tylkoUlica30)
                || zlodziejArea.intersects(map.tylkoUlica31) || zlodziejArea.intersects(map.tylkoUlica32) || zlodziejArea.intersects(map.tylkoUlica33)
                || zlodziejArea.intersects(map.tylkoUlica34) || zlodziejArea.intersects(map.tylkoUlica35) || zlodziejArea.intersects(map.tylkoUlica36)
                || zlodziejArea.intersects(map.tylkoUlica37) || zlodziejArea.intersects(map.tylkoUlica38) || zlodziejArea.intersects(map.tylkoUlica39)
                || zlodziejArea.intersects(map.tylkoUlica40) || zlodziejArea.intersects(map.tylkoUlica41) || zlodziejArea.intersects(map.tylkoUlica42)
                || zlodziejArea.intersects(map.tylkoUlica43) || zlodziejArea.intersects(map.tylkoUlica44) || zlodziejArea.intersects(map.tylkoUlica45)
                || zlodziejArea.intersects(map.tylkoUlica46) || zlodziejArea.intersects(map.tylkoUlica47) || zlodziejArea.intersects(map.tylkoUlica48)
                || zlodziejArea.intersects(map.tylkoUlica49) || zlodziejArea.intersects(map.tylkoUlica50) || zlodziejArea.intersects(map.tylkoUlica51)
                || zlodziejArea.intersects(map.tylkoUlica52) || zlodziejArea.intersects(map.tylkoUlica53) || zlodziejArea.intersects(map.tylkoUlica54)
                || zlodziejArea.intersects(map.tylkoUlica55) || zlodziejArea.intersects(map.tylkoUlica56) || zlodziejArea.intersects(map.tylkoUlica57)
                || zlodziejArea.intersects(map.tylkoUlica58) || zlodziejArea.intersects(map.tylkoUlica59) || zlodziejArea.intersects(map.tylkoUlica60)
                || zlodziejArea.intersects(map.tylkoUlica61) || zlodziejArea.intersects(map.tylkoUlica62) || zlodziejArea.intersects(map.tylkoUlica63)
                || zlodziejArea.intersects(map.tylkoUlica64) || zlodziejArea.intersects(map.tylkoUlica65) || zlodziejArea.intersects(map.tylkoUlica66)
                || zlodziejArea.intersects(map.tylkoUlica67) || zlodziejArea.intersects(map.tylkoUlica68) || zlodziejArea.intersects(map.tylkoUlica69)
                || zlodziejArea.intersects(map.tylkoUlica70) || zlodziejArea.intersects(map.tylkoUlica71) || zlodziejArea.intersects(map.tylkoUlica72)
                || zlodziejArea.intersects(map.tylkoUlica73) || zlodziejArea.intersects(map.tylkoUlica74) || zlodziejArea.intersects(map.tylkoUlica75)
                || zlodziejArea.intersects(map.tylkoUlica76) || zlodziejArea.intersects(map.tylkoUlica77) || zlodziejArea.intersects(map.tylkoUlica78)
                || zlodziejArea.intersects(map.tylkoUlica79) || zlodziejArea.intersects(map.tylkoUlica80) || zlodziejArea.intersects(map.tylkoUlica81)
                || zlodziejArea.intersects(map.tylkoUlica82) || zlodziejArea.intersects(map.tylkoUlica83) || zlodziejArea.intersects(map.tylkoUlica84)
                || zlodziejArea.intersects(map.tylkoUlica85) || zlodziejArea.intersects(map.tylkoUlica86) || zlodziejArea.intersects(map.tylkoUlica87)
                || zlodziejArea.intersects(map.tylkoUlica88) || zlodziejArea.intersects(map.tylkoUlica89) || zlodziejArea.intersects(map.tylkoUlica90)
                || zlodziejArea.intersects(map.tylkoUlica91) || zlodziejArea.intersects(map.tylkoUlica92) || zlodziejArea.intersects(map.tylkoUlica93)
                || zlodziejArea.intersects(map.tylkoUlica94) || zlodziejArea.intersects(map.tylkoUlica95) || zlodziejArea.intersects(map.tylkoUlica96)
                || zlodziejArea.intersects(map.tylkoUlica97) || zlodziejArea.intersects(map.tylkoUlica98) || zlodziejArea.intersects(map.tylkoUlica99)
                || zlodziejArea.intersects(map.tylkoUlica100)) {
            switch (kierunek) {
                case "prawo":
                    zlodziejX -= map.tileSize;
                    break;
                case "lewo":
                    zlodziejX += map.tileSize;
                    break;
                case "dol":
                    zlodziejY -= map.tileSize;
                    break;
                case "gora":
                    zlodziejY += map.tileSize;
                    break;
            }
        }
    }
}