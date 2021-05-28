package com.cafe.appcafe.cafe.controllers;

import com.cafe.appcafe.cafe.models.Contractor;
import com.cafe.appcafe.cafe.service.ContractorService;
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
public class ContractorController {

    private final ContractorService contractorService;

    @Autowired
    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping("/contractor")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Contractor> contractorList = contractorService.findAll();
        model.addAttribute("contractor", contractorList);
        return "contractor/contractor-list";
    }

    @GetMapping("/contractor-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm() {
        return "contractor/contractor-create";
    }

    @PostMapping("/contractor-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String create(@RequestParam String nameContractor,
                         @RequestParam String address,
                         @RequestParam String phoneNumber) {
        contractorService.save(nameContractor, address, phoneNumber);
        return "redirect:/contractor";
    }

    @GetMapping("/contractor-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable(value = "id") Long id, Model model) {

        if (contractorService.contractorList(id).isEmpty()) {
            return "redirect:/contractor";
        }
        model.addAttribute("contractor", contractorService.contractorList(id));
        return "/contractor/contractor-update";
    }

    @PostMapping("/contractor-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable(value = "id") Long id,
                         @RequestParam String nameContractor,
                         @RequestParam String address,
                         @RequestParam String phoneNumber) {
        contractorService.update(id, nameContractor, address, phoneNumber);
        return "redirect:/contractor";
    }

    @GetMapping("/contractor-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable(value = "id") Long id) {
        contractorService.delete(id);
        return "redirect:/contractor";
    }
}
