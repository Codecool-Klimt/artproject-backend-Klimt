package com.codecool.fileshare.repository;

public class DbConstants {
    final static String HOST = System.getenv("host");
    final static String PORT = System.getenv("port");
    final static String DB_NAME = System.getenv("dbname");
    final static String DB_USERNAME = System.getenv("dbusername");
    final static String DB_PASSWORD = System.getenv("dbpassword");
    final static String TOKEN_KEY = System.getenv("tokenkey");
    final static String DB_URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME;
}
