package cous.stic4.scolariteapi.controller;
import cous.stic4.scolariteapi.dao.ClasseRepository;
import cous.stic4.scolariteapi.dao.EtudiantRepository;
import cous.stic4.scolariteapi.dao.InscriptionRepository;
import cous.stic4.scolariteapi.model.Classe;
import cous.stic4.scolariteapi.model.Etudiant;
import cous.stic4.scolariteapi.model.Inscription;
import cous.stic4.scolariteapi.utils.Utilitaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/inscription")
@RestController
public class InscriptionController
{
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @GetMapping("/add")
    public String add(Model model){
        Inscription inscription=new Inscription();
        model.addAttribute("inscription",inscription);
        model.addAttribute("classes",classeRepository.findAll());
        inscription.setAnnee_academic(Utilitaire.anneeacademique());
        return "inscription/add";
    }
    @GetMapping("/classe/{id}")
    public @ResponseBody
    Classe findClasse(@PathVariable("id") long id) {
        Optional<Classe> c = classeRepository.findById(id);
        if (c.isPresent()){
            return c.get();
        }
        return new Classe();
    }
    @GetMapping("/etudiant/{matricule}")
    public @ResponseBody
    Etudiant findEtudiant(@PathVariable("matricule") String matricule) {
        Etudiant e = etudiantRepository.findByMatricule(matricule);
        if (e!=null){
            return e;
        }
        return new Etudiant();
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("inscription") Inscription i)
    {
        if(i.getEtudiant().getId()!=0)
        {
            i.setEtudiant(etudiantRepository.getOne(i.getEtudiant().getId()));
        }
        i.setAnnee_academic(Utilitaire.anneeacademique());
        inscriptionRepository.save(i);
        return "redirect:/inscription/";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("classes",classeRepository.findAll());
        model.addAttribute("annees",Utilitaire.anneeacademiquelist());
        model.addAttribute("inscriptions",new ArrayList<Inscription>());
        return "inscription/list";
    }
    @PostMapping("/list")
    public String list(long classe_id, String annee, Model model)
    {
        model.addAttribute("classes",classeRepository.findAll());
        model.addAttribute("annees",Utilitaire.anneeacademiquelist());
        model.addAttribute("inscriptions",inscriptionRepository.findEtudiantParClasseEtAnnee(classe_id,annee));
        model.addAttribute("id_classe",classe_id);
        model.addAttribute("an",annee);
        return "inscription/list";
    }
    @GetMapping("/export")
    public void export(@RequestParam("annee") String annee, @RequestParam("classe_id") long classe_id, HttpServletResponse response, Model model) throws IOException {
       response.setContentType("application/pdf");
       String headerkey="Content-Disposition";
       String headervalue="attachment; fileName=etudiant_"+classe_id+"_"+annee+".pdf";
       response.setHeader(headerkey,headervalue);
       List<Inscription> inscrits=inscriptionRepository.findEtudiantParClasseEtAnnee(classe_id,annee);
    }
}
