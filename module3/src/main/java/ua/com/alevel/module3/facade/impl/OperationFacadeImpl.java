package ua.com.alevel.module3.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.module3.dto.AccountResponseDto;
import ua.com.alevel.module3.dto.OperationRequestDto;
import ua.com.alevel.module3.dto.OperationResponseDto;
import ua.com.alevel.module3.facade.OperationFacade;
import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.Account;
import ua.com.alevel.module3.persistense.entity.Operation;
import ua.com.alevel.module3.service.OperationService;
import ua.com.alevel.module3.view.PageAndSizeData;
import ua.com.alevel.module3.view.PageData;
import ua.com.alevel.module3.view.SortData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationFacadeImpl implements OperationFacade {

    final OperationService operationService;

    public OperationFacadeImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public PageData<OperationResponseDto> findAll(WebRequest request) {
        return null;
    }

    @Override
    public PageData<OperationResponseDto> findAll() {
        return null;
    }

    @Override
    public void create(OperationRequestDto req) {
        operationService.create(req.getOperation());
    }

    @Override
    public void update(OperationRequestDto req, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PageData<OperationResponseDto> findAllByAccount(Long id) {
        PageAndSizeData pageAndSizeData = new PageAndSizeData(1, -1);
        SortData sortData = new SortData("id", "asc");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Operation> all = operationService.findAllByAccount(dataTableRequest,id);
        List<OperationResponseDto> list = all.getItems().
                stream().
                map(OperationResponseDto::new).
                collect(Collectors.toList());

        PageData<OperationResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.setCurrentShowFromEntries(((long) (pageAndSizeData.getPage() - 1) * pageData.getPageSize() + 1));
        pageData.setCurrentShowToEntries(Math.min(((long) (pageAndSizeData.getPage()) * pageData.getPageSize()), all.getItemsSize()));
        pageData.setTotalPageSize((int) Math.ceil(((double) all.getItemsSize() / (double) pageData.getPageSize())));
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }
}
