package cartola.gamer.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cartola.gamer.web.model.TiposFalhas;
import cartola.gamer.web.repository.TiposFalhasRepository;
import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/gerenciar-tipos-falhas")
public class TiposFalhasController{

    @Autowired
    private TiposFalhasRepository tiposFalhasRepository;

    private final String routeBase = "/gerenciar-tipos-falhas";
    private final String moduloTitulo = "Gerenciar Tipos Falhas";

    public TiposFalhasController(TiposFalhasRepository tiposFalhasRepository){
        this.tiposFalhasRepository = tiposFalhasRepository;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registros", this.tiposFalhasRepository.findAll());

        return "/tipos_falhas/index";
    }

    // CREATE
    @GetMapping("/novo")
    public String create(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", new TiposFalhas());

        return "/tipos_falhas/create_edit";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("registro") TiposFalhas registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        if(result.hasErrors()){
            //redirectAttributes.addFlashAttribute("error", "Falha ao tentar efetuar o cadastro!");
            return "/tipos_falhas/create_edit";
        }

        this.tiposFalhasRepository.save(registro);
        redirectAttributes.addFlashAttribute("success", "Cadastro realizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // EDIT
    @GetMapping("/{id}/editar")
    public String edit(@PathVariable("id") Long id, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", this.tiposFalhasRepository.findById(id));

        return "/tipos_falhas/create_edit";
    }

    @PutMapping("/{id}/atualizar")
    public String update(@PathVariable("id") Long id, @ModelAttribute("registro") TiposFalhas registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);

        if(result.hasErrors()){
            return "/tipos_falhas/create_edit";
        }

        Optional<TiposFalhas> findResult = this.tiposFalhasRepository.findById(registro.getId());

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar atualizar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        // Salva os novos dados no BD
        findResult.get().setDescricao(registro.getDescricao());
        this.tiposFalhasRepository.save(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro atualizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // DELETE
    @DeleteMapping("/{id}/deletar")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model){
        Optional<TiposFalhas> findResult = this.tiposFalhasRepository.findById(id);

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar deletar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        this.tiposFalhasRepository.delete(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro deletado com sucesso!");

        return "redirect:" + this.routeBase;
    }
}
