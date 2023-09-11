/**
 * Copyright © 2023 Interspace Co., Ltd. All rights reserved.
 *
 * Licensed under the Interspace's License,
 * you may not use this file except in compliance with the License.
 */
package org.example;

import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.api.connector.sink2.SinkWriter;
import org.bson.Document;

/**
 * purpose of the class
 *
 * @author Truong
 */
public class MongoDBSink implements Sink<String> {

    @Override
    public SinkWriter<String> createWriter(InitContext initContext) throws IOException {
        return new MongDBSinkWriter();
    }
}
