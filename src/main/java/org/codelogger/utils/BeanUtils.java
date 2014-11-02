package org.codelogger.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.codelogger.utils.exceptions.BeanInstantiationException;
import org.codelogger.utils.exceptions.FatalBeanException;

/**
 * 处理JavaBean的工具类。 A useful tool to handle beans.
 * 
 * @author DengDefei
 * 
 */
public abstract class BeanUtils {

    private static final String SOURCE_IS_NULL_MESSAGE = "The source can not be null.";

    private static final String TARGETIS_NULL_MESSAGE = "The target can not be null.";

    private static final String CAN_NOT_COPY_PROPERTIES = "Could not copy properties from source to target";

    private static final String NO_DEFAULT_CONSTRUCTOR_FOUND = "No default constructor found";

    private static final Class<?>[] EMPTY_PARAMETER_CLASSTYPES = {};

    private static final Object[] EMPTY_PARAMETER_VALUES = {};

    /**
     * 浅克隆。Return a shadow cloned object.
     * 
     * @param source
     *            源对象。source object
     * @return 新对象，包含源对象的所有非final数据。a new object with shadow cloned.
     * @throws BeanInstantiationException
     */
    public static <T> T clone(final T source) throws BeanInstantiationException {

        if (source == null) {
            return null;
        }
        Class<T> sourceClass = ClassUtils.getClass(source);
        try {
            if (sourceClass.isArray()) {
                Class<?> componentType = sourceClass.getComponentType();
                Object[] array = ArrayUtils.toArray(source,
                        ClassUtils.getWrapperClass(componentType));
                @SuppressWarnings("unchecked")
                T newInstance = (T) Array.newInstance(ClassUtils.getPrimitiveClass(componentType),
                        Array.getLength(source));
                for (int i = 0; i < Array.getLength(source); i++) {
                    Array.set(newInstance, i, array[i]);
                }
                return newInstance;
            }
            if (ClassUtils.isDataClass(sourceClass)) {
                T newInstance = newInstance(sourceClass, ClassUtils.getPrimitiveClass(source),
                        source);
                copyProperties(source, newInstance);
                return newInstance;
            }
            T newInstance = sourceClass.newInstance();
            Field[] fields = sourceClass.getDeclaredFields();
            for (Field field : fields) {
                getAndSetValue(source, field, newInstance, field);
            }
            return newInstance;
        } catch (Exception e) {
            throw new BeanInstantiationException(sourceClass, NO_DEFAULT_CONSTRUCTOR_FOUND, e);
        }
    }

    /**
     * 把相同名称和类型的字段从源对象中复制到目标对象中去(目标对象中final字段除外)。 Copy same name and type field
     * value from source to target(except target final field).
     * 
     * @param source
     *            源对象。source object.
     * @param target
     *            目标对象。target object.
     */
    public static void copyProperties(final Object source, final Object target) {

        ExceptionUtils.iae.throwIfNull(source, SOURCE_IS_NULL_MESSAGE);
        ExceptionUtils.iae.throwIfNull(target, TARGETIS_NULL_MESSAGE);
        Class<Object> sourceClass = ClassUtils.getClass(source);
        Class<Object> targetClass = ClassUtils.getClass(target);
        Field[] sourceFields = sourceClass.getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();
        try {
            for (Field sourceField : sourceFields) {
                Field targetField = getFieldByField(targetFields, sourceField);
                if (targetField != null) {
                    getAndSetValue(source, sourceField, target, targetField);
                }
            }
        } catch (Exception e) {
            throw new FatalBeanException(CAN_NOT_COPY_PROPERTIES, e);
        }
    }

    /**
     * 返回一个给定类型的新实例。 Returns a newly allocated instance of the class represented
     * by this class.
     * 
     * @param clazz
     *            想要实例化的类型。want to create instance class.
     * @return 给定类型的新实例。a newly allocated instance of the class represented by
     *         this class.
     */
    public static <T> T newInstance(final Class<T> clazz) {

        return ClassUtils.instantiateClass(clazz);
    }

    /**
     * 根据给定实例化参数类型和数据实例化指定类型的对象。 Returns a newly allocated instance of the class
     * represented by this class use given constructor parameters class type and
     * constructor parameters value.
     * 
     * @param clazz
     *            想要实例化的类型。want to create instance class.
     * @param fieldType
     *            构造函数的唯一参数类型。constructor single parameter class type.
     * @param value
     *            构造函数的唯一参数数据。constructor single parameter value.
     * @return 实例化对象。a newly allocated instance of the class represented by this
     *         class.
     */
    public static <T> T newInstance(final Class<T> clazz, final Class<?> fieldType,
            final Object value) {

        return ClassUtils.instantiateClass(clazz, getParameterTypes(fieldType),
                getParameterValues(value));
    }

    /**
     * 根据给定的多个实例化参数类型和对应数据实例化指定类型的对象。Returns a newly allocated instance of the
     * class represented by this class use given constructor parameters class
     * type and constructor parameters value.
     * 
     * @param clazz
     *            想要实例化的类型。want to create instance class.
     * @param fieldTypes
     *            、构造函数的参数类型的数组。constructor parameters class type.
     * @param values
     *            、构造函数的参数数值的数组。constructor parameters value.
     * @return 实例化对象。a newly allocated instance of the class represented by this
     *         class.
     */
    public static <T> T newInstance(final Class<T> clazz, final Class<?>[] fieldTypes,
            final Object[] values) {

        return ClassUtils.instantiateClass(clazz, getParameterTypes(fieldTypes),
                getParameterValues(values));
    }

    private static Class<?>[] getParameterTypes(final Class<?>... parameterTypes) {

        if (ArrayUtils.isEmpty(parameterTypes)) {
            return EMPTY_PARAMETER_CLASSTYPES;
        }
        Class<?>[] parameterTypeArray = new Class<?>[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameterTypeArray[i] = parameterTypes[i];
        }
        return parameterTypeArray;
    }

    private static Object[] getParameterValues(final Object... parameterValues) {

        if (ArrayUtils.isEmpty(parameterValues)) {
            return EMPTY_PARAMETER_VALUES;
        }
        Object[] parameterValueArray = new Object[parameterValues.length];
        for (int i = 0; i < parameterValues.length; i++) {
            parameterValueArray[i] = parameterValues[i];
        }
        return parameterValueArray;
    }

    private static void getAndSetValue(final Object source, final Field sourceField,
            final Object target, final Field targetField) throws IllegalAccessException {

        if (Modifier.isFinal(targetField.getModifiers())) {
            return;
        }
        sourceField.setAccessible(true);
        Object value = sourceField.get(source);
        targetField.setAccessible(true);
        targetField.set(target, value);
    }

    private static Field getFieldByField(final Field[] fields, final Field target) {

        for (Field field : fields) {

            String fieldName = field.getName();
            String targetFieldName = target.getName();
            Class<?> fieldType = field.getType();
            Class<?> targetType = target.getType();
            if (fieldName.equals(targetFieldName) && fieldType.equals(targetType)) {
                return field;
            }
        }
        return null;
    }

}
