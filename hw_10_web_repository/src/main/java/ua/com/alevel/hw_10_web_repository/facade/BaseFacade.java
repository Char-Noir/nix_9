package ua.com.alevel.hw_10_web_repository.facade;


import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_10_web_repository.dto.RequestDto;
import ua.com.alevel.hw_10_web_repository.dto.ResponseDto;
import ua.com.alevel.hw_10_web_repository.view.PageData;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto, RES_FULL extends RES> {

    void create(REQ req);

    void update(REQ req, Long id);

    void delete(Long id);

    RES_FULL findById(Long id);


    PageData<RES> findAll(WebRequest request);

    PageData<RES> findAll();
}