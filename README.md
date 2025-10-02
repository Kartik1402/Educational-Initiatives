# VirtualClassroom & Design Patterns in Java

A *console-based Virtual Classroom Manager* built in Java to manage virtual classrooms, students, assignments, and submissions. The application demonstrates **OOP principles, SOLID design, and usage of design patterns** like *Singleton, Factory, Observer, Builder, Proxy, Facade, and State*.

---

## Table of Contents

* [Project Overview](#project-overview)
* [Features](#features)
* [Prerequisites](#prerequisites)
* [Classes and Explanation](#classes-and-explanation)
* [Design Patterns Used](#design-patterns-used)
* [Logging](#logging)
* [Exception Handling](#exception-handling)
* [Notes](#notes)

---

## Project Overview

This repository contains **two main projects**:

1. **Virtual Classroom Manager** – Simulates a virtual classroom system with:

   * Add/list/remove classrooms
   * Enroll students
   * Schedule assignments and submit them
   * Notify observers of classroom/assignment events

2. **Design Patterns Examples** – Demonstrates six classic design patterns:

   * **Behavioral** → Observer, State
   * **Structural** → Facade, Proxy
   * **Creational** → Factory, Builder

---

## Features

### Virtual Classroom

* Add, remove, and view classrooms
* Enroll students in classrooms
* Schedule assignments
* Submit assignments
* Observer notifications on events

### Design Patterns

* Observer: Notify observers for stock/classroom events
* State: Manage object states (e.g., Order or Classroom states)
* Facade: Simplified interface for complex subsystems (Media Player)
* Proxy: Lazy-loading of heavy objects
* Factory: Create objects dynamically based on input
* Builder: Flexible construction of objects with optional parameters

---

## Prerequisites

* Java 8+ installed
* IDE: IntelliJ IDEA, Eclipse, or VS Code

---

## Classes and Explanation

### Virtual Classroom

* `ClassroomManager`: Handles classroom operations
* `Classroom`: Represents a classroom
* `Student`: Represents a student
* `Assignment`: Represents an assignment
* `Submission`: Represents student submissions

### Design Patterns

* Observer & Subject → Observer pattern implementation
* OrderState & related classes → State pattern
* MediaFacade → Facade pattern
* ProxyImage & RealImage → Proxy pattern
* ShapeFactory & Shape → Factory pattern
* Computer & ComputerBuilder → Builder pattern

---

## Design Patterns Used

* Singleton – For managers (optional in Virtual Classroom)
* Factory – Creates Shape or other objects dynamically
* Builder – Flexible creation of Computer objects
* Observer – Notify observers in stock or classroom events
* State – Order or classroom state transitions
* Facade – Simplified interface to media subsystems
* Proxy – Lazy-loading heavy resources

---

## Logging

* Central `Logger` class for consistent logging
* Info and error logs with timestamps

---

## Exception Handling

* Defensive programming (null checks, input validation)
* Try-catch blocks for transient errors
* Clear error messages for invalid operations

---

## Notes

* Each class in its own file with proper naming conventions
* Modular code, easy to extend for more features or patterns
* Console-based applications demonstrate **OOP, SOLID principles, and design patterns**
