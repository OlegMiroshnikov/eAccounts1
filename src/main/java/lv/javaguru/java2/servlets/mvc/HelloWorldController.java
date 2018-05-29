package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.businesslogic.client.addclient.AddClientRequest;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//@Component
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/helloWorld.jsp", "Hello from MVC!");
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

}