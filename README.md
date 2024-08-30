# TeamProject-BeaconFire
https://github.com/Charsirecl/TeamProject-BeaconFire?tab=readme-ov-file

Team: Yizheng Wang, Mary Yang and Charles Li

## Overview
- This backend-focused project utilizes a microservice architecture for building an Employee and HR Management Portal.
- It includes services for Employee, Application, Housing, and Email management, with secure communication managed through Netflix Eureka and Spring Cloud API Gateway.
- The system provides user authentication, document management, and real-time status updates for onboarding, ensuring a seamless experience for both employees and HR staff.

### System Architecture
- Microservices: The application is built using a microservice architecture, with each service handling a specific domain.
- Authentication Server: Separate from the microservices, this server manages user authentication and role management.
- Spring Cloud & Netflix Eureka: Used for service discovery and load balancing.
- Spring Security: Secures each microservice and ensures that sensitive data is protected.
- Database Integration: The application uses MySQL for relational data and MongoDB for document storage, with Amazon RDS and - - MongoDB Atlas managing database access.
- AWS S3: All file storage, such as uploaded documents and images, is managed using AWS S3 buckets.

## Links
- PPT https://docs.google.com/presentation/d/1yPzCTWQRZubsmM6-aze-MemQufYNSg8wQ_x30mC_ToI/edit?usp=sharing
- Swagger Screenshot https://diamond-quokka-0cf.notion.site/Swagger-ScreenShots-468f5e0072364eec9dfb02966d5ed9b4?pvs=4
- jacoco https://diamond-quokka-0cf.notion.site/Jacoco-Coverage-c6bb34f5292743cfb918ce8fd7f8c652?pvs=4

### Features
#### Employee Features
1. Registration
- HR generates a registration token sent via email to new employees.
- Employees use this token to access the registration page and create their account.
  
2. Login & Navigation

- Employees can log in using their username or email and password.
- Upon successful login, employees are redirected to the appropriate section based on their onboarding status.
3. Onboarding Process

- Employees fill out an onboarding application, upload necessary documents, and submit them for HR review.
- The system tracks the status of the onboarding application, providing feedback if rejected and redirecting employees based on approval status.
4. Profile Management

- Employees can view and update their personal information, including contact details, emergency contacts, and documents.
- The portal allows employees to manage their visa status and housing details.
5. Housing Management

- Employees can view their assigned housing details, including the address and roommate information.
- The system allows employees to report facility issues and communicate with HR through a report thread.
#### HR Features
1. Employee Management

- HR can access and manage employee profiles, including personal information, visa status, and contact details.
- The system includes a searchable summary view of all employees, with the ability to view full profiles.
2. Onboarding Application Review

- HR can review onboarding applications submitted by employees, with the ability to approve or reject them.
- HR can provide feedback on rejected applications, which is visible to the employee.
3. Housing Management

- HR can manage housing assignments, view and update facility details, and monitor facility reports submitted by employees.
- The system includes a summary view of all houses, with detailed information about each house and its occupants.
4. Registration Management

- HR can generate and send registration tokens to new employees, allowing them to access the registration page.
- The system ensures that registration tokens are time-bound, enhancing security.



