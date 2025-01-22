package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTCPClient {
    private String serverIP;
    private int serverPort;
    private String name;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public InputStream getIs() {
        return is;
    }

    public OutputStream getOs() {
        return os;
    }

    public String getName() {
        return name;
    }

    public SocketTCPClient (String serverIP, int serverPort, String name) { 
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.name = name;
    }

    public void connect() throws UnknownHostException, IOException {
        System.out.println("(Cliente) Estableciendo conexión...");
        try {
            socket = new Socket (serverIP, serverPort);
            os = socket.getOutputStream();
            is = socket.getInputStream();
            
            System.out.println("(Cliente) Conexión establecida.");
            PantallaComunicacion comunicacion = new PantallaComunicacion(this);
            comunicacion.setVisible(true);
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                System.out.println("Leyendo...");
                System.out.println(br.readLine());
            }
        } catch (ConnectException e) {
            System.out.println("(Cliente) Conexión rehusada");
        }
    }
    
    public void stop() throws IOException {
        System.out.println("(Cliente) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(Cliente) Conexiones cerradas.");
    }

    public static void main(String[] args) {
        PantallaPuerto pedirPuerto = new PantallaPuerto();
        pedirPuerto.setVisible(true);
    }
}