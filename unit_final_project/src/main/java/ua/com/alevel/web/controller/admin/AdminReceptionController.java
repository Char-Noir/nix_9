package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.ReceptionFacade;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.reception.ReceptionRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.reception.ReceptionResponseDto;

import static ua.com.alevel.web.controller.ControllerUtils.RECEPTION_COLUMNS;

@Controller
@RequestMapping("/admin/reception")
public class AdminReceptionController extends AbstractController {


    private final ReceptionFacade receptionFacade;

    public AdminReceptionController(ReceptionFacade receptionFacade) {
        this.receptionFacade = receptionFacade;
    }

    @GetMapping("/s")
    public String findReceptions(Model model, WebRequest webRequest) {
        PageData<ReceptionResponseDto> response = receptionFacade.findAll(webRequest);
        initDataTable(response, RECEPTION_COLUMNS, model);
        model.addAttribute("createUrl", "/admin/reception/s/all");
        model.addAttribute("nextUrl", "/admin/reception/details/");
        model.addAttribute("cardHeader", "All Receptions");
        return "pages/admin/receptions/all";
    }

    @PostMapping("s/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/reception/s");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        ReceptionResponseDto dto = receptionFacade.findById(id);
        model.addAttribute("reception", dto);
        return "pages/admin/receptions/details";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("reception", new ReceptionRequestDto());
        return "pages/admin/receptions/add";
    }

    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable @ValidId Long id) {
        ReceptionResponseDto reception = receptionFacade.findById(id);
        model.addAttribute("reception", new ReceptionRequestDto());
        model.addAttribute("receptionOld", reception);
        return "pages/admin/receptions/update";
    }

    @PostMapping("/edit/post/{id}")
    public String updatePost(@ModelAttribute(name = "reception") ReceptionRequestDto dto, @PathVariable @ValidId Long id) {
        ReceptionResponseDto reception = receptionFacade.findById(id);
        receptionFacade.update(dto, reception.getId());
        return "redirect:/admin/reception/details/" + id;
    }

    @PostMapping("/add/post")
    public String addPost(@ModelAttribute ReceptionRequestDto dto) {
        receptionFacade.create(dto);
        return "redirect:/admin/reception/s";
    }


}
