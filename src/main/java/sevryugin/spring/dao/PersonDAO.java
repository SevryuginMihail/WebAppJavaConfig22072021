package sevryugin.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSOutput;
import sevryugin.spring.models.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * PersonDao. сделано на JdbcTemplate
 *
 * @author Mihail_Sevryugin
 */
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from ali.person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from ali.person where id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into ali.person values (1,? ,?,?)",
                person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update ali.person set name=?, age=?, email=? where id=?",
                updatedPerson.getName(),updatedPerson.getAge(),updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from ali.person where id=?",id);
    }
}