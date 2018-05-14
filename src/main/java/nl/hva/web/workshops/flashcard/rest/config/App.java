package nl.hva.web.workshops.flashcard.rest.config;


import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * The application config
 * 
 * @author marciofk
 */
@ApplicationPath("services/rest")
public class App extends ResourceConfig {
    
    public App() {
    }
    
    
    
}
