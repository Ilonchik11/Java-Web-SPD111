package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filters.CharsetFilter;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {
    @Override
    protected void configureServlets() {
        filter("/*").through(CharsetFilter.class);

        serve("/").with(HomeServlet.class);
        serve("/auth").with(AuthServlet.class);
        serve("/additem").with(AddItemServlet.class);
        serve("/cart").with(CartServlet.class);
        serve("/shop").with(ShopServlet.class);
        serve("/promotion").with(PromotionServlet.class);
        serve("/signup").with(SignupServlet.class);

        serve("/shop-api").with(ShopApiServlet.class);
    }
}
