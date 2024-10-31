package com.gastos.seguimiento.exception;

public class UserException {

    // Excepción para cuando el usuario no es encontrado
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    // Excepción para cuando el usuario ya existe
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    // Excepción para cuando los datos del usuario son inválidos
    public static class InvalidUserDataException extends RuntimeException {
        public InvalidUserDataException(String message) {
            super(message);
        }
    }

    // Excepción para cuando el usuario no tiene permiso para acceder a un recurso o realizar una acción
    public static class UserUnauthorizedException extends RuntimeException {
        public UserUnauthorizedException(String message) {
            super(message);
        }
    }

    // Excepción para cuando la contraseña es incorrecta
    public static class IncorrectPasswordException extends RuntimeException {
        public IncorrectPasswordException(String message) {
            super(message);
        }
    }

    // Excepción para cuando hay problemas con el token de autenticación JWT
    public static class InvalidTokenException extends RuntimeException {
        public InvalidTokenException(String message) {
            super(message);
        }
    }


}
