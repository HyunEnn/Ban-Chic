package com.ssafy.banchic.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        log.info("Transaction의 Read Only가 " + isReadOnly + " 입니다.");

        if (isReadOnly) {
            log.info("Replica 서버로 요청합니다.");
            return "replica";
        }

        log.info("Source 서버로 요청합니다.");
        return "source";
    }

}