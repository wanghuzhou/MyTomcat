package com.wanghz.mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Tomcat启动类
 */
public class MyTomcat {
    //端口
    private int port;

    ServerSocket serverSocket = null;

    // servlet
    public static final Map<String, String> urlServletMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServletMapping();

        try {

            System.out.println("com.wanghz.mytomcat.MyTomcat is start...");

            while (true) {
                Socket socket = serverSocket.accept();
                /*InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                com.wanghz.mytomcat.MyRequest myRequest = new com.wanghz.mytomcat.MyRequest(inputStream);
                com.wanghz.mytomcat.MyResponse myResponse = new com.wanghz.mytomcat.MyResponse(outputStream);

                dispatch(myRequest, myResponse);
                socket.close();*/
                new Thread(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();

                        MyRequest myRequest = new MyRequest(inputStream);
                        MyResponse myResponse = new MyResponse(outputStream);

                        dispatch(myRequest, myResponse);
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        new Thread(() -> doStart()).start();
    }

    private void doStart() {
        System.out.println("com.wanghz.mytomcat.MyTomcat is start...");
        while (true){
            try {
                Socket client = serverSocket.accept();
                new ClientHandler(client).start();
            }catch (IOException e){
                System.out.println("服务端异常");
            }
        }
    }

    /**
     * 初始化servletMapping
     */
    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    /**
     * 请求分发
     *
     * @param myRequest  请求
     * @param myResponse 响应
     */
    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = urlServletMap.get(myRequest.getUrl());
        try {
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = myServletClass.newInstance();
            myServlet.service(myRequest, myResponse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }
}
