package com.ldh.poxy;

import com.ldh.poxy.bean.Font;
import com.ldh.poxy.bean.FontProvider;

import java.util.Map;

/**
 * 静态代理实现添加缓存功能
 * 一次只可以为一个FontProvider添加，针对其他的provier需要写类似的类
 * <p>
 * 静态代理只能针对一个接口代理
 * Create by ldh on 2017/11/10.
 */
public class CachedFontProvider implements FontProvider {
    private FontProvider fontProvider;
    private Map<String, Font> cached;

    public CachedFontProvider(FontProvider fontProvider) {
        this.fontProvider = fontProvider;
    }

    @Override
    public Font getFont(String name) {
        Font font = cached.get(name);
        if (font == null) {
            //在之前代码的基础上增加缓存功能
            font = fontProvider.getFont(name);
            cached.put(name, font);
        }
        return font;
    }
}
