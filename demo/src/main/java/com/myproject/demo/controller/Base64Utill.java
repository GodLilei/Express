package com.myproject.demo.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class Base64Utill {
    public static String encode(String code) throws UnsupportedEncodingException, Exception {
        return encodeBase64(code.getBytes());
    }

    public static String decode(String code) throws UnsupportedEncodingException, Exception {
        return new String(decodeBase64(code));
    }

    public static String encodeBase64(byte[] input) throws Exception {
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, new Object[] { input });
        return (String) retObj;
    }

    public static byte[] decodeBase64(String input) throws Exception {
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, input);
        return (byte[]) retObj;
    }

    public static void main(String[] rags) throws UnsupportedEncodingException, Exception {
        String str = "Aa1122";
        String code = Base64Utill.encode(str);
        System.err.println("加密前：" + str);
        System.err.println("加密后：" + code);
        System.err.println("解密后：" + Base64Utill.decode(code));

    }

}
