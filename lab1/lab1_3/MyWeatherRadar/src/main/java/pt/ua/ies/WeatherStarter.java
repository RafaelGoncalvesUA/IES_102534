package pt.ua.ies;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import pt.ua.ies.ipma_client.IpmaCityForecast; //may need to adapt package name
import pt.ua.ies.ipma_client.IpmaService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    //todo: should generalize for a city passed as argument
    private static int CITY_ID = 1010500;
    private static final Logger logger = LogManager.getLogger(WeatherStarter.class);

    public static void  main(String[] args ) {

		logger.info("Início de execução do projeto.");

        if (args.length == 0) {
            System.err.println("Erro: execução sem argumento.");
            System.exit(1);
        }

        try {
            CITY_ID = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Erro: argumento não é um inteiro.");
            System.exit(1);
        }

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint
        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                var firstDay = forecast.getData().listIterator().next();

                System.out.printf( "\nmax temp for %s is %4.1f %n",
                        firstDay.getForecastDate(),
                        Double.parseDouble(firstDay.getTMax()));
                
                logger.info("Execução do programa sem erros.");
                System.exit(0);
            } else {
                System.out.println( "No results for this request!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}