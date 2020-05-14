package com.grubjack.cinema.controller;

import com.grubjack.cinema.dao.entities.Ticket;
import com.grubjack.cinema.dao.entities.User;
import com.grubjack.cinema.dao.enums.DayOfWeek;
import com.grubjack.cinema.dao.enums.TimeOfDay;
import com.grubjack.cinema.service.ShowService;
import com.grubjack.cinema.service.TicketService;
import com.grubjack.cinema.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MenuServiceController {

    private final UserService userService;
    private final TicketService ticketService;
    private final ShowService showService;

    @GetMapping(value = "/checkLogin")
    public ResponseEntity<?> checkLogin(@RequestParam String email, String password) {
        return ResponseEntity.ok(userService.checkLogin(email, password));
    }

    @GetMapping(value = "/tickets")
    public ResponseEntity<?> tickets(@RequestParam String email, String password) {
        return ResponseEntity.ok(userService.checkLogin(email, password));
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        return ResponseEntity.ok(request.getSession().invalidate());
    }

    @GetMapping(value = "/registerUser")
    public ResponseEntity<?> registerUser(User user)
    {
      userService.create(user);

      return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/createMovie")
    public ResponseEntity<?> login(@RequestParam String email, String password) {
        return ResponseEntity.ok(showService.create(show));
    }

    @GetMapping(value = "/hall")
    public ResponseEntity<?> hall()
    {
        return ResponseEntity.ok(showService.findAll());
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/deleteUser")
    public ResponseEntity<?> login(@RequestParam int userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }

    @GetMapping(value = "/buyTicket")
    public ResponseEntity<?> login(@RequestParam Ticket ticket, int userId) {
        TicketRepository.save(ticket, userId);
        return ResponseEntity.ok(ticketService.buyTicket(ticket.getId(), userId));
    }

    @GetMapping(value = "/cancelTicket")
    public ResponseEntity<?> login(@RequestParam int ticketId) {
        return ResponseEntity.ok(ticketService.cancel(ticketId));
    }
}
