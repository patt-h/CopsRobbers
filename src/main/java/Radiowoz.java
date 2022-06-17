import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Radiowoz extends Policjant {
    Mapa map;
    int liczebnosc;

    /**
     * Ustalenie tekstury i szybkosci radiowozu
     * @param map - mapa
     */
    public Radiowoz(Mapa map) {
        this.map = map;
        szybkosc = 4;

        try {
            tekstura = ImageIO.read(getClass().getResourceAsStream("radiowoz.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda wyliczajaca pierwsze polozenie
     */
    public void Polozenie() {
        for (int i = 0; i < liczebnosc; i++) {
            map.radiowozList[i] = new Radiowoz(map);
            map.radiowozList[i].policjantX = (random.nextInt(map.kolumny-1)) * map.tileSize;
            map.radiowozList[i].policjantY = (random.nextInt(map.rzedy-1)) * map.tileSize;
        }
    }
    /**
     * Metoda detekcji kolizji drogi
     * @param map - mapa
     */
    public void tylkoUlica(Mapa map) {
        this.map = map;
        policjantArea = new Rectangle(policjantX, policjantY,64, 64);

        if (policjantArea.intersects(map.tylkoUlica1) || policjantArea.intersects(map.tylkoUlica2) || policjantArea.intersects(map.tylkoUlica3)
            || policjantArea.intersects(map.tylkoUlica4) || policjantArea.intersects(map.tylkoUlica5) || policjantArea.intersects(map.tylkoUlica6)
            || policjantArea.intersects(map.tylkoUlica7) || policjantArea.intersects(map.tylkoUlica8) || policjantArea.intersects(map.tylkoUlica9)
            || policjantArea.intersects(map.tylkoUlica10) || policjantArea.intersects(map.tylkoUlica11) || policjantArea.intersects(map.tylkoUlica12)
            || policjantArea.intersects(map.tylkoUlica13) || policjantArea.intersects(map.tylkoUlica14) || policjantArea.intersects(map.tylkoUlica15)
            || policjantArea.intersects(map.tylkoUlica16) || policjantArea.intersects(map.tylkoUlica17) || policjantArea.intersects(map.tylkoUlica18)
            || policjantArea.intersects(map.tylkoUlica19) || policjantArea.intersects(map.tylkoUlica20) || policjantArea.intersects(map.tylkoUlica21)
            || policjantArea.intersects(map.tylkoUlica22) || policjantArea.intersects(map.tylkoUlica23) || policjantArea.intersects(map.tylkoUlica24)
            || policjantArea.intersects(map.tylkoUlica25) || policjantArea.intersects(map.tylkoUlica26) || policjantArea.intersects(map.tylkoUlica27)
            || policjantArea.intersects(map.tylkoUlica28) || policjantArea.intersects(map.tylkoUlica29) || policjantArea.intersects(map.tylkoUlica30)
            || policjantArea.intersects(map.tylkoUlica31) || policjantArea.intersects(map.tylkoUlica32) || policjantArea.intersects(map.tylkoUlica33)
            || policjantArea.intersects(map.tylkoUlica34) || policjantArea.intersects(map.tylkoUlica35) || policjantArea.intersects(map.tylkoUlica36)
            || policjantArea.intersects(map.tylkoUlica37) || policjantArea.intersects(map.tylkoUlica38) || policjantArea.intersects(map.tylkoUlica39)
            || policjantArea.intersects(map.tylkoUlica40) || policjantArea.intersects(map.tylkoUlica41) || policjantArea.intersects(map.tylkoUlica42)
            || policjantArea.intersects(map.tylkoUlica43) || policjantArea.intersects(map.tylkoUlica44) || policjantArea.intersects(map.tylkoUlica45)
            || policjantArea.intersects(map.tylkoUlica46) || policjantArea.intersects(map.tylkoUlica47) || policjantArea.intersects(map.tylkoUlica48)
            || policjantArea.intersects(map.tylkoUlica49) || policjantArea.intersects(map.tylkoUlica50) || policjantArea.intersects(map.tylkoUlica51)
            || policjantArea.intersects(map.tylkoUlica52) || policjantArea.intersects(map.tylkoUlica53) || policjantArea.intersects(map.tylkoUlica54)
            || policjantArea.intersects(map.tylkoUlica55) || policjantArea.intersects(map.tylkoUlica56) || policjantArea.intersects(map.tylkoUlica57)
            || policjantArea.intersects(map.tylkoUlica58) || policjantArea.intersects(map.tylkoUlica59) || policjantArea.intersects(map.tylkoUlica60)
            || policjantArea.intersects(map.tylkoUlica61) || policjantArea.intersects(map.tylkoUlica62) || policjantArea.intersects(map.tylkoUlica63)
            || policjantArea.intersects(map.tylkoUlica64) || policjantArea.intersects(map.tylkoUlica65) || policjantArea.intersects(map.tylkoUlica66)
            || policjantArea.intersects(map.tylkoUlica67) || policjantArea.intersects(map.tylkoUlica68) || policjantArea.intersects(map.tylkoUlica69)
            || policjantArea.intersects(map.tylkoUlica70) || policjantArea.intersects(map.tylkoUlica71) || policjantArea.intersects(map.tylkoUlica72)
            || policjantArea.intersects(map.tylkoUlica73) || policjantArea.intersects(map.tylkoUlica74) || policjantArea.intersects(map.tylkoUlica75)
            || policjantArea.intersects(map.tylkoUlica76) || policjantArea.intersects(map.tylkoUlica77) || policjantArea.intersects(map.tylkoUlica78)
            || policjantArea.intersects(map.tylkoUlica79) || policjantArea.intersects(map.tylkoUlica80) || policjantArea.intersects(map.tylkoUlica81)
            || policjantArea.intersects(map.tylkoUlica82) || policjantArea.intersects(map.tylkoUlica83) || policjantArea.intersects(map.tylkoUlica84)
            || policjantArea.intersects(map.tylkoUlica85) || policjantArea.intersects(map.tylkoUlica86) || policjantArea.intersects(map.tylkoUlica87)
            || policjantArea.intersects(map.tylkoUlica88) || policjantArea.intersects(map.tylkoUlica89) || policjantArea.intersects(map.tylkoUlica90)
            || policjantArea.intersects(map.tylkoUlica91) || policjantArea.intersects(map.tylkoUlica92) || policjantArea.intersects(map.tylkoUlica93)
            || policjantArea.intersects(map.tylkoUlica94) || policjantArea.intersects(map.tylkoUlica95) || policjantArea.intersects(map.tylkoUlica96)
            || policjantArea.intersects(map.tylkoUlica97) || policjantArea.intersects(map.tylkoUlica98) || policjantArea.intersects(map.tylkoUlica99)
            || policjantArea.intersects(map.tylkoUlica100)) {
            switch (kierunek) {
                case "prawo":
                    policjantX -= map.tileSize;
                    break;
                case "lewo":
                    policjantX += map.tileSize;
                    break;
                case "dol":
                    policjantY -= map.tileSize;
                    break;
                case "gora":
                    policjantY += map.tileSize;
                    break;
            }
        }
    }
}