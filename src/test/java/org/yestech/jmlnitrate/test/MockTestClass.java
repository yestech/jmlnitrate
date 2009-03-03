/*
 * File name:           $RCSfile: $
 *
 * Revision:            $Revision: $
 * Last revised by:     $Author: $
 * Last revision date:  $Date: $
 *
 * Original Author:     Arthur Copeland
 *
 * Licensed using GPL Available - http://opensource.org/licenses/gpl-license.php
 *
 */
package org.yestech.jmlnitrate.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents a Generic Class used for test purposes <b>ONLY</b>.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class MockTestClass {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(MockTestClass.class);

    //--------------------------------------------------------------------------
    // C O N T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public MockTestClass() {
        super();
        logger.debug("Inside the Default ctor.....");
    }

    /**
     * Default Ctor with a String.
     */
    public MockTestClass(String s) {
        super();
        logger.debug("Inside the String ctor.....");
        logger.debug("String value: " + s);
    }

    /**
     * Default Ctor with an int.
     */
    public MockTestClass(int i) {
        super();
        logger.debug("Inside the int ctor.....");
        logger.debug("int value: " + i);
    }

    /**
     * Default Ctor with an long.
     */
    public MockTestClass(long l) {
        super();
        logger.debug("Inside the long ctor.....");
        logger.debug("long value: " + l);
    }

    /**
     * Default Ctor with an float.
     */
    public MockTestClass(float f) {
        super();
        logger.debug("Inside the float ctor.....");
        logger.debug("float value: " + f);
    }

    /**
     * Default Ctor with an double.
     */
    public MockTestClass(double d) {
        super();
        logger.debug("Inside the double ctor.....");
        logger.debug("double value: " + d);
    }

    /**
     * Default Ctor with an boolean.
     */
    public MockTestClass(boolean b) {
        super();
        logger.debug("Inside the boolean ctor.....");
        logger.debug("boolean value: " + b);
    }

    //--------------------------------------------------------------------------
    // S T A T I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Test instance method
     *
     * @param s String
     * @param i int
     */
    public static void testStatic(String s, int i) {
        logger.debug("Inside the testStatic String and int method.....");
        logger.debug("String value: " + s);
        logger.debug("int value: " + i);
    }

    /**
     * Test instance method
     *
     * @param s String
     * @param i int
     */
    public static void testStatic(int i, String[] s) {
        logger.debug("Inside the testStatic int,String[] method.....");
        logger.debug("int value: " + i);
        logger.debug("String array size: " + s.length);
        for (int l = 0; l < s.length; l++) {
            logger.debug("String array value[" + l + "]: " + s[l]);
        }
    }

    /**
     * Test instance method
     *
     * @param s String
     */
    public static void testStatic(String[] s) {
        logger.debug("Inside the testStatic String[] method.....");
        logger.debug("String array size: " + s.length);
        for (int l = 0; l < s.length; l++) {
            logger.debug("String array value[" + l + "]: " + s[l]);
        }
    }

    /**
     * Test instance method
     *
     * @param i int
     */
    public static void testStatic(int[] i) {
        logger.debug("Inside the testStatic int[] method.....");
        logger.debug("int array size: " + i.length);
        for (int l = 0; l < i.length; l++) {
            logger.debug("int array value[" + l + "]: " + i[l]);
        }
    }

    /**
     * Test instance method
     *
     * @param s String
     */
    public static void testStatic(String s) {
        logger.debug("Inside the testStatic String method.....");
        logger.debug("String value: " + s);
    }

    /**
     * Test instance method
     *
     * @param i int
     */
    public static void testStatic(int i) {
        logger.debug("Inside the testStatic int method.....");
        logger.debug("int value: " + i);
    }

    /**
     * Test instance method
     *
     * @param l long
     */
    public static void testStatic(long l) {
        logger.debug("Inside the testStatic long method.....");
        logger.debug("long value: " + l);
    }

    /**
     * Test instance method
     *
     * @param f float
     */
    public static void testStatic(float f) {
        logger.debug("Inside the testStatic float method.....");
        logger.debug("float value: " + f);
    }

    /**
     * Test instance method
     *
     * @param d double
     */
    public static void testStatic(double d) {
        logger.debug("Inside the testStatic double method.....");
        logger.debug("double value: " + d);
    }

    /**
     * Test instance method
     *
     * @param b boolean
     */
    public static void testStatic(boolean b) {
        logger.debug("Inside the testStatic boolean method.....");
        logger.debug("boolean value: " + b);
    }

    //--------------------------------------------------------------------------
    // I N S T A N C E   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Test instance method
     *
     * @param s String
     */
    public void test(String s) {
        logger.debug("Inside the test String method.....");
        logger.debug("String value: " + s);
    }

    /**
     * Test instance method
     *
     * @param i int
     */
    public void test(int i) {
        logger.debug("Inside the test int method.....");
        logger.debug("int value: " + i);
    }

    /**
     * Test instance method
     *
     * @param l long
     */
    public void test(long l) {
        logger.debug("Inside the test long method.....");
        logger.debug("long value: " + l);
    }

    /**
     * Test instance method
     *
     * @param f float
     */
    public void test(float f) {
        logger.debug("Inside the test float method.....");
        logger.debug("float value: " + f);
    }

    /**
     * Test instance method
     *
     * @param d double
     */
    public void test(double d) {
        logger.debug("Inside the test double method.....");
        logger.debug("double value: " + d);
    }

    /**
     * Test instance method
     *
     * @param b boolean
     */
    public void test(boolean b) {
        logger.debug("Inside the test boolean method.....");
        logger.debug("boolean value: " + b);
    }

}
