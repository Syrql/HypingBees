package fr.syrql.hypingbees.mysql;

public class MysqlStorage {

    private static final String CREATE_TABLE_BEEHIVE = "CREATE TABLE IF NOT EXISTS beehive (" +
            " uuid VARCHAR(255), " +
            "islanduuid VARCHAR(255), " +
            "time INT, )";
}
