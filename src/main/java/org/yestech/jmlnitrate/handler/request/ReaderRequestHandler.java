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
package org.yestech.jmlnitrate.handler.request;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * This {@link RequestHandler} represents an Adaptor for a {Reader}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class ReaderRequestHandler extends BaseRequestHandler {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(ReaderRequestHandler.class);

    /**
     * Holds the default buffer size
     */
    final private static int DEFAULT_BUFFER_SIZE = 92;

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the Reader in a Buffered state.
     */
    private BufferedReader stream;

    /**
     * Holds the buffer size to use
     */
    private int bufferSize = DEFAULT_BUFFER_SIZE;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public ReaderRequestHandler() {
        super();
    }

    //--------------------------------------------------------------------------
    // P A C K A G E   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Mutator to set internal Reader.
     *
     * @param stream This is the InputStream to use
     */
    void setStream(InputStream stream) throws Exception {
        setStream(new InputStreamReader(stream));
    }

    /**
     * Mutator to set internal Reader.
     *
     * @param stream This is the Reader
     */
    void setStream(Reader stream) throws Exception {
        setStream(stream, bufferSize);
    }

    /**
     * Mutator to set internal Reader with a buffer size supplied.
     *
     * @param stream This is the Reader
     * @param bufferSize This is the Size of the Buffer
     */
    void setStream(Reader stream, int bufferSize) throws Exception {
        if (bufferSize < 1) {
            logger.warn("Sorry but the bufferSize must be atleast 1");
            throw new IllegalArgumentException("Sorry but the " +
                                               "bufferSize must be atleast 1");
        }
        if (stream == null) {
            logger.warn("Sorry but the stream must not be NULL");
            throw new NullPointerException("Sorry but the " +
                                               "stream must not be NULL");
        }

        this.bufferSize = bufferSize;
        this.stream = new BufferedReader(stream, this.bufferSize);
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Accessor to return the internal {@link Reader} of the request.
     *
     * @return the {@link BufferedReader}
     */
    public BufferedReader getStream() {
        return stream;
    }

    /**
     * Accessor to return the size of the buffer of the internal {@link
     * Reader} of the request.
     *
     * @return the buffer size
     */
    public int getBufferSize() {
        return bufferSize;
    }
}

