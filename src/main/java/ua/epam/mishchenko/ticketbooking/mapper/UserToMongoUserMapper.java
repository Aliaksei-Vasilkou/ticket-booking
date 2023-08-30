package ua.epam.mishchenko.ticketbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.epam.mishchenko.ticketbooking.model.Ticket;
import ua.epam.mishchenko.ticketbooking.model.User;
import ua.epam.mishchenko.ticketbooking.model.mongo.UserMongoDb;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserToMongoUserMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "balance", source = "userAccount.money")
    @Mapping(target = "count", expression = "java(getTicketCount(user.getTickets()))")
    @Mapping(target = "tickets", expression = "java(getTicketIds(user.getTickets()))")
    UserMongoDb map(User user);

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
