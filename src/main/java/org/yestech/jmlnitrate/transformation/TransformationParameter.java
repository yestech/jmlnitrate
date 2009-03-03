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
package org.yestech.jmlnitrate.transformation;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * This class represents a Enumerated Type for <b>ALL</b> the <b>General</b>
 * Parameters that are needed during {@link Transformation}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
final public class TransformationParameter implements Serializable {

    //--------------------------------------------------------------------------
    // I N N E R   C L A S S E S 
    //--------------------------------------------------------------------------
    /**
     * This internal interface gives any specific Parameters class the ability
     * to extend the {@link TransformationParameter} class.
     */
    public interface TransformationParameterConstants extends Serializable {
        //----------------------------------------------------------------------
        // P A R A M E T E R   T O K E N I Z E R S 
        //----------------------------------------------------------------------
        /**
         * Holds the Class Request Parameter
         */
        final public static TransformationParameter DELIMETER =
            TransformationParameter.DELIMETER;

        /**
         * Holds the Class Request Parameter
         */
        final public static TransformationParameter SEPARATOR =
            TransformationParameter.SEPARATOR;

        //----------------------------------------------------------------------
        // R E Q U E S T   P A R A M E T E R S 
        //----------------------------------------------------------------------
        /**
         * Holds the Class Request Parameter
         */
        final public static TransformationParameter CLASS =
            TransformationParameter.CLASS;

        /**
         * Holds the Constructor Request Parameter
         */
        final public static TransformationParameter CONSTRUCTOR =
            TransformationParameter.CONSTRUCTOR;

        /**
         * Holds the Constructor Argument Request Parameter
         */
        final public static TransformationParameter CONSTRUCTOR_ARGUMENT =
            TransformationParameter.CONSTRUCTOR_ARGUMENT;

        /**
         * Holds the Method Request Parameter
         */
        final public static TransformationParameter METHOD =
            TransformationParameter.METHOD;

        /**
         * Holds the Method Argument Request Parameter
         */
        final public static TransformationParameter METHOD_ARGUMENT =
            TransformationParameter.METHOD_ARGUMENT;

        /**
         * Holds the Type Request Parameter
         */
        final public static TransformationParameter TYPE =
            TransformationParameter.TYPE;

        //----------------------------------------------------------------------
        // A R G U M E N T   P A R A M E T E R S 
        //----------------------------------------------------------------------
        /**
         * Holds the Int Primitive Argument Parameter
         */
        final public static TransformationParameter INT =
            TransformationParameter.INT;

        /**
         * Holds the Long Primitive Argument Parameter
         */
        final public static TransformationParameter LONG =
            TransformationParameter.LONG;

        /**
         * Holds the Float Primitive Argument Parameter
         */
        final public static TransformationParameter FLOAT =
            TransformationParameter.FLOAT;

        /**
         * Holds the Double Primitive Argument Parameter
         */
        final public static TransformationParameter DOUBLE =
            TransformationParameter.DOUBLE;

        /**
         * Holds the Byte Primitive Argument Parameter
         */
        final public static TransformationParameter BYTE =
            TransformationParameter.BYTE;

        /**
         * Holds the Boolean Primitive Argument Parameter
         */
        final public static TransformationParameter BOOLEAN =
            TransformationParameter.BOOLEAN;

        /**
         * Holds the Char Primitive Argument Parameter
         */
        final public static TransformationParameter CHAR =
            TransformationParameter.CHAR;
    }

    //--------------------------------------------------------------------------
    // P A R A M E T E R   T O K E N I Z E R S 
    //--------------------------------------------------------------------------
    /**
     * Request Parameter Tokenizer
     */
    final public static TransformationParameter DELIMETER = new
        TransformationParameter(";", new Integer(0));

    /**
     * Argument Parameter Tokenizer
     */
    final public static TransformationParameter SEPARATOR = new
        TransformationParameter("^", new Integer(1));

    //--------------------------------------------------------------------------
    // R E Q U E S T   P A R A M E T E R S 
    //--------------------------------------------------------------------------
    /**
     * Class Request Parameter
     */
    final public static TransformationParameter CLASS = new
        TransformationParameter("class", new Integer(2));

    /**
     * Constructor Request Parameter
     */
    final public static TransformationParameter CONSTRUCTOR = new
        TransformationParameter("constructor", new Integer(4));

    /**
     * Method Request Parameter
     */
    final public static TransformationParameter METHOD = new
        TransformationParameter("method", new Integer(8));

    /**
     * Type Request Parameter
     */
    final public static TransformationParameter TYPE = new
        TransformationParameter("type", new Integer(16));

    /**
     * Constructor Argument Request Parameter
     */
    final public static TransformationParameter CONSTRUCTOR_ARGUMENT = new
        TransformationParameter("constructorArgument", new Integer(32));

    /**
     * Method Argument Request Parameter
     */
    final public static TransformationParameter METHOD_ARGUMENT = new
        TransformationParameter("methodArgument", new Integer(64));

    //--------------------------------------------------------------------------
    // A R G U M E N T   P A R A M E T E R S 
    //--------------------------------------------------------------------------
    /**
     * Int Primitive Argument Parameter
     */
    final public static TransformationParameter INT = new
        TransformationParameter("int", new Integer(3));

    /**
     * Long Primitive Argument Parameter
     */
    final public static TransformationParameter LONG = new
        TransformationParameter("long", new Integer(9));

    /**
     * Float Primitive Argument Parameter
     */
    final public static TransformationParameter FLOAT = new
        TransformationParameter("float", new Integer(27));

