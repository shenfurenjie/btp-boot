package com.tiger.btp.framework.graphql;

import com.tiger.btp.app.AppServiceFactory;
import com.tiger.btp.app.DataModelDaoFactory;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.app.DataModelFactory;
import com.tiger.btp.model.data_model.Attribute;
import com.tiger.btp.model.data_model.Relation;
import graphql.schema.DataFetcher;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Data
@Component
public class DataFetcherFactory {

    @Autowired
    AppServiceFactory appServiceFactory;

    @Autowired
    DataModelFactory dataModelFactory;

    @Autowired
    DataModelDaoFactory dataModelDaoFactory;

    /**
     * map appName:appDataFetcher
     */
    Map<String, Map<String, DataFetcher>> appDataFetcherMap = new ConcurrentHashMap<>();

    /**
     * 获取dataFetcher
     *
     * @return
     */
    public Map<String, DataFetcher> getQueryDataFetcher() {
        Map<String, DataFetcher> queryDataFetcherMap = new HashMap<>();
        for (DataModelExt dataModelExt : dataModelFactory.getDataModelExtMap().values()) {
            /**
             *  query
             */
            QueryDataFetcher queryFetcher = new QueryDataFetcher();
            queryFetcher.setModel(dataModelExt);
            queryFetcher.setDataModelDaoFactory(dataModelDaoFactory);
            queryDataFetcherMap.put(dataModelExt.getQueryName(), queryFetcher);

            /**
             *  queryPaged
             */
            QueryDataPagedFetcher queryFetcherPaged = new QueryDataPagedFetcher();
            queryFetcherPaged.setModel(dataModelExt);
            queryFetcherPaged.setDataModelDaoFactory(dataModelDaoFactory);
            queryDataFetcherMap.put(dataModelExt.getQueryPagedName(), queryFetcherPaged);

        }
        return queryDataFetcherMap;
    }

    /**
     * 获取关联dataModel
     *
     * @param ext
     * @return
     */
    public Map<String, DataFetcher> getRelDataModelFetcher(DataModelExt ext) {
        Map<String, DataFetcher> attributeDataFetcher = new HashMap<>();
        if (CollectionUtils.isNotEmpty(ext.getRelation())) {
            for (Relation relation : ext.getRelations().getRelation()) {
                String name = relation.getName();
                QueryRelDataFetcher fetcher = new QueryRelDataFetcher();
                fetcher.setModel(dataModelFactory.getDataModelExtMap().get(relation.getModelId()));
                fetcher.setDataModelDaoFactory(dataModelDaoFactory);
                fetcher.setRelation(relation);
                attributeDataFetcher.put(name, fetcher);
            }
        }
        return attributeDataFetcher;
    }

    /**
     * 获取dataModel
     *
     * @param ext
     * @return
     */
    public Map<String, DataFetcher> getDataModelFetcher(DataModelExt ext) {
        Map<String, DataFetcher> attributeDataFetcherMap = new HashMap<>();
        for (Attribute attribute : ext.getAttribute()) {
            AttributeDataFetcher dataFetcher = new AttributeDataFetcher();
            dataFetcher.setAttribute(attribute);
            dataFetcher.setModelExt(ext);
            attributeDataFetcherMap.put(attribute.getName(), dataFetcher);
        }
        getRelDataModelFetcher(ext);
        return attributeDataFetcherMap;
    }

    /**
     * 获取dataModel
     *
     * @param ext
     * @return
     */
    public Map<String, DataFetcher> getQueryResultFetcher(DataModelExt ext) {
        Map<String, DataFetcher> dataFetcherMap = new HashMap<>();
        dataFetcherMap.put("records", getDataModelFetcher(ext).get(ext.getClassName()));

//        Map<String, Object> mappedResult = new HashMap<>();
//        mappedResult.put("records", result.getRecords());
//        mappedResult.put("size",pageCnd.getSize());
//        mappedResult.put("current",pageCnd.getCurrent());
//        mappedResult.put("totalCount",result.getTotal());
//        mappedResult.put("pages",result.getPages());

        return dataFetcherMap;
    }

}
