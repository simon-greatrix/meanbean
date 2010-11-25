package org.meanbean.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meanbean.util.AssertionUtils;
import org.meanbean.util.SimpleValidationHelper;
import org.meanbean.util.ValidationHelper;

/**
 * Concrete ObjectPropertyEqualityConsistentAsserter that provides a means of verifying whether the equality of an
 * object and its property is consistent. <br/>
 * 
 * This object is specific to testing properties that are "significant" in the implemented equals logic of a given
 * object. Significant properties are those considered by the equals logic. Whether a property is significant or not
 * changes the test's expectation of whether the property will affect the equality of an object. <br/>
 * 
 * Significant properties <strong>should</strong> affect the equality of an object if any of the properties have
 * changed.
 * 
 * @author Graham Williamson
 */
class SignificantObjectPropertyEqualityConsistentAsserter implements ObjectPropertyEqualityConsistentAsserter {

	/** Logging mechanism. */
	private final Log log = LogFactory.getLog(SignificantObjectPropertyEqualityConsistentAsserter.class);

	/** Input validation helper. */
	private final ValidationHelper validationHelper = new SimpleValidationHelper(log);

	/**
	 * Assert that the equality of two logically equivalent objects is consistent when a change is made to a property of
	 * one of the objects. <br/>
	 * 
	 * It is permissible to check the equality of two objects when no change has been made to the named property. This
	 * check is performed when logically equivalent objects are passed to the <i>originalPropertyValue</i> and
	 * <i>newPropertyValue</i> arguments.
	 * 
	 * @param propertyName
	 *            The name of the property that may have changed.
	 * @param originalObject
	 *            The object that the modified object was logically equivalent to prior to its modification.
	 * @param modifiedObject
	 *            The modified object, which was logically equivalent to the original object prior to the modification
	 *            of its named property.
	 * @param originalPropertyValue
	 *            The original value of the modified property. This should still be the value of the named property in
	 *            the original object.
	 * @param newPropertyValue
	 *            The new value of the named property in the modified object.
	 * 
	 * @throws IllegalArgumentException
	 *             If any of the parameters are deemed illegal. For example, if any are <code>null</code>.
	 * @throws AssertionError
	 *             If the equality of the modified object is inconsistent with the changes made to its property.
	 */
	@Override
	public void assertConsistent(String propertyName, Object originalObject, Object modifiedObject,
	        Object originalPropertyValue, Object newPropertyValue) throws IllegalArgumentException, AssertionError {
		log.debug("assertConsistent: Entering with propertyName=[" + propertyName + "], originalObject=["
		        + originalObject + "], modifiedObject=[" + modifiedObject + "], originalPropertyValue=["
		        + originalPropertyValue + "], newPropertyValue=[" + newPropertyValue + "].");
		validationHelper.ensureExists("propertyName", "assert consistency of equals", propertyName);
		validationHelper.ensureExists("originalObject", "assert consistency of equals", originalObject);
		validationHelper.ensureExists("modifiedObject", "assert consistency of equals", modifiedObject);
		validationHelper.ensureExists("originalPropertyValue", "assert consistency of equals", originalPropertyValue);
		validationHelper.ensureExists("newPropertyValue", "assert consistency of equals", newPropertyValue);
		boolean newPropertyValueEqualsOriginalPropertyValue = newPropertyValue.equals(originalPropertyValue);
		boolean originalObjectEqualsModifiedObject = originalObject.equals(modifiedObject);
		String variableString = "(x." + propertyName + "=[" + originalPropertyValue + "] vs y." + propertyName + "=["
		        + newPropertyValue + "])";
		if (originalObjectEqualsModifiedObject && !newPropertyValueEqualsOriginalPropertyValue) {
			String message = "objects that differ due to supposedly significant property [" + propertyName
			        + "] where considered equal. " + variableString + ". is property [" + propertyName
			        + "] actually insignificant?";
			log.debug("assertConsistent: " + message);
			AssertionUtils.fail(message);
		} else if (!originalObjectEqualsModifiedObject && newPropertyValueEqualsOriginalPropertyValue) {
			String message = "objects that should be equal were considered unequal when testing significant "
			        + "property [" + propertyName + "]. " + variableString + ". is equals incorrect?";
			log.debug("assertConsistent: " + message);
			AssertionUtils.fail(message);
		}
		log.debug("assertConsistent: Equals logic is consistent for property [" + propertyName + "]");
	}
}