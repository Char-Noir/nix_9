package ua.com.alevel.module3.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.module3.dto.AccountResponseDto;
import ua.com.alevel.module3.dto.OperationRequestDto;
import ua.com.alevel.module3.dto.OperationResponseDto;
import ua.com.alevel.module3.facade.AccountFacade;
import ua.com.alevel.module3.facade.OperationFacade;
import ua.com.alevel.module3.view.PageData;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/operations")
public class OperationController {

    private final OperationFacade clientFacade;
    private final AccountFacade accountFacade;

    public OperationController(OperationFacade clientFacade, AccountFacade accountFacade) {
        this.clientFacade = clientFacade;
        this.accountFacade = accountFacade;
    }


    @GetMapping("/outcome/{id}")
    public String add(@PathVariable Long id, Model model, WebRequest webRequest) {
        PageData<AccountResponseDto> all = accountFacade.findAll();
        all.setItems(all.getItems().stream()
                .filter(account -> !Objects.equals(account.getId(), id))
                .toList());
        OperationRequestDto operation = new OperationRequestDto();
        operation.setOutcomeid(id);
        model.addAttribute("clients", all.getItems());
        model.addAttribute("operation", operation);
        return "pages/operation/new";
    }

    @PostMapping("/new")
    public String newOp(@ModelAttribute("operation") OperationRequestDto dto) {
        System.out.println(dto);
        clientFacade.create(dto);
        return "redirect:/";
    }

    @GetMapping("/csv/{id}/vupiska.csv")
    public String getCsv(ModelMap model, @PathVariable Long id){
        AbstractController.HeaderName[] columnNames = new AbstractController.HeaderName[]{
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("InCome", "incomeid", "incomeid"),
                new AbstractController.HeaderName("OutCome", "outcomeid", "outcomeid"),
                new AbstractController.HeaderName("DateTime", "datetime", "datetime"),
                new AbstractController.HeaderName("Purpose", "purpose", "purpose"),
                new AbstractController.HeaderName("Money", "sum", "sum"),
                new AbstractController.HeaderName("Category", "null", "null")
        };
        PageData<OperationResponseDto> response = clientFacade.findAllByAccount(id);
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
                    data.setOrder("idoperation");
                }
            }
            headerDataList.add(data);
        }
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Accounts");
        model.addAttribute("id", id);
        return "pages/operation/all";
    }

}
