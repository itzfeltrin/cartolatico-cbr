package cartola.gamer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cartola.gamer.web.model.Passos;
import cartola.gamer.web.repository.PassosRepository;


import java.util.Optional;

import javax.validation.Valid;


@Controller
@RequestMapping("/gerenciar-passos")
public class PassosController{

    @Autowired
    private PassosRepository passosRepository;

    private final String routeBase = "/gerenciar-passos";
    private final String moduloTitulo = "Gerenciar Passos (Solução)";

    public PassosController(PassosRepository passosRepository){
        this.passosRepository = passosRepository;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registros", this.passosRepository.findAll());

        return "/passos/index";
    }

    // CREATE
    @GetMapping("/novo")
    public String create(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", new Passos());

        return "/passos/create_edit";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("registro") Passos registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        if(result.hasErrors()){
            //redirectAttributes.addFlashAttribute("error", "Falha ao tentar efetuar o cadastro!");
            return "/passos/create_edit";
        }

        this.passosRepository.save(registro);
        redirectAttributes.addFlashAttribute("success", "Cadastro realizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // EDIT
    @GetMapping("/{id}/editar")
    public String edit(@PathVariable("id") Long id, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", this.passosRepository.findById(id));

        return "/passos/create_edit";
    }

    @PutMapping("/{id}/atualizar")
    public String update(@PathVariable("id") Long id,  @ModelAttribute("registro") Passos registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);

        if(result.hasErrors()){
            return "/passos/create_edit";
        }

        Optional<Passos> findResult = this.passosRepository.findById(registro.getId());

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar atualizar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        // Salva os novos dados no BD
        findResult.get().setDescricao(registro.getDescricao());
        this.passosRepository.save(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro atualizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // DELETE
    @DeleteMapping("/{id}/deletar")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model){
        Optional<Passos> findResult = this.passosRepository.findById(id);

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar deletar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        this.passosRepository.delete(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro deletado com sucesso!");

        return "redirect:" + this.routeBase;
    }
}
