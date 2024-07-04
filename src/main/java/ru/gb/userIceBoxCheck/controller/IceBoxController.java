package ru.gb.userIceBoxCheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.userIceBoxCheck.model.IceBox;
import ru.gb.userIceBoxCheck.repository.IceBoxRepository;
import ru.gb.userIceBoxCheck.service.IseBoxService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/iceBox")
public class IceBoxController {

    @Autowired
    private IseBoxService iseBoxService;
    @Autowired
    private IceBoxRepository iceBoxRepository;

    @GetMapping
    public List<IceBox> findAll(){
        return iseBoxService.findAll();
    }

    @DeleteMapping("/deleteIceBox/{id}")
    public String deleteIceBox(@PathVariable Long id){
        iseBoxService.deleteById(id);
        return "IceBox has been deleted";
    }

    @GetMapping("/{id}")
    public Optional<IceBox> findById(@PathVariable Long id){
        return iseBoxService.findById(id);
    }

    @GetMapping("/newIceBox")
    public String newIceBox(Model model){
        model.addAttribute("newIceBox", iceBoxRepository.findAll());
        return "IceBox has been created";
    }

    @PostMapping("/newIceBox")
    public String newIceBox(@RequestBody IceBox iceBox, Model model){
        iseBoxService.saveIseBox(iseBoxService.addIceBox(iceBox.getList()));
        model.addAttribute("iceBox", iseBoxService.findAll());
        return "IceBox has been created";
    }
}