    /**
     * Double Primitive Argument Parameter
     */
    final public static TransformationParameter DOUBLE = new
        TransformationParameter("double", new Integer(81));

    /**
     * Byte Primitive Argument Parameter
     */
    final public static TransformationParameter BYTE = new
        TransformationParameter("byte", new Integer(243));

    /**
     * Boolean Primitive Argument Parameter
     */
    final public static TransformationParameter BOOLEAN = new
        TransformationParameter("boolean", new Integer(729));

    /**
     * Char Primitive Argument Parameter
     */
    final public static TransformationParameter CHAR = new
        TransformationParameter("char", new Integer(2187));

    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(TransformationParameter.class);

    /**
     * Holds the Enumeration by name
     */
    private static HashMap typeByName = new HashMap();

    /**
     * Holds the Enumeration by identifier
     */
    private static HashMap typeById = new HashMap();

    //--------------------------------------------------------------------------
    // S T A T I C   I N I T I A L I Z E R S 
    //--------------------------------------------------------------------------
    //setting up the enumeration
    static {
        //parameters by name
        typeByName.put(DELIMETER.getName(), DELIMETER);
        typeByName.put(SEPARATOR.getName(), SEPARATOR);
        typeByName.put(CLASS.getName(), CLASS);
        typeByName.put(CONSTRUCTOR.getName(), CONSTRUCTOR);
        typeByName.put(METHOD.getName(), METHOD);
        typeByName.put(CONSTRUCTOR_ARGUMENT.getName(), CONSTRUCTOR_ARGUMENT);
        typeByName.put(METHOD_ARGUMENT.getName(), METHOD_ARGUMENT);
        typeByName.put(TYPE.getName(), TYPE);
        typeByName.put(INT.getName(), INT);
        typeByName.put(LONG.getName(), LONG);
        typeByName.put(FLOAT.getName(), FLOAT);
        typeByName.put(DOUBLE.getName(), DOUBLE);
        typeByName.put(BYTE.getName(), BYTE);
        typeByName.put(BOOLEAN.getName(), BOOLEAN);
        typeByName.put(CHAR.getName(), CHAR);

        //parameters by id
        typeById.put(DELIMETER.getIdentifier(), DELIMETER);
        typeById.put(SEPARATOR.getIdentifier(), SEPARATOR);
        typeById.put(CLASS.getIdentifier(), CLASS);
        typeById.put(CONSTRUCTOR.getIdentifier(), CONSTRUCTOR);
        typeById.put(METHOD.getIdentifier(), METHOD);
        typeById.put(CONSTRUCTOR_ARGUMENT.getIdentifier(),
                     CONSTRUCTOR_ARGUMENT);
        typeById.put(METHOD_ARGUMENT.getIdentifier(), METHOD_ARGUMENT);
        typeById.put(TYPE.getIdentifier(), TYPE);
        typeById.put(INT.getName(), INT);
        typeById.put(LONG.getName(), LONG);
        typeById.put(FLOAT.getName(), FLOAT);
        typeById.put(DOUBLE.getName(), DOUBLE);
        typeById.put(BYTE.getName(), BYTE);
        typeById.put(BOOLEAN.getName(), BOOLEAN);
        typeById.put(CHAR.getName(), CHAR);
    }

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the name of the Parameter
     */
    private String name;

    /**
     * Holds the identifier of the Parameter
     */
    private Integer id;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    private TransformationParameter() {
        super();
    }

    /**
     * Creates a new TransformationParameter with a speciic name and identifier.
     *
     * @param name The Name of the Parameter
     * @param id The Identifier of the Parameter
     */
    private TransformationParameter(String name, Integer id) {
        this();
        this.name = name;
        this.id = id;
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Accessor to get the Name of the Parameter.
     *
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor to get the identifier of the Parameter
     *
     * @return the Identifier
     */
    public Integer getIdentifier() {
        return id;
    }

    //--------------------------------------------------------------------------
    // S T A T I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Retrieves the Parameter that is associated with the Name.
     *
     * @param name Name to lookup
     * @return the Parameter if found else null
     */
    public static TransformationParameter getByName(String name) {
        return (TransformationParameter)typeByName.get(name);
    }

    /**
     * Retrieves the Parameter that is associated with the Identifier.
     *
     * @param id Identifier to lookup
     * @return the Parameter if found else null
     */
    public static TransformationParameter getByIdentifier(int id) {
        return getByIdentifier(new Integer(id));
    }

    /**
     * Retrieves the Parameter that is associated with the Identifier.
     *
     * @param id Identifier to lookup
     * @return the Parameter if found else null
     */
    public static TransformationParameter getByIdentifier(Integer id) {
        return (TransformationParameter)typeById.get(id);
    }

    /**
     * Returns an unmodifiable Collection of Parameters that are known about.
     *
     * @return the Collection of Parameters
     */
    public static Collection getParameters() {
        return Collections.unmodifiableCollection(typeById.values());
    }

    //--------------------------------------------------------------------------
    // S E R I A L I Z A B L E   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * This method handles resolving a deserialized instance to a known
     * globally available instance.
     */
    private Object readResolve() throws ObjectStreamException {
        return TransformationParameter.getByIdentifier(this.id);
    }

    //--------------------------------------------------------------------------
    // O B J E C T   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Computes a hashcode for the Parameter.
     *
     * @return the hashcode
     */
    public int hashCode() {
        return id.hashCode();
    }
}

