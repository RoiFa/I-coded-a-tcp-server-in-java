package client;

import java.awt.Dimension;
import java.awt.GridLayout;
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
        this.setTitle("Configuraciones de la comunicación");
        Toolkit mipantalla = Toolkit.getDefaultToolkit();

        Dimension tamanoPantalla = mipantalla.getScreenSize();

        int alturaPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(anchoPantalla/2, alturaPantalla/2);
        setLocation(anchoPantalla/4, alturaPantalla/4);
        setMinimumSize(new Dimension(anchoPantalla/2, alturaPantalla/2));
        setContentPane(new Pantalla());
    }

    protected void close() {
        this.dispose();
    }

    private class Pantalla extends JPanel{

        public Pantalla() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel info = new JPanel(new GridLayout(3, 2));
            JLabel textIP = new JLabel("IP del servidor:");
            JLabel textPort = new JLabel("Puerto de la conexión");
            JLabel textName = new JLabel("Escriba su nombre:");
            JTextField ip = new JTextField();
            JTextField port = new JTextField();
            JTextField name = new JTextField();
            JButton cancel = new JButton("Cancelar");
            JButton accept = new JButton("Aceptar");
            
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                }
            });

            accept.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SocketTCPClient cliente = new SocketTCPClient(ip.getText(), Integer.parseInt(port.getText()), name.getText());
                    try {
                        close();
                        cliente.connect();
                    } catch (UnknownHostException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            info.add(textIP);
            info.add(ip);
            info.add(textPort);
            info.add(port);
            info.add(textName);
            info.add(name);
            
            this.add(info);
            this.add(cancel);
            this.add(accept);
        }
    }
}