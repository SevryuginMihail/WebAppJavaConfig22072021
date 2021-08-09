package sevryugin.spring.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sevryugin.spring.dao.PersonDAO;
import sevryugin.spring.models.Person;

import java.util.List;

/**
 * PeopleController.
 *
 * @author Mihail_Sevryugin
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * Метод для отображения всех людей
     *
     * @param model
     * @return
     */
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    /**
     * Метод для просмотра одного человека
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    /**
     * Метод для создания человека через пустой конструктор
     *
     * @param person
     * @return
     */
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    /**
     * Метод для создания человека - данные из формы
     *
     * @param person - объект из формы
     * @pqrqm bindingResult - оюъект для ошибок валидации
     * @return - редирект на список всех людей
     */
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    /**
     * Метод для перехода к методу редактирования одного человека
     *
     * @param model
     * @param id    - id человека, которого будем редактировать
     * @return - перенаправляет на форму для редактирования имени
     */
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    /**
     * Метод для изминения человека
     *
     * @param person - пришедший из формы объект person
     * @param id     - id изменяемого человека
     * @return
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    /**
     * Метод удаляет одного человека из бд по его id
     *
     * @param id - id человека, которого хотим удалить
     * @return - перенаправляемся на список людей, полученый из бд
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}