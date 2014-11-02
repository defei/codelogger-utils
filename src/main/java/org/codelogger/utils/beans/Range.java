package org.codelogger.utils.beans;

import java.io.Serializable;

import org.codelogger.utils.exceptions.InvalidRangeException;

/**
 * 用来存储区间范围,如:
 * <p>
 * 日期：2012-01-01 - 2012-12-31, 2013-01-01 - 2013-12-31</br> 数字：1 - 2, 3- 4
 * </p>
 * 
 * @author DengDefei
 * 
 * @param <T>
 *            任何实现了Comparable接口的类型
 */
public class Range<T> implements Serializable, Comparable<Range<T>> {

    private static final long serialVersionUID = 9044178218752272802L;

    private final Comparable<T> lowerBound;

    private final Comparable<T> upperBound;

    private static final String ERROR_MESSAGE = "Invalid range definition!";

    public Range(final Comparable<T> lowerBound, final Comparable<T> upperBound) {

        if (lowerBound == null || upperBound == null) {
            throw buildException(lowerBound, upperBound);
        }
        @SuppressWarnings("unchecked")
        T endObj = (T) upperBound;
        if (lowerBound.compareTo(endObj) > 0)
            throw buildException(lowerBound, upperBound);

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public static <T> Range<T> getInstance(final Comparable<T> lowerBound,
            final Comparable<T> upperBound) {

        return new Range<T>(lowerBound, upperBound);
    }

    /**
     * 判断给定的值是否在当前范围内。
     * 
     * @param value
     *            用于测试的值
     * @return 给定的值在当前范围内则返回true，反之则为false。
     */
    public boolean includes(final Comparable<T> value) {

        @SuppressWarnings("unchecked")
        T lowerBound = (T) this.lowerBound;
        @SuppressWarnings("unchecked")
        T upperBound = (T) this.upperBound;
        return (value.compareTo(lowerBound) >= 0) && (value.compareTo(upperBound) <= 0);
    }

    private InvalidRangeException buildException(final Comparable<?> lowerBound,
            final Comparable<?> upperBound) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ERROR_MESSAGE);
        stringBuilder.append(" [lowerBound:");
        stringBuilder.append(lowerBound);
        stringBuilder.append(",upperBound:");
        stringBuilder.append(upperBound);
        stringBuilder.append("] cannot both null and lowerBound can not after upperBound.");
        return new InvalidRangeException(stringBuilder.toString());
    }

    @Override
    public int hashCode() {

        int result = 1;
        result = 31 * result + (this.upperBound != null ? this.upperBound.hashCode() : 0);
        result = 31 * result + (this.lowerBound != null ? this.lowerBound.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Range) {
            @SuppressWarnings("unchecked")
            Range<T> other = (Range<T>) obj;
            return lowerBound.equals(other.getLowerBound())
                    && upperBound.equals(other.getUpperBound());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(lowerBound.toString());
        stringBuilder.append(" - ");
        stringBuilder.append(upperBound.toString());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int compareTo(final Range<T> otherRange) {

        T otherLowerBound = otherRange.getLowerBound();
        int lowerCompare = lowerBound.compareTo(otherLowerBound);
        if (lowerCompare != 0) {
            return lowerCompare;
        } else {
            T otherUpperBound = otherRange.getUpperBound();
            return upperBound.compareTo(otherUpperBound);
        }
    }

    public T getLowerBound() {

        @SuppressWarnings("unchecked")
        T lowerBound = (T) this.lowerBound;
        return lowerBound;
    }

    public T getUpperBound() {

        @SuppressWarnings("unchecked")
        T upperBound = (T) this.upperBound;
        return upperBound;
    }
}