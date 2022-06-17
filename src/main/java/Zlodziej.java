import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Zlodziej {
    int liczebnosc;
    int zlodziejX;
    int zlodziejY;
    int szybkosc;
    String kierunek = "gora";
    public Rectangle zlodziejArea;
    int limiter = 0;
    BufferedImage tekstura;
    Random random = new Random();
    Mapa map;

    /**
     * Metoda wyliczajaca nastepne polozenie zlodzieja oraz sprawdzajaca detekcje budynkow i krancow mapy
     * @param map - mapa
     */
    public void currentPosition(Mapa map) {
        this.map = map;
        limiter += szybkosc;
        zlodziejArea = new Rectangle(zlodziejX-16, zlodziejY-16, 96 ,96);
        map.buildingRectangle();

        if (limiter > map.slider.getValue()) {
            limiter = 0;
        }
        if (limiter == map.slider.getValue()) {
            if (zlodziejArea.intersects(map.building1) || zlodziejArea.intersects(map.building2)
                    || zlodziejArea.intersects(map.building3) || zlodziejArea.intersects(map.building4)
                    || zlodziejArea.intersects(map.building5) || zlodziejArea.intersects(map.building6)) {
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
                limiter = 0;
            }
        }
        if (limiter == map.slider.getValue()) {
            int k = random.nextInt(100) + 1; //losowa liczba z przedzialu 0-100

            if (k <= 25 && zlodziejX != ((map.kolumny - 1) * map.tileSize)) {
                zlodziejX += map.tileSize;
                kierunek = "prawo";
            }
            if (k > 25 && k <= 50 && zlodziejX != 0) {
                zlodziejX -= map.tileSize;
                kierunek = "lewo";
            }
            if (k > 50 && k <= 75 && zlodziejY != ((map.rzedy - 1) * map.tileSize)) {
                zlodziejY += map.tileSize;
                kierunek = "dol";
            }
            if (k > 75 && zlodziejY != 0) {
                zlodziejY -= map.tileSize;
                kierunek = "gora";
            }
            limiter = 0;
        }
    }
    /**
     * Metoda rysowania zlodzieja na mapie
     * @param g2 - parametr metody Graphics2D
     * @param map - mapa
     */
    public void rysowanie(Graphics2D g2, Mapa map) {
        g2.drawImage(tekstura, zlodziejX, zlodziejY, map.tileSize, map.tileSize, null);
        //g2.drawRect(zlodziejX-16, zlodziejY-16, 96, 96);
    }
}