package com.microsoft.identity.common.adal.internal.util;

import com.microsoft.identity.common.adal.internal.AndroidTestHelper;
import com.microsoft.identity.common.adal.internal.ReflectionUtils;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class HashMapExtensionTests extends AndroidTestHelper {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testURLFormDecodeNegative() throws IllegalArgumentException,
            ClassNotFoundException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        final String methodName = "urlFormDecode";
        Object object = ReflectionUtils.getNonPublicInstance("com.microsoft.identity.common.adal.internal.util.HashMapExtensions");
        Method m = ReflectionUtils.getTestMethod(object, methodName, String.class);
        HashMap<String, String> result = (HashMap<String, String>) m.invoke(object, "nokeyvalue");
        assertNotNull(result);
        assertTrue(result.isEmpty());

        result = (HashMap<String, String>) m.invoke(object, "&&&");
        assertNotNull(result);
        assertTrue(result.isEmpty());

        result = (HashMap<String, String>) m.invoke(object, "=&=");
        assertNotNull(result);
        assertTrue(result.isEmpty());

        result = (HashMap<String, String>) m.invoke(object, "=&");
        assertNotNull(result);
        assertTrue(result.isEmpty());

        result = (HashMap<String, String>) m.invoke(object, "&=");
        assertNotNull(result);
        assertTrue(result.isEmpty());

        result = (HashMap<String, String>) m.invoke(object, "&a=");
        assertNotNull(result);
        assertTrue(result.isEmpty());

        result = (HashMap<String, String>) m.invoke(object, "&=b");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testURLFormDecodePositive() throws IllegalArgumentException,
            ClassNotFoundException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        final String methodName = "urlFormDecode";
        Object object = ReflectionUtils.getNonPublicInstance("com.microsoft.identity.common.adal.internal.util.HashMapExtensions");
        Method m = ReflectionUtils.getTestMethod(object, methodName, String.class);
        HashMap<String, String> result = (HashMap<String, String>) m.invoke(object, "a=b&c=2");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.containsKey("a"));
        assertTrue(result.containsKey("c"));
        assertTrue(result.containsValue("b"));
        assertTrue(result.containsValue("2"));

        assertTrue(result.containsKey("a"));
        assertTrue(result.containsKey("c"));
        assertTrue(result.containsValue("b"));
        assertTrue(result.containsValue("2"));

        result = (HashMap<String, String>) m.invoke(object, "a=v");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.containsKey("a"));
        assertTrue(result.containsValue("v"));

        result = (HashMap<String, String>) m.invoke(object, "d=f&");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.containsKey("d"));
        assertTrue(result.containsValue("f"));
        assertTrue(result.size() == 1);
    }
}