# 🎸 Fret & Sound Guitar Shop — Inventory Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen?style=for-the-badge&logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue?style=for-the-badge&logo=apache-maven)
![License](https://img.shields.io/badge/License-Educational-purple?style=for-the-badge)

**A comprehensive Spring Boot inventory management application for guitar shop operations**

[Features](#-features) • [Quick Start](#-quick-start) • [Documentation](#-project-structure) • [Testing](#-testing) • [WGU Requirements](#-wgu-assessment-requirements)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Quick Start](#-quick-start)
- [Project Structure](#-project-structure)
- [Usage Guide](#-usage-guide)
- [Testing](#-testing)
- [WGU Assessment Requirements](#-wgu-assessment-requirements)
- [Development](#-development)
- [License](#-license)

---

## 🎯 Overview

The **Fret & Sound Guitar Shop Inventory Management System** is a full-stack web application built with Spring Boot and Thymeleaf. This system enables efficient management of guitar parts (strings, tuners, pickups, necks, bridges) and complete guitar products (acoustic guitars, electric guitars, beginner packs).

### Business Context

**Customer:** Fret & Sound Guitar Shop  
**Purpose:** Streamline inventory tracking, enforce stock level constraints, and facilitate product sales  
**Scope:** Educational project developed for Western Governors University (WGU) Software Development assessment

---

## ✨ Features

### Core Functionality

- ✅ **Parts Management** — Track in-house and outsourced guitar components
- ✅ **Product Management** — Manage complete guitar assemblies and packages
- ✅ **Inventory Constraints** — Enforce minimum and maximum stock levels
- ✅ **Buy Now Feature** — Process product sales with automatic inventory updates
- ✅ **Validation System** — Prevent invalid inventory operations
- ✅ **Sample Data** — Auto-populate with realistic guitar shop inventory
- ✅ **About Page** — Company information and navigation

### Technical Features

- 🔄 **File-based Persistence** — Data stored in `guitar_shop_inventory.dat`
- 🎨 **Responsive UI** — Thymeleaf templates with Bootstrap styling
- ✔️ **Unit Testing** — JUnit tests for critical business logic
- 🔒 **Input Validation** — Server-side validation with user-friendly error messages

---

## 🛠 Technology Stack

| Layer | Technology | Version |
|-------|-----------|---------|
| **Backend** | Java | 17+ |
| **Framework** | Spring Boot | 2.7 |
| **Template Engine** | Thymeleaf | 3.0+ |
| **Build Tool** | Maven | 3.6+ |
| **Testing** | JUnit | 5.x |
| **Persistence** | File-based (Serialization) | — |

---

## 🚀 Quick Start

### Prerequisites

Ensure you have the following installed on your system:

- **Java Development Kit (JDK)** 17 or higher
- **Apache Maven** 3.6 or higher
- **Git** (for cloning the repository)

Verify installations:

```bash
java -version    # Should show Java 17+
mvn -version     # Should show Maven 3.6+
```

### Installation & Running

1. **Clone the repository**

```bash
git clone https://gitlab.wgu.edu/YOUR-USERNAME/fret-and-sound-inventory.git
cd fret-and-sound-inventory
```

2. **Build the project**

```bash
mvn clean install
```

3. **Run the application**

```bash
mvn spring-boot:run
```

4. **Access the application**

Once you see the message **"Started InventoryApplication"**, open your browser and navigate to:

```
http://localhost:8080/mainscreen
```

5. **Stop the application**

Press **Ctrl+C** in the terminal to stop the server.

---

## 📁 Project Structure

```
fret-and-sound-inventory/
│
├── src/
│   ├── main/
│   │   ├── java/com/shop/inventory/
│   │   │   ├── InventoryApplication.java          # Application entry point
│   │   │   ├── controller/
│   │   │   │   ├── MainController.java            # Main screen & about page
│   │   │   │   ├── PartController.java            # Part CRUD operations
│   │   │   │   └── ProductController.java         # Product CRUD & buy now
│   │   │   ├── entity/
│   │   │   │   ├── Part.java                      # Abstract part entity
│   │   │   │   ├── InhousePart.java               # In-house manufactured parts
│   │   │   │   ├── OutsourcedPart.java            # Vendor-supplied parts
│   │   │   │   └── Product.java                   # Guitar products
│   │   │   ├── repository/
│   │   │   │   └── FileInventoryRepository.java   # File-based data storage
│   │   │   ├── service/
│   │   │   │   └── InventoryService.java          # Business logic & sample data
│   │   │   └── validator/
│   │   │       ├── PartValidator.java             # Part validation rules
│   │   │       └── ProductValidator.java          # Product validation rules
│   │   └── resources/
│   │       ├── application.properties              # Spring Boot configuration
│   │       ├── messages.properties                 # Validation messages
│   │       └── templates/                          # Thymeleaf HTML templates
│   │           ├── mainscreen.html                 # Main inventory screen
│   │           ├── about.html                      # About page
│   │           ├── inhousepartform.html            # In-house part form
│   │           ├── outsourcedpartform.html         # Outsourced part form
│   │           └── productform.html                # Product form
│   └── test/
│       └── java/com/shop/inventory/entity/
│           └── PartTest.java                       # Unit tests for Part entity
│
├── pom.xml                                         # Maven dependencies
├── README.md                                       # This file
└── guitar_shop_inventory.dat                       # Data file (auto-generated)
```

---

## 📖 Usage Guide

### Main Screen

The main screen displays two tables:

1. **Parts Table** — Lists all guitar parts with columns:
   - ID, Name, Price, Inventory, Min, Max
   - Actions: Update, Delete

2. **Products Table** — Lists all guitar products with columns:
   - ID, Name, Price, Inventory
   - Actions: Update, Delete, **Buy Now**

### Managing Parts

**Add a New Part:**
1. Click **"Add Inhouse Part"** or **"Add Outsourced Part"**
2. Fill in the form (Name, Price, Inventory, Min, Max)
3. Click **"Submit"**

**Update a Part:**
1. Click **"Update"** next to the part
2. Modify fields as needed
3. Click **"Submit"**

**Delete a Part:**
1. Click **"Delete"** next to the part
2. Confirm deletion

### Managing Products

**Add a New Product:**
1. Click **"Add Product"**
2. Fill in product details
3. Select associated parts
4. Click **"Submit"**

**Buy Now Feature:**
1. Click **"Buy Now"** next to any product
2. System decrements product inventory by 1
3. Success/error message displays at the top

### Sample Inventory

On first launch, the system automatically loads sample data:

**5 Parts:**
- Guitar Strings (Outsourced)
- Tuning Pegs (In-house)
- Humbucker Pickup (Outsourced)
- Maple Neck (In-house)
- Tremolo Bridge (Outsourced)

**5 Products:**
- Classic Acoustic Guitar
- Standard Electric Guitar
- Beginner Guitar Pack
- Premium Les Paul Style
- Vintage Stratocaster Style

---

## 🧪 Testing

### Run All Tests

```bash
mvn test
```

### Expected Output

```
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
```

### Test Coverage

**PartTest.java** includes two unit tests:

1. **`partMinAndMaxFieldsAreStoredAndRetrievedCorrectly()`**
   - Verifies that min/max values are properly stored and retrieved

2. **`partInventoryWithinMinMaxIsValid()`**
   - Validates that inventory levels respect min/max constraints

---

## 📝 WGU Assessment Requirements

This project fulfills all requirements (A–K) for the WGU Software Development assessment. Below is a comprehensive mapping of each requirement to specific implementation details.

### **A. Git Repository**

**Requirement:** Provide a GitLab repository with commit history.

**Implementation:**
- Repository URL: `https://gitlab.wgu.edu/YOUR-USERNAME/fret-and-sound-inventory`
- Multiple commits demonstrating incremental development
- View commit history: `git log --oneline`

---

### **B. README File**

**Requirement:** Provide a comprehensive README file.

**Implementation:**
- ✅ This file (`README.md`)
- Includes: Overview, setup instructions, project structure, usage guide, testing, and requirement mappings

---

### **C. Customize HTML User Interface**

**Requirement:** Customize the HTML user interface for the customer's application (shop name, product names, part names).

**Implementation:**

| File | Line(s) | Change |
|------|---------|--------|
| `mainscreen.html` | 5, 27, 59–60, 86–87 | Title and H1: "Fret & Sound Guitar Shop"; Parts/Products tables display names from model |
| `about.html` | 5, 19 | Shop name in page title and H1 heading |
| `inhousepartform.html` | 11 | Page title includes shop name |
| `outsourcedpartform.html` | 11 | Page title includes shop name |
| `productform.html` | 11 | Page title includes shop name |
| `InventoryService.java` | 88–165 | Sample inventory with guitar-specific part and product names |

---

### **D. About Page**

**Requirement:** Add an "About" page with navigation.

**Implementation:**

| File | Line(s) | Change |
|------|---------|--------|
| `about.html` | (entire file) | Complete About page for Fret & Sound Guitar Shop with company information |
| `mainscreen.html` | 29–31 | Navigation menu: Main Screen, About |
| `about.html` | 20–22 | Navigation menu: Main Screen, About |
| `MainController.java` | 32–35 | `GET /about` endpoint mapping |

**Access:** Navigate to `http://localhost:8080/about`

---

### **E. Sample Inventory**

**Requirement:** Add sample inventory (5 parts, 5 products) that loads only when both lists are empty.

**Implementation:**

| File | Line(s) | Change |
|------|---------|--------|
| `InventoryService.java` | 62–72 | `addSampleInventoryIfEmpty()` method; returns false if parts or products already exist |
| `InventoryService.java` | 74–165 | `addGuitarShopSampleInventory()`: Creates 5 parts, 5 products with proper associations |
| `MainController.java` | 21 | Main screen load triggers `addSampleInventoryIfEmpty()` |

**Sample Data:**
- **Parts:** Guitar Strings, Tuning Pegs, Humbucker Pickup, Maple Neck, Tremolo Bridge
- **Products:** Classic Acoustic Guitar, Standard Electric Guitar, Beginner Guitar Pack, Premium Les Paul Style, Vintage Stratocaster Style

---

### **F. Buy Now Button**

**Requirement:** Add a "Buy Now" button next to Update/Delete that decrements product inventory by one and displays a message.

**Implementation:**

| File | Line(s) | Change |
|------|---------|--------|
| `mainscreen.html` | 90–92 | Buy Now button; form POSTs to `/products/buy/{id}` |
| `mainscreen.html` | 35–36 | Message div displays `buyMessage` (success/error) |
| `ProductController.java` | 111–120 | `buyNow(id)` method; uses RedirectAttributes for flash messages |
| `InventoryService.java` | 168–176 | `buyNow(productId)` decrements product inventory; returns boolean success |

**Behavior:**
- Success: "Product purchased successfully!"
- Error: "Product out of stock or not found."

---

### **G. Maximum and Minimum Inventory**

**Requirement:** Modify parts to track minimum and maximum inventory levels.

**Implementation:**

| File | Line(s) | Change |
|------|---------|--------|
| `Part.java` | 10–11, 76–92 | Added `min` and `max` fields with getters/setters |
| `InventoryService.java` | 88–135 | Sample parts use `setMin()` and `setMax()` methods |
| `inhousepartform.html` | 38–48 | Min/Max input fields added to form |
| `outsourcedpartform.html` | 38–48 | Min/Max input fields added to form |
| `FileInventoryRepository.java` | 18 | Storage file: `guitar_shop_inventory.dat` |
| `PartValidator.java` | 28–35 | Validation: inventory must be between min and max |
| `mainscreen.html` | 62–63 | Parts table displays Min and Max columns |

---

### **H. Validation**

**Requirement:** Add validation to prevent inventory from going below minimum or above maximum.

**Implementation:**

| File | Line(s) | Change |
|------|---------|--------|
| `PartValidator.java` | 30–35 | Validation errors for `inv < min` and `inv > max` |
| `ProductValidator.java` | 42–55 | `validateProductInventoryUpdate()`: Prevents part inventory from dropping below min when product is updated |
| `ProductController.java` | 82–83 | Calls `validateProductInventoryUpdate()` before saving product |
| `productform.html` | 30 | Displays global validation errors |
| Part form templates | 28, 32, 36, 40, 44 | `th:errors` attributes for name, price, inv, min, max fields |

**Validation Rules:**
- Part inventory must be ≥ minimum
- Part inventory must be ≤ maximum
- Product updates cannot reduce associated part inventory below minimum

---

### **I. Unit Tests**

**Requirement:** Add at least two unit tests for the maximum and minimum fields.

**Implementation:**

| File | Line(s) | Change |
|------|---------|--------|
| `PartTest.java` | (entire file) | New test class with JUnit 5 |
| `PartTest.java` | 17–28 | `partMinAndMaxFieldsAreStoredAndRetrievedCorrectly()` — Tests getter/setter functionality |
| `PartTest.java` | 31–41 | `partInventoryWithinMinMaxIsValid()` — Tests inventory constraint validation |

**Run Tests:**
```bash
mvn test
```

---

### **J. Remove Unused Validators**

**Requirement:** Remove class files for any unused validators.

**Implementation:**

| Status | Details |
|--------|---------|
| ✅ **Compliant** | Only `PartValidator.java` and `ProductValidator.java` exist; both are actively used in controllers |
| N/A | No unused validators to remove |

---

### **K. Professional Communication**

**Requirement:** Submission demonstrates professional communication with proper grammar, spelling, and punctuation.

**Implementation:**
- ✅ All code comments are grammatically correct
- ✅ README file uses professional technical writing
- ✅ Variable and method names follow Java naming conventions
- ✅ Commit messages are clear and descriptive
- ✅ **Recommendation:** Run all documentation through Grammarly for Education before final submission

---

## 💻 Development

### Building the Project

```bash
# Clean and compile
mvn clean compile

# Package as JAR
mvn clean package

# Run the packaged JAR
java -jar target/inventory-0.0.1-SNAPSHOT.jar
```

### Code Style

- **Java Conventions:** Follows Oracle Java Code Conventions
- **Naming:** CamelCase for classes, camelCase for methods/variables
- **Indentation:** 4 spaces (no tabs)

### Data Persistence

- Data is stored in `guitar_shop_inventory.dat` using Java serialization
- File is created automatically on first run
- Delete the file to reset to sample inventory

---

## 📄 License

This project was developed for educational assessment purposes as part of the Western Governors University (WGU) Software Development program.

**Author:** [Your Name]  
**Course:** Software Development  
**Institution:** Western Governors University  
**Year:** 2026

---

<div align="center">

**Made with ☕ and 🎸 for WGU Assessment**

</div>
