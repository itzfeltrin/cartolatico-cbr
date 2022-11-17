package cartola.gamer.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
import cartola.gamer.cbr.modelo.ResultCase;
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

        int[] rodadas = IntStream.rangeClosed(1, 30).toArray();

        model.addAttribute("query", new SearchQuery());
        model.addAttribute("clubes", clubes);
        model.addAttribute("rodadas", rodadas);

        return "index";
    }

    @PostMapping("/busca")
    public String submitQuery(@ModelAttribute SearchQuery query, Model model) {
        model.addAttribute("query", query);

        RealizaConsultas realizaConsultas = new RealizaConsultas();

        CaseBaseDescription gameState = new CaseBaseDescription();
        gameState.setPosicao(query.getPosicao().toLowerCase());
        gameState.setCusto(query.getCusto());
        gameState.setId_rodada(query.getId_rodada());
        if (query.getSomente_provaveis()) {
            gameState.setStatus(7);
        } else {
            gameState.setStatus(null);
        }

        try {
            List<ResultCase> resultCases = realizaConsultas.retornaConsulta(gameState);
            Collections.sort(resultCases); // retorna do menor pro maior
            Collections.reverse(resultCases);

            model.addAttribute("resultCases", resultCases);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Clube> clubes = this.getClubes();

        model.addAttribute("clubes", clubes);

        return "resultado";
    }
}
