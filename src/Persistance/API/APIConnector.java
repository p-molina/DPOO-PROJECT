package Persistance.API;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * La clase APIConnector abstrae los detalles específicos de la conexión a la API del sistema "El Cofre".
 * Esta clase sigue el patrón de diseño Singleton para facilitar el acceso externo manteniendo
 * una única instancia. Configura las peticiones HTTP necesarias para interactuar con la API.
 */
public class APIConnector {

    // Atributo estático para implementar el patrón de diseño Singleton.
    private static final ThreadLocal<APIConnector> threadLocalInstance = new ThreadLocal<>();

    // URL base de la API.
    private static final String BASE_API_URL = "https://balandrau.salle.url.edu/dpoo/S1_Project_107/"; // Siendo el tercer parametro el {id} de nuestro grupo

    // Constructor privado.
    private APIConnector() {}

    /**
     * Método estático que devuelve la instancia compartida gestionada por el singleton.
     *
     * @return La instancia compartida de APIConnector.
     */
    public static APIConnector getInstance() {
        APIConnector connector = threadLocalInstance.get();
        if (connector == null) {
            connector = new APIConnector();
            threadLocalInstance.set(connector);
        }

        return connector;
    }

    /**
     * Método para realizar una petición HTTP GET a la API.
     *
     * @param endpoint El endpoint específico para la petición.
     * @return La respuesta de la petición.
     */
    public String getRequest(String endpoint) {
        try {
            URL url = new URL(BASE_API_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (Exception e) {
            System.err.println("Error en GET request: " + e.getMessage());
            return null;
        }
    }

}
