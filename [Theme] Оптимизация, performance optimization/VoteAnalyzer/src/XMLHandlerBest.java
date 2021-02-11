import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class XMLHandlerBest extends DefaultHandler {

    private final SimpleDateFormat visitDateFormat;
    //private final HashMap<String, Integer> votersCount;
    private final HashMap<Integer, WorkTime> voteStationWorkTimes;
    //private final HashMap<String, Integer> duplicates = new HashMap<>();
    private List<String> listOfWorkTimes;
    private final HashSet<String> set = new HashSet<>();
    private final String[] duplicates = new String[100];
    private int index = 0;

    public XMLHandlerBest() {
        this.visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        this.voteStationWorkTimes = new HashMap<>();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("voter")) {
                String name = attributes.getValue("name");
                String birthDay = attributes.getValue("birthDay");
                String voter = name + " (" + birthDay + ")";

                if (!set.add(voter)) {
                    duplicates[index++] = voter;
                }
            } else if (qName.equals("visit")) {
                int station = Integer.parseInt(attributes.getValue("station"));
                Date time = visitDateFormat.parse(attributes.getValue("time"));
                WorkTime workTime = voteStationWorkTimes.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    voteStationWorkTimes.put(station, workTime);
                }
                workTime.addVisitTime(time.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
    }

    @Override
    public void endDocument() {

        set.clear();
        listOfWorkTimes = new ArrayList<>();
        for (int i = 0; i < duplicates.length; i++) {
            int count = 2;
            for (int j = i + 1; j < duplicates.length; j++) {
                if (duplicates[i] != null && duplicates[j] != null) {
                    if (duplicates[i].equals(duplicates[j])) {
                        count++;
                        duplicates[j] = null;
                    }
                }
            }
            if (duplicates[i] != null) {
                duplicates[i] = duplicates[i] + " - " + count;
            }
        }
        System.out.println(Arrays.toString(duplicates));

        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            listOfWorkTimes.add("\t" + votingStation + " - " + workTime);
        }

        voteStationWorkTimes.clear();
    }

    public void printVoteStationsWorkTimes() {

        System.out.println("Duplicated voters: ");
        listOfWorkTimes.forEach(System.out::println);
    }

    public void printDuplicatedVoters() {

        System.out.println("Voting station work times: ");
        for (String duplicate : duplicates) {
            if(duplicate != null){
                System.out.println(duplicate);
            }
        }
    }
}
