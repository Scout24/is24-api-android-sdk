package de.is24.restapi.android.sdk.http.response.handler;

import java.io.InputStream;

import org.apache.http.HttpStatus;

import android.test.AndroidTestCase;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.domain.MiniExpose;
import de.is24.restapi.android.sdk.domain.MiniExposeAttributes;
import de.is24.restapi.android.sdk.domain.Page;
import de.is24.restapi.android.sdk.domain.PictureAttachment;
import de.is24.restapi.android.sdk.domain.RealEstateType;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.test.MockRequestExecuter;
import de.is24.restapi.android.sdk.test.NetworkTestHelper;


public class SearchResponseHandlerTest extends AndroidTestCase {
  private String searchResultApartmentRent =
    "{\"resultlist.resultlist\":{\"paging\":{\"pageNumber\":1,\"pageSize\":20,\"numberOfPages\":1,\"numberOfHits\":6},\"resultlistEntries\":[{\"@realEstateType\":\"0\",\"@numberOfHits\":\"6\",\"resultlistEntry\":[{\"@id\":\"59484946\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/59484946\",\"id\":59484946,\"distance\":\"0.10\",\"resultlist.realEstate\":{\"creationDate\":\"2011-03-15T19:45:02.000+01:00\",\"lastModificationDate\":\"2011-03-15T19:45:54.000+01:00\",\"@xsi.type\":\"ns7:ApartmentRent\",\"@id\":\"59484946\",\"title\":\"kleene Hütte in Mitte\",\"address\":{\"street\":\"Lange Str.\",\"houseNumber\":5,\"postcode\":10243,\"city\":\"Berlin\",\"quarter\":\"Friedrichshain (Friedrichshain)\",\"wgs84Coordinate\":{\"latitude\":52.51265796696754,\"longitude\":13.432535316705534}},\"titlePicture\":{\"@creation\":\"2011-04-27T15:20:47.426+02:00\",\"@modification\":\"2011-04-27T15:20:47.426+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result003\\/N\\/70\\/749\\/671\\/70749671-4.jpg?2876602898\",\"floorplan\":false},\"price\":{\"value\":\"250.00\",\"currency\":\"EUR\",\"marketingType\":\"RENT\",\"priceIntervalType\":\"MONTH\"},\"livingSpace\":\"35.00\",\"numberOfRooms\":1,\"energyPerformanceCertificate\":true,\"builtInKitchen\":false,\"balcony\":false,\"garden\":false,\"floorplan\":false}},{\"@creation\":\"2010-12-27T19:53:36.000+01:00\",\"@modification\":\"2011-03-07T13:54:08.000+01:00\",\"@id\":\"58442851\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/58442851\",\"id\":58442851,\"distance\":0.11,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:ApartmentRent\",\"@id\":\"58442851\",\"title\":\"Wohnen mit Ausblick!\",\"address\":{\"street\":\"Andreasstr.\",\"houseNumber\":22,\"postcode\":10243,\"city\":\"Berlin\",\"quarter\":\"Friedrichshain (Friedrichshain)\",\"wgs84Coordinate\":{\"latitude\":52.51353246272289,\"longitude\":13.431628909968241}},\"titlePicture\":{\"@creation\":\"2011-04-27T15:20:47.426+02:00\",\"@modification\":\"2011-04-27T15:20:47.426+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result003\\/N\\/70\\/749\\/671\\/70749671-4.jpg?2876602898\",\"floorplan\":false},\"price\":{\"value\":486.26,\"currency\":\"EUR\",\"marketingType\":\"RENT\",\"priceIntervalType\":\"MONTH\"},\"livingSpace\":46.31,\"numberOfRooms\":2,\"energyPerformanceCertificate\":true,\"builtInKitchen\":true,\"balcony\":false,\"garden\":false,\"floorplan\":false}},{\"@creation\":\"2010-12-12T14:04:41.000+01:00\",\"@modification\":\"2011-03-09T08:26:22.000+01:00\",\"@id\":\"58301185\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/58301185\",\"id\":58301185,\"distance\":1.84,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:ApartmentRent\",\"@id\":\"58301185\",\"title\":\"Wohnen am Spittelmarkt! 2 Bäder, 2 Balkone + hochwertig ausgestattet - Erstbezug ab Herbst 2011!\",\"address\":{\"street\":\"Seydelstraße\",\"houseNumber\":21,\"postcode\":10179,\"city\":\"Berlin\",\"quarter\":\"Mitte (Mitte)\",\"wgs84Coordinate\":{\"latitude\":52.50946369844532,\"longitude\":13.40419626053429}},\"titlePicture\":{\"@creation\":\"2011-04-27T15:20:47.427+02:00\",\"@modification\":\"2011-04-27T15:20:47.427+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result002\\/N\\/77\\/489\\/542\\/77489542-4.jpg?1217283613\",\"floorplan\":true},\"price\":{\"value\":1388.79,\"currency\":\"EUR\",\"marketingType\":\"RENT\",\"priceIntervalType\":\"MONTH\"},\"livingSpace\":106.78,\"numberOfRooms\":3,\"energyPerformanceCertificate\":true,\"builtInKitchen\":true,\"balcony\":true,\"garden\":false,\"floorplan\":true}},{\"@creation\":\"2011-01-11T14:12:44.000+01:00\",\"@modification\":\"2011-03-07T16:40:56.000+01:00\",\"@id\":\"58574643\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/58574643\",\"id\":58574643,\"distance\":2.11,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:ApartmentRent\",\"@id\":\"58574643\",\"title\":\"CityQuartier DomAquarée, WE 6.8.\",\"address\":{\"street\":\"St.-Wolfgang-Straße\",\"houseNumber\":\"\",\"postcode\":10178,\"city\":\"Berlin\",\"quarter\":\"Mitte (Mitte)\",\"wgs84Coordinate\":{\"latitude\":52.52096066985244,\"longitude\":13.402739635719472}},\"titlePicture\":{\"@creation\":\"2011-04-27T15:20:47.428+02:00\",\"@modification\":\"2011-04-27T15:20:47.428+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result001\\/N\\/71\\/909\\/784\\/71909784-4.jpg?4001527015\",\"floorplan\":false},\"price\":{\"value\":906.75,\"currency\":\"EUR\",\"marketingType\":\"RENT\",\"priceIntervalType\":\"MONTH\"},\"livingSpace\":\"58.50\",\"numberOfRooms\":2,\"energyPerformanceCertificate\":true,\"builtInKitchen\":true,\"balcony\":true,\"garden\":false,\"floorplan\":false}},{\"@creation\":\"2007-04-25T10:35:58.000+02:00\",\"@modification\":\"2011-02-23T18:48:25.000+01:00\",\"@id\":\"41430231\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/41430231\",\"id\":41430231,\"distance\":2.18,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:ApartmentRent\",\"@id\":\"41430231\",\"title\":\"++Ruhig gelegene, exklusive Neubauwohnung im 6. OG am Hackeschen Markt++Garnisonkirchplatz++\",\"address\":{\"street\":\"Garnisonkirchplatz\",\"houseNumber\":2,\"postcode\":10178,\"city\":\"Berlin\",\"quarter\":\"Mitte (Mitte)\",\"wgs84Coordinate\":{\"latitude\":52.52189197392211,\"longitude\":13.402405447551171}},\"titlePicture\":{\"@creation\":\"2011-04-27T15:20:47.429+02:00\",\"@modification\":\"2011-04-27T15:20:47.429+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result003\\/N\\/703\\/721\\/703721-4.jpg?3735529687\",\"floorplan\":false},\"price\":{\"value\":\"530.00\",\"currency\":\"EUR\",\"marketingType\":\"RENT\",\"priceIntervalType\":\"MONTH\"},\"livingSpace\":\"45.00\",\"numberOfRooms\":2,\"energyPerformanceCertificate\":true,\"builtInKitchen\":true,\"balcony\":false,\"garden\":false,\"floorplan\":false}},{\"@creation\":\"2011-02-02T18:45:13.000+01:00\",\"@modification\":\"2011-03-09T18:13:25.000+01:00\",\"@id\":\"58881483\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/58881483\",\"id\":58881483,\"distance\":2.46,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:ApartmentRent\",\"@id\":\"58881483\",\"title\":\"Erstbezug - hochwertig saniertes Single-Appartement zwischen Torstraße uns Schönhauser Allee\",\"address\":{\"street\":\"Christinenstraße\",\"houseNumber\":7,\"postcode\":10119,\"city\":\"Berlin\",\"quarter\":\"Prenzlauer Berg (Prenzlauer Berg)\",\"wgs84Coordinate\":{\"latitude\":52.53012035570407,\"longitude\":13.40815689282956}},\"titlePicture\":{\"@creation\":\"2011-04-27T15:20:47.430+02:00\",\"@modification\":\"2011-04-27T15:20:47.430+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result002\\/N\\/76\\/360\\/156\\/76360156-4.jpg?3563168891\",\"floorplan\":false},\"price\":{\"value\":\"499.00\",\"currency\":\"EUR\",\"marketingType\":\"RENT\",\"priceIntervalType\":\"MONTH\"},\"livingSpace\":39.94,\"numberOfRooms\":1,\"energyPerformanceCertificate\":true,\"builtInKitchen\":true,\"balcony\":false,\"garden\":false,\"floorplan\":false}}]}]}}";
  private String searchResultHouseBuy =
    "{\"resultlist.resultlist\":{\"paging\":{\"pageNumber\":1,\"pageSize\":20,\"numberOfPages\":1,\"numberOfHits\":9},\"resultlistEntries\":[{\"@realEstateType\":\"3\",\"@numberOfHits\":\"9\",\"resultlistEntry\":[{\"@id\":\"59153736\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/59153736\",\"id\":59153736,\"distance\":6.55,\"resultlist.realEstate\":{\"creationDate\":\"2011-03-15T19:45:02.000+01:00\",\"lastModificationDate\":\"2011-03-15T19:45:54.000+01:00\",\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"59153736\",\"title\":\"Geräumiges Einfamilienhaus mit Einliegerwohnung in Karlshorster Bestlage\",\"address\":{\"city\":\"Berlin\",\"quarter\":\"Karlshorst (Lichtenberg)\"},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.448+02:00\",\"@modification\":\"2011-04-28T13:09:37.448+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result001\\/N\\/78\\/314\\/646\\/78314646-4.jpg?2605901119\",\"floorplan\":true},\"price\":{\"value\":\"370000.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":\"190.00\",\"plotArea\":\"636.00\",\"numberOfRooms\":7,\"energyPerformanceCertificate\":true,\"floorplan\":true,\"courtage\":{\"hasCourtage\":\"YES\"}}},{\"@id\":\"59104012\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/59104012\",\"id\":59104012,\"distance\":\"7.70\",\"resultlist.realEstate\":{\"creationDate\":\"2011-02-18T16:38:26.000+01:00\",\"lastModificationDate\":\"2011-02-18T16:38:45.000+01:00\",\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"59104012\",\"title\":\"Ein Projekt der NCC Deutschland - EFH SIRIUS 110 auf der Parzelle 4 mit Aktionspaket- Außenanlage!!\",\"address\":{\"street\":\"Budsiner Str.\",\"houseNumber\":\"17-19\",\"postcode\":12683,\"city\":\"Berlin\",\"quarter\":\"Biesdorf (Marzahn)\",\"wgs84Coordinate\":{\"latitude\":52.50665212724824,\"longitude\":13.5456802143344}},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.450+02:00\",\"@modification\":\"2011-04-28T13:09:37.450+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result001\\/N\\/76\\/808\\/514\\/76808514-4.jpg?691598318\",\"floorplan\":false},\"price\":{\"value\":\"193460.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":117.42,\"plotArea\":\"492.00\",\"numberOfRooms\":4,\"energyPerformanceCertificate\":true,\"floorplan\":false,\"courtage\":{\"hasCourtage\":\"NO\"}}},{\"@creation\":\"2011-03-09T12:35:32.000+01:00\",\"@modification\":\"2011-03-09T12:45:59.000+01:00\",\"@id\":\"59459965\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/59459965\",\"id\":59459965,\"distance\":7.71,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"59459965\",\"title\":\"Angebot! Blickfang auf sonnigem Einzelgrundstück mit unverbaubarem Südblick in Späthsfelde!!\",\"address\":{\"street\":\"Ligusterweg\",\"houseNumber\":\"\",\"postcode\":12437,\"city\":\"Berlin\",\"quarter\":\"Baumschulenweg (Treptow)\",\"wgs84Coordinate\":{\"latitude\":52.447629652724764,\"longitude\":13.4750902250048}},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.451+02:00\",\"@modification\":\"2011-04-28T13:09:37.451+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result001\\/N\\/79\\/85\\/217\\/79085217-4.jpg?43378083\",\"floorplan\":true},\"price\":{\"value\":\"181700.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":\"110.00\",\"plotArea\":\"500.00\",\"numberOfRooms\":4,\"energyPerformanceCertificate\":true,\"floorplan\":true,\"courtage\":{\"hasCourtage\":\"NO\"}}},{\"@creation\":\"2011-01-20T13:23:48.000+01:00\",\"@modification\":\"2011-02-24T17:07:59.000+01:00\",\"@id\":\"58682174\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/58682174\",\"id\":58682174,\"distance\":8.91,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"58682174\",\"title\":\"Repräsentatives Einfamilienhaus auf hohem Niveau\",\"address\":{\"city\":\"Berlin\",\"quarter\":\"Niederschönhausen (Pankow)\"},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.453+02:00\",\"@modification\":\"2011-04-28T13:09:37.453+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result001\\/N\\/73\\/194\\/240\\/73194240-4.jpg?3669835458\",\"floorplan\":true},\"price\":{\"value\":\"429900.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":\"130.00\",\"plotArea\":\"1253.00\",\"numberOfRooms\":4,\"energyPerformanceCertificate\":true,\"floorplan\":true,\"courtage\":{\"hasCourtage\":\"NO\"}}},{\"@creation\":\"2007-03-27T10:53:47.000+02:00\",\"@modification\":\"2011-01-10T13:32:45.000+01:00\",\"@id\":\"41149687\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/41149687\",\"id\":41149687,\"distance\":8.98,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"41149687\",\"title\":\"Enfamilienhaus\\/Bungalow(massiv) inkl. Grundstück\",\"address\":{\"city\":\"Berlin\",\"quarter\":\"Blankenburg (Weißensee)\"},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.454+02:00\",\"@modification\":\"2011-04-28T13:09:37.454+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result002\\/N\\/1\\/70\\/344\\/1070344-4.jpg?2464254325\",\"floorplan\":false},\"price\":{\"value\":\"118200.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":\"80.00\",\"plotArea\":\"640.00\",\"numberOfRooms\":3,\"energyPerformanceCertificate\":true,\"floorplan\":false,\"courtage\":{\"hasCourtage\":\"NO\"}}},{\"@creation\":\"2010-10-10T22:09:47.000+02:00\",\"@modification\":\"2011-02-28T19:38:23.000+01:00\",\"@id\":\"57510703\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/57510703\",\"id\":57510703,\"distance\":10.02,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"57510703\",\"title\":\"Schönes Haus mit großem Garten\",\"address\":{\"city\":\"Berlin\",\"quarter\":\"Buckow (Neukölln)\"},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.456+02:00\",\"@modification\":\"2011-04-28T13:09:37.456+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result003\\/N\\/61\\/432\\/964\\/61432964-4.jpg?3726686622\",\"floorplan\":false},\"price\":{\"value\":\"680000.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":403.78,\"plotArea\":\"847.00\",\"numberOfRooms\":30,\"energyPerformanceCertificate\":true,\"floorplan\":false,\"courtage\":{\"hasCourtage\":\"YES\"}}},{\"@creation\":\"2010-12-09T19:36:55.000+01:00\",\"@modification\":\"2011-02-18T19:47:36.000+01:00\",\"@id\":\"58278270\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/58278270\",\"id\":58278270,\"distance\":10.56,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"58278270\",\"title\":\"Gepflegtes Einfamilienhaus (Reihenhaus) mit Tiefgarage in Wittenau\",\"address\":{\"city\":\"Berlin\",\"quarter\":\"Wittenau (Reinickendorf)\"},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.457+02:00\",\"@modification\":\"2011-04-28T13:09:37.457+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result002\\/N\\/68\\/690\\/641\\/68690641-4.jpg?3283358132\",\"floorplan\":true},\"price\":{\"value\":\"230000.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":\"125.00\",\"plotArea\":\"217.00\",\"numberOfRooms\":5,\"energyPerformanceCertificate\":true,\"floorplan\":true,\"courtage\":{\"hasCourtage\":\"YES\"}}},{\"@creation\":\"2010-12-01T14:21:12.000+01:00\",\"@modification\":\"2011-01-18T12:40:27.000+01:00\",\"@id\":\"58181632\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/58181632\",\"id\":58181632,\"distance\":11.65,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"58181632\",\"title\":\"Variabler Bungalow mit 110 m² Wfl.+ Wohnraumlüftung in Altglienicke\",\"address\":{\"street\":\"Am Bruchland\",\"houseNumber\":\"\",\"postcode\":12524,\"city\":\"Berlin\",\"quarter\":\"Altglienicke (Treptow)\",\"wgs84Coordinate\":{\"latitude\":52.42518720435494,\"longitude\":13.530005495090936}},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.459+02:00\",\"@modification\":\"2011-04-28T13:09:37.459+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result003\\/N\\/67\\/640\\/429\\/67640429-4.jpg?1438843355\",\"floorplan\":true},\"price\":{\"value\":\"211290.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":\"110.00\",\"plotArea\":\"666.00\",\"numberOfRooms\":4,\"energyPerformanceCertificate\":true,\"floorplan\":true,\"courtage\":{\"hasCourtage\":\"YES\"}}},{\"@creation\":\"2010-10-06T14:25:53.000+02:00\",\"@modification\":\"2010-12-09T01:00:56.000+01:00\",\"@id\":\"57457496\",\"@xlink.href\":\"http:\\/\\/sandbox.immobilienscout24.de\\/restapi\\/api\\/search\\/v1.0\\/expose\\/57457496\",\"id\":57457496,\"distance\":12.43,\"resultlist.realEstate\":{\"@xsi.type\":\"ns7:HouseBuy\",\"@id\":\"57457496\",\"title\":\"* AXISA * ~ Mehrfamilienhaus mit großem Grundstück\",\"address\":{\"city\":\"Berlin\",\"quarter\":\"Waidmannslust (Reinickendorf)\"},\"titlePicture\":{\"@creation\":\"2011-04-28T13:09:37.460+02:00\",\"@modification\":\"2011-04-28T13:09:37.460+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result001\\/N\\/60\\/903\\/399\\/60903399-4.jpg?2832569858\",\"floorplan\":false},\"price\":{\"value\":\"298000.00\",\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":\"315.00\",\"plotArea\":\"890.00\",\"numberOfRooms\":10,\"energyPerformanceCertificate\":true,\"floorplan\":false,\"courtage\":{\"hasCourtage\":\"YES\"}}}]}]}}";
  private SearchResponseHandler handler;
  private MockRequestExecuter executer;

