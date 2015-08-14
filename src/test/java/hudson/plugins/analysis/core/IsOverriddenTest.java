package hudson.plugins.analysis.core;

import static org.junit.Assert.*;

import org.junit.Test;

import hudson.plugins.analysis.util.Compatibility;

/**
 * Test for {@link Compatibility.isOverridden} method.
 */
public class IsOverriddenTest {

    /**
     * Test that a method is found by isOverridden even when it is inherited from an intermediate class.
     */
    @Test
    public void isOverriddenTest() {
        assertTrue(Compatibility.isOverridden(Base.class, Derived.class, "method"));
        assertTrue(Compatibility.isOverridden(Base.class, Intermediate.class, "method"));
        assertFalse(Compatibility.isOverridden(Base.class, Base.class, "method"));
    }

    /**
     * Negative test.
     * Trying to check for a method which does not exist in the hierarchy,
     */
    @Test(expected = AssertionError.class)
    public void isOverriddenNegativeTest() {
        Compatibility.isOverridden(Base.class, Derived.class, "method2");
    }

    public abstract class Base {
        protected abstract void method();
    }
    public abstract class Intermediate extends Base {
        protected void method() {}
    }
    public class Derived extends Intermediate {}

}

