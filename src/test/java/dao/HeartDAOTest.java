package dao;

import domain.Heart;
import domain.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
public class HeartDAOTest {
    HeartDAO heartDAO;
    EntityManager entityManager;

    Heart heart;

    @Before
    public void setUp() throws Exception {
        heartDAO = new HeartDAO();

        entityManager = mock(EntityManager.class);
        heartDAO.setEm(entityManager);

        heart = new Heart();
        heart.setId(1L);
    }

    @Test
    public void save() throws Exception {
        //prep
        Heart test = new Heart();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Heart test1 = (Heart) invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).persist(any(Tweet.class));

        //test
        heartDAO.Save(test);

        //verify
        assertNotNull(test.getId());
    }

    @Test
    public void delete() throws Exception {
        //prep
        Heart test = new Heart();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Heart test1 = (Heart) invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).remove(any(Tweet.class));

        //test
        heartDAO.Delete(test);

        //verify
        assertNotNull(test.getId());
    }

    @Test
    public void Find() throws Exception {
        //prep
        when(entityManager.find(Heart.class, heart.getId())).thenReturn(heart);

        //test
        Heart result = heartDAO.Find(heart.getId());

        //verify
        verify(entityManager).find(Heart.class, heart.getId());
        assertSame(heart, result);
    }

}