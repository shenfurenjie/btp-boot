package com.tiger.btp.app;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class DataFetcherFactory {

    DataModelDaoFactory dataModelDaoFactory;

    public DataFetcherFactory(DataModelDaoFactory dataModelDaoFactory) {
        this.dataModelDaoFactory = dataModelDaoFactory;
    }
}
