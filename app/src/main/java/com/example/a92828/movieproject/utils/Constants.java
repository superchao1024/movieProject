package com.example.a92828.movieproject.utils;

/**
 * 配置各个页面的联网地址
 */
public class Constants {

    public static String TOP250_URL = "http://api.douban.com/v2/movie/top250";

    public static String SHOWING = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b";

    public static String COMING = "https://api.douban.com/v2/movie/coming_soon?apikey=0b2bdeda43b5688921839c8ecb20399b";

    //搜索
    public static String SEARCH = "https://api.douban.com/v2/movie/search?q=";

    //口碑
    public static String KOUBEI = "https://api.douban.com/v2/movie/weekly?apikey=0b2bdeda43b5688921839c8ecb20399b";

    //预告片
    public static String YUGAOPIAN = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    //票房
    public static String PIAOFANG = "https://api.shenjian.io/?appid=d0167af32e8819183d73c893aca42f79";



}
