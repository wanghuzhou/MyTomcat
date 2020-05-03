package com.wanghz.mytomcat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketThread implements Runnable{
    @Override
    public void run() {
        /*Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        com.wanghz.mytomcat.MyRequest myRequest = new com.wanghz.mytomcat.MyRequest(inputStream);
        com.wanghz.mytomcat.MyResponse myResponse = new com.wanghz.mytomcat.MyResponse(outputStream);

        dispatch(myRequest, myResponse);
        socket.close();*/
    }
}
