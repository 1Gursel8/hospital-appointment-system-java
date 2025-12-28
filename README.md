# Hospital Appointment System (CRS)

![Language](https://img.shields.io/badge/language-Java-orange.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)
![Interface](https://img.shields.io/badge/interface-Swing%20%26%20CLI-blue)

A comprehensive **Clinical Reservation System (CRS)** developed in Java. This project demonstrates advanced Object-Oriented Programming (OOP) principles, featuring a **Dual-Interface Architecture** that allows users to interact with the system via either a modern **Graphical User Interface (Swing)** or a classic **Terminal Interface**.

It handles complex scheduling logic, data persistence via serialization, and robust error handling with custom exceptions.

## 🚀 Features

### 🖥️ Dual User Interface
* **GUI Mode:** Built with **Java Swing**, featuring `JFrame` windows, `JTable` for data visualization, and event-driven button actions.
* **Terminal Mode:** A full-featured Command Line Interface (CLI) for quick management and administration tasks.

### 🏗️ Object-Oriented Architecture
* **Inheritance & Polymorphism:** `Doctor` and `Patient` classes extend the abstract `Person` class, utilizing overridden methods like `toString()`.
* **Encapsulation:** Strict access control with private fields and public getters/setters.
* **Custom Exceptions:** Implements `IDException` and `DuplicateInfoException` for specific error scenarios (e.g., duplicate records, invalid IDs).

### 💾 Data Persistence & Logic
* **Serialization:** The entire system state (Patients, Appointments, Hospitals) is saved to/loaded from `.ser` files using `ObjectOutputStream` and `ObjectInputStream`.
* **Smart Scheduling:** The `Schedule` class uses `Calendar` logic to prevent conflicting appointments and enforce daily patient limits per doctor.

### ✅ Unit Testing
* Includes **JUnit** test cases (`Tests.java`) to validate core logic, such as:
    * Rendezvous creation.
    * Data loading integrity.
    * Exception throwing validation.

## 🛠️ Installation & Compilation

**Important:** This project uses a custom package named `gurseluyanik`. Please follow the folder structure below to avoid compilation errors.

### 1. Clone the Repository
```bash
git clone [https://github.com/1Gursel8/hospital-appointment-system-java.git](https://github.com/1Gursel8/hospital-appointment-system-java.git)
cd hospital-appointment-system-java
```

### 2. Compile
Navigate to the `src` directory and compile all Java files.
```bash
cd src
javac gurseluyanik/*.java
```

### 3. Run
Run the `MainMethod` class using the fully qualified package name.
```bash
java gurseluyanik.MainMethod
```

## 🎮 Usage

Upon launching, the system prompts you to choose an interface:

```text
=== GUI or Terminal ===
1. GUI Mode
2. Terminal Mode
3. Exit
```

### 🖥️ GUI Mode
* **Dashboard:** Visual hierarchy of Hospitals > Sections > Doctors.
* **Patient Management:** View and manage appointments in a tabular format.
* **Navigation:** Easy-to-use buttons for switching between menus.

### ⌨️ Terminal Mode
* **Operations:** Add hospitals, sections, doctors, and patients via console commands.
* **Data Management:** Use option `6. Save` to persist data to disk and `7. Load` to retrieve it.
* **Scheduling:** Make appointments with automatic validation checks.

## 📸 Screenshots

### 1. Main Selection Menu
![Main Menu](screenshots/main_selection.png)

### 2. Graphical User Interface (GUI)
![GUI Mode](screenshots/gui_view.png)

### 3. Terminal Interface & Operations
![Terminal Mode](screenshots/terminal_view.png)

## 📂 Project Structure

The project follows a single-package structure (`gurseluyanik`) to ensure direct access and simplicity.

```text
src/gurseluyanik/
├── MainMethod.java           # Application Entry Point
├── GUI.java                  # Swing Interface Implementation
├── Terminal.java             # CLI Implementation
├── CRS.java                  # Central Logic & Serialization
├── Tests.java                # JUnit Test Cases
├── IDException.java          # Custom Exception
├── DuplicateInfoException.java # Custom Exception
├── Person.java               # Abstract Base Model
├── Doctor.java               # Model Class
├── Patient.java              # Model Class
├── Hospital.java             # Model Class
├── Section.java              # Model Class
├── Schedule.java             # Logic Class
└── Rendezvous.java           # Model Class
```

## 👤 Author

**Gürsel Uyanık**
* Computer Engineering Student
* GitHub: [1Gursel8](https://github.com/1Gursel8)

---
*This project was developed as a comprehensive Object-Oriented Programming (OOP) assignment.*