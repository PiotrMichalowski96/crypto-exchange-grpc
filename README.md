# Crypto Exchange Service
[![<PiotrMichalowski96>](https://circleci.com/gh/PiotrMichalowski96/crypto-exchange-grpc.svg?style=svg)](https://circleci.com/gh/PiotrMichalowski96/ImportedCars)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=PiotrMichalowski96_crypto-exchange-grpc)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=PiotrMichalowski96_crypto-exchange-grpc&metric=bugs)](https://sonarcloud.io/dashboard?id=PiotrMichalowski96_ImportedCars)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=PiotrMichalowski96_crypto-exchange-grpc&metric=coverage)](https://sonarcloud.io/dashboard?id=PiotrMichalowski96_ImportedCars)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=PiotrMichalowski96_crypto-exchange-grpc&metric=ncloc)](https://sonarcloud.io/dashboard?id=PiotrMichalowski96_ImportedCars)

Crypto Exchange Service allows to fetch cryptocurrency data via gRPC.

## Table of contents
* [Overview](#Overview)
* [Technologies](#Technologies)

## Overview


Application contains a scheduler that is calling Minerstat API every hour in order to retrieve a cryptocurrency data and persist it in MongoDB.

gRPC is used to provide a communication for clients that want to fetch cryptocurrency data.
## Technologies
### Application
- Kotlin
- Spring Boot
- Spring Cloud OpenFeign
- gRPC
- MongoDB

### Testing
- JUnit 5

Cryptocurrency data is provided by [minerstat](https://minerstat.com/)