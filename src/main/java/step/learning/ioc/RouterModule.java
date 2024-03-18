package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/").with(HomeServlet.class);
        serve("/cart").with(CartServlet.class);
        serve("/promotion").with(PromotionServlet.class);
        serve("/signup").with(SignupServlet.class);
        serve("/additem").with(AddItemServlet.class);
    }
}
