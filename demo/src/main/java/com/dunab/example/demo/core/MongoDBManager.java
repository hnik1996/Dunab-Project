package com.dunab.example.demo.core;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MongoDBManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBManager.class);

    @Autowired
    private MongoClient  mongoClient;

    private final String dbName;
    public MongoDBManager(String dbName){
        this.dbName = dbName;

    }

    public DB getDB() {
        return this.mongoClient.getDB(this.dbName);
    }

    public BasicDBObject getDefaultIndicesOptions() {
        return (new BasicDBObject("name", "regularIndex")).append("default_language", "none");
    }

    public boolean collectionExists(String collectionName) {
        return this.getDB().collectionExists(collectionName);
    }

    public DBCollection getCollection(String collectionName) {
        return this.getDB().getCollection(collectionName);
    }

    public DBCollection getOrCreateCollection(String collectionName, BasicDBObject indices, BasicDBObject indicesOptions) {
        if (this.collectionExists(collectionName)) {
            return this.getCollection(collectionName);
        } else {
            LOGGER.info("Collection with name : '" + collectionName + "' doesn't exists. creating it with inices: ->" + indices + " \n and indicesOptions: " + indicesOptions);
            DBCollection collection = this.getDB().createCollection(collectionName, (DBObject)null);
            if (!indices.isEmpty()) {
                collection.dropIndexes();
                collection.createIndex(indices, indicesOptions);
            }

            return collection;
        }
    }

    public DBCollection getOrCreateCollection(String collectionName, BasicDBObject indices) {
        BasicDBObject indicesOptions = this.getDefaultIndicesOptions();
        return this.getOrCreateCollection(collectionName, indices, indicesOptions);
    }

    public void deleteCollection(String collectionName) {
        DBCollection collection = this.getCollection(collectionName);
        if (collection == null) {
            LOGGER.debug("Collection with name '" + collectionName + "' doesn't exists");
        } else {
            collection.drop();
        }
    }
}
