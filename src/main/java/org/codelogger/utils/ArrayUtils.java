package org.codelogger.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 一个用来处理Array的工具类。</br> A useful tools to handle array.
 *
 * @author DengDefei
 *
 */
public class ArrayUtils {

  /**
   * 未找到要检索的值
   */
  public static final int INDEX_OF_NOT_FOUND = -1;

  /**
   * 判定给定的数组是否为空。 </br> Returns true if the array is null or empty; false
   * otherwise.
   *
   * @param array 用来测试的数组。</br>the array to be tested.
   * @return 如果数组为null或长度为0则返回true，否则返回false。</br>true if the array is null or
   *         empty; false otherwise.
   */
  public static boolean isEmpty(final Object[] array) {

    return array == null || array.length == 0;
  }

  /**
   * 判定给定的数组是否为空。</br> Returns true if given object is an not null array and it
   * is length == 0; false otherwise.
   *
   * @param array 用来测试的数组。</br>the array to be tested.
   * @return 如果给定的是数组并且为null或长度为0则返回true，否则返回false。</br>true if given object is
   *         an array and it is null or length == 0; false otherwise.
   */
  public static boolean isEmpty(final Object array) {

    if (array == null) {
      return true;
    }
    boolean isArray = array.getClass().isArray();
    if (isArray) {
      int length = Array.getLength(array);
      return length == 0;
    }
    return isArray;
  }

  /**
   * 统计Array的长度
   *
   * @param array 数组
   * @return 数组的长度
   * @throws IllegalArgumentException 如果给定的数据不为array
   */
  public static int count(final Object array) {

    if (isArray(array)) {
      return Array.getLength(array);
    } else if (array != null) {
      throw new IllegalArgumentException("Given data is not an array.");
    } else {
      return 0;
    }
  }

  /**
   * 判定给定的数组是否不为空。</br> Return true if given array is both not null and not
   * empty;false otherwise.
   *
   * @param array 用来测试的数组。</br>the array to be tested.
   * @return 如果数组不为空且长度大于0则返回true，否则返回false。</br> true if given array is both
   *         not null and not empty;false otherwise.
   */
  public static boolean isNotEmpty(final Object[] array) {

    return !isEmpty(array);
  }

  /**
   * 判定给定的数组是否不为空。</br> Returns true if given object is an array,and not null
   * and length > 0; false otherwise.
   *
   * @param array 用来测试的数组。</br>the array to be tested.
   * @return 如果给定的是数组并且不为null且长度大于0则返回true，否则返回false。</br>true if given object
   *         is an array,and not null and length > 0; false otherwise.
   */
  public static boolean isNotEmpty(final Object array) {

    return !isEmpty(array);
  }

  /**
   * 判定是否为数组。Returns true if given object is an not null array; false otherwise.
   *
   * @param object 用来测试的数组。the array to be tested.
   * @return 如果给定的对象不为null且是数组类型则返回true，否则返回false。true if given object is an not
   *         null array; false otherwise.
   */
  public static boolean isArray(final Object object) {

    if (object == null) {
      return false;
    }
    return object.getClass().isArray();
  }

  /**
   * 判定指定的数组是否包含指定的元素。 Returns true if this array contains the given element;
   * false otherwise. More formally, returns true if and only if this list
   * contains at least one element e such that (o==null ? e==null :
   * o.equals(e)).
   *
   * @param array 用来测试的数组。the array to be tested.
   * @param element 用来测试的元素。the element to be tested.
   * @return 如果给定的数组不为空且包含至少一个指定的元素则返回true，否则返回false。 true if given array
   *         contains given element; false otherwise.
   */
  public static <T> boolean contains(final T[] array, final T element) {

    if (array == null) {
      return false;
    }
    int index = indexOf(array, element);
    return index != INDEX_OF_NOT_FOUND;
  }

  /**
   * 查找指定元素在给定数组中的索引。</br> Returns the index within given array of the first
   * occurrence of the specified element.<br>
   * If not found any array element equals specified, return <span
   * style="color:blue">-1</span>.
   *
   * @param array 一个不为null的数组。any not null array.
   * @param element 任何对象。any object.
   * @return 返回指定元素在给定的数组中的第一个索引。 <br>
   *         如果没找到则返回 <span style="color:blue">-1</span>.</br> the index within
   *         given array of the first occurrence of the specified element.
   */
  public static <T> int indexOf(final T[] array, final T element) {

    for (int i = 0; i < array.length; i++) {
      if (JudgeUtils.equals(array[i], element)) {
        return i;
      }
    }
    return INDEX_OF_NOT_FOUND;
  }

