package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.model.Cellphone;
import shop.service.CellphoneService;

@Controller
public class IndexController {
    private CellphoneService cellphoneService;
   
    @Autowired
    public IndexController(CellphoneService cellphoneService) {
        this.cellphoneService = cellphoneService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(Model model, 
                        Cellphone cellphone) {
        System.out.println("手机搜索条件: " + cellphone);
        List<Cellphone> cellphones = cellphoneService.search(cellphone);
        model.addAttribute("cellphones", cellphones);
        return "index";
    }
}
