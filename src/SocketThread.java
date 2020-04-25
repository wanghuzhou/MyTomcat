import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketThread implements Runnable{
    @Override
    public void run() {
        /*Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        MyRequest myRequest = new MyRequest(inputStream);
        MyResponse myResponse = new MyResponse(outputStream);

        dispatch(myRequest, myResponse);
        socket.close();*/
    }
}
