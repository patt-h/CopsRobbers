import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Pole {
    Mapa map;
    public PoleImage[] pole;

    /**
     * Utworzenie tablicy ze wszystkimi teksturami
     * @param map - mapa
     */
    public Pole(Mapa map) {
        this.map = map;
        pole = new PoleImage[15];
        getFieldImage();
    }

    /**
     * Przypisanie kazdej pozycji w tablicy do poszczegolnej tekstury
     */
    public void getFieldImage() {
        try {
            pole[0] = new PoleImage();
            pole[0].image = ImageIO.read(getClass().getResourceAsStream("trawa.png"));

            pole[1] = new PoleImage();
            pole[1].image = ImageIO.read(getClass().getResourceAsStream("mur_left.png"));

            pole[2] = new PoleImage();
            pole[2].image = ImageIO.read(getClass().getResourceAsStream("mur_rog_up_left.png"));

            pole[3] = new PoleImage();
            pole[3].image = ImageIO.read(getClass().getResourceAsStream("mur_rog_up_right.png"));

            pole[4] = new PoleImage();
            pole[4].image = ImageIO.read(getClass().getResourceAsStream("mur_rog_down_left.png"));

            pole[5] = new PoleImage();
            pole[5].image = ImageIO.read(getClass().getResourceAsStream("mur_rog_down_right.png"));

            pole[6] = new PoleImage();
            pole[6].image = ImageIO.read(getClass().getResourceAsStream("dach_domu.png"));

            pole[7] = new PoleImage();
            pole[7].image = ImageIO.read(getClass().getResourceAsStream("droga.png"));
            pole[7].tylkoUlica = true;

            pole[8] = new PoleImage();
            pole[8].image = ImageIO.read(getClass().getResourceAsStream("srodek_droga.png"));
            pole[8].tylkoUlica = true;

            pole[9] = new PoleImage();
            pole[9].image = ImageIO.read(getClass().getResourceAsStream("mur_right.png"));

            pole[10] = new PoleImage();
            pole[10].image = ImageIO.read(getClass().getResourceAsStream("mur_up.png"));

            pole[11] = new PoleImage();
            pole[11].image = ImageIO.read(getClass().getResourceAsStream("mur_down.png"));

            pole[12] = new PoleImage();
            pole[12].image = ImageIO.read(getClass().getResourceAsStream("chodnik.png"));

            pole[13] = new PoleImage();
            pole[13].image = ImageIO.read(getClass().getResourceAsStream("przejscie.png"));

            pole[14] = new PoleImage();
            pole[14].image = ImageIO.read(getClass().getResourceAsStream("srodek_droga_bok.png"));
            pole[14].tylkoUlica = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda rysowania calej mapy w dostosowaniu do rozmiaru
     * @param g2 - parametr metody Graphics2D
     * @param map - mapa
     */
    public void wstawPoleNaPozycje(Graphics2D g2, Mapa map) {
        int kolumny = 0;
        int rzedy = 0;
        int x = 0;
        int y = 0;

        while (kolumny < map.kolumny && rzedy < map.rzedy) {
            g2.drawImage(pole[0].image, x, y, map.tileSize, map.tileSize, null);
            kolumny++;
            x += map.tileSize;

            if (kolumny == map.kolumny) {
                g2.drawImage(pole[9].image, x - map.tileSize, y, map.tileSize, map.tileSize, null);
                kolumny = 0;
                x = 0;
                g2.drawImage(pole[1].image, x, y, map.tileSize, map.tileSize, null);
                rzedy++;
                y += map.tileSize;
            }
        }
        while (kolumny < map.kolumny) {
            g2.drawImage(pole[10].image, x, 0, map.tileSize, map.tileSize, null);
            g2.drawImage(pole[11].image, x, map.tileSize * (map.rzedy - 1), map.tileSize, map.tileSize, null);
            kolumny++;
            x += map.tileSize;
        }
        g2.drawImage(pole[2].image, 0, 0, map.tileSize, map.tileSize, null);
        g2.drawImage(pole[3].image, (map.kolumny-1) * map.tileSize, 0, map.tileSize, map.tileSize, null);
        g2.drawImage(pole[4].image, 0, (map.rzedy-1) * map.tileSize, map.tileSize, map.tileSize, null);
        g2.drawImage(pole[5].image, (map.kolumny-1) * map.tileSize, (map.rzedy-1) * map.tileSize, map.tileSize, map.tileSize, null);

        if (map.wyborMapy == 1) {
            g2.drawImage(pole[12].image, (1 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (1 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (1 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (1 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (1 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (1 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (1 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (4 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (6 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (7 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (8 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (4 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (6 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (7 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (8 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (2 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (2 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (2 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (2 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (2 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (3 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (3 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (3 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (3 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[6].image, (2 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[6].image, (2 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[6].image, (8 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[6].image, (8 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[13].image, (5 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[13].image, (5 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[13].image, (5 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[13].image, (5 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            } //Mala mapa

        if (map.wyborMapy == 2) {
            g2.drawImage(pole[7].image, (3 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (3 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[6].image, (4 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (4 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (4 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (3 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[6].image, (4 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (4 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (4 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[13].image, (4 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (6 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (7 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (7 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (7 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (7 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (7 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (7 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);


            g2.drawImage(pole[7].image, (8 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (9 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (10 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (11 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[14].image, (9 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (10 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (11 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (9 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (10 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (11 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (15 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (15 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (16 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (16 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (17 * map.tileSize), (1 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (17 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[6].image, (16 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (17 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[13].image, (16 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (15 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[6].image, (16 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (15 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (16 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (17 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (15 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (16 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (17 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (9 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (10 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (11 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[14].image, (9 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (10 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (11 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (9 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (10 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (11 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (14 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (13 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (13 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (13 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (13 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (13 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[12].image, (9 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            } //Srednia mapa

        if (map.wyborMapy == 3) {
            g2.drawImage(pole[6].image, (7 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[6].image, (9 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[6].image, (11 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[6].image, (17 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[6].image, (19 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[6].image, (21 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[12].image, (2 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (4 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (6 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (7 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (8 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (12 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (13 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (14 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (18 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (19 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (20 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (21 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (22 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (23 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (24 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (25 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (2 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[12].image, (2 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (3 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (4 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (5 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (6 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (7 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (8 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (12 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (13 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (14 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (18 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (19 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (20 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (21 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (22 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (23 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (24 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (25 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (12 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[12].image, (2 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (2 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[12].image, (26 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (26 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (9 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (10 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (11 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (15 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (16 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (17 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (18 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (19 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (20 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (21 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (22 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (23 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (24 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (3 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (6 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (7 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (8 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (9 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (10 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (11 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (12 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (13 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (14 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (15 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (16 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (17 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (18 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (19 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (20 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (21 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (22 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (23 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (24 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (4 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (9 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (10 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (11 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (15 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (16 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (17 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (18 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (19 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (20 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (21 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (22 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (23 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (24 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (5 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (9 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (10 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (11 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (15 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (16 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (17 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (18 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (19 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (20 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (21 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (22 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (23 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (24 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (9 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (6 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (7 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (8 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (9 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (10 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (11 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (12 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (13 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (14 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (15 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (16 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (17 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (18 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (19 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (20 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (21 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[14].image, (22 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (23 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (24 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (10 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (4 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (6 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (7 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (8 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (9 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (10 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (11 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (12 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (13 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (14 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (15 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (16 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (17 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (18 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (19 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (20 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (21 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (22 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (23 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (24 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (11 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (4 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (4 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (3 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (4 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (5 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (23 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (24 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (23 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (24 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[7].image, (23 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[8].image, (24 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[7].image, (25 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[12].image, (6 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (7 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (8 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (12 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (13 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (14 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (18 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (19 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (20 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (21 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (22 * map.tileSize), (6 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[12].image, (6 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (7 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (8 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (9 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (11 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (12 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (13 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (14 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (17 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (18 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (19 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (20 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (21 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (22 * map.tileSize), (8 * map.tileSize), map.tileSize, map.tileSize, null);

            g2.drawImage(pole[12].image, (6 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (8 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (10 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (12 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (13 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (14 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (15 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (16 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (18 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (20 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            g2.drawImage(pole[12].image, (22 * map.tileSize), (7 * map.tileSize), map.tileSize, map.tileSize, null);
            } //Duza mapa
        }
}