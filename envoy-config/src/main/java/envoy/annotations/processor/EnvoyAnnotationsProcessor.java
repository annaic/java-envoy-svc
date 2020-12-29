package envoy.annotations.processor;

import com.google.auto.service.AutoService;
import envoy.annotations.Cluster;
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
        for(TypeElement annotation: annotations){
            //System.err.println(annotation.getQualifiedName());
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            for(Element e : annotatedElements){
                //System.err.println("For:" + annotation.getQualifiedName());
                if("envoy.annotations.Cluster".equals(annotation.getQualifiedName().toString())){
                    TypeMirror typeMirror = e.asType();
                    //System.err.println("TypeMirror:" + typeMirror.toString());
                    if(!typeMirror.toString().equals(Connection.class.getName())){
                        messager.printMessage(Diagnostic.Kind.ERROR,
                                "The type annotated with @Cluster must be a Connection");
                    }
                    //We have a valid Cluster annotation
                    //Get it
                    Cluster cluster = e.getAnnotation(Cluster.class);
                    String name = e.getSimpleName().toString();
                    try {
                        EnvoyGenerator.Instance.processCluster(cluster, name, accumulator);
                    } catch (IOException | TemplateException ioException) {
                        ioException.printStackTrace();
                        messager.printMessage(Diagnostic.Kind.ERROR,
                                ioException.getMessage());
                    }
                    //System.err.println("cluster:" + cluster);

                }
                //System.err.println("e:" + e.getSimpleName());
//                Cluster cluster = e.getAnnotation(Cluster.class);
//                System.err.println(cluster);
//                if(e instanceof TypeElement){
//                    TypeElement te = (TypeElement)e;
//                    System.err.println("te:" + te.getQualifiedName());
//                }
            }


        }
        return false;
    }

    private void processCluster(Cluster cluster, StringBuilder accumulator) {
        //Convert the Cluster to the Generator Type
    }
}