  @Override
  protected void setUp() throws Exception {
    LibraryContext.getInstance().initApplicationContext(getContext());
    handler = new SearchResponseHandler();
    executer = new MockRequestExecuter();
    NetworkTestHelper.setHttpExecuter(executer);
  }

  public void testSuccessApartmentRent() throws Exception {
    InputStream responseStream = NetworkTestHelper.createResponseInputStream(searchResultApartmentRent);
    Response<Page> result = handler.handleResponse(responseStream, HttpStatus.SC_OK, new Response<Page>());
    assertNotNull(result);
    assertNotNull(result.result);
    assertTrue(result.success);
    assertEquals(HttpStatus.SC_OK, result.statusCode);
    assertEquals(1, result.result.pageNumber);
    assertEquals(20, result.result.pageSize);
    assertEquals(1, result.result.pageCount);
    assertEquals(6, result.result.totalMatchCount);
    assertNotNull(result.result.results);
    assertTrue(result.result.results.size() > 0);
    assertTrue(result.result.results.size() == 6);
  }

  public void testSuccessHouseBuy() throws Exception {
    InputStream responseStream = NetworkTestHelper.createResponseInputStream(searchResultHouseBuy);
    Response<Page> result = handler.handleResponse(responseStream, HttpStatus.SC_OK, new Response<Page>());
    assertNotNull(result);
    assertNotNull(result.result);
    assertTrue(result.success);
    assertEquals(HttpStatus.SC_OK, result.statusCode);
    assertEquals(1, result.result.pageNumber);
    assertEquals(20, result.result.pageSize);
    assertEquals(1, result.result.pageCount);
    assertEquals(9, result.result.totalMatchCount);
    assertNotNull(result.result.results);
    assertTrue(result.result.results.size() > 0);
    assertTrue(result.result.results.size() == 9);
  }

