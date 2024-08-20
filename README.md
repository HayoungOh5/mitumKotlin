# MitumKotlin

MitumKotlin is an early version tool for signing Mitum operations (transactions) using ECDSA with a private key. It takes a JSON string as input, signs the operation with the private key, and returns the signed operation in JSON format. Despite the mutable property of operations, the tool ensures that signing is possible.

## Features

- Sign Mitum operations (transactions) using ECDSA.
- Accepts operations in JSON string format.
- Returns signed operations in JSON format.
- Handles operations with a mutable structure.

## Requirements

- Kotlin version 2.0.10-release-540 (JRE 1.8.0_292-b10) or later.
- Gradle (for building and running the project).

## Setup

**Clone the repository:**

```sh
git clone https://github.com/HayoungOh5/mitumKotlin.git
cd mitumKotlin
```

## Building the Project

To build the project, run:
``` sh
./gradlew clean build
```

This will compile the code and prepare it for execution.


## Running the Project

```sh
./gradlew run
```

## Example Usage

When you run the program, it will execute `Main.kt`. In `Main.kt`, you need to provide the following inputs:

- `privatekey`: The private key used to sign the operation. This must correspond to the operation sender's account.
- `jsonString`: The JSON string representing the Mitum operation to be signed.

### Important Notes

- Ensure that the `privatekey` is valid for account of sender of operation you provide. The program does not perform validation to check if the privatekey is correct.