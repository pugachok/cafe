package com.cafe.appcafe.cafe.controllers;

import com.cafe.appcafe.cafe.models.Position;
import com.cafe.appcafe.cafe.service.PositionService;
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
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/position")
    @PreAuthorize("hasAuthority('developers:read')")
    public String findAll(Model model) {
        List<Position> positions = positionService.findAll();
        model.addAttribute("position", positions);
        return "position/position-list";
    }

    @GetMapping("/position-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createPositionForm() {
        return "position/position-create";
    }

    @PostMapping("/position-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createPosition(@RequestParam String namePosition) {
        positionService.savePosition(namePosition);
        return "redirect:/position";
    }

    @GetMapping("/position-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updatePositionForm(@PathVariable(value = "id") long id, Model model) {
        if (positionService.positionList(id).isEmpty()) {
            return "redirect:/position";
        }
        model.addAttribute("position", positionService.positionList(id));
        return "position/position-update";
    }

    @PostMapping("/position-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updatePosition(@PathVariable(value = "id") long id, @RequestParam String namePosition, Model model) {
        positionService.updatePosition(id, namePosition);
        return "redirect:/position";
    }

    @GetMapping("/page-400")
    private String getPage400() {
        return "error/page-400";
    }

    @GetMapping("/page-500")
    private String getPage500() {
        return "error/page-500";
    }

    @GetMapping("/position-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String positionDelete(@PathVariable(value = "id") long id) {
        try{
            positionService.delete(id);
            return "redirect:/position";
        } catch (Exception e) {
            return "error/page-400";
        }

    }

}