  /**
   * 添加指定元素到给定数组的末尾，并返回这个新的数组。</br> Append the given element to the end of given
   * array.
   *
   * @param array 源数组。
   * @param element 被添加的元素。
   * @return 一个新的数组，包含给定数组的所有元素和指定元素。</br>a new array
   */
  public static <T> T[] append(final T[] array, final T element) {

    Class<?> compType = getClassType(array, element);
    if (compType == null) {
      return null;
    }
    int newArrLength = array != null ? array.length + 1 : 1;
    @SuppressWarnings("unchecked")
    T[] newArray = (T[]) Array.newInstance(compType, newArrLength);
    if (array != null) {
      System.arraycopy(array, 0, newArray, 0, array.length);
    }
    newArray[newArray.length - 1] = element;
    return newArray;
  }

  private static <T> Class<?> getClassType(final T[] array, final T element) {

    Class<?> compType = null;
    if (array != null) {
      compType = array.getClass().getComponentType();
    } else if (element != null) {
      compType = element.getClass();
    }
    return compType;
  }

  /**
   * 添加指定数组到给定数组的末尾，并返回这个新的数组。Append the given array1 to the end of given
   * array0.
   *
   * @param array0 排在前面的源数组。
   * @param array1 排在后面的源数组。
   * @return 一个新的数组，包含给定的两个数组的全部元素。a new array
   */
  public static <T> T[] append(final T[] array0, final T[] array1) {

    Class<?> compType = null;
    if (array0 != null) {
      compType = array0.getClass().getComponentType();
    } else if (array1 != null) {
      compType = array1.getClass().getComponentType();
    } else {
      return null;
    }
    int arrayLength0 = array0 != null ? array0.length : 0;
    int arrayLength1 = array1 != null ? array1.length : 0;
    int newArrLength = arrayLength0 + arrayLength1 + 1;
    @SuppressWarnings("unchecked")
    T[] newArr = (T[]) Array.newInstance(compType, newArrLength);
    if (array0 != null) {
      System.arraycopy(array0, 0, newArr, 0, arrayLength0);
    }
    if (array1 != null) {
      System.arraycopy(array1, 0, newArr, arrayLength0, arrayLength1);
    }
    return newArr;
  }

  /**
   * 添加指定元素到给定数组的头位置，并返回这个新的数组。Append the given element to the start of the
   * given array.
   *
   * @param element 被添加的元素。
   * @param array 源数组。
   * @return 一个新的数组，包含给定数组的所有元素和指定元素。a new array
   */
  public static <T> T[] append(final T element, final T[] array) {

    Class<?> compType = getClassType(array, element);

    int newArrLength = array != null ? array.length + 1 : 1;
    @SuppressWarnings("unchecked")
    T[] newArr = (T[]) Array.newInstance(compType, newArrLength);
    newArr[0] = element;
    if (array != null) {
      System.arraycopy(array, 0, newArr, 1, array.length);
    }
    return newArr;
  }

  /**
   * 裁剪数组。Returns a new array if array is not empty and start greater or equals
   * 0 and less than array length; array self otherwise;
   *
   * @param array 源数组。array to be handed.
   * @param start 裁剪的起始位置的索引。start index number.
   * @return 一个新的数组，包含从指定索引开始到数组结束的所有元素。如果数组为空或起始索引小于0或大于数组本身索引长度，则返回数组本身。a new
   *         array if array is not empty and start greater or equals 0 and less
   *         than array length; array self otherwise;
   */
  public static <T> T[] subArray(final T[] array, final int start) {

    int end = array == null ? 0 : array.length - 1;
    return subArray(array, start, end);
  }

  /**
   * 裁剪数组。
   *
   * @param array 源数组。
   * @param start 裁剪的起始位置的索引。
   * @param end 裁剪的结束位置的索引。
   * @return 
   *         一个新的数组，包含从指定索引开始到指定索引结束的所有元素。如果数组为空或起始索引小于0，或起始索引大于结束索引，或结束大于数组本身索引长度
   *         ，则返回数组本身。
   */
  public static <T> T subArray(final T array, final int start, final int end) {

    if (isEmpty(array) || start < 0 || start > end || end >= Array.getLength(array)) {
      return array;
    }
    int newArraySize = end - start + 1;
    @SuppressWarnings("unchecked")
    T newArray = (T) buildArray(ClassUtils.getPrimitiveClass(ClassUtils.getComponentClass(array)),
      newArraySize, null);
    System.arraycopy(array, start, newArray, 0, newArraySize);
    return newArray;
  }