  public void testSuccessApartmentRentFirstMiniExpose() throws Exception {
    InputStream responseStream = NetworkTestHelper.createResponseInputStream(searchResultApartmentRent);
    Response<Page> result = handler.handleResponse(responseStream, HttpStatus.SC_OK, new Response<Page>());
    assertNotNull(result);
    assertNotNull(result.result);
    assertTrue(result.success);
    assertEquals(HttpStatus.SC_OK, result.statusCode);
    assertNotNull(result.result.results);
    assertTrue(result.result.results.size() > 0);

    MiniExpose resultMiniExpose = result.result.results.get(0);

    assertEquals("2011-03-15T19:45:02.000+01:00", resultMiniExpose.getString(MiniExposeAttributes.CREATION_DATE));
    assertEquals("0,1 km", resultMiniExpose.getString(MiniExposeAttributes.DISTANCE));
    assertEquals("2011-03-15T19:45:54.000+01:00", resultMiniExpose.getString(MiniExposeAttributes.MODIFICATION_DATE));
    assertEquals("52.51265796696754", resultMiniExpose.getString(MiniExposeAttributes.LATITUDE));
    assertEquals("13.432535316705534", resultMiniExpose.getString(MiniExposeAttributes.LONGITUDE));
    assertEquals("35 m²", resultMiniExpose.getString(MiniExposeAttributes.LIVING_SPACE));
    assertEquals("Berlin", resultMiniExpose.getString(MiniExposeAttributes.OBJECT_CITY));
    assertEquals("5", resultMiniExpose.getString(MiniExposeAttributes.OBJECT_HOUSE_NUMBER));
    assertEquals("Lange Str.", resultMiniExpose.getString(MiniExposeAttributes.OBJECT_STREET));
    assertEquals("10243", resultMiniExpose.getString(MiniExposeAttributes.OBJECT_POSTCODE));
    assertEquals("250,00 €", resultMiniExpose.getString(MiniExposeAttributes.PRICE));
    assertEquals("1", resultMiniExpose.getString(MiniExposeAttributes.NUMBER_OF_ROOMS));
    assertEquals("http://picture.immobilienscout24.de/files/result003/N/70/749/671/70749671-4.jpg?2876602898",
      resultMiniExpose.get(MiniExposeAttributes.TITLE_PICTURE, PictureAttachment.class).url);
    assertEquals("kleene Hütte in Mitte", resultMiniExpose.getString(MiniExposeAttributes.TITLE));
    assertEquals(RealEstateType.ApartmentRent, resultMiniExpose.realEstateType);
    assertEquals("59484946", resultMiniExpose.id);
  }

