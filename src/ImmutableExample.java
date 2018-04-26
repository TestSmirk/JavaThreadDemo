import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        unmodifiableMap.put("a",1);
    }
}