  /**
   * 裁剪数组。 Returns a new array if array is not empty and start greater or equals
   * 0 and less than end and end less than array length; array self otherwise;
   *
   *
   * @param array 源数组。array to be handed.
   * @param start 裁剪的起始位置的索引。start index number.
   * @param end 裁剪的结束位置的索引。end index number.
   * @return 
   *         一个新的数组，包含从指定索引开始到指定索引结束的所有元素。如果数组为空或起始索引小于0，或起始索引大于结束索引，或结束大于数组本身索引长度
   *         ，则返回数组本身。a new array if array is not empty and start greater or
   *         equals 0 and less than end and end less than array length; array
   *         self otherwise;
   */
  public static <T> T[] subArray(final T[] array, final int start, final int end) {

    if (isEmpty(array) || start < 0 || start > end || end >= array.length) {
      return array;
    }
    @SuppressWarnings("unchecked")
    Class<T> componentClass = (Class<T>) ClassUtils.getComponentClass(array);
    int newArraySize = end - start + 1;
    T[] newArray = buildArray(componentClass, newArraySize, null);
    System.arraycopy(array, start, newArray, 0, newArraySize);
    return newArray;
  }

  /**
   * 移出给定数组中的所有指定元素。Remove all given target element from given array.
   *
   * @param array 源数组。 source array.
   * @param target 想要被移除的元素。element want to remove.
   * @return 一个新的数组，包含给定数组中除指定元素外的所有元素。元素索引相对不变。a new array have removed all
   *         given target.
   */
  public static <T> T[] remove(final T[] array, final T target) {

    if (array == null) {
      return null;
    }
    int count = count(array, target);
    if (count > 0) {
      @SuppressWarnings("unchecked")
      T[] newArray = (T[]) buildArray(array.getClass().getComponentType(), array.length - count,
        null);
      int index = 0;
      for (T element : array) {
        if (JudgeUtils.notSame(element, target)) {
          newArray[index++] = element;
        }
      }
      return newArray;
    }
    return array;
  }

