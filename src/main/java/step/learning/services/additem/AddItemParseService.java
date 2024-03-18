package step.learning.services.additem;

import javax.servlet.http.HttpServletRequest;

public interface AddItemParseService {
    AddItemParseResult parse(HttpServletRequest request) ;
}
