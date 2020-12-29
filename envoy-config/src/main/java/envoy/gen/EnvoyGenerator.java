package envoy.gen;

import envoy.annotations.Cluster;
import envoy.gen.freemarker.AddressVO;
import envoy.gen.freemarker.ClusterVO;
import envoy.gen.freemarker.mapper.AnnotationToVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

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
        Writer out = new OutputStreamWriter(System.out);
        template.process(root, out);
        //Convert the Cluster to the Generator Type
    }


}
