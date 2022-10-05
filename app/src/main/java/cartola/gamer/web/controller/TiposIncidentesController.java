package cartola.gamer.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cartola.gamer.web.model.TiposIncidentes;
import cartola.gamer.web.repository.TiposIncidentesRepository;

import java.util.Optional;

import javax.validation.Valid;


@Controller
@RequestMapping("/gerenciar-tipos-incidentes")
public class TiposIncidentesController{

    @Autowired
    private TiposIncidentesRepository tiposIncidentesRepository;

    private final String routeBase = "/gerenciar-tipos-incidentes";
    private final String moduloTitulo = "Gerenciar Tipos Incidentes";

    public TiposIncidentesController(TiposIncidentesRepository tiposIncidentesRepository){
        this.tiposIncidentesRepository = tiposIncidentesRepository;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registros", this.tiposIncidentesRepository.findAll());

        return "/tipos_incidentes/index";
    }

    // CREATE
    @GetMapping("/novo")
    public String create(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", new TiposIncidentes());

        return "/tipos_incidentes/create_edit";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("registro") TiposIncidentes registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        if(result.hasErrors()){
            //redirectAttributes.addFlashAttribute("error", "Falha ao tentar efetuar o cadastro!");
            return "/tipos_incidentes/create_edit";
        }

        this.tiposIncidentesRepository.save(registro);
        redirectAttributes.addFlashAttribute("success", "Cadastro realizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // EDIT
    @GetMapping("/{id}/editar")
    public String edit(@PathVariable("id") Long id, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", this.tiposIncidentesRepository.findById(id));

        return "/tipos_incidentes/create_edit";
    }

    @PutMapping("/{id}/atualizar")
    public String update(@PathVariable("id") Long id, @ModelAttribute("registro") TiposIncidentes registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);

        if(result.hasErrors()){
            return "/tipos_incidentes/create_edit";
        }

        Optional<TiposIncidentes> findResult = this.tiposIncidentesRepository.findById(registro.getId());

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar atualizar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        // Salva os novos dados no BD
        findResult.get().setDescricao(registro.getDescricao());
        this.tiposIncidentesRepository.save(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro atualizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // DELETE
    @DeleteMapping("/{id}/deletar")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model){
        Optional<TiposIncidentes> findResult = this.tiposIncidentesRepository.findById(id);

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar deletar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        this.tiposIncidentesRepository.delete(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro deletado com sucesso!");

        return "redirect:" + this.routeBase;
    }
}
