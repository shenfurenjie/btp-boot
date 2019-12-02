package com.tiger.btp.framework.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * freemarker 工具类
 */
public class FtlUtils {

    private static Configuration cfg;

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
    }


    public static String render(String template, Map<String, Object> model) throws IOException, TemplateException {
        Writer out = new StringWriter();
        Template t = new Template("templateName", new StringReader("[#ftl]" + template), cfg);
        t.process(model, out);
        String transformedTemplate = out.toString();
        return transformedTemplate;
    }

    public static String render(String template, Object model) throws IOException, TemplateException {
        Template t = new Template("templateName", new StringReader(template), cfg);
        Writer out = new StringWriter();
        t.process(model, out);
        String transformedTemplate = out.toString();
        return transformedTemplate;
    }

    public static String render2(String template, Map<String, Object> model) throws IOException, TemplateException {
        return render(template, (Object) model);
    }

    public static void main(StringUtil[] args) throws IOException, TemplateException {

        Map<String, Object> pp = new HashMap<String, Object>();

        pp.put("search", "");


        System.out.println(FtlUtils.render("1=1 [#if search?has_content]and tableId = :search or tableName = :search [/#if] ", pp));


    }


}
