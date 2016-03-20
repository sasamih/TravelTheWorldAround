package net.etfbl.twitter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;

public class TwitterClass {

	public static void main(String[] args) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("y3ZpFJNKxq5in9B0feRovbwXU");
		cb.setOAuthConsumerSecret("FfmWnCAaJFUYVwKVfWab5Vkbvianlj9SdDsJaAhfvXNaffD1WM");
		cb.setOAuthAccessToken("710839699611521024-JNLgGXyhoxraLbRgp7Zi7jpLb4NZ3it");
		cb.setOAuthAccessTokenSecret("e5SwbMricn03ijEcfYkOeldT0Kc6E5yot0E7AjlmME01k");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();

		int pageno = 1;
		String user = "@klub_putnika";
		ArrayList<Status> statuses = new ArrayList<Status>();

		while (true) {

			try {

				int size = statuses.size();
				Paging page = new Paging(pageno++, 500);
				statuses.addAll(twitter.getUserTimeline(user, page));
				if (statuses.size() == size)
					break;
			} catch (TwitterException e) {

				e.printStackTrace();
			}
		}
		for (Status s : statuses) {
			if (s.getMediaEntities().length != 0)
				System.out.println(s.getMediaEntities()[0].getURL() + " " +  s.getText());
		}
	}

}