  public void testSuccessHouseBuySecondMiniExpose() throws Exception {
    InputStream responseStream = NetworkTestHelper.createResponseInputStream(searchResultHouseBuy);
    Response<Page> result = handler.handleResponse(responseStream, HttpStatus.SC_OK, new Response<Page>());
    assertNotNull(result);
    assertNotNull(result.result);
    assertTrue(result.success);
    assertEquals(HttpStatus.SC_OK, result.statusCode);
    assertNotNull(result.result.results);
    assertTrue(result.result.results.size() > 0);

    MiniExpose resultMiniExpose = result.result.results.get(1);

    assertEquals("2011-02-18T16:38:26.000+01:00", resultMiniExpose.getString(MiniExposeAttributes.CREATION_DATE));
    assertEquals("7,7 km", resultMiniExpose.getString(MiniExposeAttributes.DISTANCE));
    assertEquals("2011-02-18T16:38:45.000+01:00", resultMiniExpose.getString(MiniExposeAttributes.MODIFICATION_DATE));
    assertEquals("52.50665212724824", resultMiniExpose.getString(MiniExposeAttributes.LATITUDE));
    assertEquals("13.5456802143344", resultMiniExpose.getString(MiniExposeAttributes.LONGITUDE));
    assertEquals("117,42 m²", resultMiniExpose.getString(MiniExposeAttributes.LIVING_SPACE));
    assertEquals("Berlin", resultMiniExpose.getString(MiniExposeAttributes.OBJECT_CITY));
    assertEquals("17-19", resultMiniExpose.getString(MiniExposeAttributes.OBJECT_HOUSE_NUMBER));
    assertEquals("Budsiner Str.", resultMiniExpose.getString(MiniExposeAttributes.OBJECT_STREET));
    assertEquals("12683", resultMiniExpose.getString(MiniExposeAttributes.OBJECT_POSTCODE));
    assertEquals("193.460,00 €", resultMiniExpose.getString(MiniExposeAttributes.PRICE));
    assertEquals("4", resultMiniExpose.getString(MiniExposeAttributes.NUMBER_OF_ROOMS));
    assertEquals("http://picture.immobilienscout24.de/files/result001/N/76/808/514/76808514-4.jpg?691598318",
      resultMiniExpose.get(MiniExposeAttributes.TITLE_PICTURE, PictureAttachment.class).url);
    assertEquals("Ein Projekt der NCC Deutschland - EFH SIRIUS 110 auf der Parzelle 4 mit Aktionspaket- Außenanlage!!",
      resultMiniExpose.getString(MiniExposeAttributes.TITLE));
    assertEquals(RealEstateType.HouseBuy, resultMiniExpose.realEstateType);
    assertEquals("59104012", resultMiniExpose.id);
  }
}
