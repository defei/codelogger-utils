package org.codelogger.utils.bean;

import java.util.Date;

import org.codelogger.utils.beans.Range;
import org.codelogger.utils.beans.RangeTrendData;
import org.junit.Test;

public class RangeTrendDataTest {

    @Test
    public void build() {

        Date date = new Date();
        RangeTrendData<Long> rangeTrendData = new RangeTrendData<Long>();
        Range<Date> dateRangekey = Range.getInstance(date, date);
        rangeTrendData.put(dateRangekey, 1L);
        RangeTrendData<Integer> rangeTrendData2 = new RangeTrendData<Integer>();
        Range<Long> longRangeKey = Range.getInstance(1L, 2L);
        rangeTrendData2.put(longRangeKey, 1);
    }
}
