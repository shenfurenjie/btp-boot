package com.rj.btp.framework.model.condition;

import com.rj.btp.framework.model.enums.OrderByEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SortCnd {

    String colName;

    OrderByEnum order = OrderByEnum.asc;
}
