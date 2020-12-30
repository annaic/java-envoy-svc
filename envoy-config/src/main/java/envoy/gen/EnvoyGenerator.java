package envoy.gen;

import envoy.annotations.Cluster;
import envoy.annotations.Listener;
import envoy.gen.freemarker.AddressVO;
import envoy.gen.freemarker.ClusterVO;
import envoy.gen.freemarker.ListenerVO;
import envoy.gen.freemarker.mapper.AnnotationToVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class EnvoyGenerator {
    public static final EnvoyGenerator Instance = new EnvoyGenerator();
    private Configuration cfg;
    private EnvoyGenerator(){
        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */

        /* Create and adjust the configuration singleton */
        cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setNumberFormat("computer");
        cfg.setClassForTemplateLoading(EnvoyGenerator.class, "templates");

        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }

    public void processCluster(Cluster cluster, String name, StringBuilder accumulator) throws IOException, TemplateException {
        Template template = cfg.getTemplate("cluster.yaml.ftl");

        Map root = new HashMap();
        ClusterVO clusterVO = AnnotationToVO.mapCluster(cluster, name);
        root.put("clusterVO", clusterVO);
        /* Merge data-model with template */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer out = new OutputStreamWriter(baos);
        template.process(root, out);
        //Convert the Cluster to the Generator Type
        String output = baos.toString("UTF-8");
        accumulator.append(output);
        //System.err.println(output);

    }


    public void processListener(Listener listener, String name, String trafficDirection, StringBuilder accumulator)
            throws IOException, TemplateException {
        Template template = cfg.getTemplate("listener.yaml.ftl");

        Map root = new HashMap();
        ListenerVO listenerVO = AnnotationToVO.mapListener(listener,name,trafficDirection);
        root.put("listener", listenerVO);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer out = new OutputStreamWriter(baos);
        template.process(root, out);
        String output = baos.toString("UTF-8");
        accumulator.append(output);
        System.err.println(output);
    }
}
