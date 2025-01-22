package server;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class LogScreen extends JFrame implements Runnable{
    JTextArea puerto;
    int port;

    public LogScreen(int port){
        super();
        this.port=port;
        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla=mipantalla.getScreenSize();
        int alturaPantalla=tamanoPantalla.height;
        int anchoPantalla=tamanoPantalla.width;
        
        setSize(300,150);
        setLocation(anchoPantalla/4,alturaPantalla/4);
        setVisible(true);
        setTitle("Configuraciones de la comunicación");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(0,90,110,30));

        JPanel panel2 = new JPanel();
        panel2.setSize(300,50);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.setBorder(new EmptyBorder(0,0,0,0));

        
        JLabel text = new JLabel();
        text.setSize(200,100);
        text.setText("Log de servidor:");
        puerto = new JTextArea();
        puerto.setSize(100, 50);
        puerto.setText("Inicializando el servidor...");

        panel.add(text);
        panel.add(puerto);
        panel.add(panel2);
    }

    public void conect(int port){
        try {
            SocketTCPServer servidor = new SocketTCPServer(port);
            servidor.start();
            puerto.setText("Inicializando el servidor...[Ok]");
            System.out.println("coño");
        } catch (IOException e) {
            e.printStackTrace();
            puerto.setText("Inicializando el servidor...[Err]");
        }
    }

    @Override
    public void run() {
        conect(port);
    }
}