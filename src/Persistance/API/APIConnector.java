package Persistance.API;

import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.io.IOException;

public class APIConnector {

    private static final ThreadLocal<APIConnector> threadLocalInstance = new ThreadLocal<>();
    private static final String BASE_API_URL = "https://balandrau.salle.url.edu/dpoo/S1_Project_107/";
    private ApiHelper apiHelper;

    private APIConnector() throws ApiException {
        apiHelper = new ApiHelper();
    }

    public static APIConnector getInstance() {
        APIConnector connector = threadLocalInstance.get();
        try {
            if (connector == null) {
                connector = new APIConnector();
                threadLocalInstance.set(connector);
            }
        } catch (ApiException e) {
            //Aqui hace falta pasar correctamente el error
        }
        return connector;
    }

    public String getRequest(String endpoint) throws IOException {
        return apiHelper.getFromUrl(BASE_API_URL + endpoint);
    }

    public String postRequest(String endpoint, String jsonBody) throws IOException {
        return apiHelper.postToUrl(BASE_API_URL + endpoint, jsonBody);
    }

    public String deleteRequest(String endpoint) throws IOException {
        return apiHelper.deleteFromUrl(BASE_API_URL + endpoint);
    }
}
