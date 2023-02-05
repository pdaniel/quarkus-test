#Resurse

https://github.com/quarkusio/quarkus/issues/16225

https://quarkus.io/guides/funqy-amazon-lambda

https://github.com/quarkusio/quarkus-quickstarts


https://quarkus.io/guides/amazon-lambda-http#simulate-amazon-lambda-deployment-with-sam-cli

quarkus ext list --concise -i -s jdbc

quarkus ext add kubernetes health

quarkus ext rm kubernetes

https://quarkus.io/guides/cli-tooling

https://quarkus.io/guides/amazon-lambda-http


        Architectures:
        - arm64

aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin 399657272011.dkr.ecr.eu-central-1.amazonaws.com

https://quarkiverse.github.io/quarkiverse-docs/quarkus-amazon-services/dev/amazon-sqs.html
https://quarkiverse.github.io/quarkiverse-docs/quarkus-amazon-services/dev/amazon-sns.html


https://docs.localstack.cloud/user-guide/aws/sqs/

# notification Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/notification-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- AWS Lambda ([guide](https://quarkus.io/guides/amazon-lambda)): Write AWS Lambda functions
- MongoDB client ([guide](https://quarkus.io/guides/mongodb)): Connect to MongoDB in either imperative or reactive style
- AWS Lambda HTTP ([guide](https://quarkus.io/guides/amazon-lambda-http)): Allow applications written for a servlet container to run in AWS Lambda
- MongoDB with Panache ([guide](https://quarkus.io/guides/mongodb-panache)): Simplify your persistence code for MongoDB via the active record or the repository pattern
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A JAX-RS implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Logging GELF ([guide](https://quarkus.io/guides/centralized-log-management)): Log using the Graylog Extended Log Format and centralize your logs in ELK or EFK
- Amazon SNS ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-amazon-services/dev/amazon-sns.html)): Connect to Amazon SNS pub/sub messaging service
- Cache ([guide](https://quarkus.io/guides/cache)): Enable application data caching in CDI beans
- Amazon SQS ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-amazon-services/dev/amazon-sqs.html)): Connect to Amazon SQS messaging queue service

## Provided Code

### Amazon Lambda Integration example

This example contains a Quarkus Greeting Lambda ready for Amazon.

[Related guide section...](https://quarkus.io/guides/amazon-lambda)

> :warning: **INCOMPATIBLE WITH DEV MODE**: Amazon Lambda Binding is not compatible with dev mode yet!

AWSTemplateFormatVersion: '2010-09-09'
Transform: AWS::Serverless-2016-10-31
Description: AWS Serverless Quarkus HTTP - quark-1.0-SNAPSHOT
Globals:
Api:
EndpointConfiguration: REGIONAL
BinaryMediaTypes:
- "*/*"

Resources:
Quark:
Type: AWS::Serverless::Function
Properties:
Handler: io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest
Runtime: java11
CodeUri: function.zip
MemorySize: 512
Policies: AWSLambdaBasicExecutionRole
Timeout: 15
Architectures:
- arm64
Events:
HttpApiEvent:
Type: HttpApi

Outputs:
QuarkApi:
Description: URL for application
Value: !Sub 'https://${ServerlessHttpApi}.execute-api.${AWS::Region}.amazonaws.com/'
Export:
Name: QuarkApi
