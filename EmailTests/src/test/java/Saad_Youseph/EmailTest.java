package Saad_Youseph;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.mail.SimpleEmail;
import javax.mail.Session;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    private Email email;

    @BeforeEach
    void setUp() {
        email = new SimpleEmail();
    }

    @AfterEach
    void tearDown() {
        email = null;
    }

    @Test
    void testAddBcc() throws EmailException {
        email.addBcc("test1@example.com", "test2@example.com");
        assertEquals(2, email.getBccAddresses().size(), "Expected 2 BCC addresses");
    }

    @Test
    void testAddCc() throws EmailException {
        email.addCc("testcc@example.com");
        assertEquals(1, email.getCcAddresses().size(), "Expected 1 CC address");
    }

    @Test
    void testAddHeader() {
        email.addHeader("X-CUSTOM-HEADER", "HeaderValue");
        // Just check no exception thrown:
        assertTrue(true);
    }

    @Test
    void testAddReplyTo() throws EmailException {
        email.addReplyTo("reply@example.com", "Reply Name");
        assertEquals(1, email.getReplyToAddresses().size(), "Expected 1 reply-to address");
    }

    @Test
    void testBuildMimeMessage() throws EmailException {
        email.setHostName("localhost");
        email.setFrom("sender@example.com");
        email.addTo("recipient@example.com");

        email.buildMimeMessage();
        assertNotNull(email.getMimeMessage(), "MimeMessage should be built");
    }

    @Test
    void testGetHostName() {
        email.setHostName("smtp.example.com");
        assertEquals("smtp.example.com", email.getHostName());
    }

    @Test
    void testGetMailSession() throws EmailException {
        email.setHostName("smtp.example.com");
        Session session = email.getMailSession();
        assertNotNull(session, "Mail session should not be null");
    }

    @Test
    void testGetSentDate() {
        Date date = new Date();
        email.setSentDate(date);
        assertEquals(date, email.getSentDate(), "Sent date should match");
    }

    @Test
    void testGetSocketConnectionTimeout() {
        email.setSocketConnectionTimeout(5000);
        assertEquals(5000, email.getSocketConnectionTimeout(), "Socket timeout mismatch");
    }

    @Test
    void testSetFrom() throws EmailException {
        email.setFrom("from@example.com");
        assertNotNull(email.getFromAddress(), "From address should not be null");
        assertEquals("from@example.com", email.getFromAddress().getAddress(), "From address mismatch");
    }
}