package dao;

import domain.Tweet;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import sun.nio.cs.US_ASCII;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
public class TweetDAOTest {
    TweetDAO tweetDAO;
    EntityManager entityManager;

    Tweet tweet;

    @Before
    public void setUp() throws Exception {
        tweetDAO = new TweetDAO();

        entityManager = mock(EntityManager.class);
        tweetDAO.setEm(entityManager);

        tweet = new Tweet();
        tweet.setId(1L);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void save() throws Exception {
        //prep
        Tweet test = new Tweet();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Tweet test1 = (Tweet) invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).persist(any(Tweet.class));

        //test
        tweetDAO.Save(test);

        //verify
        assertNotNull(test.getId());
    }

    @Test
    public void delete() throws Exception {
        //prep
        Tweet test = new Tweet();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Tweet test1 = (Tweet) invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).remove(any(Tweet.class));

        //test
        tweetDAO.Delete(test);

        //verify
        assertNotNull(test.getId());
    }

    @Test
    public void edit() throws Exception {
        //prep
        Tweet test = new Tweet();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Tweet test1 = (Tweet) invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).merge(any(Tweet.class));

        //test
        tweetDAO.Edit(test);

        //verify
        assertNotNull(test.getId());
    }

    @Test
    public void find() throws Exception {
        //prep
        when(entityManager.find(Tweet.class, tweet.getId())).thenReturn(tweet);

        //test
        Tweet result = tweetDAO.Find(tweet.getId());

        //verify
        verify(entityManager).find(Tweet.class, tweet.getId());
        assertSame(tweet, result);
    }

    @Test
    public void getLatestTweets() throws Exception {
        //prep
        Query query = mock(Query.class);
        when(entityManager.createNamedQuery("Tweet.all")).thenReturn(query);
        when(query.setParameter("following", new ArrayList<User>())).thenReturn(query);
        when(query.setMaxResults(10)).thenReturn(query);

        List<Tweet> dummyResult = new ArrayList<>();
        dummyResult.add(tweet);
        when(query.getResultList()).thenReturn(dummyResult);

        //test
        List<Tweet> result = tweetDAO.GetTimeLines(new ArrayList<User>());

        //verify
        verify(entityManager).createNamedQuery("Tweet.all");
        verify(query).getResultList();
        assertSame(dummyResult, result);
    }
}