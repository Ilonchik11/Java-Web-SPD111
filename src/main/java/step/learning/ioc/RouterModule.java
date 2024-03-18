package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.servlets.CartServlet;
import step.learning.servlets.HomeServlet;
import step.learning.servlets.PromotionServlet;
import step.learning.servlets.SignupServlet;

public class RouterModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/").with(HomeServlet.class);
        serve("/cart").with(CartServlet.class);
        serve("/promotion").with(PromotionServlet.class);
        serve("/signup").with(SignupServlet.class);
    }
}
