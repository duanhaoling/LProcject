package com.ldh.android.javaprocessor;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * hello world
 *
 * @author ldh
 * @date 2017/11/17
 */
@SupportedAnnotationTypes("com.ldh.android.javaprocessor.CustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CustomAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        StringBuilder builder = new StringBuilder()
                .append("package com.ldh.android.javaprocessor;\n\n")
                // open class
                .append("public class GeneratedClass {\n\n")
                // open method
                .append("\tpublic String getMessage() {\n")
                .append("\t\treturn \"");


        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (Element element : roundEnv.getElementsAnnotatedWith(CustomAnnotation.class)) {
            String objectType = element.getSimpleName().toString();


            // this is appending to the return statement
            builder.append(objectType).append(" says hello!\\n");
        }

        // end return
        builder.append("\";\n")
                // close method
                .append("\t}\n")
                // close class
                .append("}\n");


        try { // write the file
            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.yuntao.annotationprocessor.generated.GeneratedClass");


            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }


        return true;
    }
}
