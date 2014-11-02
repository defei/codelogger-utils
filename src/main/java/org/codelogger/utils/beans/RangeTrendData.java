package org.codelogger.utils.beans;

import java.util.LinkedHashMap;

/**
 * 按区间范围增涨存储数据
 *
 * @param <T> 任何类型
 * @author DengDefei
 */
@SuppressWarnings("rawtypes")
public class RangeTrendData <T extends Comparable<T>> extends LinkedHashMap<Range, T> {

  /**
   *
   */
  private static final long serialVersionUID = -4875793436370785297L;

}
