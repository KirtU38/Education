import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class XMLHandlerBetter extends DefaultHandler {

    private final SimpleDateFormat visitDateFormat;
    private final HashMap<String, Integer> votersCount;
    private final HashMap<Integer, WorkTime> voteStationWorkTimes;
    private List<String> listOfDuplicates;
    private List<String> listOfWorkTimes;

    public XMLHandlerBetter() {
        this.visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        this.votersCount = new HashMap<>();
        this.voteStationWorkTimes = new HashMap<>();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("voter")) {
                String name = attributes.getValue("name");
                String birthDay = attributes.getValue("birthDay");
                String voter = name + " (" + birthDay + ")";
                int count = votersCount.getOrDefault(voter, 0);
                votersCount.put(voter, count + 1);
            } else if (qName.equals("visit")) {
                Integer station = Integer.valueOf(attributes.getValue("station"));
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

        listOfDuplicates = new ArrayList<>();
        listOfWorkTimes = new ArrayList<>();

        for (String voter : votersCount.keySet()) {
            int numberOfVotes = votersCount.get(voter);
            if (numberOfVotes > 1) {
                listOfDuplicates.add(voter + " - " + numberOfVotes);
            }
        }
        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            listOfWorkTimes.add("\t" + votingStation + " - " + workTime);
        }

        votersCount.clear();
        voteStationWorkTimes.clear();
    }

    public void printVoteStationsWorkTimes() {

        System.out.println("Duplicated voters: ");
        listOfWorkTimes.forEach(System.out::println);
    }

    public void printDuplicatedVoters() {

        System.out.println("Voting station work times: ");
        listOfDuplicates.forEach(System.out::println);
    }
}
