package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {
    private ServerSocket serverSocket;
    private Socket socket;
    InputStream is;
    OutputStream os;

    public SocketTCPServer (int puerto) throws IOException {
        serverSocket = new ServerSocket (puerto);
    }
    public void start() throws IOException {
        System.out.println("(Servidor) Esperando conexiones...");
        socket = serverSocket.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(Servidor) Conexión establecida.");
    }
    public void stop() throws IOException {
        System.out.println(" (Servidor) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        serverSocket.close();
        System.out.println(" (Servidor) Conexiones cerradas.");
    }

    public static void main(String[] args) {
        InterfazServer serv = new InterfazServer();
        serv.show(true);
    }
}
