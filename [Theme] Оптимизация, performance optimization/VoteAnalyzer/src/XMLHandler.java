import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {

    private Voter voter;
    private final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private final SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private final HashMap<Voter, Integer> votesCount = new HashMap<>();
    private final HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();


    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) {
        try {
            if (tag.equals("voter") && voter == null) {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);
            } else if (tag.equals("visit") && voter != null) {
                int count = votesCount.getOrDefault(voter, 0);
                votesCount.put(voter, count + 1);

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

        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void printVoteStationsWorkTimes() {

        System.out.println("Duplicated voters: ");
        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }
    }

    public void printDuplicatedVoters() {

        System.out.println("Voting station work times: ");
        for (Voter voter : votesCount.keySet()) {
            int count = votesCount.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + " - " + count);
            }
        }
    }
}
