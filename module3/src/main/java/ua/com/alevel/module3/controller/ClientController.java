package ua.com.alevel.module3.controller;


import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.module3.dto.ClientResponceDto;
import ua.com.alevel.module3.facade.ClientFacade;
import ua.com.alevel.module3.view.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clients")
public class ClientController extends AbstractController {

    private final ClientFacade clientFacade;

    public ClientController(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    //findAll
    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Email", "email", "email"),
                new HeaderName("Accounts", null, null)
        };
        PageData<ClientResponceDto> response = clientFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnNames) {
            HeaderData data = new HeaderData();
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
                    data.setOrder("idclient");
                }
            }
            headerDataList.add(data);
        }

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/clients/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Clients");
        return "pages/client/all";
    }


    //findAll
    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/clients", model);
    }
}
