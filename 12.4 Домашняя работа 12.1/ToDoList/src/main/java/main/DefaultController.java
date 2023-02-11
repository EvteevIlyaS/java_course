package main;

import main.model.Doing;
import main.model.DoingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class DefaultController {
    @Autowired
    private DoingRepository doingRepository;

    @Value("${spring.datasource.url}")
    private String urlDataBase;

    @RequestMapping("/")
    public String index(Model model) {
        List<Doing> doings = new ArrayList<>();
        doingRepository.findAll().iterator().forEachRemaining(doings::add);
        model.addAttribute("doings", doings);
        model.addAttribute("urlDataBase", urlDataBase);
        return "index";
    }
}
