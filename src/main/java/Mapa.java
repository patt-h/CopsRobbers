import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Mapa extends JPanel implements Runnable, ActionListener {
    public int kolumny = 21;
    public int rzedy = 13;
    public final int tileSize = 64;
    int FPS = 60;
    public int wyborMapy = 2;
    boolean brakDrobnych;
    boolean brakRowerowych;
    boolean brakBankowych;
    Thread simulationThread;

    Pole pole = new Pole(this);
    Random random = new Random();
    public int piechotaRuchy;
    public int radiowozRuchy;
    public Piechota piechotaList[] = new Piechota[20];
    public Radiowoz radiowozList[] = new Radiowoz[20];
    public DrobnyZlodziej drobnyZlodziejList[] = new DrobnyZlodziej[20];
    public RowerowyZlodziej rowerowyZlodziejList[] = new RowerowyZlodziej[20];
    public BankowyZlodziej bankowyZlodziejList[] = new BankowyZlodziej[20];

    JPanel symulacjaPanel = new JPanel();
    JToggleButton button1 = new JToggleButton("Mała");
    JToggleButton button2 = new JToggleButton("Średnia");
    JToggleButton button3 = new JToggleButton("Duża");
    JLabel podgladLabel = new JLabel();

    JSlider slider = new JSlider(4,60,60);
    JLabel label = new JLabel("Tempo symulacji: ");

    //ZLICZANIE CZASU SYMULACJI
    int czas = 0;
    int minuty = 0;
    int sekundy = 0;
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            czas += 1000;
            minuty = (czas/60000) % 60;
            sekundy = (czas/1000) % 60;
        }
    });

    /**
     * Panel wyboru mapy w pierwszym okienku
     */
    public void getMapSize() {
        button2.setSelected(true); //bo domyslnie jest srednia mapa
        ImageIcon sredniaMapa = new ImageIcon(getClass().getResource("sredniaMapa.png"));
        Image image = sredniaMapa.getImage();
        Image podgladImage = image.getScaledInstance(350,217,Image.SCALE_SMOOTH);
        ImageIcon sredniaIcon = new ImageIcon(podgladImage);
        podgladLabel.setIcon(sredniaIcon);
        podgladLabel.setBounds(320,45,350,217);
        symulacjaPanel.add(podgladLabel);

        button1.setBounds(10,230,80,80);
        button1.setFocusable(false);
        button1.addActionListener(this);
        button2.setBounds(100,230,80,80);
        button2.setFocusable(false);
        button2.addActionListener(this);
        button3.setBounds(190,230,80,80);
        button3.setFocusable(false);
        button3.addActionListener(this);

        symulacjaPanel.setLayout(null);
        symulacjaPanel.add(button1);
        symulacjaPanel.add(button2);
        symulacjaPanel.add(button3);
    }

    /**
     * Listener przyciskow wyboru mapy w pierwszym okienku
     * @param e - ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            button1.setSelected(true);
            button2.setSelected(false);
            button3.setSelected(false);
            wyborMapy = 1;
            kolumny = 11;
            rzedy = 9;

            ImageIcon malaMapa = new ImageIcon(getClass().getResource("malaMapa.png"));
            Image image = malaMapa.getImage();
            Image podgladImage = image.getScaledInstance(350,217,Image.SCALE_SMOOTH);
            ImageIcon malaIcon = new ImageIcon(podgladImage);
            podgladLabel.setIcon(malaIcon);
            podgladLabel.setBounds(320,45,350,217);
            symulacjaPanel.add(podgladLabel);
        }
        if (e.getSource() == button2) {
            button1.setSelected(false);
            button2.setSelected(true);
            button3.setSelected(false);
            wyborMapy = 2;
            kolumny = 21;
            rzedy = 13;

            ImageIcon sredniaMapa = new ImageIcon(getClass().getResource("sredniaMapa.png"));
            Image image = sredniaMapa.getImage();
            Image podgladImage = image.getScaledInstance(350,217,Image.SCALE_SMOOTH);
            ImageIcon sredniaIcon = new ImageIcon(podgladImage);
            podgladLabel.setIcon(sredniaIcon);
            podgladLabel.setBounds(320,45,350,217);
            symulacjaPanel.add(podgladLabel);
        }
        if (e.getSource() == button3) {
            button1.setSelected(false);
            button2.setSelected(false);
            button3.setSelected(true);
            wyborMapy = 3;
            kolumny = 29;
            rzedy = 15;

            ImageIcon duzaMapa = new ImageIcon(getClass().getResource("duzaMapa.png"));
            Image image = duzaMapa.getImage();
            Image podgladImage = image.getScaledInstance(350,217,Image.SCALE_SMOOTH);
            ImageIcon duzaIcon = new ImageIcon(podgladImage);
            podgladLabel.setIcon(duzaIcon);
            podgladLabel.setBounds(320,45,350,217);
            symulacjaPanel.add(podgladLabel);
        }
    }

    /**
     * Suwak predkosci symulacji
     */
    public void suwakPredkosci() {
        this.setLayout(null);
        label.setBounds(15,tileSize * (rzedy -1) + 9,150,25);
        slider.setBounds(10,tileSize * (rzedy -1) + 25,150,25);
        slider.setOpaque(false);
        slider.addChangeListener(this::sliderListener);
        this.add(label);
        this.add(slider);
    }

    /**
     * Listener suwaka
     * @param e - ChangeEvent
     */
    public void sliderListener(ChangeEvent e) {
        if (slider.getValue() > 50) {
            slider.setValue(60);
        }
        if (slider.getValue() > 30 && slider.getValue() <= 50) {
            slider.setValue(40);
        }
        if (slider.getValue() > 12 && slider.getValue() <= 30) {
            slider.setValue(20);
        }
        if (slider.getValue() <= 12) {
            slider.setValue(4);
        }
    }

    /**
     * Rozpoczecie symulacji
     */
    public void startSimulationThread() {
        simulationThread = new Thread(this);
        simulationThread.start();
        timer.start();
    }

    /**
     * Ustawienie jak czesto odswieza sie symulacja
     */
    @Override
    public void run() {
        while (simulationThread != null) {
            double drawInterval = 1000000000/FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Aktualizacja polozenia o wyliczone wartosci
     */
    public void update() {
        int licznikNullDrobny = 0;
        int licznikNullRowerowy = 0;
        int licznikNullBankowy = 0;

        //PIECHOTA
        for (int i = 0; i < piechotaList.length; i++) {
            if (piechotaList[i] != null) {
                piechotaList[i].currentPosition(this);
                piechotaRuchy = piechotaList[0].iloscRuchow;
            }
        }
        //RADIOWOZ
        for (int i = 0; i < radiowozList.length; i++) {
            if (radiowozList[i] != null) {
                radiowozList[i].currentPosition(this);
                radiowozList[i].tylkoUlica(this);
                radiowozRuchy = radiowozList[0].iloscRuchow;
            }
        }
        //DROBNY ZLODZIEJ
        for (int i = 0; i < drobnyZlodziejList.length; i++) {
            if (drobnyZlodziejList[i] != null) {
                drobnyZlodziejList[i].currentPosition(this);

                int x = drobnyZlodziejList[i].zlodziejX;
                int y = drobnyZlodziejList[i].zlodziejY;
                for (int k = 0; k < piechotaList.length; k++) {
                    if (piechotaList[k] != null) {
                        if (x == piechotaList[k].policjantX && y == piechotaList[k].policjantY) {
                            drobnyZlodziejList[i] = null;
                        }
                    }
                }
                for (int k = 0; k < radiowozList.length; k++) {
                    if (radiowozList[k] != null) {
                        if (x == radiowozList[k].policjantX && y == radiowozList[k].policjantY) {
                            drobnyZlodziejList[i] = null;
                        }
                    }
                }
            }
        }
        //ROWEROWY ZLODZIEJ
        for (int i = 0; i < rowerowyZlodziejList.length; i++) {
            if (rowerowyZlodziejList[i] != null) {
                rowerowyZlodziejList[i].currentPosition(this);

                int x = rowerowyZlodziejList[i].zlodziejX;
                int y = rowerowyZlodziejList[i].zlodziejY;

                for (int k = 0; k < piechotaList.length; k++) {
                    if (piechotaList[k] != null) {
                        if (x == piechotaList[k].policjantX && y == piechotaList[k].policjantY) {
                            int h = random.nextInt(100+1);
                            //25% na ucieczke
                            if (h <= 25) {
                                System.out.println("ZLODZIEJ UCIEKL!");
                                rowerowyZlodziejList[i].ucieczka();
                                if (rowerowyZlodziejList[i].zlodziejX != ((kolumny - 1) * tileSize)) {
                                    rowerowyZlodziejList[i].zlodziejX += tileSize;
                                }
                                else {
                                    rowerowyZlodziejList[i].zlodziejX -= tileSize;
                                }
                            }
                            if (h > 25) {
                                rowerowyZlodziejList[i] = null;
                            }
                        }
                    }
                }
                for (int k = 0; k < radiowozList.length; k++) {
                    if (radiowozList[k] != null) {
                        if (x == radiowozList[k].policjantX && y == radiowozList[k].policjantY) {
                            int h = random.nextInt(100+1);
                            //25% na ucieczke
                            if (h <= 25) {
                                System.out.println("ZLODZIEJ UCIEKL!");
                                rowerowyZlodziejList[i].ucieczka();
                                if (rowerowyZlodziejList[i].zlodziejX != ((kolumny - 1) * tileSize)) {
                                    rowerowyZlodziejList[i].zlodziejX += tileSize;
                                }
                                else {
                                    rowerowyZlodziejList[i].zlodziejX -= tileSize;
                                }
                            }
                            if (h > 25) {
                                rowerowyZlodziejList[i] = null;
                            }
                        }
                    }
                }
            }
        }
        //BANKOWY ZLODZIEJ
        for (int i = 0; i < bankowyZlodziejList.length; i++) {
            if (bankowyZlodziejList[i] != null) {
                bankowyZlodziejList[i].currentPosition(this);
                bankowyZlodziejList[i].tylkoUlica(this);

                int x = bankowyZlodziejList[i].zlodziejX;
                int y = bankowyZlodziejList[i].zlodziejY;

                for (int k = 0; k < piechotaList.length; k++) {
                    if (piechotaList[k] != null) {
                        if (x == piechotaList[k].policjantX && y == piechotaList[k].policjantY) {
                            bankowyZlodziejList[i] = null;
                        }
                    }
                }
                for (int k = 0; k < radiowozList.length; k++) {
                    if (radiowozList[k] != null) {
                        if (x == radiowozList[k].policjantX && y == radiowozList[k].policjantY) {
                            bankowyZlodziejList[i] = null;
                        }
                    }
                }
            }
        }
        //KONCOWY EKRAN GDY BRAK ZLODZIEI
        for (int i = 0; i < piechotaList.length; i++) {

            if (drobnyZlodziejList[i] == null) {
                licznikNullDrobny++;
                if (licznikNullDrobny == 20) {
                    brakDrobnych = true;
                }
            }
            if (rowerowyZlodziejList[i] == null) {
                licznikNullRowerowy++;
                if (licznikNullRowerowy == 20) {
                    brakRowerowych = true;
                }
            }
            if (bankowyZlodziejList[i] == null) {
                licznikNullBankowy++;
                if (licznikNullBankowy == 20) {
                    brakBankowych = true;
                }
            }
            if (brakDrobnych && brakRowerowych && brakBankowych) {
                simulationThread = null;
                timer.stop();
                new EndFrame(this);
            }
        }
    }

    /**
     * Funkcja rysowania elementow
     * @param g - parametr metody Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create(); // <- przez to stracilem 6 godzin zycia szukajac powodu nie pojawiania sie suwaka

        pole.wstawPoleNaPozycje(g2,this); //MAPA
        for (int i = 0; i < piechotaList.length; i++) {
            if (piechotaList[i] != null) {
                piechotaList[i].rysowanie(g2, this);
            }
        }
        for (int i = 0; i < radiowozList.length; i++) {
            if (radiowozList[i] != null) {
                radiowozList[i].rysowanie(g2, this);
            }
        }
        for (int i = 0; i < drobnyZlodziejList.length; i++) {
            if (drobnyZlodziejList[i] != null) {
                drobnyZlodziejList[i].rysowanie(g2,this);
            }
        }
        for (int i = 0; i < rowerowyZlodziejList.length; i++) {
            if (rowerowyZlodziejList[i] != null) {
                rowerowyZlodziejList[i].rysowanie(g2,this);
            }
        }
        for (int i = 0; i < bankowyZlodziejList.length; i++) {
            if (bankowyZlodziejList[i] != null) {
                bankowyZlodziejList[i].rysowanie(g2,this);
            }
        }
        g2.dispose();
    }
    Rectangle building1, building2, building3, building4, building5, building6;
    Rectangle tylkoUlica1, tylkoUlica2, tylkoUlica3, tylkoUlica4, tylkoUlica5, tylkoUlica6, tylkoUlica7, tylkoUlica8;
    Rectangle tylkoUlica9, tylkoUlica10, tylkoUlica11, tylkoUlica12, tylkoUlica13, tylkoUlica14, tylkoUlica15, tylkoUlica16;
    Rectangle tylkoUlica17, tylkoUlica18, tylkoUlica19, tylkoUlica20, tylkoUlica21, tylkoUlica22, tylkoUlica23, tylkoUlica24;
    Rectangle tylkoUlica25, tylkoUlica26, tylkoUlica27, tylkoUlica28, tylkoUlica29, tylkoUlica30, tylkoUlica31, tylkoUlica32;
    Rectangle tylkoUlica33, tylkoUlica34, tylkoUlica35, tylkoUlica36, tylkoUlica37, tylkoUlica38, tylkoUlica39, tylkoUlica40;
    Rectangle tylkoUlica41, tylkoUlica42, tylkoUlica43, tylkoUlica44, tylkoUlica45, tylkoUlica46, tylkoUlica47, tylkoUlica48;
    Rectangle tylkoUlica49, tylkoUlica50, tylkoUlica51, tylkoUlica52, tylkoUlica53, tylkoUlica54, tylkoUlica55, tylkoUlica56;
    Rectangle tylkoUlica57, tylkoUlica58, tylkoUlica59, tylkoUlica60, tylkoUlica61, tylkoUlica62, tylkoUlica63, tylkoUlica64;
    Rectangle tylkoUlica65, tylkoUlica66, tylkoUlica67, tylkoUlica68, tylkoUlica69, tylkoUlica70, tylkoUlica71, tylkoUlica72;
    Rectangle tylkoUlica73, tylkoUlica74, tylkoUlica75, tylkoUlica76, tylkoUlica77, tylkoUlica78, tylkoUlica79, tylkoUlica80;
    Rectangle tylkoUlica81, tylkoUlica82, tylkoUlica83, tylkoUlica84, tylkoUlica85, tylkoUlica86, tylkoUlica87, tylkoUlica88;
    Rectangle tylkoUlica89, tylkoUlica90, tylkoUlica91, tylkoUlica92, tylkoUlica93, tylkoUlica94, tylkoUlica95, tylkoUlica96;
    Rectangle tylkoUlica97, tylkoUlica98, tylkoUlica99, tylkoUlica100;
    /**
     * Tworzenie kwadratow do detekcji kolizji
     */
    public void buildingRectangle() {
        if (wyborMapy == 1) {
            building1 = new Rectangle(2 * tileSize, 2 * tileSize, tileSize, tileSize);
            building2 = new Rectangle(2 * tileSize, 6 * tileSize, tileSize, tileSize);
            building3 = new Rectangle(8 * tileSize, 2 * tileSize, tileSize, tileSize);
            building4 = new Rectangle(8 * tileSize, 6 * tileSize, tileSize, tileSize);
            building5 = new Rectangle(8 * tileSize, 6 * tileSize, tileSize, tileSize);
            building6 = new Rectangle(8 * tileSize, 6 * tileSize, tileSize, tileSize);

            tylkoUlica1 = new Rectangle(3 * tileSize, 1 * tileSize, tileSize, tileSize);
            tylkoUlica2 = new Rectangle(4 * tileSize, 1 * tileSize, tileSize, tileSize);
            tylkoUlica3 = new Rectangle(5 * tileSize, 1 * tileSize, tileSize, tileSize);
            tylkoUlica4 = new Rectangle(6 * tileSize, 1 * tileSize, tileSize, tileSize);
            tylkoUlica5 = new Rectangle(7 * tileSize, 1 * tileSize, tileSize, tileSize); //GORA

            tylkoUlica6 = new Rectangle(1 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica7 = new Rectangle(1 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica8 = new Rectangle(1 * tileSize, 5 * tileSize, tileSize, tileSize); //LEWO

            tylkoUlica9 = new Rectangle(9 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica10 = new Rectangle(9 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica11 = new Rectangle(9 * tileSize, 5 * tileSize, tileSize, tileSize); //PRAWO

            tylkoUlica12 = new Rectangle(3 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica13 = new Rectangle(4 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica14 = new Rectangle(5 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica15 = new Rectangle(6 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica16 = new Rectangle(7 * tileSize, 7 * tileSize, tileSize, tileSize); //DOL

            tylkoUlica17 = new Rectangle(5 * tileSize, 4 * tileSize, tileSize, tileSize); //SRODEK

            tylkoUlica18 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica19 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica20 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica21 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica22 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica23 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica24 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica25 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica26 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica27 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica28 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica29 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica30 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica31 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica32 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica33 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica34 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica35 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica36 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica37 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica38 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica39 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica40 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica41 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica42 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica43 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica44 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica45 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica46 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica47 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica48 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica49 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica50 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica51 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica52 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica53 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica54 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica55 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica56 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica57 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica58 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica59 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica60 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica61 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica62 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica63 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica64 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica65 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica66 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica67 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica68 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica69 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica70 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica71 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica72 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica73 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica74 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica75 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica76 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica77 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica78 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica79 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica80 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica81 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica82 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica83 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica84 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica85 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica86 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica87 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica88 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica89 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica90 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica91 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica92 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica93 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica94 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica95 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica96 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica97 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica98 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica99 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica100 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize); //ZAPYCHACZE
        }
        if (wyborMapy == 2) {
            building1 = new Rectangle(4 * tileSize, 4 * tileSize, tileSize, tileSize);
            building2 = new Rectangle(4 * tileSize, 8 * tileSize, tileSize, tileSize);
            building3 = new Rectangle(16 * tileSize, 4 * tileSize, tileSize, tileSize);
            building4 = new Rectangle(16 * tileSize, 8 * tileSize, tileSize, tileSize);
            building5 = new Rectangle(16 * tileSize, 8 * tileSize, tileSize, tileSize);
            building6 = new Rectangle(16 * tileSize, 8 * tileSize, tileSize, tileSize);

            tylkoUlica1 = new Rectangle(3 * tileSize, 0, tileSize, tileSize);
            tylkoUlica2 = new Rectangle(4 * tileSize, 0, tileSize, tileSize);
            tylkoUlica3 = new Rectangle(5 * tileSize, 0, tileSize, tileSize);
            tylkoUlica4 = new Rectangle(6 * tileSize, 0, tileSize, tileSize);
            tylkoUlica5 = new Rectangle(7 * tileSize, 0, tileSize, tileSize);
            tylkoUlica6 = new Rectangle(8 * tileSize, 0, tileSize, tileSize);
            tylkoUlica7 = new Rectangle(9 * tileSize, 0, tileSize, tileSize);
            tylkoUlica8 = new Rectangle(10 * tileSize, 0, tileSize, tileSize);
            tylkoUlica9 = new Rectangle(11 * tileSize, 0, tileSize, tileSize);
            tylkoUlica10 = new Rectangle(12 * tileSize, 0, tileSize, tileSize);
            tylkoUlica11 = new Rectangle(13 * tileSize, 0, tileSize, tileSize);
            tylkoUlica12 = new Rectangle(14 * tileSize, 0, tileSize, tileSize);
            tylkoUlica13 = new Rectangle(15 * tileSize, 0, tileSize, tileSize);
            tylkoUlica14 = new Rectangle(16 * tileSize, 0, tileSize, tileSize);
            tylkoUlica15 = new Rectangle(17 * tileSize, 0, tileSize, tileSize); //GORA

            tylkoUlica16 = new Rectangle(3 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica17 = new Rectangle(4 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica18 = new Rectangle(5 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica19 = new Rectangle(6 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica20 = new Rectangle(7 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica21 = new Rectangle(8 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica22 = new Rectangle(9 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica23 = new Rectangle(10 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica24 = new Rectangle(11 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica25 = new Rectangle(12 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica26 = new Rectangle(13 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica27 = new Rectangle(14 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica28 = new Rectangle(15 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica29 = new Rectangle(16 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica30 = new Rectangle(17 * tileSize, 12 * tileSize, tileSize, tileSize); //DOL

            tylkoUlica31 = new Rectangle(2 * tileSize, 1 * tileSize, tileSize, tileSize);
            tylkoUlica32 = new Rectangle(2 * tileSize, 2 * tileSize, tileSize, tileSize);

            tylkoUlica33 = new Rectangle(3 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica34 = new Rectangle(4 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica35 = new Rectangle(5 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica36 = new Rectangle(5 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica37 = new Rectangle(5 * tileSize, 5 * tileSize, tileSize, tileSize);

            tylkoUlica38 = new Rectangle(5 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica39 = new Rectangle(5 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica40 = new Rectangle(5 * tileSize, 9 * tileSize, tileSize, tileSize);
            tylkoUlica41 = new Rectangle(4 * tileSize, 9 * tileSize, tileSize, tileSize);
            tylkoUlica42 = new Rectangle(3 * tileSize, 9 * tileSize, tileSize, tileSize);

            tylkoUlica43 = new Rectangle(2 * tileSize, 10 * tileSize, tileSize, tileSize);
            tylkoUlica44 = new Rectangle(2 * tileSize, 11 * tileSize, tileSize, tileSize); //LEWO

            tylkoUlica45 = new Rectangle(18 * tileSize, 1 * tileSize, tileSize, tileSize);
            tylkoUlica46 = new Rectangle(18 * tileSize, 2 * tileSize, tileSize, tileSize);

            tylkoUlica47 = new Rectangle(17 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica48 = new Rectangle(16 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica49 = new Rectangle(15 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica50 = new Rectangle(15 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica51 = new Rectangle(15 * tileSize, 5 * tileSize, tileSize, tileSize);

            tylkoUlica52 = new Rectangle(15 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica53 = new Rectangle(15 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica54 = new Rectangle(15 * tileSize, 9 * tileSize, tileSize, tileSize);
            tylkoUlica55 = new Rectangle(16 * tileSize, 9 * tileSize, tileSize, tileSize);
            tylkoUlica56 = new Rectangle(17 * tileSize, 9 * tileSize, tileSize, tileSize);

            tylkoUlica57 = new Rectangle(18 * tileSize, 10 * tileSize, tileSize, tileSize);
            tylkoUlica58 = new Rectangle(18 * tileSize, 11 * tileSize, tileSize, tileSize); //PRAWO

            tylkoUlica59 = new Rectangle(9 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica60 = new Rectangle(10 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica61 = new Rectangle(11 * tileSize, 4 * tileSize, tileSize, tileSize); //SRODEK GORA

            tylkoUlica62 = new Rectangle(9 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica63 = new Rectangle(10 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica64 = new Rectangle(11 * tileSize, 8 * tileSize, tileSize, tileSize); //SRODEK DOL

            tylkoUlica65 = new Rectangle(9 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica66 = new Rectangle(9 * tileSize, 5 * tileSize, tileSize, tileSize);
            tylkoUlica67 = new Rectangle(9 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica68 = new Rectangle(9 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica69 = new Rectangle(9 * tileSize, 8 * tileSize, tileSize, tileSize); //SRODEK LEWO

            tylkoUlica70 = new Rectangle(11 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica71 = new Rectangle(11 * tileSize, 5 * tileSize, tileSize, tileSize);
            tylkoUlica72 = new Rectangle(11 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica73 = new Rectangle(11 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica74 = new Rectangle(11 * tileSize, 8 * tileSize, tileSize, tileSize); //SRODEK PRAWO

            tylkoUlica75 = new Rectangle(2 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica76 = new Rectangle(18 * tileSize, 6 * tileSize, tileSize, tileSize);

            tylkoUlica77 = new Rectangle(3 * tileSize, 5 * tileSize, tileSize, tileSize);
            tylkoUlica78 = new Rectangle(4 * tileSize, 5 * tileSize, tileSize, tileSize);
            tylkoUlica79 = new Rectangle(3 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica80 = new Rectangle(4 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica81 = new Rectangle(17 * tileSize, 5 * tileSize, tileSize, tileSize);
            tylkoUlica82 = new Rectangle(16 * tileSize, 5 * tileSize, tileSize, tileSize);
            tylkoUlica83 = new Rectangle(16 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica84 = new Rectangle(17 * tileSize, 7 * tileSize, tileSize, tileSize); // BOKI SRODEK

            tylkoUlica85 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica86 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica87 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica88 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica89 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica90 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica91 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica92 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica93 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica94 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica95 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica96 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica97 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica98 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica99 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize);
            tylkoUlica100 = new Rectangle(-4 * tileSize, -4 * tileSize, tileSize, tileSize); //ZAPYCHACZE
        }
        if (wyborMapy == 3) {
            building1 = new Rectangle((7 * tileSize), (7 * tileSize), tileSize, tileSize);
            building2 = new Rectangle((9 * tileSize), (7 * tileSize), tileSize, tileSize);
            building3 = new Rectangle((11 * tileSize), (7 * tileSize), tileSize, tileSize);
            building4 = new Rectangle((17 * tileSize), (7 * tileSize), tileSize, tileSize);
            building5 = new Rectangle((19 * tileSize), (7 * tileSize), tileSize, tileSize);
            building6 = new Rectangle((21 * tileSize), (7 * tileSize), tileSize, tileSize);

            tylkoUlica1 = new Rectangle(3 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica2 = new Rectangle(4 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica3 = new Rectangle(5 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica4 = new Rectangle(6 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica5 = new Rectangle(7 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica6 = new Rectangle(8 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica7 = new Rectangle(9 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica8 = new Rectangle(10 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica9 = new Rectangle(11 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica10 = new Rectangle(12 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica11 = new Rectangle(13 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica12 = new Rectangle(14 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica13 = new Rectangle(15 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica14 = new Rectangle(16 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica15 = new Rectangle(17 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica16 = new Rectangle(18 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica17 = new Rectangle(19 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica18 = new Rectangle(20 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica19 = new Rectangle(21 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica20 = new Rectangle(22 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica21 = new Rectangle(23 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica22 = new Rectangle(24 * tileSize, 2 * tileSize, tileSize, tileSize);
            tylkoUlica23 = new Rectangle(25 * tileSize, 2 * tileSize, tileSize, tileSize); //GORA

            tylkoUlica24 = new Rectangle(3 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica25 = new Rectangle(4 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica26 = new Rectangle(5 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica27 = new Rectangle(6 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica28 = new Rectangle(7 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica29 = new Rectangle(8 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica30 = new Rectangle(9 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica31 = new Rectangle(10 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica32 = new Rectangle(11 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica33 = new Rectangle(12 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica34 = new Rectangle(13 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica35 = new Rectangle(14 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica100 = new Rectangle(15 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica36 = new Rectangle(16 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica37 = new Rectangle(17 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica38 = new Rectangle(18 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica39 = new Rectangle(19 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica40 = new Rectangle(20 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica41 = new Rectangle(21 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica42 = new Rectangle(22 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica43 = new Rectangle(23 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica44 = new Rectangle(24 * tileSize, 12 * tileSize, tileSize, tileSize);
            tylkoUlica45 = new Rectangle(25 * tileSize, 12 * tileSize, tileSize, tileSize); //DOL

            tylkoUlica46 = new Rectangle(2 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica47 = new Rectangle(2 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica48 = new Rectangle(2 * tileSize, 5 * tileSize, tileSize, tileSize);
            tylkoUlica49 = new Rectangle(2 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica50 = new Rectangle(2 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica51 = new Rectangle(2 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica52 = new Rectangle(2 * tileSize, 9 * tileSize, tileSize, tileSize);
            tylkoUlica53 = new Rectangle(2 * tileSize, 10 * tileSize, tileSize, tileSize);
            tylkoUlica54 = new Rectangle(2 * tileSize, 11 * tileSize, tileSize, tileSize); //LEWO

            tylkoUlica55 = new Rectangle(26 * tileSize, 3 * tileSize, tileSize, tileSize);
            tylkoUlica56 = new Rectangle(26 * tileSize, 4 * tileSize, tileSize, tileSize);
            tylkoUlica57 = new Rectangle(26 * tileSize, 5 * tileSize, tileSize, tileSize);
            tylkoUlica58 = new Rectangle(26 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica59 = new Rectangle(26 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica60 = new Rectangle(26 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica61 = new Rectangle(26 * tileSize, 9 * tileSize, tileSize, tileSize);
            tylkoUlica62 = new Rectangle(26 * tileSize, 10 * tileSize, tileSize, tileSize);
            tylkoUlica63 = new Rectangle(26 * tileSize, 11 * tileSize, tileSize, tileSize); //PRAWO

            tylkoUlica64 = new Rectangle(6 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica65 = new Rectangle(7 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica66 = new Rectangle(8 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica67 = new Rectangle(9 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica68 = new Rectangle(10 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica69 = new Rectangle(11 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica70 = new Rectangle(12 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica71 = new Rectangle(13 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica72 = new Rectangle(14 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica73 = new Rectangle(15 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica74 = new Rectangle(16 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica75 = new Rectangle(17 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica76 = new Rectangle(18 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica77 = new Rectangle(19 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica78 = new Rectangle(20 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica79 = new Rectangle(21 * tileSize, 6 * tileSize, tileSize, tileSize);
            tylkoUlica80 = new Rectangle(22 * tileSize, 6 * tileSize, tileSize, tileSize); //SRODEK GORA

            tylkoUlica81 = new Rectangle(6 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica82 = new Rectangle(7 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica83 = new Rectangle(8 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica84 = new Rectangle(9 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica85 = new Rectangle(10 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica86 = new Rectangle(11 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica87 = new Rectangle(12 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica88 = new Rectangle(13 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica89 = new Rectangle(14 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica90 = new Rectangle(15 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica91 = new Rectangle(16 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica92 = new Rectangle(17 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica93 = new Rectangle(18 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica94 = new Rectangle(19 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica95 = new Rectangle(20 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica96 = new Rectangle(21 * tileSize, 8 * tileSize, tileSize, tileSize);
            tylkoUlica97 = new Rectangle(22 * tileSize, 8 * tileSize, tileSize, tileSize); //SRODEK DOL

            tylkoUlica98 = new Rectangle(6 * tileSize, 7 * tileSize, tileSize, tileSize);
            tylkoUlica99 = new Rectangle(22 * tileSize, 7 * tileSize, tileSize, tileSize);
        }
    }
}