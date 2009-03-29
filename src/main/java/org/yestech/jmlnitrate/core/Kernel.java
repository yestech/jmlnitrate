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
import org.yestech.lib.lang.Clazz;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * This class represents the actual Micro-Kernel.  The job of the kernel is to
 * manager all the {@link KernelWorker}s and dispatch them correctly and
 * thread-safe.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
final public class Kernel implements Serializable {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
        LogFactory.getLog(Kernel.class);

    /**
     * Holds the single instance of the Kernel
     */
    final private static Kernel kernel = new Kernel();

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    private Kernel() {
        super();
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Executes a KernelContext in a synchronous fashion.
     *
     * @param process The KernelContext to execute
     * @return the Result of the Execution
     * @throws Exception If an error happens during execution
     */
    public Object synchronousExecution(KernelContext process) throws Exception {
        return execute(process.getProcess());
    }

    /**
     * Executes a KernelContext in an asynchronous fashion.
     *
     * @param process The KernelContext to execute
     * @return the Result of the Execution
     * @throws Exception If an error happens during execution
     */
    public Object asynchronousExecution(KernelContext process) throws Exception
    {
        throw new UnsupportedOperationException("Sorry Not Yet Implemented...");
    }

    //--------------------------------------------------------------------------
    // S T A T I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Returns the single copy of the Kernel.
     *
     * @return the Single instance of the Kernel
     */
    public static Kernel getInstance() {
        return kernel;
    }

    /**
     * Creates and returns a {@link KernelProcess}.
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
     * @param processName The Name of the Process
     * @param className The name of the Class to execute
     * @param ctor Array of Constructor data needed for the Kernel Process
     * @param method Array of Method data needed for the Kernel Process
     * @return a KernelProcess
     * @throws Exception if an error happens creating the Process
     */
    public static KernelProcess createKernelProcess(String processName, String
                                                    className, Object[] ctor,
                                                               Object[] method) throws
                                                    Exception {
        return createKernelProcess(processName,
                                   Clazz.getClass(className), ctor,
                                   method);
    }

    /**
     * Creates and returns a {@link KernelProcess}.
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
     * @param processName The Name of the Process
     * @param clazz The Class to execute
     * @param ctor Array of Constructor data needed for the Kernel Process
     * @param method Array of Method data needed for the Kernel Process
     * @return a KernelProcess
     * @throws Exception if an error happens creating the Process
     */
    public static KernelProcess createKernelProcess(String processName, Class
                                                    clazz, Object[] ctor,
                                                           Object[] method) throws
                                                    Exception {
        return KernelProcessFactory.createKernelProcess(processName, clazz,
                                                        ctor, method);
    }

    //--------------------------------------------------------------------------
    // I N T E R N A L   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Adds a KernelProcess to the Pool.
     */
    private void addProcess(KernelProcess process) {
        throw new UnsupportedOperationException("Not yet implemented...");
    }

    /**
     *  Executes a KernelProcess.
     *
     * @param process The Process to Execute
     * @return the Result of the Execution
     * @throws Exception if error happens while running the Process
     */
    private Object execute(KernelProcess process) throws Exception {
        //check to make sure process is initialized
        if (!process.isInitialized()) {
            logger.error("KernelProcess not initialized... Process[" +
                         process + "]");
            throw new IllegalStateException("KernelProcess not " +
                                            "initialized...");
        }

        Class clazz = process.getExecutionClass();
        /*
          if (logger.isDebugEnabled()) {
            logger.debug("process class to execute: " + clazz);
            logger.debug("method Name to execute: " +
                         process.getExecutionMethodName());
            Class[] tmc =  process.getExecutionMethodTypes();
            logger.debug("method Types to execute: " + tmc );
            if (tmc != null) {
                logger.debug("method Types length to execute: " + tmc.length );
                for (int tm = 0; tm < tmc.length; tm++) {
                    logger.debug("method type[" + tm + "]: " + tmc[tm]);
                }
            }
        }
        */
        Method method = Clazz.getMethod(clazz, process.getExecutionMethodName(),
                process.getExecutionMethodTypes());

        Object obj = null;
        //check to see if ctor needed?
        if (!process.isStaticMethod()) {
            Class[] ctorTypes = process.getExecutionConstructorTypes();
            Object[] ctorArgs = process.getExecutionConstructorArgs();
            if (ctorTypes == null && ctorArgs == null) {
                //use default ctor
                obj = clazz.newInstance();
            } else {
                //find the ctor
                Constructor ctor = Clazz.getConstructor(
                    clazz, process.getExecutionConstructorTypes());
                obj = ctor.newInstance(process.getExecutionConstructorArgs());
            }

        }
        return invokeMethod(method, obj, process.getExecutionMethodArgs());
    }

    /**
     * Invokes a method.
     *
     * @param method the Method to invoke
     * @param obj The Object to use in the invocation
     * @param methodArgs the Arguments to the Methods
     * @return the Result of the Execution
     * @throws Exception if an error happens during invocation
     */
    private Object invokeMethod(Method method, Object obj, Object[] methodArgs)
        throws Exception {
        return method.invoke(obj, methodArgs);
    }
}

