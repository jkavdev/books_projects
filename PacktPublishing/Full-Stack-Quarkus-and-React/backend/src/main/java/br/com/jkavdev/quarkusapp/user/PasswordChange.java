package br.com.jkavdev.quarkusapp.user;

public record PasswordChange(String currentPassword, String newPassword) {
}
