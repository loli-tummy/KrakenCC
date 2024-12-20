package pictures.cunny.kraken;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StringUtils {
    public static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final List<String> alphabet =
            Arrays.stream("qwertyuiopasdfghjklzxcvbnm1234567890/._-".split("")).toList();

    public static String randomText(int amount) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < amount; i++) {
            str.append(CollectionUtils.random(alphabet));
        }

        return str.toString();
    }
}
