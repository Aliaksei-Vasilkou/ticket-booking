package ua.epam.mishchenko.ticketbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.epam.mishchenko.ticketbooking.model.Event;
import ua.epam.mishchenko.ticketbooking.model.Ticket;
import ua.epam.mishchenko.ticketbooking.model.mongo.EventMongoDb;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EventToMongoEventMapper {

    @Mapping(target = "title", source = "title")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "price", source = "ticketPrice")
    @Mapping(target = "ticketCount", expression = "java(getTicketCount(event.getTickets()))")
    @Mapping(target = "tickets", expression = "java(getTicketIds(event.getTickets()))")
    EventMongoDb map(Event event);

    default int getTicketCount(List<Ticket> tickets) {
        return tickets.size();
    }

    default List<String> getTicketIds(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getId)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
