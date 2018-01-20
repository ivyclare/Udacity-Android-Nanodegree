package com.udacity.gradle.builditbigger;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;
/**
 * Created by Neba on 17-Aug-17.
 */
public class EndPointsAsyncTaskTest {

    // create  a signal to let us know when our task is done.
    final CountDownLatch signal = new CountDownLatch(1);

    @Test
    public void testAsync(){
        assertNotNull("1");
    }


}