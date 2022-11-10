package cartola.gamer.web.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cartola.gamer.cbr.RealizaConsultas;
import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import cartola.gamer.cbr.descriptions.Clube;
import cartola.gamer.cbr.modelo.CasosRetornadosModelo;
import cartola.gamer.cbr.modelo.RetornoModelo;
import cartola.gamer.model.SearchQuery;
import cartola.gamer.web.utils.HibernateUtil;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;

@Controller
@RequestMapping("/")
public class IndexController {

    private List<Clube> getClubes() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Query<cartola.gamer.cbr.descriptions.Clube> query = session.createQuery("from Clube", Clube.class);
        List<Clube> clubes = query.list();
        session.close();

        return clubes;
    }

    @GetMapping
    public String index(Model model) {
        List<Clube> clubes = this.getClubes();

        model.addAttribute("clubes", clubes);
        model.addAttribute("query", new SearchQuery());

        return "index";
    }

    @PostMapping("/busca")
    public String submitQuery(@ModelAttribute SearchQuery query, Model model) {
        model.addAttribute("query", query);

        RealizaConsultas realizaConsultas = new RealizaConsultas();

        CaseBaseDescription gameState = new CaseBaseDescription();
        gameState.setPosicao(query.getPosicao().toLowerCase());
        gameState.setCusto(query.getCusto());
        gameState.setMando(query.getMando());
        gameState.setId_oponente(query.getId_oponente());
        gameState.setStatus(7);

        try {
            RetornoModelo result = realizaConsultas.retornaConsulta(gameState);
            List<CasosRetornadosModelo> cases = result.getListaCasosRetornados();

            for (CasosRetornadosModelo retrievedCase : cases) {
                System.out.println(retrievedCase.getSimilaridadeDoCasoComAconsulta());
            }
            System.out.println();

            model.addAttribute("cases", cases);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Clube> clubes = this.getClubes();

        model.addAttribute("clubes", clubes);

        return "resultado";
    }
}
