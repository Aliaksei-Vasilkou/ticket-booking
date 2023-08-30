package ua.epam.mishchenko.ticketbooking.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.epam.mishchenko.ticketbooking.model.mongo.TicketMongoDb;

@Repository
public interface TicketMongoRepository extends MongoRepository<TicketMongoDb, String> {

}
