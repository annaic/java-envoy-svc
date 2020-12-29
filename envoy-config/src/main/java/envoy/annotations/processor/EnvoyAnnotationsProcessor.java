package envoy.annotations.processor;

import com.google.auto.service.AutoService;
import envoy.annotations.Cluster;
import envoy.annotations.Listener;
import envoy.config.Connection;
import envoy.gen.EnvoyGenerator;
import freemarker.template.TemplateException;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes("envoy.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class EnvoyAnnotationsProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager =  processingEnv.getMessager();
        if(annotations.size() == 0) return true;
        StringBuilder accumulator = new StringBuilder();
        accumulator.append("clusters:\n");
        for(TypeElement annotation: annotations){
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            for(Element e : annotatedElements){
                if("envoy.annotations.Cluster".equals(annotation.getQualifiedName().toString())){
                    TypeMirror typeMirror = e.asType();
                    if(!typeMirror.toString().equals(Connection.class.getName())){
                        messager.printMessage(Diagnostic.Kind.ERROR,
                                "The type annotated with @Cluster must be a Connection");
                    }
                    //We have a valid Cluster annotation, Get it
                    Cluster cluster = e.getAnnotation(Cluster.class);
                    String name = e.getSimpleName().toString();
                    try {
                        EnvoyGenerator.Instance.processCluster(cluster, name, accumulator);
                    } catch (IOException | TemplateException ioException) {
                        ioException.printStackTrace();
                        messager.printMessage(Diagnostic.Kind.ERROR,
                                ioException.getMessage());
                    }
                }else if("envoy.annotations.Listener".equals(annotation.getQualifiedName().toString())){
                    Listener listener = e.getAnnotation(Listener.class);
                    //System.err.println(listener);

                }
            }
        }
        //Print the accumulator
        System.err.println(accumulator.toString());
        return false;
    }


}
