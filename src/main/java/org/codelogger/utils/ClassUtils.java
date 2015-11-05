package org.codelogger.utils;

import static org.codelogger.utils.ArrayUtils.contains;

import java.lang.reflect.Constructor;

import org.codelogger.utils.exceptions.BeanInstantiationException;

/**
 * 处理对象类型的工具类。 A useful tools to handle class.
 *
 * @author DengDefei
 *
 */
public abstract class ClassUtils {

  private static final String NULL_STRING = "null";

  private static final String NO_DEFAULT_CONSTRUCTOR_FOUND = "No default constructor found";

  private static final String CLASS_IS_INTERFACE = "Given class is an interface";

  private static final Class<?>[] PRIMITIVE_CLASSES = { boolean.class, byte.class, short.class,
      char.class, int.class, float.class, long.class, double.class };

  private static final Class<?>[] WRAPPER_CLASSES = { Boolean.class, Byte.class, Short.class,
      Character.class, Integer.class, Float.class, Long.class, Double.class };

  private static final Class<?>[] DATA_PRIMITIVE_CLASS = { String.class };

  private ClassUtils() {

  }

  /**
   * 返回给定对象的类型。<br>
   * 如果给定对象为 null 则返回 null.</br> Returns given object class type.<br>
   * if given object is null return null.
   *
   * @param object 任何对象。 any object.
   * @return 给定对象的类型。given object class type.
   */
  public static <T> Class<T> getClass(final T object) {

    if (object == null) {
      return null;
    }
    @SuppressWarnings("unchecked")
    Class<T> clazz = (Class<T>) object.getClass();
    return clazz;
  }

  /**
   * 返回给定对象的组件类型，如List&lt;Long&gt;的组件类型为Long<br>
   * 如果给定对象不是集合或数组，则返回null。 Returns the given object component type.<br>
   * If given object does not represent an array ,this method returns null.
   *
   *
   * @param object 任何对象。 any object
   * @return 给定对象的组件类型。given object component type.
   */
  public static Class<?> getComponentClass(final Object object) {

    if (object == null) {
      return null;
    }
    return object.getClass().getComponentType();
  }

  /**
   * 返回给定对象的封装类型或自身类型.<br>
   * 如果给定对象是基本数据类型，则返回其封装类型。<br>
   * 如果给定对象为 null 则返回 null. Returns given object wrapper class or self class.<br>
   * if given object class is primitive class then return wrapper class.<br>
   * if given object is null return null.
   *
   * @param object 任意对象。any object.
   * @return 给定对象的封装类型。given object's wrapper class.
   */
  public static Class<?> getWrapperClass(final Object object) {

    if (object == null) {
      return null;
    }
    return getWrapperClass(object.getClass());
  }

  /**
   * Returns given class wrapper class or self class.<br>
   * if given class is primitive class then return wrapper class.<br>
   * if given class is null return null.
   *
   * @param clazz any class.
   * @return given class's wrapper class.
   */
  public static Class<?> getWrapperClass(final Class<?> clazz) {

    if (clazz == null) {
      return null;
    }
    int index = ArrayUtils.indexOf(PRIMITIVE_CLASSES, clazz);
    if (index != ArrayUtils.INDEX_OF_NOT_FOUND) {
      return WRAPPER_CLASSES[index];
    }
    return clazz;
  }

  /**
   * Returns given object class primitive class or self class.<br>
   * if given object class is 8 wrapper class then return primitive class.<br>
   * if given object class is null return null.
   *
   * @param object object to be handle.
   * @return given object class primitive class or self class.
   */
  public static Class<?> getPrimitiveClass(final Object object) {

    if (object == null) {
      return null;
    }
    return getPrimitiveClass(object.getClass());
  }

  /**
   * Returns given class primitive class or self class.<br>
   * if given class is 8 wrapper class then return primitive class.<br>
   * if given class is null return null.
   *
   * @param clazz class to handle.
   * @return given class primitive class or self class.
   */
  public static Class<?> getPrimitiveClass(final Class<?> clazz) {

    if (clazz == null) {
      return null;
    }
    int index = ArrayUtils.indexOf(WRAPPER_CLASSES, clazz);
    if (index != ArrayUtils.INDEX_OF_NOT_FOUND) {
      return PRIMITIVE_CLASSES[index];
    }
    return clazz;
  }

