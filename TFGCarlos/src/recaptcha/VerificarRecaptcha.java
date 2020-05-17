package recaptcha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class VerificarRecaptcha {

    public static boolean validate(String gRecaptchaResponse) throws IOException  {
      	 String urlCaptcha = "https://www.google.com/recaptcha/api/siteverify?secret=6LeKMsEUAAAAADpfCgOFSOS4rqv3WG8dl_Yw9uLE&response="+gRecaptchaResponse;

          if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
              return false;
          }


          /*
           * Esta de abajo es la validaciÃ³n que habrÃ­a que hacer pero como no funciona
           * la dejo comentada.
           */


          try {
              URL url = new URL(urlCaptcha);
              HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
              BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
              String inputLine;
              StringBuffer connectionResponse = new StringBuffer();
              while ((inputLine = in.readLine()) != null) {
           	   connectionResponse.append(inputLine);
              }
              in.close();
              System.out.println("Response from google Recaptcha service="+connectionResponse);
              JsonReader jsonReader = Json.createReader(new StringReader(connectionResponse.toString()));
              JsonObject jsonObject = jsonReader.readObject();
              jsonReader.close();
              if(jsonObject.getBoolean("success"))
           	   return true;
              else
           	   return false;
          } catch (MalformedURLException e) {
     	     e.printStackTrace();
          } catch (IOException e) {
     	     e.printStackTrace();
     	     throw e;
          }
          return false;
      }
}
