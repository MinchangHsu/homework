package com.caster.homework;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CasterTest {
    public static void main(String[] args) throws ParseException {

        System.out.println(String.valueOf(Math.random() * 10000));
        BigDecimal rate = new BigDecimal(Math.random() * 1000000);
        System.out.println(rate.setScale(4, RoundingMode.HALF_UP));

        DecimalFormat df = new DecimalFormat("#,###.####");
        System.out.println(df.format(rate.doubleValue()));

        // Mar 30, 2022 12:55:00 UTC  UTC表示世界協調時間(Coordinated Universal Time) (正在使用)
        // 2022-03-30T12:55:00+00:00
        // Mar 30, 2022 at 13:55 BST   BST表示英國夏令時間(British Summer Time)

//
//        String dateStr = "Mar 30, 2022 12:55:01 UTC";
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss Z", Locale.US);
//        sdf.setTimeZone((TimeZone.getTimeZone("UTC")));
//        Date d = sdf.parse(dateStr);
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
//
//        System.out.println(d.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
//
//        DateTimeFormatter dateFormat1B = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.of("UTC"));
//        System.out.println(dateFormat1B.format(d.toInstant()));
//
//        sdf2.setTimeZone((TimeZone.getTimeZone("UTC")));
//        String one = sdf2.format(d);
//        System.out.println("1." + one );
//
//        System.out.println("========================");
//
//
//        String dateStr2 = "2022-03-30T12:55:00+00:00";
//        DateTimeFormatter dateFormat2A = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'H:m:sXXX").withZone(ZoneId.of("UTC"));
//        LocalDateTime localDateTime2 = LocalDateTime.parse(dateStr2, dateFormat2A);
//        DateTimeFormatter dateFormat2B = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.of("UTC"));
//        System.out.println(dateFormat2B.format(localDateTime2));
//
//
//        System.out.println("========================");
//
//        String dateStr3 = "Mar 30, 2022 at 13:55 BST";
//        SimpleDateFormat sdf4 = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm Z", Locale.US);
//        sdf4.setTimeZone((TimeZone.getTimeZone("BST")));
//        Date d3 = sdf4.parse(dateStr3);
//        DateTimeFormatter dateFormat3 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.of("UTC+1"));
//        String three = dateFormat3.format(d3.toInstant());
//        System.out.println("3." + three);


        System.out.println("end.");
    }

    public static void kfjglsdkfjgf() throws ParseException {

        //首先这种时间格式应该是美国时间的一种格式，因为这里不写上Locale.US"的话，则会抛ParseException异常
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

        String cstStr = "Wed, 17 Oct 2018 20:17:40 CST";
        String bstStr = "Wed, 17 Oct 2018 20:17:40 BST";

        System.out.println(sdf.parse(cstStr));
        System.out.println(sdf.parse(bstStr));

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        System.out.println(sdf.parse(cstStr));

    }

}
