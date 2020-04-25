import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author  MySelf
 * @create  2018/9/29
 * @desc 客户端接入监听类
 **/
public class ClientHandler {

    /** 数据连接值 */
    public static final int MAX_DATA_LEN = 1024;
    private final Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void start(){
        System.out.println("新客户端接入");
        new Thread(() -> doStart()).start();
    }

    /**
     * 对客户端的业务处理，接收并重写回去
     */
    private void doStart(){
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            MyRequest myRequest = new MyRequest(inputStream);
            MyResponse myResponse = new MyResponse(outputStream);

            dispatch(myRequest,myResponse);
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = MyTomcat.urlServletMap.get(myRequest.getUrl());
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

}
