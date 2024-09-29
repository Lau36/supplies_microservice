package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.ports.output.ReportClientPort;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.ReportClient;

public class ReportClientPortImpl implements ReportClientPort {
    private ReportClient reportClient;

    public ReportClientPortImpl(ReportClient reportClient) {
        this.reportClient = reportClient;
    }

    @Override
    public void addReport() {

    }
}
