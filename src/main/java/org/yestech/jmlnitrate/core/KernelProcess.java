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

import org.yestech.jmlnitrate.util.CacheManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * This class represents a process that wishes to be executed by the {@link
 * Kernel}.  It gets associated with a KernelContext.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
final public class KernelProcess implements Serializable {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
        LogFactory.getLog(KernelProcess.class);

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the Name of the Process
     */
    private String name;

    /**
     * Holds the class to execute
     */
    private Class clazz;

    /**
     * Holds whether to method is static or not
     */
    private boolean staticMethod;

    /**
     * Holds whether to Process has been initialized
     */
    private boolean initialized;

    /**
     * Holds the Class Array of Contructor Parameter Types
     */
    private Class[] ctorTypes;

    /**
     * Holds the Object Array of Contructor Parameter Values
     */
    private Object[] ctorArgs;

    /**
     * Holds the Name of the Method to invoke
     */
    private String methodName;

    /**
     * Holds the Class Array of Method Parameter Types
     */
    private Class[] methodTypes;

    /**
     * Holds the Object Array of Method Parameter Values
     */
    private Object[] methodArgs;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    private KernelProcess() {
        super();
        setInitialized(false);
    }

    /**
     * Creates a new Process with a Name.
     *
     * @param name Name of the Process
     */
    public KernelProcess(String name) {
        this();
        this.name = name;
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Accessor to get the Name associated with the Process.
     *
     * @return the Name of the Process
     */
    public String getProcessName() {
        return name;
    }

    /**
     * Mutator to set the class to execute by the kernel.
     *
     * @param clazz The Class to execute
     * @throws Exception if error happens
     */
    public void setExecutionClass(Class clazz) throws Exception {
        this.clazz = clazz;
    }

    /**
     * Mutator to set the class to execute by the kernel.
     *
     * @param clazz The FQN for the Class to execute
     * @throws Exception if error happens
     */
    public void setExecutionClass(String clazz) throws Exception {
        setExecutionClass(CacheManager.getClass(clazz));
    }

    /**
     * Accessor to get the Execution Class gor the Process.
     *
     * @return the Execution Class
     */
    public Class getExecutionClass() {
        return clazz;
    }

    /**
     * Mutator to set the Constructor to use by the Execution Class.  This
     * method assumes that a empty Constructor should be used.
     *
     * @throws Exception if error happens
     */
    public void setExecutionConstructor() throws Exception {
        setExecutionConstructor(null, null);
    }

    /**
     * Mutator to set the Constructor Parameter Types and the Parameter Values
     * to use by the Execution Class.  If the Parameter Types and Parameter
     * Values are null then it is assumed that an empty Constructor should be
     * used.
     *
     * @param ctorTypes Array of the Class Type the Constructor uses
     * @param ctorArgs Array of Objects Arguments to use in the Constructor
     * @throws Exception if error happens
     */
    public void setExecutionConstructor(Class[] ctorTypes, Object[] ctorArgs)
        throws Exception {
        if ((ctorTypes == null && ctorArgs == null) || (ctorTypes != null &&
                                                        ctorArgs != null)) {
            this.ctorTypes = ctorTypes;
            this.ctorArgs = ctorArgs;
        } else {
            logger.error("Either both arguments need to be null or both " +
                         "can't be null..");
            throw new NullPointerException("Either both arguments need to " +
                                           "be null or both can't be null..");
        }
    }

    /**
     * Accessor to get the Constructor Parameter Types to use by the Execution
     * Class.
     *
     * @return The Array of the Class Type the Constructor uses, null if
     * deafault ctor must be used.
     */
    public Class[] getExecutionConstructorTypes() {
        return ctorTypes;
    }

    /**
     * Accessor to get the Array of Constructor Parameter Value Objects to use
     * by the Execution Class.
     *
     * @return The Array of Objects Arguments to use in the Constructor, null if
     * deafault ctor must be used.
     */
    public Object[] getExecutionConstructorArgs() {
        return ctorArgs;
    }


    /**
     * Mutator to set the Method Parameter Types and the Parameter Values
     * to use by the Execution Class.  This method assumes that the method isn't
     * static so a proper Constructor <b>must</b> be supplied.
     *
     * @param methodName The Name of the Method to Execute
     * @param methodTypes Array of the Class Type the Method uses
     * @param methodArgs Array of Objects Arguments to use in the Method
     * @throws Exception if error happens
     */
    public void setExecutionMethod(String methodName, Class[] methodTypes,
                                   Object[] methodArgs) throws Exception {
        setExecutionMethod(methodName, methodTypes, methodArgs, false);
    }

    /**
     * Mutator to set the Method Parameter Types and the Parameter Values
     * to use by the Execution Class.  This method assumes that the method isn't
     * static so a proper Constructor <b>must</b> be supplied.
     *
     * @param methodName The Name of the Method to Execute
     * @param methodTypes Array of the Class Type the Method uses
     * @param methodArgs Array of Objects Arguments to use in the Method
     * @param staticMethod Specifies whether the method is static or not
     * @throws Exception if error happens
     */
    public void setExecutionMethod(String methodName, Class[] methodTypes,
                                   Object[] methodArgs, boolean staticMethod)
        throws Exception {
        this.methodName = methodName;
        this.methodTypes = methodTypes;
        this.methodArgs = methodArgs;
        this.staticMethod = staticMethod;
    }

    /**
     * Accessor to get the Name of the Method to Execute.
     *
     * @return The Name of the Method to Execute
     */
    public String getExecutionMethodName() {
        return methodName;
    }

    /**
     * Accessor to get the Method Parameter Types to use by the Execution
     * Class.
     *
     * @return The Array of the Class Type the Method uses
     */
    public Class[] getExecutionMethodTypes() {
        return methodTypes;
    }

    /**
     * Accessor to get the Array of Method Parameter Value Objects to use
     * by the Execution Class.
     *
     * @return The Array of Objects Arguments to use in the Method
     */
    public Object[] getExecutionMethodArgs() {
        return methodArgs;
    }

    /**
     * Returns whether the method to execute is static or not.
     *
     * @return true if static, else false if not
     */
    public boolean isStaticMethod() {
        return staticMethod;
    }

    /**
     * Returns whether the Process has be properly initialized
     *
     * @return true if static, else false if not
     */
    public boolean isInitialized() {
        return initialized;
    }

    //--------------------------------------------------------------------------
    // F R I E N D L Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Mutator to set that the Process has be properly initialized.
     *
     * @param initialized Whether Process has been initialized
     */
    void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    //--------------------------------------------------------------------------
    // O B J E C T   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Checks if two object are equal.
     *
     * @param obj Object to check
     * @return true if equal, else false
     */
    public boolean equals(Object obj) {
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        KernelProcess tp = (KernelProcess)obj;
        return this.name.equals(tp.name);
    }

    /**
     * Computes a hashcode for the Process.
     *
     * @return a computed hashcode
     */
    public int hashCode() {
        return getClass().hashCode() ^ name.hashCode();
    }

}
