package envoy.annotations.processor;

import com.google.auto.service.AutoService;
import envoy.annotations.Cluster;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("envoy.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class EnvoyAnnotationsProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(annotations.size() == 0) return true;
        for(TypeElement annotation: annotations){
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            System.err.println(annotation.getQualifiedName());
            for(Element e : annotatedElements){
                System.err.println("e:" + e.getSimpleName());
                Cluster cluster = e.getAnnotation(Cluster.class);
                System.err.println(cluster);
                if(e instanceof TypeElement){
                    TypeElement te = (TypeElement)e;
                    System.err.println("te:" + te.getQualifiedName());
                }
            }


        }
        return false;
    }
}
