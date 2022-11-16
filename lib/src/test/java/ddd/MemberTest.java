package ddd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void getName() {
        Member member = new Member("das08");
        assertEquals(member.getName(), "das08");
    }
}