  /**
   * Returns given object's full class name.<br>
   * e.g: <span style="color:blue">org.codelogger.utils.ClassUtils</span><br>
   * If given object is null return empty string.
   *
   * @param object any object.
   * @return given object's full class name.
   */
  public static String getClassName(final Object object) {

    return object != null ? object.getClass().getName() : NULL_STRING;
  }

  /**
   * Returns given class simple class name.<br>
   * e.g: given class is:<span style="color:blue">java.lang.Object</span>, then
   * return:<span style="color:blue">Object</span>
   *
   * @param object any object.
   * @return object simple class name.
   */
  public static String getSimpleClassName(final Object object) {

    return object != null ? object.getClass().getSimpleName() : NULL_STRING;
  }

  /**
   * Create and initialize a new instance of the given class by default
   * constructor.
   *
   * @param clazz can not be null.
   * @return a newly allocated instance of the class represented by this class.
   * @throws IllegalArgumentException if given class is null.
   * @throws BeanInstantiationException if given class no default constructor
   *           found.
   */
  public static <T> T instantiateClass(final Class<T> clazz) throws IllegalArgumentException,
    BeanInstantiationException {

    return instantiateClass(clazz, MethodUtils.EMPTY_PARAMETER_CLASSTYPES,
      MethodUtils.EMPTY_PARAMETER_VALUES);
  }

  /**
   * Create and initialize a new instance of the given class by given
   * parameterTypes and parameterValues.
   *
   * @param clazz can not be null.
   * @param parameterTypes constructor parameters class type array.
   * @param parameterValues constructor parameters value array.
   * @return a newly allocated instance of the class represented by this class.
   * @throws BeanInstantiationException if given class no given parameter types
   *           constructor found.
   */
  public static <T> T instantiateClass(final Class<T> clazz, final Class<?>[] parameterTypes,
    final Object[] parameterValues) throws BeanInstantiationException {

    if (clazz.isInterface()) {
      throw new BeanInstantiationException(clazz, CLASS_IS_INTERFACE);
    }
    try {
      Constructor<T> constructor = clazz.getConstructor(parameterTypes);
      T newInstance = constructor.newInstance(parameterValues);
      return newInstance;
    } catch (Exception e) {
      throw new BeanInstantiationException(clazz, NO_DEFAULT_CONSTRUCTOR_FOUND, e);
    }
  }

  /**
   * Returns true if the given object class is 8 primitive class or their
   * wrapper class,or String class,or null.
   *
   * @param object any object.
   * @return true if the given object class is 8 primitive class or their
   *         wrapper class,or String class,or null; false otherwise.
   */
  public static boolean isDataObject(final Object object) {

    if (object == null) {
      return true;
    }
    Class<?> clazz = object.getClass();
    return isDataClass(clazz);
  }

  /**
   * Returns true if the given class is 8 primitive class or their wrapper
   * class,or String class,or null.
   *
   * @param clazz any class.
   * @return true if the given class is 8 primitive class or their wrapper
   *         class,or String class,or null; false otherwise.
   */
  public static boolean isDataClass(final Class<?> clazz) {

    if (clazz == null || clazz.isPrimitive()) {
      return true;
    }
    boolean isWrapperClass = contains(WRAPPER_CLASSES, clazz);
    if (isWrapperClass) {
      return true;
    }
    boolean isDataClass = contains(DATA_PRIMITIVE_CLASS, clazz);
    if (isDataClass) {
      return true;
    }
    return false;
  }

  public static boolean classIs(final Object object, final Class<?> clazz) {

    if (object == clazz) {
      return true;
    }
    if (object == null || clazz == null) {
      return false;
    }
    return clazz.isAssignableFrom(object.getClass());
  }

  public static boolean componentClassIs(final Object object, final Class<?> clazz) {

    if (object == clazz) {
      return true;
    }
    if (object == null || clazz == null) {
      return false;
    }
    return clazz.isAssignableFrom(getComponentClass(object));
  }

}
