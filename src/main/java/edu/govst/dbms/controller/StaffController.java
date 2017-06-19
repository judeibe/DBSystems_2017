package edu.govst.dbms.controller;

import edu.govst.dbms.model.Staff;
import edu.govst.dbms.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping(value = "/staff/new", method = RequestMethod.POST)
    public String save(Staff staff, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("There are errors! {}", bindingResult);
            return "staff/new";
        }

        log.info("Creating new staff: {}", staff);
        staffService.create(staff);

        return "/staff";
    }

    @RequestMapping(value = "/staff/new", method = RequestMethod.GET)
    public ModelAndView create() {
        log.info("Showing staff add view");
        ModelAndView modelAndView = new ModelAndView("staff/new");
        modelAndView.addObject(new Staff());
        return modelAndView;
    }
}
