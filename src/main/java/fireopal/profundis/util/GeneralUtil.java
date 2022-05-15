package fireopal.profundis.util;

import java.util.ArrayList;
import java.util.List;

public class GeneralUtil {
    public static <E> List<E> combineLists(List<E> list1, List<E> list2) {
        List<E> newList = new ArrayList<>();
        newList.addAll(list1);
        newList.addAll(list2);
        return newList;
    }
}
