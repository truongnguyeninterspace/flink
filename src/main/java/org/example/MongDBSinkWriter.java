/**
 * Copyright © 2023 Interspace Co., Ltd. All rights reserved.
 *
 * Licensed under the Interspace's License,
 * you may not use this file except in compliance with the License.
 */
package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.flink.api.connector.sink2.SinkWriter;
import org.bson.Document;

/**
 * purpose of the class
 *
 * @author Truong
 */
public class MongDBSinkWriter implements SinkWriter<String> {

//    MongoClient mongoClient;
//    MongoDatabase database;
//    MongoCollection<Document> collection;

    MongDBSinkWriter( ) {
//        mongoClient = new MongoClient("localhost", 27017);
//
//        MongoCredential credential;
//        credential = MongoCredential.createCredential("root", "local",
//                "example".toCharArray());
//        System.out.println("Connected to the database successfully");
//
//        database = mongoClient.getDatabase("local");
//        System.out.println("Credentials ::" + credential);
//        collection = database.getCollection("bank");
    }

    @Override
    public void write(String s, Context context)
            throws IOException, InterruptedException {
        String payload = s.substring(s.indexOf("payload"));
        int start = payload.indexOf("after") + 7;
        int end = payload.indexOf("source") - 2;
        String result = payload.substring(start, end);
        result = result.replace("\"", "");
        System.out.println(result);

        File file = new File("append.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(result + System.lineSeparator());
        fr.close();
//        String payload = s.substring(s.indexOf("payload"));
//        int start = payload.indexOf("after") +7;
//        int end =  payload.indexOf("source") -2;
//        String result = payload.substring(start, end);
//        result = result.replace("\"","");
//        System.out.println(result);
//        Document document = new Document(result, String.class);
//
//        collection.insertOne(new Document());
    }

    @Override
    public void flush(boolean b) throws IOException, InterruptedException {

    }

    @Override
    public void close() throws Exception {

    }
}
