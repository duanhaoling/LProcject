package com.ldh.android.javaprocessor;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Android注解快速入门和实用解析
 * http://www.jianshu.com/p/9ca78aa4ab4d
 *
 * https://race604.com/annotation-processing/
 *
 * @AutoService(Processor.class)，谷歌提供的自动注册注解，为你生成注册Processor所需要的格式文件（com.google.auto相关包）。
 * init(ProcessingEnvironment env)，初始化处理器，一般在这里获取我们需要的工具类。
 * getSupportedAnnotationTypes()，指定注解处理器是注册给哪个注解的，返回指定支持的注解类集合。
 * getSupportedSourceVersion() ，指定java版本。
 * process()，处理器实际处理逻辑入口。
 * Created by ldh on 2017/9/11.
 */
public class BindViewDemoProcessor extends AbstractProcessor{

    private Elements mElementUtils;
    private Types mTypeUtils;
    private Filer mFiler;
    private Messager mMessages;

    /**
     * 注解处理器的初始化
     * 一般在这里获取我们需要的工具类
     * @param env 提供工具类Elements, Types和Filer
     */
    @Override
    public synchronized void init(ProcessingEnvironment env){
        super.init(env);
        //Element代表程序的元素，例如包、类、方法。
        mElementUtils = env.getElementUtils();

        //处理TypeMirror的工具类，用于取类信息
        mTypeUtils = env.getTypeUtils();

        //Filer可以创建文件
        mFiler = env.getFiler();

        //错误处理工具
        mMessages = env.getMessager();
    }

    /**
     * 处理器实际处理逻辑入口
     * @param  annoations
     * @param env 所有注解的集合
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annoations,
                           RoundEnvironment env) {
        //do someThing
        return false;
    }

    //指定注解处理器是注册给哪个注解的，返回指定支持的注解类集合。
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> sets = new LinkedHashSet<String>();

        //大部分class而已getName、getCanonicalNam这两个方法没有什么不同的。
        //但是对于array或内部类等就不一样了。
        //getName返回的是[[Ljava.lang.String之类的表现形式，
        //getCanonicalName返回的就是跟我们声明类似的形式。
        sets.add(BindView.class.getCanonicalName());

        return sets;
    }

    //指定Java版本，一般返回最新版本即可
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
