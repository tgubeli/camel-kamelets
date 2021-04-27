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
package org.apache.camel.kamelets.catalog;

import io.fabric8.kubernetes.api.model.apiextensions.v1.JSONSchemaProps;
import org.apache.camel.kamelets.catalog.model.KameletTypeEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KameletsCatalogTest {
    static KameletsCatalog catalog;

    @BeforeAll
    public static void createKameletsCatalog() throws IOException {
        catalog = new KameletsCatalog();
    }

    @Test
    void testKameletsSize() throws Exception {
        assertEquals(62, catalog.getKamelets().size());
    }

    @Test
    void testKameletsByName() throws Exception {
        assertEquals(13, catalog.getKameletsByName("aws").size());
    }

    @Test
    void testKameletsByType() throws Exception {
        assertEquals(6, catalog.getKameletsByType(KameletTypeEnum.ACTION.type()).size());
    }

    @Test
    void testGetKameletsDefinition() throws Exception {
        JSONSchemaProps props = catalog.getKameletDefinition("aws-sqs-source");
        assertEquals(6, props.getProperties().keySet().size());
        assertTrue(props.getProperties().keySet().contains("queueNameOrArn"));
    }

    @Test
    void testGetKameletsDefinitionNotExists() throws Exception {
        JSONSchemaProps props = catalog.getKameletsDefinition("word");
        assertNull(props);
    }
}