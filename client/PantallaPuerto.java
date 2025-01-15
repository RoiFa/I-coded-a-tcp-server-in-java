package client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PantallaPuerto extends JFrame {

    public PantallaPuerto() {
        Toolkit mipantalla = Toolkit.getDefaultToolkit();

        Dimension tamanoPantalla = mipantalla.getScreenSize();

        int alturaPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;
        setSize(anchoPantalla/2, alturaPantalla/2);
        setLocation(anchoPantalla/4, alturaPantalla/4);
        setMinimumSize(new Dimension(anchoPantalla/2, alturaPantalla/2));
        setTitle("Calculadora");
        setContentPane(new Pantalla());
    }

    private class Pantalla extends JPanel{

        public Pantalla() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel text = new JLabel("Puerto de la conexión:");
            JTextField algo = new JTextField();
            algo.setSize(100, 500);
            algo.setPreferredSize(new Dimension(100, 25));
            JButton accept = new JButton("Aceptar");
            accept.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SocketTCPClient cliente = new SocketTCPClient("127.0.0.1", Integer.parseInt(algo.getText()));
                    try {
                        cliente.start();
                        cliente.getOs().write(100);
                        System.out.println("Mensaje del servidor:" + cliente.getIs().read());
                        cliente.stop();
                    } catch (UnknownHostException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
            });
            this.add(text);
            this.add(algo);
            this.add(accept);
        }
    }
}