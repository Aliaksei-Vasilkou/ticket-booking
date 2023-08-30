package ua.epam.mishchenko.ticketbooking.service.impl;

import org.springframework.stereotype.Service;
import ua.epam.mishchenko.ticketbooking.mapper.EventToMongoEventMapper;
import ua.epam.mishchenko.ticketbooking.mapper.TicketToMongoTicketMapper;
import ua.epam.mishchenko.ticketbooking.mapper.UserToMongoUserMapper;
import ua.epam.mishchenko.ticketbooking.model.Event;
import ua.epam.mishchenko.ticketbooking.model.Ticket;
import ua.epam.mishchenko.ticketbooking.model.User;
import ua.epam.mishchenko.ticketbooking.model.mongo.EventMongoDb;
import ua.epam.mishchenko.ticketbooking.model.mongo.TicketMongoDb;
import ua.epam.mishchenko.ticketbooking.model.mongo.UserMongoDb;
import ua.epam.mishchenko.ticketbooking.repository.EventRepository;
import ua.epam.mishchenko.ticketbooking.repository.TicketRepository;
import ua.epam.mishchenko.ticketbooking.repository.UserRepository;
import ua.epam.mishchenko.ticketbooking.repository.mongo.EventMongoRepository;
import ua.epam.mishchenko.ticketbooking.repository.mongo.TicketMongoRepository;
import ua.epam.mishchenko.ticketbooking.repository.mongo.UserMongoRepository;
import ua.epam.mishchenko.ticketbooking.service.MongoMigrationService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MongoMigrationServiceImpl implements MongoMigrationService {

    private final UserRepository userRepository;
    private final UserMongoRepository userMongoRepository;
    private final UserToMongoUserMapper userMapper;

    private final TicketRepository ticketRepository;
    private final TicketMongoRepository ticketMongoRepository;
    private final TicketToMongoTicketMapper ticketMapper;

    private final EventRepository eventRepository;
    private final EventMongoRepository eventMongoRepository;
    private final EventToMongoEventMapper eventMapper;

    public MongoMigrationServiceImpl(UserMongoRepository userMongoRepository,
                                     UserRepository userRepository,
                                     UserToMongoUserMapper userMapper,
                                     TicketRepository ticketRepository,
                                     TicketMongoRepository ticketMongoRepository,
                                     TicketToMongoTicketMapper ticketMapper,
                                     EventRepository eventRepository,
                                     EventMongoRepository eventMongoRepository,
                                     EventToMongoEventMapper eventMapper) {
        this.userMongoRepository = userMongoRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.ticketRepository = ticketRepository;
        this.ticketMongoRepository = ticketMongoRepository;
        this.ticketMapper = ticketMapper;
        this.eventRepository = eventRepository;
        this.eventMongoRepository = eventMongoRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public boolean migrate() {
        syncUsers();
        syncTickets();
        syncEvents();
        return true;
    }

    private void syncUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserMongoDb> collect = StreamSupport.stream(users.spliterator(), false)
                .map(userMapper::map)
                .collect(Collectors.toList());

        userMongoRepository.insert(collect);
    }

    private void syncTickets() {
        Iterable<Ticket> tickets = ticketRepository.findAll();
        List<TicketMongoDb> collect = StreamSupport.stream(tickets.spliterator(), false)
                .map(ticketMapper::map)
                .collect(Collectors.toList());

        ticketMongoRepository.insert(collect);
    }

    private void syncEvents() {
        Iterable<Event> events = eventRepository.findAll();
        List<EventMongoDb> collect = StreamSupport.stream(events.spliterator(), false)
                .map(eventMapper::map)
                .collect(Collectors.toList());

        eventMongoRepository.insert(collect);
    }
}
