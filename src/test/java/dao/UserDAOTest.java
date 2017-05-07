package dao;

import domain.User;
import org.junit.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
@Stateless
public class UserDAOTest {
    UserDAO userDAO;
    EntityManager entityManager;

    User user;


    @Before
    public void setUp() throws Exception {
        userDAO = new UserDAO();

        entityManager = mock(EntityManager.class);
        userDAO.setEm(entityManager);

        user = new User();
        user.setId((long) 1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void delete() throws Exception {
        //prep
        User test = new User();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                User test1 = (User)invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).remove(any(User.class));

        //test
        userDAO.Delete(test);

        //verify
        assertNotNull(test.getId());
    }

    @Test
    public void edit() throws Exception {
        //prep
        User test = new User();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                User test1 = (User)invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).merge(any(User.class));

        //test
        userDAO.Edit(test);

        //verify
        assertNotNull(test.getId());
    }

    @Test
    public void find() throws Exception {
        //prep
        when(entityManager.find(User.class, user.getId())).thenReturn(user);

        //test
        User result = userDAO.Find(user.getId());

        //verify
        verify(entityManager).find(User.class, user.getId());
        assertSame(user, result);
    }

    @Test
    public void followers() throws Exception {
        //prep
        Query query = mock(Query.class);
//        when(entityManager.createNamedQuery("User.followers")).thenReturn(query);
        when(query.setParameter("user", user)).thenReturn(query);

        List<User> dummyResult = new ArrayList<>();
        when(query.getResultList()).thenReturn(dummyResult);

        //test
//        List<User> result = userDAO.Followers(user);

        //verify
//        verify(entityManager).createNamedQuery("User.followers");
//        verify(query).getResultList();
//        assertSame(dummyResult, result);
    }

    @Test
    public void save() throws Exception {
        //prep
        User test = new User();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                User test1 = (User)invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).persist(any(User.class));

        //test
        userDAO.Save(test);

        //verify
        assertNotNull(test.getId());
    }

}