package server;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Dimension;

class InterfazServer extends JFrame{
    public InterfazServer(){
        super();
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
        panel.setBorder(new EmptyBorder(20,90,110,30));

        JPanel panel2 = new JPanel();
        panel2.setSize(300,50);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.setBorder(new EmptyBorder(10,0,0,0));

        
        JLabel text = new JLabel();
        text.setSize(200,100);
        text.setText("Puerto de la conexión:");
        JTextField puerto = new JTextField();
        puerto.setSize(100, 50);
        JButton cancel = new JButton();
        cancel.setText("Cancelar");
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt){
                JButton btn = (JButton) evt.getSource();
                try {
                    close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        JButton accept = new JButton();
        accept.setText("Aceptar");
        accept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt){
                JButton btn = (JButton) evt.getSource();
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        panel.add(text);
        panel.add(puerto);
        panel.add(panel2);
        panel2.add(cancel);
        panel2.add(accept);
    }

    public void close(){
        this.dispose();
    }
}