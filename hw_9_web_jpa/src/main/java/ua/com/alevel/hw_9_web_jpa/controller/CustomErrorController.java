package ua.com.alevel.hw_9_web_jpa.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error/base");
        String errorMsg;
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400 -> errorMsg = "Http Error Code: 400. Bad Request";
            case 401 -> errorMsg = "Http Error Code: 401. Unauthorized";
            case 404 -> errorMsg = "Http Error Code: 404. Resource not found";
            case 500 -> errorMsg = "Http Error Code: 500. Internal Server Error";
            default -> errorMsg = "Unknown error.";
        }
        errorPage.addObject("showMessage", true);
        errorPage.addObject("errorMessage", errorMsg);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}