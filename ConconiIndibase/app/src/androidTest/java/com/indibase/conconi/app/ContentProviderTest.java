package com.indibase.conconi.app;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.indibase.conconi.models.Test;

import java.util.Date;

/**
 * Created by Ralph on 6/2/2015.
 */
public class ContentProviderTest extends ActivityInstrumentationTestCase2<CyclingActivity>{

    CyclingActivity activity;

    public ContentProviderTest(){
        super(CyclingActivity.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testInsert(){
        Test t = new Test(new Date(), 5);
        int actual = activity.storeTest(t);
        assertEquals(2, actual); //only works on empty db (after clean install)
    }

}
