package Persistance.API;

import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.io.IOException;

/**
 * Clase APIConnector que gestiona las conexiones y solicitudes a una API específica.
 * Esta clase implementa el patrón de diseño Singleton para asegurar una única instancia
 * de la conexión a lo largo de toda la aplicación.
 * <p>
 * Utiliza ApiHelper para manejar las solicitudes HTTP a la API.
 * </p>
 *
 * @see edu.salle.url.api.ApiHelper
 */
public class APIConnector {
    private static final String BASE_API_URL = "https://balandrau.salle.url.edu/dpoo/S1_grup_107/";   // URL base de la API.
    private static APIConnector instance = null;
    private ApiHelper apiHelper;

    /**
     * Constructor privado que inicializa ApiHelper.
     *
     * @throws ApiException Si hay un error al inicializar ApiHelper o al conectar con la API.
     */
    private APIConnector() throws ApiException {
        apiHelper = new ApiHelper();
    }

    /**
     * Obtiene la instancia única de APIConnector.
     * Si la instancia no existe, la crea e inicializa.
     *
     * @return La instancia única de APIConnector.
     * @throws ApiException Si hay un error al crear la instancia de APIConnector.
     */
    public static APIConnector getInstance() throws ApiException {
        if (instance == null) {
            instance = new APIConnector();
        }
        return instance;
    }

    /**
     * Realiza una solicitud GET a la API.
     *
     * @param endpoint El endpoint de la API a consultar.
     * @return La respuesta de la API como cadena de texto.
     * @throws IOException Si ocurre un error en la comunicación con la API.
     * @throws ApiException Si la API retorna un error.
     */
    public String getRequest(String endpoint) throws IOException, ApiException {
        return apiHelper.getFromUrl(BASE_API_URL + endpoint);
    }

    /**
     * Realiza una solicitud POST a la API.
     *
     * @param endpoint El endpoint de la API donde se publicará.
     * @param jsonBody El cuerpo de la solicitud en formato JSON.
     * @return La respuesta de la API como cadena de texto.
     * @throws IOException Si ocurre un error en la comunicación con la API.
     * @throws ApiException Si la API retorna un error.
     */
    public String postRequest(String endpoint, String jsonBody) throws IOException, ApiException {
        return apiHelper.postToUrl(BASE_API_URL + endpoint, jsonBody);
    }

    /**
     * Realiza una solicitud DELETE a la API.
     *
     * @param endpoint El endpoint de la API a donde se enviará la solicitud DELETE.
     * @return La respuesta de la API como cadena de texto.
     * @throws IOException Si ocurre un error en la comunicación con la API.
     * @throws ApiException Si la API retorna un error.
     */
    public String deleteRequest(String endpoint) throws IOException, ApiException {
        return apiHelper.deleteFromUrl(BASE_API_URL + endpoint);
    }

}
