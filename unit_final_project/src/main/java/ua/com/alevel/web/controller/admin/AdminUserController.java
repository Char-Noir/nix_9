package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.user.UserRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

import static ua.com.alevel.web.controller.ControllerUtils.USER_COLUMNS;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends AbstractController {

    private final UserFacade userFacade;

    public AdminUserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/s")
    public String findUsers(Model model, WebRequest webRequest) {
        PageData<UserResponseDto> response = userFacade.findAll(webRequest);
        initDataTable(response, USER_COLUMNS, model);
        model.addAttribute("createUrl", "/admin/user/s/all");
        model.addAttribute("nextUrl", "/admin/user/details/");
        model.addAttribute("cardHeader", "All Users");
        return "pages/admin/users/all";
    }

    @PostMapping("s/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "user/s");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        UserResponseDto dto = userFacade.findById(id);
        model.addAttribute("user", dto);
        return "pages/admin/users/details";
    }

    @GetMapping("/add/")
    public String add(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "pages/admin/users/add";
    }

    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable @ValidId Long id) {
        UserResponseDto user = userFacade.findById(id);
        model.addAttribute("user", new UserRequestDto());
        model.addAttribute("userOld", user);
        return "pages/admin/users/update";
    }

    @PostMapping("/edit/post/{id}")
    public String updatePost(@ModelAttribute(name = "user") UserRequestDto dto, @PathVariable @ValidId Long id) {
        userFacade.update(dto, id);
        return "redirect:/admin/user/details/" + id;
    }

    @PostMapping("/delete/post/{id}")
    public String deletePost(@ModelAttribute(name = "user") UserRequestDto dto, @PathVariable @ValidId Long id) {
        userFacade.delete(id);
        return "redirect:/admin/user/details/" + id;
    }

    @PostMapping("/add/post")
    public String addPost(@ModelAttribute UserRequestDto dto) {
        userFacade.create(dto);
        return "redirect:/admin/user/s";
    }


}
