package ua.epam.mishchenko.ticketbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.epam.mishchenko.ticketbooking.model.Ticket;
import ua.epam.mishchenko.ticketbooking.model.mongo.TicketMongoDb;

@Mapper(componentModel = "spring", uses = UserToMongoUserMapper.class)
public interface TicketToMongoTicketMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "place", source = "place")
    @Mapping(target = "category", source = "category")
    TicketMongoDb map(Ticket ticket);
}
