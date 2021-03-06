package by.it.shelkovich.project.java.servlets;

import by.it.shelkovich.project.java.actions.ActionFactory;
import by.it.shelkovich.project.java.actions.Actions;
import by.it.shelkovich.project.java.actions.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servGETnPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servGETnPost(req, resp);
    }

    protected void servGETnPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ICommand command = ActionFactory.INSTANCE.getCommand(req);
        ICommand nextCommand = command.execute(req);
        if (nextCommand==null) {
            RequestDispatcher disp=req.getRequestDispatcher(command.getJSP());
            disp.include(req,resp);}
        else{
            resp.sendRedirect("?command="+nextCommand);
        }

    }
}
