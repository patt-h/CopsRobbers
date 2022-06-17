import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInput extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    Font newFont = new Font("Times New Roman", Font.PLAIN, 14);

    JLabel label1 = new JLabel("Wprowadź liczbę piechoty:");
    JTextField userInput1 = new JTextField();

    JLabel label2 = new JLabel("Wprowadź liczbę radiowozów:");
    JTextField userInput2 = new JTextField();

    JLabel label3 = new JLabel("Wprowadź liczbę drobnych złodziei:");
    JTextField userInput3 = new JTextField();

    JLabel label4 = new JLabel("Wprowadź liczbę rowerowych złodziei:");
    JTextField userInput4 = new JTextField();

    JLabel label5 = new JLabel("Wprowadź liczbę bankowych złodziei:");
    JTextField userInput5 = new JTextField();

    JLabel label6 = new JLabel("Wybierz rozmiar mapy:");
    JLabel label7 = new JLabel("<html><br>(max 20)</br></html>");
    JLabel label8 = new JLabel("Podgląd mapy:");

    JButton button = new JButton("OK");

    Symulacja symulacja = new Symulacja();
    Mapa map = new Mapa();
    Piechota piechota = new Piechota(map);
    Radiowoz radiowoz = new Radiowoz(map);
    DrobnyZlodziej drobnyZlodziej = new DrobnyZlodziej(map);
    RowerowyZlodziej rowerowyZlodziej = new RowerowyZlodziej(map);
    BankowyZlodziej bankowyZlodziej = new BankowyZlodziej(map);

    /**
     * Pierwsze okno, w ktorym wprowadzamy dane
     */
    public UserInput() {
        map.getMapSize();
        this.setResizable(false);
        this.setSize(700,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Wprowadź dane");

        panel.setBounds(0, 0, 290,230);
        panel.setLayout(null);

        label1.setBounds(10, 20, 160, 25);
        label1.setFont(newFont);
        label2.setBounds(10,45,200,25);
        label2.setFont(newFont);
        label3.setBounds(10,100,250,25);
        label3.setFont(newFont);
        label4.setBounds(10,125,250,25);
        label4.setFont(newFont);
        label5.setBounds(10,150,250,25);
        label5.setFont(newFont);
        label6.setBounds(10,205,250,25);
        label6.setFont(newFont);
        label7.setBounds(237,-12,50,30);
        label8.setBounds(320,20,160,25);
        label8.setFont(newFont);

        userInput1.setBounds(250, 21, 25, 25);
        userInput2.setBounds(250,46,25,25);
        userInput3.setBounds(250,101,25,25);
        userInput4.setBounds(250,126,25,25);
        userInput5.setBounds(250,151,25,25);

        button.setBounds(570, 300, 100, 50);
        button.setFocusable(false);
        button.addActionListener(this);

        panel.add(button);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(userInput1);
        panel.add(userInput2);
        panel.add(userInput3);
        panel.add(userInput4);
        panel.add(userInput5);
        this.add(button);
        this.add(label8);
        this.add(panel);
        this.add(map.symulacjaPanel);
        this.setVisible(true);
    }

    /**
     * Listener przycisku OK, jesli zostanie klikniety to rozpoczyna sie symulacja
     * @param e - ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            try {
                piechota.liczebnosc = Integer.parseInt(userInput1.getText());
                if (piechota.liczebnosc < 0) {
                    return;
                }
                radiowoz.liczebnosc = Integer.parseInt(userInput2.getText());
                if (radiowoz.liczebnosc < 0) {
                    return;
                }
                drobnyZlodziej.liczebnosc = Integer.parseInt(userInput3.getText());
                if (drobnyZlodziej.liczebnosc < 0) {
                    return;
                }
                rowerowyZlodziej.liczebnosc = Integer.parseInt(userInput4.getText());
                if (rowerowyZlodziej.liczebnosc < 0) {
                    return;
                }
                bankowyZlodziej.liczebnosc = Integer.parseInt(userInput5.getText());
                if (bankowyZlodziej.liczebnosc < 0) {
                    return;
                }
                piechota.Polozenie();
                radiowoz.Polozenie();
                drobnyZlodziej.Polozenie();
                rowerowyZlodziej.Polozenie();
                bankowyZlodziej.Polozenie();
                this.dispose();

                map.suwakPredkosci();
                symulacja.add(map);
                symulacja.setSize((map.kolumny*map.tileSize)+16,(map.rzedy*map.tileSize)+39);
                symulacja.setLocationRelativeTo(null);
                symulacja.setVisible(true);
                map.startSimulationThread();
            } catch (NumberFormatException g) {
                System.out.println("To nie jest liczba!");
                return;
            }
        }
    }
}