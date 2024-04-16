package org.jluc.ctr.tools.calendrier.tools.google.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.jluc.ctr.tools.calendrier.model.Evenement;
import org.jluc.ctr.tools.calendrier.model.TypeActivite;

public class CalendarServices {

    // Listes des calendriers :
    // Calendrier - 1 - Numéros de semaine
    // Calendrier - 2 - Jours fériés en France
    // Calendrier - 3 - Module 6 - 20
    // Calendrier - 4 - ANTEOR
    // Calendrier - 5 - GP N4 CTR BPL
    // Calendrier - 6 - Handi
    // Calendrier - 7 - INITIATEUR CTR BPL
    // Calendrier - 8 - MF1 CTR BPL
    // Calendrier - 9 - MF2 CTR BPL
    // Calendrier - 10 - Module 20-40
    // Calendrier - 11 - RIFA
    // Calendrier - 12 - TEK
    // Calendrier - 13 - TIV CTR BPL
    // Calendrier - 14 - TSI
    // Calendrier - 15 - Recycleur BPL
    // Calendrier - 16 - planning.ctrbpl@gmail.com
    private static CalendarServices mInstance;

    private List<CalendarListEntry> mCalendarList;

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "CalendrierCTR";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart. If modifying
     * these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/resources/credentials.json";

    public static CalendarServices getInstance() {
        if (mInstance == null) {
            mInstance = new CalendarServices();
        }
        return mInstance;
    }

    private CalendarServices() {
        // Build a new authorized API client service.
        try {
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
            CalendarList calendarList = service.calendarList().list().execute();
            mCalendarList = calendarList.getItems();
            int i = 1;
            for (CalendarListEntry calendar : mCalendarList) {
                System.out.println("Calendrier - " + i + " - " + calendar.getSummary());
                i++;
            }
        } catch (GeneralSecurityException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT
     *            The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException
     *             If the credentials.json file cannot be found.
     */
    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarServices.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH))).setAccessType("offline").build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("planning.ctrbpl@gmail.com");
        // returns an authorized Credential object.
        return credential;
    }

    public void addEvent(Evenement event) {
        TypeActivite activite = event.getType().getActivite();

    }

    private Event createEvent() {
        Event event = new Event().setSummary("Google I/O 2015").setLocation("800 Howard St., San Francisco, CA 94103").setDescription("A chance to hear more about Google's developer products.");

        DateTime startDateTime = new DateTime("2015-05-28T09:00:00-07:00");
        EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2015-05-28T17:00:00-07:00");
        EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        return event;
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        CalendarServices.getInstance();
    }

}
