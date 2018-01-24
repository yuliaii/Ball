package ball;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 24.01.2018.
 */
public class Form {

    private JPanel panelMain;
    private JTextField rField;
    private JTextField xField;
    private JTextField yField;
    private JTextField dxField;
    private JTextField dyField;
    private JTextField tField;
    private JTextField dtField;
    private JTextField kField;
    private JTextArea logArea;
    private JButton goButton;


    public Form() {
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            go();
            }
        });
    }


    public void go() {

        float r = Float.parseFloat(rField.getText());
        float x = Float.parseFloat(xField.getText());
        float y = Float.parseFloat(yField.getText());
        float dx = Float.parseFloat(dxField.getText());
        float dy = Float.parseFloat(dyField.getText());
        float t = Float.parseFloat(tField.getText()) * 1000;
        float dt = Float.parseFloat(dtField.getText()) * 1000;
        float k = Float.parseFloat(kField.getText());
        float lBord = 0;
        float rBord = 15;
        float dtR = 20;
        float mult = dtR / 1000;
        float nextLogT = 0;
        int count = 1;
        logArea.setText("");

        for (int i = 0; i <= t; i += dtR) {
            if (nextLogT < 1) {
                nextLogT = dt;
                logArea.append(String.format("t%d: x = %.2f , y = %.2f , height = %.2f , speed = %.2f \n", count, x, y, y - r, Math.sqrt(dx * dx + dy * dy)));
                count++;
            }
            nextLogT -= dtR;
            dy -= mult * 9.8;
            x += dx * mult;
            y += dy * mult;

            if (y - r < 0) {
                y = r;
                dy *= -k;
                if (Math.abs(dy) < 0.01) {
                    dy = 0;
                }
            }

            if (x - r < lBord) {
                x = r;
                dx *= -k;
            }

            if (x + r > rBord) {
                x = rBord - r;
                dx *= -k;
            }
        }

    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Form().panelMain);
        frame.pack();
        frame.setVisible(true);


    }
}



