# Secure Music Storage!
## Code Architecture / High-Level Design
![ArchitectureDiagram1 0](https://github.com/stephenarosaj/secure-music-storage/assets/113568428/20a017f1-fb1c-4da1-996d-5c08b1e3d0d3)

### Overview
Our system uses a **Layered System Architecture**. It is made up of three main layers, the **Frontend**, the **Server Layer**, and the **Storage Layer**, and each layer has its own **Sub-Layers** / **Components**.

Users interact with the **Frontend** to communicate with the **Backend**, requesting that actions be performed on the **Storage Layer**.  

## Frontend Layer
This layer is hosted on a website built using **React**. It makes up the **User Interface**, which displays data from the **Storage Layer** and allows users to make requests to the **Backend**. 

### Authentication Component
Users must authenticate themselves before they perform many interactions with the **Storage Layer** through the **Backend**.  Authentication will be handled using [Firebase Auth](https://firebase.google.com/docs/auth). Once authenticated, users will be able to interact with files in the **Storage Layer** by interacting with buttons, checkboxes, and text fields in the **UI**. These elements will make requests to the **Backend** specifying their **Requested Action** on the **Storage Layer**. Each request will also contain the user's **Authentication Token**, identifying the user to the **Backend**.

For unauthenticated users (those who haven't logged in), they will provide a "Public" or "General" **Authentication Token** which identifies them as a generic unauthenticated user.  

## Backend Layer
This layer is hosted on a **Spring** server built using **Java**. It handles requests from the **Frontend** which specify an action to be performed which interacts with the **Storage Layer**. 

### Security Sub-Layer
Recall that each request from the **Frontend** contains a user's **Requested Action** and their **Authentication Token**. Before each request can be fulfilled, the **Backend** must verify that the user has the proper **Permissions** to carry out the **Requested Action** on the specified file.

The Security **Sub-Layer** will use the **Authentication Token** to lookup the user's **Permissions** (stored in the **Storage Layer**) for the specified file, and then compare those to the **Requested Action**. 
- If the user has the proper **Permissions**, the action will be performed on the **Storage Layer** through the **CRUD Sub-Layer** and the result will be forwarded to the **Frontend** as a response from the **Backend**. 
- If the user does **not** have the proper **Permissions**, the **Backend** will not perform the action and will forward a failure response to the **Frontend** explaining the lack of **Permissions**. 

It's possible that instead of implementing this functionality ourselves, we could use [Firebase Security Rules](https://firebase.google.com/docs/rules) - we would only have to configure the security rules for actions on files rather than implement them ***(using this instead of our own implementation is still up for debate)***

### Crud Sub-Layer
The **Crud Sub-Layer** is the component of the **Backend** which handles all operations on the **Storage Layer**. It cannot be interacted with directly by the user (say, via an endpoint) and can only be called on by the **Security Sub-Layer** after verifying a request from a user. 

## Storage Layer
This layer is a collection of databases hosted through **Firebase**. Each file will be given a **UUID** that uniquely identifies it from all other stored files. 

To enforce the **Permissions** security system, files are stored separately from their metadata, including their permissions. 
- For storing user files (the actual data of the files), we will use [Firebase Cloud Storage](https://firebase.google.com/docs/firestore)
- For storing metadata about the files (file name, owner, upload date, modification date, size, **Permissions**) we will use [Firebase Cloud Firestore](https://firebase.google.com/docs/firestore) or [Firebase Realtime Database](https://firebase.google.com/docs/database) ***(this is still up for debate)***

### Possible Interactions with Storage Layer
- **View File:**
A user requests to "view" a file when they want to see the files they've uploaded or otherwise have access to. They will be able to view all the files for which they have the "view" permission. Specifically, they will see all **Metadata** about the file.
- **Upload File:**
A user requests to "upload" a file when they want to add a new file to the **Storage Layer**, or overwrite an existing file. There will be constraints on filenames for the sake of security (to prevent SQL injection!) and simplicity (to prevent confusion when viewing files). Only authenticated users may upload files, and they may only overwrite existing files when they have the "write" permission for that file. 
- **Download File:**
A user requests to "download" a file when they want to retrieve an existing file from the **Storage Layer**. They will be able to download all the files for which they have the "download" permission. 
- **Delete File:**
A user requests to "delete" a file when they want to remove an existing file from the **Storage Layer**. They will be able to delete all the files for which they have the "delete" permission. 
- **Share File:**
A user requests to "share" a file when they want to add permissions to the file for other users. They will be able to share all the files for which they have the "share" permission. The set of permissions they are able to share with other users is equal to the set of permissions they themselves have, with respect to a specific file. 
