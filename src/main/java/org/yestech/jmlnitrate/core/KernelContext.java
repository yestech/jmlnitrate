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
 * This class represents a Kernel Context.  A Kernel Context is an aggregation
 * of a {@link KernelProcess} and a unique identifier for the process.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
final public class KernelContext {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
        LogFactory.getLog(KernelContext.class);

    /**
     * Holds the internal counter for the process ids.
     */
    private static int totalProcesses = 0;

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the Kernel Process
     */
    private KernelProcess process;

    /**
     * Holds the id associated with the Process
     */
    private int processID;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Private for data integrity gaurentee.
     */
    private KernelContext() {
        super();
    }

    /**
     * Create a new Kernel Context from a KernelProcess.
     *
     * @param process The KernelProcess
     */
    public KernelContext(KernelProcess process) {
        this();
        this.process = process;
        //generate id
        setProcessID(generateProcessID());
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Accessor to get the ID associated with the {@link KernelProcess}
     *
     * @return the Process ID
     */
    public int getProcessID() {
        return processID;
    }

    /**
     * Accessor to get the {@link KernelProcess}
     *
     * @return the Process
     */
    public KernelProcess getProcess() {
        return process;
    }

    //--------------------------------------------------------------------------
    // F R I E N D L Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Mutator to set the KernelProcess of the Context.  It also generates a
     * new ProcessID for the Context.
     *
     * @param process The KernelProcess
     */
    void setProcess(KernelProcess process) {
        this.process = process;
        setProcessID(generateProcessID());
    }

    //--------------------------------------------------------------------------
    // I N T E R N A L   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Generates and Returns a new ProcessID.
     *
     * @return A new ProcessID
     */
    private int generateProcessID() {
        return ++totalProcesses;
    }

    /**
     * Mutator to set a new ProcessID.
     *
     * @param processID The ProcessID to use
     */
    private void setProcessID(int processID) {
        this.processID = processID;
    }
}

