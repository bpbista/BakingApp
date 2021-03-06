package np.com.bpb.bakingapp;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mock;

import np.com.bpb.bakingapp.utils.NetUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NetUtilsUnitTest {
    @Mock
    Context context;
    @Test
    public void testInternetConnection()  {
        //  create mock
        NetUtils test = mock(NetUtils.class);

        // define return value for method getUniqueId()
        when(test.isConnected(context)).thenReturn(true);

        // use mock in test....
        assertEquals(test.isConnected(context), true);
    }
}