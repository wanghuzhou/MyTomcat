package com.wanghz.mytomcat;

import java.io.IOException;

public class FindGirlServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        doPost(myRequest, myResponse);
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post girl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
