package com.tiger.btp.framework.graphql;

import com.tiger.btp.app.AppService;
import com.tiger.btp.app.AppServiceFactory;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.app.DataModelFactory;
import com.tiger.btp.framework.mapper.BaseMapper;
import graphql.schema.DataFetcher;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Data
//@Component
public class DataFetcherFactory {

    @Autowired
    AppServiceFactory appServiceFactory;

    /**
     * map appName:appDataFetcher
     */
    Map<String, Map<String, DataFetcher>> appDataFetcherMap = new ConcurrentHashMap<>();

    @Autowired
    BaseMapper baseMapper;

    public Map<String, DataFetcher> getQueryDataFetcher() {
        Map<String, DataFetcher> queryDataFetcher = new HashMap<>();
        Iterator iterator = appServiceFactory.getAppServiceMap().keySet().iterator();
        while (iterator.hasNext()) {
            AppService appService = appServiceFactory.getAppService((String) iterator.next());
            DataModelFactory dataModelFactory = appService.getDataModelFactory();
            for (DataModelExt ext : dataModelFactory.getDataModelExtMap().values()) {
                String modelClass = appService.getModelPackageName() + "." + ext.getClassName();
                log.info("modelClass is {}", modelClass);
                Class c = Object.class;
                try {
                    c = Class.forName(modelClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                QueryDataFetcher fetcher = new QueryDataFetcher();
                fetcher.setModelClass(c);
                fetcher.setModel(ext);
                fetcher.setBaseMapper(baseMapper);
                //fetcher.setModelDaoFactory(modelDaoFactory);
                queryDataFetcher.put("find_" + ext.getId() + "_by_id", fetcher);

            }
        }
        return queryDataFetcher;
    }
}
