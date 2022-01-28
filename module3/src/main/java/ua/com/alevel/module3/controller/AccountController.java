package ua.com.alevel.module3.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.module3.dto.AccountResponseDto;
import ua.com.alevel.module3.dto.OperationRequestDto;
import ua.com.alevel.module3.facade.AccountFacade;
import ua.com.alevel.module3.view.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountFacade clientFacade;

    public AccountController(AccountFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    //findAll
    @GetMapping("/client/{id}")
    public String findAllByCLient(@PathVariable Long id, Model model, WebRequest webRequest) {
        AbstractController.HeaderName[] columnNames = new AbstractController.HeaderName[]{
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("Client", "idaccount", "idaccount"),
                new AbstractController.HeaderName("Balance", "balance", "balance"),
                new AbstractController.HeaderName("Add Operation", null, null)
        };
        PageData<AccountResponseDto> response = clientFacade.findAllByClient(webRequest, id);
        response.initPaginationState(response.getCurrentPage());
        List<AbstractController.HeaderData> headerDataList = new ArrayList<>();
        for (AbstractController.HeaderName headerName : columnNames) {
            AbstractController.HeaderData data = new AbstractController.HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                System.out.println(response.getSort());
                if (response.getSort().equals(headerName.getDbName())) {
                    System.out.println(response.getOrder());
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder("idaccount");
                }
            }
            headerDataList.add(data);
        }

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/accounts/client/all/" + id);
        model.addAttribute("urlNext", "/operations/outcome/");
        model.addAttribute("urlPast", "");
        model.addAttribute("buttonName", "Add operation");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Accounts by Client");
        return "pages/account/all";
    }


    //findAll
    @PostMapping("/client/all/{id}")
    public ModelAndView findAllRedirectByClient(@PathVariable Long id, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/accounts/client/" + id, model);
    }

    //findAll
    @GetMapping()
    public String findAll(Model model, WebRequest webRequest) {
        AbstractController.HeaderName[] columnNames = new AbstractController.HeaderName[]{
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("Client", "idaccount", "idaccount"),
                new AbstractController.HeaderName("Balance", "balance", "balance"),
                new AbstractController.HeaderName("Get csv", null, null)
        };
        PageData<AccountResponseDto> response = clientFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<AbstractController.HeaderData> headerDataList = new ArrayList<>();
        for (AbstractController.HeaderName headerName : columnNames) {
            AbstractController.HeaderData data = new AbstractController.HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                System.out.println(response.getSort());
                if (response.getSort().equals(headerName.getDbName())) {
                    System.out.println(response.getOrder());
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder("idaccount");
                }
            }
            headerDataList.add(data);
        }

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/accounts/all");
        model.addAttribute("urlNext", "/operations/csv/");
        model.addAttribute("urlPast", "/vupiska.csv");
        model.addAttribute("buttonName", "Get csv");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Accounts");
        return "pages/account/all";
    }


    //findAll
    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/accounts/", model);
    }


}
