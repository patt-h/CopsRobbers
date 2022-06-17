import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Policjant {
    int liczebnosc;
    int policjantX;
    int policjantY;
    public int iloscRuchow;
    int szybkosc;
    String kierunek = "gora";
    public Rectangle policjantArea;
    int limiter = 0;
    BufferedImage tekstura;
    Random random = new Random();

    Mapa map;

    /**
     * Metoda wyliczajaca nastepne polozenie policjanta oraz sprawdzajaca detekcje budynkow i krancow mapy
     * @param map - mapa
     */
    public void currentPosition(Mapa map) {
        this.map = map;
        limiter += szybkosc;
        policjantArea = new Rectangle(policjantX - 16, policjantY - 16, 96, 96);
        map.buildingRectangle();

        if (limiter > map.slider.getValue()) {
            limiter = 0;
        }
        if (limiter == map.slider.getValue()) {
            if (policjantArea.intersects(map.building1) || policjantArea.intersects(map.building2)
                    || policjantArea.intersects(map.building3) || policjantArea.intersects(map.building4)
                    || policjantArea.intersects(map.building5) || policjantArea.intersects(map.building6)) {
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
                limiter = 0;
                iloscRuchow++;
            }
            if (limiter == map.slider.getValue()) {
                int i = random.nextInt(100) + 1; //losowa liczba z przedzialu 0-100

                if (i <= 25 && policjantX != ((map.kolumny - 1) * map.tileSize)) {
                    policjantX += map.tileSize;
                    kierunek = "prawo";
                }
                if (i > 25 && i <= 50 && policjantX != 0) {
                    policjantX -= map.tileSize;
                    kierunek = "lewo";
                }
                if (i > 50 && i <= 75 && policjantY != ((map.rzedy - 1) * map.tileSize)) {
                    policjantY += map.tileSize;
                    kierunek = "dol";
                }
                if (i > 75 && policjantY != 0) {
                    policjantY -= map.tileSize;
                    kierunek = "gora";
                }
                limiter = 0;
                iloscRuchow++;
            }
        }
    }
    /**
     * Metoda rysowania zlodzieja na mapie
     * @param g2 - parametr metody Graphics2D
     * @param map - mapa
     */
    public void rysowanie(Graphics2D g2, Mapa map) {
        g2.drawImage(tekstura, policjantX, policjantY, map.tileSize, map.tileSize, null);
    }
}