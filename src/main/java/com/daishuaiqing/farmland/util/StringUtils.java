package com.daishuaiqing.farmland.util;

import java.util.Random;

public class StringUtils {
    /**
     * 去除下划线，下划线后字母转为大写
     * @param str
     * @return
     */
    public static String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        return StringUtils.toUpperCaseFirstOne(result);
    }

    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.replaceUnderLineAndUpperCase("user_name"));
    }

    /**
     * 以:为分隔符切割字符串
     * @param str
     * @return
     */
    public static String[] splitByColon(String str){
        return str.split(":");
    }

    private static Random random=new Random();
    private static char[] randomChar=new char[]{'1','2','3','4','5','6','7','8','9','0','Q',
            'W','E','R','T','Y','U','I','O','P','A','S',
            'D','F','G', 'H','J','K','L','Z','X','C','V',
            'B','N','M','q','w','e','r','t','y','u','i',
            'o','p','a','s','d','f','g','h','j','k','l',
            'z','x','c','v','b','n','m'};

    public static boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }


    public static boolean isNotEmpty(String str){
        if((str!=null)&&!"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    public static String randomStr(Integer length){
        StringBuffer result=new StringBuffer();
        for (int i=0;i<length;i++){
            result.append(randomChar[random.nextInt(62)]);
        }
        return result.toString();
    }

    public static String randomNumb(Integer length){
        StringBuffer result=new StringBuffer();
        for (int i=0;i<length;i++){
            result.append(randomChar[random.nextInt(9)]);
        }
        return result.toString();
    }

    public static String autoFillZero(Integer length,int number){
        String format="%0"+length+"d";
        // 0 代表前面补充0
        // length 代表长度
        // d 代表参数为正数型
        return String.format(format, number);
    }

    public static Integer castInt(String intStr){
        return Integer.parseInt(intStr.replaceFirst("^0*", ""));
    }

}
