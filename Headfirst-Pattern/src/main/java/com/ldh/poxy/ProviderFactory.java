package com.ldh.poxy;

import com.ldh.poxy.bean.Font;
import com.ldh.poxy.bean.FontProvider;

import java.lang.reflect.Proxy;

/**
 * Create by ldh on 2017/11/10.
 */

public class ProviderFactory {
    private ProviderFactory() {

    }


    /**
     * 通过装饰者模式或者代理模式添加缓存功能
     * 对工厂类进行相应修改，代码使用处不必进行任何修改。
     * 这也是面向接口编程以及工厂模式的一个好处
     * <p>
     * 直接修改FontProviderFromDisk类也可以实现目的，但是我们还有FontProviderFromNet,
     * FontProviderFromSystem等多种实现类，一一修改太过繁琐且易出错。
     * 况且将来还可能添加日志，权限检查，异常处理等功能显然用静态代理类更好一点
     *
     * @return
     */
    public static FontProvider getFontProvider() {
        //静态代理
//        return new CachedFontProvider(new FontProviderFromDisk());
        //动态代理写法
        Class<FontProvider> targetClass = FontProvider.class;
        return (FontProvider) Proxy.newProxyInstance(targetClass.getClassLoader(),
                new Class[]{targetClass},
                new CachedProviderHandler(new FontProviderFromDisk()));
    }


    public static void main(String[] args) {

        FontProvider fontProvider = ProviderFactory.getFontProvider();
        Font font = fontProvider.getFont("微软雅黑");
        //......
    }
}
