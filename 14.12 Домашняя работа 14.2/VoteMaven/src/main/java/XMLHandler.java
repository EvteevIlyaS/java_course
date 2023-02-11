import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
//    private Voter voter;
//    private WorkTime workTime;

//    private SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
//    private SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

//    private HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
//    private HashMap<Voter, Integer> voterCounts = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
            try {
                DBConnection.addVoter(attributes.getValue("name"), attributes.getValue("birthDay"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        try {
//            if (qName.equals("voter") && voter == null) {
//
//                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
//                voter = new Voter(attributes.getValue("name"), birthDay);
//            }
//            else if (qName.equals("visit") && voter != null) {
//                Integer station = Integer.parseInt(attributes.getValue("station"));
//                Date time = visitDateFormat.parse(attributes.getValue("time"));
//                workTime = voteStationWorkTimes.get(station);
//                if (workTime == null) {
//                    workTime = new WorkTime();
//                    voteStationWorkTimes.put(station, workTime);
//                }
//                workTime.addVisitTime(time.getTime());

//                int count = voterCounts.getOrDefault(voter, 0);
//                voterCounts.put(voter, count + 1);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if (qName.equals("voter")) {
//            voter = null;
//        }
//    }
}
