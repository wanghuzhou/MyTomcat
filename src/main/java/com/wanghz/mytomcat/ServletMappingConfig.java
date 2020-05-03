package com.wanghz.mytomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("hello", "/hello", "com.wanghz.mytomcat.HelloWorldServlet"));
        servletMappingList.add(new ServletMapping("girl", "/girl", "com.wanghz.mytomcat.FindGirlServlet"));
    }
}
