package ua.com.alevel.hw_9_web_jpa.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_9_web_jpa.view.PageAndSizeData;
import ua.com.alevel.hw_9_web_jpa.view.SortData;

import java.util.Objects;

public final class WebRequestUtil {

    public static final String DEFAULT_SORT_PARAM_VALUE = "id_patient";
    public static final String DEFAULT_ORDER_PARAM_VALUE = "asc";
    public static final int DEFAULT_PAGE_PARAM_VALUE = 1;
    public static final int DEFAULT_SIZE_PARAM_VALUE = 10;
    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";
    private static final String SORT_PARAM = "sort";
    private static final String ORDER_PARAM = "order";

    private WebRequestUtil() {
    }

    public static PageAndSizeData generatePageAndSizeData(WebRequest webRequest) {
        int page = webRequest.getParameter(PAGE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(webRequest.getParameter(PAGE_PARAM))) : DEFAULT_PAGE_PARAM_VALUE;
        int size = webRequest.getParameter(SIZE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(webRequest.getParameter(SIZE_PARAM))) : DEFAULT_SIZE_PARAM_VALUE;
        return new PageAndSizeData(page, size);
    }

    public static PageAndSizeData defaultPageAndSizeData() {
        return new PageAndSizeData(DEFAULT_PAGE_PARAM_VALUE, DEFAULT_SIZE_PARAM_VALUE);
    }

    public static SortData generateSortData(WebRequest webRequest) {
        String sort = StringUtils.isNotBlank(webRequest.getParameter(SORT_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(SORT_PARAM)) : DEFAULT_SORT_PARAM_VALUE;
        String order = StringUtils.isNotBlank(webRequest.getParameter(ORDER_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(ORDER_PARAM)) : DEFAULT_ORDER_PARAM_VALUE;
        return new SortData(sort, order);
    }

    public static SortData generateSortData(WebRequest webRequest, String defaultSortParamValue) {
        String sort = StringUtils.isNotBlank(webRequest.getParameter(SORT_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(SORT_PARAM)) : defaultSortParamValue;
        String order = StringUtils.isNotBlank(webRequest.getParameter(ORDER_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(ORDER_PARAM)) : DEFAULT_ORDER_PARAM_VALUE;
        return new SortData(sort, order);
    }

    public static SortData defaultSortData() {
        return new SortData(DEFAULT_SORT_PARAM_VALUE, DEFAULT_ORDER_PARAM_VALUE);
    }
}