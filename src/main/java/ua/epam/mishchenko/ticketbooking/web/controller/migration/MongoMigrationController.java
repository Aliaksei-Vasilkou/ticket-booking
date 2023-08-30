package ua.epam.mishchenko.ticketbooking.web.controller.migration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.mishchenko.ticketbooking.service.MongoMigrationService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/migrations")
public class MongoMigrationController {

    private static final Logger log = LoggerFactory.getLogger(MongoMigrationController.class);

    private final MongoMigrationService migrationService;

    public MongoMigrationController(MongoMigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @GetMapping
    public ModelAndView migrate() {
        Map<String, Object> model = new HashMap<>();
        boolean result = migrationService.migrate();

        if (result) {
            model.put("result", "Success");
        } else {
            model.put("result", "Something went wrong");
        }

        return new ModelAndView("migration", model);
    }
}
