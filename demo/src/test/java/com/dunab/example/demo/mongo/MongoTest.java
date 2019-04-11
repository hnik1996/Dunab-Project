package com.dunab.example.demo.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoTest {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongo = new MongoClient("127.0.0.1", 27017);
        DB db = mongo.getDB("dunab");
        Object eval = db.eval("db.sample.insert({\"name\":\"illidan\"})");
        System.out.println(eval);
        System.out.println("Fenito!");
    }
}
