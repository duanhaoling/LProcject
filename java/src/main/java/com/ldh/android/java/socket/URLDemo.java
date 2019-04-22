package com.ldh.android.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * desc:
 * Created by ldh on 2019/4/16.
 */
public class URLDemo {
    public static void main(String[] args) throws IOException {
         //使用URL读取网页内容
        //创建一个URL实例
        URL url = new URL("http://www.bing.com");
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String data = br.readLine();
        while (data != null) {
            System.out.println(data);
            data = br.readLine();
        }
        br.close();
        isr.close();
        is.close();
    }
}
