package ohtu;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "014323663";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats.herokuapp.com/students/"+studentNr+"/submissions";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream =  method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        if (subs.length == 0) {
            System.out.println("FAILURE");
        } else {
            System.out.println("Opiskelijanumero: " + subs[0].getStudent_number());
            int tehtavia = 0;
            int tunteja = 0;
            int i = 1;
            for (Submission submission : subs) {
                submission.laita();
                System.out.println("viikko " + i + ": tehtyjä tehtäviä: " + submission.getTasksDone() + ", aikaa käytetty: " + submission.getHours() + " tuntia, tehdyt tehtävät: ");
                submission.tulosta();
                i++;
                tehtavia += submission.getTasksDone();
                tunteja += submission.getHours();
            }

            System.out.println("Yhteensä: " + tehtavia + " tehtävää, " + tunteja + " tuntia");

        }
    }
}