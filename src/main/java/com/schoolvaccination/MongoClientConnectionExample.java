package com.schoolvaccination;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoClientConnectionExample {
    public static void main(String[] args) {
    	String connectionString = "mongodb://root:root@schoolvaccinationcluster-shard-00-00.sxhstal.mongodb.net:27017,schoolvaccinationcluster-shard-00-01.sxhstal.mongodb.net:27017,schoolvaccinationcluster-shard-00-02.sxhstal.mongodb.net:27017/?ssl=true&replicaSet=atlas-abc-shard-0&authSource=admin&retryWrites=true&w=majority";
    	String uri = "mongodb+srv://root:root@schoolvaccinationcluster.sxhstal.mongodb.net/?retryWrites=true&w=majority&authSource=admin";
	
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}

