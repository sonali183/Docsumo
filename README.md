# Automation for the Docsumo website to:

# Split a PDF document.
    Download split PDF pages.
 # Prerequisites
    Java Development Kit (JDK)
    ChromeDriver
    Selenium WebDriver
# Configuration
    Set the path to your ChromeDriver executable.
    Customize Chrome options if needed.
    Specify the path to your multipage PDF file.
    Define the folder where downloaded files will be saved.
# Usage
    Navigates to the Docsumo Merge PDF page.
    Clicks "Split pdf by Page."
    Uploads a multipage PDF.
    Waits for page count.
    Splits the PDF.
# Downloads the split PDFs.
    Verifies downloaded file count and extensions.


# API Tests for https://httpbin.org/

This repository contains API tests written in Java for https://httpbin.org/. These tests cover the following scenarios:

- **Scenario 1:** Verify image extension on the `/images` endpoint.
- **Scenario 2:** Set and verify cookies using the `/cookies/set` and `/cookies` endpoints.
- **Scenario 3:** Delete a cookie using the `/cookies/delete` endpoint and verify its deletion.


# Running the Tests
  Ensure you have the necessary Java libraries (HttpClient and JUnit) added to your project.
  Compile and run the tests using your preferred Java development environment or build tool.
  The tests will execute, and the results will be displayed in your console.

- Clone this repository to your local machine using Git:
  ```bash
  git clone <repository_url>
