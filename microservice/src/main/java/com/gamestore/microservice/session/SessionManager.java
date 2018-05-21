package com.gamestore.microservice.session;

import com.gamestore.microservice.data.rest.login.Login;
import com.gamestore.microservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author leonardo.carmona
 */
@Component
public class SessionManager {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Map<String, Session> session = new HashMap<>();

    public Session login(Login login) {
        Object[] args = new Object[]{login.getUsername(), login.getPassword()};

        try {
            return this.jdbcTemplate.queryForObject("select * from customer where email = ? and password = ?", args, (rs, rowNum) -> {
                Session          session  = new Session();
                Session.Customer customer = new Session.Customer();

                session.setToken(UUID.randomUUID().toString());
                session.setCustomer(customer);

                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));

                this.session.put(session.getToken(), session);

                return session;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Session checkAuthorization(HttpServletRequest request, Function<Session, Boolean> function) throws UnauthorizedException {
        Session session = this.getSession(request);

        if (Boolean.TRUE == function.apply(session)) {
            return session;
        }

        throw new UnauthorizedException();
    }

    public Session checkAuthorization(HttpServletRequest request) throws UnauthorizedException {
        Session session = this.getSession(request);

        if (session == null) {
            throw new UnauthorizedException();
        }

        return session;
    }

    public Session getSession(HttpServletRequest request) {
        return session.get(request.getHeader("Authorization"));
    }

}
