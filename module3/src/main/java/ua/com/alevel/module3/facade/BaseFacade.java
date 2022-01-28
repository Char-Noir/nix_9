package ua.com.alevel.module3.facade;


import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.module3.dto.RequestDto;
import ua.com.alevel.module3.dto.ResponseDto;
import ua.com.alevel.module3.view.PageData;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {



    PageData<RES> findAll(WebRequest request);

    PageData<RES> findAll();
}