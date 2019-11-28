package com.tiger.btp.framework.common.utils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

public class JSONUtils {

    public static String getJSONStringWithoutQuotation(Object map) {
        String whereJSON = StringUtils.EMPTY;
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            serializer.config(SerializerFeature.QuoteFieldNames, false);
            serializer.write(map);
            whereJSON = out.toString();
        } finally {
            out.close();
        }
        return whereJSON;
    }
}
