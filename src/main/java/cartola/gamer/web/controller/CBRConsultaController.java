package cartola.gamer.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cartola.gamer.cbr.RealizaConsultas;
import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;



@Controller
@RequestMapping("/consultar-casos")
public class CBRConsultaController{

/*    @Autowired
    private BaseCasosRepository tiposIncidentesRepository;*/

    private final String routeBase = "/consultar-casos";
    private final String moduloTitulo = "Consultar Casos";

    /*public CBRConsultaController(BaseCasosRepository tiposIncidentesRepository){
        this.tiposIncidentesRepository = tiposIncidentesRepository;
    }*/

    @GetMapping()
    public String index(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        //model.addAttribute("registros", this.tiposIncidentesRepository.findAll());
        // Define a consulta
        CaseBaseDescription description = new CaseBaseDescription();
        description.setTipo_incidente(1);
        description.setIp_origem("192.168.0.1");
        description.setSistema_operacional(1);

        RealizaConsultas consulta = new RealizaConsultas();
        try {
            System.out.println("Teste CBR");
            System.out.println(consulta.retornaConsulta(description));

        } catch (ExecutionException e) {
            System.out.println("catch do main");
            e.printStackTrace();
        }

        return "/cbr/index";
    }
/*
    // CREATE
    @GetMapping("/novo")
    public String create(Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        model.addAttribute("registro", new BaseCasos());

        return "/base_casos/create_edit";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("registro") BaseCasos registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);
        if(result.hasErrors()){
            //redirectAttributes.addFlashAttribute("error", "Falha ao tentar efetuar o cadastro!");
            return "/base_casos/create_edit";
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

        return "/base_casos/create_edit";
    }

    @PutMapping("/{id}/atualizar")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("registro") BaseCasos registro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("routeBase", this.routeBase);
        model.addAttribute("moduloTitulo", this.moduloTitulo);

        if(result.hasErrors()){
            return "/base_casos/create_edit";
        }

        Optional<BaseCasos> findResult = this.tiposIncidentesRepository.findById(registro.getId());

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar atualizar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        // Salva os novos dados no BD
        //findResult.get().setDescricao(registro.getDescricao());
        this.tiposIncidentesRepository.save(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro atualizado com sucesso!");

        return "redirect:" + this.routeBase;
    }

    // DELETE
    @DeleteMapping("/{id}/deletar")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model){
        Optional<BaseCasos> findResult = this.tiposIncidentesRepository.findById(id);

        if(!findResult.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Falha ao tentar deletar o cadastro!");
            return "redirect:" + this.routeBase;
        }

        this.tiposIncidentesRepository.delete(findResult.get());
        redirectAttributes.addFlashAttribute("success", "Cadastro deletado com sucesso!");

        return "redirect:" + this.routeBase;
    }*/
}
