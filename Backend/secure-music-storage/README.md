## How to Run the Secure Music Storage Application

Follow these steps to get the Secure Music Storage application up and running on your system:

### Prerequisites

- **IDE**: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) is recommended for running this project.
- **Java Development Kit (JDK)**: JDK 17 is required. Make sure it is installed on your system. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or use [OpenJDK](https://jdk.java.net/17/).
- **Git**: Ensure Git is installed for cloning the repository. Download it from [git-scm.com](https://git-scm.com/downloads) if you haven't already.

### Setup Instructions

1. **Clone the Repository**

   Open a terminal or command prompt and run the following command to clone the project into your desired local folder:

   ```bash
   git clone git@github.com:stephenarosaj/secure-music-storage.git


2. **Open the Project in IntelliJ IDEA**

- Launch IntelliJ IDEA.
- Select **Open** or **Import** from the welcome screen, or choose **File > Open...** from the main menu if you have another project open.
- Navigate to the folder where you cloned the project, select the root directory of the project, and click **OK**.
- IntelliJ IDEA will load the project.

3. **Configure the Project**

After opening the project in IntelliJ IDEA, perform the following configurations:

- **Set Project JDK**: Ensure that JDK 17 is selected as the Project SDK. You can do this by going to **File > Project Structure > Project** and selecting the appropriate JDK from the Project SDK dropdown.
- **Configure Maven**: IntelliJ IDEA usually auto-detects and configures Maven based on the `pom.xml` file in the project. Make sure that the Maven projects are imported correctly by checking the Maven tool window in IntelliJ IDEA.

4. **Run the Application**

- Navigate to the `Main` class located at `secure-music-storage/src/main/java/edu/brown/cs/securemusicstorage/SecureMusicStorageApplication`.
- Right-click on the `Main` class file and select **Run 'Main'** to start the application.
