package com.tiger.btp.app;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModelResource<T> {

    /**
     * XML数据反序列化到新创建的Java对象中,dataModelBean容器
     */
    private static Map<Class<?>, Unmarshaller> unmarshallerMap = new ConcurrentHashMap();
    /**
     * java对象序列话为转为XML格式的数据，dataModelXml容器
     */
    private static Map<Class<?>, Marshaller> marshallerMap = new ConcurrentHashMap();

    private Class<T> targetClass;

    private InputStream inputStream;

    /**
     * 实例化dataMode资源
     *
     * @param targetClass
     * @param inputStream
     */
    public ModelResource(Class<T> targetClass, InputStream inputStream) {
        this.targetClass = targetClass;
        this.inputStream = inputStream;
        if (unmarshallerMap.containsKey(targetClass)) {
            throw new RuntimeException("dataModel " + this.targetClass + "has loaded!");
        }
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(targetClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Marshaller marshaller = context.createMarshaller();
//            unmarshaller.setProperty("jaxb.encoding", "UTF-8");
//            unmarshaller.setProperty("jaxb.formatted.output", true);
            unmarshallerMap.put(targetClass, unmarshaller);
            marshallerMap.put(targetClass, marshaller);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    public T readModel() {
        try {
            T obj = (T) unmarshallerMap.get(this.targetClass).unmarshal(this.inputStream);
            return obj;
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
