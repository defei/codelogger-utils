package org.codelogger.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A useful tools to handle collection, likes judge empty, not empty, get first
 * or null, to list, to set, to array and so on...
 * 
 * @author DengDefei
 */
public class CollectionUtils {

    private CollectionUtils() {

    }

    /**
     * Returns true if the collection is implements Collection; false otherwise.
     * 
     * @param collection
     *            the collection to be tested.
     * @return true if the collection is implements Collection; false otherwise.
     */
    public static boolean isCollection(final Object collection) {

        return collection instanceof Collection;
    }

    /**
     * Returns true if the collection is null or empty; false otherwise.
     * 
     * @param collection
     *            the collection to be tested.
     * @return true if the collection is null or empty; false otherwise.
     */
    public static boolean isEmpty(final Collection<?> collection) {

        return collection == null || collection.isEmpty();
    }

    /**
     * Returns true if the collection is both not null and not empty; false
     * otherwise.
     * 
     * @param collection
     *            the collection to be tested.
     * @return true if the collection is both not null and not empty; false
     *         otherwise.
     */
    public static boolean isNotEmpty(final Collection<?> collection) {

        return !isEmpty(collection);
    }

    /**
     * Return either the first element when the collection is not null and empty
     * or null.
     * 
     * @param collection
     *            the collection to handle.
     * @return the first element when the collection is not null and empty or
     *         null.
     */
    public static <T> T getFirstOrNull(final Collection<T> collection) {

        if (isEmpty(collection)) {
            return null;
        }
        if (collection instanceof List) {
            return ((List<T>) collection).get(0);
        } else {
            return collection.iterator().next();
        }
    }

    /**
     * Returns the first not null element if the collection is not null and have
     * not null value, else return null.
     * 
     * @param collection
     *            the collection to be handled.
     * @return the first not null element if the collection is not null and have
     *         not null value, else null.
     */
    public static <T> T getFirstNotNullValue(final Collection<T> collection) {

        if (isNotEmpty(collection)) {
            for (T element : collection) {
                if (element != null) {
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * Convert given collection to a list.
     * 
     * @param collection
     *            source collection.
     * @return a list has full given collection element and order by collection
     *         index.
     */
    public static <T> List<T> toList(final Collection<T> collection) {

        if (isEmpty(collection)) {
            return new ArrayList<T>(0);
        } else {
            return new ArrayList<T>(collection);
        }
    }

    /**
     * Convert given collection to a set.
     * 
     * @param collection
     *            source collection.
     * @return a set has all unique element of given collection.
     */
    public static <T> Set<T> toSet(final Collection<T> collection) {

        if (isEmpty(collection)) {
            return new HashSet<T>(0);
        } else {
            return new HashSet<T>(collection);
        }
    }

    /**
     * Convert given collection to an array.
     * 
     * @param collection
     *            source collection.
     * @return an array has full given collection element and order by
     *         collection index.
     */
    public static <T> T[] toArray(final Collection<T> collection) {

        T next = getFirstNotNullValue(collection);
        if (next != null) {
            Object[] objects = collection.toArray();
            @SuppressWarnings("unchecked")
            T[] convertedObjects = (T[]) Array.newInstance(next.getClass(), objects.length);
            System.arraycopy(objects, 0, convertedObjects, 0, objects.length);
            return convertedObjects;
        } else {
            return null;
        }
    }

    /**
     * 将给定的集合打乱，并全部添加到一个新的List,此操作不会影响传入的对象.
     * 
     * @param collection
     *            任意的集体类型。
     * @return 一个无序的List.
     */
    public static <T> List<T> disorder(final Collection<T> collection) {

        List<T> result = new ArrayList<T>();
        if (isNotEmpty(collection)) {
            ArrayList<T> tempArray = new ArrayList<T>(collection);
            while (!tempArray.isEmpty()) {
                int index = MathUtils.randomInt(tempArray.size() - 1);
                result.add(tempArray.get(index));
                tempArray.remove(index);
            }
        }
        return result;
    }

    public static <T> T getLastElement(final Collection<T> collection) {

        if (isEmpty(collection)) {
            return null;
        }
        if (collection instanceof List) {
            List<T> list = (List<T>) collection;
            return list.get(list.size() - 1);
        } else {
            T last = null;
            for (T next : collection) {
                last = next;
            }
            return last;
        }
    }

    public static <T> int getCount(final Collection<T> collection) {

        return isNotEmpty(collection) ? collection.size() : 0;
    }

    /**
     * 按指定的分割符拼接集合里的元素。
     * 
     * @param collection
     *            任意集合。
     * @param separator
     *            分割符
     * @return 按指定的分割符拼接集合里的元素的字符串。
     */
    public static <T> String join(final Collection<T> collection, final String separator) {

        if (isEmpty(collection)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean isFirstVisited = true;
        for (Object object : collection) {
            if (isFirstVisited) {
                isFirstVisited = false;
            } else {
                stringBuffer.append(separator);
            }
            stringBuffer.append(ObjectUtils.toString(object));
        }
        return stringBuffer.toString();
    }

    /**
     * 按指定的分割符拼接集合里的元素。
     * 
     * @param collection
     *            任意集合。
     * @param separator
     *            分割符
     * @return 按指定的分割符拼接集合里的元素的字符串。
     */
    public static String join(final Object collection, final String separator) {

        Collection<?> convertedCollection = (Collection<?>) collection;
        return join(convertedCollection, separator);
    }
}
