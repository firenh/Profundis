package fireopal.profundis.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MaterialRulesMixinHelper {
    public static <T> List<T> combine(List<T> list, Collection<T> add) {
        List<T> newList = new ArrayList<>(list);
        newList.addAll(add);
        return newList;
    }
}
