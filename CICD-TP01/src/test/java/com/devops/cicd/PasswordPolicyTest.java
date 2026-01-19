package com.devops.cicd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyTest {

    @Test
    void password_shouldBeInvalid_whenLessThan8Characters() {
        PasswordPolicy policy = new PasswordPolicy();

        boolean result = policy.isStrong("Ab1!");

        assertFalse(result);
    }

    @Test
    void password_shouldBeInvalid_whenNoUppercaseLetter() {
        PasswordPolicy policy = new PasswordPolicy();

        boolean result = policy.isStrong("password1!");

        assertFalse(result);
    }

    @Test
    void password_shouldBeInvalid_whenNoLowercaseLetter() {
        PasswordPolicy policy = new PasswordPolicy();

        boolean result = policy.isStrong("PASSWORD1!");

        assertFalse(result);
    }

    @Test
    void password_shouldBeInvalid_whenNoDigit() {
        PasswordPolicy policy = new PasswordPolicy();

        boolean result = policy.isStrong("Password!");

        assertFalse(result);
    }

    @Test
    void password_shouldBeInvalid_whenNoSpecialCharacter() {
        PasswordPolicy policy = new PasswordPolicy();

        boolean result = policy.isStrong("Password1");

        assertFalse(result);
    }

    @Test
    void password_shouldBeValid_whenAllRulesAreSatisfied() {
        PasswordPolicy policy = new PasswordPolicy();

        boolean result = policy.isStrong("StrongP@ss1");

        assertTrue(result);
    }
}
