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
package org.yestech.jmlnitrate.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents a Factory that can create a valid {@link
 * KernelProcess}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class KernelProcessFactory {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
        LogFactory.getLog(KernelProcessFactory.class);

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    private KernelProcessFactory() {
        super();
    }

    //--------------------------------------------------------------------------
    // F A C T O R Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Re-initializes {@link KernelProcess}.  If the ctor is Array is null then
     * the Default ctor will be used to instanciate the Class.
     *
     * <br>
     * Constructor Array Format:
     * <br>
     * <ol>
     *    <li>Array of Classes that are the Types that signal the Constructor to
     *        use.
     *    <li>Array of Objects that are the Arguments that the Constructor
     *        needs.
     * </ol>
     * <br>
     * Method Array Format:
     * <br>
     * <ol>
     *    <li>The name of the Method to execute
     *    <li>Array of Classes that are the Types that signal the Method to use.
     *    <li>Array of Objects that are the Arguments that the Method needs.
     *    <li>{@link Boolean} whether method static or not.
     * </ol>
     *
     * @param process The Process to initialize
     * @param clazz The Class to execute
     * @param ctor Array of Constructor data needed for the Kernel Process
     * @param method Array of Method data needed for the Kernel Process
     * @throws Exception if an error happens creating the Process
     */
    public static void initializeKernelProcess(KernelProcess process, Class
                                               clazz, Object[] ctor, Object[]
                                               method) throws Exception {
        if (process == null || clazz == null || method == null) {
            logger.error("None of the parameters can be null....");
            throw new NullPointerException("None of the parameters can " +
                                           "be null....");
        }
        process.setExecutionClass(clazz);
        setMethod(process, method);
        if (!process.isStaticMethod()) {
            if (logger.isDebugEnabled()) {
                logger.debug("trying to set Ctor: " + ctor);
            }
            setConstructor(process, ctor);
        }
        process.setInitialized(true);
    }

    /**
     * Creatation Method that create a valid and initialized {@link
     * KernelProcess}.  If the ctor is Array is null then
     * the Default ctor will be used to instanciate the Class.
     *
     * <br>
     * Constructor Array Format:
     * <br>
     * <ol>
     *    <li>Array of Classes that are the Types that signal the Constructor to
     *        use.
     *    <li>Array of Objects that are the Arguments that the Constructor
     *        needs.
     * </ol>
     * <br>
     * Method Array Format:
     * <br>
     * <ol>
     *    <li>The name of the Method to execute
     *    <li>Array of Classes that are the Types that signal the Method to use.
     *    <li>Array of Objects that are the Arguments that the Method needs.
     *    <li>{@link Boolean} whether method static or not.
     * </ol>
     *
     * @param name The Name of the Process
     * @param clazz The Class to execute
     * @param ctor Array of Constructor data needed for the Kernel Process
     * @param method Array of Method data needed for the Kernel Process
     * @return An valid and initialized {@link KernelProcess}
     * @throws Exception if an error happens creating the Process
     */
    public static KernelProcess createKernelProcess(String name, Class clazz,
                                                    Object[] ctor, Object[]
                                                    method) throws Exception {
        if (name == null) {
            logger.error("None of the parameters can be null....");
            throw new NullPointerException("None of the parameters can " +
                                           "be null....");
        }

        KernelProcess process = new KernelProcess(name);
        initializeKernelProcess(process, clazz, ctor, method);
        return process;
    }

    //--------------------------------------------------------------------------
    // I N T E R N A L   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Configures the Constructor of the process.
     *
     * @param process The Process to add the Constructor
     * @param ctor The Array of Objects to use by the Constructor
     * @throws Exception if an error happens configuring the Constructor
     */
    private static void setConstructor(KernelProcess process, Object[] ctor)
        throws Exception {
        if (ctor != null && ctor.length != 2) {
            logger.error("The ctor array must be of length 2 or null");
            throw new IllegalArgumentException("The ctor array must be of " +
                                               "length 2 or null");
        }
        if (ctor == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("setting Default Ctor: " + ctor);
            }
            process.setExecutionConstructor();
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("setting Custom Ctor: " + ctor[0] +
                             " ; " + ctor[1]);
            }
            process.setExecutionConstructor((Class[])ctor[0],
                                            (Object[])ctor[1]);
        }
    }

    /**
     * Configures the Method of the process.
     *
     * @throws Exception if an error happens configuring the Method
     */
    private static void setMethod(KernelProcess process, Object[] method)
        throws Exception {
        if (method.length != 4) {
            logger.error("The method array must be of length 4");
            throw new IllegalArgumentException("The method array must be of " +
                                               "length 4");
        }
        boolean staticMethod = ((Boolean)method[3]).booleanValue();
        process.setExecutionMethod((String)method[0], (Class[])method[1],
                                   (Object[])method[2], staticMethod);
    }
}

