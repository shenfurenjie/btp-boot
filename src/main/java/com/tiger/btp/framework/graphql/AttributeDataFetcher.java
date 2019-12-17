package com.tiger.btp.framework.graphql;

import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.model.data_model.Attribute;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.Data;


/**
 * @Author: TigerRen
 * @Date: 2019/12/4 2:20 PM
 */
@Data
public class AttributeDataFetcher implements DataFetcher {

    DataModelExt modelExt;

    Attribute attribute;

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {

        Object value = dataFetchingEnvironment.getSource();
        if (value == null) {
            return null;
        }
        return value;
    }
}
