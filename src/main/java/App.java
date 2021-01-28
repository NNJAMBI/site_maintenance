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
        String layout = "layout.hbs";
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        //displaying the homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("Engineers", allEngineers);
            List<Site> allSites = Site.all();
            model.put("sites", allSites);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


//get an engineer
        get("/Engineer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("Engineer", allEngineers);
            return new ModelAndView(model, "engineers.hbs");
        }, new HandlebarsTemplateEngine());

//adding a new site to an engineer

        get("Engineer/:id/Sites/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("engineer", allEngineers);
            Engineer engineer = Engineer.find(Integer.parseInt(request.params(":id")));
            model.put("engineerId", engineer);
            List<Site> allSites = Site.all();
            model.put("sites", allSites);
            return new ModelAndView(model, "engineer-site-form.hbs");
        }, new HandlebarsTemplateEngine());

       // linking the site ID to the engineer ID

        get("/Engineers/:engineerId/Sites/:id ", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("engineer", allEngineers);
            Engineer engineer= Engineer.find(Integer.parseInt(request.params(":engineerId")));
            model.put("engineerId", engineer);
            Site site = Site.find(Integer.parseInt(request.params(":id")));
            model.put("site", site );
            return new ModelAndView(model, "sites.hbs");
        }, new HandlebarsTemplateEngine());

//getting the site ID

        get("/Sites/:id", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            List<Site> allSites = Site.all();
            model.put("Site", allSites);
            Site site = Site.find(Integer.parseInt(request.params(":id")));
            model.put("Site", site);
            return new ModelAndView(model, "Site.hbs");
        }, new HandlebarsTemplateEngine());


        //adding a new engineer

        get("/Engineer/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("engineer", allEngineers);
            return new ModelAndView(model, "engineer-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/Engineer", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String firstname= request.queryParams("firstname");
            String lastname = request.queryParams( "lastname");
            String email = request.queryParams("email");
            Engineer newEngineer = new Engineer(firstname, lastname, email);
            newEngineer.save();
            return new ModelAndView(model, "engineer-success.hbs");
        }, new HandlebarsTemplateEngine());


        //retrieving the engineer Id

        get("/Engineers/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("engineer", allEngineers);
            Engineer engineer = Engineer.find(Integer.parseInt(request.params(":id")));
            List<Site> allSites = Site.all();
            model.put("Site", allSites);
            return new ModelAndView(model, "stylist.hbs");
        }, new HandlebarsTemplateEngine());

    }

}