  /**
   * 统计数组中包含指定元素的个数。Count how many given target in given array.
   *
   * @param array 源数组。source array.
   * @param target 想要被统计的元素。element want to count.
   * @return 数组中包含指定元素的个数。the count of given target in given array.
   */
  public static <T> int count(final T[] array, final T target) {

    int count = 0;
    if (isNotEmpty(array)) {
      for (T element : array) {
        if (JudgeUtils.equals(element, target)) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * 新建数组。Returns a new given size length array instance by given type's wrapper
   * type use default value.
   *
   * @param type 数组封装类型。the array component type.
   * @param size 数组长度。want to build array length.
   * @param defaultValue 默认值。the default value of array element.
   * @return 一个按给定类型封装的指定长度数组，并按给定的默认值填充。a new array
   */
  public static <T> T[] buildArray(final Class<T> type, final int size, final T defaultValue) {

    Class<?> wrapperClass = ClassUtils.getWrapperClass(type);
    @SuppressWarnings("unchecked")
    T[] convertedArray = (T[]) Array.newInstance(wrapperClass, size);
    for (int i = 0; i < size; i++) {
      convertedArray[i] = defaultValue;
    }
    return convertedArray;
  }

  /**
   * 复制源数组的数数据到指定数组。如果给定的数组有为空的存在，则不执行任何操作。否则按给定数组中长度最小的长度来复制数据。
   *
   * @param source 源数组。
   * @param target 目标数组。
   */
  public static <T> void copy(final T source, final T target) {

    if (isEmpty(source) || isEmpty(target)) {
      return;
    }
    int sourceLength = Array.getLength(source);
    int targetLength = Array.getLength(target);
    int minLenght = Math.min(sourceLength, targetLength);
    copy(source, target, 0, 0, minLenght);
  }

  /**
   * 按指定的起始位置来复制源数组的数数据到指定数组。
   * <p>
   * 以下情况将不会执行任何操作：</br> 1.sourceStartIndex < 0 或 targetStartIndex < 0。</br>
   * 2.sourceStartIndex > source数组的长度，或 sourceStartIndex + copyLength >
   * source数组的长度。</br> 3.targetStartIndex > target数组的长度 或 targetStartIndex +
   * copyLength > target数组的长度。
   * </p>
   *
   * @param source 源数组。
   * @param target 目标数组。
   * @param sourceStartIndex 源数组的起始位置的索引。
   * @param targetStartIndex 目标数组的起始位置的索引。
   * @param copyLength 想要复制的元素个数。
   */
  public static <T> void copy(final T source, final T target, final int sourceStartIndex,
    final int targetStartIndex, final int copyLength) {

    int sourceLength = Array.getLength(source);
    int targetLength = Array.getLength(target);
    int minLenght = Math.min(sourceLength, targetLength) - 1;
    if (sourceStartIndex < 0 || targetStartIndex < 0 || sourceStartIndex + 1 > sourceLength
      || targetStartIndex + 1 > targetLength || copyLength > minLenght) {
      return;
    }
    int sourceIndex = 0;
    int targetIndex = 0;
    for (int index = 0; index < copyLength; index++) {
      Array.set(target, targetIndex++, Array.get(source, sourceIndex++));
    }
  }

  /**
   * 将集合类型转换为数组。
   *
   * @param collection 实现了Collection接口的对象。
   * @return 一个包含按传入对象元素顺序排序并包含所有元素的新数组。
   */
  public static <T> T[] toArray(final Collection<T> collection) {

    return CollectionUtils.toArray(collection);
  }

  /**
   * 将基本数据类型的数组转换成给定封装类型的数组。Convert primitive array to given wrapper type array.
   *
   * @param source 基本数据类型组成的数组。primitive array...
   * @param wrapperType 给定数组元素将封装成的类型。the array element type will covert to
   *          given wrapper type.
   * @return 一个新的封装类型的数组，并包含给定数组的所有元素。a new wrapper type array have full source
   *         element.
   */
  public static <T> T[] toArray(final Object source, final Class<T> wrapperType) {

    if (source != null) {
      int length = Array.getLength(source);
      T[] convertedArray = buildArray(wrapperType, length, null);
      for (int i = 0; i < length; i++) {
        @SuppressWarnings("unchecked")
        T element = (T) Array.get(source, i);
        convertedArray[i] = element;
      }
      return convertedArray;
    } else {
      return null;
    }
  }

  /**
   * 将给定数组转换成List。Convert given array to a list.
   *
   * @param array 源数组。source array.
   * @return 一个新的数组并包含按给定数组顺序的所有元素。a list has full given array element and order
   *         by array index.
   */
  public static <T> List<T> toList(final T[] array) {

    if (array != null) {
      List<T> list = new ArrayList<T>(array.length);
      for (T element : array) {
        list.add(element);
      }
      return list;
    } else {
      return new ArrayList<T>(0);
    }
  }

  /**
   * 将给定的基本数据数组转换为给定的封装类型的数组。Convert primitive array to given wrapper type list.
   *
   * @param array 源基本数据数组。source primitive array.
   * @param wrapperType 将要转换成的封装类型。the array element type will covert to given
   *          wrapper type.
   * @return 一个新的给定封装类型的数组并包含按给定数组顺序的所有元素。 a new list have full given array
   *         element.
   */
  public static <T> List<T> toList(final Object array, final Class<T> wrapperType) {

    T[] objectArray = toArray(array, wrapperType);
    return toList(objectArray);
  }

  /**
   * 将给定数组转换成Set。注意:相同hashCode的元素将只保留一个。Convert given array to a set.
   *
   * @param array 源数组。source array.
   * @return 一个保留所有唯一元素的Set。a set have all unique element of array.
   */
  public static <T> Set<T> toSet(final T[] array) {

    if (isNotEmpty(array)) {
      Set<T> set = new HashSet<T>(array.length);
      for (T element : array) {
        set.add(element);
      }
      return set;
    } else {
      return new HashSet<T>(0);
    }
  }

  /**
   * 将给定的基本数据类型的数组转换成给定封装数组类型的Set。注意:相同hashCode的元素将只保留一个。Convert primitive array
   * to given wrapper type set.
   *
   * @param array 源数组。 source array.
   * @param wrapperType 将要转换成的封装类型。the array element type will convert to given
   *          wrapper type.
   * @return 一个新的给定封装类型的Set并包含按给定数组的所有唯一元素。a new wrapper type set have all
   *         unique element of array.
   */
  public static <T> Set<T> toSet(final Object array, final Class<T> wrapperType) {

    T[] objectArray = toArray(array, wrapperType);
    return toSet(objectArray);
  }

  /**
   * 返回数组最后一个元素。如果数组为空，返回null.
   *
   * @param array 任意数组。
   * @return 数组最后一个元素。如果数组为空，返回null.
   */
  public static <T> T getLastElement(final T[] array) {

    return isNotEmpty(array) ? array[array.length - 1] : null;
  }

  /**
   * 按指定的分割符拼接数组里的元素。
   *
   * @param array 任意数组。
   * @param separator 分割符
   * @return 按指定的分割符拼接数组里的元素的字符串。
   */
  public static String join(final Object[] array, final String separator) {

    if (isEmpty(array)) {
      return "";
    }
    StringBuffer stringBuffer = new StringBuffer();
    boolean isFirstVisited = true;
    for (Object object : array) {
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
   * 按指定的分割符拼接数组里的元素。
   *
   * @param array 任意数组。
   * @param separator 分割符
   * @return 按指定的分割符拼接数组里的元素的字符串。
   */
  public static String join(final Object array, final String separator) {

    return join(toArray(array, ClassUtils.getComponentClass(array)), separator);
  }
}
