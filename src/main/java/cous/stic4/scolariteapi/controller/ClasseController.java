package cous.stic4.scolariteapi.controller;


import cous.stic4.scolariteapi.dao.ClasseRepository;
import cous.stic4.scolariteapi.model.Classe;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classe")
public class ClasseController {
    @Autowired
    private ClasseRepository classeRepository;
    @GetMapping("/")
    public List<Classe> index(){
      return classeRepository.findAll() ;
    }

    @GetMapping("/edit/{id}")
    public Classe edit(@PathVariable("id")  long id , Model model){
        Optional<Classe> cl=classeRepository.findById(id);
        Classe classe = new Classe();
        if (cl.isPresent()){
            classe=cl.get();
        }
      return classe;

    }
    @PostMapping("/add")
    public  Classe add(@RequestBody Classe c)
    {
        return classeRepository.save(c);
    }

}
