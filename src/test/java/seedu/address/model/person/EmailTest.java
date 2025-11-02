package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("")); // missing local part
        assertFalse(Email.isValidEmail("e1234567u.nus.edu")); // missing '@' symbol
        assertFalse(Email.isValidEmail("e1234567@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("e1234567@-")); // invalid domain name
        assertFalse(Email.isValidEmail("e1234567@u_.nus.edu")); // underscore in domain name
        assertFalse(Email.isValidEmail("e123 4567")); // spaces in local part
        assertFalse(Email.isValidEmail("e1234567@u.nu s.edu")); // spaces in domain name
        assertFalse(Email.isValidEmail(" e1234567")); // leading space
        assertFalse(Email.isValidEmail("e1234567 ")); // trailing space
        assertFalse(Email.isValidEmail("e1234567@")); // double '@' symbol
        assertFalse(Email.isValidEmail("e1234@567")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-e1234567")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("e1234567-")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("e1234..567")); // local part has two consecutive periods
        assertFalse(Email.isValidEmail("e1234567@u.nus@edu")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("e1234567@.nus.edu")); // domain name starts with a period
        assertFalse(Email.isValidEmail("e1234567.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("e1234567@-u.nus.edu")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("e1234567-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("e1234567@u.nus.e")); // top level domain has less than two chars
        assertFalse(Email.isValidEmail("e12345678")); // more than 7 digit
        assertFalse(Email.isValidEmail("a1234567")); // start with a
        assertFalse(Email.isValidEmail("E1234567")); // start with capital E
        assertFalse(Email.isValidEmail("E1234567")); // domain name with all cap

        // valid email
        assertTrue(Email.isValidEmail("e1234567")); // standard
    }

    @Test
    public void equals() {
        Email email = new Email("e1234567");

        // same values -> returns true
        assertTrue(email.equals(new Email("e1234567")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("e1234557")));
    }
}
