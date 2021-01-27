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
            model.put("Site", site );
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/Engineer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("Engineer",Engineer.all());
            model.put("template", "templates/Engineer.hbs");
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });
        get("/Engineer/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/add-engineer-form.hbs");
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        post("/Engineer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String firstName = req.queryParams("firstName");
            String secondName = req.queryParams("secondName");
            String Email = req.queryParams("Email");
            Engineer engineer = new Engineer(firstName,secondName,Email);
            engineer.save();
            model.put("template", "templates/Engineer-added-success-page.hbs");
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });
    }

}

