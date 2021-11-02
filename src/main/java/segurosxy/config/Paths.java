package segurosxy.config;

public class Paths {
    public static final String INDEX = "/";
    public static final String Numero = "/numeros";

    public static final String ID_PARAM = ":id";

    public static String formatPostLocation(String id) {
        return String.format("%s/%s", Numero, id);
    }
}
