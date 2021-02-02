import java.time.LocalDateTime;
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


//get all engineers
        get("/Engineer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("Engineer", allEngineers);
            return new ModelAndView(model, "engineers.hbs");
        }, new HandlebarsTemplateEngine());

      //get an engineer

//        get("/Engineer/:id", (req, res) -> {
//        Map<String, Object> model = new HashMap<>();
//            int idOfEngineer = Integer.parseInt(req.params("id"));
//            Engineer foundEngineer = Engineer.find(idOfEngineer);
//            model.put("engineer", foundEngineer);
//            //List<Engineer> allEngineers = Engineer.all();
//           // model.put("Engineer", allEngineers);
//            return new ModelAndView(model, "engineer.hbs");
//        }, new HandlebarsTemplateEngine());


//adding a new site to an engineer

        get("Engineer/:id/site/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Engineer engineer = Engineer.find(Integer.parseInt(request.params(":id")));
            model.put("engineer", engineer);
            return new ModelAndView(model, "engineer-site-form.hbs");
        }, new HandlebarsTemplateEngine());

       // linking the site ID to the engineer ID

        get("/Engineer/:id/site/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Engineer engineer = Engineer.find(Integer.parseInt(request.params(":id")));
            Site site = Site.find(Integer.parseInt(request.params(":id")));
            model.put("engineer", engineer);
            model.put("site", site);
            return new ModelAndView(model, "sites.hbs");
        }, new HandlebarsTemplateEngine());

//getting the site ID

        get("/Sites/:id", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Site site = Site.find(Integer.parseInt(request.params(":id")));
            model.put("site", site);
            return new ModelAndView(model, "site.hbs");
        }, new HandlebarsTemplateEngine());


        //adding a new engineer

        get("/Engineer/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Engineer> allEngineers = Engineer.all();
            model.put("Engineer", allEngineers);
            return new ModelAndView(model, "engineer-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/Engineer", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String firstName= request.queryParams("firstName");
            String secondName = request.queryParams( "secondName");
            String Email = request.queryParams("Email");
            Engineer newEngineer = new Engineer( firstName, secondName, Email);
            newEngineer.save();
            return new ModelAndView(model, "engineer-success.hbs");
        }, new HandlebarsTemplateEngine());


        //adding a new site

        get("/Site/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Site> allSites = Site.all();
            model.put("Site", allSites);
            return new ModelAndView(model, "site-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/Site", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String site_name= request.queryParams("site_name");
            String county = request.queryParams("county");
          //  Site newSite = new Site(site_name, county, Site.getEngineerId());
            //newSite.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //retrieving the engineer Id

        get("/Engineer/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int engId = Integer.parseInt(request.params(":id"));
            Engineer engineer = Engineer.find(engId);
            model.put("Engineer", engineer);
            model.put("Site", Site.findByEngineer(engId));
            return new ModelAndView(model, "engineer.hbs");
        }, new HandlebarsTemplateEngine());


        //get a site
        get("/site", (req, res) -> {
            Map<String, Object> model = new HashMap<String , Object>();
            List<Site> allSites = Site.all();
            model.put("Site", allSites);
            return new ModelAndView(model, "sites.hbs");
        }, new HandlebarsTemplateEngine());

        //updating site details
        post("/sites/:engineerId/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int engineerId = Integer.parseInt(request.params("engineerId"));
            model.put("engineer", engineerId);
            String site_name = request.queryParams("site_name");
            String county = request.queryParams("county");
            Site site = Site.find(engineerId);
            site.update(site_name, county);
            request.session().attribute("site_name");
            return new ModelAndView(model, "success.hbs");
        });


        //delete
        post("Engineer/:id/sites/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int siteId = Integer.parseInt(request.params("id"));
            String site_name = request.queryParams("site_name");
            model.put("site_name", site_name);
         //   Engineer engineer = Engineer.find(Integer.parseInt(Site.getEngineerId()));
         //   engineer.delete();
            Site site = Site.find(siteId);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


//delete engineer all
        post("/Engineer/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            EngineerDetails engineer = Engineer.findDetails(request.params("id"));
            engineer.delete();
            model.put("engineer", Engineer.all());
            return new ModelAndView(model, "engineer.hbs");
        }, new HandlebarsTemplateEngine());
    }


}

