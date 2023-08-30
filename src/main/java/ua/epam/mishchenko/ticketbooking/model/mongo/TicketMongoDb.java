package ua.epam.mishchenko.ticketbooking.model.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.epam.mishchenko.ticketbooking.model.Category;
import ua.epam.mishchenko.ticketbooking.model.Event;
import ua.epam.mishchenko.ticketbooking.model.User;

import javax.persistence.Id;

@Document(collection = "TicketCollection")
public class TicketMongoDb {

    @Id
    private String id;

    private UserMongoDb user;

//    private EventMongoDb event;

    private Integer place;

    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserMongoDb getUser() {
        return user;
    }

    public void setUser(UserMongoDb user) {
        this.user = user;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
