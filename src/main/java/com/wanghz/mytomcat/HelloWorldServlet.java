package com.wanghz.mytomcat;

import java.io.IOException;

public class HelloWorldServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        doPost(myRequest, myResponse);
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
