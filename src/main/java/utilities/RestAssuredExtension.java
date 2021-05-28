package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


public class RestAssuredExtension {

    public Map<String,Long> ccyConversionList = new HashMap<String, Long>();
    public RestAssuredExtension() {

    }


    private RequestSpecification buildRequest(){

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("https://pro-api.coinmarketcap.com/v1");
        builder.setContentType(ContentType.JSON);
        RequestSpecification request = RestAssured.given().spec(builder.build());

        request.header(new Header("ACCEPT", "application/json"));
        request.header(new Header("X-CMC_PRO_API_KEY", "c1c92228-451f-4d5d-9149-9bd4275c361f"));
        return request;
    }

    public Response GetIDs(String url, Map<String,String> queryParams) throws URISyntaxException {
        RequestSpecification request = buildRequest();
        if(queryParams!=null && !queryParams.isEmpty()){
            request.queryParams(queryParams);
        }

        return request.get(new URI(url));
    }


    public Response GetConvertedCurrencies(String url, Map<String, Long> currencyMap) throws URISyntaxException, ParseException {
        for (Map.Entry<String, Long> entry: currencyMap.entrySet()) {
            RequestSpecification request = buildRequest();
            request.queryParams("symbol", entry.getKey());
            request.queryParams("amount", 1);
            request.queryParams("convert_id", 2832);
            Response response= request.get(new URI(url));
            assertTrue(response.getStatusCode()==200);
            ccyConversionList.put(entry.getKey(), response.jsonPath().<Long>get("data.quote.2832.price"));
        }
        return null;
    }

    public void displayConvertedCurriencies(){
        for (Map.Entry<String, Long> ccyConversion: ccyConversionList.entrySet()){
            System.out.println("Currency: "+ ccyConversion.getKey()+ " has Bolivian Boliviano price as: " +ccyConversion.getValue());
        }
    }

    public static void responseCurrencyAssertion(Response response) throws ParseException {

        assertTrue(response.getStatusCode()==200);
        JSONObject obj = (JSONObject) new JSONParser().parse(response.getBody().asString());
        JSONArray array = (JSONArray)obj.get("data");
        Iterator itr = array.iterator();
        Map<String, Long> currencyId= new HashMap<String, Long>();
        while(itr.hasNext()){
            Map abc = (Map) itr.next();
            if (abc==null || abc.isEmpty())
                continue;
            String symbol = (String) abc.get("symbol");
            if ("BTC".equals(symbol) || "USDT".equals(symbol) || "ETH".equals(symbol)){
                currencyId.put(symbol, (Long) abc.get("id"));
            }
        }

        System.out.println(currencyId);
    }

    public static void validateRequredData(Response response) throws ParseException {
        assertTrue(response.getStatusCode()==200);
        System.out.println("Currency converted to "+response.jsonPath().get("data.1027.logo"));
        assertEquals("https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png",response.jsonPath().get("data.1027.logo"));
        assertEquals("https://github.com/ethereum/wiki/wiki/White-Paper",response.jsonPath().get("data.1027.urls.technical_doc[0]"));
        assertEquals("ETH",response.jsonPath().get("data.1027.symbol"));
        assertEquals("2015-08-07T00:00:00.000Z",response.jsonPath().get("data.1027.date_added"));
        assertTrue(((List)response.jsonPath().get("data.1027.tags")).contains("mineable"));

    }

    public static void apiStatusCode(Response response, String status) throws ParseException {
//        assertTrue(response.getStatusCode()==200);
        assertTrue(response.getStatusCode()==Integer.parseInt(status));

    }

    public static void validateLogoURL(Response response, String url) throws ParseException {
        System.out.println("Currency converted to : "+response.jsonPath().get("data.1027.logo"));
        assertEquals(url,response.jsonPath().get("data.1027.logo"));
    }

    public static void validateTechnicalDocURL(Response response, String url) throws ParseException {
        System.out.println("Technical Doc URL : "+ response.jsonPath().get("data.1027.urls.technical_doc[0]"));
        assertEquals(url,response.jsonPath().get("data.1027.urls.technical_doc[0]"));
    }

    public static void validateSymbol(Response response, String symbol) throws ParseException {
        System.out.println("Symbol : "+ response.jsonPath().get("data.1027.symbol"));
        assertEquals(symbol,response.jsonPath().get("data.1027.symbol"));
    }

    public static void validateDate_added(Response response, String dateAdded) throws ParseException {
        System.out.println("dateAdded : "+ response.jsonPath().get("data.1027.date_added"));
        assertEquals(dateAdded,response.jsonPath().get("data.1027.date_added"));
    }

    public static void validateTag(Response response, String tag) throws ParseException {
        assertTrue(((List)response.jsonPath().get("data.1027.tags")).contains(tag));
    }

    public static void filterCurrenciesWithMineableTag(Response response) throws ParseException {
        assertTrue(response.getStatusCode()==200);
        int dataLength = ((HashMap)(response.jsonPath().get("data"))).size();
        for(int i=1; i<=dataLength; i++){
            if(((List)response.jsonPath().get("data.'"+i+"'.tags")).contains("mineable")){
                System.out.println(response.jsonPath().get("data.'"+i+"'.name") +": currency has the mineable tag associated");
            }
        }
    }


    public static Map responseAssertion(Response response) throws ParseException {
        assertTrue(response.getStatusCode()==200);
        JSONObject obj = (JSONObject) new JSONParser().parse(response.getBody().asString());
        JSONArray array = (JSONArray)obj.get("data");
        Iterator itr = array.iterator();
        Map<String, Long> currencyId= new HashMap<String, Long>();
        while(itr.hasNext()){
            Map abc = (Map) itr.next();
            if (abc==null || abc.isEmpty())
                continue;
            String symbol = (String) abc.get("symbol");
            if ("BTC".equals(symbol) || "USDT".equals(symbol) || "ETH".equals(symbol)){
                currencyId.put(symbol, (Long) abc.get("id"));
            }
        }

        System.out.println(currencyId);
        return currencyId;
    }


}
