package client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PantallaComunicacion extends JFrame {

    private SocketTCPClient client;
    private PrintWriter pw;
    
    
    public PantallaComunicacion(SocketTCPClient client) {
        this.client = client;
        this.pw = new PrintWriter(this.client.getOs());
        this.setTitle("--- 1 - " + client.getName() + " ---");
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

    private class Pantalla extends JPanel {
        
        public Pantalla() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JTextArea history = new JTextArea();

            JPanel chooseClient = new JPanel(new FlowLayout());
            JPanel sendMessage = new JPanel(new FlowLayout());

            JLabel destinatario = new JLabel("Destinatario");
            JComboBox<String> destinatarioList = new JComboBox<String>();
            destinatarioList.addItem("1 - " + client.getName());

            JTextField message = new JTextField(50);
            JButton send = new JButton("Enviar");
            send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = message.getText();
                    pw.print(text + "|@|" + client.getName() + "|@|" + String.valueOf(destinatarioList.getSelectedItem()));
                    history.setText(history.getText() + "## TO -> " + String.valueOf(destinatarioList.getSelectedItem()) + " ##\n" + text + "\n");
                    pw.flush();
                }
            });

            chooseClient.add(destinatario);
            chooseClient.add(destinatarioList);

            sendMessage.add(message);
            sendMessage.add(send);

            this.add(history);
            this.add(chooseClient);
            this.add(sendMessage);
        }
    }

    public void receiveText(String text) {
        System.out.println(text);
    }

}
