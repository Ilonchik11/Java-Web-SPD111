package step.learning.ioc;

import com.google.inject.AbstractModule;
import step.learning.services.db.DbService;
import step.learning.services.db.MySqlDbService;
import step.learning.services.form.FormParseService;
import step.learning.services.form.HybridFormParser;
import step.learning.services.hash.HashService;
import step.learning.services.hash.Md5HashService;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // конфігурація служб
        // Буде запит на HashService -- повернути об'єкт Md5HashService
        bind(HashService.class).to(Md5HashService.class); // ASP: Service.AddSingleton<Hash,Md5>
        bind(DbService.class).to(MySqlDbService.class);
        bind(FormParseService.class).to(HybridFormParser.class);
    }
}

/*
    Інверсія управління (Inversion of Control, IoC) - це архітектурний стиль, згідно з яким
    питання життєвого циклу об'єктів вирішуються окремим спеціальним модулем (контейнером
    залежностей, інжектором). Життєвий цикл об'єкту - CRUD, у простішому випадку мова іде
    про створення (С) об'єктів: чи створювати новий інстанс класу / чи залишати раніше
    створений. Також на модуль IoC покладається задача заповнення (Resolve) об'єктів -
    впровадження (Inject) у них залежностей - посилань на ті самі об'єкти, що їх створює IoC.

    Без IoC                              З IoC
    class Klass {                        class Klass {
    service = new Service()              @Inject Service service
    ...                                  ...
    }                                    }
    ...
    k1 = new Klass()                     k = Injector.Resolve(Klass)
    k2 = new Klass()                     k = Injector.Resolve(Klass)
    у k1 та k2 різні service             у k1 та k2 однакові service
 */

/*
    Впровадження на базі Google Guice
    Spring - аналог
    - підключаємо до проєкту
    <!-- https://mvnrepository.com/artifact/com.google.inject/guice -->
<dependency>
    <groupId>com.google.inject</groupId>
    <artifactId>guice</artifactId>
    <version>6.0.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.google.inject.extensions/guice-servlet -->
    <dependency>
      <groupId>com.google.inject.extensions</groupId>
      <artifactId>guice-servlet</artifactId>
      <version>5.1.0</version>
    </dependency>

    - створюємо клас - "слухач створення контексту (розгортання застосунку)
    (див. IocContextListener)
    - створюємо класи-конфігуратори
        = ServiceModule(цей клас) - для налаштування служб за DIP (з SOLID)
        згідно з яким залежності слід створювати не від класів, а від інтерфейсів.
        Але оскільки об'єкт з інтерфейсу створити не можна, необхідно встановити
        зв'язок (bind) між інтерфейсом та його реалізацією (класом)
        = RouterModule - для налаштування маршрутизації сервлетів. Створюємо
        інструкції маршрутизації (див. клас) та замінюємо сервлетні анотації
        (@WebServlet) на @Singleton для всіх сервлетів
    - змінюємо налаштування сервера (web.xml)
 */