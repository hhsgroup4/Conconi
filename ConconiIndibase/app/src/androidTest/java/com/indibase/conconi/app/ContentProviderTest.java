package com.indibase.conconi.app;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.indibase.conconi.models.Test;
import com.indibase.conconi.test_activities.DatabaseActivity;

import java.util.Date;

/**
 * Created by Ralph on 6/2/2015.
 */
public class ContentProviderTest extends ActivityInstrumentationTestCase2<DatabaseActivity>{

    DatabaseActivity activity;

    public ContentProviderTest(){
        super(DatabaseActivity.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testQuery(){
        Test test = activity.getTest(1);
        System.out.println(test.toString());
        assertEquals(2, test.getId());
        assertEquals(170, test.getDeflection_point());
    }

}
