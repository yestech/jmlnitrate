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
package org.yestech.jmlnitrate.transformation.inbound;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.transformation.BaseTransformation;
import org.yestech.jmlnitrate.transformation.TransformationParameter;
import org.yestech.lib.cache.InMemoryReflectionCache;
import org.yestech.lib.lang.Clazz;

import java.io.BufferedReader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
/**
 *  This class represents the Base class for <b>ALL</b> {@link
 *  InboundTransformation}.
 *
 * @author  Arthur Copeland
 * @version  $Revision: 3 $
 */
public abstract class BaseInboundTransformation extends BaseTransformation
     implements InboundTransformation
{
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S
    //--------------------------------------------------------------------------
    /**
     *  Holds the logger
     */
    final private static Log logger = LogFactory.getLog(BaseInboundTransformation.class);

    /**
     *  Holds the buffer size in bytes 94 bytes
     */
    private final int BUFFER_SIZE = 94 * 1;


    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S
    //--------------------------------------------------------------------------
    /**
     *  Default Ctor.
     */
    public BaseInboundTransformation() {
        super();
    }


    //--------------------------------------------------------------------------
    // P R O T E C T E D   M E T H O D S
    //--------------------------------------------------------------------------
    /**
     *  Retrieves the Constructor Array from the raw string request. The request of
     *  the String must be: <br>
     *  <pre>
     *  null - if empty / default ctor
     *
     *  class type for ctor ^
     * </pre>
     *
     * @param  ctor the Raw Ctor Request to convert
     * @return  an Object Array in the valid format for the {@link
     *      org.yestech.jmlnitrate.core.Kernel#createKernelProcess(String, String,
     *      Object[], Object[])}
     * @throws  Exception if error happens
     */
    protected Object[] getConstructor(String ctor) throws Exception {
        Object[] ctorArray = new Object[2];
        //check for default ctor
        if (ctor == null || ctor.equals("")) {
            ctor = null;
        } else {
            throw new UnsupportedOperationException("Not Yet implemented....");
            /*
                *  Object[] request = parseParameterRequest(ctor);
                *  /make more dynamic
                *  Class[] ctorClasses = new Class[1];
                *  ctorClasses[0] = getClass((String)request[0]);
                *  /make more dynamic
                *  /get the instanciated objects needed for the constructor
                *  Object[] ctorObjects = new Object[1];
                *  for (int i = 0; i < 1; i++) {
                *  /get the class
                *  Class tempClazz = Class.forName(ctorTypes[i]);
                *  ctorClasses[i] = tempClazz;
                *  /get the Object
                *  Class[] tempClasses = { tempClazz };
                *  Object[] tempArgs = { ctorArgs[i] };
                *  Constructor tempCtor =
                *  tempClazz.getConstructor(tempClasses);
                *  ctorObjects[i] = tempCtor.newInstance(tempArgs);
                *  }
                *  ctorArray[0] = ctorClasses;
                *  ctorArray[1] = ctorObjects;
                */
        }
        return ctorArray;
    }


    /**
     *  Retrieves the Method Array to execute from a method arguments arrays
     *  describing the Methods. <br>
     *  Method Array Format:
     *  <ol>
     *    <li> **Method Name - String <b>Mandatory</b>
     *    <li> **Static method - Boolean <b>Mandatory</b>
     *    <li> Method Argument Type - String Optional [0..*]
     *  </ol>
     *  <br>
     *  Method Argument Array Format:
     *  <ol>
     *    <li>
     *  </ol>
     *
     *
     * @param  method Array describing the method to create
     * @param  arguments Array of arguments needed for the Method
     * @return  an Object Array in the valid format for the {@link
     *      org.yestech.jmlnitrate.core.Kernel#createKernelProcess(String, String,
     *      Object[], Object[])}
     * @throws  Exception if error happens
     */
    protected Object[] getMethod(Object[] method, Object[] arguments) throws
        Exception {
        Object[] executeMethod = new Object[4];
        if (method == null || method.length < 2 || (arguments != null &&
            arguments.length < 1)) {
            logger.error("arguments must not be null....");
            throw new NullPointerException("arguments must not be null....");
        }
        String methodName = (String) method[0];

        Boolean staticMethod = Boolean.valueOf((String) method[1]);

        //check to see if method is empty or not.  short circuit if it is
        if (method.length == 2) {
            executeMethod[0] = methodName;
            executeMethod[1] = null;
            executeMethod[2] = null;
            executeMethod[3] = staticMethod;
        } else {
            //get the class
            int typeSize = method.length;
            Class[] methodTypes = new Class[typeSize - 2];
            for (int t = 2, mt = 0; t < typeSize; t++, mt++) {
                methodTypes[mt] = getClass((String) method[t]);
            }

            //get the Method Objects
            int argSize = arguments.length;
            Object[] methodArgs = new Object[argSize];
            boolean isArray = false;

            for (int i = 0; i < argSize; i++) {
                //check to make sure arguments holds an Object Array
                Object[] parsedArgValues = (Object[]) arguments[i];
                if (parsedArgValues.length == 0) {
                    methodArgs[i] = null;
                } else {
                    //find out if an array is needed?
                    isArray = parsedArgValues[0].getClass().isArray();
                    if (isArray) {
                        int asize = parsedArgValues.length;
                        Object array = Array.newInstance(methodTypes[i], asize);
                        methodTypes[i] = array.getClass();
                        for (int a = 0; a < asize; a++) {
                            Object value = null;
                            Object[] aparams = (Object[]) parsedArgValues[a];
                            int apsize = aparams.length;
                            //check to see if ctor needed
                            if (apsize > 1) {
                                //ctor needed
                                Object[] actorArgs = {aparams[0]};
                                Class[] actorTypes = {
                                    getClass((String) aparams[2])};
                                Class actorClass = getClass((String) aparams[1]);
                                Constructor actor =
                                    InMemoryReflectionCache.getCtor(actorClass,
                                    actorTypes);

                                value = actor.newInstance(actorArgs);
                            } else {
                                //this is the value doesn't need a ctor
                                value = aparams[0];
                            }
                            Array.set(array, a, value);
                        }
                        methodArgs[i] = array;
                    } else {
                        int psize = parsedArgValues.length;

                        //check to see if ctor needed
                        if (psize > 1) {
                            //ctor needed
                            Object[] ctorArgs = {parsedArgValues[0]};
                            Class[] ctorTypes = {
                                getClass((String) parsedArgValues[2])};
                            Class ctorClass =
                                getClass((String) parsedArgValues[1]);

                            Constructor ctor =
                                InMemoryReflectionCache.getCtor(ctorClass,
                                ctorTypes);

                            methodArgs[i] = ctor.newInstance(ctorArgs);
                        } else {
                            //this is the value doesn't need a ctor
                            Object pvalue = parsedArgValues[0];
                            methodArgs[i] = pvalue;
                        }
                    }
                }
            }

            executeMethod[0] = methodName;
            executeMethod[1] = methodTypes;
            executeMethod[2] = methodArgs;
            executeMethod[3] = staticMethod;
        }

        return executeMethod;
    }


    /**
     *  Retrieves the Method Array from the raw string request. The request of the
     *  String must be: <br>
     *  <pre>
     * </pre>
     *
     * @param  method the Raw Method Request to convert
     * @return  an Object Array in the valid format for the {@link
     *      org.yestech.jmlnitrate.core.Kernel#createKernelProcess(String, String,
     *      Object[], Object[])}
     * @throws  Exception if error happens
     */
    protected Object[] getMethod(String method) throws
        Exception {
        if (method == null || method.equals("")) {
            logger.error("Parameter " + TransformationParameter.METHOD.getName()
                 + "must be supplied....");
            throw new NullPointerException("Parameter " +
                TransformationParameter.METHOD.getName()
                 + " must be supplied....");
        }
        return parseParameterRequest(method);
    }


    /**
     *  Retrieves the Method Array from the raw string request. The request of the
     *  String must be: <br>
     *  <pre>
     * </pre>
     *
     * @param  arguments The Array of Arguments needed for the Method
     * @return  an Object Array in the valid format for the {@link
     *      org.yestech.jmlnitrate.core.Kernel#createKernelProcess(String, String,
     *      Object[], Object[])}
     * @throws  Exception if error happens
     */
    protected Object[] getMethodArguments(Object[] arguments)
         throws Exception {
        if ((arguments != null && arguments.length != 0) && arguments.length <
            1) {
            logger.error("Parameter " +
                TransformationParameter.METHOD_ARGUMENT.getName()
                 + "must be supplied....");
            throw new NullPointerException("Parameter " +
                TransformationParameter.METHOD_ARGUMENT.getName()
                 + " must be supplied....");
        }
        if (arguments == null || (arguments != null && arguments.length == 0)) {
            return null;
        } else {
            int size = arguments.length;
            Object[] args = new Object[size];
            for (int i = 0; i < size; i++) {
                Object arg = arguments[i];
                if (arg.getClass().isArray()) {
                    Object[] argArray = (Object[]) arg;
                    int as = argArray.length;
                    if (as == 1) {
                        args[i] = parseParameterRequest((String) argArray[0]);
                    } else {
                        Object[] ta = new Object[as];
                        for (int a = 0; a < as; a++) {
                            ta[a] = parseParameterRequest((String) argArray[a]);
                        }
                        args[i] = ta;
                    }
                } else {
                    args[i] = parseParameterRequest((String) arg);
                }
            }
            return args;
        }
    }


    /**
     *  Returns the Class Type for a Primitive type.
     *
     * @param  classType Description of the Parameter
     * @return  the Class Type
     * @exception  Exception Description of the Exception
     */
    private Class getClass(String classType) throws Exception {
        TransformationParameter classParameter =
            TransformationParameter.getByName(classType);
        Class type = null;
        if (classParameter != null) {
            if (classParameter.equals(TransformationParameter.INT)) {
                //int
                type = Integer.TYPE;
            } else if (classParameter.equals(TransformationParameter.LONG)) {
                //long
                type = Long.TYPE;
            } else if (classParameter.equals(TransformationParameter.FLOAT)) {
                //float
                type = Float.TYPE;
            } else if (classParameter.equals(TransformationParameter.DOUBLE)) {
                //double
                type = Double.TYPE;
            } else if (classParameter.equals(TransformationParameter.BOOLEAN)) {
                //boolean
                type = Boolean.TYPE;
            } else if (classParameter.equals(TransformationParameter.CHAR)) {
                //char
                type = Character.TYPE;
            } else if (classParameter.equals(TransformationParameter.BYTE)) {
                //byte
                type = Byte.TYPE;
            } else {
                logger.error("Not a valid Class Type...");
                throw new IllegalArgumentException("Not a valid Class Type...");
            }
        } else {
            type = Clazz.getClass(classType);
        }
        return type;
    }


    /**
     *  Returns an Array of a Class Type
     *
     * @return  and Array
     */
    private Object getArray() {
        return null;
    }


    /**
     *  Parses a Parameter Request String and return a array of the tokens in the
     *  string. The default delimeters are ^ and ;
     *
     * @param  rawRequest the Parameter Request
     * @return  Array of the tokens
     * @throws  Exception if an error happens
     */
    private Object[] parseParameterRequest(String rawRequest) throws
        Exception {
        StreamTokenizer tokenStream = new StreamTokenizer(new
            BufferedReader(new StringReader(rawRequest), BUFFER_SIZE));
        //reset the stream
        tokenStream.resetSyntax();
        //configure tokens
        tokenStream.lowerCaseMode(false);
        tokenStream.eolIsSignificant(false);

        //add word chars
        tokenStream.wordChars(32, 58);
        tokenStream.wordChars(60, 93);
        tokenStream.wordChars(95, 126);

        //add <CR>\r <LF>\n
        tokenStream.wordChars(10, 10);
        tokenStream.wordChars(13, 13);

        //set ^ AND ; as delimeters to string tokens
        tokenStream.quoteChar(94);

        //removed ;
        //tokenStream.quoteChar(59);

        //set up the temp arraylist
        ArrayList tokens = new ArrayList();

        int result = tokenStream.ttype;
        while (result != StreamTokenizer.TT_EOF) {
            result = tokenStream.nextToken();
            switch (result) {
                            case StreamTokenizer.TT_EOF:
                                break;
                            default:
                                tokens.add(tokenStream.sval);
                                break;
            }
        }
        return tokens.toArray();
    }
}
