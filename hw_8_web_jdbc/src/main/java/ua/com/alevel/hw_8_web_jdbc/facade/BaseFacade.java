package ua.com.alevel.hw_8_web_jdbc.facade;


import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_8_web_jdbc.dto.RequestDto;
import ua.com.alevel.hw_8_web_jdbc.dto.ResponseDto;
import ua.com.alevel.hw_8_web_jdbc.view.PageData;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto, RES_FULL extends RES> {

    void create(REQ req);

    void update(REQ req, Long id);

    void delete(Long id);

    RES_FULL findById(Long id);

    boolean exists(Long id);

    PageData<RES> findAll(WebRequest request);

    PageData<RES> findAll();
}