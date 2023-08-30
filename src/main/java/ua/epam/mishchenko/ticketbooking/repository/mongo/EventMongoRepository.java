package ua.epam.mishchenko.ticketbooking.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.epam.mishchenko.ticketbooking.model.mongo.EventMongoDb;

@Repository
public interface EventMongoRepository extends MongoRepository<EventMongoDb, String> {

}
