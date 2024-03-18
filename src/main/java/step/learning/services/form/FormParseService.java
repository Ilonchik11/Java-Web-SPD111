package step.learning.services.form;

import javax.servlet.http.HttpServletRequest;

public interface FormParseService {
    FormParseResult parse(HttpServletRequest request) ;
}
