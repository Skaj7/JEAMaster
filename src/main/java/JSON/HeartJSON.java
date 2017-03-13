package JSON;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
public class HeartJSON {
    @XmlElement public int userId;
    @XmlElement public int tweetId;
}
