/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.kafkaconnector.azure.common.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AzureStorageService<T> implements AzureService<T> {
    private static final Logger LOG = LoggerFactory.getLogger(AzureStorageService.class);
    private final AzuriteContainer container = new AzuriteContainer();

    public AzureStorageService() {
        container.start();
    }

    protected AzuriteContainer getContainer() {
        return container;
    }

    @Override
    public void initialize() {
        LOG.info("Azurite local blob service running at address {}:{}", container.getHost(),
                container.getMappedPort(AzureServices.BLOB_SERVICE));
        LOG.info("Azurite local queue service running at address {}:{}", container.getHost(),
                container.getMappedPort(AzureServices.QUEUE_SERVICE));
    }

    @Override
    public void shutdown() {
        container.stop();
    }
}
