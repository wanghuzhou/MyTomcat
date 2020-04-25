import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("hello", "/hello", "HelloWorldServlet"));
        servletMappingList.add(new ServletMapping("girl", "/girl", "FindGirlServlet"));
    }
}
