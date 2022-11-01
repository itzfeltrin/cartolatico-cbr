package cartola.gamer.web.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cartola.gamer.cbr.descriptions.Clube;
import cartola.gamer.web.utils.HibernateUtil;

@Controller
@RequestMapping("/")
public class IndexController{
    @GetMapping
    public String index(Model model){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Query<cartola.gamer.cbr.descriptions.Clube> query = session.createQuery("from Clube", Clube.class);
        List<Clube> clubList = query.list();    
        
        model.addAttribute("clubList", clubList);

        return "/index";
    }
}
