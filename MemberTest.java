package D4.nhyh;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberTest {
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("Leila Rock", "lrock@gmail.com", "5129087654");
    }

    @Test
    void testGetName_ShouldReturnCorrectName() {
        assertEquals("Leila Rock", member.getName());
    }

    @Test
    void testGetEmail_ShouldReturnCorrectEmail() {
        assertEquals("lrock@gmail.com", member.getEmail());
    }

    @Test
    void testGetPhone_ShouldReturnCorrectPhone() {
        assertEquals("5129087654", member.getPhone());
    }

    @Test
    void TestGetId_ShouldReturnIncrementedId() {
        Member anotherMember = new Member("Dave Patel", "dave@yahoo.com", "5083245677");
        assertTrue(anotherMember.getId() > member.getId());
    }

    @Test
    void testSetName_ShouldChangeName() {
        member.setName("Jay Pichett");
        assertEquals("Jay Pichett", member.getName());
    }

    @Test
    void testSetEmmail_ShouldChangeEmail() {
        member.setEmail("pichett08@gmail.com");
        assertEquals("pichett08@gmail.com", member.getEmail());
    }

    @Test
    void testGetPhone_ShouldChangePhone() {
        member.setPhone("5081113344");
        assertEquals("5081113344", member.getPhone());
    }

    @Test
    void testUniqueIDGeneration() {
        Member member1 = new Member("Leila Rock", "lrock@gmail.com", "5129087654");
        Member member2 = new Member("Dave Patel", "dave@yahoo.com", "5083245677");
        assertNotEquals(member1.getId(), member2.getId());
        assertTrue(member2.getId() > member1.getId());
    }
}