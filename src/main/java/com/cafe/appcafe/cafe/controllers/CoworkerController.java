package com.cafe.appcafe.cafe.controllers;

import com.cafe.appcafe.cafe.exeption.ApiRequestException;
import com.cafe.appcafe.cafe.models.Coworker;
import com.cafe.appcafe.cafe.models.Position;
import com.cafe.appcafe.cafe.service.CoworkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CoworkerController {

    private final CoworkerService coworkerService;

    @Autowired
    public CoworkerController(CoworkerService coworkerService) {
        this.coworkerService = coworkerService;
    }

    @GetMapping("/coworker")
    @PreAuthorize("hasAuthority('developers:read')")
    public String findAll(Model model) {
        List<Coworker> coworkers = coworkerService.findAll();
        model.addAttribute("coworker", coworkers);
        return "coworker/coworker-list";
    }

    @GetMapping("/coworker-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createCoworkerForm(Model model) {
        List<Position> positions = coworkerService.getPositionList();
        model.addAttribute("pos", positions);
        return "coworker/coworker-create";
    }


    @PostMapping("/coworker-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createCoworker(@RequestParam String fio,
                                 @RequestParam String namePosition) {
        coworkerService.saveCoworker(fio, namePosition);
        return "redirect:/coworker";
    }

    @GetMapping("/coworker-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateCoworkerForm(@PathVariable(value = "id") long id, Model model) {
        List<Position> positions = coworkerService.getPositionList();

        if (coworkerService.coworkerList(id).isEmpty()) {
            return "redirect:/coworker";
        }
        model.addAttribute("coworkers", coworkerService.coworkerList(id));
        model.addAttribute("pos", positions);
        return "coworker/coworker-update";
    }

    @PostMapping("/coworker-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateCoworker(@PathVariable(value = "id") long id,
                                 @RequestParam String fio,
                                 @RequestParam String namePosition) {
        coworkerService.updateCoworker(id, fio, namePosition);
        return "redirect:/coworker";
    }

    @GetMapping("/coworker-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String coworkerDelete(@PathVariable(value = "id") long id, Model model) {
        coworkerService.deleteCoworker(id);
        return "redirect:/coworker";
    }

}
