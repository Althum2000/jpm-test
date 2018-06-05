# jpm-test
JP Morgan Code Test

Overview
========
This project attempts to solve the reporting engine test set by JPM as part of their interview process.

Requirements
============
This project requires Java 8 and Maven.  The POM also specifies the use of the Jacoco build plugin to provide code coverage reporting.  This is optional and may be removed at the discretion of the reviewer.

Developer Comments
==================
The specification for the reporting engine requires output to be directed to the console.  For this reason, the System.out PrintStream object is passed to the ReportingService at runtime.  Sonarqube may consider this bad practice and state that a Logger should be used instead of System.out.  However, for this specific solution, System.out is not hardcoded in the ReportingServiceImpl and flexibility is retained to pass in other PrintStream objects.
