package com.tiger.btp.framework.common.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: TigerRen
 * @Date: 2019/11/28 4:24 PM
 */
public class JavaToPdfHtmlFreeMarkerUtil {

    private static final String DEST = "target/HelloWorld_CN_HTML_FREEMARKER.pdf";
    private static final String HTML = "template_freemarker.html";
    private static final String FONT = "simhei.ttf";

    private static Configuration freemarkerCfg;


    static {
        freemarkerCfg = new Configuration(Configuration.VERSION_2_3_23);
        freemarkerCfg.setDefaultEncoding("UTF-8");
        try {
            freemarkerCfg.setDirectoryForTemplateLoading(new File("/Users/renjie/workspace/ideaProjects/btp-boot/src/main/resources"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * java转pdf
     *
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public static void javaToPdf() throws DocumentException, FileNotFoundException {
        String DEST = "target/HelloWorld.pdf";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        document.add(new Paragraph("hello world"));
        document.close();
        writer.close();

    }

    /**
     * java转pdf中文支持
     *
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public static void javaToPdfCN() throws DocumentException, FileNotFoundException {
        String DEST = "target/HelloWorld_CN.pdf";
        String FONT = "simhei.ttf";

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        Font f1 = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        document.add(new Paragraph("hello world,我是任杰", f1));
        document.close();
        writer.close();

    }

    /**
     * java转pdf,通过html生成pdf
     *
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public static void JavaToPdfHtml() throws DocumentException, IOException {

        String DEST = "target/HelloWorld_CN_HTML.pdf";
        String HTML = "/Users/renjie/workspace/ideaProjects/btp-boot/src/main/resources/template.html";
        String FONT = "simhei.ttf";
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML), null, Charset.forName("UTF-8"), fontImp);
        // step 5
        document.close();

    }

    /**
     * java转pdf,通过freemarker 动态html生成pdf
     *
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public static void JavaToPdfHtmlFreeMarker() throws DocumentException, IOException {

        String DEST = "target/HelloWorld_CN_HTML_FREEMARKER.pdf";
        String HTML = "/Users/renjie/workspace/ideaProjects/btp-boot/src/main/resources/template_freemarker.html";
        String FONT = "simhei.ttf";

        Map<String, Object> data = new HashMap();
        data.put("name", "任杰");
        String content = freeMarkerRender(data, HTML);
        createPdf(content, DEST);

    }

    public static void createPdf(String content, String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new ByteArrayInputStream(content.getBytes()), null, Charset.forName("UTF-8"), fontImp);
        // step 5
        document.close();

    }

    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate("template_freemarker.html");
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            template.process(data, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) {
        try {
            javaToPdf();
            javaToPdfCN();
            JavaToPdfHtml();
            JavaToPdfHtmlFreeMarker();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
