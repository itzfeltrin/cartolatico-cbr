package cartola.gamer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cartola.gamer.web.model.TiposSO;
import cartola.gamer.web.repository.TiposSORepository;


import java.util.Optional;

import javax.validation.Valid;


@Controller
@RequestMapping("/gerenciar-tipos-so")
public class TiposSOController{

    @Autowired
    private TiposSORepository tiposSORepository;

    private final String routeBase = "/gerenciar-tipos-so";
    private final String moduloTitulo = "Gerenciar Tipos S.O.";

    public TiposSOController(TiposSORepository tiposSORepository){
        this.tiposSORepository = tiposSORepository;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registros", this.tiposSORepository.findAll());

        return "/tipos_so/index";
    }

    // CREATE
    @GetMapping("/novo")
    public String create(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", new TiposSO());

        return "/tipos_so/create_edit";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("registro") TiposSO registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        if(result.hasErrors()){
            //redirectAttributes.addFlashAttribute("error", "Falha ao tentar efetuar o cadastro!");
            return "/tipos_so/create_edit";
        }

        this.tiposSORepository.save(registro);
        redirectAttributes.addFlashAttribute("success", "Cadastro realizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // EDIT
    @GetMapping("/{id}/editar")
    public String edit(@PathVariable("id") Long id, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", this.tiposSORepository.findById(id));

        return "/tipos_so/create_edit";
    }

    @PutMapping("/{id}/atualizar")
    public String update(@PathVariable("id") Long id, @ModelAttribute("registro") TiposSO registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);

        if(result.hasErrors()){
            return "/tipos_so/create_edit";
        }

        Optional<TiposSO> findResult = this.tiposSORepository.findById(registro.getId());

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar atualizar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        // Salva os novos dados no BD
        findResult.get().setDescricao(registro.getDescricao());
        this.tiposSORepository.save(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro atualizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // DELETE
    @DeleteMapping("/{id}/deletar")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model){
        Optional<TiposSO> findResult = this.tiposSORepository.findById(id);

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar deletar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        this.tiposSORepository.delete(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro deletado com sucesso!");

        return "redirect:" + this.routeBase;
    }
}
