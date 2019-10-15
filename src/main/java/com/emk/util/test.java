package com.emk.util;

import org.jeecgframework.core.util.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by panjs008 on 2019-08-10.
 */
public class test {
    public static void main(String[] args) {
        int year = 2017;
        int month = 6;//月份从0开始,10代表11月份
        Calendar calendar = new GregorianCalendar(year, month, 1);
        int i = 1;
        while (calendar.get(Calendar.MONTH) < month + 1) {
            calendar.set(Calendar.WEEK_OF_YEAR, i++);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            if (calendar.get(Calendar.MONTH) == month) {
                System.out.printf("星期天：%tF%n", calendar);
            }
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            if (calendar.get(Calendar.MONTH) == month) {
                System.out.printf("星期六：%tF%n", calendar);
            }
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2019); // 2010年

        c.set(Calendar.YEAR, 2019); // 2010年
        c.set(Calendar.MONTH, 9); // 6 月
        System.out.println("------------" + c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月的天数和周数-------------");
        System.out.println("天数：" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("周数：" + c.getActualMaximum(Calendar.WEEK_OF_MONTH));

        System.out.println(Integer.parseInt("2019-07".toString().substring(0,4)));
        try {
            Calendar cal1 = DateUtils.parseCalendar("2019-07-01 07:10","yyyy-MM-dd HH:mm");
            Calendar cal2 = DateUtils.parseCalendar("2019-07-01 07:56","yyyy-MM-dd HH:mm");
            int m = DateUtils.dateDiff('m',cal2,cal1);
            System.out.println(m);
            String s1 = "2019-07-01 07:10";
            String s2 = "2019-07-01 08:10";
            String s3 = "2019-07-01 09:10";
           System.out.println(String.format("%02d", 1));
        } catch (ParseException e) {
            e.printStackTrace();
        };

        c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2019); // 2010年
        c.set(Calendar.MONTH, 0); // 2010年
        System.out.print("sdf:"+c.getActualMaximum(Calendar.DAY_OF_MONTH));
    }
}
