# java-cryptography-labs

Java-based backend application built with Spring Boot. It provides robust API endpoints for encrypting and decrypting data using AES encryption standards. This application is designed to demonstrate secure cryptographic operations, suitable for educational purposes or as a base for more complex applications.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- JDK 17 or higher
- Gradle 7.0 or higher

### Installation

1. **Clone the repository:**

```bash
git clone https://github.com/squidmin/java-cryptography-labs.git
cd java-cryptography-labs
./gradlew build
./gradlew bootRun
```

The server will start running on http://localhost:8080.

## Usage

The application provides the following RESTful endpoints:

- **POST /api/crypto/encrypt** - Takes plain text and returns the encrypted data.
- **POST /api/crypto/decrypt** - Takes encrypted text and returns the decrypted data.

You can use tools like Postman or cURL to interact with the API:

**Encrypt data**:

```bash
echo -n 'YourPlainText' | xargs -I {} curl -X POST http://localhost:8080/api/crypto/encrypt -H "Content-Type: text/plain" -d '{}'
```

**Decrypt data**:

```bash
echo -n 'YourEncryptedText' | xargs -I {} curl -X POST http://localhost:8080/api/crypto/decrypt -H "Content-Type: text/plain" -d '{}'
```

![encrypt_decrypt.gif](docs%2Fimg%2Fencrypt_decrypt.gif)
