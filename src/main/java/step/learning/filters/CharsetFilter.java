package step.learning.filters;

import com.google.inject.Singleton;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Singleton // При використанні Guice необхідна анотація @Singleton
public class CharsetFilter implements Filter {

    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /* При ініціалізації фільтру передається конфігурація, через неї
        * можна дізнатися про файлові шляхи та інші параметри оточення.
        * Зберігаємо для можливості подальшого доступу */
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /* chain - ланцюг фільтрів, яким проходе запит (і відповідь у зворотньому напрямі)
        * завдання фільтру викликати перехід до наступного шару (або зупинити роботу) */
        request.setCharacterEncoding(StandardCharsets.UTF_8.name()); // прямий хід до виклику chain
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        chain.doFilter(request, response);

        // код, описаний після chain буде виконуватись на зворотньому шляху
    }

    @Override
    public void destroy() {
        this.filterConfig = null; // аналог delete - змешення кількості посилань на об'єкт
    }
}
/*
Фільтри (сервлетні фільтри) - концепція Middleware у JSP
утворення каскаду обробників запиту, які передають (або не передають)
процес один іншому. Фільтри спрацьовують раніше за сервлети, тому
вони не залежать від методу запиту.
Прямий хід - проходження запиту до JSP (до представлення).
Зворотній хід - повернення згенерованого HTML до сервера (Tomcat).
 */