import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/site_maintenance";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "admin");
        String layout = "templates/layout.hbs";
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);
//get all sites and engineers
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("Engineer", allEngineers);
            List<Site> site = Site.all();
            model.put("Site", site);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/Engineer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("Engineer", allEngineers);
            return new ModelAndView(model, "engineers.hbs");
        }, new HandlebarsTemplateEngine());

//form to add engineer
        get("/Engineer/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("Engineer", allEngineers);
            return new ModelAndView(model, "add-engineer-form.hbs");
        }, new HandlebarsTemplateEngine());

//process form to create new engineer
        post("/Engineer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String firstName = req.queryParams("firstName");
            String secondName = req.queryParams("secondName");
            String Email = req.queryParams("Email");
            Engineer engineer = new Engineer(firstName, secondName, Email);
            engineer.save();
            res.redirect("engineer-added-success-page.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());


        get("/Engineer/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Engineer engineer = Engineer.find(Integer.parseInt(req.params(":id")));
            model.put("Engineer", engineer);
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());


        post("/Engineer/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Engineer engineer = Engineer.find(Integer.parseInt(req.params(":id")));
            engineer.delete();
            res.redirect("Engineer-delete-success-page.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());


        get("/Engineer/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editEngineer", true);
            Engineer engineer = Engineer.find(Integer.parseInt(req.params("id")));
            model.put("Engineer", engineer);
            model.put("Engineer", Engineer.all());
            return new ModelAndView(model, "Engineer-edit.hbs");
        }, new HandlebarsTemplateEngine());


        post("/Engineer/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String firstName = req.queryParams("firstName");
            String secondName = req.queryParams("secondName");
            String email = req.queryParams("Email");
            Engineer engineer = Engineer.find(Integer.parseInt(req.params(":id")));
            engineer.update(firstName, secondName, email);
            String url = String.format("/Engineer/%d", engineer.getId());
            res.redirect(url);
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        //get all sites
        get("/Site", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Site> allSites = Site.all();
            model.put("Site", allSites);
            return new ModelAndView(model, "sites.hbs");
        }, new HandlebarsTemplateEngine());
    }

}

