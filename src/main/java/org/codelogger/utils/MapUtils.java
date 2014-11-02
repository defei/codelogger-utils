package org.codelogger.utils;

import java.util.List;
import java.util.Map;

public class MapUtils {

    /**
     * Returns true if given map is null or empty; false otherwise.
     * 
     * @param map
     *            the map to be tested.
     * @return true if the map is null or empty; false otherwise.
     */
    public static boolean isEmpty(final Map<?, ?> map) {

        return map == null || map.isEmpty();
    }

    /**
     * Returns true if the map is both not null and not empty; false otherwise.
     * 
     * @param map
     *            the map to be tested.
     * @return true if the map is both not null and not empty; false otherwise.
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {

        return !isEmpty(map);
    }

    public static <KT, VT> VT[] getMapValuesOrderByKey(final Map<KT, VT> valuesMap,
            final List<KT> keys, final VT defaultValue) {

        VT[] values = null;
        Class<VT> valueType = null;
        if (defaultValue != null) {
            @SuppressWarnings("unchecked")
            Class<VT> type = (Class<VT>) defaultValue.getClass();
            valueType = type;
        } else {
            VT firstNotNullValue = CollectionUtils.getFirstNotNullValue(valuesMap.values());
            if (firstNotNullValue != null) {
                @SuppressWarnings("unchecked")
                Class<VT> type = (Class<VT>) firstNotNullValue.getClass();
                valueType = type;
            } else {
                return values;
            }
        }
        if (keys != null) {
            values = ArrayUtils.buildArray(valueType, keys.size(), defaultValue);
            for (int i = 0; i < keys.size(); i++) {
                VT value = valuesMap.get(keys.get(i));
                if (value != null) {
                    values[i] = value;
                }
            }
        }
        return values;
    }
}
