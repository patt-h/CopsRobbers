import javax.swing.*;
import java.awt.*;

public class EndFrame extends JFrame {
    JLabel endLabel1 = new JLabel();
    JLabel endLabel2 = new JLabel();
    JLabel endLabel3 = new JLabel();
    JLabel endLabel4 = new JLabel();
    JPanel panel = new JPanel();
    Font mainFont = new Font("Times New Roman", Font.PLAIN, 25);
    Font endFont = new Font("Times New Roman", Font.PLAIN, 15);

    Mapa map;

    /**
     * Koncowe okno ze statystykami
     * @param map - mapa
     */
    public EndFrame(Mapa map) {
        this.map = map;
        this.setResizable(false);
        this.setTitle("Koniec!");
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,150);
        this.setLocationRelativeTo(null);

        int wszystkieRuchy = map.piechotaRuchy + map.radiowozRuchy;
        int normalneTempo = map.piechotaRuchy/60;
        String wersja;

        if (normalneTempo > 1 && normalneTempo < 5) {
            wersja = "minuty";
        } else {
            wersja = "minut";
        }

        endLabel1.setBounds(0,0,500,50);
        endLabel1.setText("Wszyscy złodzieje zostali złapani!");
        endLabel1.setFont(mainFont);

        endLabel2.setBounds(0,0,500,25);
        endLabel2.setText("Ilość ruchów potrzebnych do wyeliminowania złodziei: " + wszystkieRuchy);
        endLabel2.setFont(endFont);

        endLabel3.setBounds(0,0,500,25);
        endLabel3.setText("Ilość czasu do wykonania wszystkich ruchów przy normalnym tempie: " + normalneTempo + " " + wersja);
        endLabel3.setFont(endFont);

        endLabel4.setBounds(0,0,500,25);
        endLabel4.setText("Symulacja trwała: " + map.minuty + " min " + map.sekundy + " s");
        endLabel4.setFont(endFont);

        panel.add(endLabel1);
        panel.add(endLabel2);
        panel.add(endLabel3);
        panel.add(endLabel4);
        this.add(panel);
        this.setVisible(true);
    }
}